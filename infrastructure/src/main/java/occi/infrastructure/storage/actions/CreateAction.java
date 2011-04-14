package occi.infrastructure.storage.actions;

import java.net.URI;
import java.util.UUID;

import occi.core.Action;
import occi.core.Method;
import occi.infrastructure.Storage;
import occi.infrastructure.injection.Injection;
import occi.infrastructure.interfaces.StorageInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAction extends Action {
	private static final Logger LOGGER = LoggerFactory
	.getLogger(CreateAction.class);
	
	private static StorageInterface storageInterface = Injection.getStorageInterface();
	
	@Override
	public void execute(URI uri, Method method) {
		LOGGER.debug("libvirt: Create Storage");
		Storage storage = null;
		String uriString = uri.toString();
		if (uri.toString().endsWith("/")) {
			uriString = uriString.substring(0, uri.toString().length() - 1);
			uriString = uriString.substring(uriString.length() - 36);
		}
		LOGGER.debug("URI " + uriString);
		UUID storageUuid = UUID.fromString(uriString);
		LOGGER.debug("UUID " + storageUuid.toString());
		for (UUID uuid : Storage.getStorageList().keySet()) {
			if (uuid.equals(storageUuid)) {
				storage = Storage.getStorageList().get(storageUuid);
			}
		}
		storageInterface.createStorage(storage);
	}
}