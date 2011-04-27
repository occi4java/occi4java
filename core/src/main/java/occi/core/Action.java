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

package occi.core;

import java.net.URI;

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
 * or Mixin instance it is associated with. [T. Metsch, A. Edmonds, R. Nyren and
 * A.Papaspyrou - Open Cloud Computing Interface - Core,
 * http://ogf.org/documents/GFD.183.pdf, Apr. 2011]
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