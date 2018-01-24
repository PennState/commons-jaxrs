package edu.psu.swe.commons.jaxrs.exceptions.mappers;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import edu.psu.swe.commons.jaxrs.ErrorMessage;
import edu.psu.swe.commons.jaxrs.exceptions.ConflictException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

  public Response toResponse(WebApplicationException e) {
    Response response;
    boolean includeExceptionMessage = false;
    
    ErrorMessage em = new ErrorMessage(Status.BAD_REQUEST);
    
    /********************************************** CLIENT EXCEPTIONS (400s) *******************************************************/
    if (e.getClass().equals(BadRequestException.class)) {
      em.setStatus(Status.BAD_REQUEST);
      includeExceptionMessage = true;
      log.info(e.getMessage());
      
    } else if (e.getClass().equals(NotFoundException.class)) {
      em.setStatus(Status.NOT_FOUND);
      includeExceptionMessage = true;
      log.info(e.getMessage());
      
    } else if (e.getClass().equals(ForbiddenException.class)) {
      em.setStatus(Status.FORBIDDEN);
      includeExceptionMessage = true;
      log.info(e.getMessage());
      
    } else if (e.getClass().equals(NotAuthorizedException.class)) {
      em.setStatus(Status.UNAUTHORIZED);
      includeExceptionMessage = true;
      log.info(e.getMessage());
      
    } else if (e.getClass().equals(ConflictException.class)) {
      em.setStatus(Status.CONFLICT);
      includeExceptionMessage = true;
      log.info(e.getMessage());
    }
    
    /********************************************** SERVER EXCEPTIONS (500s) *******************************************************/  
    else {
      //catch all and return as an internal error
      em.setStatus(Status.INTERNAL_SERVER_ERROR);
      log.error(e.getMessage(), e);
    }
    
    //Build Error Message Body
    if (includeExceptionMessage) {
      em.addErrorMessage(e.getMessage());
    }
    
    response = em.toResponse();

    return response;
    
  }
}


