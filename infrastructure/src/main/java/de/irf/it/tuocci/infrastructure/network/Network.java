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

package de.irf.it.tuocci.infrastructure.network;

import de.irf.it.tuocci.core.Resource;
import de.irf.it.tuocci.core.annotations.Action;
import de.irf.it.tuocci.core.annotations.Attaches;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.annotations.Kind;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
@Category(term = "network", scheme = "http://schemas.ogf.org/occi/infrastructure#", title = "Network Resource")
@Kind
@Attaches(mixins = {IPNetwork.class})
public class Network
        extends Resource {

    @Attribute(name = "occi.network.vlan", required = false, mutable = true)
    private Short vlan;

    @Attribute(name = "occi.network.label", required = false, mutable = true)
    private String label;

    @Attribute(name = "occi.network.state")
    private State state;

    @Category(term = "up", scheme = "http://schemas.ogf.org/occi/infrastructure/network/action#")
    @Action()
    public void up() {

    }

    @Category(term = "down", scheme = "http://schemas.ogf.org/occi/infrastructure/network/action#")
    @Action()
    public void down() {

    }

    public enum State {

        ACTIVE(),

        INACTIVE()

    }
}
