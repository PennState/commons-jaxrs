package edu.psu.exception;

public class UnableToLoadException extends Exception
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public UnableToLoadException(String what)
  {
    super(what);
  }
  
  public UnableToLoadException(String what, Throwable why)
  {
    super(what, why);
  }
}
