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
	Storage createStorage(Storage storage);

	/**
	 * Removes the storage resource file.
	 * 
	 * @param storage
	 */
	Storage removeStorage(Storage storage);

	/**
	 * Starts a existing storage resource.
	 * 
	 * @param storage
	 */
	Storage onlineStorage(Storage storage);

	/**
	 * Stops a existing storage resource.
	 * 
	 * @param storage
	 */
	Storage offlineStorage(Storage storage);

	/**
	 * Makes a snapshot of the storage resource.
	 * 
	 * @param storage
	 */
	Storage makeSnapshot(Storage storage);

	/**
	 * Makes a backup of the storage resource.
	 * 
	 * @param storage
	 */
	Storage makeBackup(Storage storage);

	/**
	 * Resizes a existing storage resource.
	 * 
	 * @param storage
	 */
	Storage resizeStorage(Storage storage);
}