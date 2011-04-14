import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Compute resources. Creates some compute resources with invalid
 * parameters.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciComputeTest {
	Set<String> set = new HashSet<String>();

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void occiTestNegativeCores() throws NumberFormatException,
			IllegalArgumentException, URISyntaxException {
		try {
			new Compute(Architecture.x86, -2, "Test", 2, 2, State.active, set);
			fail("occiTestNegativeCores should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestPositiveCores() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2, State.active, set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestNegativeSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", -2, 2, State.active, set);
			fail("occiTestNegativeSpeed should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestNegativeFloatSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", -2.1F, 2, State.active,
					set);
			fail("occiTestNegativeFloatSpeed should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestPositiveSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2, State.active, set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestPositiveFloatSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2.0F, 2, State.active, set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestNegativeMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, -2, State.active, set);
			fail("occiTestNegativeMemory should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestNegativeFloatMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, -2.1F, State.active,
					set);
			fail("occiTestNegativeFloatMemory should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestPositiveMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2, State.active, set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestPositiveFloatMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2.1F, State.active, set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestUUID() {
		try {
			Compute.computeList.get(UUID.fromString("1337"));
			fail("occiTestUUID should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}