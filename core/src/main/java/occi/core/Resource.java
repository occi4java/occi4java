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
 * OCCI Core Model.
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
	 * Link from the set MUST also remove the Link instance.
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
		if (this.links != null) {
			setLinks(this.links);
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
		this.links = links;
	}
}