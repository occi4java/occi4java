import java.net.URISyntaxException;

import javax.naming.directory.SchemaViolationException;

import occi.core.Action;
import occi.core.Category;
import occi.core.Discovery;
import occi.core.Entity;
import occi.core.Kind;
import occi.core.Link;
import occi.core.Method;
import occi.core.Mixin;
import occi.core.Resource;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertNotNull(Discovery.class);
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