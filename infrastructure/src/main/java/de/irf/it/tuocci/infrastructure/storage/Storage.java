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

import de.irf.it.tuocci.core.Resource;
import de.irf.it.tuocci.core.annotations.Action;
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
@Category(term = "storage", scheme = "http://schemas.ogf.org/occi/infrastructure#", title = "Storage Resource")
@Kind
public class Storage
        extends Resource {

    @Attribute(name = "occi.storage.size", mutable = true)
    private Float size;

    @Attribute(name = "occi.storage.state")
    private State state;

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public State getState() {
        return state;
    }

    @Category(term = "online", scheme = "http://schemas.ogf.org/occi/infrastructure/storage/action#")
    @Action()
    public void online() {

    }

    @Category(term = "offline", scheme = "http://schemas.ogf.org/occi/infrastructure/storage/action#")
    @Action()
    public void offline() {

    }

    @Category(term = "backup", scheme = "http://schemas.ogf.org/occi/infrastructure/storage/action#")
    @Action()
    public void backup() {

    }

    @Category(term = "snapshot", scheme = "http://schemas.ogf.org/occi/infrastructure/storage/action#")
    @Action()
    public void snapshot() {

    }

    @Category(term = "resize", scheme = "http://schemas.ogf.org/occi/infrastructure/storage/action#")
    @Action()
    public void resize(@Attribute(name = "size") Float size) {

    }

    public enum State {

        ONLINE(),

        OFFLINE(),

        BACKUP(),

        SNAPSHOT(),

        RESIZE(),

        DEGRADED()

    }

}
