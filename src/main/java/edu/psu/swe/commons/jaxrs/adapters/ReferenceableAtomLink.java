package edu.psu.swe.commons.jaxrs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//
@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class ReferenceableAtomLink extends AtomLink {
  
  @XmlElement(name = "id")
  private String id;
  
}
