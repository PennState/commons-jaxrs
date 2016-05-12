package edu.psu.swe.commons.jaxrs.common;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.psu.swe.commons.jaxrs.Iso8601DateTimeFormatAdapter;

@XmlRootElement(name="version-info")
@XmlAccessorType(XmlAccessType.NONE)
public class Version {

	@XmlElement(name="title")
	private String title;

	@XmlElement(name="vendor")
	private String vendor;
	
	@XmlElement(name="vendor-id")
	private String vendorId;
	
	@XmlElement(name="version")
	private String version;
	
	@XmlElement(name="scm-commit-id")
	private String scmCommitId;
	
	@XmlElement(name="scm-branch")
	private String scmBranch;
	
	@XmlElement(name="jenkins-build-id")
	private String jenkinsBuildId;
	
	@XmlElement(name="built-by")
	private String builtBy;
	
	@XmlElement(name="build-date")
	@XmlJavaTypeAdapter(Iso8601DateTimeFormatAdapter.class)
	private Date buildDate;
	
	@XmlElement(name="build-jdk")
	private String buildJdk;
	
	@XmlElement(name="logging-profile")
	private String loggingProfile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getScmCommitId() {
		return scmCommitId;
	}

	public void setScmCommitId(String scmCommitId) {
		this.scmCommitId = scmCommitId;
	}

	public String getScmBranch() {
		return scmBranch;
	}

	public void setScmBranch(String scmBranch) {
		this.scmBranch = scmBranch;
	}

	public String getJenkinsBuildId() {
		return jenkinsBuildId;
	}

	public void setJenkinsBuildId(String jenkinsBuildId) {
		this.jenkinsBuildId = jenkinsBuildId;
	}

	public String getBuiltBy() {
		return builtBy;
	}

	public void setBuiltBy(String builtBy) {
		this.builtBy = builtBy;
	}

	public Date getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public String getBuildJdk() {
		return buildJdk;
	}

	public void setBuildJdk(String buildJdk) {
		this.buildJdk = buildJdk;
	}

	public String getLoggingProfile() {
		return loggingProfile;
	}

	public void setLoggingProfile(String loggingProfile) {
		this.loggingProfile = loggingProfile;
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
