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
 * Denotes the OCCI category of the annotated class or method.
 * <p/>
 * The <code>Category</code> annotation indicates the OCCI Core Model type of
 * the annotated element. It <b>must</b> be used in conjunction with
 * <ul>
 * <li>
 * the {@link Kind} or {@link Mixin} annotations; in this case, it attaches to
 * the class being annotated as such, or
 * </li>
 * <li>
 * the {@link Action} annotation; in this case, it attaches to the method being
 * annotated as such.
 * </li
 * </ul>
 * A <code>Category</code> uniquely identifies itself through the combination
 * of
 * a <i>term</i> and a <i>scheme</i>; two classes <b>must not</b> expose the
 * same <code>Category</code> within the same runtime.
 * <p/>
 * The attributes exposed by the category as defined in the corresponding OCCI
 * specifications or future extensions <b>must</b> be present within the
 * annotated element; that is, for a class being with a <code>Category</code>
 * annotation, fields must exist with matching {@link Attribute} annotations as
 * described in the corresponding specification(s), while in case of an {@link
 * Action}, the method must supply matching {@link Attribute} annotations on
 * its parameters. Consumers of the <code>Category</code> annotation are
 * expected to discover the attributes corresponding to a certain category via
 * reflection.
 * <p/>
 * Categories defined by the OCCI family of specifications (see the <a
 * href="http://www.ogf.org/">Open Grid Forum web pages</a>) use the
 * <code>http://schemas.ogf.org/occi/</code> base URL for their schemes; this
 * base URL <b>must not</b> be used by service provider extensions.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.4.1"
 * @since 0.3 ("gordons")
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Category {

    /**
     * The unique identifier of the Category instance within the categorization
     * scheme described by the annotation on the corresponding class.
     * <p/>
     * Together with the Category's <code>scheme</code> property, the
     * <code>term</code> denotes the identifying URI of a category. As such, it
     * <b>must</b> be unique within the categorization scheme.
     * <p/>
     * This attribute is {@link Attribute#required() required} and <b>not</b>
     * {@link Attribute#mutable() mutable}.
     *
     * @return The <code>term</code> part of the identifying URI for this
     *         category.
     *
     * @see #scheme()
     */
    String term();

    /**
     * The categorization scheme of the Category instance described by the
     * annotation on the corresponding class.
     * <p/>
     * Together with the Category's <code>scheme</code> property, the
     * <code>term</code> denotes the identifying URI of a category.
     * <p/>
     * Each scheme <b>must</b> be a valid URI, ending with the fragment
     * delimiter
     * '#'. The base URL <i>http://schemas.ogf.org/occi/</i> is reserved for
     * OCCI-specified categories and <b>must not</b> be used for service
     * provider extensions.
     * <p/>
     * This attribute is {@link Attribute#required() required} and <b>not</b>
     * {@link Attribute#mutable() mutable}.
     *
     * @return The <code>scheme</code> of the identifying URI for this
     *         category.
     *
     * @see #term()
     */
    String scheme();

    /**
     * The display name of the Category instance described by the annotation on
     * the corresponding class.
     * <p/>
     * This attribute is <b>not</b> {@link Attribute#required() required} and
     * <b>not</b> {@link Attribute#mutable() mutable}.
     *
     * @return The display name of this Category.
     */
    String title() default "";

}
