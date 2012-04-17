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

package occi.core.check;

import javax.naming.directory.SchemaViolationException;

import occi.config.OcciConfig;

public class OcciCoreCheck {
	/**
	 * This method checks, if the scheme is correct
	 * 
	 * @return true or false
	 */
	public static void checkScheme(String scheme)
			throws SchemaViolationException {
		if (scheme.contains(OcciConfig.getInstance().config
				.getString("occi.scheme"))
				|| scheme.contains(OcciConfig.getInstance().config
						.getString("occi.scheme.alternative"))) {
			return;
		} else {
			throw new SchemaViolationException("Scheme " + scheme
					+ " not supported");
		}
	}
}