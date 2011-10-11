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

package de.irf.it.tuocci.core.exceptions;

/**
 * Thrown when an {@link de.irf.it.tuocci.core.annotations.Attribute} cannot be
 * accessed on request. Possible reasons include
 * <ul>
 * <li>the attribute being not present on the Entity at all,</li>
 * <li>
 * the modification of an attribute having failed (because of
 * provided data being invalid, out of range, wrong type,
 * or not acceptable), or
 * </li>
 * <li>
 * the field carrying the attribute not being exposed properly as
 * expected from the common Java Beans pattern.
 * </li>
 * </ul>
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see <a href="http://download.oracle.com/javase/1.5.0/docs/guide/beans/index.html">The
 *      Java Beans Component API</a>
 * @see de.irf.it.tuocci.core.annotations
 * @since 0.3 ("gordons")
 */
public class AttributeAccessException
        extends Exception {

    /**
     * Creates a new instance of this class.
     */
    public AttributeAccessException() {
        super();
    }

    /**
     * Creates a new instance of this class, using the given parameters.
     *
     * @param message
     *         An error message describing the reason for this
     *         exception being thrown.
     */
    public AttributeAccessException(String message) {
        super(message);
    }

    /**
     * Creates an new instance of this class, using the given parameters.
     *
     * @param cause
     *         The originating error that was the cause for this
     *         exception.
     */
    public AttributeAccessException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates an new instance of this class, using the given parameters.
     *
     * @param message
     *         An error message describing the reason for this
     *         exception being thrown.
     * @param cause
     *         The originating error that was the cause for this
     *         exception.
     */
    public AttributeAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
