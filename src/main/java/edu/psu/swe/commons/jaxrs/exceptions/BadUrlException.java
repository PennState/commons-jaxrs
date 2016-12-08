package edu.psu.swe.commons.jaxrs.exceptions;

public class BadUrlException extends RuntimeException {
  private static final long serialVersionUID = 7360783673606191576L;

  public BadUrlException(String what) {
    super(what);
  }
}
