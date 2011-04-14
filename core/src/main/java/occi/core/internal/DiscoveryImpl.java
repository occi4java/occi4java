package occi.core.internal;

import java.util.HashSet;
import java.util.Set;

import occi.core.Action;
import occi.core.Category;
import occi.core.Discovery;
import occi.core.Entity;
import occi.core.Kind;
import occi.core.Mixin;
import occi.core.Resource;

/**
 * This class provides a OCCI implementation for the Discovery interface.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
public class DiscoveryImpl implements Discovery {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Entity> getEntitySubTypes(Kind kind) {
		return kind.getEntities();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getAttributesForEntitySubType(Kind kind) {
		return kind.getAttributes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Action> getInvocableOperations(Kind kind) {
		return kind.getActions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Kind> getMixinInstances(Set<Resource> resources) {
		HashSet<Kind> mixinInstances = new HashSet<Kind>();
		// get all mixin instances from the set of resources
		for (Resource resource : resources) {
			for (Kind kind : resource.getMixins()) {
				// put them in the hashset
				mixinInstances.add(kind);
			}
		}
		return mixinInstances;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set getAdditionalCapabilities(Mixin mixin) {
		Set set = new HashSet();
		// put all actions to the set
		for (Action action : mixin.getActions()) {
			set.add(action);
		}
		// and all attributes
		for (String string : mixin.getAttributes()) {
			set.add(string);
		}
		// return set with actions and strings
		return set;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Kind> discoverKinds() {
		return Kind.getKinds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Mixin> discoverMixins() {
		return Mixin.getMixins();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Category> discoverCategories() {
		return Category.getCategories();
	}
}