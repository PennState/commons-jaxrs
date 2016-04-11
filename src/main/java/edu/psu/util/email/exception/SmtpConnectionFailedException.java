package edu.psu.util.email.exception;

public class SmtpConnectionFailedException extends Exception
{
  public SmtpConnectionFailedException(String what)
  {
    super(what);
  }
}
