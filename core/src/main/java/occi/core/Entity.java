package occi.core;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity is an OCCI base type. The parent type of Resource and Link.
 * 
 * Entity is an abstract type which both Resource and Link inherit. Each
 * sub-type of Entity is identified by a unique Kind instance.
 * 
 * Entity enforces for all sub-types a required id attribute and an optional
 * title attribute. Every sub-type of Entity is assigned a Kind instance
 * 
 * Entity itself is assigned the Kind instance
 * http://schemas.ogf.org/occi/core#entity for type identification.
 * 
 * Being an abstract type Entity itself can never be instantiated.
 * 
 * For more information see "Open Cloud Computing Interface - Core"
 * specification
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public abstract class Entity {
	/**
	 * A unique identifier (within the service providers namespace) of the
	 * Entity sub-type instance.
	 */
	private URI id;
	/**
	 * The display name of the instance
	 */
	private String title;
	/**
	 * The Kind instance uniquely identifying the Entity sub-type of the
	 * resource instance.
	 */
	private Kind kind;
	/**
	 * The Mixin instances associated to this resource instance. Consumers can
	 * expect the attributes and Actions of the associated Mixins to be exposed
	 * by the instance.
	 */
	private Set<Kind> mixins;

	public Entity(URI id, String title, Kind kind, Set<Kind> mixins) {
		this.id = id;
		this.title = title;
		this.kind = kind;
		this.mixins = mixins;

		Set<String> s = new HashSet<String>();
		s.add("summary");
	}

	public Entity(URI id, String title, Set<Kind> mixins) {
		this.id = id;
		this.title = title;
		this.mixins = mixins;

		Set<String> s = new HashSet<String>();
		s.add("summary");
	}

	public Entity(URI id, String title, Kind kind) {
		this.id = id;
		this.title = title;
		this.kind = kind;

		Set<String> s = new HashSet<String>();
		s.add("summary");
	}

	public Entity(URI id, Kind kind) {
		this.id = id;
		this.kind = kind;

		Set<String> s = new HashSet<String>();
		s.add("summary");
	}

	public void setId(URI id) {
		this.id = id;
	}

	/**
	 * Returns the current id
	 * 
	 * @return current id
	 */
	public URI getId() {
		return id;
	}

	/**
	 * Returns the current title as a String
	 * 
	 * @return the current title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set kind
	 * 
	 * @return kind
	 */
	public void setKind(Kind kind) {
		this.kind = kind;
	}

	/**
	 * Returns kind
	 * 
	 * @return kind
	 */
	public Kind getKind() {
		return kind;
	}

	/**
	 * Returns mixins
	 * 
	 * @return mixins
	 */
	public Set<Kind> getMixins() {
		return mixins;
	}

	/**
	 * Sets mixins
	 * 
	 * @param mixins
	 */
	public void setMixins(Set<Kind> mixins) {
		this.mixins = mixins;
	}
}