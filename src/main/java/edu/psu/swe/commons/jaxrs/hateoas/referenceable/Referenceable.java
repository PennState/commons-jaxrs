package edu.psu.swe.commons.jaxrs.hateoas.referenceable;

import edu.psu.swe.commons.jaxrs.hateoas.referenceable.ReferenceableAtomLink;

public interface Referenceable<T> {

  public ReferenceableAtomLink convertToReferenceableType();

  public void loadReferenceableType(ReferenceableAtomLink link);
}
