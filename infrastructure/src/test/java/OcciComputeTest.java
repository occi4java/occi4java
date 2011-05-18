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
			Compute.getComputeList().get(UUID.fromString("1337"));
			fail("occiTestUUID should return an exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}