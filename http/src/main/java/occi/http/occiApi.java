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

import java.io.File;

import occi.config.OcciConfig;

import org.apache.log4j.PropertyConfigurator;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

public class occiApi extends ServerResource {
	// initialize server component
	public static Component comp = new Component();

	public static void main(String[] args) throws Exception {
		// load all logger properties
		if (new File("conf/log4j.properties").exists())
			PropertyConfigurator.configure("conf/log4j.properties");
		else
			PropertyConfigurator
					.configure("../core/src/main/resources/conf/log4jTest.properties");
		// Create the HTTP server and listen on port 8182
		comp.getServers().add(Protocol.HTTP,
				OcciConfig.getInstance().config.getInt("occi.server.port"));

		comp.getDefaultHost().attach("/", OcciRestRoot.class);
		// Router for the Query/Discovery Interface.
		comp.getDefaultHost().attach("/-/", OcciRestQuery.class);

		// Returns one single instance of a compute resource
		comp.getDefaultHost().attach("/compute", OcciRestCompute.class);
		comp.getDefaultHost().attach("/compute/{uuid}", OcciRestCompute.class);

		// Returns all compute instances
		comp.getDefaultHost().attach("/compute/", OcciRestComputes.class);

		// Router for storage instances
		comp.getDefaultHost().attach("/storage", OcciRestStorage.class);

		// Returns all storage instances
		comp.getDefaultHost().attach("/storage/", OcciRestStorages.class);

		// Router for storage instances
		comp.getDefaultHost().attach("/network", OcciRestNetwork.class);
		comp.getDefaultHost().attach("/network/{uuid}", OcciRestNetwork.class);
		comp.getDefaultHost().attach("/networkinterface",
				OcciRestNetworkInterface.class);
		comp.getDefaultHost().attach("/networkinterface/{uuid}",
				OcciRestNetworkInterface.class);
		// Returns all storage instances
		comp.getDefaultHost().attach("/network/", OcciRestNetworks.class);
		// Router for all available mixin instances. Returns mixin information.
		comp.getDefaultHost().attach("/{mixin}", OcciRestMixin.class);
		// start occi api
		comp.start();
	}
}