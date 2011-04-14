package occi;

import occi.config.OcciConfig;
import occi.http.occiApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for the query interface. It tests to submit a get request with
 * accept header text/plain and another one with text/occi. HTTP PUT tests to
 * create a mixin instance. HTTP DELETE tests to delete a mixin instance.
 * 
 * @author Sebastian Laag
 */
public class TestRestQuery {
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
	public void testGetQueryTextOcci() {
		// connect to api
		clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.serverlocation") + "-/");

		try {
			// execute get request
			Representation representation = clientResource.get(new MediaType(
					"text/occi"));
			
			LOGGER.info("Representation: " + representation.getText());
		} catch (Exception e) {
			LOGGER.error("Failed to submit a get request with accept header text/occi: " + e.getMessage());
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
			
			LOGGER.debug("Representation: " + representation.getText());
		} catch (Exception e) {
			LOGGER.error("Failed to submit a get request with accept header text/plain: " + e.getMessage());
		}
	}

	@Ignore
	public void testPutQuery() {

	}

	@Ignore
	public void testDeleteQuery() {

	}

	@After
	public void tearDown() {
		clientResource = null;
		System.gc();
	}
}