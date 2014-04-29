package edu.psu.rest;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorMessage
{
  @XmlElement(name="status")
  private int status_;
  
  @XmlElement(name="message")
  private String message_;
  
  @XmlElementWrapper(name="error-list")
  @XmlElement(name="error-message")
  List<String> errorMessages_ = new ArrayList<>();
  
  @XmlElementWrapper(name="link-list")
  @XmlElement(name = "link", nillable = true)
  List<String> externalLinks_ = null;
  
  public ErrorMessage(int status, String message)
  {
    status_ = status;
    message_ = message;
  }
   
  public void setStatus(Status status)
  {
    status_ = status.ordinal();
  }
  
  public Status getStatus()
  {
    return Status.valueOf(Integer.toString(status_));
  }
  
  public void setMessage(String message)
  {
    message_ = message;
  }
  
  public String getMessage()
  {
    return message_;
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
}
