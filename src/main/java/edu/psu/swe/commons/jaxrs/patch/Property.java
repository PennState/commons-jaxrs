package edu.psu.swe.commons.jaxrs.patch;

import java.lang.reflect.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Property {
  final Object object;
  final Type type;
}
