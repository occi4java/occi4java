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

package occi.http.check;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import occi.core.Category;
import occi.core.Entity;
import occi.core.Kind;
import occi.core.Link;
import occi.core.Mixin;
import occi.core.Resource;

import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class checks some statements, if they start with capital or not and gives the
 * string back.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciCheck extends ServerResource {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciCheck.class);

	/**
	 * Class checks some statements, if they start with capital or not and gives
	 * the string back.
	 * 
	 * @param string
	 * @return String
	 */
	public static Map<String, String> checkCaseSensitivity(String string) {
		Map<String, String> caseMap = new HashMap<String, String>();
		if (string.contains("x-occi-location")) {
			caseMap.put("x-occi-location", "x-occi-location");
		} else if (string.contains("X-OCCI-Location")) {
			caseMap.put("x-occi-location", "X-OCCI-Location");
		}

		if (string.contains("occi.compute.Category")) {
			caseMap.put("occi.compute.category", "occi.compute.Category");
		}

		if (string.contains("Category")) {
			caseMap.put("category", "Category");
		} else if (string.contains("category")) {
			caseMap.put("category", "category");
		}

		if (string.contains("x-occi-attribute")) {
			caseMap.put("x-occi-attribute", "x-occi-attribute");
		} else if (string.contains("X-OCCI-Attribute")) {
			caseMap.put("x-occi-attribute", "X-OCCI-Attribute");
		}

		if (string.contains("content-type")) {
			caseMap.put("accept", "content-type");
		} else if (string.contains("Content-Type")) {
			caseMap.put("accept", "Content-Type");
		}

		// Comma-separated because the contains() Method of String matches also
		// the Accept-* Keys. But the needed key is comma-separated from the
		// value in the String
		if (string.contains("Accept,")) {
			caseMap.put("accept", "Accept");
		} else if (string.contains("accept,")) {
			caseMap.put("accept", "accept");
		}

		return caseMap;
	}

	public static Representation checkContentType(Form requestHeaders,
			StringBuffer buffer, Response response) {
		Representation representation = null;

		LOGGER.debug("Request Headers: " + requestHeaders);
		String acceptCase = "";
		if (OcciCheck.checkCaseSensitivity(requestHeaders.toString()).get(
				"accept") != null) {
			acceptCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("accept");
		} else {
			acceptCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("content-type");
		}

		LOGGER.debug("acceptCase: " + acceptCase);
		String acceptHeader = requestHeaders.getFirstValue(acceptCase);
		LOGGER.debug("acceptHeader: " + acceptHeader);
		if (acceptHeader == null) {
			acceptHeader = " ";
		}

		// if text/occi is requested return text/occi, otherwise text/plain
		if (acceptHeader.equalsIgnoreCase("text/occi")) {
			representation = new StringRepresentation(" ", new MediaType(
					"text/occi"));
			response.setStatus(Status.SUCCESS_OK, buffer.toString());
		} else if ((acceptHeader.equalsIgnoreCase("text/plain"))
				|| (acceptHeader.equalsIgnoreCase(" "))
				|| (acceptHeader.equalsIgnoreCase("*/*"))) {
			representation = new StringRepresentation(buffer.toString(),
					MediaType.TEXT_PLAIN);
			response.setStatus(Status.SUCCESS_OK, buffer.toString());
		} else {
			representation = new StringRepresentation("Unsupported Media Type",
					MediaType.TEXT_PLAIN);
			response.setStatus(Status.CLIENT_ERROR_UNSUPPORTED_MEDIA_TYPE);
		}
		// set character set to null
		representation.setCharacterSet(null);
		return representation;
	}

	/**
	 * Count the colons of the given String
	 * 
	 * @param attributes
	 * @throws Exception
	 */
	public static void countColons(String attributes, int i) throws Exception {
		int count = 0;
		LOGGER.debug("Counting colons");
		// Iterate over every char
		for (char c : attributes.toCharArray()) {
			// Check if the char is a colon and increase count
			if (c == ':') {
				LOGGER.debug("Colon found");
				count++;
			}
		}
		// Throw new Exception, if more than one colon is found in the request
		if (count > i) {
			LOGGER.error("One Colon found in the Request");
			throw new Exception(
					"One Colon found in the Request. Please use the Request like this: XOCCI-Attribute: occi.compute.cores=2 [...]");
		}
	}

	/**
	 * Adds a whitespace after every semicolon and removes leading and trailing
	 * whitespaces.
	 * 
	 * @param string
	 * @return string
	 */
	public static String addWhitespaceAfterSemicolonAndTrim(String string) {
		if (string.contains(";")) {
			string = string.replace(";", "; ");
		}
		string = string.trim();

		return string;
	}

	/**
	 * Specific method for header rendering for the query interface. Generates
	 * the header rendering with the given params.
	 * 
	 * @param queryKinds
	 * @param compute
	 * @param attributes
	 */
	public void setHeaderRendering(LinkedList<Kind> queryKinds) {
		Form xOcciLocation = new Form();
		Response.getCurrent().getAttributes()
				.put("org.restlet.http.headers", xOcciLocation);
		String location = "";
		String category = "";
		if (queryKinds != null) {
			for (Mixin mixin : Mixin.getMixins()) {
				category += mixin.getTitle() + "; scheme=\""
						+ mixin.getScheme() + "\"" + "; class=\"mixin\"" + ", ";
				location += "/" + mixin.getTitle() + "/,";
			}
			for (Kind kind : queryKinds) {
				if (!kind.equals(queryKinds.getLast())) {
					category += kind.getTerm() + "; scheme=\""
							+ kind.getScheme() + "\"" + "; class=\"mixin\""
							+ ", ";
				} else {
					category += kind.getTerm();
				}
				if (kind.equals(queryKinds.getLast())) {
					location += "/" + kind.getTerm() + "/";
				} else if (!kind.equals(queryKinds.getLast())) {
					location += "/" + kind.getTerm() + "/,";
				} else if (kind.equals(queryKinds.getFirst())) {
					location += "/" + kind.getTerm() + "/,";
				}
			}
		}

		xOcciLocation.add("Category", category);
		xOcciLocation.add("X-OCCI-Location", location);
		xOcciLocation.add("Link",
				"setHeaderRendering(LinkedList<Kind> queryKinds)");
	}

	/**
	 * Specific method for header rendering for the query interface. Generates
	 * the header rendering with the given params.
	 * 
	 * @param queryKinds
	 * @param compute
	 * @param attributes
	 */
	public void setHeaderRendering(LinkedList<Kind> queryKinds,
			Resource resource, String attributes, StringBuffer link) {
		Form xOcciLocation = new Form();
		Response.getCurrent().getAttributes()
				.put("org.restlet.http.headers", xOcciLocation);
		String location = "";
		String category = "";
		StringBuffer linkBuffer = new StringBuffer();
		if (queryKinds != null) {
			for (Mixin mixin : Mixin.getMixins()) {
				// category += mixin.getTitle() + ",";
				category += mixin.getTitle() + "; scheme=\""
						+ mixin.getScheme() + "\"" + "; class=\"mixin\"" + ", ";
				location += "/" + mixin.getTitle() + "/,";
			}
			for (Kind kind : queryKinds) {
				if (!kind.equals(queryKinds.getLast())) {
					category += kind.getTerm() + ",";
				} else {
					category += kind.getTerm();
				}
				if (kind.equals(queryKinds.getLast())) {
					location += "/" + kind.getTerm() + "/";
				} else if (!kind.equals(queryKinds.getLast())) {
					location += "/" + kind.getTerm() + "/,";
				} else if (kind.equals(queryKinds.getFirst())) {
					location += "/" + kind.getTerm() + "/,";
				}
			}
		}

		if ((resource != null) && (attributes != null)) {
			category = null;
			// xOcciLocation = null;
			Response.getCurrent().getAttributes()
					.put("org.restlet.http.headers", xOcciLocation);
			category = ""; // resource.getKind().getTerm();

			// add information about category and location to the header
			if (resource.getKind() != null) {
				for (Category mixin : resource.getKind().getCategories()) {
					if (!mixin.getTitle().equalsIgnoreCase("action")) {
						category += mixin.getTitle() + "; scheme=\""
								+ mixin.getScheme().toString()
								+ "\"; class=\"mixin\";";
					}

				}
			}

			for (String actionName : resource.getKind().getActionNames()) {
				String actionScheme = resource.getKind().getScheme().toString();
				String actionSchemeSubStr = actionScheme.substring(0,
						actionScheme.length() - 1);
				linkBuffer.append("</");
				linkBuffer.append(resource.getKind().getTerm() + "/");
				linkBuffer.append(resource.getId());
				linkBuffer.append("/?action=");
				linkBuffer.append(actionName.substring(actionName
						.lastIndexOf("#") + 1));
				linkBuffer.append(">;rel=\"" + actionSchemeSubStr + "/");
				linkBuffer.append(resource.getKind().getTerm() + "/action#");
				linkBuffer.append(actionName.substring(actionName
						.lastIndexOf("#") + 1) + "\" ");

			}
		}
		if (link != null) {
			linkBuffer.append(link);
		}

		if (location.isEmpty()) {
			xOcciLocation.add("X-OCCI-Location", location);
		}

		xOcciLocation.add("Link", linkBuffer.toString());
		xOcciLocation.add("Category", category);
		xOcciLocation.add("X-OCCI-Attribute", attributes);
	}

	/**
	 * Specific method for header rendering for the query interface. Generates
	 * the header rendering with the given params.
	 * 
	 * @param queryKinds
	 * @param link
	 * @param attributes
	 * @param linked
	 */
	public void setHeaderRendering(LinkedList<Kind> queryKinds, Link link,
			String attributes, StringBuffer linked) {
		Form xOcciLocation = new Form();
		Response.getCurrent().getAttributes()
				.put("org.restlet.http.headers", xOcciLocation);
		String location = link.getKind().getTitle() + "/" + link.getId();

		String category = "";
		StringBuffer linkBuffer = new StringBuffer();
		if (queryKinds != null) {

			for (Mixin mixin : Mixin.getMixins()) {
				// category += mixin.getTitle() + ",";
				category += mixin.getTitle() + "; scheme=\"" + link.getScheme()
						+ "\"" + "; class=\"kind\"" + ", ";
				location += "/" + mixin.getTitle() + "/,";
			}
			for (Kind kind : queryKinds) {
				if (!kind.equals(queryKinds.getLast())) {
					category += kind.getTerm() + ",";
				} else {
					category += kind.getTerm();
				}
				if (kind.equals(queryKinds.getLast())) {
					location += "/" + kind.getTerm() + "/";
				} else if (!kind.equals(queryKinds.getLast())) {
					location += "/" + kind.getTerm() + "/,";
				} else if (kind.equals(queryKinds.getFirst())) {
					location += "/" + kind.getTerm() + "/,";
				}
			}
		}

		if ((link != null) && (attributes != null)) {
			category = "";
			// xOcciLocation = null;
			Response.getCurrent().getAttributes()
					.put("org.restlet.http.headers", xOcciLocation);

			// add information about category and location to the header
			if (link.getKind() != null) {
				// for (Category mixin : link.getKind()) {
				if (!link.getKind().getTitle().equalsIgnoreCase("action")) {
					category += link.getKind().getTitle() + "; scheme=\""
							+ link.getScheme() + "\"; class=\"kind\";";
				}

				// }
			}

			if (link.getKind() != null) {
				for (String actionName : link.getKind().getActionNames()) {
					String actionScheme = link.getKind().getScheme().toString();
					String actionSchemeSubStr = actionScheme.substring(0,
							actionScheme.length() - 1);
					linkBuffer.append("</");
					linkBuffer.append(link.getKind().getTerm() + "/");
					linkBuffer.append(link.getId());
					linkBuffer.append("/?action=");
					linkBuffer.append(actionName.substring(actionName
							.lastIndexOf("#") + 1));
					linkBuffer.append(">;rel=\"" + actionSchemeSubStr + "/");
					linkBuffer.append(link.getKind().getTerm() + "/action#");
					linkBuffer.append(actionName.substring(actionName
							.lastIndexOf("#") + 1) + "\" ");

				}
			}
		}
		if (linked != null) {
			linkBuffer.append(linked);
		}

		// xOcciLocation.add("Link", linkBuffer.toString());

		xOcciLocation.add("Category", category);
		xOcciLocation.add("X-OCCI-Location", location);
		xOcciLocation.add("X-OCCI-Attribute",
				linkBuffer.toString().replace("X-OCCI-Attribute: ", ""));
	}

	public static void setHeaderRendering(Mixin mixin) {
		Form xOcciLocation = new Form();
		Response.getCurrent().getAttributes()
				.put("org.restlet.http.headers", xOcciLocation);
		String category = mixin.getTitle();
		String clazz = "mixin";
		String scheme = mixin.getScheme().toString();
		String rel = "";
		int i = 1;
		if (mixin.getRelated() != null) {
			for (Mixin related : mixin.getRelated()) {
				if (mixin.getRelated().size() >= i) {
					rel += related.getTerm();
				} else {
					rel += related.getTerm() + ",";
				}
				i++;
			}
		}
		String entities = "";
		if (mixin.getEntities() != null) {
			for (Entity entity : mixin.getEntities()) {
				entities += entity.getKind().getTerm() + "/" + entity.getId();
			}
		}
		xOcciLocation.add("Category", category);
		xOcciLocation.add("Class", clazz);
		xOcciLocation.add("Scheme", scheme);
		if (!entities.equals("")) {
			xOcciLocation.add("X-OCCI-Location", entities);
		}
	}

	public static boolean isUUID(String string) {
		if (!string.matches("[\\w]{8}-[\\w]{4}-[\\w]{4}-[\\w]{4}-[\\w]{12}")) {
			return false;
		} else {
			return true;
		}
	}
}