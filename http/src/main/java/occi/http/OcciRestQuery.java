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

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.directory.SchemaViolationException;

import occi.config.OcciConfig;
import occi.core.Action;
import occi.core.Kind;
import occi.core.Link;
import occi.core.Mixin;
import occi.http.check.OcciCheck;
import occi.infrastructure.Compute;
import occi.infrastructure.Network;
import occi.infrastructure.Storage;
import occi.infrastructure.links.IPNetworkInterface;
import occi.infrastructure.links.NetworkInterface;
import occi.infrastructure.links.StorageLink;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OcciRestQuery extends ServerResource {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestQuery.class);

	/**
	 * List for all necessary kinds (compute, storage, network)
	 */
	private final List<Kind> queryKinds = new LinkedList<Kind>();
	/**
	 * List for all necessary links (compute, storage, network)
	 */
	private final List<Link> queryLinks = new LinkedList<Link>();

	private static IPNetworkInterface ipNetworkInterface = null;

	/**
	 * Returns all available resources.
	 * 
	 * @return ComputeResource String
	 */
	@Get
	public String getOCCIRequest() {
		// set occi version
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		LOGGER.debug("getReference().getLastSegment(): "
				+ getReference().getLastSegment());

		// if there is something behind -, set bad request
		if (!getReference().getLastSegment().equals("-")) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return "There are no resources at the query interface.";
		}

		createQueryKinds();
		createQueryLinks();
		createQueryMixins();

		StringBuffer buffer = new StringBuffer();
		/*
		 * Print all properties of the kind instance
		 */
		for (Kind kind : queryKinds) {
			if (kind != null) {
				buffer.append("Category: " + kind.getTerm() + ";");
				buffer.append(" scheme=\"" + kind.getScheme() + "\";");
				// buffer.append("\r\n");
				buffer.append(" class=\"kind\"");
				buffer.append("\r\n");
				// append related scheme to buffer, if kind has a related kind
				if (kind.getRelated() != null) {
					for (Kind related : kind.getRelated()) {
						if (related != null) {
							buffer.append("\t\t rel=")
									.append(related.getScheme()).append(";\n");
						}
					}
				}
				buffer.append("\t\t attributes=\"");
				if (kind.getAttributes() != null) {
					for (String attribute : kind.getAttributes()) {
						if (attribute != null) {
							buffer.append(attribute).append(" ");
						}
					}
				}
				buffer.append("\";\n");
				buffer.append("\t\t actions=");
				for (String actionName : kind.getActionNames()) {
					if (actionName != null) {
						buffer.append(actionName).append(" ");
					}
				}
				buffer.append(";");
				buffer.append("\r\n");
				buffer.append("\t\t location=/").append(kind.getTerm())
						.append("/;");
				buffer.append("\r\n");
			}
		}

		/*
		 * Print all link properties.
		 */
		for (Link link : queryLinks) {
			if (link != null) {
				buffer.append("Category: ").append(link.getKind().getTerm())
						.append(";");
				buffer.append("\t\t scheme=\"")
						.append(link.getKind().getScheme()).append("\";");
				buffer.append("\r\n");
				buffer.append("\t\t class=\"link\";");
				buffer.append("\r\n");
				// append related scheme to buffer, if kind has a related kind
				if (link.getKind().getRelated() != null) {
					for (Kind related : link.getKind().getRelated()) {
						if (related != null) {
							buffer.append("\t\t rel=")
									.append(related.getScheme()).append(";\n");
						}
					}
				}
				buffer.append("\t\t attributes=\"");
				if (link.getKind().getAttributes() != null) {
					for (String attribute : link.getKind().getAttributes()) {
						if (attribute != null) {
							buffer.append(attribute + " ");
						}
					}
				}
				buffer.append("\";\n");
				buffer.append("\t\t actions=");
				for (String actionName : link.getKind().getActionNames()) {
					if (actionName != null) {
						buffer.append(actionName).append(" ");
					}
				}
				buffer.append(";");
				buffer.append("\r\n");
				buffer.append("\t\t location=/")
						.append(link.getKind().getTerm()).append("/;");
				buffer.append("\r\n");
			}
		}

		/*
		 * Print all properties of the mixin instance
		 */

		if (ipNetworkInterface != null) {
			buffer.append("Category: ").append(ipNetworkInterface.getTitle())
					.append(";");
			buffer.append("\t\t scheme=\"")
					.append(ipNetworkInterface.getScheme()).append("\";");
			buffer.append("\r\n");
			buffer.append("\t\t class=\"mixin\"");
			buffer.append("\r\n");
			// append related scheme to buffer, if kind has a related
			// kind
			if (ipNetworkInterface.getRelated() != null) {
				for (Mixin related : ipNetworkInterface.getRelated()) {
					if (related != null) {
						buffer.append("\t\t rel=").append(related.getScheme())
								.append(";\n");
					}
				}
			}
			buffer.append("\t\t attributes=\"");
			if (ipNetworkInterface.getAttributes() != null) {
				for (String attribute : ipNetworkInterface.getAttributes()) {
					if (attribute != null) {
						buffer.append(attribute).append(" ");
					}
				}
			}
			buffer.append("\";\n");
			buffer.append("\t\t location=/")
					.append(ipNetworkInterface.getTerm()).append("/;");
			buffer.append("\r\n");
		}

		/*
		 * Print all properties of the mixin instance
		 */
		for (Mixin mixin : Mixin.getMixins()) {
			if (mixin != null && !mixin.getTerm().equals("ipnetwork")) {
				buffer.append("Category: " + mixin.getTitle()).append(";");

				buffer.append("\r\n");
				buffer.append("\t\t class=\"mixin\"");
				buffer.append("\r\n");
				// append related scheme to buffer, if kind has a related
				// kind
				if (mixin.getRelated() != null) {
					for (Mixin related : mixin.getRelated()) {
						if (related != null) {
							buffer.append("\t\t rel=")
									.append(related.getScheme()).append(";\n");
						}
					}
				}
				buffer.append("\t\t scheme=").append(mixin.getScheme())
						.append(";");
				buffer.append("\r\n");
				buffer.append("\t\t location=/").append(mixin.getTerm())
						.append("/;");
				buffer.append("\r\n");
			}
		}
		buffer.append("\r\n");

		// Generate all action names list
		Network.generateActionNames();
		Storage.generateActionNames();
		Compute.generateActionNames();

		/*
		 * Print all action names.
		 */
		for (String computeAction : Compute.getActionNames()) {
			if (computeAction.contains("#")) {
				buffer.append(
						"Category: "
								+ computeAction.substring(computeAction
										.lastIndexOf("#") + 1)).append(";");
			} else {
				buffer.append("Cagegory: action;");
			}
			buffer.append(
					" scheme=\""
							+ computeAction.substring(0,
									computeAction.lastIndexOf("#") + 1))
					.append("\"\n");

			if (computeAction.contains("#start")) {
				// buffer.append(" attributes: ");
			} else if (computeAction.contains("#stop")) {
				// buffer.append(" attributes: "
				// + StopAction.getStopAttributesAsString() + " ");
			} else if (computeAction.contains("#restart")) {
				// buffer.append(" attributes: "
				// + RestartAction.getRestartAttributesAsString()
				// + " ");
			} else if (computeAction.contains("#suspend")) {
				// buffer.append(" attributes: "
				// + SuspendAction.getSuspendAttributesAsString()
				// + " ");
			}

		}

		for (String storageAction : Storage.getActionNames()) {
			if (storageAction.contains("#")) {
				buffer.append("Category: ")
						.append(storageAction.substring(storageAction
								.lastIndexOf("#") + 1)).append(";");
			} else {
				buffer.append("Cagegory: action;");
			}
			buffer.append("\t\t scheme=\"")
					.append(storageAction.substring(0,
							storageAction.lastIndexOf("#") + 1)).append("\"\n");
		}

		for (String networkAction : Network.getActionNames()) {
			if (networkAction.contains("#")) {
				buffer.append(
						"Category: "
								+ networkAction.substring(networkAction
										.lastIndexOf("#") + 1)).append(";");
			} else {
				buffer.append("Cagegory: action;");
			}
			buffer.append(
					"\t\t scheme=\""
							+ networkAction.substring(0,
									networkAction.lastIndexOf("#") + 1))
					.append("\"\n");
		}

		// access the request headers and get the Accept attribute of the
		// Content-Type
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		Representation representation = OcciCheck.checkContentType(
				requestHeaders, buffer, getResponse());
		LOGGER.debug("Raw Request headers: " + requestHeaders.toString());

		/*
		 * if content type equals text/occi, render query information in the
		 * header and NOT in the body
		 */
		if (representation.getMediaType().toString().equals("text/occi")) {
			// Generate the header rendering if media-type equals text/occi
			OcciCheck occiCheck = new OcciCheck();
			occiCheck.setHeaderRendering((LinkedList<Kind>) queryKinds);
			getResponse().setEntity(representation);
			return " ";
		}
		// add content type and status to client response
		getResponse().setEntity(representation);
		return " ";
	}

	/**
	 * Creates the list with link instances necessary for the query list.
	 */
	private void createQueryMixins() {
		IPNetworkInterface.generateAttributeList();
		if (ipNetworkInterface == null) {
			HashSet<Mixin> related = new HashSet<Mixin>();
			try {
				related.add(new Mixin(null, "ipnetwork", "ipnetwork",
						"http://schemas.ogf.org/occi/core#link",
						IPNetworkInterface.attributes));
				ipNetworkInterface = new IPNetworkInterface(related,
						"ipnetwork", "ipnetwork",
						"http://schemas.ogf.org/occi/infrastructure/network#",
						IPNetworkInterface.attributes);
			} catch (SchemaViolationException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates the list with link instances necessary for the query list.
	 */
	private void createQueryLinks() {
		NetworkInterface.generateAttributeList();
		StorageLink.generateAttributeList();
		try {
			Link storageLink = new Link(null, null);
			Set<Kind> storageRelated = new HashSet<Kind>();
			storageRelated.add(new Kind(null, null, null, null, "storagelink",
					"storagelink",
					"http://schemas.ogf.org/occi/infrastructure#link",
					StorageLink.attributes));
			storageLink.setKind(new Kind(null, storageRelated, null, null,
					"storagelink", "storagelink",
					"http://schemas.ogf.org/occi/infrastructure#",
					StorageLink.attributes));
			queryLinks.add(storageLink);

			Set<Kind> networkInterfaceRelated = new HashSet<Kind>();
			networkInterfaceRelated.add(new Kind(null, null, null, null,
					"networkinterface", "networkinterface",
					"http://schemas.ogf.org/occi/infrastructure#link",
					NetworkInterface.getAttributes()));
			Link networkInterface = new Link(null, null);
			networkInterface.setKind(new Kind(null, networkInterfaceRelated,
					null, null, "networkinterface", "networkinterface",
					"http://schemas.ogf.org/occi/infrastructure#",
					NetworkInterface.getAttributes()));
			queryLinks.add(networkInterface);
		} catch (SchemaViolationException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the list with kind instances necessary for the query list.
	 */
	private void createQueryKinds() {
		// add the actions to the action list
		HashSet<String> computeActionNames = Compute.generateActionNames();
		HashSet<String> networkActionNames = Network.generateActionNames();
		HashSet<String> storageActionNames = Storage.generateActionNames();
		// generate list with actions
		HashSet<Action> computeActionSet = Compute.generateActionSet();
		HashSet<Action> networkActionSet = Network.generateActionSet();
		HashSet<Action> storageActionSet = Storage.generateActionSet();

		Compute.generateAttributeList();
		Network.generateAttributeList();
		Storage.generateAttributeList();
		try {
			// create a related Kind and add it to the list for the query
			// interface
			HashSet<Kind> relatedSet = new HashSet<Kind>();
			Kind related = new Kind(null, null, null, null, "storage",
					"storage", "http://schemas.ogf.org/occi/core#resource",
					null);
			relatedSet.add(related);
			// create compute kind and add to kind list
			Kind compute = new Kind(computeActionSet, relatedSet, null, null,
					"compute", "compute",
					"http://schemas.ogf.org/occi/infrastructure#",
					Compute.getAttributes());

			compute.setActionNames(computeActionNames);
			queryKinds.add(compute);
			// create storage kind and add to kind list
			Kind storage = new Kind(storageActionSet, relatedSet, null, null,
					"storage", "storage",
					"http://schemas.ogf.org/occi/infrastructure#",
					Storage.getAttributes());
			storage.setActionNames(storageActionNames);
			queryKinds.add(storage);
			// create network kind and add to kind list
			Kind network = new Kind(networkActionSet, relatedSet, null, null,
					"network", "network",
					"http://schemas.ogf.org/occi/infrastructure#",
					Network.getAttributes());
			network.setActionNames(networkActionNames);
			queryKinds.add(network);
		} catch (Exception e) {
			// create log message
			e.printStackTrace();
		}
	}

	/**
	 * Add Mixin definition.
	 * 
	 * @param representation
	 * @return added mixin definition and status
	 */
	@Put
	public String putOCCIRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));

		LOGGER.debug("Analyzing header information");

		StringBuffer buffer = new StringBuffer();

		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		LOGGER.info("Request header: " + requestHeaders);

		String categoryCase = OcciCheck.checkCaseSensitivity(
				requestHeaders.toString()).get("category");
		String[] header;
		// if there is no category in header, its a bad request
		if (requestHeaders.getFirstValue(categoryCase) != null) {
			header = requestHeaders.getFirstValue(categoryCase).split(";");
		} else {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					buffer.toString());
			return " ";
		}
		String scheme = "";
		for (int i = 0; i < header.length; i++) {
			header[i] = header[i].trim();
			if (header[i].contains("scheme=")) {
				if (header[i].contains("\"")) {
					scheme = header[i].substring(8, header[i].length() - 1);
				} else {
					scheme = header[i].substring(7, header[i].length());
				}
				LOGGER.info("scheme: " + scheme);
			}
		}
		Set<Mixin> related = new HashSet<Mixin>();
		try {
			// iterate through all mixins to find the related
			for (Mixin mixin : Mixin.getMixins()) {
				if (mixin.getScheme().toString() == requestHeaders
						.getFirstValue("rel")) {
					related.add(mixin);
				}
			}
			String term = header[0];
			String title = header[0];
			LOGGER.debug("term: " + term);
			LOGGER.debug("title: " + title);
			// create new mixin instance
			new Mixin(related, term, title, scheme, null);
			LOGGER.debug("Created mixin");
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE,
					buffer.toString());
			return "Exception caught " + e.getMessage();
		}
		// access the request headers and get the Accept attribute
		Representation representation = OcciCheck.checkContentType(
				requestHeaders, buffer.append(" "), getResponse());
		getResponse().setEntity(representation);
		// getResponse().setStatus(Status.SUCCESS_OK);
		return " ";
	}

	/**
	 * Delete a existing mixin definition. If it does not exist 404 Not Found
	 * Exception.
	 * 
	 * @param representation
	 * @return removed mixin defintion and status
	 */
	@Delete
	public String deleteOCCIRequest(Representation representation) {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));

		StringBuffer buffer = new StringBuffer();
		// save header information
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");

		String requestCategory = "";
		String categoryCase = OcciCheck.checkCaseSensitivity(
				requestHeaders.toString()).get("category");
		// match category and Category

		if (requestHeaders.getFirstValue(categoryCase) != null) {
			// get header values
			requestCategory = requestHeaders.getFirstValue(categoryCase);
		} else {
			// set status code bad request
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return " ";
		}
		requestCategory = OcciCheck
				.addWhitespaceAfterSemicolonAndTrim(requestCategory);

		// print header information
		LOGGER.debug("Request Header: " + requestCategory);
		StringTokenizer categoryList = new StringTokenizer(requestCategory);
		HashMap<String, Object> categoryMap = new HashMap<String, Object>();
		// fill category map with header information
		while (categoryList.hasMoreTokens()) {
			// store header information in seperate array fields
			String[] temp = categoryList.nextToken().split("\\=");
			LOGGER.debug("Category List: " + temp[0]);
			if (temp.length > 1) {
				LOGGER.debug("Category List: " + temp[0] + "\t" + temp[1]);
				if (temp[0] != null && temp[1] != null) {
					// remove leading, trailing whitespaces and add whitespaces
					// between semicolons
					temp[0] = OcciCheck
							.addWhitespaceAfterSemicolonAndTrim(temp[0]);
					temp[1] = OcciCheck
							.addWhitespaceAfterSemicolonAndTrim(temp[1]);
					categoryMap.put(temp[0], temp[1]);
				}
			} else {
				if (temp[0].contains(";")) {
					temp[0] = temp[0].replace(";", "");
				}
				categoryMap.put(categoryCase, temp[0]);
			}
		}
		// iterate through all mixin instances
		for (Mixin mixin : Mixin.getMixins()) {
			// if the request parameter match a existing mixin definition,
			// delete the definition
			LOGGER.info("Category Map: " + categoryMap.get(categoryCase)
					+ "\t mixin title: " + mixin.getTitle());
			if (mixin.getTitle().equals(categoryMap.get(categoryCase))) {
				Mixin.getMixins().remove(mixin);
				mixin = null;
				// access the request headers and get the Accept attribute
				representation = OcciCheck.checkContentType(requestHeaders,
						buffer.append(" "), getResponse());
				// set representation
				getResponse().setEntity(representation);
				// getResponse().setStatus(Status.SUCCESS_OK,
				// buffer.toString());
				return " ";
			}
		}
		buffer.append("Mixin definition not found");
		getResponse().setStatus(Status.CLIENT_ERROR_PRECONDITION_FAILED,
				buffer.toString());
		return buffer.toString();
	}
}