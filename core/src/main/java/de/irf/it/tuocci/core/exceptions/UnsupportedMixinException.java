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
 * Thrown when a {@link de.irf.it.tuocci.core.annotations.Mixin} is attached to
 * a class that does not indicate support for it.
 * <p/>
 * More specifically, if a <code>Mixin</code> instance is assigned using
 * {@link de.irf.it.tuocci.core.Entity#attachMixin(Object)},
 * a check is performed whether the <code>Entity</code> instance (or the
 * corresponding sub-class of it) carries the {@link
 * de.irf.it.tuocci.core.annotations.Attaches} annotation with the value of
 * {@link Object#getClass()} for the provided <code>Mixin</code>. If not,
 * this exception is raised.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Id$
 */
public class UnsupportedMixinException
        extends Exception {

    /**
     * Creates a new instance of this class.
     */
    public UnsupportedMixinException() {
        super();
    }

    /**
     * Creates an new instance of this class, using the given parameters.
     *
     * @param message
     *         An error message describing the reason for this
     *         exception being thrown.
     */
    public UnsupportedMixinException(String message) {
        super(message);
    }

    /**
     * Creates an new instance of this class, using the given parameters.
     *
     * @param cause
     *         The originating error that was the cause for this
     *         exception.
     */
    public UnsupportedMixinException(Throwable cause) {
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
    public UnsupportedMixinException(String message, Throwable cause) {
        super(message, cause);
    }

}
