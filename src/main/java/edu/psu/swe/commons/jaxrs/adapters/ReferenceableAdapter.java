package edu.psu.swe.commons.jaxrs.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.psu.swe.commons.jaxrs.ReferenceableAtomLink;

//
public class ReferenceableAdapter<T> extends XmlAdapter<ReferenceableAtomLink, Referenceable<T>> {

  private Class<T> myClass;

  @Override
  public Referenceable unmarshal(ReferenceableAtomLink v) throws Exception {
    Referenceable<T> ref = (Referenceable<T>) myClass.newInstance();
    ref.loadReferenceableType(v);
    return ref;
  }

  @Override
  public ReferenceableAtomLink marshal(Referenceable v) throws Exception {
    ReferenceableAtomLink link = new ReferenceableAtomLink();
    link.setId(v.getReferenceableId());
    return link;
  }

}
