import java.net.URISyntaxException;

import javax.naming.NamingException;

import junit.framework.Assert;
import occi.config.OcciConfig;
import occi.http.occiApi;
import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;
import occi.libvirt.manager.VmManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to test the compute interface. The test class starts the occi api and
 * connects to it via client resource.
 * 
 * Test cases are: HTTP POST HTTP GET HTTP DELETE HTTP PUT
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
@Ignore
public class OcciLibvirtTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciLibvirtTest.class);
	private final ClientResource clientResource = new ClientResource(
			OcciConfig.getInstance().config.getString("occi.server.location"));

	@Before
	public void setUp() {
		// start occi api
		occiApi occi = new occiApi();
		try {
			occi.main(null);
			VmManager vmManager = new VmManager();
		} catch (Exception ex) {
			LOGGER.error("Failed to start occiApi: " + ex.getMessage());
		}
	}

	@Test
	public void testCreateComputeAndStart() {
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
		// connect to api
		this.clientResource.setReference(OcciConfig.getInstance().config
				.getString("occi.server.location")
				+ "compute/"
				+ compute.getId() + "/?action=start");
		Form form = new Form();
		form.add("X-OCCI-Attribute", "method=start");
		LOGGER.error("web representation: " + form.toString());
		// create new representation
		Representation representation = null;
		try {
			// send post request
			representation = this.clientResource.post(form.toString(),
					new MediaType("text/occi"));
		} catch (Exception ex) {
			LOGGER.error("Failed to execute POST request " + ex.getMessage());
		}
		Assert.assertNotNull(representation);
		// get request and print it in debugger
		Request request = Request.getCurrent();
		LOGGER.debug("Request: " + request.toString() + "\n\n"
				+ form.getMatrixString());
		LOGGER.debug("--------------------------------");
		// get current response
		Response response = Response.getCurrent();
		Assert.assertNotNull(response);
		LOGGER.debug("Response: " + response.toString());
	}
}