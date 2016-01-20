package edu.psu.rest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

import javax.ws.rs.core.EntityTag;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResourceMetadata {

  @XmlElement(nillable=true)
  private String resourceType;

  @XmlElement(nillable=true)
  @XmlJavaTypeAdapter(InstantFormatAdapter.class)
  private Instant created;

  @XmlElement(nillable=true)
  @XmlJavaTypeAdapter(InstantFormatAdapter.class)
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
  
  public static EntityTag hash(Object object) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    return hash(object.toString());
  }
  
  public static EntityTag hash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(input.getBytes("UTF-8"));
    byte[] hash = digest.digest();
    return new EntityTag(Base64.getEncoder().encodeToString(hash));
  }

}
