package edu.psu.swe.commons.jaxrs.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@AddNoCacheHeader
public class NoCacheResponseFilter implements ContainerResponseFilter {

  private static final Logger LOG = LoggerFactory.getLogger(NoCacheResponseFilter.class);

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

    responseContext.getHeaders()
                   .add("Cache-Control", "no-cache, no-store, must-revalidate");

    responseContext.getHeaders()
                   .add("Pragma", "no-cache");

    responseContext.getHeaders()
                   .add("Expires", "0");
    
    LOG.info("Response Headers Added for no-cache: Cache-Control, Pragma, Expires");
  }
}
