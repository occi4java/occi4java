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

import de.irf.it.tuocci.core.CategoryRegistry;
import de.irf.it.tuocci.core.annotations.Category;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
@Path("-")
public class QueryInterfaceResource {

    private Set<Class<?>> registeredCategories;

    private Set<Object> registeredMixins;

    private Map<URI, Class<?>> mappedLocations;
    private CategoryRegistry categoryRegistry;
    private LocationResolver locationResolver;

    public Set<Class<?>> getRegisteredCategories() {
        return registeredCategories;
    }

    public void setRegisteredCategories(Set<Class<?>> registeredCategories) {
        this.registeredCategories = registeredCategories;
    }

    public Set<Object> getRegisteredMixins() {
        return registeredMixins;
    }

    public void setRegisteredMixins(Set<Object> registeredMixins) {
        this.registeredMixins = registeredMixins;
    }

    public Map<URI, Class<?>> getMappedLocations() {
        return mappedLocations;
    }

    public void setMappedLocations(Map<URI, Class<?>> mappedLocations) {
        this.mappedLocations = mappedLocations;
    }

    @GET
    @Produces("text/plain")
    public Response returnQueryInterfaceDataAsTextPlain() {
        Response r = null;
        
        return r;
    }

    @GET
    @Produces("text/occi")
    public Response returnQueryInterfaceDataAsTextOcci(@HeaderParam("Category") Set<String> categories) {
        Response r = null;

        /*
         * Check for requested filters, and verify applicability.
         */
        if(categories != null && !categories.isEmpty()) {
            
        } // if

        return r;
    }


    public void setCategoryRegistry(CategoryRegistry categoryRegistry) {
        this.categoryRegistry = categoryRegistry;
    }

    public CategoryRegistry getCategoryRegistry() {
        return categoryRegistry;
    }

    public void setLocationResolver(LocationResolver locationResolver) {
        this.locationResolver = locationResolver;
    }

    public LocationResolver getLocationResolver() {
        return locationResolver;
    }
}
