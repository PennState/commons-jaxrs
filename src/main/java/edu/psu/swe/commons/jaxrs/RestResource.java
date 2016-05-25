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
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.EntityTag;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResource<T> {
  
  private static final Logger LOG = LoggerFactory.getLogger(RestResource.class);
  
  @XmlElement
  private T resource;

  @XmlElement
  private RestResourceMetadata meta;

  @XmlElementWrapper(name = "links")
  @XmlElement(name = "link")
  @JsonProperty("links")
  private List<AtomLink> links;

  public RestResource(RestResourceMetadata meta) {
    this.meta = meta;
  }

  private RestResource() {

  }

  public RestResource(T resource, RestResourceMetadata meta) {
    this.resource = resource;
    this.meta = meta;
  }

  public T getResource() {
    return resource;
  }

  public void setResource(T resource) {
    this.resource = resource;
  }

  //Break the reference binding before returning
  public RestResourceMetadata getMeta() {
    final RestResourceMetadata copy = new RestResourceMetadata(meta);
    return copy;
  }

  public List<AtomLink> getLinks() {
    return links;
  }

  public void setLinks(List<AtomLink> links) {
    this.links = links;
  }
  
  public static <T> RestResource<T> wrap(T dto, String type, AtomLink atomLink) {
    return wrap(dto, type, Collections.singletonList(atomLink));
  }
  
  public static <T> RestResource<T> wrap(T dto, String type, List<AtomLink> atomLinkList) {
    EntityTag etag;
    try {
      etag = RestResourceMetadata.hash(dto);
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      LOG.error("Unable to create ETag", e);
      etag = null;
    }

    RestResource<T> rr = new RestResource<>(RestResourceMetadata.createMetaData(etag, type));
    rr.setResource(dto);
    rr.setLinks(atomLinkList);
    return rr;
  }

}
