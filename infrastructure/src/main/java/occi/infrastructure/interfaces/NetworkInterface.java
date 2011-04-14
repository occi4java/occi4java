package occi.infrastructure.interfaces;

import occi.infrastructure.Network;

/**
 * Interface to execute operations on network resources. Necessary interface to
 * connect to the occi implementation.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public interface NetworkInterface {
	/**
	 * Starts a stopped network.
	 * 
	 * @param network
	 * @return Network
	 */
	public Network upNetwork(Network network);
	
	/**
	 * Stops a existing network.
	 * 
	 * @param network
	 * @return Network
	 */
	public Network downNetwork(Network network);
}