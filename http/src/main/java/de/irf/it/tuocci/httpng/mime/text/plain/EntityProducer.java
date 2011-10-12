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

package de.irf.it.tuocci.httpng.mime.text.plain;

import de.irf.it.tuocci.core.Entity;
import de.irf.it.tuocci.core.Link;
import de.irf.it.tuocci.core.Resource;
import de.irf.it.tuocci.core.annotations.Action;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.httpng.mime.text.AbstractTextRenderer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
public class EntityProducer
        extends AbstractTextRenderer
        implements MessageBodyWriter<Entity> {

    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Entity.class.isAssignableFrom(type);
    }

    public long getSize(Entity entity, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public void writeTo(Entity entity, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(entityStream));

        /*
         * Determine OCCI type to render.
         */
        if (Resource.class.isAssignableFrom(type)) {
            /*
             * Handle Resource case.
             */
            Resource resource = (Resource) entity;

            /*
            * Render this entity's category.
            */
            String category = this.renderCategory(resource.getClass().getAnnotation(Category.class));
            bw.append(category);

            /*
            * Render all attached Mixin categories.
            */
            for (Object o : resource.getMixins()) {
                String mixin = this.renderCategory(o.getClass().getAnnotation(Category.class));
                bw.append(mixin);
            } // for

            /*
            * Render all links to other entities.
            */
            for (Link l : resource.getLinks()) {
                String link = this.renderLink(l);
                bw.append(link);
            } // for

            /*
            * Render all attributes.
            */
            for (Attribute a : resource.getAttributes()) {
                String attribute = this.renderAttribute(a, entity);
                bw.append(attribute);
            } // for

            /*
            * Render all actions.
            */
            for (Action a : resource.listActions()) {
                // String action = this.renderAction();
            } // for

        } // if
        else if (Link.class.isAssignableFrom(type)) {
            /*
             * Handle Link case.
             */
            Link link = (Link) entity;

            // TODO: not yet implemented.
        } // else if
        else {
            // TODO: not yet implemented.
        } // if
    }
}
