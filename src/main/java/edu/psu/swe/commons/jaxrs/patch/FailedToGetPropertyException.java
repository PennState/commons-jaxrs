package edu.psu.swe.commons.jaxrs.patch;

import edu.psu.swe.commons.jaxrs.patch.exception.FailedOperationException;

class FailedToGetPropertyException extends FailedOperationException {

  public FailedToGetPropertyException(String message) {
    super(message);
  }

  public FailedToGetPropertyException(String message, Throwable cause) {
    super(message, cause);
  }
}
