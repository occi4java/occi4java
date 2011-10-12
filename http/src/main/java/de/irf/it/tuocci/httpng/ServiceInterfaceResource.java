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

package de.irf.it.tuocci.httpng;

import de.irf.it.tuocci.core.Entity;
import de.irf.it.tuocci.core.ModelRegistry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.UUID;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
@Path("/")
public class ServiceInterfaceResource {

    /**
     * TODO: not yet commented.
     */
    private ModelRegistry modelRegistry;

    /**
     * TODO: not yet commented.
     */
    private LocationResolver locationResolver;

    /**
     * TODO: not yet commented.
     *
     * @param modelRegistry
     * @param locationResolver
     */
    public ServiceInterfaceResource(ModelRegistry modelRegistry, LocationResolver locationResolver) {
        this.modelRegistry = modelRegistry;
        this.locationResolver = locationResolver;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param entity
     * @return
     */
    @POST
    @Path("{path}")
    @Consumes({"text/plain"})
    public Entity postEntityAsTextPlain(@PathParam("path") String term, Entity entity) {
        
        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param entity
     * @return
     */
    @POST
    @Path("{path}")
    @Consumes({"text/occi"})
    public Entity postEntityAsTextOcci(@PathParam("path") String term, Entity entity) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param entity
     * @return
     */
    @POST
    @Path("{path}")
    @Consumes({"application/json"})
    public Entity postEntityAsApplicationJson(@PathParam("path") String term, Entity entity) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @return
     */
    @GET
    @Path("{term}/{id}")
    @Produces({"text/plain"})
    public Entity getEntityAsTextPlain(@PathParam("term") String term, @PathParam("id") UUID id) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @return
     */
    @GET
    @Path("{term}/{id}")
    @Produces({"text/occi"})
    public Entity getEntityAsTextOcci(@PathParam("term") String term, @PathParam("id") UUID id) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @return
     */
    @GET
    @Path("{term}/{id}")
    @Produces({"application/json"})
    public Entity getEntityAsApplicationJson(@PathParam("term") String term, @PathParam("id") UUID id) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @param entity
     * @return
     */
    @PUT
    @Path("{term}/{id}")
    @Consumes({"text/plain"})
    @Produces({"text/plain"})
    public Entity putEntityAsTextPlain(@PathParam("term") String term, @PathParam("id") UUID id, Entity entity) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @param entity
     * @return
     */
    @PUT
    @Path("{term}/{id}")
    @Consumes({"text/occi"})
    @Produces({"text/occi"})
    public Entity putEntityAsTextOcci(@PathParam("term") String term, @PathParam("id") UUID id, Entity entity) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @param entity
     * @return
     */
    @PUT
    @Path("{term}/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Entity putEntityAsApplicationJson(@PathParam("term") String term, @PathParam("id") UUID id, Entity entity) {

        return null;
    }

    /**
     * TODO: not yet commented.
     *
     * @param term
     * @param id
     * @param entity
     * @return
     */
    @DELETE
    @Path("{term}/{id}")
    public void deleteEntity(@PathParam("term") String term, @PathParam("id") UUID id) {

    }
}
