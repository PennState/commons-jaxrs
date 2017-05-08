package edu.psu.swe.commons.jaxrs.referenceable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.swe.commons.jaxrs.AtomLink;
import lombok.Data;

@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class ReferenceableAtomLink extends AtomLink {
  
  @XmlElement(name = "id")
  private String id;
  
}
