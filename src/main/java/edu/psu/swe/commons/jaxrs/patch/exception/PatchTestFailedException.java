package edu.psu.swe.commons.jaxrs.patch.exception;

public class PatchTestFailedException extends PatchOperationFailedException {

  public PatchTestFailedException(int operationNumber, String message) {
    super(operationNumber, message);
  }

  public PatchTestFailedException(int operationNumber, String message, Throwable cause) {
    super(operationNumber, message, cause);
  }
}
