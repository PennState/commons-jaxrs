package edu.psu.swe.commons.jaxrs.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.swe.commons.jaxrs.RestResourceMetadata;

@Provider
@AddEtagHeader
public class EtagResponseFilter implements ContainerResponseFilter {

  private static final Logger LOG = LoggerFactory.getLogger(EtagResponseFilter.class);
  
  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    Object entity = responseContext.getEntity();
    EntityTag etag;
    
    try {
      etag = RestResourceMetadata.hash(entity);
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      throw new IllegalArgumentException("unable to create ETag", e);
    }
    
    responseContext.getHeaders().add("ETag", etag.getValue());
  }

}
