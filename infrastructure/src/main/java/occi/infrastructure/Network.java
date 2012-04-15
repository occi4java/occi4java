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

package occi.infrastructure;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.naming.directory.SchemaViolationException;

import occi.config.OcciConfig;
import occi.core.Action;
import occi.core.Kind;
import occi.core.Link;
import occi.core.Resource;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Network type represents a L2 networking entity (e.g. a virtual switch).
 * It can be extended using the mixin mechanism (or sub-typed) to support L3/L4
 * capabilities such as TCP/IP etc. For the purposes of this specification we
 * define an OCCI mixin so that IP networking can be supported where required.
 * Network inherits the Resource base type defined in OCCI Core Model.
 * 
 * The Network type is assigned the
 * http://schemas.ogf.org/occi/infrastructure#network Kind. A Network instance
 * MUST use and expose this Kind. [T. Metsch, A. Edmonds - Open Cloud Computing
 * Interface - Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class Network extends Resource {
	/**
	 * 802.1q VLAN Ientifier (e.g. 343).
	 */
	private int vlan;
	/**
	 * Tag based VLANs (e.g. external-dmz).
	 */
	private String label;
	/**
	 * Current state of the instance.
	 */
	private State state;

	/**
	 * Possible status of the instance
	 */
	public enum State {
		active, inactive
	}

	/**
	 * Static HashSet of all network attributes.
	 */
	private static HashSet<String> attributes = new HashSet<String>();

	/**
	 * Static Hashmap of all Network Resources. The Key is a UUID, the Value a
	 * Network Object.
	 */
	private static Map<UUID, Network> networkList = new HashMap<UUID, Network>();

	/*
	 * All possible network actions.
	 */
	private static ListableBeanFactory beanFactory = new ClassPathXmlApplicationContext(
			"occiConfig.xml");
	private static Action up = (Action) beanFactory.getBean("up");
	private static Action down = (Action) beanFactory.getBean("down");

	private final static HashSet<Action> actionSet = new HashSet<Action>();
	private final static HashSet<String> actionNames = new HashSet<String>();

	/**
	 * Minimal constructor.
	 * 
	 * @param links
	 * @param attributes
	 * @throws URISyntaxException
	 * @throws SchemaViolationException
	 */
	public Network(State state, Set<Link> links, Set<String> attributes)
			throws URISyntaxException, SchemaViolationException {
		super(links, attributes);
		this.state = state;

		setKind(new Kind(actionSet, null, null, null, "network", "network",
				OcciConfig.getInstance().config.getString("occi.scheme")
						+ "/infrastructure#", attributes));

		networkList.put(UUID.fromString(getId().toString()), this);
		generateActionNames();
		generateActionNames();
		generateAttributeList();
	}

	public Network(State state, String label, Set<Link> links,
			Set<String> attributes) throws URISyntaxException,
			SchemaViolationException {
		super(links, attributes);
		this.state = state;
		this.label = label;

		networkList.put(UUID.fromString(getId().toString()), this);
		generateActionNames();
		generateAttributeList();
	}

	public Network(State state, int vlan, Set<Link> links,
			Set<String> attributes) throws URISyntaxException,
			SchemaViolationException {
		super(links, attributes);
		this.state = state;
		this.vlan = vlan;

		networkList.put(UUID.fromString(getId().toString()), this);
		generateActionNames();
		generateAttributeList();
	}

	public Network(State state, String label, int vlan, Set<Link> links,
			Set<String> attributes) throws URISyntaxException,
			SchemaViolationException {
		super(links, attributes);
		this.state = state;

		networkList.put(UUID.fromString(getId().toString()), this);
		generateActionNames();
		generateAttributeList();
	}

	public Network(HashMap<String, String> attributes)
			throws SchemaViolationException, URISyntaxException {
		super(attributes);

		networkList.put(UUID.fromString(getId().toString()), this);
		generateActionNames();
		generateAttributeList();
	}

	/**
	 * Sets vlan integer.
	 * 
	 * @param vlan
	 */
	public final void setVlan(int vlan) {
		this.vlan = vlan;
	}

	/**
	 * Returns vlan of the network interface.
	 * 
	 * @return vlan
	 */
	public final int getVlan() {
		return vlan;
	}

	/**
	 * Returns state of the network interface.
	 * 
	 * @return state
	 */
	public final State getState() {
		return this.state;
	}

	/**
	 * Sets the state of the instance.
	 * 
	 * @param state
	 */
	public final void setState(State state) {
		this.state = state;
	}

	/**
	 * Sets the label of the instance.
	 * 
	 * @param label
	 */
	public final void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the label of the instance.
	 * 
	 * @return label
	 */
	public final String getLabel() {
		return label;
	}

	/**
	 * Returns list of all network resources.
	 * 
	 * @return network map
	 */
	public final static Map<UUID, Network> getNetworkList() {
		return networkList;
	}

	/**
	 * Return list of all action names.
	 * 
	 * @return action names
	 */
	public final static HashSet<String> getActionNames() {
		return actionNames;
	}

	/**
	 * Generate list with action names.
	 */
	public final static HashSet<String> generateActionNames() {
		if (actionNames.isEmpty()) {
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				if (beanFactory
						.getBean(beanFactory.getBeanDefinitionNames()[i])
						.toString().contains("network")) {
					actionNames.add(OcciConfig.getInstance().config
							.getString("occi.scheme")
							+ "/infrastructure/network/action#"
							+ beanFactory.getBeanDefinitionNames()[i]);
				}
			}
		}
		return actionNames;
	}

	/**
	 * Generate list with actions.
	 * 
	 * @return hash set of actions
	 */
	public final static HashSet<Action> generateActionSet() {
		if (actionSet.isEmpty()) {
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				if (beanFactory
						.getBean(beanFactory.getBeanDefinitionNames()[i])
						.toString().contains("network")) {
					actionSet.add((Action) beanFactory.getBean(beanFactory
							.getBeanDefinitionNames()[i]));
				}
			}
		}
		return actionSet;
	}

	/**
	 * Generate attribute List.
	 */
	public final static void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.network.vlan");
			attributes.add("occi.network.label");
			attributes.add("occi.network.state");
		}
	}

	/**
	 * Return the network attributes.
	 * 
	 * @return attributes
	 */
	public static HashSet<String> getAttributes() {
		return attributes;
	}
}