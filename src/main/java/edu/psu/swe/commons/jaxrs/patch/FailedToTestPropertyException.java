package edu.psu.swe.commons.jaxrs.patch;

import edu.psu.swe.commons.jaxrs.patch.exception.FailedOperationException;

class FailedToTestPropertyException extends FailedOperationException {

  public FailedToTestPropertyException(String message) {
    super(message);
  }

  public FailedToTestPropertyException(String message, Throwable cause) {
    super(message, cause);
  }
}
