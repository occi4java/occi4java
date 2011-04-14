import occi.config.OcciConfig;

import org.junit.Assert;
import org.junit.Test;

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