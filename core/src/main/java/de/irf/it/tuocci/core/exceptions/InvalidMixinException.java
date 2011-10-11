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
 * Thrown when an object is provided as {@link
 * de.irf.it.tuocci.core.annotations.Mixin}, but has incomplete or invalid
 * annotations.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see de.irf.it.tuocci.core.annotations
 * @since 0.3 ("gordons")
 */
public class InvalidMixinException
        extends Exception {

    /**
     * Creates a new instance of this class.
     */
    public InvalidMixinException() {
        super();
    }

    /**
     * Creates a new instance of this class, using the given parameters.
     *
     * @param message
     *         An error message describing the reason for this
     *         exception being thrown.
     */
    public InvalidMixinException(String message) {
        super(message);
    }

    /**
     * Creates an new instance of this class, using the given parameters.
     *
     * @param cause
     *         The originating error that was the cause for this
     *         exception.
     */
    public InvalidMixinException(Throwable cause) {
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
    public InvalidMixinException(String message, Throwable cause) {
        super(message, cause);
    }

}
