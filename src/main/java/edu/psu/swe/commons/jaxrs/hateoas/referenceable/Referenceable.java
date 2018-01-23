package edu.psu.swe.commons.jaxrs.hateoas.referenceable;

public interface Referenceable<T> {

  public ReferenceableAtomLink convertToReferenceableType();

  public void loadReferenceableType(ReferenceableAtomLink link);
}
