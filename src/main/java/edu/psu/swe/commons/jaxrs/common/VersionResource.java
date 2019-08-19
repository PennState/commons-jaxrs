package edu.psu.swe.commons.jaxrs.common;

/*
 * The Pennsylvania State University © 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;

import java.io.IOException;
import java.util.jar.Manifest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.swe.commons.jaxrs.utilities.ManifestUtil;

@Path("version")
public class VersionResource {

	private static final Logger LOG = LoggerFactory.getLogger(VersionResource.class);

  private Version version;

	public VersionResource() {
		LOG.warn(
				"Version endpoint was not initialized correctly, please add the VersionResource instance to the RestApplication singleton list.");
	}

	public VersionResource(Class<? extends Object> clazz) {
		if (clazz != null) {
	    Manifest manifest;
      try {
        manifest = ManifestUtil.locateManifest(clazz);
        version = ManifestUtil.getVersionInfo(manifest);
      } catch (IOException e) {
        LOG.error("Unable to lookup manifest information, will not be able to display version information");
      }
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Version getVersion() {
		if (version == null) {
			LOG.warn(
					"unable to determine version information, please add the VersionResource instance to the RestApplication singleton list.");
			throw new NotFoundException("Version endpoint not found");
		}

		return version;
	}

}