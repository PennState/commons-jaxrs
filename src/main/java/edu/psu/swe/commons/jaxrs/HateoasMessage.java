package edu.psu.swe.commons.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "hateoas-message")
public class HateoasMessage<T> {
  
  @XmlElement(name="status")
  @XmlJavaTypeAdapter(XmlStatusAdapter.class)
  private Status status_;
  
  @XmlElement(name = "value")
  private T value_;
  
  @XmlElementWrapper(name = "reference-list")
  private List<AtomLink> references_;
  
  public HateoasMessage() {
    // Required no-argument constructor
  }
  
  public HateoasMessage(Status status) {
    status_ = status;
  }
  
  public HateoasMessage(Status status, T value) {
    status_ = status;
    value_ = value;
  }

  public Status getStatus() {
    return status_;
  }

  public void setStatus(Status status) {
    status_ = status;
  }

  public T getValue() {
    return value_;
  }

  public void setValue(T value) {
    value_ = value;
  }

  public List<AtomLink> getReferences() {
    return references_;
  }

  public void setReferences(List<AtomLink> references) {
    references_ = references;
  }
  
  public void addReference(AtomLink reference) {
    if(references_ == null) {
      references_ = new ArrayList<AtomLink>();
    }
    references_.add(reference);
  }

}
