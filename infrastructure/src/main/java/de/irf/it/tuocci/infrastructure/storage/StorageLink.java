/*
 * This file is part of tuOCCI.
 *
 *     tuOCCI is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as
 *     published by the Free Software Foundation, either version 3 of
 *     the License, or (at your option) any later version.
 *
 *     tuOCCI is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with tuOCCI.  If not, see <http://www.gnu.org/licenses/>.
 */

// $Id$ //

package de.irf.it.tuocci.infrastructure.storage;

import de.irf.it.tuocci.core.Link;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.annotations.Kind;
import de.irf.it.tuocci.infrastructure.compute.Compute;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
@Category(term = "storagelink", scheme = "http://schemas.ogf.org/occi/infrastructure#", title = "StorageLink Link")
@Kind
public class StorageLink
        extends Link {

    @Attribute(name = "occi.storagelink.deviceid", mutable = true)
    private String deviceId;

    @Attribute(name = "occi.storagelink.mountpoint", required = false, mutable = true)
    private String macAddress;

    @Attribute(name = "occi.networkinterface.state")
    private State state;

    public StorageLink(Compute source, Storage target) {
        super(source, target);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public State getState() {
        return state;
    }

    public enum State {

        active(),

        inactive()

    }
}
