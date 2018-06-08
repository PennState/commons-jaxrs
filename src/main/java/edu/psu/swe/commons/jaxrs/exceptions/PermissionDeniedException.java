package edu.psu.swe.commons.jaxrs.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response.Status;

@ApplicationException
public class PermissionDeniedException extends RuntimeException {
  private static final long serialVersionUID = 7360783673606191576L;
  
  Status statusCode;
  
  public PermissionDeniedException(int code) {
    switch (code) {
    case 401:
      statusCode = Status.UNAUTHORIZED;
      break;
    default:
      statusCode = Status.FORBIDDEN;
      break;
    }
  }

  public PermissionDeniedException(Status statusCode) {
    this.statusCode = statusCode;
  }

  public Status getStatusCode() {
    return statusCode;
  }
}
