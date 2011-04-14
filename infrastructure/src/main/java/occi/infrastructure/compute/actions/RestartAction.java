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
 * Implementation of the Action Interface. In this case the RestartAction
 * restarts the current VM identified by the UUID
 * 
 * @see Action
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class RestartAction extends Action {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestartAction.class);

	private static ComputeInterface computeInterface = Injection.getComputeInterface();
	
	/**
	 * Enum for the Restart Actions
	 * 
	 */
	public enum Restart implements Method {
		graceful, warm, cold
	}

	public Restart restart;
	public HashSet<String> attributes = new HashSet<String>();

	public RestartAction() throws SchemaViolationException, URISyntaxException {
		attributes.add("graceful");
		attributes.add("warm");
		attributes.add("cold");
		Category category = new Category("restart",
				"http://schemas.ogf.org/occi/infrastructure/compute/action#",
				"Action", attributes);

	}

	@Override
	public void execute(URI uri, Method restart) {
		LOGGER.debug("libvirt: Restart");
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
		computeInterface.restartCompute(compute, restart);
	}

	/**
	 * Returns all Attributes of this action.
	 * 
	 * @return attributes
	 */
	public HashSet<String> getAttributes() {
		if (attributes.isEmpty()) {
			for (Restart restart : Restart.values()) {
				attributes.add(restart.toString());
			}
		}
		return attributes;
	}

	/**
	 * Returns the possible Restart Attributes of the Enum
	 * 
	 * @return String
	 */
	public static String getRestartAttributesAsString() {
		StringBuffer restartAttributes = new StringBuffer();
		for (Restart restart : Restart.values()) {
			restartAttributes.append(restart.toString() + ", ");
		}
		return restartAttributes.toString();
	}

}