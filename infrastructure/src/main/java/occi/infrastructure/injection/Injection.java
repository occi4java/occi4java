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