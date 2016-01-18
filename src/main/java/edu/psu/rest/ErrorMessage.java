package edu.psu.rest;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorMessage
{
  @XmlElement(name="status")
  @XmlJavaTypeAdapter(XmlStatusAdapter.class)
  private Status status_;
  
  @XmlElementWrapper(name="error-message-list")
  @XmlElement(name="error-message")
  List<String> errorMessages_ = new ArrayList<>();
  
  @XmlElementWrapper(name="reference-list", nillable = true)
  @XmlElement(name = "link", nillable = true)
  List<String> externalLinks_ = null;
  
  public ErrorMessage()
  {}
  
  public ErrorMessage(Status status)
  {
    status_ = status;
  }
   
  public void setStatus(Status status)
  {
    status_ = status;
  }
  
  public Status getStatus()
  {
    return status_;
  }
  
  public void setErrorMessageList(List<String> messageList)
  {
    if (messageList == null)
    {
      errorMessages_.clear();
    }
    else
    {
      errorMessages_ = messageList;
    }
  }
  
  public void addErrorMessage(String message)
  {
    errorMessages_.add(message);
  }
  
  public List<String> getErrorMessageList()
  {
    return Collections.unmodifiableList(errorMessages_);
  }
  
  public void setExternalLinkList(List<String> linkList)
  {
    externalLinks_ = linkList;
  }
  
  public void addExtenalLink(URL link)
  {
    if (externalLinks_ == null)
    {
      externalLinks_  = new ArrayList<>();
    }
    
    externalLinks_.add(link.toString());
  }
  
  public List<String> getExternalLinkList()
  {
    return externalLinks_;
  }
  
  public Response toResponse()
  {
    return Response.status(status_).entity(this).build();
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Status = ");
    sb.append(status_.getStatusCode());
    sb.append("\n");
    
    if (errorMessages_ != null)
    {
      sb.append("Error Messages");
      sb.append("\n");
      for (String s : errorMessages_)
      {
        sb.append(s);
        sb.append("\n");
      }
    }
    
    if (externalLinks_ != null)
    {
      sb.append("External Links");
      for (String s : externalLinks_)
      {
        sb.append(s);
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}
