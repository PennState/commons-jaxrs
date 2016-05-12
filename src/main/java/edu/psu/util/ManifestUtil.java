package edu.psu.util;

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


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.swe.commons.jaxrs.common.Version;

public class ManifestUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ManifestUtil.class);

	public static Manifest locateManifest(Class<? extends Object> clazz)
			throws IOException {
		String className = clazz.getSimpleName() + ".class";
		String fullClassName = clazz.getName().replace('.', '/') + ".class";
		String classPath = clazz.getResource(className).toString();

		classPath = classPath.replace("/WEB-INF/classes", "");

		String manifestPath = classPath.replace(fullClassName,
				"META-INF/MANIFEST.MF");

		Manifest manifest = new Manifest(new URL(manifestPath).openStream());
		return manifest;
	}

	public static Version getVersionInfo(Manifest manifest) {
		if (manifest == null) {
			LOG.warn("unable to find manifest information from jar.");
			return null;
		}

		Attributes attributes = manifest.getMainAttributes();

		Version version = new Version();
		version.setVendor(attributes.getValue("Implementation-Vendor"));
		version.setVendorId(attributes.getValue("Implementation-Vendor-Id"));
		version.setTitle(attributes.getValue("Implementation-Title"));
		version.setVersion(attributes.getValue("Implementation-Version"));
		version.setScmBranch(attributes.getValue("SCM-Branch"));
		version.setScmCommitId(attributes.getValue("SCM-Revision"));
		version.setJenkinsBuildId(attributes.getValue("Build-Number"));
		version.setBuildJdk(attributes.getValue("Build-Jdk"));
		String buildDate = attributes.getValue("Build-Date");
		if (buildDate != null && buildDate.length() > 0) {
			version.setBuildDate(buildDate != null ? new Date(Long
					.valueOf(buildDate)) : null);
		}else
		{
			LOG.warn("unable to read build date from manifest");
			version.setBuildDate(new Date());
		}
		version.setBuiltBy(attributes.getValue("Built-By"));
		version.setLoggingProfile(attributes.getValue("Logging-Profile"));
		return version;
	}

}
