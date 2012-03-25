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

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.directory.SchemaViolationException;

import occi.core.Mixin;

/**
 * In order to support L3/L4 capabilities (e.g. IP, TCP etc.) with the
 * NetworkInterface type, an OCCI Mixin instance is herewith defined.
 * 
 * The IPNetworkInterface mixin is assigned the "scheme" of
 * http://schemas.ogf.org/occi/infrastructure/networkinterface# and the \term"
 * value ipnetworkinterface. An IPNetworkInterface mixin MUST support these
 * attributes. [T. Metsch, A. Edmonds - Open Cloud Computing Interface -
 * Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class IPNetworkInterface extends Mixin {
	/**
	 * Internet Protocol(IP) network address (e.g. 192.168.0.1/24, fc00::/7) of
	 * the link [T. Metsch, A. Edmonds - Open Cloud Computing Interface -
	 * Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	private String ip;
	/**
	 * Internet Protocol(IP) network address (e.g. 192.168.0.1/24, fc00::/7) [T.
	 * Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure,
	 * http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	private String gateway;
	/**
	 * Address mechanism: dynamic e.g. uses the dynamic host configuration
	 * protocol, static e.g. uses user supplied static net- work configurations.
	 * [T. Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure,
	 * http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
	 */
	private Allocation allocation;

	/**
	 * Possible allocations.
	 */
	public enum Allocation {
		DYNAMIC, STATIC
	}

	/**
	 * Static HashSet of all network interface attributes.
	 */
	public static HashSet<String> attributes = new HashSet<String>();

	public IPNetworkInterface(String ip, String gateway, Allocation allocation)
			throws SchemaViolationException, URISyntaxException {
		super(null, "ipnetwork", "ipnetwork",
				"http://schemas.ogf.org/occi/infrastructure/network#", null);
		this.ip = ip;
		this.gateway = gateway;
		this.allocation = allocation;

		generateAttributeList();
	}

	/**
	 * Constructor for the query interface. Necessary.
	 * 
	 * @param term
	 * @param title
	 * @param scheme
	 * @throws SchemaViolationException
	 * @throws URISyntaxException
	 */
	public IPNetworkInterface(Set<Mixin> related, String term, String title,
			String scheme, Set<String> attributes)
			throws SchemaViolationException, URISyntaxException {
		super(related, term, title, scheme, attributes);

		generateAttributeList();
	}

	/**
	 * Generate attribute List.
	 */
	public static void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.network.address");
			attributes.add("occi.network.gateway");
			attributes.add("occi.network.allocation");
		}
	}

	/**
	 * Sets the network interface ip.
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Returns the network interface ip.
	 * 
	 * @param ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the network interface gateway.
	 * 
	 * @param ip
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * Returns the network interface gateway.
	 * 
	 * @param gateway
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * Sets the network interface allocation.
	 * 
	 * @param allocation
	 */
	public void setAllocation(Allocation allocation) {
		this.allocation = allocation;
	}

	/**
	 * Returns the network interface allocation.
	 * 
	 * @param allocation
	 */
	public Allocation getAllocation() {
		return allocation;
	}
}