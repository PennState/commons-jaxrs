package edu.psu.swe.commons.jaxrs;

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


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.swe.commons.jaxrs.enumerations.RelationshipType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResourceMetadata {
  
  private static final Logger LOG = LoggerFactory.getLogger(RestResourceMetadata.class);
  
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
  
  public static RestResourceMetadata createMetaData(EntityTag etag, String type)
  {
    RestResourceMetadata meta = new RestResourceMetadata();
    
    meta.setVersion(etag.toString());
    meta.setCreated(Instant.now());
    meta.setResourceType(type);
    
    return meta;
  }
  
  public static AtomLink createSelfLink(UriInfo uriInfo)
  {
    String url = null;
    
    try
    {
      url = UriUtilities.urlAsString(uriInfo, true);
    }
    catch(MalformedURLException mue)
    {
      LOG.info("Failed to convert url " + mue.getLocalizedMessage());
    }
    
    AtomLink self = new AtomLink();
    self.setRelation(RelationshipType.SELF.toString());
    self.setHyperlink(url);
    self.setMimeType(Constants.PSU_CONTENT_TYPE_V1);
    return self;
  }

}
