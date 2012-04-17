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

import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OcciConfig singleton class. It saves all occi properties.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class OcciConfig extends XMLConfiguration {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciConfig.class);
	/**
	 * Instance of OcciConfig
	 */
	private static OcciConfig instance = null;
	private static ConfigurationFactory factory;
	/**
	 * Configuration for all occi properties
	 */
	public Configuration config;

	private OcciConfig() {
		factory = new ConfigurationFactory();
		// load configuration file
		URL configURL = getClass().getResource("/conf/config.xml");
		factory.setConfigurationURL(configURL);
		try {
			// pick up configuration
			config = factory.getConfiguration();
		} catch (ConfigurationException e) {
			LOGGER.error("Failed to load config file.");
		}
	}

	public static OcciConfig getInstance() {
		if (instance == null) {
			// create OcciConfig instance
			instance = new OcciConfig();
		}
		return instance;
	}

	public static void main(String[] args) {
		new OcciConfig();
	}
}