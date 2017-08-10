package edu.psu.swe.commons.jaxrs.patch;

public class JsonPointerParseException extends Exception {

  public JsonPointerParseException(String message) {
    super(message);
  }

  public JsonPointerParseException(Throwable cause) {
    super(cause);
  }

  public JsonPointerParseException(String message, Throwable cause) {
    super(message, cause);
  }
}
