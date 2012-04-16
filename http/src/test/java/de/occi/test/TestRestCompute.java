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
import java.net.URISyntaxException;

import javax.naming.NamingException;

import occi.config.OcciConfig;
import occi.http.occiApi;
import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Class to test the compute interface. The test class starts the occi api and
 * connects to it via client resource.
 * 
 * Test cases are: HTTP POST HTTP GET HTTP DELETE HTTP PUT
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class TestRestCompute {
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
	public void testGetCompute() {
		Compute compute = null;
		try {
			compute = new Compute(Architecture.x64, 2, "TestCase", 200, 20,
					State.active, null);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		// test if compute ist not null
		Assert.assertNotNull(compute);
		// connect to api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location")
				+ "compute/"
				+ compute.getId());
		clientResource.setHostRef(OcciConfig.getInstance().config
				.getString("occi.server.location")
				+ "compute/"
				+ compute.getId());
		// create new representation
		Representation representation = null;
		try {
			// send post request
			representation = clientResource.get();
		} catch (Exception ex) {
			System.out.println("Failed to execute GET request: "
					+ ex.getMessage());
		}
		Assert.assertNotNull(representation);
		// get request and print it in debugger
		Request request = Request.getCurrent();
		System.out.println(request.toString() + "\n\n");
		System.out.println("--------------------------------");
		// get current response
		Response response = Response.getCurrent();
		Assert.assertNotNull(response);
		System.out.println("Response: " + response.toString());

		try {
			representation.write(System.out);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n--------------------------------");
	}

	// TODO Die Parameter beim Request werden nicht korrekt übergeben. Keine
	// Ahnung woran es liegt.
	@Test(enabled = false)
	public void testPostCompute() {
		// connect to api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location") + "compute");
		// Tests if client resource is connected to api
		Assert.assertNotNull(clientResource);
		// try to create a compute resource
		String architecture = "x86";
		String cores = "20";
		String hostname = "Ubuntu";
		String speed = "2000000";
		String memory = "1024";
		String category = "compute";
		// create new request and add all attributes
		Form form = new Form();
		form.add("occi.compute.architecture", architecture);
		form.add("occi.compute.cores", cores);
		form.add("occi.compute.hostname", hostname);
		form.add("occi.compute.speed", speed);
		form.add("occi.compute.memory", memory);
		form.add("category", category);
		System.out.println("\n FORM " + form.toString());
		// create new representation
		Representation representation = null;
		try {
			// send post request
			representation = clientResource.post(form.toString(),
					MediaType.TEXT_PLAIN);
		} catch (Exception ex) {
			System.out.println("Failed to execute POST request "
					+ ex.getMessage());
		}
		Assert.assertNotNull(representation);
		// get request and print it in debugger
		Request request = Request.getCurrent();
		System.out
				.println(request.toString() + "\n\n" + form.getMatrixString());
		System.out.println("--------------------------------");
		// get current response
		Response response = Response.getCurrent();
		Assert.assertNotNull(response);
		System.out.println("Response: " + response.toString());

		try {
			representation.write(System.out);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n--------------------------------");
	}

	@AfterTest
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}