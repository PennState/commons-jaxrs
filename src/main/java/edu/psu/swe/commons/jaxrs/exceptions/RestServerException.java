package edu.psu.swe.commons.jaxrs.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

import edu.psu.swe.commons.jaxrs.ErrorMessage;

@ApplicationException
public class RestServerException extends RuntimeException {
  private static final long serialVersionUID = 7360783673606191576L;
  
  RestClientException rce;

  public RestServerException(Response response) {
    rce = new RestClientException(response);
  }

  public RestServerException(int statusCode, ErrorMessage errorMessage) {
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
