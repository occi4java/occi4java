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

package de.irf.it.tuocci.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates for a <code>Kind</code> that it accepts the attachment of one or
 * more <code>Mixin</code>s.
 * <p/>
 * The <code>Attaches</code> annotation allows a given class which exposes
 * itself as a {@link Kind} to indicate that its implementation is able to
 * handle the attachment of other classes which expose themselves as {@link
 * Mixin}s.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @since 0.3 ("gordons")
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Attaches {

    /**
     * Returns the <code>Mixin</code>s supported by the annotated class.
     * <p/>
     * The classes returned <b>must</b> carry the {@link Mixin} annotation.
     *
     * @return A non-empty list of types supported by the annotated class.
     */
    Class<?>[] mixins();

}
