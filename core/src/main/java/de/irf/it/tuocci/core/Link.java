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

package de.irf.it.tuocci.core;

import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.annotations.Kind;

/**
 * Defines a base association between two resources in the OCCI Core Model.
 * <p/>
 * A <code>Link</code> indicates that one <code>resource</code> instance is
 * connected to another. Either its source or target may refer to an item not
 * within the owning service provider's domain.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.5.3"
 * @since 0.3 ("gordons")
 */
@Category(term = "link", scheme = "http://schemas.ogf.org/occi/core#", title = "Link")
@Kind
public class Link
        extends Entity {

    /**
     * The {@link Resource} instance this <code>Link</code> originates from.
     */
    @Attribute(name = "occi.core.source", required = true)
    private Resource source;

    /**
     * The {@link Resource} instance this <code>Link</code> points to.
     */
    @Attribute(name = "occi.core.target", required = true)
    private Resource target;

    /**
     * Creates a new instance of this class, using the given parameters.
     *
     * @param source
     *         The {@link Resource} instance this <code>Link</code> originates
     *         from.
     * @param target
     *         The {@link Resource} instance this <code>Link</code> points to.
     */
    public Link(Resource source, Resource target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Returns the resource this link originates from.
     *
     * @return The resource this link originates from.
     */
    public Resource getSource() {
        return source;
    }

    /**
     * Modifies the resource this link originates from.
     *
     * @param source
     *         The new value for the resource this link originates from.
     */
    public void setSource(Resource source) {
        this.source = source;
    }

    /**
     * Returns the resource this link points to.
     *
     * @return The resource this link points to.
     */
    public Resource getTarget() {
        return target;
    }

    /**
     * Modifies the resource this link points to.
     *
     * @param target
     *         The new value for the resource this link points to.
     */
    public void setTarget(Resource target) {
        this.target = target;
    }
}
