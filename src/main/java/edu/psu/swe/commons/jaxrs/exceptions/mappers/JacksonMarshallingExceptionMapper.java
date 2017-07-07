package edu.psu.swe.commons.jaxrs.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import edu.psu.swe.commons.jaxrs.ErrorMessage;

public class JacksonMarshallingExceptionMapper implements ExceptionMapper<InvalidFormatException> {

  @Override
  public Response toResponse(InvalidFormatException exception) {
    ErrorMessage em = new ErrorMessage(Status.BAD_REQUEST);
    em.addErrorMessage(exception.getMessage());
    return em.toResponse();
  }

}
