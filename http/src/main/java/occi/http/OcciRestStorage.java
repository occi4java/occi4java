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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import occi.config.OcciConfig;
import occi.core.Kind;
import occi.core.Mixin;
import occi.http.check.OcciCheck;
import occi.infrastructure.Storage;
import occi.infrastructure.Storage.State;

import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface for Storage resources. Returns information about a specific storage
 * resources.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciRestStorage extends ServerResource {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestStorage.class);

	private final OcciCheck occiCheck = new OcciCheck();

	@Get
	public String getOCCIRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		StringBuffer buffer = new StringBuffer();
		/*
		 * Print all properties of the kind instance
		 */
		for (Kind kind : Kind.getKinds()) {
			if (kind != null && kind.getTerm().equals("storage")) {

				buffer.append("Category: " + kind.getTerm() + ";");
				buffer.append("\t\t scheme=\"" + kind.getScheme() + "\";");
				buffer.append("\r\n");
				buffer.append("\t\t class=\"kind\";");
				buffer.append("\r\n");
				// append related scheme to buffer, if kind has a related kind
				if (kind.getRelated() != null) {
					for (Kind related : kind.getRelated()) {
						if (related != null) {
							buffer.append("\t\t rel=" + related.getScheme()
									+ ";\n");
						}
					}
				}
				buffer.append("\t\t attributes=\"");
				if (kind.getAttributes() != null) {
					for (String attribute : kind.getAttributes()) {
						if (attribute != null) {
							buffer.append(attribute + " ");
						}
					}
				}
				buffer.append("\";\n");
				buffer.append("\t\t actions=");
				for (String actionName : kind.getActionNames()) {
					if (actionName != null) {
						buffer.append(actionName + " ");
					}
				}
				buffer.append(";");
				buffer.append("\r\n");
				buffer.append("\t\t location=/" + kind.getTerm() + "/;");
				buffer.append("\r\n");
				getResponse().setStatus(Status.SUCCESS_OK);
				return buffer.toString();
			}
		}
		
		getResponse().setStatus(Status.SUCCESS_NO_CONTENT);
		return " ";
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
		LOGGER.debug("Incoming delete request at storage");
		try {
			// get storage resource that should be deleted
			Storage storage = Storage.getStorageList().get(
					UUID.fromString(getReference().getLastSegment()));
			// remove it from storage resource list
			if (Storage.getStorageList().remove(
					UUID.fromString(storage.getId().toString())) == null) {
				throw new NullPointerException(
						"There is no resorce with the given ID");
			}
			// set storage resource to null
			storage = null;
			getResponse().setStatus(Status.SUCCESS_OK);
			return " ";
		} catch (NullPointerException e) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND,
					e.getMessage());
			return "UUID not found! " + e.toString()
					+ "\n Storage resource could not be deleted.";
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND,
					e.getMessage());
			return e.toString();
		}
	}

	/**
	 * Method to create a new storage instance.
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
		try {
			// access the request headers and get the X-OCCI-Attribute
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Current request: " + requestHeaders);
			String attributeCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("x-occi-attribute");
			String xocciattributes = requestHeaders.getValues(attributeCase)
					.replace(",", " ");
			LOGGER.debug("Media-Type: "
					+ requestHeaders.getFirstValue("accept", true));

			OcciCheck.countColons(xocciattributes, 1);

			// split the single occi attributes and put it into a (key,value)
			// map
			LOGGER.debug("Raw X-OCCI Attributes: " + xocciattributes);
			StringTokenizer xoccilist = new StringTokenizer(xocciattributes);
			HashMap<String, Object> xoccimap = new HashMap<String, Object>();
			LOGGER.debug("Tokens in XOCCIList: " + xoccilist.countTokens());
			while (xoccilist.hasMoreTokens()) {
				String[] temp = xoccilist.nextToken().split("\\=");
				if (temp[0] != null && temp[1] != null) {
					LOGGER.debug(temp[0] + " " + temp[1] + "\n");
					xoccimap.put(temp[0], temp[1]);
				}
			}

			// put occi attributes into a buffer for the response
			StringBuffer buffer = new StringBuffer();
			buffer.append("occi.storage.state=").append(
					xoccimap.get("occi.storage.state"));
			buffer.append(" occi.storage.size=").append(
					xoccimap.get("occi.storage.size"));

			Set<String> set = new HashSet<String>();
			set.add("summary: ");
			set.add(buffer.toString());
			set.add(requestHeaders.getFirstValue("scheme"));

			// create new Compute instance with the given attributes
			Storage storage = new Storage(Float.parseFloat((String) xoccimap
					.get("occi.storage.size")), State.offline, null, null);
			storage.setKind(new Kind(null, "storage", "storage", null));
			StringBuffer resource = new StringBuffer();
			resource.append("/").append(storage.getKind().getTerm())
					.append("/");
			getRootRef().setPath(resource.toString());

			// check of the category
			if (!"storage".equalsIgnoreCase(xoccimap.get(
					"occi.storage.Category").toString())) {
				throw new IllegalArgumentException("Illegal Category type: "
						+ xoccimap.get("occi.storage.Category"));
			}
			for (Mixin mixin : Mixin.getMixins()) {
				if (mixin.getEntities() != null) {
					if (mixin.getEntities().contains(storage)) {
						buffer.append("Category: " + mixin.getTitle()
								+ "; scheme=\"" + mixin.getScheme()
								+ "\"; class=\"mixin\"");
					}
				}
			}
			// Check accept header
			if (requestHeaders.getFirstValue("accept", true)
					.equals("text/occi")
					|| requestHeaders.getFirstValue("content-type", true)
							.equals("text/occi")) {
				// Generate header rendering
				occiCheck.setHeaderRendering(null, storage, buffer.toString(),
						null);
				getResponse().setEntity(representation);
				getResponse().setStatus(Status.SUCCESS_OK);
			}
			storage.getCreate().execute(storage.getId(), null);
			// Location Rendering in HTTP Header, not in body
			setLocationRef((getRootRef().toString() + storage.getId()));
			representation = OcciCheck.checkContentType(requestHeaders, buffer,
					getResponse());
			getResponse().setEntity(representation);
			// set response status
			getResponse().setStatus(Status.SUCCESS_OK, buffer.toString());
			return Response.getCurrent().toString();
		} catch (Exception e) {
			e.printStackTrace();
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception caught: " + e.toString() + "\n";
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
	public String putOCCIRequest(Representation representation) {
		try {
			// set occi version info
			getServerInfo().setAgent(
					OcciConfig.getInstance().config.getString("occi.version"));
			OcciCheck.isUUID(getReference().getLastSegment());
			Storage storage = Storage.getStorageList().get(
					UUID.fromString(getReference().getLastSegment()));
			// access the request headers and get the X-OCCI-Attribute
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Raw Request Headers: " + requestHeaders);
			String xocciattributes = "";
			xocciattributes = requestHeaders.getFirstValue("x-occi-attribute",
					true);

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
				while (xoccilist.hasMoreTokens()) {
					String[] temp = xoccilist.nextToken().split("\\=");
					if (temp.length > 1 && temp[0] != null && temp[1] != null) {
						xoccimap.put(temp[0], temp[1]);
					}
				}
				if (!xoccimap.isEmpty()) {
					// Change the storage attribute if it is send by the request
					if (xoccimap.containsKey("occi.storage.size")) {
						storage.setSize(Float.parseFloat(xoccimap.get(
								"occi.storage.size").toString()));
					}
					if (xoccimap.containsKey("occi.storage.state")) {
						storage.setState(State.valueOf((String) xoccimap
								.get("occi.storage.state")));
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
		} catch (Exception e) {
			LOGGER.error("Exception caught: " + e.toString());
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST,
					e.toString());
			return "Exception: " + e.getMessage() + "\n";
		}
		return " ";
	}
}