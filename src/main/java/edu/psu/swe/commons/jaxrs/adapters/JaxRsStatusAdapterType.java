package edu.psu.swe.commons.jaxrs.adapters;

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


import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error-message")
@XmlAccessorType(XmlAccessType.NONE)
public class JaxRsStatusAdapterType
{
  @XmlElement(name="code")
  private int code_;
  
  @XmlElement(name="message")
  private String message_;
  
  public JaxRsStatusAdapterType() {
    // Required no-argument constructor for JAXB marshalling/unmarshalling
  }
  
  public JaxRsStatusAdapterType(Status status)
  {
    code_ = status.getStatusCode();
    message_  = status.name();
  }
  
  public void setStatus(Status status)
  {
    code_ = status.getStatusCode();
    message_ = status.name();
  }
  
  public Status getStatus()
  {
    return Status.fromStatusCode(code_);
  }
  
  public int getCode()
  {
    return code_;
  }
  
  public String getMessage()
  {
    return message_;
  }
}
