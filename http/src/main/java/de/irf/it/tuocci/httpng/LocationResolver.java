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
import de.irf.it.tuocci.core.Link;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
public class LocationResolver {

    /**
     * TODO: not yet commented.
     */
    private Map<String, Class<?>> categoriesByLocation;

    /**
     * TODO: not yet commented.
     */
    private Map<String, Entity> resourcesByPath;

    /**
     * TODO: not yet commented.
     */
    private Map<Entity, String> pathsByResource;

    /**
     * TODO: not yet commented.
     */
    protected LocationResolver() {
        this.resourcesByPath = new TreeMap<String, Entity>();
        this.pathsByResource = new HashMap<Entity, String>();
    }

    /**
     * TODO: not yet commented.
     *
     * @param locationMapping
     */
    public LocationResolver(Map<String, Class<?>> locationMapping) {
        this();
        this.categoriesByLocation = new HashMap<String, Class<?>>();
        this.categoriesByLocation.putAll(locationMapping);
    }

    /**
     * TODO: not yet commented.
     *
     * @param location
     * @param resource
     */
    public void addResource(String location, Entity resource) {
        this.resourcesByPath.put(location, resource);
        this.pathsByResource.put(resource, location);
    }

    /**
     * TODO: not yet commented.
     *
     * @param location
     */
    public void removeResource(String location) {
        Entity resource = this.resourcesByPath.get(location);
        this.resourcesByPath.remove(location);
        this.pathsByResource.remove(resource);
    }

    /**
     * TODO: not yet commented.
     *
     * @param resource
     */
    public void removeResource(Entity resource) {
        String location = this.pathsByResource(resource);
        this.pathsByResource.remove(resource);
        this.resourcesByPath.remove(location);
    }

    public String getTargetLocation(Link l) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
