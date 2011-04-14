package occi;

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
 * Test class to test the http storages interface. Submits a HTTP GET request. Other
 * requests are not possible.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class TestRestStorages {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestRestStorages.class);
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
	public void testGetStorages() {
		// connect to Api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location") + "storage/");
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

	@After
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}