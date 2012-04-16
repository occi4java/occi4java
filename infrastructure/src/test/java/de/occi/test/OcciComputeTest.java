package de.occi.test;
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

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;

/**
 * Test class for Compute resources. Creates some compute resources with invalid
 * parameters.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciComputeTest {
	Set<String> set = new HashSet<String>();

	@BeforeTest
	public void setUp() {
	}

	@AfterTest
	public void tearDown() {
	}

	@Test
	public void occiTestNegativeCores() {
		try {
			new Compute(Architecture.x86, -2, "Test", 2, 2, State.active, set);
			Assert.fail("occiTestNegativeCores should return an exception");
		} catch (NumberFormatException e) {
			System.out.println("Expected NumberFormatException");
		} catch (NamingException e) {
			System.out.println("Expected NamingException");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void occiTestPositiveCores() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2, State.active, set);
		} catch (NumberFormatException e) {
			System.out.println("Expected NumberFormatException");
		} catch (IllegalArgumentException e) {
			System.out.println("Expected IllegalArgumentException");
		} catch (URISyntaxException e) {
			System.out.println("Expected URISyntaxException");
		} catch (NamingException e) {
			System.out.println("Expected NamingException");
		}
	}

	@Test
	public void occiTestNegativeSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", -2, 2, State.active, set);
			Assert.fail("occiTestNegativeSpeed should return an exception");
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestNegativeFloatSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", -2.1F, 2, State.active,
					set);
			Assert.fail("occiTestNegativeFloatSpeed should return an exception");
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestPositiveSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2, State.active, set);
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestPositiveFloatSpeed() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2.0F, 2, State.active, set);
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestNegativeMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, -2, State.active, set);
			Assert.fail("occiTestNegativeMemory should return an exception");
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestNegativeFloatMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, -2.1F, State.active,
					set);
			Assert.fail("occiTestNegativeFloatMemory should return an exception");
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestPositiveMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2, State.active, set);
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestPositiveFloatMemory() {
		try {
			new Compute(Architecture.x86, 2, "Test", 2, 2.1F, State.active, set);
		} catch (Exception e) {
			System.out.println("Expected Exception");
		}
	}

	@Test
	public void occiTestUUID() {
		try {
			Compute.getComputeList().get(UUID.fromString("1337"));
			Assert.fail("occiTestUUID should return an exception");
		} catch (IllegalArgumentException e) {
			System.out.println("Expected Exception");
		}
	}
}