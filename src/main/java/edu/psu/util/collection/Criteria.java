package edu.psu.util.collection;

public interface Criteria<T>
{
  boolean evaluateMatch(T t);
}
