/**
 * Copyright (C) 2010-2011 Sebastian Heckmann, Sebastian Laag
 *
 * Contact Email: <sebastian.heckmann@udo.edu>, <sebastian.laag@udo.edu>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package occi.infrastructure.interfaces;

import occi.core.Method;
import occi.infrastructure.Compute;

/**
 * Interface to execute operations on compute resources. Necessary interface to
 * connect to the occi implementation.
 * 
 * @author Sebastian Heckmann
 * @author Sebastian Laag
 */
public interface ComputeInterface {
	/**
	 * Creates a new definition for a compute resource.
	 * 
	 * @param compute
	 * @return compute
	 */
	Compute createCompute(Compute compute);
	/**
	 * Starts a existing compute resource.
	 * 
	 * @param compute
	 * @return Compute
	 */
	Compute startCompute(Compute compute);

	/**
	 * Stops a existing compute resource.
	 * 
	 * @param compute
	 * @param stop
	 * @return Compute
	 */
	Compute stopCompute(Compute compute, Method stop);
	
	/**
	 * Suspends a existing compute resource.
	 * 
	 * @param compute
	 * @return Compute
	 */
	Compute suspendCompute(Compute compute, Method suspend);
	
	/**
	 * Starts a started compute resource.
	 * 
	 * @param compute
	 * @return Compute
	 */
	Compute restartCompute(Compute compute, Method restart);
	
	/**
	 * Deletes all files for a existing compute resource.
	 * 
	 * @param compute
	 * @return compute
	 */
	Compute deleteCompute(Compute compute);
}