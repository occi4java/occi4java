package occi.http;

import java.util.UUID;

import occi.config.OcciConfig;
import occi.http.check.OcciCheck;
import occi.infrastructure.Network;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OcciRestNetworks extends ServerResource {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OcciRestNetworks.class);

	@Get
	public String getOCCIRequest() {
		// set occi version info
		getServerInfo().setAgent(
				OcciConfig.getInstance().config.getString("occi.version"));

		// access the request headers and get the X-OCCI-Attribute
		Form requestHeaders = (Form) getRequest().getAttributes().get(
				"org.restlet.http.headers");
		LOGGER.debug("Current request: " + requestHeaders);

		StringBuffer buffer = new StringBuffer();
		/*
		 * Print all properties of the kind instance
		 */
		for (UUID uuid : Network.getNetworkList().keySet()) {
			buffer.append(getRootRef() + "/" + uuid.toString());

			Representation representation = OcciCheck.checkContentType(
					requestHeaders, buffer, getResponse());
			if (representation.getMediaType().toString().equals("text/occi")) {
				// Set Location Attribute
				setLocationRef(buffer.toString());
				// return http status code
				getResponse().setStatus(Status.SUCCESS_OK, " ");
				return " ";
			}
			getResponse().setStatus(Status.SUCCESS_OK);
			return buffer.toString();
		}
		getResponse().setStatus(Status.SUCCESS_NO_CONTENT, " ");
		return " ";
	}
}
