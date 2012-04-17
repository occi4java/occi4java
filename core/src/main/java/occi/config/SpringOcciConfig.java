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

package occi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * OcciConfig singleton class. It saves all occi properties.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
@Configuration
@ImportResource("config.xml")
@PropertySource("classpath:OCCI.properties")
public class SpringOcciConfig {
	private static final long serialVersionUID = 1L;
	// private static final Logger LOGGER = LoggerFactory
	// .getLogger(OcciConfig.class);

	@Value("${occi.server.location}")
	private String serverLocation;

	/**
	 * Instance of OcciConfig
	 */
	// private static OcciConfig instance = null;
	// private static ConfigurationFactory factory;
	/**
	 * Configuration for all occi properties
	 */
	// public Configuration config;

	public SpringOcciConfig() {
		// factory = new ConfigurationFactory();
		// // load configuration file
		// URL configURL = getClass().getResource("/conf/config.xml");
		// factory.setConfigurationURL(configURL);
		// try {
		// // pick up configuration
		// config = factory.getConfiguration();
		// } catch (ConfigurationException e) {
		// LOGGER.error("Failed to load config file.");
		// }
	}

	// public static OcciConfig getInstance() {
	// if (instance == null) {
	// // create OcciConfig instance
	// instance = new OcciConfig();
	// }
	// return instance;
	// }

	public String getServerLocation() {
		return serverLocation;
	}

	public static void main(String[] args) {
		// OcciConfig config = new OcciConfig();
		// System.out.println(config.getServerLocation());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] { new ClassPathResource(
				"OCCI.properties") };
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}
}