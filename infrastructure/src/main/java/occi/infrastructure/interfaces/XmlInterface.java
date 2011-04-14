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
	public void createComputeXmlDescription(Compute compute);

	/**
	 * Creates Xml file for a existing network resource.
	 * 
	 * @param uuid
	 * @param network
	 */
	public void createNetworkXmlDescription(String uuid, Network network);

	/**
	 * Creates Xml file for a existing network interface resource.
	 * 
	 * @param uuid
	 * @param networkInterface
	 */
	public void createNetworkInterfaceXmlDescription(String uuid,
			NetworkInterface networkInterface);

	/**
	 * Returns xml file as string for a given uuid.
	 * 
	 * @param uuid
	 * @return xml string
	 * @throws FileNotFoundException
	 */
	public String getXmlAsString(String uuid) throws FileNotFoundException;
}