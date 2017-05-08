/**
 * 
 */
package edu.psu.swe.commons.jaxrs.converters.param;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.Instant;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 * @author swm16
 *
 */
@Provider
public class InstantConverterProvider implements ParamConverterProvider {

  /* (non-Javadoc)
   * @see javax.ws.rs.ext.ParamConverterProvider#getConverter(java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[])
   */
  @Override
  public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

    if(rawType.getName().equals(Instant.class.getName()) ) {
      return new ParamConverter<T>() {

        @Override
        public T fromString(String value) {
          return rawType.cast(Instant.parse(value));
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
