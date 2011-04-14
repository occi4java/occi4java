
import java.net.URISyntaxException;

import javax.naming.NamingException;

import occi.infrastructure.Compute;
import occi.infrastructure.Compute.Architecture;
import occi.infrastructure.Compute.State;
import occi.infrastructure.compute.actions.StopAction.Stop;
import occi.libvirt.manager.VmManager;
import occi.libvirt.vm.VirtualMachineMarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class to test all Vm Manager operations.
 * 
 * @author Sebastian Laag
 * @author Sebastian Heckmann
 */
@Ignore
public class OcciVmManagerTest {
	private VmManager vmManager;
	private Compute compute;
	private VirtualMachineMarshaller vmm;

	@Before
	public void setUp() {
		this.vmManager = new VmManager();
		this.vmm = new VirtualMachineMarshaller();
		try {
			this.compute = new Compute(Architecture.x86, 2, "Test", 2F, 2F,
					State.active, null);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testVmManagerCreate() {
		this.vmm.createComputeXmlDescription(this.compute);
		this.vmManager.startCompute(this.compute);
	}

	@After
	public void teardown() {
		this.vmManager.stopCompute(this.compute, Stop.poweroff);
		this.compute = null;
		this.vmm = null;
		this.vmManager = null;
		System.gc();
	}
}