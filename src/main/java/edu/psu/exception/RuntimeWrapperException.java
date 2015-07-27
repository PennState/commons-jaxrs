package edu.psu.exception;

public class RuntimeWrapperException extends RuntimeException
{
  private static final long serialVersionUID = 8649641682576598034L;
  
  Throwable cause;

  RuntimeWrapperException(Throwable cause)
  {
    this.cause = cause;
  }

  static void throwRuntimeWrapperException(Exception e)
  {
    throw new RuntimeWrapperException(e);
  }
}
