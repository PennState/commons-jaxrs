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


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pair")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pair<E, F> implements Serializable
{
  private static final long serialVersionUID = 1247816543522165967L;
  
  public Pair(E e, F f)
  {
    first = e;
    second = f;
  }

  @XmlElement(name="lhs")
  public E first;
  
  @XmlElement(name="rhs")
  public F second;
}
