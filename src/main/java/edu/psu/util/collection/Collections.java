package edu.psu.util.collection;

/*
 * The Pennsylvania State University © 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


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
