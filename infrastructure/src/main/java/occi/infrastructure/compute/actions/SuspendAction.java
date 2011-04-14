package occi.infrastructure.compute.actions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.UUID;

import javax.naming.directory.SchemaViolationException;

import occi.core.Action;
import occi.core.Category;
import occi.core.Method;
import occi.infrastructure.Compute;
import occi.infrastructure.injection.Injection;
import occi.infrastructure.interfaces.ComputeInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the Action Interface. In this case the SuspendAction
 * suspends the current VM identified by the UUID
 * 
 * @see Action
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class SuspendAction extends Action {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SuspendAction.class);

	private static ComputeInterface computeInterface = Injection.getComputeInterface();
	
	/**
	 * Enum for the Start Actions
	 * 
	 */
	public enum Suspend implements Method {
		hibernate, suspend
	}

	public Suspend suspend;
	public HashSet<String> attributes = new HashSet<String>();

	public SuspendAction() throws SchemaViolationException, URISyntaxException {
		if (attributes.isEmpty()) {
			attributes.add("hibernate");
			attributes.add("suspend");
		}
		Category category = new Category("start",
				"http://schemas.ogf.org/occi/infrastructure/compute/action#",
				"Action", attributes);
	}

	@Override
	public void execute(URI uri, Method suspend) {
		LOGGER.debug("libvirt: Suspend");
		Compute compute = null;
		String uriString = uri.toString();
		if (uri.toString().endsWith("/")) {
			uriString = uriString.substring(0, uri.toString().length() - 1);
			uriString = uriString.substring(uriString.length() - 36);
		}
		LOGGER.debug("URI " + uriString);
		UUID computeUuid = UUID.fromString(uriString);
		LOGGER.debug("UUID " + computeUuid.toString());
		for (UUID uuid : Compute.getComputeList().keySet()) {
			if (uuid.equals(computeUuid)) {
				compute = Compute.getComputeList().get(computeUuid);
			}
		}
		computeInterface.suspendCompute(compute, suspend);
	}

	/**
	 * Returns all Attributes of this action.
	 * 
	 * @return attributes
	 */
	public HashSet<String> getAttributes() {
		if (attributes.isEmpty()) {
			for (Suspend suspend : Suspend.values()) {
				attributes.add(suspend.toString());
			}
		}
		return attributes;
	}

	/**
	 * Returns the possible Suspend Attributes of the Enum
	 * 
	 * @return String
	 */
	public static String getSuspendAttributesAsString() {
		StringBuffer suspendAttributes = new StringBuffer();
		for (Suspend suspend : Suspend.values()) {
			suspendAttributes.append(suspend.toString() + ", ");
		}
		return suspendAttributes.toString();
	}
}