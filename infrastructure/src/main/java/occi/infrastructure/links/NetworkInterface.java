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

package occi.infrastructure.links;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import javax.naming.directory.SchemaViolationException;

import occi.core.Link;
import occi.core.Resource;

/**
 * The NetworkInterface type represents an L2 client device (e.g. network
 * adapter). It can be extended using the mix-in mechanism or sub-typed to
 * support L3/L4 capabilities such as TCP/IP etc. NetworkInterface inherits the
 * Link base type defined in the OCCI Core Model.
 * 
 * The NetworkInterface type is assigned the Kind instance
 * http://schemas.ogf.org/occi/infrastructure#networkinterface. A
 * NetworkInterface instance MUST use and expose this Kind. The Kind instance
 * assigned to the Network- Interface type MUST be related to the
 * http://schemas.ogf.org/occi/core#link Kind. [T. Metsch, A. Edmonds - Open
 * Cloud Computing Interface - Infrastructure,
 * http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class NetworkInterface extends Link {
	/**
	 * Identifier that relates the link to the link's device interface. [T. Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	private final String networkInterface;
	/**
	 * MAC address associated with the link's device interface. [T. Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	private String mac;
	/**
	 * Current status of the instance. [T. Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	private final State state;

	/**
	 * Possible status of the instance. [T. Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	public enum State {
		active, inactive
	}

	/**
	 * Random UUID of the compute resource.
	 */
	private final UUID uuid;

	public static Map<UUID, NetworkInterface> networkInterfaceList = new HashMap<UUID, NetworkInterface>();

	/**
	 * Static HashSet of all network interface attributes.
	 */
	private static HashSet<String> attributes = new HashSet<String>();

	public NetworkInterface(String networkInterface, String mac, State state,
			Resource link, Resource target) throws URISyntaxException,
			SchemaViolationException {
		super(link, target);
		this.networkInterface = networkInterface;
		this.mac = mac;
		this.state = state;
		uuid = UUID.randomUUID();
		setId(new URI(uuid.toString()));
		// put resource into networkinterface list
		networkInterfaceList.put(uuid, this);

		// setKind(new Kind(this, target.getKind().getTerm(), target.getKind()
		// .getTitle(), attributes));

		// Generate attribute list
		generateAttributeList();
	}

	/**
	 * Generate attribute List.
	 */
	public static void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.networkinterface.interface");
			attributes.add("occi.networkinterface.mac");
			attributes.add("occi.networkinterface.state");
		}
	}

	/**
	 * Return the network interface attributes.
	 * 
	 * @return attributes
	 */
	public static HashSet<String> getAttributes() {
		return attributes;
	}

	/**
	 * Returns the network interface.
	 * 
	 * @return string
	 */
	public String getNetworkInterface() {
		return networkInterface;
	}

	/**
	 * Sets the mac adress of the network interface.
	 * 
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * Returns the mac adress of the network interface.
	 * 
	 * @return string
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Returns the state of the network interface.
	 * 
	 * @return state
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Returns the list with all network interface instances.
	 * 
	 * @return list with network interfaces
	 */
	public static Map<UUID, NetworkInterface> getNetworkInterfaceList() {
		return networkInterfaceList;
	}
	
	/**
	 * Sets the network interface list.
	 * 
	 * @param networkInterfaceList
	 */
	public void setNetworkInterfaceList(Map<UUID, NetworkInterface> networkInterfaceList) {
		NetworkInterface.networkInterfaceList = networkInterfaceList;
	}
}