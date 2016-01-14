package edu.psu.rest;

import java.time.Instant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResourceMetadata {

  @XmlElement
  private String resourceType;

  @XmlElement
  private Instant created;

  @XmlElement
  private Instant lastUpdated;

  @XmlElement
  private String version;

  public RestResourceMetadata(RestResourceMetadata other)
  {
    resourceType = other.getResourceType();
    created = other.getCreated();
    lastUpdated = other.getLastUpdated();
    version = other.getVersion();
  }
  
  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  public Instant getCreated() {
    return created;
  }

  public void setCreated(Instant created) {
    this.created = created;
  }

  public Instant getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Instant lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}
