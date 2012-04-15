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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.naming.directory.SchemaViolationException;

/**
 * An OCCI base type. The parent type for all domain-specific resource types.
 * The Resource type inherits Entity and describes a concrete resource that can
 * be inspected and manipulated. It represents a general object in the OCCI
 * model. A Resource is suitable to represent real world resources, e.g. virtual
 * machines, networks, services, etc. through specialisation. Resource enforces
 * the inheritance of a set of common attributes into sub-types. Moreover, it
 * introduces relationships to other Resource instances through instances of the
 * Link type. The Resource type is the first of three entry points to extend the
 * OCCI Core Model. [T. Metsch, A. Edmonds, R. Nyren and A.Papaspyrou - Open
 * Cloud Computing Interface - Core, http://ogf.org/documents/GFD.183.pdf, Apr.
 * 2011]
 * 
 * For more information see "Open Cloud Computing Interface - Core"
 * specification.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class Resource extends Entity {
	/**
	 * A summarizing description of the Resource instance.
	 */
	private static String summary;
	/**
	 * A set of Link compositions. Being a composite relation the removal of a
	 * Link from the set MUST also remove the Link instance. [T. Metsch, A.
	 * Edmonds, R. Nyren and A.Papaspyrou - Open Cloud Computing Interface -
	 * Core, http://ogf.org/documents/GFD.183.pdf, Apr. 2011]
	 */
	protected static Set<Link> links;

	/**
	 * Resource constructor
	 * 
	 * @param Description
	 *            of the resource (Compute)
	 * @param set
	 *            of links
	 * @param set
	 *            of strings
	 * 
	 * @throws URISyntaxException
	 */
	public Resource(String summary, Set<Link> links, Set<String> attributes)
			throws URISyntaxException, SchemaViolationException {
		super(null, null);
		setId(new URI(UUID.randomUUID().toString()));
		setKind(getKind());
		setSummary(summary);
		if (Resource.links != null) {
			setLinks(Resource.links);
		} else {
			setLinks(new HashSet<Link>());
		}
	}

	public Resource(String summary, Set<String> attributes)
			throws URISyntaxException, SchemaViolationException {
		super(null, null);
		setId(new URI(UUID.randomUUID().toString()));
		setKind(getKind());
		setSummary(summary);
	}

	public Resource(Set<Link> links, Set<String> attributes)
			throws URISyntaxException, SchemaViolationException {
		super(null, null);
		setId(new URI(UUID.randomUUID().toString()));
		setKind(getKind());
		if (links != null) {
			setLinks(links);
		} else {
			setLinks(new HashSet<Link>());
		}
	}

	public Resource(HashMap<String, String> attributes)
			throws SchemaViolationException, URISyntaxException {
		super(new URI(UUID.randomUUID().toString()), new Kind(null, null, null,
				null, attributes.get("term"), attributes.get("title"),
				attributes.get("scheme"), null));
	}

	/**
	 * Returns summary
	 * 
	 * @return summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets summary
	 * 
	 * @param summary
	 */
	public void setSummary(String summary) {
		Resource.summary = summary;
	}

	/**
	 * Returns links
	 * 
	 * @return links
	 */
	public Set<Link> getLinks() {
		return links;
	}

	/**
	 * Sets links
	 * 
	 * @param links
	 */
	public void setLinks(Set<Link> links) {
		Resource.links = links;
	}
}