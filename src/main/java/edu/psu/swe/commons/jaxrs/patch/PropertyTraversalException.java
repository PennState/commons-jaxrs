package edu.psu.swe.commons.jaxrs.patch;

public class PropertyTraversalException extends Exception {

  public PropertyTraversalException(String message) {
    super(message);
  }

  public PropertyTraversalException(Throwable cause) {
    super(cause);
  }

  public PropertyTraversalException(String message, Throwable cause) {
    super(message, cause);
  }

}
