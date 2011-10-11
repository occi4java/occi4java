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
 * Thrown when an {@link de.irf.it.tuocci.core.annotations.Action} cannot be
 * properly invoked. Possible reasons include
 * <ul>
 * <li>the action being not present on the Entity at all,</li>
 * <li>
 * the invocation of an action having failed (because of
 * provided parameters being invalid, out of range, wrong type,
 * or not acceptable, or some operational failure in the underlying
 * method),
 * </li>
 * <li>
 * one or more of the provided arguments not matching the "action
 * signature" or not properly being converted to the corresponding
 * parameter type of the method, or
 * </li>
 * <li>
 * the method carrying the action not being exposed properly (i.e.
 * not being public and instance-based).
 * </li>
 * </ul>
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see de.irf.it.tuocci.core.annotations.Attribute
 * @see de.irf.it.tuocci.core.annotations
 * @since 0.3 ("gordons")
 */
public class ActionTriggerException
        extends Exception {

    /**
     * Creates a new instance of this class.
     */
    public ActionTriggerException() {
        super();
    }

    /**
     * Creates a new instance of this class, using the given parameters.
     *
     * @param message
     *         An error message describing the reason for this
     *         exception being thrown.
     */
    public ActionTriggerException(String message) {
        super(message);
    }

    /**
     * Creates an new instance of this class, using the given parameters.
     *
     * @param cause
     *         The originating error that was the cause for this
     *         exception.
     */
    public ActionTriggerException(Throwable cause) {
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
    public ActionTriggerException(String message, Throwable cause) {
        super(message, cause);
    }

}
