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

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * The Storage type represent resources that record information to a data
 * storage device. Storage inherits the Resource base type defined in the OCCI
 * Core Model. The Storage type is assigned the Kind instance
 * http://schemas.ogf.org/occi/infrastructure#storage. A Storage instance MUST
 * use and expose this Kind.
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
	public static Map<UUID, Storage> storageList = new HashMap<UUID, Storage>();

	/**
	 * Static HashSet of all storage attributes.
	 */
	public static HashSet<String> attributes = new HashSet<String>();

	/*
	 * All possible storage actions.
	 */
	private static XmlBeanFactory beanFactory = new XmlBeanFactory(
			new ClassPathResource("occiConfig.xml"));
	public Action online = (Action) beanFactory.getBean("online");
	public Action offline = (Action) beanFactory.getBean("offline");
	public Action resize = (Action) beanFactory.getBean("resize");
	public Action backup = (Action) beanFactory.getBean("backup");
	public Action snapshot = (Action) beanFactory.getBean("snapshot");
	public Action create = (Action) beanFactory.getBean("create");

	private final static HashSet<Action> actionSet = new HashSet<Action>();
	private final static HashSet<String> actionNames = new HashSet<String>();

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
	public void setSize(float size) {
		this.size = size;
	}

	/**
	 * Returns the size of the storage resource.
	 * 
	 * @return size
	 */
	public float getSize() {
		return size;
	}

	/**
	 * Set state of the storage resource.
	 * 
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Return the state of the storage resource.
	 * 
	 * @return state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Returns list of all storage resources.
	 * 
	 * @return network map
	 */
	public static Map<UUID, Storage> getStorageList() {
		return storageList;
	}

	/**
	 * Return list of all action names.
	 * 
	 * @return action names
	 */
	public static HashSet<String> getActionNames() {
		return actionNames;
	}

	/**
	 * Generate attribute List.
	 */
	public static void generateAttributeList() {
		if (attributes.isEmpty()) {
			// add all attributes to attribute list
			attributes.add("occi.storage.size");
			attributes.add("occi.storage.state");
		}
	}

	/**
	 * Generate Action Names.
	 */
	public static HashSet<String> generateActionNames() {
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
	public static HashSet<Action> generateActionSet() {
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
}