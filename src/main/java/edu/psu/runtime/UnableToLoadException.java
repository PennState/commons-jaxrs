package edu.psu.runtime;

public class UnableToLoadException extends RuntimeException
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
