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
package occi.http.console;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import occi.infrastructure.Compute;
import occi.infrastructure.Storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starts a console where you can execute several commands to monitor the
 * application.
 * 
 * @author Sebastian Laag
 */
public class OcciConsole extends Thread {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciConsole.class);

	@Override
	public void run() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (input != null) {
				String[] args = input.split(" ");
				try {
					parse(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Checks console input for valid commands.
	 * 
	 * @param command
	 */
	private static void parse(String[] args) {
		if (args[0].equals("help")) {
			LOGGER.info("Avaiable Commands: ");
			LOGGER.info("\t compute");
			LOGGER.info("\t\t lists all compute resources");
			
			LOGGER.info("\t storage");
			LOGGER.info("\t\t lists all storage resources");
			
			LOGGER.info("\t exit");
			LOGGER.info("\t\t terminates http server");
		} else if (args[0].equals("compute")) {
			// initialize compute list
			Map<UUID, Compute> computeList = Compute.getComputeList();
			if (computeList.size() > 0) {
				int i = 1;
				for (UUID id : computeList.keySet()) {
					Compute compute = computeList.get(id);
					LOGGER.info("ID: {}", compute.getId());
					if (i < computeList.size()) {
						LOGGER.info(",");
					}
					i++;
				}
			} else {
				LOGGER.info("There are no compute resources.");
			}
		} else if(args[0].equals("storage")) {
			// initialize compute list
			Map<UUID, Storage> storageList = Storage.getStorageList();
			if (storageList.size() > 0) {
				int i = 1;
				for (UUID id : storageList.keySet()) {
					Storage storage = storageList.get(id);
					LOGGER.info("ID: {}", storage.getId());
					if (i < storageList.size()) {
						LOGGER.info(",");
					}
					i++;
				}
			} else {
				LOGGER.info("There are no storage resources.");
			}
		}
		if (args[0].equals("exit")) {
			LOGGER.info("Shutting down...");
			System.exit(0);
		}
	}
}