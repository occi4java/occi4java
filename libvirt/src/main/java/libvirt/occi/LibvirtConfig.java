package libvirt.occi;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * OcciConfig singleton class. It saves all occi properties.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 * 
 */
public class LibvirtConfig extends XMLConfiguration {
	private static final long serialVersionUID = 1L;
	/**
	 * Instance of OcciConfig
	 */
	private static LibvirtConfig instance = null;
	/**
	 * Configuration for all occi properties
	 */
	public Configuration config;

	public static LibvirtConfig getInstance() {
		if (instance == null) {
			// create LibvirtConfig instance
			instance = new LibvirtConfig();
		}
		return instance;
	}

	public String getProperty(String key) {
		String value = null;
		Properties prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream("/conf/libvirt.properties"));
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("=============== key=" + key + ", value=" + value);
		return value;
	}

	public static void main(String[] args) {
		new LibvirtConfig();
	}
}