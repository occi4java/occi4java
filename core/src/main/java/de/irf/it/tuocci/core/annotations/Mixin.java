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
 * Indicates that a class provides <code>Mixin</code> capabilities for other
 * <code>Kind</code>s.
 * <p/>
 * The <code>Mixin</code> annotation marks a given class as an OCCI Core
 * Model Mixin which extends a given Kind. The annotated class <b>must
 * not</b> &mdash in contrast to {@link Kind}-annotated classes &mdash; extend
 * {@link de.irf.it.tuocci.core.Entity}. Also, a class annotated as
 * <code>Kind</code> <b>cannot</b> be annotated as <code>Mixin</code> at the
 * same time.
 * <p/>
 * In order to comply to the OCCI Core Model, every <code>Mixin</code>
 * annotation <b>must</b> have an accompanying {@link Category} annotation in
 * order to expose the type name. In addition, attributes and actions that
 * belong to the Mixin instance described by the annotated class <b>must</b>
 * be annotated accordingly.
 * <p/>
 * The relationships of a given mixin, as required by the OCCI Core Model, are
 * implicitly exposed by the class inheritance structure, and consumers of the
 * <code>Mixin</code> annotation <b>must</b> discover them using reflection.
 * Since the annotation is <b>not</b> inherited, consumers are required to
 * walk through the full type hierarchy (namely up to, and including,
 * {@link de.irf.it.tuocci.core.Entity} to discover the full relationship
 * structure of a given Mixin instance.
 * <p/>
 * It should be noted that the two-way association of Mixins and Entities as
 * described in the OCCI Core Model is not fully exposed by the
 * <code>Mixin</code> annotation: The set of resource instances &mdash; as
 * denoted by the <i>entities</i> field of the Mixin specification &mdash; is
 * not covered by this implementation and <b>should</b> be handled elsewhere.
 * <p/>
 * Although technically possible, it is <b>not recommended</b> to use the
 * <code>Mixin</code> annotation on interfaces: While the introduction of
 * actions is similar to implementations using classes,
 * attributes are difficult to handle, due to the lack of properties in the
 * language.
 * <p/>
 * Note that {@link de.irf.it.tuocci.core.Entity} classes that support the
 * attachment of certain <code>Mixin</code> instances <b>must</b> be
 * annotated as such using the {@link Attaches} annotation.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.4.3"
 * @since 0.3 ("gordons")
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mixin {

    // This space intentionally left blank.

}
