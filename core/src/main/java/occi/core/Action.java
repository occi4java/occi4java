package occi.core;

import java.net.URI;
import java.util.HashSet;

/**
 * Action is an OCCI base type. Represent an invocable operation on a Entity
 * sub-type instance or collection thereof.
 * 
 * The Action type is an abstract type. Each sub-type of Action defines an
 * invocable operation applicable to an Entity sub-type instance or a collection
 * thereof. In general, Actions modify state by, for example, performing complex
 * operation such as rebooting a virtual machine.
 * 
 * An Action MUST always bound to either a Kind or a Mixin instance through a
 * composite association. An Action is considered to be a capability of the Kind
 * or Mixin instance it is associated with.
 * 
 * For more information see "Open Cloud Computing Interface - Core"
 * specification
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public abstract class Action {
	/**
	 * The identifying Category of the Action.
	 */
	private static Category category;

	public abstract void execute(URI uri, Method method);

	/**
	 * Returns the category of the action.
	 * 
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
}