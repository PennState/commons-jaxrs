package edu.psu.swe.commons.jaxrs.patch;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

class ArrayIndex extends JsonReference {

  private final int index;

  ArrayIndex(int index, JsonReference next) {
    super(next);
    this.index = index;
  }

  /*
   * TODO much of this code looks repetitive. however, each case does do
   * something different each time that it complicates extracting out the
   * commonalities
   */

  /**
   * {@inheritDoc}
   */
  @Override
  public Object add(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToAddPropertyException {
    Object newParent;

    if (parentType == null && (parent instanceof List || parent instanceof Set)) {
      throw new FailedToAddPropertyException("Cannot have a List or Set at the root of a JSON pointer that is not the property of a container class");
    }
    Class<?> parentClass = parent.getClass();

    try {
      if (super.next != null) {
        Property childProperty = this.getChild(parent, parentType);
        Object child = childProperty.getObject();

        if (child == null) {
          throw new PropertyIsNullException("Index is null: " + this.getIndex());
        }
        Type childType = childProperty.getType();
        Object newChild = super.next.add(child, childType, jsonValue);

        if (newChild != child) {
          this.setChild(parent, newChild);
        }
        newParent = parent;
      } else if (parent.getClass().isArray()) {
        int length = Array.getLength(parent);
        int index = this.index == -1 ? length : this.index;

        if (index <= length) {
          int newLength = length + 1;
          Class<?> componentType = parent.getClass().getComponentType();
          newParent = Array.newInstance(componentType, newLength);

          System.arraycopy(parent, 0, newParent, 0, index);
          System.arraycopy(parent, index, newParent, index + 1, length - index);

          Type type = parentType instanceof GenericArrayType ? ((GenericArrayType) parentType).getGenericComponentType() : parentClass.getComponentType();
          Object value;

          try {
            value = super.convert(jsonValue, type);
          } catch (IllegalArgumentException convertException) {
            throw new FailedToSetPropertyException("Failed to convert JSON to " + type.getTypeName() + ": " + this.getIndex(), convertException);
          }
          Array.set(newParent, index, value);
        } else {
          throw new PropertyDoesNotExistException("Index is out of bounds (" + length + "): " + this.getIndex());
        }
      } else if (parent instanceof List) {
        @SuppressWarnings("unchecked")
        List<Object> parentAsList = (List<Object>) parent;
        int length = parentAsList.size();
        int index = this.index == -1 ? length : this.index;

        if (index > length) {
          throw new PropertyDoesNotExistException("Index is out of bounds (" + length + "): " + this.getIndex());
        }
        Type type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        Object value;

        try {
          value = super.convert(jsonValue, type);

          parentAsList.add(index, value);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToSetPropertyException("Failed to convert JSON to " + type.getTypeName() + ": " + this.getIndex(), convertException);
        } catch (UnsupportedOperationException unsupportedOperationException) {
          throw new FailedToAddPropertyException("Cannot add to immutable List property", unsupportedOperationException);
        }
        newParent = parent;
      } else if (parent instanceof Set) {
        @SuppressWarnings("unchecked")
        Set<Object> parentAsSet = (Set<Object>) parent;
        Type type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        Object value;

        try {
          value = super.convert(jsonValue, type);

          parentAsSet.add(value);
        }  catch (IllegalArgumentException convertException) {
          throw new FailedToAddPropertyException("Failed to convert JSON to " + type.getTypeName(), convertException);
        } catch (UnsupportedOperationException unsupportedOperationException) {
          throw new FailedToAddPropertyException("Cannot add to immutable Set property", unsupportedOperationException);
        }
        newParent = parent;
      } else {
        throw new PropertyDoesNotExistException("Cannot index into " + parentClass.getCanonicalName());
      }
    } catch (IllegalArgumentException | IllegalAccessException unexpected) {
      throw new FailedToAddPropertyException("Unexpected exception", unexpected);
    } catch (InvocationTargetException removeException) {
      throw new FailedToAddPropertyException("Getter method threw an exception when accessing index of " + parentClass.getCanonicalName() + ": " + this.getIndex(), removeException);
    } catch (FailedToSetPropertyException setException) {
      throw new FailedToAddPropertyException("Failed to add property because we failed to set the field", setException);
    }
    return newParent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object remove(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToRemovePropertyException {
    Object newParent;
    Class<?> parentClass = parent.getClass();

    try {
      if (super.next != null) {
        Property childProperty = this.getChild(parent, parentType);
        Object child = childProperty.getObject();

        if (child == null) {
          throw new PropertyIsNullException("Index is null: " + this.getIndex());
        }
        Type childType = childProperty.getType();
        Object newChild = super.next.remove(child, childType, jsonValue);

        if (newChild != child) {
          this.setChild(parent, newChild);
        }
        newParent = parent;
      } else if (parent.getClass().isArray()) {
        int length = Array.getLength(parent);
        int index = this.index == -1 ? length - 1 : this.index;

        this.checkLength(index, length);

        int newLength = length - 1;
        Class<?> componentType = parent.getClass().getComponentType();
        newParent = Array.newInstance(componentType, newLength);

        System.arraycopy(parent, 0, newParent, 0, index);
        System.arraycopy(parent, index + 1, newParent, index, newLength - index);
      } else if (parent instanceof List) {
        List<?> parentAsList = (List<?>) parent;
        int length = parentAsList.size();
        int index = this.index == -1 ? length - 1 : this.index;
        newParent = parent;

        this.checkLength(index, length);

        try {
          parentAsList.remove(index);
        } catch (UnsupportedOperationException unsupportedOperationException) {
          throw new FailedToRemovePropertyException("Cannot remove from immutable List property in " + parentClass.getCanonicalName(), unsupportedOperationException);
        }
      } else if (parent instanceof Set) {
        if (jsonValue == null) {
          throw new FailedToRemovePropertyException("Value property was not provided in JSON Patch Remove request for a Set");
        }
        Set<?> parentAsSet = (Set<?>) parent;
        Type type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        Object value;

        try {
          value = super.convert(jsonValue, type);

          parentAsSet.remove(value);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToRemovePropertyException("Failed to convert JSON to " + type.getTypeName());
        } catch (UnsupportedOperationException unsupportedOperationException) {
          throw new FailedToRemovePropertyException("Cannot mutate immutable Set property in " + parentClass.getCanonicalName(), unsupportedOperationException);
        }
        newParent = parent;
      } else {
        throw new PropertyDoesNotExistException("Cannot index into " + parentClass.getCanonicalName());
      }
    } catch (IllegalArgumentException | IllegalAccessException unexpected) {
      throw new FailedToRemovePropertyException("Unexpected exception", unexpected);
    } catch (InvocationTargetException removeException) {
      throw new FailedToRemovePropertyException("Getter method threw an exception when accessing index of " + parentClass.getCanonicalName() + ": " + this.getIndex(), removeException);
    }
    return newParent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object get(Object parent, Type parentType) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToGetPropertyException {
    Object child;
    Class<?> parentClass = parent.getClass();

    try {
      if (super.next != null) {
        Property property = this.getChild(parent, parentType);
        Object propertyValue = property.getObject();

        if (propertyValue == null) {
          throw new PropertyIsNullException("Index is null: " + this.getIndex());
        }
        Type propertyType = property.getType();
        child = super.next.get(propertyValue, propertyType);
      } else {
        Property property = this.getChild(parent, parentType);
        child = property.getObject();
      }
    } catch (IllegalArgumentException | IllegalAccessException unexpected) {
      throw new FailedToGetPropertyException("An unexpected exception occurred when indexing into " + parentClass.getCanonicalName() + ": " + this.getIndex(), unexpected);
    } catch (InvocationTargetException getException) {
      throw new FailedToGetPropertyException("An exception was thrown when attempting to index into " + parentClass.getCanonicalName() + ": " + this.getIndex(), getException);
    }
    return child;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void set(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToSetPropertyException {
    if (parentType == null && parent instanceof List) {
      throw new FailedToSetPropertyException("Cannot have a List at the root of a JSON pointer that is not the property of a container class");
    }
    Class<?> parentClass = parent.getClass();

    try {
      if (super.next != null) {
        Object child;
        Type childType;
        Property property = this.getChild(parent, parentType);
        child = property.getObject();

        if (child == null) {
          throw new PropertyIsNullException("Index is null: " + this.getIndex());
        }
        childType = property.getType();

        super.next.set(child, childType, jsonValue);
      } else if (parentClass.isArray()) {
        int length = Array.getLength(parent);
        int index = this.index == -1 ? length - 1 : this.index;

        this.checkLength(index, length);

        Type componentType = parentType instanceof GenericArrayType ? ((GenericArrayType) parentType).getGenericComponentType() : parentClass.getComponentType();
        Object value;

        try {
          value = super.convert(jsonValue, componentType);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToSetPropertyException("Failed to convert JSON to " + componentType.getTypeName() + ": " + this.getIndex(), convertException);
        }
        Array.set(parent, index, value);
      } else if (parent instanceof List) {
        /*
         * TODO assuming that `parentType` is a java.util.*List<?>, I haven't
         * yet seen a situation where that is not true, otherwise this will
         * fail, will need to analyze the type of `parent` further to get
         * the parameterized type
         */
        Type type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        Object value;

        try {
          value = super.convert(jsonValue, type);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToSetPropertyException("Failed to convert JSON to " + type.getTypeName() + ": " + this.getIndex(), convertException);
        }
        @SuppressWarnings("unchecked")
        List<Object> parentAsList = (List<Object>) parent;
        int length = parentAsList.size();
        int index = this.index == -1 ? length - 1 : this.index;

        this.checkLength(index, length);

        try {
          parentAsList.set(index, value);
        } catch (UnsupportedOperationException unsupportedOperationException) {
          throw new FailedToSetPropertyException("Cannot mutate immutable List property in " + parentClass.getCanonicalName(), unsupportedOperationException);
        }
      } else if (parent instanceof Set) {
        /**
         * TODO see previous TODO
         */
        Type type = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        Object value;

        try {
          value = super.convert(jsonValue, type);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToSetPropertyException("Failed to convert JSON to " + type.getTypeName());
        }
        @SuppressWarnings("unchecked")
        Set<Object> parentAsSet = (Set<Object>) parent;

        try {
          parentAsSet.add(value);
        } catch (UnsupportedOperationException unsupportedOperationException) {
          throw new FailedToSetPropertyException("Cannot mutate immutable Set property in " + parentClass.getCanonicalName(), unsupportedOperationException);
        }
      } else {
        throw new PropertyDoesNotExistException("Cannot index into " + parentClass.getCanonicalName());
      }
    } catch (IllegalArgumentException | IllegalAccessException unexpected) {
      throw new FailedToSetPropertyException("An unexpected exception occurred when indexing into " + parentClass.getCanonicalName() + ": " + this.getIndex(), unexpected);
    } catch (InvocationTargetException setException) {
      throw new FailedToSetPropertyException("An exception was thrown when attempting to index into " + parentClass.getCanonicalName() + ": " + this.getIndex(), setException);
    }
  }

  public boolean test(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToTestPropertyException {
    boolean result;

    if (parentType == null && parent instanceof List) {
      throw new FailedToTestPropertyException("Cannot test the index of a List at the root of a JSON pointer that is not the property of a container class");
    }
    Object child;
    Type childType;
    Object value;
    Type valueType;
    Class<?> parentClass = parent.getClass();

    try {
      if (super.next != null) {
        Property property = this.getChild(parent, parentType);
        child = property.getObject();

        if (child == null) {
          throw new PropertyIsNullException("Index is null: " + this.getIndex());
        }
        childType = property.getType();
        result = super.next.test(child, childType, jsonValue);
      } else if (parent instanceof Set) {
        Set<?> parentAsSet = (Set<?>) parent;
        valueType = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        value = super.convert(jsonValue, valueType);
        result = parentAsSet.contains(value);
      } else {
        if (parentClass.isArray()) {
          int length = Array.getLength(parent);
          int index = this.index == -1 ? length - 1 : this.index;

          this.checkLength(index, length);

          child = Array.get(parent, index);
          valueType = parentType instanceof GenericArrayType ? ((GenericArrayType) parentType).getGenericComponentType() : parentClass.getComponentType();
        } else if (parent instanceof List) {
          List<?> parentAsList = (List<?>) parent;
          int length = parentAsList.size();
          int index = this.index == -1 ? length - 1 : this.index;

          this.checkLength(index, length);

          child = parentAsList.get(index);
          valueType = ((ParameterizedType) parentType).getActualTypeArguments()[0];
        } else {
          throw new PropertyDoesNotExistException("Cannot index into " + parentClass.getCanonicalName());
        }
        try {
          value = super.convert(jsonValue, valueType);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToTestPropertyException("Failed to convert JSON to " + valueType.getTypeName() + ": " + this.getIndex(), convertException);
        }
        result = Objects.equals(child, value);
      }
    } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException unexpected) {
      throw new FailedToTestPropertyException("An unexpected exception occurred when indexing into " + parentClass.getCanonicalName() + ": " + this.getIndex(), unexpected);
    }
    return result;
  }

  @Override
  public String toString() {
    return index == -1 ? "-" : Integer.toString(index);
  }

  private void setChild(Object parent, Object child) {
    Class<?> parentClass = parent.getClass();

    if (parentClass.isArray()) {
      Array.set(parent, this.index, child);
    } else if (parent instanceof List) {
      @SuppressWarnings("unchecked")
      List<Object> parentAsList = (List<Object>) parent;

      parentAsList.set(this.index, child);
    } else {
      throw new RuntimeException("Should not have gotten this far");
    }
  }

  private Property getChild(Object parent, Type type) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, PropertyDoesNotExistException {
    Property child;
    Class<?> parentClass = parent.getClass();

    if (parentClass.isArray()) {
      int length = Array.getLength(parent);
      int index = this.index == -1 ? length - 1 : this.index;

      this.checkLength(index, length);

      Object childValue = Array.get(parent, index);
      Type childType = parentClass.getComponentType();
      child = new Property(childValue, childType);
    } else if (parent instanceof List) {
      List<?> parentAsList = (List<?>) parent;
      int length = parentAsList.size();
      int index = this.index == -1 ? length - 1 : this.index;

      this.checkLength(index, length);

      Object childValue = parentAsList.get(this.index);
      Type childType = ((ParameterizedType) type).getActualTypeArguments()[0];
      child = new Property(childValue, childType);
    } else if (parent instanceof Map) {
      Object childValue = ((Map<?, ?>) parent).get(this.index);
      Type childType = ((ParameterizedType) type).getActualTypeArguments()[1];
      child = new Property(childValue, childType);
    } else {
      throw new PropertyDoesNotExistException("Cannot index into " + parentClass.getCanonicalName());
    }
    return child;
  }

  private void checkLength(int index, int length) throws PropertyDoesNotExistException {
    if (index >= length || index < 0) {
      throw new PropertyDoesNotExistException("Index is out of bounds (" + length + "): " + this.getIndex());
    }
  }

  private String getIndex() {
    return this.index == -1 ? "-" : Integer.toString(this.index);
  }
}
