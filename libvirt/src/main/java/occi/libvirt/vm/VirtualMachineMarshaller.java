package occi.libvirt.vm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import libvirt.occi.LibvirtConfig;

import occi.infrastructure.Compute;
import occi.infrastructure.Network;
import occi.infrastructure.interfaces.XmlInterface;
import occi.infrastructure.links.IPNetworkInterface;
import occi.libvirt.generated.Domain;
import occi.libvirt.generated.Domain.Clock;
import occi.libvirt.generated.Domain.Devices;
import occi.libvirt.generated.Domain.Devices.Disk;
import occi.libvirt.generated.Domain.Devices.Disk.Source;
import occi.libvirt.generated.Domain.Devices.Disk.Target;
import occi.libvirt.generated.Domain.Devices.Graphics;
import occi.libvirt.generated.Domain.Devices.Input;
import occi.libvirt.generated.Domain.Devices.Interface;
import occi.libvirt.generated.Domain.Devices.Interface.Vlan;
import occi.libvirt.generated.Domain.Features;
import occi.libvirt.generated.Domain.Os;
import occi.libvirt.generated.Domain.Os.Boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualMachineMarshaller implements XmlInterface {
	// Initialize logger for Vm Manager.
	private static Logger LOGGER = LoggerFactory
			.getLogger(VirtualMachineMarshaller.class);

	/**
	 * Create a Xml File for the start of a virtual machine.
	 * 
	 * @param compute
	 * @return domain
	 */
	private Domain createComputeDescription(Compute compute) {
		Domain domain = new Domain();
		domain.setType("kvm");
		domain.setName(compute.getId().toString());
		domain.setUuid(compute.getId().toString());
		domain.setMemory(compute.getMemory());
		domain.setCurrentMemory(compute.getMemory());
		domain.setVcpu(compute.getCores());
		Os os = new Os();
		os.setType("hvm");
		Boot boot = new Boot();
		boot.setDev("hd");
		os.setBoot(boot);
		domain.setOs(os);
		Features features = new Features();
		features.setAcpi("");
		domain.setFeatures(features);
		Clock clock = new Clock();
		clock.setOffset("localtime");
		domain.setClock(clock);
		domain.setOnPoweroff("destroy");
		domain.setOnReboot("restart");
		domain.setOnCrash("restart");
		Devices devices = new Devices();
		Disk disk = new Disk();
		disk.setType("file");
		disk.setDevice("disk");
		Source source = new Source();
		// TODO richtiges Storage File auswählen und Pfad setzen
		source.setFile(LibvirtConfig.getInstance().getProperty(
				"libvirt.storageDirectory")
				+ compute.getId() + ".raw");
		Target target = new Target();
		target.setDev("hda");
		disk.setSource(source);
		disk.setTarget(target);
		devices.setDisk(disk);
		Graphics graphics = new Graphics();
		graphics.setType("vnc");
		graphics.setPort(-1);
		devices.setGraphics(graphics);
		Input input = new Input();
		input.setType("mouse");
		input.setBus("ps2");
		devices.setInput(input);
		domain.setDevices(devices);

		return domain;
	}

	/**
	 * Gets the xml file of an existing compute resource and adds the
	 * description for the network interface.
	 * 
	 * @param uuid
	 * @param network
	 */
	private Domain addNetworkDescription(String uuid, Network network) {
		Domain domain = createComputeDescription(Compute.getComputeList().get(
				UUID.fromString(uuid)));

		Devices devices = domain.getDevices();
		Interface inter = new Interface();
		inter.setType("bridge");
		inter.setName(network.getLabel());
		inter.setUuid(network.getId().toString());
		occi.libvirt.generated.Domain.Devices.Interface.Source source = new occi.libvirt.generated.Domain.Devices.Interface.Source();
		source.setBridge("br0");
		inter.setSource(source);
		Vlan vlan = new Vlan();
		vlan.setTag(String.valueOf(network.getVlan()));
		inter.setVlan(vlan);
		devices.setInterface(inter);
		domain.setDevices(devices);

		return domain;
	}

	/**
	 * Add network interface description to the xml file.
	 * 
	 * @param uuid
	 * @param networkInterface
	 * @return domain
	 */
	private Domain addNetworkInterfaceDescription(String uuid,
			NetworkInterface networkInterface) {
		Domain domain = addNetworkDescription(uuid, Network.getNetworkList()
				.get(UUID.fromString(uuid)));

		// TODO Not implemented yet

		return domain;
	}

	/**
	 * Add ip network interface description to the xml file.
	 * 
	 * @param uuid
	 * @param IPNetworkInterface
	 * @return domain
	 */
	private Domain addIpNetworkDescription(String uuid,
			IPNetworkInterface ipNetworkInterface) {
		Domain domain = null;

		// TODO Not implemented yet

		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	public void createComputeXmlDescription(Compute compute) {
		writeFileToDisk(createComputeDescription(compute));
	}

	/**
	 * {@inheritDoc}
	 */
	public void createNetworkXmlDescription(String uuid, Network network) {
		writeFileToDisk(addNetworkDescription(uuid, network));
	}

	/**
	 * {@inheritDoc}
	 */
	public void createNetworkInterfaceXmlDescription(String uuid,
			NetworkInterface networkInterface) {
		writeFileToDisk(addNetworkInterfaceDescription(uuid, networkInterface));
	}

	/**
	 * Gets the xml file of an existing compute resource and adds the
	 * description for the network interface.
	 * 
	 * @param uuid
	 * @param network
	 */
	public String getXmlAsString(String uuid) throws FileNotFoundException {
		// if the file does not exist, it cant be read
		if (!new File(LibvirtConfig.getInstance().getProperty(
				"libvirt.xmlDirectory")
				+ uuid + ".xml").exists())
			createComputeXmlDescription(Compute.computeList.get(UUID
					.fromString(uuid)));
		String xmlString = "";
		// read xml file
		String filename = "/" + uuid + ".xml";
		InputStream is = null;
		while (is == null)
			is = new FileInputStream(LibvirtConfig.getInstance().getProperty(
			"libvirt.xmlDirectory") + filename);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		try {
			// iterate through stream and add it to a string
			while (br.ready()) {
				xmlString += br.readLine() + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return xml file as string
		return xmlString;
	}

	/**
	 * Writes a existing domain definition as XML file to disk.
	 * 
	 * @param domain
	 */
	private void writeFileToDisk(Domain domain) {
		String xmlDirectory = LibvirtConfig.getInstance().getProperty(
				"libvirt.xmlDirectory");
		LOGGER.debug("XML Directory: " + xmlDirectory);
		FileOutputStream fos = null;
		File file = null;
		try {
			new File(xmlDirectory).mkdir();
			file = new File(LibvirtConfig.getInstance().getProperty(
					"libvirt.xmlDirectory")
					+ domain.getUuid().toString() + ".xml");
			if (file.exists())
				file.delete();
			fos = new FileOutputStream(file);

			JAXBContext context = JAXBContext.newInstance(Domain.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(domain, fos);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}