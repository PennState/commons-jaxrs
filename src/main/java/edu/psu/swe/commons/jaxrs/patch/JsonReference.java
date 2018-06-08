package edu.psu.swe.commons.jaxrs.patch;

import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import edu.psu.swe.commons.jaxrs.server.ObjectMapperContextResolver;

abstract class JsonReference {

  protected final JsonReference next;
  protected final ObjectMapper objectMapper;

  protected JsonReference(JsonReference next) {
    this.next = next;
    this.objectMapper = next == null ? new ObjectMapperContextResolver().getContext(null) : null;
  }
  /**
   * 
   * @param parent
   * @param parentType
   * @param jsonValue
   * @return
   * @throws PropertyDoesNotExistException
   * @throws PropertyIsNullException
   * @throws FailedToAddPropertyException
   */
  abstract Object add(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToAddPropertyException;

  /**
   * 
   * @param parent
   * @param parentType
   * @return
   * @throws PropertyDoesNotExistException
   * @throws PropertyIsNullException
   * @throws FailedToRemovePropertyException
   */
  abstract Object remove(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToRemovePropertyException;

  /**
   * 
   * @param parent
   * @param parentType
   * @return
   * @throws PropertyDoesNotExistException
   * @throws PropertyIsNullException
   * @throws FailedToGetPropertyException
   */
  abstract Object get(Object parent, Type parentType) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToGetPropertyException;

  /**
   * 
   * @param parent
   * @param parentType
   * @param jsonValue
   * @throws PropertyDoesNotExistException
   * @throws PropertyIsNullException
   * @throws FailedToSetPropertyException
   */
  abstract void set(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToSetPropertyException;

  /**
   * 
   * @param parent
   * @param parentType
   * @param jsonValue
   * @return
   * @throws PropertyDoesNotExistException
   * @throws PropertyIsNullException
   * @throws FailedToTestPropertyException
   */
  abstract boolean test(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToTestPropertyException;

  protected Object convert(JsonNode jsonValue, Type type) throws IllegalArgumentException {
    Object value;

    if (jsonValue.isNull()) {
      if (type instanceof Class<?> && ((Class<?>) type).isPrimitive()) {
        throw new IllegalArgumentException("Cannot set " + type.getTypeName() + " to null");
      }
    } else {
      // prevent automatic conversion of some types by jackson such as numbers to strings and vice-versa
      if (!jsonValue.isTextual() && type == String.class) {
        throw new IllegalArgumentException("Cannot convert to " + type.getTypeName() + " from non-string");
      } else if (!jsonValue.isFloatingPointNumber() && (type == Float.class || type == float.class || type == Double.class || type == double.class)) {
        throw new IllegalArgumentException("Cannot convert to " + type.getTypeName() + " from non-floating point");
      } else if (!jsonValue.isIntegralNumber() && (type == Long.class || type == long.class
                                                   || type == Integer.class || type == int.class
                                                   || type == Short.class || type == short.class
                                                   || type == Byte.class || type == byte.class
                                                   || type == Character.class || type == char.class)) {
        throw new IllegalArgumentException("Cannot convert to " + type.getTypeName() + " from non-integral");
      }
    }
    JavaType javaType = TypeFactory.defaultInstance().constructType(type);
    value = this.objectMapper.convertValue(jsonValue, javaType);

    return value;
  }
}
