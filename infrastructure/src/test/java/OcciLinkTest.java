import java.net.URISyntaxException;

import javax.naming.NamingException;
import javax.naming.directory.SchemaViolationException;

import occi.core.Link;
import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Network;
import occi.infrastructure.Network.State;
import occi.infrastructure.Storage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for links. Creates a link between compute and network or compute
 * and storage.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciLinkTest {
	private Storage storage;
	private Network network;
	private Compute compute;
	private Link link;

	@Before
	public void setUp() {
		network = null;
		link = null;
		compute = null;
		storage = null;
	}

	@Test
	public void testNetworkLink() {
		try {
			network = new Network(State.active, "network", 1, null, null);
		} catch (SchemaViolationException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// test if network is not null
		Assert.assertNotNull(network);
		try {
			compute = new Compute(Architecture.x64, 2, "ComputeTest", 20, 20,
					occi.infrastructure.Compute.State.active, null);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(compute);
		try {
			link = new Link(network, compute);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(link);
		Assert.assertNotNull(link.getTitle());
	}

	@Test
	public void testStorageLink() {
		try {
			storage = new Storage(2F, occi.infrastructure.Storage.State.offline, null, null);
		} catch (SchemaViolationException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// test if network is not null
		Assert.assertNotNull(storage);
		try {
			compute = new Compute(Architecture.x64, 2, "ComputeTest", 20, 20,
					occi.infrastructure.Compute.State.active, null);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(compute);
		try {
			link = new Link(storage, compute);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(link);
		Assert.assertNotNull(link.getTitle());
	}
	
	@After
	public void tearDown() {
		network = null;
		compute = null;
		storage = null;
		link = null;
		System.gc();
	}
}