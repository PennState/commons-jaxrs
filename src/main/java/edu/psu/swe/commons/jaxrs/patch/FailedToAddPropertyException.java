package edu.psu.swe.commons.jaxrs.patch;

import edu.psu.swe.commons.jaxrs.patch.exception.FailedOperationException;

class FailedToAddPropertyException extends FailedOperationException {

  public FailedToAddPropertyException(String message) {
    super(message);
  }

  public FailedToAddPropertyException(String message, Throwable cause) {
    super(message, cause);
  }
}
