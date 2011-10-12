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

import de.irf.it.tuocci.core.ModelRegistry;
import de.irf.it.tuocci.core.annotations.Category;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
@Path("/{path:-/|\\.well-known/org/ogf/occi/-/}")
public class QueryInterfaceResource {

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
    public QueryInterfaceResource(ModelRegistry modelRegistry, LocationResolver locationResolver) {
        this.modelRegistry = modelRegistry;
        this.locationResolver = locationResolver;
    }

    /**
     * TODO: not yet commented.
     *
     * @return
     */
    @GET
    @Produces("text/plain")
    public Response getAsTextPlain() {
        Response r = null;
        
        return r;
    }

    /**
     * TODO: not yet commented.
     *
     * @return
     */
    @GET
    @Produces("text/occi")
    public Response getAsTextOcci() {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @return
     */
    @GET
    @Produces("application/json")
    public Response getAsApplicationJson() {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    @POST
    @Consumes("text/plain")
    public Response postAsTextPlain(Category category) {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    @POST
    @Consumes("text/occi")
    public Response postAsTextOcci(Category category) {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    @POST
    @Consumes("application/json")
    public Response postAsApplicationJson(Category category) {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    @DELETE
    @Consumes("text/plain")
    public Response deleteAsTextPlain(Category category) {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    @DELETE
    @Consumes("text/occi")
    public Response deleteAsTextOcci(Category category) {
        Response result = null;

        return result;
    }

    /**
     * TODO: not yet commented.
     *
     * @param category
     * @return
     */
    @DELETE
    @Consumes("application/json")
    public Response deleteAsApplicationJson(Category category) {
        Response result = null;

        return result;
    }
}
