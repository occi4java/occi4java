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
 * Denotes an invocable operation applicable to an {@link
 * de.irf.it.tuocci.core.Entity} sub-class instance.
 * <p/>
 * The <code>Action</code> annotation indicates for a method on a class that it
 * should be exposed via OCCI as an operation that can be invoked by a
 * corresponding client.
 * <p/>
 * It <b>must</b> be used on the method level and can apply to an
 * arbitrary class, provided that this class is annotated as {@link
 * Kind} or {@link Mixin}. In addition to that, it is <b>recommended</b> for
 * the former that the class in question inherits from {@link
 * de.irf.it.tuocci.core.Entity} to ensure full OCCI Core Model compliance.
 * </p>
 * Since <code>Action</code>s in the OCCI Core Model require their own type,
 * this annotation <b>must</b> be used in conjunction with the {@link Category}
 * annotation, indicating the OCCI category of the action.
 * <p/>
 * If the <code>Action</code> requires additional attributes for invocation,
 * the corresponding method parameters <b>must</b> be annotated with the {@link
 * Attribute} annotation. Methods exposed as <code>Action</code>s <b>must
 * not</b> carry parameters that are not exposed as <code>Attribute</code>s.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.5.4"
 * @since 0.3 ("gordons")
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {

    // This space intentionally left blank.

}
