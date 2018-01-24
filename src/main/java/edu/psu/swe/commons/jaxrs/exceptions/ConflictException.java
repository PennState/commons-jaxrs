package edu.psu.swe.commons.jaxrs.exceptions;

import java.net.URI;

import javax.ejb.ApplicationException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Thrown to return a 409 Conflict response with optional Location header and
 * entity.
 */
@ApplicationException
public class ConflictException extends ClientErrorException {

  private static final long serialVersionUID = -5403009371368084717L;

  public ConflictException() {
    this(null, null, null);
  }

  public ConflictException(String message, URI location) {
    this(message, location, null);
  }

  public ConflictException(String message, URI location, Object entity) {
    super(message, Response.status(Status.CONFLICT)
                  .location(location)
                  .entity(entity)
                  .build());
  }
}