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

package occi.http.helper;

import java.util.Random;

/**
 * Class to generate random mac adresses.
 * 
 * @author Sebastian Laag
 */
public class MacAdressGenerator {

	private final static String[] pool = { "a", "b", "c", "d", "e", "f", "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };

	public static String generateMacAdress() {
		// initialize integer generator
		Random generator = new Random();
		String mac = "";
		for (int i = 0; i < 12; i++) {
			if (i % 2 != 0) {
				mac += pool[generator.nextInt(16)];
			} else {
				mac += "-";
			}
		}
		return mac;
	}
}