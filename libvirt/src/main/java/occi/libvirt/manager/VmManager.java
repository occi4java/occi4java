package occi.libvirt.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import libvirt.occi.LibvirtConfig;

import occi.config.OcciConfig;
import occi.core.Method;
import occi.infrastructure.Compute;
import occi.infrastructure.compute.actions.RestartAction.Restart;
import occi.infrastructure.compute.actions.StopAction.Stop;
import occi.infrastructure.compute.actions.SuspendAction.Suspend;
import occi.infrastructure.interfaces.ComputeInterface;
import occi.libvirt.vm.VirtualMachineMarshaller;

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VmManager implements ComputeInterface {

	private enum ACCESS {
		SESSION, SYSTEM,
	};

	// Initialize logger for Vm Manager.
	private static Logger LOGGER = LoggerFactory.getLogger(VmManager.class);
	/**
	 * String for the remote host.
	 */
	String remoteHost = "";
	boolean preferSystemConnection = false;
	/**
	 * Time to wait to kill the compute resource.
	 */
	private static int killingTime = 60 * 1000;
	/**
	 * Map of all running jobs.
	 */
	protected final Map<String, ComputeLocation> runningJobs = new HashMap<String, ComputeLocation>();

	private final Map<String, Map<ACCESS, String>> uriMapping = new HashMap<String, Map<ACCESS, String>>() {
		{
			put("qemu", new HashMap<ACCESS, String>() {
				{
					put(ACCESS.SESSION, "qemu:///system");
					put(ACCESS.SYSTEM, "qemu:///system");
				}
			});
		}
	};

	/**
	 * Map for all existing xml libvirt templates.
	 */
	private final Map<String, String> templateMap = new HashMap<String, String>() {
		{
			put("qemu", "qemuTemplateNeu.xml");
		}
	};

	/**
	 * Sets possible file extensions for the provided hypervisors.
	 * 
	 * @param compute
	 * @return string
	 */
	private String determineHypervisor(Compute compute) {
		Map<String, String[]> extensionMap = new HashMap<String, String[]>() {
			{
				put("raw", new String[] { "qemu" });
				put("qcow", new String[] { "qemu" });
				put("qcow2", new String[] { "qemu" });
			}
		};

		String vhddPath = "/home/laag/Downloads/linux-02.raw";
		LOGGER.debug("vhdd-path is {}", vhddPath);
		int index = vhddPath.lastIndexOf(".");
		if (index != -1) {
			String suffix = vhddPath.substring(index + 1);
			LOGGER.debug("extracted file extension of {} is {}", vhddPath,
					suffix);
			for (String hypervisor : extensionMap.get(suffix)) {
				LOGGER.debug("checking hypervisor capability for {}",
						hypervisor);
				return hypervisor;
			}
		}
		return null;
	}

	/**
	 * Builds the uri for the right hypervisor.
	 * 
	 * @param hypervisor
	 * @return hypervisor uri
	 */
	private String buildHypervisorURI(String hypervisor) {
		String uri;
		if (this.preferSystemConnection) {
			LOGGER.info("preferSystemConnection is enable. So i have to "
					+ "check for system-access first");
			uri = this.uriMapping.get(hypervisor).get(ACCESS.SYSTEM);
			LOGGER.debug("unformatted system-URI for {} is: {}", new Object[] {
					hypervisor, uri });
			if (uri == null) {
				LOGGER.info("There is no system support for hypervisor {}."
						+ "I have to use the session string", hypervisor);
				uri = this.uriMapping.get(hypervisor).get(ACCESS.SESSION);
				LOGGER.debug("unformatted system-URI for {} is: {}",
						new Object[] { hypervisor, uri });
			}
		} else {
			LOGGER.info("preferSystemConnection is disable. So i have to "
					+ "check for session-access first");
			uri = this.uriMapping.get(hypervisor).get(ACCESS.SESSION);
			if (uri == null) {
				LOGGER.info("There is no session support for hypervisor {}."
						+ "I have to use the system string", hypervisor);
				uri = this.uriMapping.get(hypervisor).get(ACCESS.SYSTEM);
				LOGGER.debug("unformattet session-URI for {} is: {}",
						new Object[] { hypervisor, uri });
			}
		}
		if (this.remoteHost == null || this.remoteHost.equals("")) {
			uri = String.format(uri, "");
		} else {
			uri = String.format(uri, this.remoteHost);
		}
		LOGGER.debug("uri is {}", uri);
		return uri;
	}

	/**
	 * {@inheritDoc}
	 */
	public Compute startCompute(Compute compute) {
		LOGGER.debug("Starting VM.");
		final String vmid = compute.getId().toString();
		Connect connection;
		String hypervisor = determineHypervisor(compute);
		if (hypervisor != null) {
			LOGGER.debug("Hypervisor will be: {}", hypervisor);
			String libvirtURI = buildHypervisorURI(hypervisor);
			try {
				LOGGER.debug("Opening connection to libvirt using URI {}",
						libvirtURI);
				// initialize connection to vm
				connection = new Connect(libvirtURI);
				LOGGER.debug("calling method to create vm description");
				// create xml string
				VirtualMachineMarshaller vmm = new VirtualMachineMarshaller();
				vmm.createComputeXmlDescription(compute);
				String domainDescription = null;
				try {
					domainDescription = vmm.getXmlAsString(compute.getId()
							.toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				LOGGER.info("DOMAIN DESCRIPTION: " + domainDescription);
				Domain domain = connection.domainDefineXML(domainDescription);
				LOGGER.info(
						"VM {} has been configured. Next command will start it.",
						vmid);
				LOGGER.debug("New domain will start now.");
				if (domain.getInfo().state
						.equals(DomainInfo.DomainState.VIR_DOMAIN_PAUSED)) {
					domain.resume();
					return compute;
				}
				if (new File(
						OcciConfig.getInstance().config
								.getString("occi.ramDirectory")
								+ compute.getId() + "-saved.ram").exists()) {
					// TODO hibernate umsetzen
				}

				domain.create();
				LOGGER.info("job {} is running", vmid);
				// save hypervisor for stop, suspend and resume
				// methods
				ComputeLocation location = new ComputeLocation(libvirtURI,
						compute);
				this.runningJobs.put(vmid, location);
				// compute.setVmState(VmState.PROCEEDING);
				LOGGER.debug("Listing running domains on {}", libvirtURI);
				for (int i : connection.listDomains()) {
					LOGGER.debug("\t"
							+ connection.domainLookupByID(i).getName());
				}
				LOGGER.debug("closing connection to libvirt");
				connection.close();
			} catch (LibvirtException e) {
				LOGGER.error("Exception caught: ", e);
				// compute.setVmState(VmState.FAILED);
			}
		} else {
			LOGGER.error("cannot start vm on this machine");
		}
		return compute;
	}

	/**
	 * Stops virtual machine, if exists.
	 * 
	 * @param compute
	 * @param stopAction
	 * @return compute resource
	 */
	public Compute stopCompute(Compute compute, Method stop) {
		final String computeId = compute.getId().toString();
		Connect connection;
		// Compute status;
		try {
			LOGGER.debug("Trying to connect to Hypervisor {}",
					this.runningJobs.get(computeId));
			connection = new Connect(this.runningJobs.get(computeId)
					.getLocation());
			Domain domain = connection.domainLookupByUUIDString(computeId);
			LOGGER.debug("going to shutdown vm {} through acpi-event",
					computeId);
			if (stop.toString().equals(Stop.poweroff.toString())) {
				domain.destroy();
			}
			if (stop.toString().equals(Stop.acpioff.toString())
					|| stop.toString().equals(Stop.graceful.toString())) {
				domain.shutdown();
				LOGGER.debug("waiting {} seconds", killingTime / 1000);
				try {
					Thread.sleep(killingTime);
				} catch (InterruptedException e) {
					LOGGER.error("Exception caught: ", e);
				}
				LOGGER.debug("checking status of vm {}", computeId);
				domain = connection.domainLookupByUUIDString(computeId);
				if (domain != null) {
					LOGGER.debug(
							"vm {} still active. going to destroy it right now",
							computeId);
					domain.destroy();
					// compute.setVmState(VmState.CANCELED);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						LOGGER.error("Exception caught: ", e);
					}
					LOGGER.debug("vm {} has been destroyed", computeId);
				} else {
					LOGGER.debug("vm {} has been shutdown by acpi-event",
							computeId);
					// compute.setVmState(VmState.FINISHED);
				}
			}
			LOGGER.info("Remove {} from runningJobs-List", computeId);
			this.runningJobs.remove(computeId);
			connection.close();
		} catch (LibvirtException e) {
			LOGGER.error("Exception caught: ", e);
		}
		return compute;
	}

	/**
	 * {@inheritDoc}
	 */
	public Compute suspendCompute(Compute compute, Method suspend) {
		final String computeId = compute.getId().toString();
		Connect connection;
		try {
			LOGGER.debug("Trying to connect to Hypervisor {}",
					this.runningJobs.get(computeId));
			connection = new Connect(this.runningJobs.get(computeId)
					.getLocation());
			Domain domain = connection.domainLookupByUUIDString(computeId);
			LOGGER.debug("going to suspend vm {}", computeId);
			if (suspend.toString().equals(Suspend.suspend.toString()))
				domain.suspend();
			if (suspend.toString().equals(Suspend.hibernate.toString())) {
				String filename = "tmp/" + compute.getId().toString()
						+ "-saved.ram";
				domain.save(filename);
			}
			LOGGER.info("Remove {} from runningJobs-List", computeId);
			connection.close();
		} catch (LibvirtException e) {
			LOGGER.error("Exception caught: ", e);
		}
		return compute;
	}

	/**
	 * {@inheritDoc}
	 */
	public Compute restartCompute(Compute compute, Method restart) {
		if (restart.toString().equals(Restart.graceful.toString())
				|| restart.toString().equals(Restart.warm.toString())) {
			stopCompute(compute, Stop.acpioff);
		}
		if (restart.toString().equals(Restart.cold.toString())) {
			stopCompute(compute, Stop.poweroff);
		}
		startCompute(compute);
		return compute;
	}

	public Compute createCompute(Compute compute) {
		LOGGER.debug("Creating VM.");
		final String vmid = compute.getId().toString();
		Connect connection;
		String hypervisor = determineHypervisor(compute);
		if (hypervisor != null) {
			LOGGER.debug("Hypervisor will be: {}", hypervisor);
			String libvirtURI = buildHypervisorURI(hypervisor);
			try {
				LOGGER.debug("Opening connection to libvirt using URI {}",
						libvirtURI);
				// initialize connection to vm
				connection = new Connect(libvirtURI);
				LOGGER.debug("calling method to create vm description");
				// create xml string
				VirtualMachineMarshaller vmm = new VirtualMachineMarshaller();
				vmm.createComputeXmlDescription(compute);
				String domainDescription = null;
				try {
					domainDescription = vmm.getXmlAsString(compute.getId()
							.toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				LOGGER.info("DOMAIN DESCRIPTION: " + domainDescription);
				connection.domainDefineXML(domainDescription);
				LOGGER.info(
						"VM {} has been configured. Next command will start it.",
						vmid);
				LOGGER.debug("closing connection to libvirt");
				connection.close();
			} catch (LibvirtException e) {
				LOGGER.error("Exception caught: ", e);
			}
		} else {
			LOGGER.error("cannot start vm on this machine");
		}
		return compute;
	}

	/**
	 * {@inheritDoc}
	 */
	public Compute deleteCompute(Compute compute) {
		LOGGER.debug("Deleting VM.");
		final String vmid = compute.getId().toString();
		Connect connection;
		String hypervisor = determineHypervisor(compute);
		if (hypervisor != null) {
			LOGGER.debug("Hypervisor will be: {}", hypervisor);
			String libvirtURI = buildHypervisorURI(hypervisor);
			try {
				LOGGER.debug("Opening connection to libvirt using URI {}",
						libvirtURI);
				// initialize connection to vm
				connection = new Connect(libvirtURI);

				Domain domain = connection.domainLookupByUUIDString(compute
						.getId().toString());
				if(domain.isActive() != 0)
					domain.destroy();
				domain.undefine();
				File xmlFiletoDelete = new File(LibvirtConfig.getInstance()
						.getProperty("libvirt.xmlDirectory")
						+ compute.getId()
						+ ".xml");
				if (xmlFiletoDelete.exists())
					xmlFiletoDelete.delete();
				File storageFiletoDelete = new File(LibvirtConfig.getInstance()
						.getProperty("libvirt.storageDirectory")
						+ compute.getId() + ".raw");
				if (storageFiletoDelete.exists())
					storageFiletoDelete.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return compute;
	}
}