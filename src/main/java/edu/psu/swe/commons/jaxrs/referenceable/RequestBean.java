package edu.psu.swe.commons.jaxrs.referenceable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import lombok.Data;

@RequestScoped
@Data
public class RequestBean {

  @Context
  private UriInfo uriInfo;
  
  @Inject
  private HttpServletRequest request;
  
}
