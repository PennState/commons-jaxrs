package edu.psu.swe.commons.jaxrs.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
@AddNoCacheHeader
public class NoCacheResponseFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

    CacheControl cc = new CacheControl();
    cc.setNoCache(true);
    cc.setNoStore(true);
    cc.setMustRevalidate(true);
    
    responseContext.getHeaders()
                   .add("Cache-Control", cc);

    responseContext.getHeaders()
                   .add("Pragma", "no-cache");

    responseContext.getHeaders()
                   .add("Expires", "0");
    
    log.debug("***************Response Headers Added for no-cache: Cache-Control, Pragma, Expires**********");
  }
}
