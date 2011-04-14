package occi.infrastructure.compute.actions;

import java.net.URI;
import java.util.UUID;

import occi.core.Action;
import occi.core.Method;
import occi.infrastructure.Compute;
import occi.infrastructure.injection.Injection;
import occi.infrastructure.interfaces.ComputeInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the Action Interface. In this case the DeleteAction deletes
 * all files for a existing compute resource.
 * 
 * @see Action
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public class DeleteAction extends Action {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteAction.class);

	private static ComputeInterface computeInterface = Injection
			.getComputeInterface();

	@Override
	public void execute(URI uri, Method method) {
		LOGGER.debug("libvirt: Delete Compute");
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
		computeInterface.deleteCompute(compute);
	}
}