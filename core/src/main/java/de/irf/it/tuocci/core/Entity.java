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

package de.irf.it.tuocci.core;

import de.irf.it.tuocci.core.annotations.Action;
import de.irf.it.tuocci.core.annotations.Attaches;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.annotations.Kind;
import de.irf.it.tuocci.core.annotations.Mixin;
import de.irf.it.tuocci.core.exceptions.ActionTriggerException;
import de.irf.it.tuocci.core.exceptions.AttributeAccessException;
import de.irf.it.tuocci.core.exceptions.InvalidMixinException;
import de.irf.it.tuocci.core.exceptions.UnsupportedMixinException;
import de.irf.it.tuocci.core.interfaces.Queryable;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * The root type for all resources as described in the OCCI Core Model.
 * <p/>
 * The <code>Entity</code> class represents the root type in the OCCI Core
 * Model type system. All OCCI-compliant resources are derived from this class.
 * It exposes mechanisms for managing an OCCI resource with respect to its
 * fundamental attributes, ({@link #id} and {@link #title}, and provides
 * facilities for managing a resource's mixins.
 * <p/>
 * This <code>Entity</code> exposes methods for the generic management of more
 * specific OCCI resource sub-types, including
 * <ul>
 * <li>attachment and detachment of mixins</li>
 * <li>retrieval and manipulation of attributes, and</li>
 * <li>invocation of actions.</li>
 * </ul>
 * In addition, it ensures the correct attribution in the OCCI term/scheme
 * model.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de">Alexander
 *         Papaspyrou</a>
 * @version $Id$
 * @see "Ralf Nyrén, Andy Edmonds, Alexander Papaspyrou, and Thijs Metsch, <a
 *      href="http://ogf.org/documents/GFD.183.pdf">Open Cloud Computing
 *      Interface &ndash; Core</a>, Open Grid Forum Proposed Recommendation,
 *      GFD-P-R.183, April 2011, Section 4.5.1"
 * @since 0.3 ("gordons")
 */
@Category(term = "entity", scheme = "http://schemas.ogf.org/occi/core#", title = "Entity type")
@Kind
public abstract class Entity implements Queryable {

    /**
     * A unique identifier (within the service provider's namespace) of this
     * Entity.
     */
    @Attribute(name = "occi.core.id")
    private URI id;

    /**
     * The display name of this entity.
     */
    @Attribute(name = "occi.core.title", required = false, mutable = true)
    private String title;

    /**
     * A map of mixins currently attached to this entity. Objects contained in
     * this set can be expected to carry a {@link Mixin} annotation and
     * corresponding information for usage.
     * <p/>
     * The key to the map is a string concatenated as
     * <code>&lt;scheme&gt;&lt;term&gt;</code>.
     *
     * @see #attachMixin(Object)
     */
    private Map<String, Object> mixins;

    /**
     * Creates a new instance of this class.
     * <p/>
     * During creation, a random identifier for this entity is generated using
     * an <a href="http://www.ietf.org/rfc/rfc4122.txt">RFC
     * 4122-compliant UUID</a> in the corresponding URN namespace
     * (<code>urn:uuid:</code><i>uuid</i>).
     */
    protected Entity() {
        this(URI.create(new StringBuffer("urn:uuid:").append(UUID.randomUUID().toString()).toString()));
    }

    /**
     * Creates a new instance of this class, using the given parameters.
     *
     * @param id
     *         The unique identifier to be used for this class. It is
     *         <b>recommended</b> that an
     *         <a href="http://www.ietf.org/rfc/rfc4122.txt" RFC 4122-compliant
     *         UUID</a> is used and the appropriate namespace ("urn:uuid") is
     *         exposed.
     */
    protected Entity(URI id) {
        this.id = id;
        this.mixins = new HashMap<String, Object>();
    }

    /**
     * Returns the unique identifier of this entity.
     *
     * @return The unique identifier of this entity.
     */
    public URI getId() {
        return id;
    }

    /**
     * Returns the title of this entity.
     *
     * @return The title of this entity.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this entity.
     *
     * @param title
     *         The new title of this entity.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Provides a set of currently registered mixins. A copy which is
     * unmodifiable is returned.
     *
     * @return An unmodifiable set of currently registered mixins.
     */
    public Collection<Object> getMixins() {
        return Collections.unmodifiableCollection(this.mixins.values());
    }

    /**
     * Attaches an object as mixin to this entity.
     * <p/>
     * More specifically, a given, {@link Mixin}-annotated object is registered
     * with this entity to be available with its attributes and actions.
     * <p/>
     * During attachment, several semantic checks are done:
     * <ol>
     * <li>the object carries a {@link Mixin}</li> annotation,</li>
     * <li>the object carries a {@link Category} annotation, and</li>
     * <li>
     * this entity supports the provided mixin as indicated by its
     * {@link Attaches} annotation.
     * </li>
     * </ol>
     * If either of the former are missing, or the latter is indicated, an
     * exception is thrown.
     *
     * @param o
     *         The object to be attached as a mixin to this entity.
     * @throws InvalidMixinException
     *         if the provided object is missing either {@link Mixin} or {@link
     *         Category} annotations.
     * @throws UnsupportedMixinException
     *         if the provided object's class is not listed in this entity's
     *         {@link Attaches} annotation.
     */
    public final void attachMixin(Object o)
            throws InvalidMixinException, UnsupportedMixinException {
        /*
         * Check whether the examined object is annotated properly.
         */
        if (o.getClass().isAnnotationPresent(Mixin.class) && o.getClass().isAnnotationPresent(Category.class)) {
            /*
             * yes: Check whether the provided mixin is supported by this resource type.
             */
            if (this.supportsMixin(o.getClass())) {
                /*
                 * yes: Add to list of attached mixins and notify subclasses.
                 */
                Category c = o.getClass().getAnnotation(Category.class);
                this.mixins.put(c.scheme() + c.term(), o);
            } // if
            else {
                /*
                 * no: throw an exception denoting that the provided mixin is not supported.
                 */
                Category category = o.getClass().getAnnotation(Category.class);
                String message = new StringBuilder("not supported: '")
                        .append(this.getClass().getName())
                        .append("' cannot attach mixins of category \"")
                        .append(category.scheme()).append(category.term())
                        .append("\".")
                        .toString();
                throw new UnsupportedMixinException(message);
            } // else
        } // if
        else {
            /*
             * no: throw an exception denoting that the provided object is no mixin.
             */
            String message = new StringBuilder("not a mixin: '@Category' or '@Mixin' annotations missing on \"")
                    .append(o.getClass().getName())
                    .append("\".")
                    .toString();
            throw new InvalidMixinException(message);
        } // else
    }

    /**
     * Detaches the given object from the list of currently registered mixins.
     * <p/>
     * After removal, the mixin represented by the removed object cannot be
     * addressed anymore from this entity.
     *
     * @param o
     *         The mixin object to be removed from this entity.
     */
    public final void detachMixin(Object o) {
        this.mixins.remove(o);
    }

    /**
     * Checks for a given class whether it is supported by this Entity as
     * Mixin.
     * <p/>
     * More specifically, this method verifies whether the {@link #attachMixin}
     * method will accept an instance of the provided class.
     *
     * @param c
     *         The class to be checked whether it is supported by this Entity
     *         as
     *         Mixin.
     * @return <code>true</code>, if this Entity supports instances of the
     *         provided class as mixin; <code>false</code>, otherwise.
     */
    private boolean supportsMixin(Class<?> c) {
        boolean result = false;

        Class<?> self = this.getClass();

        while (!Object.class.equals(self)) {
            if (self.isAnnotationPresent(Attaches.class)) {
                Attaches attaches = self.getAnnotation(Attaches.class);
                for (Class<?> mixin : attaches.mixins()) {
                    if (c.equals(mixin)) {
                        result = true;
                        break;
                    } // if
                } // for
            } // if
            self = self.getSuperclass();
        } // while

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @return
     */
    public Set<Attribute> getAttributes() {
        Set<Attribute> result = new HashSet<Attribute>();

        /*
         * Search on this entity, climbing up the class hierarchy.
         */
        Class<?> self = this.getClass();
        while (!Object.class.equals(self)) {
            for (Field f : self.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(Attribute.class)) {
                    result.add(f.getAnnotation(Attribute.class));
                } // if
            } // for
            self = self.getSuperclass();
        } // while

        /*
         * Search on all mixins.
         */
        for (Object o : this.getMixins()) {
            for (Field f : o.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(Attribute.class)) {
                    result.add(f.getAnnotation(Attribute.class));
                } // if
            } // for
        } // for

        return result;
    }

    /**
     * Retrieves the field for an attribute with the provided name for the
     * given
     * object.
     *
     * @param object
     *         The object to be inspected.
     * @param name
     *         The name of the attribute the corresponding field is to be
     *         retrieved.
     * @return The field corresponding to the provided attribute name, if
     *         available; <code>null</code>, otherwise.
     */
    private Field findFieldForAttribute(Object object, String name) {
        Field result = null;

        Class<?> self = object.getClass();

        /*
        * Search on this entity, climbing up the class hierarchy.
        */
        while (!Object.class.equals(self)) {
            for (Field f : this.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(Attribute.class)
                        && f.getAnnotation(Attribute.class).name().equals(name)) {
                    result = f;
                    break;
                } // if
            } // for
            self = self.getSuperclass();
        } // while

        return result;
    }

    /**
     * Retrieves the getter/setter for a given field using reflection and the
     * JavaBeans Introspection API. If no corresponding method exists, nothing
     * will be returned.
     *
     * @param field
     *         The field for which getter/setter are to be retrieved
     * @param flag
     *         An indicator to which method should be retrieved;
     *         <code>true</code> returns the getter, while <code>false</code>
     *         returns the setter.
     * @return The getter/setter for the provided field, if available; or
     *         <code>null</code>, otherwise.
     */
    private Method retrieveAttributeGetterSetter(Field field, boolean flag) {
        Method result = null;

        BeanInfo bi = null;
        try {
            bi = Introspector.getBeanInfo(this.getClass());
        } // try
        catch (IntrospectionException e) {
            e.printStackTrace();  // TODO: not implemented yet.
        } // catch

        assert bi != null;
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (pd.getName().equals(field.getName())) {
                result = (flag ? pd.getReadMethod() : pd.getWriteMethod());
                break;
            } // if
        } // for

        return result;
    }

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
     * @throws AttributeAccessException
     *         if the requested attribute cannot be found on this entity or any
     *         attached mixin, or underlying retrieval failed.
     */
    public final String getAttributeValue(String name)
            throws AttributeAccessException {
        String result = null;

        Method getter = null;
        Object target = null;

        /*
         * Search for field corresponding to provided attribute name.
         */
        Field f = this.findFieldForAttribute(this, name);
        if (f != null) {
            /*
             * Found on entity.
             */
            target = this;
        } // if
        else {
            for (Object o : this.mixins.values()) {
                f = this.findFieldForAttribute(o, name);
                if (f != null) {
                    /*
                     * Found on mixin.
                     */
                    target = o;
                } // if
            } // for
        } // else

        /*
         * Retrieve value.
         */
        if (f != null) {
            getter = this.retrieveAttributeGetterSetter(f, true);
            Object o = null;
            try {
                o = getter.invoke(target);
            } // try
            catch (IllegalAccessException e) {
                String message = new StringBuffer("retrieval of '")
                        .append(name)
                        .append("' attribute on ")
                        .append(target)
                        .append(" failed: corresponding getter not available or not public")
                        .toString();
                throw new AttributeAccessException(message, e);
            } // catch
            catch (InvocationTargetException e) {
                String message = new StringBuffer("retrieval of '")
                        .append(name)
                        .append("' attribute on ")
                        .append(target)
                        .append("' failed: underlying getter raised an exception (")
                        .append(e.getMessage())
                        .append(")")
                        .toString();
                throw new AttributeAccessException(message, e);
            } // catch
            result = o.toString();
        } // if
        else {
            String message = new StringBuffer("retrieval of '")
                    .append(name)
                    .append("' attribute on ")
                    .append(target)
                    .append("' failed: no corresponding field found ")
                    .toString();
            throw new AttributeAccessException(message);
        } // else

        return result;
    }

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
    public final void setAttribute(String name, String value)
            throws AttributeAccessException {
        Method setter = null;
        Object target = null;

        /*
         * Search for field corresponding to provided attribute name.
         */
        Field f = this.findFieldForAttribute(this, name);
        if (f != null) {
            /*
             * Found on entity.
             */
            target = this;
        } // if
        else {
            for (Object o : this.mixins.values()) {
                f = this.findFieldForAttribute(o, name);
                if (f != null) {
                    /*
                     * Found on mixin.
                     */
                    target = o;
                } // if
            } // for
        } // else

        /*
         * Manipulate value.
         */
        if (f != null) {
            setter = this.retrieveAttributeGetterSetter
                    (f, false);

            Object o = this.instanceFromString(f.getDeclaringClass(), value);
            if (o == null) {
                String message = new StringBuffer("modification of '")
                        .append(name)
                        .append("' attribute on ")
                        .append(target)
                        .append("' failed: no single argument constructor, 'fromString', or 'valueOf' method for conversion to ")
                        .append(f.getDeclaringClass())
                        .append(" found")
                        .toString();
                throw new AttributeAccessException(message);
            } //

            try {
                setter.invoke(target, o);
            }  // try
            catch (IllegalAccessException e) {
                String message = new StringBuffer("modification of '")
                        .append(name)
                        .append("' attribute on ")
                        .append(target)
                        .append("' failed: corresponding setter not available or not public")
                        .toString();
                throw new AttributeAccessException(message, e);
            } // catch
            catch (InvocationTargetException e) {
                String message = new StringBuffer("modification of '")
                        .append(name)
                        .append("' attribute on ")
                        .append(target)
                        .append("' failed: underlying setter raised an exception (")
                        .append(e.getMessage())
                        .append(")")
                        .toString();
                throw new AttributeAccessException(message, e);
            } // catch
        } // if
        else {
            String message = new StringBuffer("manipulation of '")
                    .append(name)
                    .append("' attribute on ")
                    .append(target)
                    .append("' failed: no corresponding field found")
                    .toString();
            throw new AttributeAccessException(message);
        } // else
    }

    /**
     * Creates a new instance of the requested class via its single {@link
     * String} argument constructor.
     *
     * @param type
     *         The class of the instance to be created.
     * @param value
     *         The value for the constructor argument.
     * @return A new instance of the requested class, properly initialized with
     *         the given value, if successful; <code>null</code>, otherwise.
     */
    private Object instanceViaConstructor(Class<?> type, String value) {
        Object result = null;

        Constructor<?> c = null;
        try {
            c = type.getConstructor(String.class);
        } // try
        catch (NoSuchMethodException e) {
            // This space intentionally left blank.
        } // catch

        if (c != null) {
            try {
                result = c.newInstance(value);
            } // try
            catch (InstantiationException e) {
                // This space intentionally left blank.
            } // catch
            catch (IllegalAccessException e) {
                // This space intentionally left blank.
            } // catch
            catch (InvocationTargetException e) {
                // This space intentionally left blank.
            } // catch
        } // if

        return result;
    }

    /**
     * Creates a new instance of the requested class via a single argument
     * method.
     *
     * @param type
     *         The class of the instance to be created.
     * @param methodName
     *         The name of the single {@link String} argument method to be
     *         invoked.
     * @param value
     *         The value for the constructor argument.
     * @return A new instance of the requested class, properly initialized with
     *         the given value, if successful; <code>null</code>, otherwise.
     */
    private Object instanceViaMethod(Class<?> type, String methodName, String value) {
        Object result = null;

        Method m = null;
        try {
            m = type.getMethod(methodName, String.class);
        } // try
        catch (NoSuchMethodException e) {
            // This space intentionally left blank.
        }  // catch

        if (m != null) {
            try {
                result = m.invoke(null, value);
            } // try
            catch (IllegalAccessException e) {
                // This space intentionally left blank.
            } // catch
            catch (InvocationTargetException e) {
                // This space intentionally left blank.
            } // catch
        } // if

        return result;
    }

    /**
     * Creates a new instance of the requested class using the given argument.
     * <p/>
     * In that order, the following approaches are used to achieve this:
     * <ol>
     * <li>A single {@link String} argument constructor,</li>
     * <li>A <code>fromString(String)</code> method, and</li>
     * <lI>A <code>valueOf(String)</code> method.</lI>
     * </ol>
     *
     * @param c
     *         The class of the instance to be created.
     * @param value
     *         The value for the argument.
     * @return A new instance of the requested class, properly initialized with
     *         the given value, if successful; <code>null</code>, otherwise.
     */
    private Object instanceFromString(Class<?> c, String value) {
        Object result = null;

        result = this.instanceViaConstructor(c, value);
        if (result == null) {
            result = this.instanceViaMethod(c, "fromString", value);
        } // if
        if (result == null) {
            result = this.instanceViaMethod(c, "valueOf", value);
        } // if

        return result;
    }

    /**
     * Retrieves the method representing an action as indentified by the
     * provided term/scheme combination, using reflection and the JavaBeans
     * Introspection API. If no corresponding method exists, nothing will be
     * returned.
     *
     * @param object
     *         The object for which the method is to be retrieved
     * @param term
     *         The OCCI term part of the action for which the method is to be
     *         retrieved.
     * @param scheme
     *         The OCCI scheme part of the action for which the method is to be
     *         retrieved.
     * @return The method representing the action as identified by the provided
     *         term/scheme combination, if available; or <code>null</code>,
     *         otherwise.
     */
    private Method findMethodForAction(Object object, String term, String scheme) {
        Method result = null;

        Class<?> self = object.getClass();

        /*
        * Search on this entity, climbing up the class hierarchy.
        */
        while (!Object.class.equals(self)) {
            for (Field f : this.getClass().getDeclaredFields()) {
                for (Method m : self.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(Action.class)
                            && m.isAnnotationPresent(Category.class)
                            && m.getAnnotation(Category.class).term().equals(term)
                            && m.getAnnotation(Category.class).scheme().equals
                            (scheme)) {
                        result = m;
                    } // if
                } // if
            } // for
            self = self.getSuperclass();
        } // while

        return result;
    }

    public final Set<Action> listActions() {
        Set<Action> result = new HashSet<Action>();

        /*
         * Search on this entity, climbing up the class hierarchy.
         */
        Class<?> self = this.getClass();
        while (!Object.class.equals(self)) {
            for (Method m : self.getClass().getDeclaredMethods()) {
                if (m.isAnnotationPresent(Action.class)) {
                    result.add(m.getAnnotation(Action.class));
                } // if
            } // for
            self = self.getSuperclass();
        } // while

        /*
         * Search on all mixins.
         */
        for (Object o : this.getMixins()) {
            for (Method m : o.getClass().getDeclaredMethods()) {
                if (m.isAnnotationPresent(Action.class)) {
                    result.add(m.getAnnotation(Action.class));
                } // if
            } // for
        } // for

        return result;
    }

    /**
     * Triggers the action identified by the provided term/scheme combination,
     * using the provided attribute values as arguments to it.
     * <p/>
     * More specifically, this method searches both the entity instance and all
     * currently attached mixins for a method with an {@link Action} annotation
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
     * @throws ActionTriggerException
     *         if the underlying method for the requested action could not be
     *         found, invoked, a signature match is detected, or the underlying
     *         mechanisms of the invocation fail.
     * @throws AttributeAccessException
     *         if an attribute cannot be found on this method, or underlying
     *         creation of the corresponding instance failed.
     */
    public final void triggerAction(String term, String scheme, Map<String, String> attributes)
            throws ActionTriggerException, AttributeAccessException {
        Method method = null;
        Object target = null;

        /*
         * Search for method corresponding to provided term/scheme values.
         */
        method = this.findMethodForAction(this, term, scheme);
        if (method != null) {
            /*
             * Found on entity.
             */
            target = this;
        } // if
        else {
            for (Object o : this.mixins.values()) {
                method = this.findMethodForAction(o, term, scheme);
                if (method != null) {
                    /*
                     * Found on mixin.
                     */
                    target = o;
                } // if
            } // for
        } // else

        /*
        * Trigger action.
        */
        if (method != null) {
            Class<?>[] pts = method.getParameterTypes();
            List<Object> invocationArguments = new ArrayList<Object>();

            /*
             * Gather attributes for action triggering.
             */
            if (pts.length == attributes.size()) {
                for (Class<?> pt : pts) {
                    Attribute a = pt.getAnnotation(Attribute.class);
                    if (attributes.containsKey(a.name())) {
                        Object o = this.instanceFromString(pt, attributes.get(a.name()));
                        if (o == null) {
                            String message = new StringBuffer("invocation of '")
                                    .append(scheme).append(term)
                                    .append("' with attribute '")
                                    .append(a.name())
                                    .append("' failed: no single argument constructor, 'fromString', or 'valueOf' method for conversion to ")
                                    .append(pt)
                                    .append(" found")
                                    .toString();
                            throw new AttributeAccessException(message);
                        } // if
                        else {
                            invocationArguments.add(o);
                        } // else
                    } // if
                    else {
                        String message = new StringBuffer("triggering of action '")
                                .append(scheme).append(term)
                                .append("' failed: provided attributes do not match action signature")
                                .toString();
                        throw new ActionTriggerException(message);
                    } // else

                } // for

                /*
                 * Invoke corresponding method with arguments.
                 */
                try {
                    method.invoke(target, invocationArguments.toArray());
                } // try
                catch (IllegalAccessException e) {
                    String message = new StringBuffer("triggering of action '")
                            .append(scheme).append(term)
                            .append("' failed: method static, not public, or otherwise inaccessible")
                            .toString();
                    throw new AttributeAccessException(message, e);
                } // catch
                catch (InvocationTargetException e) {
                    String message = new StringBuffer("modification of action '")
                            .append(scheme).append(term)
                            .append("' failed: underlying method raised an exception (")
                            .append(e.getMessage())
                            .append(")")
                            .toString();
                    throw new AttributeAccessException(message, e);
                } // catch

            } // if
            else {
                String message = new StringBuffer("triggering of action '")
                        .append(scheme).append(term)
                        .append("' failed: number of attributes (providing ")
                        .append(attributes.size())
                        .append(") does not match action signature (requiring ")
                        .append(pts.length)
                        .append(")")
                        .toString();
                throw new ActionTriggerException(message);
            } // else
        } // if
        else {
            String message = new StringBuffer("triggering of action '")
                    .append(scheme).append(term)
                    .append("' failed: no corresponding method found on ")
                    .append(this.toString())
                    .toString();
            throw new AttributeAccessException(message);
        } // else
    }

}
