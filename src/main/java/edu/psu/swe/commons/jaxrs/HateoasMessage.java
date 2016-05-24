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


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.psu.swe.commons.jaxrs.adapters.XmlStatusAdapter;

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
