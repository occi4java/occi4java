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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.UUID;

import occi.config.OcciConfig;
import occi.core.Kind;
import occi.http.check.OcciCheck;
import occi.http.helper.MacAdressGenerator;
import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;
import occi.infrastructure.Network;
import occi.infrastructure.Storage;
import occi.infrastructure.links.NetworkInterface;

import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OCCI root interface. Supports only HTTP GET requests. GET request returns the
 * OCCI version.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciRestRoot extends ServerResource {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestRoot.class);

	/**
	 * HTTP GET request. Returns Version Info of OCCI.
	 * 
	 * @return OCCI version
	 */
	@Get
	public String getOCCIRequest() {
		LOGGER.debug("Incoming GET request");
		// set occi version info
		String occiVersion = OcciConfig.getInstance().config
				.getString("occi.version");
		getServerInfo().setAgent(occiVersion);
		Form xOcciLocation = new Form();
		StringBuffer buffer = new StringBuffer();
		// append occi version to the body
		buffer.append("OCCI Version: " + occiVersion);
		// access the request headers and get the X-OCCI-Attribute
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		LOGGER.debug("Raw Request Headers: " + requestHeaders);
		String xocciattributes = "";
		if (requestHeaders.toString().contains("network")) {
			xocciattributes = requestHeaders.getFirstValue("category", true);

			// split the single occi attributes and put it into a (key,value)
			// map
			if (xocciattributes.contains(";")) {
				xocciattributes = xocciattributes.replace(";", "; ");
			}

			StringTokenizer xoccilist = new StringTokenizer(xocciattributes);
			HashMap<String, Object> xoccimap = new HashMap<String, Object>();
			while (xoccilist.hasMoreTokens()) {
				String[] temp = xoccilist.nextToken().split("\\=");
				if (temp.length > 1) {
					if (temp[0] != null && temp[1] != null) {
						// remove leading and trailing whitespaces
						temp[0] = temp[0].trim();
						temp[1] = temp[1].trim();
						// remove semicolons
						if (temp[0].contains(";")) {
							temp[0] = temp[0].replace(";", "");
						}
						if (temp[0].contains("\"")) {
							temp[0] = temp[0].replace("\"", "");
						}
						if (temp[1].contains(";")) {
							temp[1] = temp[1].replace(";", "");
						}
						if (temp[1].contains("\"")) {
							temp[1] = temp[1].replace("\"", "");
						}
						LOGGER.debug(temp[0] + " " + temp[1]);
						xoccimap.put(temp[0], temp[1]);
					}
				} else {
					if (temp[0].contains(";")) {
						temp[0] = temp[0].replace(";", "");
					}
					xoccimap.put("category", temp[0]);
				}
			}
			Response.getCurrent().getAttributes()
					.put("org.restlet.http.headers", xOcciLocation);
			Collection<Network> networkCollection = Network.getNetworkList()
					.values();
			Iterator<Network> iterator = networkCollection.iterator();
			while (iterator.hasNext()) {
				LOGGER.debug(xoccimap.get("scheme").toString() + " "
						+ xoccimap.get("category").toString());
				Network network = iterator.next();
				if (network.getKind().getScheme().toString()
						.contains(xoccimap.get("scheme").toString())
						&& network.getKind().getTerm()
								.contains(xoccimap.get("category").toString())) {
					LOGGER.debug(xoccimap.get("scheme").toString() + " ----- "
							+ xoccimap.get("category").toString());
					xOcciLocation.add("X-OCCI-Location", network.getKind()
							.getTerm() + "/" + network.getId());
				}
			}
		}

		Representation representation = OcciCheck.checkContentType(
				requestHeaders, buffer, getResponse());
		getResponse().setEntity(representation);
		// if (requestHeaders.getFirstValue("user-agent", true) != null) {
		// if (!requestHeaders.getFirstValue("user-agent", true).contains(
		// occiVersion)) {
		// getResponse().setStatus(Status.SERVER_ERROR_NOT_IMPLEMENTED);
		// return " ";
		// }
		// }
		return buffer.toString() + " ";
	}

	/**
	 * Creates a new instance of compute, network or storage. Depends on
	 * parameters in head.
	 * 
	 * @param representation
	 * @return created instance
	 */
	@Post
	public String postOCCIRequest(Representation representation) {
		LOGGER.debug("Incoming POST request");
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));

		// access the request headers and get the X-OCCI-Attribute
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");

		String xocciattributes = "";
		String categoryCase = OcciCheck.checkCaseSensitivity(
				requestHeaders.toString()).get("category");

		if (categoryCase != null) {
			// get header values
			xocciattributes = requestHeaders.getFirstValue(categoryCase);
		} else {
			// set status code bad request
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return " ";
		}

		// split the single occi attributes and put it into a (key,value) map
		LOGGER.debug("Raw Request Headers: " + requestHeaders);
		LOGGER.debug("Raw Category Attributes: " + xocciattributes);
		if (xocciattributes.contains(";")) {
			xocciattributes = xocciattributes.replace(";", "; ");
		}

		StringTokenizer xoccilist = new StringTokenizer(xocciattributes);

		HashMap<String, Object> xoccimap = new HashMap<String, Object>();
		LOGGER.debug("Tokens in XOCCIList: " + xoccilist.countTokens());
		while (xoccilist.hasMoreTokens()) {
			String[] temp = xoccilist.nextToken().split("\\=");
			if (temp.length > 1) {
				if (temp[0] != null && temp[1] != null) {
					// remove leading and trailing whitespaces
					temp[0] = temp[0].trim();
					temp[1] = temp[1].trim();
					// remove semicolons
					if (temp[0].contains(";")) {
						temp[0] = temp[0].replace(";", "");
					}
					if (temp[0].contains("\"")) {
						temp[0] = temp[0].replace("\"", "");
					}
					if (temp[1].contains(";")) {
						temp[1] = temp[1].replace(";", "");
					}
					if (temp[1].contains("\"")) {
						temp[1] = temp[1].replace("\"", "");
					}
					LOGGER.debug(temp[0] + " " + temp[1]);
					xoccimap.put(temp[0], temp[1]);
				}
			} else {
				if (temp[0].contains(";")) {
					temp[0] = temp[0].replace(";", "");
				}
				xoccimap.put(categoryCase, temp[0]);
			}
		}
		// checks if header contains compute, network or storage and creates the
		// instance.
		if (xoccimap.containsValue("compute")) {
			// create compute resource
			postCompute(representation, categoryCase, xoccimap);
		} else if (xoccimap.containsValue("network")) {
			// create network resource
			postNetwork(representation, categoryCase, xoccimap);
		} else if (xoccimap.containsValue("storage")) {
			// create storage resource
			postStorage(representation, categoryCase, xoccimap);
		} else if (xoccimap.containsValue("networkinterface")) {
			// create network interface
			postNetworkInterface(representation, categoryCase, xoccimap,
					requestHeaders);
		} else {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			return " ";
		}
		// access the request headers and get the Accept attribute
		representation = OcciCheck.checkContentType(requestHeaders,
				new StringBuffer().append(" "), getResponse());
		// set representation
		getResponse().setEntity(representation);

		return " ";
	}

	/**
	 * Creates a new network interface instance.
	 * 
	 * @param representation
	 * @param categoryCase
	 * @param xoccimap
	 * @return string
	 */
	private String postNetworkInterface(Representation representation,
			String categoryCase, HashMap<String, Object> xoccimap,
			Form requestHeaders) {
		// create necessary objects
		Compute link = null;
		Network target = null;
		String[] sourceArray = null;
		String[] targetArray = null;
		// evaluate request header, look for x-occi-attribute, source and target
		if (requestHeaders.getFirstValue("x-occi-attribute", false) != null) {
			String[] xocciAttribute = requestHeaders.getValuesArray(
					"x-occi-attribute", false);
			LOGGER.debug(xocciAttribute[0] + " " + xocciAttribute[1]);
			sourceArray = xocciAttribute[0].split("=");
			targetArray = xocciAttribute[1].split("=");
			LOGGER.debug("source Array " + sourceArray[0] + " "
					+ sourceArray[1] + " " + sourceArray[1].lastIndexOf("/"));
			LOGGER.debug("target Array " + targetArray[0] + " "
					+ targetArray[1]);
		}
		try {
			StringBuffer resource = new StringBuffer();
			// create new network instance with the given attributes
			if (sourceArray[1] != null && targetArray[1] != null) {
				// get position of the last /
				int position = sourceArray[1].lastIndexOf("/") + 1;
				LOGGER.debug("UUID to look for: "
						+ sourceArray[1].substring(position));
				// look for uuid in compute list
				UUID linkUuid = UUID.fromString(sourceArray[1]
						.substring(position));
				link = Compute.getComputeList().get(linkUuid);
				LOGGER.debug("source UUID: " + linkUuid + " " + link.getId());
				// get network with specified uuid
				position = targetArray[1].lastIndexOf("/") + 1;
				UUID targetUuid = UUID.fromString(targetArray[1]
						.substring(position));
				target = Network.getNetworkList().get(targetUuid);
				LOGGER.debug("Target UUID: " + targetUuid + " "
						+ target.getId());
			} else {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
				return "No resources to attach the network interface with.";
			}
			if (link != null && target != null) {
				NetworkInterface networkInterface = new NetworkInterface(
						UUID.randomUUID().toString(),
						MacAdressGenerator.generateMacAdress(),
						occi.infrastructure.links.NetworkInterface.State.active,
						link, target);
				networkInterface.setKind(new Kind(null, null, null,
						"networkinterface", "networkinterface", null));
				if (networkInterface.getKind() != null) {
					LOGGER.debug("networkInterface.getKind().getTerm()"
							+ networkInterface.getKind().getTerm());
					resource.append("/")
							.append(networkInterface.getKind().getTerm())
							.append("/");
				} else {
					LOGGER.error("HALLO");
				}

				getRootRef().setPath(resource.toString());
				// check of the category
				if (!"network".equalsIgnoreCase(xoccimap.get(categoryCase)
						.toString())) {
					getResponse().setStatus(
							Status.CLIENT_ERROR_BAD_REQUEST,
							"Illegal Category type: "
									+ xoccimap.get(categoryCase));
				}
				LOGGER.debug("Network Kind scheme: "
						+ networkInterface.getKind().getScheme());
				// Location Rendering in HTTP Header, not in body
				LOGGER.debug("Network Location: " + getRootRef().toString()
						+ networkInterface.getId());
				setLocationRef((getRootRef().toString() + networkInterface
						.getId()));
			} else {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
				return "No resources to attach the network interface with.";
			}
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception caught: " + e.toString() + "\n" + e.getMessage();
		}
		return " ";
	}

	/**
	 * Creates a new compute instance.
	 * 
	 * @param representation
	 * @param categoryCase
	 * @param xoccimap
	 * @return
	 */
	private String postCompute(Representation representation,
			String categoryCase, HashMap<String, Object> xoccimap) {
		// put occi attributes into a buffer for the response
		try {
			// create new Compute instance with the given attributes
			Compute compute = new Compute(Architecture.x86, 2, UUID
					.randomUUID().toString(), 2, 8, State.inactive, null);

			StringBuffer resource = new StringBuffer();
			resource.append("/").append(compute.getKind().getTerm())
					.append("/");
			getRootRef().setPath(resource.toString());
			// check of the category
			if (!"compute".equalsIgnoreCase(xoccimap.get(categoryCase)
					.toString())) {
				throw new IllegalArgumentException("Illegal Category type: "
						+ xoccimap.get(categoryCase));
			}
			LOGGER.debug("Compute Uuid: " + compute.getUuid());
			LOGGER.debug("Compute Kind scheme: "
					+ compute.getKind().getScheme());

			// Location Rendering in HTTP Header, not in body
			setLocationRef((getRootRef().toString() + compute.getId()));
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception caught: " + e.toString() + "\n";
		}
		return " ";
	}

	/**
	 * Creates a new network instance.
	 * 
	 * @param representation
	 * @param categoryCase
	 * @param xoccimap
	 * @return string
	 */
	private String postNetwork(Representation representation,
			String categoryCase, HashMap<String, Object> xoccimap) {
		// put occi attributes into a buffer for the response
		try {
			StringBuffer resource = new StringBuffer();
			// create new network instance with the given attributes
			HashMap<String, String> attributes = new HashMap<String, String>();

			attributes.put("scheme", xoccimap.get("scheme").toString());
			attributes.put("term", "network");
			attributes.put("title", "network");
			Network network = new Network(attributes);
			resource.append("/").append(network.getKind().getTerm())
					.append("/");
			getRootRef().setPath(resource.toString());
			// check of the category
			if (!"network".equalsIgnoreCase(xoccimap.get(categoryCase)
					.toString())) {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
						"Illegal Category type: " + xoccimap.get(categoryCase));
			}
			LOGGER.debug("Network Kind scheme: "
					+ network.getKind().getScheme());
			// Location Rendering in HTTP Header, not in body
			LOGGER.debug("Network Location: " + getRootRef().toString()
					+ network.getId());
			setLocationRef((getRootRef().toString() + network.getId()));
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception caught: " + e.toString() + "\n" + e.getMessage();
		}
		return " ";
	}

	/**
	 * Creates a new storage instance.
	 * 
	 * @param representation
	 * @param categoryCase
	 * @param xoccimap
	 * @return string
	 */
	private String postStorage(Representation representation,
			String categoryCase, HashMap<String, Object> xoccimap) {
		// put occi attributes into a buffer for the response
		try {
			HashSet<String> attributes = new HashSet<String>();
			attributes.add("occi.storage.size");
			attributes.add("occi.storage.state");
			// create new Compute instance with the given attributes
			Storage storage = new Storage(20,
					occi.infrastructure.Storage.State.online, null, attributes);

			StringBuffer resource = new StringBuffer();
			resource.append("/").append(storage.getKind().getTerm())
					.append("/");
			getRootRef().setPath(resource.toString());
			// check of the category
			if (!"network".equalsIgnoreCase(xoccimap.get(categoryCase)
					.toString())) {
				throw new IllegalArgumentException("Illegal Category type: "
						+ xoccimap.get("Category"));
			}
			LOGGER.debug("Storage size: " + storage.getSize());
			LOGGER.debug("Storage Kind scheme: "
					+ storage.getKind().getScheme());

			// Location Rendering in HTTP Header, not in body
			setLocationRef((getRootRef().toString() + storage.getId()));
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception caught: " + e.toString() + "\n" + e.getMessage();
		}
		return " ";
	}

	/**
	 * Returns all available options of the OCCI Api.
	 * 
	 * @return formatted locations
	 */
	@Options
	public String renderOptions() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Possible namespaces are: \n");

		// iterate through all routers
		for (Route route : occiApi.comp.getDefaultHost().getRoutes()) {
			// look for -> and split
			buffer.append(route.toString()
					.substring(0, route.toString().indexOf(" ->"))
					.replace("\"", ""));
			buffer.append("\n");
		}
		return buffer.toString();
	}
}