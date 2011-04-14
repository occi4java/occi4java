package occi.core.check;

import javax.naming.directory.SchemaViolationException;

import occi.config.OcciConfig;

public class OcciCoreCheck {
	/**
	 * This method checks, if the scheme is correct
	 * 
	 * @return true or false
	 */
	public static void checkScheme(String scheme) throws SchemaViolationException {
		if (scheme.contains(OcciConfig.getInstance().config
				.getString("occi.scheme")) || scheme.contains(OcciConfig.getInstance().config
						.getString("occi.scheme.alternative"))) {
			return;
		} else {
			throw new SchemaViolationException("Scheme " + scheme
					+ " not supported");
		}
	}
}