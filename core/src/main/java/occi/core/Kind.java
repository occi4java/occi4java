package occi.core;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.directory.SchemaViolationException;

import occi.config.OcciConfig;

/**
 * A type in the OCCI model. A core component of the OCCI classification system.
 * The Kind type, together with the Mixin type, defines the classification
 * system of the OCCI Core Model. The Kind type represents the type
 * identification mechanism for all Entity types present in the model. n the
 * initial instantiation of the OCCI Core Model, with no core model extensions,
 * three instances of Kind will be present: one for Entity, another for Resource
 * and the last one for Link.
 * 
 * For more information see "Open Cloud Computing Interface - Core"
 * specification.
 * 
 * @param actions
 * @param related
 * @param entities
 * @param entity_type
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class Kind extends Category {
	/**
	 * Set of Actions defined by the Kind instance.
	 */
	private Set<Action> actions;
	/**
	 * Set of related instances.
	 */
	private Set<Kind> related;
	/**
	 * Entity type uniquely identified by the Kind instance.
	 */
	private static Entity entity_type;
	/**
	 * Set of resource instances, i.e. Entity sub-type instances. Resources
	 * instantiated from the Entity sub-type which is uniquely identified by
	 * this Kind instance.
	 */
	private Set<Entity> entities;
	private Set<String> actionNames = new HashSet<String>();
	/*
	 * set of existing kind instances, for the query interface
	 */
	private static Set<Kind> kinds = new HashSet<Kind>();

	public Kind(Set<Action> actions, Set<Kind> related, Set<Entity> entities,
			Entity entity_type, String term, String title, String scheme,
			Set<String> attributes) throws SchemaViolationException,
			URISyntaxException {
		super(term, scheme, title, attributes);

		this.actions = actions;
		this.related = related;
		this.entities = entities;
		Kind.entity_type = entity_type;

		kinds.add(this);
	}

	public Kind(Set<Entity> entities, Entity entity_type, String term,
			String title, Set<String> attributes)
			throws SchemaViolationException, URISyntaxException {
		super(term, OcciConfig.getInstance().config.getString("occi.scheme"), title, attributes);

		this.entities = entities;
		Kind.entity_type = entity_type;

		kinds.add(this);
	}

	public Kind(Set<Kind> related, Set<Entity> entities, Entity entity_type,
			String term, String title, Set<String> attributes)
			throws SchemaViolationException, URISyntaxException {
		super(term, OcciConfig.getInstance().config.getString("occi.scheme"), title, attributes);

		this.related = related;
		this.entities = entities;
		Kind.entity_type = entity_type;

		kinds.add(this);
	}

	public Kind(Entity entity_type, String term, String title,
			Set<String> attributes) throws SchemaViolationException,
			URISyntaxException {
		super(term, "http://schemas.ogf.org/occi/", title, attributes);

		Kind.entity_type = entity_type;

		kinds.add(this);
	}

	public Kind(Entity entity_type, Set<String> attributes)
			throws SchemaViolationException, URISyntaxException {
		super(entity_type.getKind().getTerm(), OcciConfig.getInstance().config.getString("occi.scheme"),
				entity_type.getTitle(), attributes);

		Kind.entity_type = entity_type;

		kinds.add(this);
	}

	/**
	 * Returns all action names as set.
	 * 
	 * @return Action names
	 */
	public Set<String> getActionNames() {
		return this.actionNames;
	}

	/**
	 * Sets a set of action names.
	 * 
	 * @param actionNames
	 */
	public void setActionNames(Set<String> actionNames) {
		this.actionNames = actionNames;
	}

	/**
	 * Returns actions.
	 * 
	 * @return actions
	 */
	public Set<Action> getActions() {
		return this.actions;
	}

	/**
	 * Returns related Kind.
	 * 
	 * @return related Kind
	 */
	public Set<Kind> getRelated() {
		return this.related;
	}

	/**
	 * Returns entities.
	 * 
	 * @return entities
	 */
	public Set<Entity> getEntities() {
		return this.entities;
	}

	/**
	 * Returns entity_Type.
	 * 
	 * @return entity_Type
	 */
	public Entity getEntity_type() {
		return entity_type;
	}

	/**
	 * Returns all active Kinds.
	 * 
	 * @return all active Kinds
	 */
	public static Set<Kind> getKinds() {
		return kinds;
	}
}