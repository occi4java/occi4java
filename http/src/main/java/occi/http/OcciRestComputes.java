/**
 * Copyright (C) 2010-2011 Sebastian Heckmann, Sebastian Laag
 *
 * Contact Email: <sebastian.heckmann@udo.edu>, <sebastian.laag@udo.edu>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package occi.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.naming.directory.SchemaViolationException;

import occi.config.OcciConfig;
import occi.core.Kind;
import occi.http.check.OcciCheck;
import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;
import occi.infrastructure.Network;
import occi.infrastructure.compute.actions.RestartAction.Restart;
import occi.infrastructure.compute.actions.StartAction.Start;
import occi.infrastructure.compute.actions.StopAction.Stop;
import occi.infrastructure.compute.actions.SuspendAction.Suspend;
import occi.infrastructure.links.NetworkInterface;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface for all compute resources. Only HTTP GET Request is possible. A GET
 * request returns all available compute resources.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class OcciRestComputes extends ServerResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestComputes.class);

	/**
	 * Returns all compute resources.
	 * 
	 * @return ComputeResource String
	 */
	@Get
	public String getOCCIRequest(Representation representation) {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));

		// initialize compute list
		Map<UUID, Compute> computeList = Compute.getComputeList();
		// initialize buffer
		StringBuffer buffer = new StringBuffer();
		Compute compute = null;

		// Access the request Headers
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		LOGGER.debug("Raw Request Headers: " + requestHeaders);

		LOGGER.debug("Size of compute list: " + computeList.size());
		// iterate through all available compute resources
		int i = 1;
		for (UUID id : computeList.keySet()) {
			compute = computeList.get(id);
			buffer.append(getReference());
			buffer.append(compute.getId());
			if (i < computeList.size()) {
				buffer.append(",");
			}
			i++;
		}

		representation = OcciCheck.checkContentType(requestHeaders, buffer,
				getResponse());
		getResponse().setEntity(representation);
		if (computeList.size() <= 0) {
			// return http status code
			getResponse().setStatus(Status.SUCCESS_NO_CONTENT,
					buffer.toString());
			return "There are no compute resources";
		} else if (representation.getMediaType().toString().equals("text/occi")) {
			// Set Location Attribute
			setLocationRef(buffer.toString());
			// return http status code
			getResponse().setStatus(Status.SUCCESS_OK, " ");
		} else {
			// return http status code
			getResponse().setStatus(Status.SUCCESS_OK);
		}
		return " ";
	}

	/**
	 * Changes the state of a specific compute resource and calls the related
	 * actions for it.
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Post
	public String postOCCIRequest() throws Exception {
		try {
			LOGGER.info("Incoming POST request.");
			// set occi version info
			getServerInfo().setAgent(
					OcciConfig.getInstance().config.getString("occi.version"));

			// OcciCheck.isUUID(getReference().getLastSegment());
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Current request: " + requestHeaders);
			String attributeCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("x-occi-attribute");
			String xocciattributes = requestHeaders.getValues(attributeCase)
					.replace(",", " ");
			String linkAttributes = "";
			StringTokenizer linkList = null;
			if (requestHeaders.toString().contains("Link")
					|| requestHeaders.toString().contains("link")) {
				linkAttributes = requestHeaders.getFirstValue("link", true);
				linkList = new StringTokenizer(linkAttributes);
				LOGGER.debug("Tokens in LinkList: " + linkList.countTokens());

			}

			LOGGER.debug("Media-Type: "
					+ requestHeaders.getFirstValue("accept", true));
			HashMap<String, Object> xoccimap = new HashMap<String, Object>();

			LOGGER.debug("Raw X-OCCI Attributes: " + xocciattributes);
			StringTokenizer xoccilist = new StringTokenizer(xocciattributes);

			// Obsolet
			// OcciCheck.countColons(xocciattributes, 2);

			// split the single occi attributes and put it into a (key,value)
			// map
			LOGGER.debug("Raw X-OCCI Attributes: " + xocciattributes);
			LOGGER.debug("RAW Link Attributes: " + linkAttributes);
			LOGGER.debug("Tokens in XOCCIList: " + xoccilist.countTokens());

			while (xoccilist.hasMoreTokens()) {
				String[] temp = xoccilist.nextToken().split("\\=");
				if (temp[0] != null && temp[1] != null) {
					LOGGER.debug(temp[0] + " " + temp[1] + "\n");
					xoccimap.put(temp[0], temp[1]);
				}
			}
			if (linkList != null) {
				while (linkList.hasMoreTokens()) {
					String[] temp = linkList.nextToken().split("\\=");
					if (temp.length > 1) {
						if (temp[0] != null && temp[1] != null) {
							LOGGER.debug(temp[0] + " " + temp[1] + "\n");
							xoccimap.put(temp[0], temp[1]);
						}
					} else {
						xoccimap.put("Link", temp[0]);
					}
				}
			}

			// Several possibilities to create links between resources

			/*
			 * to create a compute resource and create a link to a existing
			 * network.
			 */

			if (requestHeaders.toString().contains("Link")
					&& requestHeaders.toString().contains("Category")) {
				getResponse().setLocationRef(
						createComputeResourceAndNetworkInterface(
								requestHeaders, xoccimap, xoccilist));

			}
			// to create a link between a existing Network and Compute Resource.
			LOGGER.debug(requestHeaders.getFirstValue("Category", true)
					.contains("networkinterface")
					+ "");
			LOGGER.debug(requestHeaders.getFirstValue("Category", true));
			if (requestHeaders.getFirstValue("category", true).contains(
					"networkinterface")) {
				LOGGER.debug(requestHeaders.toString());
				LOGGER.debug(xoccimap.toString());
				LOGGER.debug(xoccilist.toString());
				LOGGER.debug("getResponse() "
						+ getRequest().getHostRef().toString());
				getResponse().setLocationRef(
						getRequest().getHostRef()
								+ "/networkinterface/"
								+ createLinkBetweenNetworkAndComputeResource(
										requestHeaders, xoccimap, xoccilist));
			}

			for (UUID id : Compute.getComputeList().keySet()) {
				Compute compute = Compute.getComputeList().get(id);
				String location = "http:"
						+ getReference().getHierarchicalPart()
						+ compute.getId();
				// check if the URI contains a action
				if (getReference().getRemainingPart().contains("action")) {

					String[] actionName = getReference().getRemainingPart()
							.subSequence(1,
									getReference().getRemainingPart().length())
							.toString().split("\\=");
					LOGGER.debug("Action Name: " + actionName[1]);

					// Check if actionName[1] is set
					if (actionName.length >= 2) {
						// Call the Start action of the compute resource
						if (actionName[1].equalsIgnoreCase("start")) {
							LOGGER.debug("Start Action called.");
							compute.getStart().execute(URI.create(location), Start
									.valueOf((String) xoccimap.get("method")));
							// Set the current state of the compute resource
							compute.setState(State.active);
						}

						if (actionName[1].equalsIgnoreCase("stop")) {
							LOGGER.debug("Stop Action called.");
							// Call the Stop action of the compute resource
							compute.getStop().execute(URI.create(location), Stop
									.valueOf((String) xoccimap.get("method")));
							// Set the current state of the compute resource
							compute.setState(State.inactive);
						}

						if (actionName[1].equalsIgnoreCase("restart")) {
							LOGGER.debug("Restart Action called.");
							// Call the Restart action of the compute resource
							compute.getRestart().execute(URI.create(location),
									Restart.valueOf((String) xoccimap
											.get("method")));
							// Set the current state of the compute resource
							compute.setState(State.active);
						}

						if (actionName[1].equalsIgnoreCase("suspend")) {
							LOGGER.debug("Suspend Action called.");
							// Call the Suspend action of the compute resource
							compute.getSuspend().execute(URI.create(location),
									Suspend.valueOf((String) xoccimap
											.get("method")));
							// Set the current state of the compute resource
							compute.setState(State.suspended);
						}
					}
				}
			}
		} catch (ResourceException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.toString());
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception: " + e.getMessage() + "\n";
		}
		return " ";
	}

	/**
	 * Method to create a link between a existing Network and Compute Resource.
	 * 
	 * @param requestHeaders
	 * @param xoccimap
	 * @param xoccilist
	 * @throws URISyntaxException
	 * @throws SchemaViolationException
	 */
	private String createLinkBetweenNetworkAndComputeResource(
			Form requestHeaders, HashMap<String, Object> xoccimap,
			StringTokenizer xoccilist) throws URISyntaxException,
			SchemaViolationException {
		String source = xoccimap.get("source").toString();
		String sourceUUID = source.substring(source.length() - 36, source
				.length());
		String target = xoccimap.get("target").toString();
		String targetUUID = target.substring(target.length() - 36, target
				.length());

		UUID sourceUuid = UUID.fromString(sourceUUID);
		UUID targetUuid = UUID.fromString(targetUUID);
		if (!xoccimap.containsKey("occi.networkinterface.mac")) {
			xoccimap.put("occi.networkinterface.mac", "54:52:00:11:22:33");
		}

		if (!xoccimap.containsKey("occi.networkinterface.networkinterface")) {
			xoccimap.put("occi.networkinterface.networkinterface",
					"networkinterface");
		}

		if (OcciCheck.isUUID(sourceUuid.toString())
				&& OcciCheck.isUUID(targetUuid.toString())) {

			Compute sourceCompute = Compute.getComputeList().get(sourceUuid);
			Network targetNetwork = Network.getNetworkList().get(targetUuid);
			NetworkInterface networkInterface = new NetworkInterface(xoccimap
					.get("occi.networkinterface.networkinterface").toString(),
					xoccimap.get("occi.networkinterface.mac").toString(),
					occi.infrastructure.links.NetworkInterface.State.inactive,
					sourceCompute, targetNetwork);
			networkInterface.setKind(new Kind(null, "networkinterface",
					"networkinterface", null));

			// Link linked = new Link(sourceCompute, targetNetwork);
			// sourceCompute.getLinks().add(linked);
			// targetNetwork.getLinks().add(linked);
			LOGGER.debug("LINK URI: " + networkInterface.getId());
			return networkInterface.getId().toString();
		}
		return " ";

	}

	/**
	 * Method to create a compute resource and create a link to a existing
	 * network.
	 * 
	 * @param requestHeaders
	 * @param xoccimap
	 * @param xoccilist
	 */
	private String createComputeResourceAndNetworkInterface(
			Form requestHeaders, HashMap<String, Object> xoccimap,
			StringTokenizer xoccilist) {
		// TODO siehe Spezifikation
		// split the single occi attributes and put it into a
		// (key,value) map
		try {
			LOGGER.debug("Tokens in XOCCIList: " + xoccilist.countTokens());
			while (xoccilist.hasMoreTokens()) {
				String[] temp = xoccilist.nextToken().split("\\=");
				if (temp[0] != null && temp[1] != null) {
					LOGGER.debug(temp[0] + " " + temp[1] + "\n");
					xoccimap.put(temp[0], temp[1]);
				}
			}
			// Check if last part of the URI is not action
			if (!getReference().toString().contains("action")) {
				// put occi attributes into a buffer for the response
				StringBuffer buffer = new StringBuffer();
				buffer.append("occi.compute.architecture=").append(
						xoccimap.get("occi.compute.architecture"));
				buffer.append(" occi.compute.cores=").append(
						xoccimap.get("occi.compute.cores"));
				buffer.append(" occi.compute.hostname=").append(
						xoccimap.get("occi.compute.hostname"));
				buffer.append(" occi.compute.speed=").append(
						xoccimap.get("occi.compute.speed"));
				buffer.append(" occi.compute.memory=").append(
						xoccimap.get("occi.compute.memory"));
				buffer.append(" occi.compute.state=").append(
						xoccimap.get("occi.compute.state"));

				Set<String> set = new HashSet<String>();
				set.add("summary: ");
				set.add(buffer.toString());
				set.add(requestHeaders.getFirstValue("scheme"));
				LOGGER.debug("Attribute set: " + set.toString());

				// create new Compute instance with the given attributes
				Compute compute = new Compute(Architecture
						.valueOf((String) xoccimap
								.get("occi.compute.architecture")), Integer
						.parseInt((String) xoccimap.get("occi.compute.cores")),
						(String) xoccimap.get("occi.compute.hostname"), Float
								.parseFloat((String) xoccimap
										.get("occi.compute.speed")), Float
								.parseFloat((String) xoccimap
										.get("occi.compute.memory")),
						State.inactive, set);

				// Get the Link UUID
				String link = requestHeaders.getFirstValue("link", true);
				String[] linkUUID = link.replaceAll("</[\\w]+/", "")
						.split(">;");
				LOGGER.debug("RegEx'ed UUID: " + linkUUID[0]);

				NetworkInterface network = new NetworkInterface(
						xoccimap.get("occi.networkinterface.interface")
								.toString(),
						xoccimap.get("occi.networkinterface.mac").toString(),
						occi.infrastructure.links.NetworkInterface.State.inactive,
						Network.getNetworkList().get(
								UUID.fromString(linkUUID[0])), compute);

				// network.setId(URI.create(linkUUID[0]));
				network.setLink(compute);
				network.getLink().setTitle("network");
				compute.getLinks().add(network);
				LOGGER.debug("NetworkInterface UUID: "
						+ network.getId().toString()
						+ network.getNetworkInterface());

				buffer.append("occi.networkinterface.interface=").append(
						xoccimap.get("occi.networkinterface.interface"));
				buffer.append("occi.networkinterface.mac=").append(
						xoccimap.get("occi.networkinterface.mac"));

				StringBuffer resource = new StringBuffer();
				resource.append("/").append(compute.getKind().getTerm())
						.append("/");
				getRootRef().setPath(resource.toString());

				// check of the category
				if (!"compute".equalsIgnoreCase(xoccimap.get(
						"occi.compute.Category").toString())) {
					throw new IllegalArgumentException(
							"Illegal Category type: "
									+ xoccimap.get("occi.compute.Category"));
				}
				return compute.getId().toString();
			}
		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.toString());
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			e.printStackTrace();
			return "Exception: " + e.getMessage() + "\n";
		}
		return " ";
	}
}
