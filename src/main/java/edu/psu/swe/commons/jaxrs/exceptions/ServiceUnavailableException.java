package edu.psu.swe.commons.jaxrs.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response.Status;

@ApplicationException
public class ServiceUnavailableException extends RuntimeException {
  private static final long serialVersionUID = 7360783673606191576L;
  
  Status statusCode;
  
  public ServiceUnavailableException(int code) {
    switch (code) {
    case 500:
      statusCode = Status.INTERNAL_SERVER_ERROR;
      break;
    case 502:
      statusCode = Status.BAD_GATEWAY;
      break;
    case 503:
      statusCode = Status.SERVICE_UNAVAILABLE;
      break;
    default:
      statusCode = Status.SERVICE_UNAVAILABLE;
      break;
    }
  }

  public ServiceUnavailableException(Status statusCode) {
    this.statusCode = statusCode;
  }

  public Status getStatusCode() {
    return statusCode;
  }
}
