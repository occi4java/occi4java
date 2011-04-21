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

package occi.infrastructure.injection;

import occi.infrastructure.interfaces.ComputeInterface;
import occi.infrastructure.interfaces.NetworkInterface;
import occi.infrastructure.interfaces.StorageInterface;
import occi.infrastructure.interfaces.XmlInterface;

/**
 * Injection class to inject all available interfaces. Injection can e.g. be
 * done with spring dependency injection.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class Injection {
	/**
	 * Injected Compute Interface.
	 */
	public static ComputeInterface computeInterface;
	/**
	 * Injected Storage Interface.
	 */
	public static StorageInterface storageInterface;
	/**
	 * Injected Network Interface.
	 */
	public static NetworkInterface networkInterface;
	/**
	 * Injected Xml Interface.
	 */
	public static XmlInterface xmlInterface;

	/**
	 * @return the computeInterface
	 */
	public static ComputeInterface getComputeInterface() {
		return computeInterface;
	}

	/**
	 * @param computeInterface
	 *            the computeInterface to set
	 */
	public void setComputeInterface(ComputeInterface ci) {
		Injection.computeInterface = ci;
	}

	/**
	 * @return the networkInterface
	 */
	public static NetworkInterface getNetworkInterface() {
		return networkInterface;
	}

	/**
	 * @param networkInterface
	 *            the networkInterface to set
	 */
	public void setNetworkInterface(NetworkInterface networkInterface) {
		Injection.networkInterface = networkInterface;
	}

	/**
	 * @return the storageInterface
	 */
	public static StorageInterface getStorageInterface() {
		return storageInterface;
	}

	/**
	 * @param storageInterface
	 *            the storageInterface to set
	 */
	public void setStorageInterface(StorageInterface storageInterface) {
		Injection.storageInterface = storageInterface;
	}

	/**
	 * @return the xmlInterface
	 */
	public static XmlInterface getXmlInterface() {
		return xmlInterface;
	}

	/**
	 * @param xmlInterface
	 *            the xmlInterface to set
	 */
	public void setXmlInterface(XmlInterface xmlInterface) {
		Injection.xmlInterface = xmlInterface;
	}
}