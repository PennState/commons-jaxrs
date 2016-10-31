package edu.psu.swe.commons.jaxrs.adapters;

import edu.psu.swe.commons.jaxrs.ReferenceableAtomLink;

//
public interface Referenceable<T> {

  public String getReferenceableId();

  public void loadReferenceableType(ReferenceableAtomLink link);
}
