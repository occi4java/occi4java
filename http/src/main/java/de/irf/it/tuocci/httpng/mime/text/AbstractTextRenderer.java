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

package de.irf.it.tuocci.httpng.mime.text;

import de.irf.it.tuocci.core.Entity;
import de.irf.it.tuocci.core.Link;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.exceptions.AttributeAccessException;
import de.irf.it.tuocci.httpng.LocationResolver;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
public abstract class AbstractTextRenderer {

    private LocationResolver locationResolver;

    public void setLocationResolver(LocationResolver locationResolver) {
        this.locationResolver = locationResolver;
    }

    public LocationResolver getLocationResolver() {
        return locationResolver;
    }

    public String renderCategory(Category c) {
        StringBuffer result = new StringBuffer();

        result.append("Category: ");
        result.append(c.term());
        result.append(";\n");

        result.append("\tscheme = ");
        result.append("\"").append(c.scheme()).append("\"");
        result.append(";\n");

        result.append("\tclass = ");
        result.append("\"kind\"");
        result.append("\n");

        return result.toString();
    }

    public String renderCategory(Class c) {
        return null;
    }

    public String renderLink(Link l) {
        StringBuffer result = new StringBuffer();

        result.append("Link: ");
        result.append("<").append(locationResolver.getTargetLocation(l)).append(">");
        result.append(";\n");

        result.append("\trel = ");
        Category rc = l.getTarget().getClass().getAnnotation(Category.class);
        String rel = new StringBuffer(rc.scheme()).append(rc.term()).toString();
        result.append("\"").append(rel).append("\"");
        result.append(";\n");

        result.append("\tself = ");
        //result.append("\"").append(Resolver.instance().getInstanceLocation(l)).append("\"");
        result.append(";\n");

        result.append("\tcategory = ");
        Category lc = l.getClass().getAnnotation(Category.class);
        String category = new StringBuffer(lc.scheme()).append(lc.term()).toString();
        result.append("\"").append(category).append("\"");
        result.append(";\n");

        return result.toString();
    }

    public String renderAttribute(Attribute a, Entity e) {
        StringBuffer result = new StringBuffer();

        result.append("X-OCCI-Attribute: ");
        result.append("\"").append(a.name()).append(" = ");
        result.append("\"");
        String value = null;
        try {
            value = e.getAttributeValue(a.name());
        } // try
        catch (AttributeAccessException aae) {
            aae.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } // catch
        result.append("\"");
        result.append("\n");

        return result.toString();
    }
}
