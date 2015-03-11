package edu.psu.injection;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides methods that produce an SLF4J Logger instance.
 * 
 * @author Steve Moyer {@literal (<smoyer@psu.edu>)}
 */
public class LoggerInjector {
  
  /**
   * Creates an SLF4J Logger instance using the class containing the injection
   * point as the FQCN determining where the Logger is placed in the category
   * hierarchy.
   * 
   * @param injectionPoint information regarding where and how the @Inject
   *        annotation was used.
   *        
   * @return an SLF4J Logger.
   */
  @Produces
  public Logger getLogger(InjectionPoint injectionPoint) {
    Class<?> clazz = injectionPoint.getMember().getDeclaringClass();
    return LoggerFactory.getLogger(clazz);
  }

}
