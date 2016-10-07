package edu.psu.swe.commons.jaxrs.objectmapper;

import javax.enterprise.inject.Produces;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class ObjectMapperProducer {

  @Produces
  @JaxrsObjectMapper
  public ObjectMapper createObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
    objectMapper.registerModule(jaxbAnnotationModule);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
    AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
    AnnotationIntrospector pair = new AnnotationIntrospectorPair(jacksonIntrospector, jaxbIntrospector);
    objectMapper.setAnnotationIntrospector(pair);
    
    return objectMapper;
  }
}
