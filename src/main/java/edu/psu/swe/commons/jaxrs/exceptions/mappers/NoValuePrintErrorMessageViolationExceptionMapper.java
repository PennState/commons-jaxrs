package edu.psu.swe.commons.jaxrs.exceptions.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import edu.psu.swe.commons.jaxrs.ErrorMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoValuePrintErrorMessageViolationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

  @Override
  public Response toResponse(ResteasyViolationException resteasyViolationException) {
    ErrorMessage em = new ErrorMessage();

    Exception e = resteasyViolationException.getException();
    List<ResteasyConstraintViolation> violations = resteasyViolationException.getViolations();
    violations.stream()
              .map(ResteasyConstraintViolation::toString)
              .forEach(log::info);

    if (e != null) {
      em.setStatus(Status.INTERNAL_SERVER_ERROR);
      em.addErrorMessage(e.toString());
    } else if (resteasyViolationException.getReturnValueViolations()
                                         .isEmpty()) {
      em.setStatus(Status.BAD_REQUEST);
      em.setErrorMessageList(extractMessages(violations));
    } else {  
      em.setStatus(Status.INTERNAL_SERVER_ERROR);
      em.setErrorMessageList(extractMessages(violations));
    }
    return em.toResponse();
  }

  private List<String> extractMessages(List<ResteasyConstraintViolation> violations) {
    return violations.stream()
                     .map(rcv -> "Constraint Violation: " + rcv.getConstraintType()
                                                               .name()
                         + "[" + rcv.getPath() + "]: " + rcv.getMessage())
                     .collect(Collectors.toList());
  }

}
