package edu.psu.swe.commons.jaxrs.patch;

import edu.psu.swe.commons.jaxrs.patch.exception.FailedOperationException;

class FailedToSetPropertyException extends FailedOperationException {

  public FailedToSetPropertyException(String message) {
    super(message);
  }

  public FailedToSetPropertyException(String message, Throwable cause) {
    super(message, cause);
  }
}
