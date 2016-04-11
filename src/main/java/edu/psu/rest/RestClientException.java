package edu.psu.rest;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;


public class RestClientException extends Exception
{
  private static final long serialVersionUID = 7360783673606191576L;

  private int statusCode_;
  private ErrorMessage errorMessage_;
  
  public RestClientException(Response response)
  {
    statusCode_ = response.getStatus();
    try {
      errorMessage_ = response.readEntity(ErrorMessage.class);
    } catch (ProcessingException e) {
      errorMessage_ = null;
    }
  }
  
  public RestClientException(int statusCode, ErrorMessage errorMessage)
  {
    statusCode_ = statusCode;
    errorMessage_ = errorMessage;
  }
  
  public int getStatusCode()
  {
    return statusCode_;
  }
  
  public ErrorMessage getErrorMessage()
  {
    return errorMessage_;
  }
  
  @Override
  public String getMessage(){
    String message = "Rest Client Exception: Status Code: " + statusCode_ + " ";
    if(errorMessage_ != null){
      message += "Error Messages: " + errorMessage_.getErrorMessageList();
    }
    
    return message;
    
  }
  
}
