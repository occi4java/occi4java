package occi.libvirt.manager;

import occi.infrastructure.Compute;

public class ComputeLocation {

	private final String location;
	private final Compute compute;

	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * @return the Resource
	 */
	public Compute getCompute() {
		return this.compute;
	}

	public ComputeLocation(String location, Compute compute) {
		super();
		this.location = location;
		this.compute = compute;
	}
}