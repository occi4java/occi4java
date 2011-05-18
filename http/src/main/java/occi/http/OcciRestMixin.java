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

import java.util.HashSet;
import java.util.UUID;

import occi.config.OcciConfig;
import occi.core.Entity;
import occi.core.Mixin;
import occi.http.check.OcciCheck;
import occi.infrastructure.Compute;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface for Mixin requests. Only HTTP GET is allowed.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class OcciRestMixin extends ServerResource {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestMixin.class);

	@Get
	public String getRestMixinRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));

		StringBuffer buffer = new StringBuffer();
		/*
		 * Print all properties of the mixin instance
		 */
		LOGGER.debug("Size of mixins: " + Mixin.getMixins().size());
		Mixin mix = null;
		for (Mixin mixin : Mixin.getMixins()) {
			if (mixin != null
					&& !mixin.getTerm().equals("ipnetwork")
					&& mixin.getTitle().equalsIgnoreCase(
							getReference().getLastSegment())) {
				mix = mixin;
				buffer.append("Category: " + mixin.getTitle());
				buffer.append("\r\n");
				buffer.append("\t\t class=\"mixin\"");
				buffer.append("\r\n");
				buffer.append("\t\t scheme=" + mixin.getScheme() + ";");
				buffer.append("\r\n");
				buffer.append("\t\t location=/" + mixin.getTerm() + ";");
				buffer.append("\r\n");
				if (mixin.getRelated() != null) {
					for (Mixin related : mixin.getRelated()) {
						buffer.append("\t\t rel=/" + related.getTerm() + ";");
						buffer.append("\r\n");
					}
				}
				if (mixin.getEntities() != null) {
					for (Entity entity : mixin.getEntities()) {
						buffer.append("\t\t X-OCCI-Location: "
								+ entity.getKind().getTerm() + "/"
								+ entity.getId());
					}
				}
			}
		}
		// if there is no mixin definition, return not found
		if (!buffer.toString().contains("Category")) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND,
					buffer.toString());
			return "Mixin definition not found";
		}
		buffer.append("\r\n");

		// access the request headers and get the Accept attribute
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		// Representation for returning content-type
		Representation representation = OcciCheck.checkContentType(
				requestHeaders, buffer, getResponse());
		if (representation.getMediaType().toString().equals("text/occi")) {
			// Generate the header rendering if media-type equals text/occi
			OcciCheck.setHeaderRendering(mix);
			getResponse().setEntity(representation);
			return " ";
		}
		return buffer.toString();
	}

	@Put
	public String putOCCIRequest(Representation representation)
			throws Exception {
		LOGGER.info("Incoming PUT request.");
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		// indicates, whether mixin was found in mixin list
		boolean found = false;
		try {
			// access the request headers and get the X-OCCI-Attribute
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Raw Request Headers: " + requestHeaders);

			String[] xocciLocation = requestHeaders.getValuesArray(OcciCheck
					.checkCaseSensitivity(requestHeaders.toString()).get(
							"x-occi-location"));
			for (Mixin mixin : Mixin.getMixins()) {
				if (mixin.getTitle().equalsIgnoreCase(
						getReference().getLastSegment())
						&& mixin.getTitle().equals(
								getReference().getLastSegment())) {
					for (UUID uuid : Compute.getComputeList().keySet()) {
						for (String element : xocciLocation) {
							if (element.contains(uuid.toString())
									&& mixin.getEntities() == null) {
								mixin.setEntities(new HashSet<Entity>());
							}
							mixin.getEntities().add(
									Compute.getComputeList().get(uuid));
							Compute.getComputeList().get(uuid).getKind()
									.getCategories().add(mixin);
							found = true;
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.debug("Exception caught: " + e.getMessage());
		}
		if (!found) {
			// if there is no mixin definition, return not found
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return " ";
		} else {
			getResponse().setStatus(Status.SUCCESS_OK);
			return " ";
		}
	}

	@Delete
	public String deleteOCCIRequest(Representation representation)
			throws Exception {
		LOGGER.info("Incoming DELETE request.");
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		// indicates, wether mixin was found in mixin list
		boolean found = false;
		try {
			// access the request headers and get the X-OCCI-Attribute
			Form requestHeaders = (Form) getRequest().getAttributes().get(
					"org.restlet.http.headers");
			LOGGER.debug("Raw Request Headers: " + requestHeaders);
			String categoryCase = OcciCheck.checkCaseSensitivity(
					requestHeaders.toString()).get("category");

			String[] xocciLocation = requestHeaders
					.getValuesArray(categoryCase);
			LOGGER.debug(getReference().getLastSegment().toString());
			for (Mixin mixin : Mixin.getMixins()) {
				if (mixin.getTitle().equalsIgnoreCase(
						getReference().getLastSegment())
						&& mixin.getTitle().equals(
								getReference().getLastSegment())) {
					found = true;
					for (UUID uuid : Compute.getComputeList().keySet()) {
						for (String element : xocciLocation) {
							LOGGER.debug(element);
							if (element.contains(uuid.toString())
									&& mixin.getEntities() == null) {
								mixin.setEntities(new HashSet<Entity>());
							}
							mixin.getEntities().remove(
									Compute.getComputeList().get(uuid));
							Compute.getComputeList().remove(uuid).getKind()
									.getCategories().add(mixin);
						}
					}

				}
			}
		} catch (Exception e) {
			LOGGER.debug("Exception caught: " + e.getMessage());
		}
		if (!found) {
			// if there is no mixin definition, return not found
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return " ";
		} else {
			getResponse().setStatus(Status.SUCCESS_OK);
			return " ";
		}
	}
}