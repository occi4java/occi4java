package occi.libvirt.manager;

import occi.infrastructure.Network;
import occi.infrastructure.interfaces.NetworkInterface;

/**
 * Manager class for all operations on network resources. Necessary interface to
 * connect to the occi implementation.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class NetworkManager implements NetworkInterface {
	/**
	 * {@inheritDoc}
	 */
	public Network upNetwork(Network network) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Network downNetwork(Network network) {
		return null;
	}
}