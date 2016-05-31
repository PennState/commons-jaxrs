package edu.psu.swe.commons.jaxrs.filter;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public class OutgoingLogFilter implements ContainerResponseFilter {

  @Context
  UriInfo uriInfo;
  
  @Context
  SecurityContext securityContext;
  
  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
     String absolutePath = uriInfo.getAbsolutePath().toString();
     int status = responseContext.getStatus();
     
     Principal principal = securityContext.getUserPrincipal();
     String principalName = principal.getName();
  }

}
