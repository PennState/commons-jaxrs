package edu.psu.version;

import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.util.ManifestUtil;

@Path("version")
public class VersionResource {

	private static final Logger LOG = LoggerFactory.getLogger(VersionResource.class);
	
	private Class<? extends Object> clazz;
	
	public VersionResource() {
		LOG.warn("Version endpoint was not initialized correctly, please add the VersionResource instance to the RestApplication singleton list.");
	}
	
	public VersionResource(Class<? extends Object> clazz) {
		this.clazz = clazz;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Version getVersion() throws IOException {
		if (clazz == null) {
			LOG.warn("unable to determine version information, please add the VersionResource instance to the RestApplication singleton list.");
			throw new NotFoundException("Version endpoint not found");
		}
		
		Manifest manifest = ManifestUtil.locateManifest(clazz);
		Version version = ManifestUtil.getVersionInfo(manifest);

		return version;
	}

}
