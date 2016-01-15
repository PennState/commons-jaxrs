package edu.psu.rest;


public class RestClientException extends Exception
{
  private static final long serialVersionUID = 7360783673606191576L;

  private int statusCode_;
  private ErrorMessage errorMessage_;
  
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
}
