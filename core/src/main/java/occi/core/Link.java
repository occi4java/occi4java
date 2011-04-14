package occi.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * An OCCI base type. A Link instance associate one Resource instance with
 * another. An instance of the Link type defines a base association between two
 * Resource instances. A Link instance indicates that one Resource instance is
 * connected to another. The Link type is the second of three entry points to
 * extend the OCCI Core Model
 * 
 * For more information see "Open Cloud Computing Interface - Core"
 * specification.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class Link extends Entity {
	/**
	 * The Resource instances the Link Link instance originates from.
	 */
	private Resource link;
	/**
	 * The Resource instances the Link instance points to.
	 */
	private Resource target;

	/*
	 * set of existing link instances, for the query interface
	 */
	private static Set<Link> links = new HashSet<Link>();

	public Link(Resource link, Resource target) throws URISyntaxException {
		super(new URI(UUID.randomUUID().toString()), "link", null, null);
		this.link = link;
		this.target = target;

		links.add(this);
	}

	/**
	 * Returns link.
	 * 
	 * @return link
	 */
	public Resource getLink() {
		return link;
	}

	/**
	 * Sets link.
	 * 
	 * @param link
	 */
	public void setLink(Resource link) {
		this.link = link;
	}

	/**
	 * Returns target.
	 * 
	 * @return target
	 */
	public Resource getTarget() {
		return target;
	}

	/**
	 * Sets target.
	 * 
	 * @param target
	 */
	public void setTarget(Resource target) {
		this.target = target;
	}

	/**
	 * Returns all active links.
	 * 
	 * @return all active links
	 */
	public static Set<Link> getLinks() {
		return links;
	}

	public String getScheme() {
		return getKind().getScheme() + "infrastructure#";
	}
}