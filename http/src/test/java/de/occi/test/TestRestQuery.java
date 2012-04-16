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

import occi.config.OcciConfig;
import occi.http.occiApi;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test class for the query interface. It tests to submit a get request with
 * accept header text/plain and another one with text/occi. HTTP PUT tests to
 * create a mixin instance. HTTP DELETE tests to delete a mixin instance.
 * 
 * @author Sebastian Laag
 */
public class TestRestQuery {
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

	@Test
	public void testGetQueryTextOcci() {
		// connect to api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.serverlocation") + "-/");

		try {
			// execute get request
			Representation representation = clientResource.get(new MediaType(
					"text/occi"));
			
			System.out.println("Representation: " + representation.getText());
		} catch (Exception e) {
			System.out.println("Failed to submit a get request with accept header text/occi: " + e.getMessage());
		}
	}

	@Test
	public void testGetQueryTextPlain() {
		// connect to api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.serverlocation") + "-/");

		try {
			// execute get request
			Representation representation = clientResource
					.get(MediaType.TEXT_PLAIN);
			
			System.out.println("Representation: " + representation.getText());
		} catch (Exception e) {
			System.out.println("Failed to submit a get request with accept header text/plain: " + e.getMessage());
		}
	}

	@Test(enabled=false)
	public void testPutQuery() {

	}

	@Test(enabled=false)
	public void testDeleteQuery() {

	}

	@AfterTest
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}