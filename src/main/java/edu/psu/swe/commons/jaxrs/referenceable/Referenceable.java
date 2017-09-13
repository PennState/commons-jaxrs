package edu.psu.swe.commons.jaxrs.referenceable;

public interface Referenceable {

  public ReferenceableAtomLink convertToReferenceableType();

  public void loadReferenceableType(ReferenceableAtomLink link);
  
}
