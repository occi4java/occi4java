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

import java.util.UUID;

import occi.config.OcciConfig;
import occi.http.check.OcciCheck;
import occi.infrastructure.Storage;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Interface for all available Storage resources. Returns information about all
 * storage resources.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciRestStorages extends ServerResource {

	/**
	 * Returns all storage resources
	 * 
	 * @return StorageResources String
	 */
	@Get
	public String getOCCIRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));
		
		// access the request headers and get the X-OCCI-Attribute
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		
		StringBuffer buffer = new StringBuffer();
		/*
		 * Print all properties of the kind instance
		 */
		for (UUID uuid : Storage.getStorageList().keySet()) {
			buffer.append(getRootRef() + "/" + uuid.toString());

			Representation representation = OcciCheck.checkContentType(
					requestHeaders, buffer, getResponse());
			if (representation.getMediaType().toString().equals("text/occi")) {
				// Set Location Attribute
				setLocationRef(buffer.toString());
				// return http status code
				getResponse().setStatus(Status.SUCCESS_OK, " ");
				return " ";
			}
			getResponse().setStatus(Status.SUCCESS_OK);
			return buffer.toString();
		}
		getResponse().setStatus(Status.SUCCESS_NO_CONTENT, " ");
		return " ";
	}
}