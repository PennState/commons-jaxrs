package edu.psu.swe.commons.jaxrs.patch;

public class PropertyDoesNotExistException extends PropertyTraversalException {

  PropertyDoesNotExistException(String message) {
    super(message);
  }

  PropertyDoesNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
