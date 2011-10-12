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

package de.irf.it.tuocci.core;

import de.irf.it.tuocci.core.annotations.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
public class ModelRegistry {

    /**
     * TODO: not yet commented.
     */
    private Map<Category, Class<?>> classesByCategory;

    /**
     * TODO: not yet commented.
     */
    private Map<Class<?>, Category> categoriesByClass;

    /**
     * TODO: not yet commented.
     *
     * @param registeredCategories
     */
    public ModelRegistry(Set<Class<?>> registeredCategories) {
        this.classesByCategory = new HashMap<Category, Class<?>>();
        this.categoriesByClass = new HashMap<Class<?>, Category>();

        for(Class<?> rc : registeredCategories) {
            if(rc.isAnnotationPresent(Category.class)) {
                Category c = rc.getAnnotation(Category.class);
                this.classesByCategory.put(c, rc);
                this.categoriesByClass.put(rc, c);
            } // if
            else {
                String message = new StringBuilder("type not acceptable: '@Category' annotations missing on \"")
                        .append(rc.getName())
                        .append("\".")
                        .toString();
                throw new IllegalArgumentException(message);
            } // else
        } // for
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    public Class<?> find(Category category) {
        return this.classesByCategory.get(category);
    }

    /**
     * TODO: not yet commented.
     *
     * @param c1ass
     * @return
     */
    public Category find(Class<?> c1ass) {
        return this.categoriesByClass.get(c1ass);
    }
}
