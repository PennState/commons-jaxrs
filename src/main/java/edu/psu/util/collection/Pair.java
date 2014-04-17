package edu.psu.util.collection;

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
