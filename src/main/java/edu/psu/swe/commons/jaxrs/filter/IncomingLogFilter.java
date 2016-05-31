package edu.psu.swe.commons.jaxrs.filter;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PreMatching
public class IncomingLogFilter implements ContainerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(IncomingLogFilter.class);

  @Context
  UriInfo uriInfo;
  
  @Context 
  SecurityContext securityContext;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    
    LocalDateTime requestDate = LocalDateTime.ofInstant(requestContext.getDate().toInstant(), ZoneId.systemDefault());
    String method = requestContext.getMethod();
    MediaType mediaType = requestContext.getMediaType();

    MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();
    for (Map.Entry<String, List<String>> me : pathParams.entrySet()) {
      LOGGER.info(me.getKey());
      for (String s : me.getValue()) {
        LOGGER.info("    " + s);
      }
    }

    MultivaluedMap<String, String> headers = requestContext.getHeaders();
    for (Map.Entry<String, List<String>> me : headers.entrySet()) {
      LOGGER.info(me.getKey());
      for (String s : me.getValue()) {
        LOGGER.info("    " + s);
      }
    }
    
    MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
    for (Map.Entry<String, List<String>> me : queryParameters.entrySet()) {
      LOGGER.info(me.getKey());
      for (String s : me.getValue()) {
        LOGGER.info("    " + s);
      }
    }

    String absolutePath = uriInfo.getAbsolutePath().toString();
    
    Principal principal = securityContext.getUserPrincipal();
    String principalName = principal.getName();    
  }

}
