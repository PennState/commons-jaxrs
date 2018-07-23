package edu.psu.swe.commons.jaxrs.converters.param;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class LocalDateConverterProvider implements ParamConverterProvider {

  /* (non-Javadoc)
   * @see javax.ws.rs.ext.ParamConverterProvider#getConverter(java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[])
   */
  @Override
  public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

    if(rawType.getName().equals(LocalDate.class.getName()) ) {
      return new ParamConverter<T>() {

        @Override
        public T fromString(String value) {
          return rawType.cast(LocalDate.parse(value));
        }

        @Override
        public String toString(T value) {
          return value.toString();
        }
        
      };
    }
    
    return null;
  }
}
