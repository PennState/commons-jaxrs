package edu.psu.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestResource<T> {

  @XmlElement
  private T resource;

  @XmlElement
  private RestResourceMetadata meta;

  @XmlElementWrapper(name = "links")
  @XmlElement(name = "link")
  @JsonProperty("links")
  private List<String> links;

  public RestResource(RestResourceMetadata meta) {
    this.meta = meta;
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

  public RestResourceMetadata getMeta() {
    return meta;
  }

  public List<String> getLinks() {
    return links;
  }

  public void setLinks(List<String> links) {
    this.links = links;
  }

}
