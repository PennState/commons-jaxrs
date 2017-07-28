package edu.psu.swe.commons.jaxrs.patch.exception;

import lombok.Getter;

@Getter
public class PatchOperationFailedException extends Exception {

  final int operationNumber;

  public PatchOperationFailedException(int operationNumber, String message) {
    super(message);

    this.operationNumber = operationNumber;
  }

  public PatchOperationFailedException(int operationNumber, String message, Throwable cause) {
    super(message, cause);

    this.operationNumber = operationNumber;
  }

  public PatchOperationFailedException(int operationNumber, Throwable cause) {
    this(operationNumber, cause.getMessage(), cause);
  }
}
