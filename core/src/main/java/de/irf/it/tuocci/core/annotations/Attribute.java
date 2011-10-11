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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares an instance field or method parameter as an <code>Attribute</code>
 * exposed via OCCI.
 * <p/>
 * The <code>Attribute</code> annotation indicates that a certain fields or
 * parameters of a class map to attributes in the OCCI Core Model.
 * <p/>
 * Depending on the usage, it belongs to the <code>Category</code> indicated by
 * <ul>
 * <li>
 * the surrounding class, if it annotates a field, or
 * </li>
 * <li>
 * the corresponding method, if it annotates a parameter.
 * </li>
 * </ul>
 * The annotation <b>must</b> be used in conjunction with the {@link Category}
 * annotation and
 * <ul>
 * <li>
 * the {@link Kind} or {@link Mixin} annotations, if referring to a field, or
 * </li>
 * <li>
 * the {@link Action} annotation, if referring to a parameter.
 * </li>
 * </ul>
 * Consumers of the <code>Attribute</code> annotation are expected to
 * discover the corresponding Category via reflection.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see de.irf.it.tuocci.core.Entity
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.4.1 and 4.5.1"
 * @since 0.3 ("gordons")
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Attribute {

    /**
     * Returns the fully-qualified name of the attribute.
     *
     * @return the name of the attribute in its fully-qualified version.
     */
    String name();

    /**
     * Indicates whether this attribute is required to be set. If so, it must
     * be
     * assigned a value during the whole lifetime of the corresponding {@link
     * de.irf.it.tuocci.core.Entity}.
     * <p/>
     * By default, attributes are <em>required</em>.
     *
     * @return <code>true</code>, if the attribute is required for this entity;
     *         <code>false</code>, otherwise.
     */
    boolean required() default true;

    /**
     * Indicates whether this attribute is mutable. If so, clients to the model
     * are allowed to modify its value after creation of the {@link
     * de.irf.it.tuocci.core.Entity}.
     * <p/>
     * By default, attributes are <em>immutable</em>.
     *
     * @return <code>true</code>, if the attribute is modifiable by a client;
     *         <code>false</code>, otherwise.
     */
    boolean mutable() default false;

}
