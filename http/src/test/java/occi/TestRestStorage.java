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

public class TestRestStorage {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestRestStorage.class);
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
	public void testGetStorage() {
		// connect to Api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location") + "storage");
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