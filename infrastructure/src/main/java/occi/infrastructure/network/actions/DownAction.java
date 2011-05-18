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

package occi.infrastructure.network.actions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.naming.directory.SchemaViolationException;

import occi.core.Action;
import occi.core.Category;
import occi.core.Method;
import occi.infrastructure.Network;
import occi.infrastructure.injection.Injection;
import occi.infrastructure.interfaces.NetworkInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownAction extends Action {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DownAction.class);

	private static NetworkInterface networkInterface = Injection
			.getNetworkInterface();

	/**
	 * Enum for the Start Actions.
	 */
	public enum Down implements Method {
		down
	}

	private Down down;

	public DownAction() throws SchemaViolationException, URISyntaxException {
		Category category = new Category("down",
				"http://schemas.ogf.org/occi/infrastructure/network/action#",
				"Action");
	}

	@Override
	public void execute(URI uri, Method method) {
		Network network = null;
		String uriString = uri.toString();
		if (uri.toString().endsWith("/")) {
			uriString = uriString.substring(0, uri.toString().length() - 1);
			uriString = uriString.substring(uriString.length() - 36);
		}
		LOGGER.debug("URI " + uriString);
		UUID networkUuid = UUID.fromString(uriString);
		LOGGER.debug("UUID " + networkUuid.toString());
		for (UUID uuid : Network.getNetworkList().keySet()) {
			if (uuid.equals(networkUuid)) {
				network = Network.getNetworkList().get(networkUuid);
			}
		}
		networkInterface.downNetwork(network);

	}
}