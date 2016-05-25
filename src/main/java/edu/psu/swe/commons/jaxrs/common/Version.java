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

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.psu.swe.commons.jaxrs.adapters.Iso8601DateTimeFormatAdapter;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "version-info")
@XmlAccessorType(XmlAccessType.NONE)
@Getter
@Setter
public class Version {

  @XmlElement(name = "title")
  private String title;

  @XmlElement(name = "vendor")
  private String vendor;

  @XmlElement(name = "vendor-id")
  private String vendorId;

  @XmlElement(name = "version")
  private String version;

  @XmlElement(name = "scm-commit-id")
  private String scmCommitId;

  @XmlElement(name = "scm-branch")
  private String scmBranch;

  @XmlElement(name = "jenkins-build-id")
  private String jenkinsBuildId;

  @XmlElement(name = "built-by")
  private String builtBy;

  @XmlElement(name = "build-date")
  @XmlJavaTypeAdapter(Iso8601DateTimeFormatAdapter.class)
  private Date buildDate;

  @XmlElement(name = "build-jdk")
  private String buildJdk;

  @XmlElement(name = "logging-profile")
  private String loggingProfile;

  public Date getBuildDate() {
    return new Date(buildDate.getTime());
  }

  public void setBuildDate(Date buildDate) {
    this.buildDate = new Date(buildDate.getTime());
  }

  @Override
  public String toString() {
    return "Version [title=" + title + ", vendor=" + vendor + ", vendorId="
        + vendorId + ", version=" + version + ", scmCommitId="
        + scmCommitId + ", scmBranch=" + scmBranch
        + ", jenkinsBuildId=" + jenkinsBuildId + ", builtBy=" + builtBy
        + ", buildJdk=" + buildJdk + ", loggingProfile="
        + loggingProfile + "]";
  }

}
