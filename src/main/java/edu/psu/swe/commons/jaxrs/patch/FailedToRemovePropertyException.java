package edu.psu.swe.commons.jaxrs.patch;

import edu.psu.swe.commons.jaxrs.patch.exception.FailedOperationException;

class FailedToRemovePropertyException extends FailedOperationException {

  public FailedToRemovePropertyException(String message) {
    super(message);
  }

  public FailedToRemovePropertyException(String message, Throwable cause) {
    super(message, cause);
  }
}
