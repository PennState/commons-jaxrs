package edu.psu.util.collection;

import java.io.Serializable;

public class Pair<E, F> implements Serializable
{
  private static final long serialVersionUID = 1247816543522165967L;
  
  public Pair(E e, F f)
  {
    first = e;
    second = f;
  }

  public E first;
  public F second;
}
