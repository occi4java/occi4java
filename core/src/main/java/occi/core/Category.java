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
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import javax.naming.directory.SchemaViolationException;

import occi.core.check.OcciCoreCheck;

/**
 * The Category Type is the basis of the type identification mechanism of the
 * OCCI specification. Instances of the Category Type are only used to identify
 * Action types. All other properties of Category are managed through its sub
 * types Mixin and Kind. [T. Metsch, A. Edmonds, R. Nyren and A.Papaspyrou -
 * Open Cloud Computing Interface - Core, http://ogf.org/documents/GFD.183.pdf,
 * Apr. 2011]
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class Category {
	/**
	 * Unique identifier of the Category instance within the categorisation
	 * scheme.
	 */
	private final String term;
	/**
	 * The categorisation scheme.
	 */
	private final URI scheme;
	/**
	 * The display name of an instance.
	 */
	private String title;
	/**
	 * The set of resource attribute names defined by the Category instance.
	 */
	private Set<String> attributes;
	/*
	 * set of existing category instances, for the query interface
	 */
	private static Set<Category> categories = new HashSet<Category>();

	/**
	 * A Category instance is uniquely identified by concatenation of
	 * categorisation scheme and category term. For example
	 * http://example.com/category/scheme#term [T. Metsch, A. Edmonds, R. Nyren
	 * and A.Papaspyrou - Open Cloud Computing Interface - Core,
	 * http://ogf.org/documents/GFD.183.pdf, Apr. 2011]
	 * 
	 * @param term
	 * @param scheme
	 * @param title
	 * @param attributes
	 */
	public Category(String term, String scheme) throws URISyntaxException,
			SchemaViolationException {
		this.term = term;
		// check if scheme is correct
		OcciCoreCheck.checkScheme(scheme);
		// set scheme
		this.scheme = new URI(scheme);
		// add new category to the category list, for discovery purpose
		categories.add(this);
	}

	public Category(String term, String scheme, String title)
			throws URISyntaxException, SchemaViolationException {
		if (scheme != null) {
			OcciCoreCheck.checkScheme(scheme);
			this.scheme = new URI(scheme);
		} else {
			this.scheme = null;
		}
		this.term = term;

		this.title = title;
		// add new category to the category list, for discovery purpose
		categories.add(this);
	}

	public Category(String term, String scheme, Set<String> attributes)
			throws URISyntaxException, SchemaViolationException {
		this.term = term;
		// check if scheme is correct
		OcciCoreCheck.checkScheme(scheme);
		this.scheme = new URI(scheme);
		if (attributes != null) {
			this.attributes = new HashSet<String>();
			this.attributes.addAll(attributes);
		}
		// add new category to the category list, for discovery purpose
		categories.add(this);
	}

	public Category(String term, String scheme, String title,
			Set<String> attributes) throws URISyntaxException,
			SchemaViolationException {
		OcciCoreCheck.checkScheme(scheme);
		this.term = term;
		this.scheme = new URI(scheme);
		this.title = title;
		if (attributes != null) {
			this.attributes = new HashSet<String>();
			this.attributes.addAll(attributes);
		}
		// add new category to the category list, for discovery purpose
		categories.add(this);
	}

	/**
	 * Returns the current term as a String
	 * 
	 * @return the current term
	 */
	public String getTerm() {
		return this.term;
	}

	/**
	 * Returns the current URI scheme
	 * 
	 * @return the current scheme
	 */
	public URI getScheme() {
		return this.scheme;
	}

	/**
	 * Returns the current title as a String
	 * 
	 * @return the current title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets a String Set of all attributes
	 * 
	 * @return String map of attributes
	 */
	public Set<String> getAttributes() {
		return this.attributes;
	}

	/**
	 * Return all active Categories
	 * 
	 * @return all active Categories
	 */
	public static Set<Category> getCategories() {
		return categories;
	}
}