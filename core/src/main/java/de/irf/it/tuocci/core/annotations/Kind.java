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

package de.irf.it.tuocci.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated class corresponds to a <code>Kind</code> in
 * the OCCI Core model.
 * <p/>
 * The <code>Kind</code> annotation assigns the annotated class a unique type
 * identifier within the OCCI Core Model type system. Every {@link
 * de.irf.it.tuocci.core.Entity}-derived class <b>must</b> map to exactly one
 * type.
 * <p/>
 * In order to comply to the OCCI Core Model, every <code>Kind</code>
 * annotation <b>must</b> have an accompanying {@link Category} annotation in
 * order to expose the type name. In addition, attributes and actions that
 * belong to the Kind instance described by the annotated class <b>must</b>
 * be annotated accordingly. It is <b>recommended</b> to derive new Kind
 * instances from the {@link de.irf.it.tuocci.core.Entity} class in order to
 * ensure full compliance with the requirements of the specification.
 * <p/>
 * The relationships of a given kind, as required by the OCCI Core Model, are
 * implicitly exposed by the class inheritance structure, and consumers of the
 * <code>Kind</code> annotation <b>must</b> discover them using reflection.
 * Since the annotation is <b>not</b> inherited, consumers are required to
 * walk through the full type hierarchy (namely up to, and including,
 * {@link de.irf.it.tuocci.core.Entity} to discover the full relationship
 * structure of a given Kind instance.
 * <p/>
 * It should be noted that the two-way association of Kinds and Entities as
 * described in the OCCI Core Model is not fully exposed by the
 * <code>Kind</code> annotation: While the discovery of the concrete Entity
 * type that defines the Kind &mdash; reflected by the <i>entity_type</i>
 * field of the Kind specification &mdash; is easy (being the result of {@link
 * de.irf.it.tuocci.core.Entity#getClass()}), the set of resource instances
 * &mdash; as denoted by the <i>entities</i> field of the Kind specification
 * &mdash; is not covered by this implementation and <b>should</b> be handled
 * elsewhere.
 * <p/>
 * Although technically possible, it is <b>not recommended</b> to use the
 * <code>Kind</code> annotation on interfaces: While the introduction of
 * actions is similar to implementations using classes, <code>Entity</code>
 * attributes are difficult to handle, due to the lack of properties in the
 * language.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.4.2"
 * @since 0.3 ("gordons")
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Kind {

    // This space intentionally left blank.

}
