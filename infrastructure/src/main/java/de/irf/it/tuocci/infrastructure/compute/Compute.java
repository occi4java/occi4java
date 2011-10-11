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

package de.irf.it.tuocci.infrastructure.compute;

import de.irf.it.tuocci.core.Resource;
import de.irf.it.tuocci.core.annotations.Action;
import de.irf.it.tuocci.core.annotations.Attribute;
import de.irf.it.tuocci.core.annotations.Category;
import de.irf.it.tuocci.core.annotations.Kind;

/**
 * TODO: not yet commented.
 *
 * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
 *         Papaspyrou</a>
 * @version $Revision$ (as of $Date$)
 */
@Category(term = "compute", scheme = "http://schemas.ogf.org/occi/infrastructure#", title = "Compute Resource")
@Kind
public class Compute
        extends Resource {

    @Attribute(name = "occi.compute.architecture", required = false, mutable = true)
    private Architecture architecture;

    @Attribute(name = "occi.compute.cores", required = false, mutable = true)
    private Integer cores;

    @Attribute(name = "occi.compute.hostname", required = false, mutable = true)
    private String hostname;

    @Attribute(name = "occi.compute.speed", required = false, mutable = true)
    private Float speed;

    @Attribute(name = "occi.compute.memory", required = false, mutable = true)
    private Float memory;

    @Attribute(name = "occi.compute.state")
    private State state;

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getMemory() {
        return memory;
    }

    public void setMemory(Float memory) {
        this.memory = memory;
    }

    public State getState() {
        return state;
    }

    @Category(term = "start", scheme = "http://schemas.ogf.org/occi/infrastructure/compute/action#")
    @Action()
    public void start() {

    }

    @Category(term = "stop", scheme = "http://schemas.ogf.org/occi/infrastructure/compute/action#")
    @Action()
    public void stop(@Attribute(name = "method") StateChangeMethod method) {
        switch (method) {
            case GRACEFUL:
                // TODO: implement
            case ACPIOFF:
                // TODO: implement
            case POWEROFF:
                // TODO: implement
            default:
                String message = new StringBuffer("not allowed: method '").append(method).append("'.").toString();
                throw new IllegalArgumentException(message);
        } // switch
    }

    @Category(term = "restart", scheme = "http://schemas.ogf.org/occi/infrastructure/compute/action#")
    @Action()
    public void restart(@Attribute(name = "method") StateChangeMethod method) {
        switch (method) {
            case GRACEFUL:
                // TODO: implement
            case WARM:
                // TODO: implement
            case COLD:
                // TODO: implement
            default:
                String message = new StringBuffer("not allowed: method '").append(method).append("'.").toString();
                throw new IllegalArgumentException(message);
        } // switch
    }

    @Category(term = "suspend", scheme = "http://schemas.ogf.org/occi/infrastructure/compute/action#")
    @Action()
    public void suspend(@Attribute(name = "method") StateChangeMethod method) {
        switch (method) {
            case HIBERNATE:
                // TODO: implement
            case SUSPEND:
                // TODO: implement
            default:
                String message = new StringBuffer("not allowed: method '").append(method).append("'.").toString();
                throw new IllegalArgumentException(message);
        } // switch
    }

    /**
     * TODO: not yet commented.
     *
     * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
     *         Papaspyrou</a>
     * @version $Revision$ (as of $Date$)
     */
    public static enum Architecture {

        X86(),

        X64()

    }

    /**
     * TODO: not yet commented.
     *
     * @author <a href="mailto:alexander.papaspyrou@tu-dortmund.de>Alexander
     *         Papaspyrou</a>
     * @version $Revision$ (as of $Date$)
     */
    public static enum State {

        ACTIVE(),

        INACTIVE(),

        SUSPENDED()

    }
}
