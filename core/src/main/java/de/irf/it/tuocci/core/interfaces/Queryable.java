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

package de.irf.it.tuocci.core.interfaces;

import de.irf.it.tuocci.core.annotations.Action;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.annotations.Mixin;
import de.irf.it.tuocci.core.exceptions.ActionTriggerException;
import de.irf.it.tuocci.core.exceptions.AttributeAccessException;
import de.irf.it.tuocci.core.exceptions.InvalidMixinException;
import de.irf.it.tuocci.core.exceptions.UnsupportedMixinException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
public interface Queryable {

    /**
     * TODO: not yet commented.
     *
     * @return
     */
    EntityType getEntityType();

    /**
     * Provides a set of currently registered mixins. A copy which is
     * unmodifiable is returned.
     *
     * @return An unmodifiable set of currently registered mixins.
     */
    Collection<Object> getMixins();

    /**
     * Attaches an object as mixin to this entity.
     * <p/>
     * More specifically, a given, {@link de.irf.it.tuocci.core.annotations.Mixin}-annotated
     * object is registered
     * with this entity to be available with its attributes and actions.
     * <p/>
     * During attachment, several semantic checks are done:
     * <ol>
     * <li>the object carries a {@link de.irf.it.tuocci.core.annotations.Mixin}</li>
     * annotation,</li>
     * <li>the object carries a {@link de.irf.it.tuocci.core.annotations.Category}
     * annotation, and</li>
     * <li>
     * this entity supports the provided mixin as indicated by its
     * {@link de.irf.it.tuocci.core.annotations.Attaches} annotation.
     * </li>
     * </ol>
     * If either of the former are missing, or the latter is indicated, an
     * exception is thrown.
     *
     * @param o
     *         The object to be attached as a mixin to this entity.
     * @throws de.irf.it.tuocci.core.exceptions.InvalidMixinException
     *         if the provided object is missing either {@link
     *         de.irf.it.tuocci.core.annotations.Mixin} or {@link
     *         de.irf.it.tuocci.core.annotations.Category} annotations.
     * @throws de.irf.it.tuocci.core.exceptions.UnsupportedMixinException
     *         if the provided object's class is not listed in this entity's
     *         {@link de.irf.it.tuocci.core.annotations.Attaches} annotation.
     */
    void attachMixin(Object o)
            throws InvalidMixinException, UnsupportedMixinException;

    /**
     * Detaches the given object from the list of currently registered mixins.
     * <p/>
     * After removal, the mixin represented by the removed object cannot be
     * addressed anymore from this entity.
     *
     * @param o
     *         The mixin object to be removed from this entity.
     */
    void detachMixin(Object o)
            throws InvalidMixinException, UnsupportedMixinException;

    /**
     * Provides a set of accessible attributes. A copy which is unmodifiable
     * is returned.
     *
     * @return An unmodifiable list of accessible attributes.
     */
    Collection<Attribute> getAttributes();

    /**
     * Retrieves the value of an attribute on this entity or an attached mixin.
     * <p/>
     * More specifically, this method searches both the entity instance and all
     * currently attached mixins for an attribute with the provided name and,
     * if found, returns its value.
     *
     * @param name
     *         The <i>name</i> of the attribute to be retrieved.
     * @return The current value of the requested attribute.
     *
     * @throws de.irf.it.tuocci.core.exceptions.AttributeAccessException
     *         if the requested attribute cannot be found on this entity or any
     *         attached mixin, or underlying retrieval failed.
     */
    String getAttributeValue(String name)
            throws AttributeAccessException;

    /**
     * Manipulates the value of an attribute on this entity or an attached
     * mixin.
     * <p/>
     * More specifically, this method searches both the entity instance and all
     * currently attached mixins for an attribute with the provided name and,
     * if found, modifies its value to the provided argument.
     *
     * @param name
     *         The <i>name</i> of the attribute to be retrieved.
     * @param value
     *         The new value for the requested attribute.
     * @throws AttributeAccessException
     *         if the requested attribute cannot be found on this entity or any
     *         attached mixin, or underlying manipulation failed.
     */
    void setAttributeValue(String name, String value)
            throws AttributeAccessException;

    /**
     * Triggers the action identified by the provided term/scheme combination,
     * using the provided attribute values as arguments to it.
     * <p/>
     * More specifically, this method searches both the entity instance and all
     * currently attached mixins for a method with an {@link de.irf.it.tuocci.core.annotations.Action} annotation
     * of {@link Category} identified by the term/scheme combination as
     * provided
     * and, if found, invokes this method with the attribute values as its
     * arguments.
     *
     * @param term
     *         The <i>term</i> part identifying the <code>Action</code> to be
     *         triggered.
     * @param scheme
     *         The <i>scheme</i> part identifying the <code>Action</code> to be
     *         triggered.
     * @param attributes
     *         A map of attribute <i>name</i>s and values for the triggering of
     *         the requested action, as denoted in the action definition.
     * @throws de.irf.it.tuocci.core.exceptions.ActionTriggerException
     *         if the underlying method for the requested action could not be
     *         found, invoked, a signature match is detected, or the underlying
     *         mechanisms of the invocation fail.
     * @throws AttributeAccessException
     *         if an attribute cannot be found on this method, or underlying
     *         creation of the corresponding instance failed.
     */
    void triggerAction(String term, String scheme, Map<String, String> attributes)
            throws ActionTriggerException, AttributeAccessException;

    /**
     * TODO: not yet commented.
     */
    enum EntityType {

        /**
         * TODO: not yet commented.
         */
        RESOURCE,

        /**
         * TODO: not yet commented.
         */
        LINK
    }
}
