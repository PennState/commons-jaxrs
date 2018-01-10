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
    boolean logError = true;
    boolean includeExceptionMessage = false;
    
    ErrorMessage em = new ErrorMessage(Status.BAD_REQUEST);
    
    /********************************************** CLIENT EXCEPTIONS (400s) *******************************************************/
    if (e.getClass().equals(BadRequestException.class)) {
      em.setStatus(Status.BAD_REQUEST);
      logError = false;
      includeExceptionMessage = true;
      
    } else if (e.getClass().equals(NotFoundException.class)) {
      logError = false;
      em.setStatus(Status.NOT_FOUND);
      includeExceptionMessage = true;
      
    } else if (e.getClass().equals(ForbiddenException.class)) {
      em.setStatus(Status.FORBIDDEN);
      includeExceptionMessage = true;
      
    } else if (e.getClass().equals(NotAuthorizedException.class)) {
      logError = false;
      em.setStatus(Status.UNAUTHORIZED);
      includeExceptionMessage = true;
      
    } else if (e.getClass().equals(ConflictException.class)) {
      logError = false;
      em.setStatus(Status.CONFLICT);
      includeExceptionMessage = true;
    }
    
    /********************************************** SERVER EXCEPTIONS (500s) *******************************************************/  
    else {
      //catch all and return as an internal error
      em.setStatus(Status.INTERNAL_SERVER_ERROR);
    }
    
    //Build Error Message Body
    if (includeExceptionMessage) {
      em.addErrorMessage(e.getMessage());
    }
    
    //Don't Log everything
    if(logError){
      log.error("Error in ead service: " + e.getMessage(), e);       
    }
    
    response = em.toResponse();

    return response;
    
  }
}


