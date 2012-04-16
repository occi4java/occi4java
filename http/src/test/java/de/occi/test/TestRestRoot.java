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

package de.occi.test;

import java.io.IOException;

import occi.config.OcciConfig;
import occi.http.occiApi;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test class to test the http root interface. Submits a HTTP GET request. Other
 * requests are not possible.
 * 
 * @author Sebastian Laag
 */
public class TestRestRoot {
	private ClientResource clientResource = new ClientResource(
			OcciConfig.getInstance().config.getString("occi.server.location"));

	@BeforeTest
	public void setUp() {
		try {
			// start occi api
			occiApi.main(null);
		} catch (Exception ex) {
			System.out.println("Failed to start occiApi: " + ex.getMessage());
		}
	}

	/**
	 * Tests to submit a get request at the root resource.
	 * @throws IOException 
	 */
	@Test
	public void testGetRoot() throws IOException {
		// connect to Api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location"));
		Representation representation = null;
		try {
			// execute get request
			representation = clientResource.get();
			// checks whether representation is null
			Assert.assertNotNull(representation);
			System.out.println("representation.toString " + representation.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests to submit a post request at the root resource.
	 */
	@Test
	public void testPostRoot() {
		// connect to Api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location"));

		try {
			// execute post request
			Representation representation = clientResource.post("X-OCCI-Attribute: occi.compute.Category=compute occi.compute.architecture=x86 occi.compute.cores=2 occi.compute.hostname=test occi.compute.memory=2.0 occi.compute.speed=2.4 occi.compute.state=inactive");
			Assert.assertNotNull(representation);
		} catch (Exception e) {
			System.out.println("Failed to submit a post request at root resource: "
					+ e.getMessage());
		}
	}

	@AfterTest
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}