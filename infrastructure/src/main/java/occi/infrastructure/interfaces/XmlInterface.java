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

package occi.infrastructure.interfaces;

import java.io.FileNotFoundException;
import java.net.NetworkInterface;

import occi.infrastructure.Compute;
import occi.infrastructure.Network;

/**
 * Interface to create several xml files. Necessary interface to connect to the
 * occi implementation.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public interface XmlInterface {
	/**
	 * Creates Xml file for a existing compute resource.
	 * 
	 * @param compute
	 */
	void createComputeXmlDescription(Compute compute);

	/**
	 * Creates Xml file for a existing network resource.
	 * 
	 * @param uuid
	 * @param network
	 */
	void createNetworkXmlDescription(String uuid, Network network);

	/**
	 * Creates Xml file for a existing network interface resource.
	 * 
	 * @param uuid
	 * @param networkInterface
	 */
	void createNetworkInterfaceXmlDescription(String uuid,
			NetworkInterface networkInterface);

	/**
	 * Returns xml file as string for a given uuid.
	 * 
	 * @param uuid
	 * @return xml string
	 * @throws FileNotFoundException
	 */
	String getXmlAsString(String uuid) throws FileNotFoundException;
}