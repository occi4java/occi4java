package occi.infrastructure.interfaces;

import occi.infrastructure.Storage;

/**
 * Interface for all operations on storage resources. Necessary interface to
 * connect to the occi implementation.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public interface StorageInterface {
	/**
	 * Creates a storage resource as file on the disk.
	 * 
	 * @param storage
	 */
	public Storage createStorage(Storage storage);

	/**
	 * Removes the storage resource file.
	 * 
	 * @param storage
	 */
	public Storage removeStorage(Storage storage);

	/**
	 * Starts a existing storage resource.
	 * 
	 * @param storage
	 */
	public Storage onlineStorage(Storage storage);

	/**
	 * Stops a existing storage resource.
	 * 
	 * @param storage
	 */
	public Storage offlineStorage(Storage storage);

	/**
	 * Makes a snapshot of the storage resource.
	 * 
	 * @param storage
	 */
	public Storage makeSnapshot(Storage storage);

	/**
	 * Makes a backup of the storage resource.
	 * 
	 * @param storage
	 */
	public Storage makeBackup(Storage storage);

	/**
	 * Resizes a existing storage resource.
	 * 
	 * @param storage
	 */
	public Storage resizeStorage(Storage storage);
}