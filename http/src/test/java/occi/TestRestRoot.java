package occi;

import java.io.IOException;

import occi.config.OcciConfig;
import occi.http.occiApi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class to test the http root interface. Submits a HTTP GET request. Other
 * requests are not possible.
 * 
 * @author Sebastian Laag
 */
public class TestRestRoot {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestRestRoot.class);
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
			LOGGER.info("representation.toString " + representation.toString());
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
			LOGGER.info("Failed to submit a post request at root resource: "
					+ e.getMessage());
		}
	}

	@After
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}