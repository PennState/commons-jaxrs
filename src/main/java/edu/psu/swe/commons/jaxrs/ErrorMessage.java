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


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.psu.swe.commons.jaxrs.adapters.XmlStatusAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorMessage
{
  @XmlElement(name="status")
  @XmlJavaTypeAdapter(XmlStatusAdapter.class)
  private Status status;
  
  @XmlElementWrapper(name="errorMessageList")
  @XmlElement(name="errorMessage")
  List<String> errorMessages = new ArrayList<>();
  
  @XmlElementWrapper(name="referenceList", nillable = true)
  @XmlElement(name = "link", nillable = true)
  List<String> externalLinks = null;
  
  public ErrorMessage()
  {}
  
  public ErrorMessage(Status status)
  {
    this.status = status;
  }
   
  public void setStatus(Status status)
  {
    this.status = status;
  }
  
  public Status getStatus()
  {
    return status;
  }
  
  public void setErrorMessageList(List<String> messageList)
  {
    if (messageList == null)
    {
      errorMessages.clear();
    }
    else
    {
      errorMessages = messageList;
    }
  }
  
  public void addErrorMessage(String message)
  {
    errorMessages.add(message);
  }
  
  public List<String> getErrorMessageList()
  {
    return Collections.unmodifiableList(errorMessages);
  }
  
  public void setExternalLinkList(List<String> linkList)
  {
    externalLinks = linkList;
  }
  
  public void addExtenalLink(URL link)
  {
    if (externalLinks == null)
    {
      externalLinks  = new ArrayList<>();
    }
    
    externalLinks.add(link.toString());
  }
  
  public List<String> getExternalLinkList()
  {
    return externalLinks;
  }
  
  public Response toResponse()
  {
    return Response.status(status).entity(this).build();
  }
  
  public Response toResponse(String mediaType)
  {
    return Response.status(status).entity(this).type(mediaType).build();
  }  
  
  public Response toResponse(MediaType mediaType)
  {
    return Response.status(status).entity(this).type(mediaType).build();
  }  
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Status = ");
    sb.append(status.getStatusCode());
    sb.append("\n");
    
    if (errorMessages != null)
    {
      sb.append("Error Messages");
      sb.append("\n");
      for (String s : errorMessages)
      {
        sb.append(s);
        sb.append("\n");
      }
    }
    
    if (externalLinks != null)
    {
      sb.append("External Links");
      for (String s : externalLinks)
      {
        sb.append(s);
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}
