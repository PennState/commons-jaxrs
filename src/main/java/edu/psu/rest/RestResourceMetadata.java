package edu.psu.rest;

import java.time.Instant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResourceMetadata {

  @XmlElement(nillable=true)
  private String resourceType;

  @XmlElement(nillable=true)
  private Instant created;

  @XmlElement(nillable=true)
  private Instant lastUpdated;

  @XmlElement(nillable=true)
  private String version;

  public RestResourceMetadata()
  {}
  
  public RestResourceMetadata(RestResourceMetadata other)
  {
    resourceType = other.getResourceType();
    created = other.getCreated();
    lastUpdated = other.getLastUpdated();
    version = other.getVersion();
  }
  
  public RestResourceMetadata(String resourceType, Instant created, Instant lastUpdated, String version)
  {
    this.resourceType = resourceType;
    this.created = created;
    this.lastUpdated = lastUpdated;
    this.version = version;
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
