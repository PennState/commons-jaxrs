package edu.psu.swe.commons.jaxrs.objectmapper;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

  @Inject
  @JaxrsObjectMapper
  private ObjectMapper objectMapper;

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return objectMapper;
  }

}
