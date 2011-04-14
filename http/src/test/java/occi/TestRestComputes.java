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