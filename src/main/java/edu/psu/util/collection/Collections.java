package edu.psu.util.collection;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public final class Collections
{
  private Collections()
  {}

  public static <T> Collection<T> subset(Collection<T> input, Criteria<T> comparator)
  {
    List<T> newList = new ArrayList<T>();

    for(T t : input)
    {
      if (comparator.evaluateMatch(t))
      {
        newList.add(t);
      }
    } 

    return newList;
  }

  public static <K,V> Map<K,V> subset(Map<K,V> input, Criteria<Map.Entry<K,V>> comparator)
  {
    Map<K,V> newMap = new HashMap<K,V>();

    for(Map.Entry<K,V> me : input.entrySet())
    {
      if (comparator.evaluateMatch(me))
      {
        newMap.put(me.getKey(), me.getValue());
      }
    }

    return newMap;
  }
}
