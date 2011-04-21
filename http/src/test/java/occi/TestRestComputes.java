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

package occi;

import occi.config.OcciConfig;
import occi.http.occiApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to test the computes interface. The test class starts the occi api and connects to it via client resource.
 * 
 * Test cases are:
 * HTTP GET
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class TestRestComputes {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestRestQuery.class);
	private ClientResource clientResource = new ClientResource(
			OcciConfig.getInstance().config.getString("occi.server.location"));

	@Before
	public void setUp() {
		// start occi api
		occiApi occi = new occiApi();
		try {
			occi.main(null);
		} catch (Exception ex) {
			LOGGER.error("Failed to start occiApi: " + ex.getMessage());
		}
	}

	@Test
	public void testGetComputes() {
		// connect to api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.serverlocation") + "compute/");

		// execute get request
		Representation representation = null;
		try {
			representation = clientResource.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("Representation: " + representation);
	}
	
	@After
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}