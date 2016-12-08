package edu.psu.swe.commons.jaxrs.exceptions;

import javax.ws.rs.core.Response;

import edu.psu.swe.commons.jaxrs.ErrorMessage;

public class ServiceForbiddenException extends Exception {
  private static final long serialVersionUID = 7360783673606191576L;
  
  RestClientException rce;

  public ServiceForbiddenException(Response response) {
    rce = new RestClientException(response);
  }

  public ServiceForbiddenException(int statusCode, ErrorMessage errorMessage) {
    rce = new RestClientException(statusCode, errorMessage);
  }

  public int getStatusCode() {
    return rce.getStatusCode();
  }

  public ErrorMessage getErrorMessage() {
    return rce.getErrorMessage();
  }

  @Override
  public String getMessage() {
    return rce.getMessage();
  }
}
