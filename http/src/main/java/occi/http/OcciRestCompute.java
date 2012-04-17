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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import occi.config.OcciConfig;
import occi.core.Link;
import occi.core.Mixin;
import occi.http.check.OcciCheck;
import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;
import occi.infrastructure.compute.actions.CreateAction;
import occi.infrastructure.compute.actions.DeleteAction;
import occi.infrastructure.compute.actions.RestartAction.Restart;
import occi.infrastructure.compute.actions.StartAction.Start;
import occi.infrastructure.compute.actions.StopAction.Stop;
import occi.infrastructure.compute.actions.SuspendAction.Suspend;
import occi.infrastructure.links.IPNetworkInterface;
import occi.infrastructure.links.NetworkInterface;

import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OcciRestCompute extends ServerResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestCompute.class);

	private final OcciCheck occiCheck = new OcciCheck();

	/**
	 * Method to create a new compute instance.
	 * 
	 * @param representation
	 * @return string
	 * @throws Exception
	 */
	@Post
	public String postOCCIRequest(Representation representation)
			throws Exception {
		LOGGER.info("Incoming POST request.");
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		HashMap<String, Object> xoccimap = new HashMap<String, Object>();
		try {
			// access the request headers and get the X-OCCI-Attribute
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Current request: " + requestHeaders);
			String attributeCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("x-occi-attribute");
			String xocciattributes = requestHeaders.getValues(attributeCase)
					.replace(",", " ");
			String acceptCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("accept");
			LOGGER.debug("Media-Type: "
					+ requestHeaders.getFirstValue(acceptCase));
			LOGGER.debug("getref getlastseg: "
					+ getReference().getLastSegment());

			OcciCheck.countColons(xocciattributes, 1);

			// split the single occi attributes and put it into a
			// (key,value)
			// map
			LOGGER.debug("Raw X-OCCI Attributes: " + xocciattributes);
			StringTokenizer xoccilist = new StringTokenizer(xocciattributes);
			// HashMap<String, Object> xoccimap = new HashMap<String,
			// Object>();
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
				buffer.append(" occi.compute.state=").append("inactive");

				Set<String> set = new HashSet<String>();
				set.add("summary: ");
				set.add(buffer.toString());
				set.add(requestHeaders.getFirstValue("scheme"));
				LOGGER.debug("Attribute set: " + set.toString());

				// create new Compute instance with the given attributes
				Compute compute = new Compute(
						Architecture.valueOf((String) xoccimap
								.get("occi.compute.architecture")),
						Integer.parseInt((String) xoccimap
								.get("occi.compute.cores")),
						(String) xoccimap.get("occi.compute.hostname"),
						Float.parseFloat((String) xoccimap
								.get("occi.compute.speed")),
						Float.parseFloat((String) xoccimap
								.get("occi.compute.memory")), State.inactive,
						set);

				URI uri = new URI(compute.getId().toString());
				// Create libvirt domain
				CreateAction createAction = new CreateAction();
				createAction.execute(uri, null);

				StringBuffer resource = new StringBuffer();
				String path = getRootRef().getPath();
				if (path != null) {
					resource.append(path);
				}
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
				for (Mixin mixin : Mixin.getMixins()) {
					if (mixin.getEntities() != null) {
						if (mixin.getEntities().contains(compute)) {
							buffer.append("Category: " + mixin.getTitle()
									+ "; scheme=\"" + mixin.getScheme()
									+ "\"; class=\"mixin\"");
						}
					}
				}
				LOGGER.debug("Compute Uuid: " + compute.getUuid());
				LOGGER.debug("Compute Kind scheme: "
						+ compute.getKind().getScheme());
				// Check accept header
				if (requestHeaders.getFirstValue(acceptCase)
						.equals("text/occi")
						|| requestHeaders.getFirstValue("content-type", true)
								.equals("text/occi")) {
					// Generate header rendering
					this.occiCheck.setHeaderRendering(null, compute,
							buffer.toString(), null);
					getResponse().setEntity(representation);
					getResponse().setStatus(Status.SUCCESS_OK);
				}
				// Location Rendering in HTTP Header, not in body
				setLocationRef((getRootRef().toString() + compute.getId()));
				representation = OcciCheck.checkContentType(requestHeaders,
						buffer, getResponse());
				getResponse().setEntity(representation);
				// set response status
				getResponse().setStatus(Status.SUCCESS_OK, buffer.toString());
				return Response.getCurrent().toString();
			} else {
				String[] splitURI = getReference().toString().split("\\/");
				LOGGER.debug("splitURI length: " + splitURI.length);
				UUID id = null;
				for (String element : splitURI) {
					if (OcciCheck.isUUID(element)) {
						id = UUID.fromString(element);
					}
				}
				LOGGER.debug("UUID: " + id);
				// Get the compute resource by the given UUID
				Compute compute = Compute.getComputeList().get(id);

				String location = "http:"
						+ getReference().getHierarchicalPart();

				// Extract the action type / name from the last part of the
				// given
				// location URI and split it after the "=" (../?action=stop)
				String[] actionName = getReference()
						.getRemainingPart()
						.subSequence(1,
								getReference().getRemainingPart().length())
						.toString().split("\\=");
				LOGGER.debug("Action Name: " + actionName[1]);

				// Check if actionName[1] is set
				if (actionName.length >= 2) {
					// Call the Start action of the compute resource
					if (actionName[1].equalsIgnoreCase("start")) {
						LOGGER.debug("Start Action called.");
						LOGGER.debug(xoccimap.toString());
						compute.getStart().execute(URI.create(location),
								Start.valueOf((String) xoccimap.get("method")));
						// Set the current state of the compute resource
						compute.setState(State.active);
					}

					if (actionName[1].equalsIgnoreCase("stop")) {
						LOGGER.debug("Stop Action called.");
						// Call the Stop action of the compute resource
						compute.getStop().execute(URI.create(location),
								Stop.valueOf((String) xoccimap.get("method")));
						// Set the current state of the compute resource
						compute.setState(State.inactive);
					}

					if (actionName[1].equalsIgnoreCase("restart")) {
						LOGGER.debug("Restart Action called.");
						// Call the Restart action of the compute resource
						compute.getRestart().execute(URI.create(location), Restart
								.valueOf((String) xoccimap.get("method")));
						// Set the current state of the compute resource
						compute.setState(State.active);
					}

					if (actionName[1].equalsIgnoreCase("suspend")) {
						LOGGER.debug("Suspend Action called.");
						// Call the Suspend action of the compute resource
						compute.getSuspend().execute(URI.create(location), Suspend
								.valueOf((String) xoccimap.get("method")));
						// Set the current state of the compute resource
						compute.setState(State.suspended);
					}
				}
			}

		} catch (ResourceException e) {
			throw e;
		}catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception caught: " + e.toString() + "\n";
		}
		return " ";
	}

	@Get
	public String getOCCIRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		LOGGER.debug("getReference().getLastSegment(): "
				+ getReference().getLastSegment().toString());
		try {
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			String acceptCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("accept");
			OcciCheck.isUUID(getReference().getLastSegment());
			// get the compute instance by the given UUID
			Compute compute = Compute.getComputeList().get(UUID
					.fromString(getReference().getLastSegment()));

			// put all attributes into a buffer for the response
			StringBuffer buffer = new StringBuffer();
			StringBuffer linkBuffer = new StringBuffer();
			buffer.append(" Category: ").append(compute.getKind().getTerm())
					.append(" scheme= ").append(compute.getKind().getScheme())
					.append(" class=\"kind\";").append(" Architecture: ")
					.append(compute.getArchitecture()).append(" Cores: ")
					.append(compute.getCores()).append(" Hostname: ")
					.append(compute.getHostname()).append(" Memory: ")
					.append(compute.getMemory()).append(" Speed: ")
					.append(compute.getSpeed()).append(" State: ")
					.append(compute.getState());

			if (! compute.getLinks().isEmpty()) {
				linkBuffer.append(" Link: ");
			}
			for (Link l : compute.getLinks()) {
				if (l instanceof NetworkInterface) {
					NetworkInterface networkInterface = (NetworkInterface) l;
					IPNetworkInterface ipNetworkInterface = null;
					for (Mixin mixin : Mixin.getMixins()) {
						if (mixin instanceof IPNetworkInterface
								&& mixin.getEntities() != null
								&& mixin.getEntities().contains(networkInterface)) {
							ipNetworkInterface = (IPNetworkInterface) mixin;
						}
					}
					linkBuffer.append("</");
					linkBuffer.append(l.getLink().getKind().getTerm());
					linkBuffer.append("/");
					linkBuffer.append(l.getId());
					linkBuffer.append(">; ");
					linkBuffer.append("rel=\""
							+ l.getLink().getKind().getScheme());
					linkBuffer.append(l.getLink().getKind().getTerm());
					linkBuffer.append("\"");
					linkBuffer.append(" self=\"/link/");
					linkBuffer.append("networkinterface/");
					linkBuffer.append(networkInterface.getId() + "\";");
					linkBuffer.append(" category=\"");
					linkBuffer.append(l.getLink().getKind().getScheme()
							+ "networkinterface\";");
					if (ipNetworkInterface != null) {
						linkBuffer.append(" category=\"");
						linkBuffer.append(ipNetworkInterface.getScheme()
								+ "ipnetworkinterface\"");
					}
					linkBuffer.append(" occi.core.target=/"
							+ networkInterface.getTarget().getKind().getTerm() + "/"
							+ networkInterface.getTarget().getId());
					linkBuffer.append(" occi.core.source=/"
							+ compute.getKind().getTerm() + "/" + compute.getId());
					linkBuffer.append(" occi.core.id="
							+ networkInterface.getId());
					linkBuffer.append(" occi.networkinterface.interface="
							+ networkInterface.getNetworkInterface());
					linkBuffer.append(" occi.networkinterface.mac="
							+ networkInterface.getMac());
					linkBuffer.append(" occi.networkinterface.state="
							+ networkInterface.getState());
					if (ipNetworkInterface != null) {
						linkBuffer.append(" occi.networkinterface.address="
								+ ipNetworkInterface.getIp());
						linkBuffer.append(" occi.networkinterface.gateway="
								+ ipNetworkInterface.getGateway());
						linkBuffer.append(" occi.networkinterface.allocation="
								+ ipNetworkInterface.getAllocation());
					}

				}
				buffer.append(linkBuffer);

				LOGGER.debug("Links: " + linkBuffer.toString());
			}

			// access the request headers and get the Accept attribute
			Representation representation = OcciCheck.checkContentType(
					requestHeaders, buffer, getResponse());
			// Check the accept header
			if (requestHeaders.getFirstValue(acceptCase).equals("text/occi")) {
				// generate header rendering
				this.occiCheck.setHeaderRendering(null, compute,
						buffer.toString(), linkBuffer);
				// set right representation and status code
				getResponse().setEntity(representation);
				getResponse().setStatus(Status.SUCCESS_OK);
				return " ";
			}
			// set right representation and status code
			getResponse().setEntity(representation);
			getResponse().setStatus(Status.SUCCESS_OK, buffer.toString());
			return buffer.toString();
		} catch (ResourceException e) {
			throw e;
		} catch (NullPointerException e) {
			e.printStackTrace();
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return "UUID(" + UUID.fromString(getReference().getLastSegment())
					+ ") not found! " + e.toString() + "\n";
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return e.toString();
		}
	}

	/**
	 * Deletes the resource which applies to the parameters in the header.
	 * 
	 * @return string deleted or not
	 */
	@Delete
	public String deleteOCCIRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		LOGGER.debug("Incoming delete request");
		try {
			OcciCheck.isUUID(getReference().getLastSegment());
			// get compute resource that should be deleted
			Compute compute = Compute.getComputeList().get(UUID
					.fromString(getReference().getLastSegment()));
			DeleteAction deleteAction = new DeleteAction();
			deleteAction.execute(new URI(compute.getId().toString()), null);
			// remove it from compute resource list
			if (Compute.getComputeList().remove(UUID.fromString(compute.getId()
					.toString())) == null) {
				throw new Exception("There is no resorce with the given ID");
			}
			getResponse().setStatus(Status.SUCCESS_OK);

			// set compute resource to null
			compute = null;
			return " ";
		} catch (ResourceException e) {
			throw e;
		} catch (NullPointerException e) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND,
					e.getMessage());
			return "UUID(" + UUID.fromString(getReference().getLastSegment())
					+ ") not found! " + e.toString()
					+ "\n Compute resource could not be deleted.";
			// Exception for isUUID method
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND,
					e.getMessage());
			return e.toString();
		}
	}

	/**
	 * Edit the parameters of a given resource instance.
	 * 
	 * @param representation
	 * @return data of altered instance
	 * @throws Exception
	 */
	@Put
	public String putOCCIRequest(Representation representation)
			throws Exception {
		try {
			// set occi version info
			getServerInfo().setAgent(
					OcciConfig.getInstance().config.getString("occi.version"));
			OcciCheck.isUUID(getReference().getLastSegment());
			Compute compute = Compute.getComputeList().get(UUID
					.fromString(getReference().getLastSegment()));
			// access the request headers and get the X-OCCI-Attribute
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Raw Request Headers: " + requestHeaders);
			String caseAttributes = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("x-occi-attribute");
			String xocciattributes = "";
			xocciattributes = requestHeaders.getFirstValue(caseAttributes);

			LOGGER.debug("X-OCCI-Attributes != null?: "
					+ xocciattributes.isEmpty());
			// Check if some attributes are given by the request
			if (xocciattributes != null) {
				// Count the colons in the Request
				OcciCheck.countColons(xocciattributes, 1);
				/*
				 * split the single occi attributes and put it into a
				 * (key,value) map
				 */
				LOGGER.debug("Raw X-OCCI Attributes: " + xocciattributes);
				StringTokenizer xoccilist = new StringTokenizer(xocciattributes);
				HashMap<String, Object> xoccimap = new HashMap<String, Object>();
				LOGGER.debug("Tokens in XOCCIList: " + xoccilist.countTokens());
				while (xoccilist.hasMoreTokens()) {
					String[] temp = xoccilist.nextToken().split("\\=");
					if (temp.length > 1 && temp[0] != null && temp[1] != null) {
						xoccimap.put(temp[0], temp[1]);
					}
				}
				LOGGER.debug("X-OCCI-Map empty?: " + xoccimap.isEmpty());
				if (!xoccimap.isEmpty()) {
					// Change the compute attribute if it is send by the request
					if (xoccimap.containsKey("occi.compute.architecture")) {
						LOGGER.info((String) xoccimap
								.get("occi.compute.architecture"));
						compute.setArchitecture(Architecture
								.valueOf((String) xoccimap
										.get("occi.compute.architecture")));
					}
					if (xoccimap.containsKey("occi.compute.cores")) {
						LOGGER.info((String) xoccimap.get("occi.compute.cores"));
						compute.setCores(Integer.parseInt(xoccimap.get(
								"occi.compute.cores").toString()));
					}
					if (xoccimap.containsKey("occi.compute.hostname")) {
						LOGGER.info((String) xoccimap
								.get("occi.compute.hostname"));
						compute.setHostname((String) xoccimap
								.get("occi.compute.hostname"));
					}
					if (xoccimap.containsKey("occi.compute.memory")) {
						LOGGER.info((String) xoccimap
								.get("occi.compute.memory"));
						compute.setMemory(Float.parseFloat(xoccimap.get(
								"occi.compute.memory").toString()));
					}
					if (xoccimap.containsKey("occi.compute.speed")) {
						LOGGER.info((String) xoccimap.get("occi.compute.speed"));
						compute.setSpeed(Float.parseFloat(xoccimap.get(
								"occi.compute.speed").toString()));
					}
					if (xoccimap.containsKey("occi.compute.state")) {
						LOGGER.info((String) xoccimap.get("occi.compute.state"));
						compute.setState(State.valueOf((String) xoccimap
								.get("occi.compute.state")));
					}

					// Location Rendering in HTTP Header, not in body
					setLocationRef(getRootRef().toString());

					// set response status
					getResponse().setStatus(Status.SUCCESS_OK);

					return Response.getCurrent().toString();
				} else {
					getResponse().setStatus(Status.SUCCESS_OK);
					return "Nothing changed";
				}
			}
			// Catch possible exceptions
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
}