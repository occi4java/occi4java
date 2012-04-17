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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;

import occi.config.OcciConfig;
import occi.core.Action;
import occi.core.Kind;
import occi.core.Resource;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Compute type represents a generic Information resource. For example a
 * virtual machine. This Compute type Inherits the Resource base type defined in
 * OCCI Core Specification. Compute is assigned to the Kind instance. [T.
 * Metsch, A. Edmonds - Open Cloud Computing Interface - Infrastructure,
 * http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class Compute extends Resource {
	/**
	 * Enumeration for CPU Architecture of the instance
	 */
	public enum Architecture {
		x86, x64
	}

	/**
	 * Number of CPU Cores assigned to the Instance
	 */
	private int cores;

	/**
	 * String DNS hostname for the Instance
	 */
	private String hostname;

	/**
	 * Float CPU Clock frequency in gigahertz
	 */
	private float speed;

	/**
	 * Float RAM in gigabytes allocated to the instance
	 */
	private float memory;

	/**
	 * State of the compute resource
	 */
	private State state;

	/**
	 * Architecture of the compute resource
	 */
	private Architecture architecture;

	/**
	 * Current State of the instance
	 */
	public enum State {
		active, inactive, suspended
	}

	/**
	 * Static Hashmap of all Compute Resources. The Key is a UUID, the Value a
	 * Compute Object.
	 */
	private static Map<UUID, Compute> computeList = new HashMap<UUID, Compute>();

	/**
	 * Static HashSet of all compute attributes.
	 */
	private static HashSet<String> attributes = new HashSet<String>();

	/**
	 * Random UUID of the compute resource.
	 */
	private final UUID uuid;

	/*
	 * All possible compute actions.
	 */
	private static ListableBeanFactory beanFactory = new ClassPathXmlApplicationContext(
			"occiConfig.xml");
	private Action start = (Action) beanFactory.getBean("start");
	private Action stop = (Action) beanFactory.getBean("stop");
	private Action suspend = (Action) beanFactory.getBean("suspend");
	private Action restart = (Action) beanFactory.getBean("restart");

	private static final HashSet<Action> actionSet = new HashSet<Action>();
	private static final HashSet<String> actionNames = new HashSet<String>();

	public Compute(Architecture architecture, int cores, String hostname,
			float speed, float memory, State state, Set<String> attributes)
			throws URISyntaxException, NumberFormatException,
			IllegalArgumentException, NamingException {
		super("Compute", links, attributes);
		this.architecture = architecture;
		this.cores = cores;
		this.hostname = hostname;
		this.speed = speed;
		this.memory = memory;
		this.state = state;

		generateActionNames();

		// check if all attributes are correct
		if ((cores < 1)) {
			throw new NumberFormatException("Number of cores is negative!");
		} else if (speed < 0) {
			throw new NumberFormatException("Number of speed is negative!");
		} else if (memory < 0) {
			throw new NumberFormatException("Number of memory is negative!");
		}
		// check if there is a hostname
		if (hostname.length() == 0) {
			throw new NamingException(
					"Name of the Compute resource can not be null");
		}
		/*
		 * set Category
		 */
		setKind(new Kind(actionSet, null, null, null, "compute", "Compute",
				OcciConfig.getInstance().config.getString("occi.scheme")
						+ "/infrastructure#", attributes));
		getKind().setActionNames(actionNames);
		// set uuid for the resource
		uuid = UUID.randomUUID();
		setId(new URI(uuid.toString()));
		// put resource into compute list
		computeList.put(uuid, this);

		// Generate attribute list
		generateAttributeList();
	}

	/**
	 * Return the full computeList
	 * 
	 * @return all compute resources
	 */
	public static final Map<UUID, Compute> getComputeList() {
		return computeList;
	}

	/**
	 * Set compute list
	 * 
	 * @param computeList
	 */
	public static final void setComputeList(Map<UUID, Compute> computeList) {
		Compute.computeList = computeList;
	}

	/**
	 * Returns the current UUID as a UUID Value
	 * 
	 * @return current UUID assigned to the Instance
	 */
	public final UUID getUuid() {
		return uuid;
	}

	/**
	 * Returns the current Cores as a int Value
	 * 
	 * @return current Cores assigned to the Instance
	 */
	public final int getCores() {
		return cores;
	}

	/**
	 * Sets the cores of the current Instance
	 * 
	 * @param cores
	 *            of the current Instance as an int
	 */
	public final void setCores(int cores) {
		this.cores = cores;
	}

	/**
	 * Returns the Hostname represented as a String of the current Instance
	 * 
	 * @return Hostname as a String
	 */
	public final String getHostname() {
		return hostname;
	}

	/**
	 * Sets the hostname of the current Instance
	 * 
	 * @param hostname
	 *            of the current Instance as a String
	 */
	public final void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * Returns the speed of the current Instance as a float
	 * 
	 * @return speed of the current Instance
	 */
	public final float getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the current Instance
	 * 
	 * @param speed
	 *            of the current Instance as a float
	 */
	public final void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Returns the memory of the current Instace as a float
	 * 
	 * @return
	 */
	public final float getMemory() {
		return memory;
	}

	/**
	 * Sets the memory of the current Instance
	 * 
	 * @param memory
	 *            of the current Instance as a float
	 */
	public final void setMemory(float memory) {
		this.memory = memory;
	}

	/**
	 * Returns the State of the current Instance
	 * 
	 * @return State of the current Instance
	 */
	public final State getState() {
		return state;
	}

	/**
	 * Sets the State of the current Instance
	 * 
	 * @param State
	 *            state of the current Instance
	 */
	public final void setState(State state) {
		this.state = state;
	}

	/**
	 * Returns the Architecture of the current Instance
	 * 
	 * @return architecture of the current Instance of enum-type Architectures
	 */
	public final Architecture getArchitecture() {
		return architecture;
	}

	/**
	 * Sets the Architecture of the current Instance
	 * 
	 * @param architecture
	 *            of the current Instance
	 */
	public final void setArchitecture(Architecture architecture) {
		this.architecture = architecture;
	}

	/**
	 * Return list of all action names.
	 * 
	 * @return action names
	 */
	public static final HashSet<String> getActionNames() {
		return actionNames;
	}

	/**
	 * Generate list with action names.
	 */
	public static final HashSet<String> generateActionNames() {
		if (actionNames.isEmpty()) {
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				if (beanFactory
						.getBean(beanFactory.getBeanDefinitionNames()[i])
						.toString().contains("compute")) {
					actionNames.add(OcciConfig.getInstance().config
							.getString("occi.scheme")
							+ "/infrastructure/compute/action#"
							+ beanFactory.getBeanDefinitionNames()[i]);
				}
			}
		}
		return actionNames;
	}

	/**
	 * Generate list with actions.
	 */
	public static final HashSet<Action> generateActionSet() {
		if (actionSet.isEmpty()) {
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				if (beanFactory
						.getBean(beanFactory.getBeanDefinitionNames()[i])
						.toString().contains("compute")) {
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
	public static final void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.compute.architecture");
			attributes.add("occi.compute.cores");
			attributes.add("occi.compute.hostname");
			attributes.add("occi.compute.memory");
			attributes.add("occi.compute.speed");
			attributes.add("occi.compute.state");
		}
	}

	/**
	 * Return the compute attributes.
	 * 
	 * @return attributes
	 */
	public static final HashSet<String> getAttributes() {
		return attributes;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public final void setStart(Action start) {
		this.start = start;
	}

	/**
	 * @return the start
	 */
	public final Action getStart() {
		return start;
	}

	/**
	 * @param stop
	 *            the stop to set
	 */
	public final void setStop(Action stop) {
		this.stop = stop;
	}

	/**
	 * @return the stop
	 */
	public final Action getStop() {
		return stop;
	}

	/**
	 * @param restart
	 *            the restart to set
	 */
	public final void setRestart(Action restart) {
		this.restart = restart;
	}

	/**
	 * @return the restart
	 */
	public final Action getRestart() {
		return restart;
	}

	/**
	 * @param suspend
	 *            the suspend to set
	 */
	public final void setSuspend(Action suspend) {
		this.suspend = suspend;
	}

	/**
	 * @return the suspend
	 */
	public final Action getSuspend() {
		return suspend;
	}
}