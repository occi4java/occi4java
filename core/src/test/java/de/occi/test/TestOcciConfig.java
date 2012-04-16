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

import org.testng.Assert;
import org.testng.annotations.Test;

import occi.config.OcciConfig;


/**
 * Test class for the Occi Config. Tests if some necessary config properties are
 * not null.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class TestOcciConfig {
	@Test
	public void testOcciConfig() {
		Assert.assertNotNull(OcciConfig.getInstance());
		Assert.assertNotNull(OcciConfig.getInstance().config
				.getString("occi.scheme"));
		Assert.assertNotNull(OcciConfig.getInstance().config
				.getString("occi.version"));
	}
}