package occi.infrastructure.links;

import java.net.URISyntaxException;
import java.util.HashSet;

import occi.core.Link;
import occi.core.Resource;

/**
 * The StorageLink type represents a link from a Resource to a target Storage
 * instance. This enables a Storage instance be attached to a Compute instance,
 * with all the prerequisite low- level operations handled by the OCCI
 * implementation. Storage inherits the Link base type defined in the OCCI Core
 * Model.
 * 
 * The StorageLink type is assigned the Kind instance
 * http://schemas.ogf.org/occi/infrastructure#storagelink. A StorageLink
 * instance MUST use and expose this Kind. The Kind instance assigned to the
 * StorageLink type MUST be related to the http://schemas.ogf.org/occi/core#link
 * Kind.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class StorageLink extends Link {
	/**
	 * Device identifier as defined by the OCCI service provider.
	 */
	private String deviceid;
	/**
	 * Point to where the storage is mounted in the guest OS.
	 */
	private String mountpoint;
	/**
	 * Current status of the instance.
	 */
	private State state;
	/**
	 * Possible status of the instance.
	 */
	private enum State {
		active, inactive
	}
	/**
	 * Static HashSet of all storage link attributes.
	 */
	public static HashSet<String> attributes = new HashSet<String>();

	public StorageLink(String deviceid, State state, Resource link,
			Resource target) throws URISyntaxException {
		super(link, target);
		this.deviceid = deviceid;
		this.state = state;
	}

	public StorageLink(String deviceid, State state, String mountpoint,
			Resource link, Resource target) throws URISyntaxException {
		super(link, target);
		this.deviceid = deviceid;
		this.state = state;
		this.mountpoint = mountpoint;
	}
	
	/**
	 * Generate attribute List.
	 */
	public static void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.storagelink.deviceid");
			attributes.add("occi.storagelink.state");
			attributes.add("occi.storagelink.mountpoint");
		}
	}

	/**
	 * Sets devices id.
	 * 
	 * @param deviceid
	 */
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	/**
	 * Returns device id of the storage link.
	 * 
	 * @return devices id
	 */
	public String getDeviceid() {
		return deviceid;
	}

	/**
	 * Sets mountpoint of the storage link.
	 * 
	 * @param mountpoint
	 */
	public void setMountpoint(String mountpoint) {
		this.mountpoint = mountpoint;
	}

	/**
	 * Returns mountpoint of the storage link.
	 * 
	 * @return mountpoint
	 */
	public String getMountpoint() {
		return mountpoint;
	}

	/**
	 * Returns state of the storage link.
	 * 
	 * @return state
	 */
	public State getState() {
		return state;
	}
}