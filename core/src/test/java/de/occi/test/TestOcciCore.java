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

import javax.naming.directory.SchemaViolationException;

import org.testng.Assert;
import org.testng.annotations.Test;

import occi.core.Action;
import occi.core.Category;
import occi.core.Entity;
import occi.core.Kind;
import occi.core.Link;
import occi.core.Method;
import occi.core.Mixin;
import occi.core.Resource;

/**
 * Test class for all core classes.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class TestOcciCore {
	@Test
	public void testOcciCoreClasses() {
		Assert.assertNotNull(Action.class);
		Assert.assertNotNull(Category.class);
		Assert.assertNotNull(Entity.class);
		Assert.assertNotNull(Kind.class);
		Assert.assertNotNull(Link.class);
		Assert.assertNotNull(Method.class);
		Assert.assertNotNull(Mixin.class);
		Assert.assertNotNull(Resource.class);
	}

	@Test
	public void testOcciCategory() {
		Category category = null;
		try {
			category = new Category("Test", "http://schemas.ogf.org/occi", "Test");
		} catch (SchemaViolationException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(category);
		Assert.assertNotNull(category.getScheme());
		Assert.assertNotNull(category.getTerm());
		Assert.assertNotNull(category.getTitle());
	}
}