package edu.psu.persistence;

public class UnableToEncryptColumnException extends RuntimeException
{
  private static final long serialVersionUID = -4987987906167952182L;

  public UnableToEncryptColumnException(Throwable cause)
  {
    super(cause);
  }
}
