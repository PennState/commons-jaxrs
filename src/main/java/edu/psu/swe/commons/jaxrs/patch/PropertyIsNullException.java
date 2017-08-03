package edu.psu.swe.commons.jaxrs.patch;

class PropertyIsNullException extends PropertyTraversalException {

  public PropertyIsNullException(String message) {
    super(message);
  }

  public PropertyIsNullException(String message, Throwable cause) {
    super(message, cause);
  }
}
