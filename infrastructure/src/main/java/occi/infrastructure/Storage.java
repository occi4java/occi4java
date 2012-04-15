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
import occi.core.Link;
import occi.core.Resource;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Storage type represent resources that record information to a data
 * storage device. Storage inherits the Resource base type defined in the OCCI
 * Core Model. The Storage type is assigned the Kind instance
 * http://schemas.ogf.org/occi/infrastructure#storage. A Storage instance MUST
 * use and expose this Kind. [T. Metsch, A. Edmonds - Open Cloud Computing
 * Interface - Infrastructure, http://ogf.org/documents/GFD.184.pdf, Apr. 2011]
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class Storage extends Resource {
	/**
	 * Storage size in gigabytes of the instance.
	 */
	private float size;
	/**
	 * Current status of the instance.
	 */
	private State state;

	/**
	 * Possible status of the instance.
	 */
	public enum State {
		online, offline, degraded
	}

	/**
	 * Static Hashmap of all Storage Resources. The Key is a UUID, the Value a
	 * Storage Object.
	 */
	private static Map<UUID, Storage> storageList = new HashMap<UUID, Storage>();

	/**
	 * Static HashSet of all storage attributes.
	 */
	private static HashSet<String> attributes = new HashSet<String>();

	/*
	 * All possible storage actions.
	 */
	private static ListableBeanFactory beanFactory = new ClassPathXmlApplicationContext(
			"occiConfig.xml");
	private Action online = (Action) beanFactory.getBean("online");
	private Action offline = (Action) beanFactory.getBean("offline");
	private Action resize = (Action) beanFactory.getBean("resize");
	private Action backup = (Action) beanFactory.getBean("backup");
	private Action snapshot = (Action) beanFactory.getBean("snapshot");
	private Action create = (Action) beanFactory.getBean("create");

	private static final HashSet<Action> actionSet = new HashSet<Action>();
	private static final HashSet<String> actionNames = new HashSet<String>();

	public Storage(Set<Link> links, Set<String> attributes)
			throws URISyntaxException, SchemaViolationException {
		super(links, attributes);
		storageList.put(UUID.fromString(this.getId().toString()), this);
		generateActionNames();
		generateAttributeList();
	}

	public Storage(float size, State state, Set<Link> links,
			Set<String> attributes) throws URISyntaxException,
			SchemaViolationException {
		super(links, attributes);
		this.size = size;
		this.state = state;
		storageList.put(UUID.fromString(this.getId().toString()), this);
		generateActionNames();
		generateAttributeList();
	}

	/**
	 * Set size of the storage resource.
	 * 
	 * @param size
	 */
	public final void setSize(float size) {
		this.size = size;
	}

	/**
	 * Returns the size of the storage resource.
	 * 
	 * @return size
	 */
	public final float getSize() {
		return size;
	}

	/**
	 * Set state of the storage resource.
	 * 
	 * @param state
	 */
	public final void setState(State state) {
		this.state = state;
	}

	/**
	 * Return the state of the storage resource.
	 * 
	 * @return state
	 */
	public final State getState() {
		return state;
	}

	/**
	 * Returns list of all storage resources.
	 * 
	 * @return network map
	 */
	public static final Map<UUID, Storage> getStorageList() {
		return storageList;
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
	 * Generate attribute List.
	 */
	public static final void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.storage.size");
			attributes.add("occi.storage.state");
		}
	}

	/**
	 * Generate Action Names.
	 */
	public static final HashSet<String> generateActionNames() {
		if (actionNames.isEmpty()) {
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				if (beanFactory
						.getBean(beanFactory.getBeanDefinitionNames()[i])
						.toString().contains("storage")) {
					actionNames.add(OcciConfig.getInstance().config
							.getString("occi.scheme")
							+ "/infrastructure/storage/action#"
							+ beanFactory.getBeanDefinitionNames()[i]);
				}
			}
		}
		return actionNames;
	}

	/**
	 * Generate Action Set.
	 */
	public static final HashSet<Action> generateActionSet() {
		if (actionSet.isEmpty()) {
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				if (beanFactory
						.getBean(beanFactory.getBeanDefinitionNames()[i])
						.toString().contains("storage")) {
					actionSet.add((Action) beanFactory.getBean(beanFactory
							.getBeanDefinitionNames()[i]));
				}
			}
		}
		return actionSet;
	}

	/**
	 * Return the storage attributes.
	 * 
	 * @return attributes
	 */
	public static final HashSet<String> getAttributes() {
		return attributes;
	}

	/**
	 * @param online
	 *            the online to set
	 */
	public void setOnline(Action online) {
		this.online = online;
	}

	/**
	 * @return the online
	 */
	public Action getOnline() {
		return online;
	}

	/**
	 * @param create
	 *            the create to set
	 */
	public void setCreate(Action create) {
		this.create = create;
	}

	/**
	 * @return the create
	 */
	public Action getCreate() {
		return create;
	}

	/**
	 * @param snapshot
	 *            the snapshot to set
	 */
	public void setSnapshot(Action snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * @return the snapshot
	 */
	public Action getSnapshot() {
		return snapshot;
	}

	/**
	 * @param offline
	 *            the offline to set
	 */
	public void setOffline(Action offline) {
		this.offline = offline;
	}

	/**
	 * @return the offline
	 */
	public Action getOffline() {
		return offline;
	}

	/**
	 * @param resize
	 *            the resize to set
	 */
	public void setResize(Action resize) {
		this.resize = resize;
	}

	/**
	 * @return the resize
	 */
	public Action getResize() {
		return resize;
	}

	/**
	 * @param backup
	 *            the backup to set
	 */
	public void setBackup(Action backup) {
		this.backup = backup;
	}

	/**
	 * @return the backup
	 */
	public Action getBackup() {
		return backup;
	}
}