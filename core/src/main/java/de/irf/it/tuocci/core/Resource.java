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

import java.util.Set;

/**
 * Describes a concrete resource in the OCCI Core Model.
 * <p/>
 * A <code>Resource</code> is the general object in the OCCI Core Model and
 * suitable to represent real world resources such as machines, services, etc.
 * through specialization.
 * <p/>
 * It also exposes means for building related groups of resources via
 * corresponding link objects, and mechanisms for their manipulation.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.5.2"
 * @since 0.3 ("gordons")
 */
@Category(term = "resource", scheme = "http://schemas.ogf.org/occi/core#", title = "Resource")
@Kind
public class Resource
        extends Entity {

    /**
     * A summarizing description of the Resource instance.
     */
    @Attribute(name = "occi.core.summary", required = false, mutable = true)
    private String summary;

    /**
     * A set of {@link Link} compositions.
     */
    private Set<Link> links;

    /**
     * Returns the summarizing description of this resource.
     *
     * @return The summarizing description of this resource.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Modifies the summarizing description of this resource.
     *
     * @param summary
     *         The new value for the summarizing description of this resource.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Returns a set of currently associated links for this resource.
     *
     * @return A set of currently associated links for this resource.
     */
    public Set<Link> getLinks() {
        return links;
    }

    /**
     * Changes the set of currently associated links for this resource.
     *
     * @param links
     *         The new set of currently associated links for this resource.
     */
    public void setLinks(Set<Link> links) {
        this.links = links;
    }
}
