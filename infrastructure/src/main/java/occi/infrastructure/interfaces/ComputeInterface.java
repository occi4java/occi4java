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
	public Compute createCompute(Compute compute);
	/**
	 * Starts a existing compute resource.
	 * 
	 * @param compute
	 * @return Compute
	 */
	public Compute startCompute(Compute compute);

	/**
	 * Stops a existing compute resource.
	 * 
	 * @param compute
	 * @param stop
	 * @return Compute
	 */
	public Compute stopCompute(Compute compute, Method stop);
	
	/**
	 * Suspends a existing compute resource.
	 * 
	 * @param compute
	 * @return Compute
	 */
	public Compute suspendCompute(Compute compute, Method suspend);
	
	/**
	 * Starts a started compute resource.
	 * 
	 * @param compute
	 * @return Compute
	 */
	public Compute restartCompute(Compute compute, Method restart);
	
	/**
	 * Deletes all files for a existing compute resource.
	 * 
	 * @param compute
	 * @return compute
	 */
	public Compute deleteCompute(Compute compute);
}