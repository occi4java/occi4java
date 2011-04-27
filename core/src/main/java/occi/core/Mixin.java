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

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.directory.SchemaViolationException;

/**
 * A type in the OCCI model. A core component of the OCCI classification system.
 * The Mixin type complements the Kind type in defining the OCCI Core Model type
 * classification system. The Mixin type represent an extension mechanism, which
 * allow new resource capabilities to be added to resource instances both at
 * creation-time and/or run-time. A Mixin instance can be associated with any
 * existing resource instance and thereby add new resource capabilities, i.e.
 * attributes and Actions, to the resource instance. However, a Mixin can never
 * be applied to a type. In the initial instantiation of the OCCI Core Model,
 * with no extensions, no Mixin instances are present. [T. Metsch, A. Edmonds,
 * R. Nyren and A.Papaspyrou - Open Cloud Computing Interface - Core,
 * http://ogf.org/documents/GFD.183.pdf, Apr. 2011]
 * 
 * For more information see "Open Cloud Computing Interface - Core"
 * specification.
 * 
 * @author Sebastian Heckmann
 * @author Ediz Bas
 * @author Sebastian Laag
 */
public class Mixin extends Category {
	/**
	 * Set of Actions defined by the Mixin instance.
	 */
	private Set<Action> actions;
	/**
	 * Set of related Mixin instances.
	 */
	private Set<Mixin> related;
	/**
	 * Set of resource instances, i.e. Entity sub-type instances, associated
	 * with the Mixin instance. [T. Metsch, A. Edmonds, R. Nyren and
	 * A.Papaspyrou - Open Cloud Computing Interface - Core,
	 * http://ogf.org/documents/GFD.183.pdf, Apr. 2011]
	 */
	private Set<Entity> entities;
	/**
	 * set of existing mixin instances, for the query interface
	 */
	private static Set<Mixin> mixins = new HashSet<Mixin>();

	/**
	 * Mixin Constructor
	 * 
	 * @param actions
	 * @param related
	 * @param entities
	 * @throws URISyntaxException
	 */
	public Mixin(Set<Action> actions, Set<Mixin> related, Set<Entity> entities)
			throws URISyntaxException, SchemaViolationException {
		super(null, "http://schemas.ogf.org/occi/core#", null, null);
		this.actions = actions;
		this.related = related;
		this.entities = entities;

		mixins.add(this);
	}

	public Mixin(Set<Mixin> related, Set<Entity> entities)
			throws URISyntaxException, SchemaViolationException {
		super(null, "http://schemas.ogf.org/occi/core#", null, null);
		this.related = related;
		this.entities = entities;

		mixins.add(this);
	}

	public Mixin(Set<Entity> entities) throws URISyntaxException,
			SchemaViolationException {
		super(null, "http://schemas.ogf.org/occi/core#", null, null);
		this.entities = entities;

		mixins.add(this);
	}

	public Mixin(Set<Mixin> related, String term, String title, String scheme,
			Set<String> attributes) throws URISyntaxException,
			SchemaViolationException {
		super(term, scheme, title, attributes);
		this.related = related;

		mixins.add(this);
	}

	/**
	 * Returns actions
	 * 
	 * @return actions
	 */
	public Set<Action> getActions() {
		return actions;
	}

	/**
	 * Returns related mixin
	 * 
	 * @return related mixin
	 */
	public Set<Mixin> getRelated() {
		return related;
	}

	/**
	 * Returns entities
	 * 
	 * @return entities
	 */
	public Set<Entity> getEntities() {
		return entities;
	}

	/**
	 * Sets entities
	 * 
	 * @param entities
	 */
	public void setEntities(Set<Entity> entities) {
		this.entities = entities;
	}

	/**
	 * Returns all active Mixins
	 * 
	 * @return all active mixins
	 */
	public static Set<Mixin> getMixins() {
		return mixins;
	}
}