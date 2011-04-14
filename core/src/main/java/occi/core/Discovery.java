package occi.core;

import java.util.Set;

/**
 * An OCCI client MUST be able to discover all instances of Kind, Mixin and
 * Category a particular service provider's OCCI implementation has defined. By
 * examining these instances a client MUST be able to, at a minimum, deduce the
 * following information:
 * 
 * The Entity sub-types available from the service provider, including core
 * model extensions. This information is provided through the Kind instances of
 * the OCCI implementation.
 * 
 * The attributes defined for each Entity sub-type. The identifying Kind
 * instance provide this information.
 * 
 * The invocable operations, i.e. Actions, defined for each Entity sub-type. The
 * identifying Kind instance provide this information.
 * 
 * Any Mixin instances that can be associated to resource instances.
 * 
 * Additional capabilities defined by a particular Mixin instance, i.e.
 * attributes and Actions.
 * 
 * The above requirements comprise the OCCI discovery mechanism. It MUST be
 * implemented. The details of exactly how the Category, Kind and Mixin
 * instances are exposed to an OCCI client is specific to the particular
 * rendering used. The relevant details can be found in the OCCI Rendering
 * documents.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public interface Discovery {

	/**
	 * An OCCI client MUST be able to discover all instances of Kind a
	 * particular service provider's OCCI implementation has defined.
	 */
	public Set<Kind> discoverKinds();

	/**
	 * An OCCI client MUST be able to discover all instances of Mixin a
	 * particular service provider's OCCI implementation has defined.
	 */
	public Set<Mixin> discoverMixins();

	/**
	 * An OCCI client MUST be able to discover all instances of Category a
	 * particular service provider's OCCI implementation has defined.
	 */
	public Set<Category> discoverCategories();

	/**
	 * The Entity sub-types available from the service provider, including core
	 * model extensions. This information is provided through the Kind instances
	 * of the OCCI implementation.
	 * 
	 * @param kind
	 * @return set of Entity sub-types
	 */
	public Set getEntitySubTypes(Kind kind);

	/**
	 * The attributes defined for each Entity sub-type. The identifying Kind
	 * instance provide this information.
	 * 
	 * @return Attributes defined for each Entity sub-type
	 */
	public Set getAttributesForEntitySubType(Kind kind);

	/**
	 * The invocable operations, i.e. Actions, defined for each Entity sub-type.
	 * The identifying Kind instance provide this information.
	 * 
	 * @param kind
	 * @return invocable operations
	 */
	public Set<Action> getInvocableOperations(Kind kind);

	/**
	 * Any Mixin instances that can be associated to resource instances.
	 * 
	 * @param resource
	 * @return Mixin instances
	 */
	public Set<Kind> getMixinInstances(Set<Resource> resources);

	/**
	 * Additional capabilities defined by a particular Mixin instance, i.e.
	 * attributes and Actions.
	 * 
	 * @param mixin instance
	 * @return Additional capabilities, i.e. attributes and actions
	 */
	public Set getAdditionalCapabilities(Mixin mixin);
}