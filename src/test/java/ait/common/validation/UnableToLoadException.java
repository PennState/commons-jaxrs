package ait.common.validation;

public class UnableToLoadException extends Exception
{
  public UnableToLoadException(String what, Throwable why)
  {
    super(what, why);
  }
 
  public UnableToLoadException(String what)
  {
    super(what);
  }
}  

