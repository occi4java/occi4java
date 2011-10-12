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

/**
 * Provides implementations for the OCCI Core Model base resource types.
 * <p/>
 * The Open Cloud Cmputing Core Model (<i>"the model"</i>) defines a
 * representation of instance types which can be manipulated through an OCCI
 * rendering implementation. It is an abstraction of real-world resources,
 * including means to identify, classify, associate and extend those resources.
 * <p/>
 * This package defines the base resource types that provide common
 * capabilities for all OCCI element instances. Such can be
 *
 * <ul>
 *     <li>
 *         a {@link Resource}, representing a real-world
 *         "thing" that is modeled through the OCCI family of specifications;
 *     </li>
 *     <li>
 *         a {@link Link}, representing a bi-directional
 *         connection between two resources; or
 *     </li>
 *     <li>
 *         any other class extending {@link Entity},
 *         therefore defining new root elements in the OCCI type hierarchy.
 *     </li>
 * </ul>
 *
 * In order to create custom types within the OCCI type system,
 * it is <b>recommended</b> to extend either <code>Resource</code> or
 * <code>Link</code>; this way, compliance with the OCCI family of
 * specifications is easiest to achieve.
 * <p/>
 * For special purposes, it is also possible to extend <code>Queryable</code>
 * directly; however, consumers of the package are encouraged to use the
 * provided implementation <b>unless</b> there is a good reason to not do so.
 * Not using either of the three is <b>strongly</b> discouraged; although it
 * is possible to start from scratch using only the annotations provided in
 * this module as well, it will require a full re-implementation of at least
 * the <code>Queryable</code> class.
 *
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou,
 * and Thijs Metsch, <a href="http://ogf.org/documents/GFD.183.pdf">Open
 * Cloud Computing Interface &ndash; Core</a>, Open Grid Forum Proposed
 * Recommendation, GFD-P-R.183, April 2011."
 *
 */
package de.irf.it.tuocci.core;
