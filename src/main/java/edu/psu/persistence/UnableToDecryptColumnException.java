package edu.psu.persistence;

public class UnableToDecryptColumnException extends RuntimeException
{
  private static final long serialVersionUID = -4987987906167952182L;

  public UnableToDecryptColumnException(Throwable cause)
  {
    super(cause);
  }
}
