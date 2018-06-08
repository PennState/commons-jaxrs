package edu.psu.swe.commons.jaxrs.patch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PropertyName extends JsonReference {

  private static final Map<Class<?>, Integer> INTEGRAL_TYPES;
  private static final Map<Class<?>, Integer> FLOAT_TYPES;

  static {
    Class<?>[] integralTypes = new Class<?>[] {
      Long.class, long.class,
      Integer.class, int.class,
      Short.class, short.class,
      Character.class, char.class,
      Byte.class, byte.class
    };
    Class<?>[] floatTypes = new Class<?>[] {
      Double.class, double.class,
      Float.class, float.class,
    };
    INTEGRAL_TYPES = new HashMap<>(integralTypes.length);
    FLOAT_TYPES = new HashMap<>(floatTypes.length);

    for (int i = 0; i < integralTypes.length; i += 2) {
      INTEGRAL_TYPES.put(integralTypes[i], i);
      INTEGRAL_TYPES.put(integralTypes[i + 1], i);
    }
    for (int i = 0; i < floatTypes.length; i += 2) {
      FLOAT_TYPES.put(floatTypes[i], i);
      FLOAT_TYPES.put(floatTypes[i + 1], i);
    }
  }

  private final String name;

  PropertyName(String name, JsonReference next) {
    super(next);
    this.name = name;
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
    Class<?> parentClass = parent.getClass();

    if (!isAnnotated(parentClass)) {
      throw new FailedToAddPropertyException(this.createNotAnnotatedMessage(parentClass));
    }
    if (super.next == null) {
      throw new FailedToAddPropertyException("Cannot add properties to objects: " + this.name);
    }
    Object child;
    Object origChild;
    Type childType;

    try {
      Property property = this.getChild(parent);
      child = property.getObject();
      origChild = this.getChild(parent).getObject();

      if (child == null) {
        throw new PropertyIsNullException("Property is null in " + parentClass.getCanonicalName() + ": " + this.name);
      }
      childType = property.getType();
    } catch (IllegalAccessException | IllegalArgumentException unexpected) {
      throw new FailedToAddPropertyException("Unexpected exception occurred when trying to add property in " + parentClass.getCanonicalName() + ": " + this.name, unexpected);
    } catch (InvocationTargetException addException) {
      throw new FailedToAddPropertyException("Could not add property because a method threw an exception in " + parentClass.getCanonicalName() + ": " + this.name, addException);
    }
    Object newChild = super.next.add(child, childType, jsonValue);

    if (newChild != origChild) {
      try {
        this.setChild(parent, newChild);
      } catch (FailedToSetPropertyException setException) {
        throw new FailedToAddPropertyException("Failed to add property in " + parentClass.getCanonicalName() + ": " + this.name, setException);
      }
    }
    return parent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object remove(Object parent, Type parentType, JsonNode jsonValue) throws  PropertyDoesNotExistException, PropertyIsNullException, FailedToRemovePropertyException {
    Class<?> parentClass = parent.getClass();

    if (!isAnnotated(parentClass)) {
      throw new FailedToRemovePropertyException(this.createNotAnnotatedMessage(parentClass));
    }
    if (super.next == null) {
      throw new FailedToRemovePropertyException("Cannot remove properties from objects: " + this.name);
    }
    Object child;
    Object origChild;
    Type childType;

    try {
      Property property = this.getChild(parent);
      child = property.getObject();
      origChild = this.getChild(parent).getObject();

      if (child == null) {
        throw new PropertyIsNullException("Property is null in " + parentClass.getCanonicalName() + ": " + this.name);
      }
      childType = property.getType();
    } catch (IllegalAccessException | IllegalArgumentException unexpected) {
      throw new FailedToRemovePropertyException("Unexpected exception occurred when trying to remove property in " + parentClass.getCanonicalName() + ": " + this.name, unexpected);
    } catch (InvocationTargetException addException) {
      throw new FailedToRemovePropertyException("Could not remove property because a method threw an exception in " + parentClass.getCanonicalName() + ": " + this.name, addException);
    }
    Object newChild = super.next.remove(child, childType, jsonValue);

    if (newChild != origChild) {
      try {
        this.setChild(parent, newChild);
      } catch (FailedToSetPropertyException setException) {
        throw new FailedToRemovePropertyException("Failed to remove property in " + parentClass.getCanonicalName() + ": " + this.name, setException);
      }
    }
    return parent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object get(Object parent, Type parentType) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToGetPropertyException {
    Object child;
    Class<?> parentClass = parent.getClass();

    if (!isAnnotated(parentClass)) {
      throw new FailedToGetPropertyException(this.createNotAnnotatedMessage(parentClass));
    }
    try {
      Property childProperty = this.getChild(parent);

      if (super.next != null) {
        Object propertyValue = childProperty.getObject();
        Type propertyType = childProperty.getType();

        if (propertyValue == null) {
          throw new PropertyIsNullException("Property of " + parentClass.getCanonicalName() + " is null: " + this.name);
        }
        child = super.next.get(propertyValue, propertyType);
      } else {
        child = childProperty.getObject();
      }
    } catch (IllegalAccessException | IllegalArgumentException unexpected) {
      throw new FailedToGetPropertyException("Unexpected exception: " + this.name, unexpected);
    } catch (InvocationTargetException getException) {
      throw new FailedToGetPropertyException("Getter method threw an exception when accessing property of " + parentClass.getCanonicalName() + ": " + this.name, getException);
    }
    return child;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void set(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToSetPropertyException {
    Class<?> parentClass = parent.getClass();

    if (!isAnnotated(parentClass)) {
      throw new FailedToSetPropertyException(this.createNotAnnotatedMessage(parentClass));
    }
    if (super.next != null) {
      try {
        Property property = this.getChild(parent);
        Object child = property.getObject();
        Type childType = property.getType();

        if (child == null) {
          throw new PropertyIsNullException("Property in " + parentClass.getCanonicalName() + " is null: " + this.name);
        }
        super.next.set(child, childType, jsonValue);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException getException) {
        throw new FailedToSetPropertyException("Failed to set property because it could not be reached: " + this.name, getException);
      }
    } else {
      search: {
        try {
          for (Class<?> clazz = parentClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
            if (clazz.getAnnotation(XmlType.class) == null && clazz.getAnnotation(XmlRootElement.class) == null) {
              continue;
            }
            for (Field field : clazz.getDeclaredFields()) {
              if (Modifier.isStatic(field.getModifiers())) {
                continue;
              }
              XmlElement xmlElement = field.getAnnotation(XmlElement.class);

              if (xmlElement != null) {
                String xmlElementName = xmlElement.name();
                String fieldName = field.getName();
                String name = "##default".equals(xmlElementName) ? fieldName : xmlElementName;

                if (this.name.equals(name)) {
                  String setterName = setter(fieldName);
                  Stream<Method> candidates = Stream.of(clazz.getDeclaredMethods())
                      .filter(method -> !Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 1 && setterName.equals(method.getName()));

                  if (jsonValue.isBoolean()) {
                    candidates = candidates.filter(method -> {
                      Class<?> parameter = method.getParameterTypes()[0];

                      return parameter == Boolean.class || parameter == boolean.class;
                    });
                  } else if (jsonValue.isTextual()) {
                    candidates = candidates.filter(method -> method.getParameterTypes()[0] == String.class);
                  } else if (jsonValue.isFloatingPointNumber()) {
                    candidates = candidates.filter(method -> FLOAT_TYPES.containsKey(method.getParameterTypes()[0]))
                        .sorted((a, b) -> FLOAT_TYPES.get(a.getParameterTypes()[0]) - FLOAT_TYPES.get(b.getParameterTypes()[0]));
                  } else if (jsonValue.isIntegralNumber()) {
                    candidates = candidates.filter(method -> INTEGRAL_TYPES.containsKey(method.getParameterTypes()[0]))
                        .sorted((a, b) -> INTEGRAL_TYPES.get(a.getParameterTypes()[0]) - INTEGRAL_TYPES.get(b.getParameterTypes()[0]));
                  } else if (jsonValue.isArray()) {
                    candidates = candidates.filter(method -> {
                      Class<?> parameterType = method.getParameterTypes()[0];

                      return parameterType.isArray() || Collection.class.isAssignableFrom(parameterType);
                    });
                  } else if (jsonValue.isObject()) {
                    candidates = candidates.filter(method -> {
                      Class<?> parameterType = method.getParameterTypes()[0];

                      return !parameterType.isPrimitive() && !parameterType.isArray() && !Collection.class.isAssignableFrom(parameterType);
                    });
                  } else if (jsonValue.isNull()) {
                    candidates = candidates.filter(method -> !method.getParameterTypes()[0].isPrimitive());
                  } else {
                    throw new IllegalArgumentException("Unexpected jackson class: " + jsonValue.getClass().getCanonicalName());
                  }
                  List<Method> setters = candidates.collect(Collectors.toList());

                  for (Method setter : setters) {
                    Object value;
                    Type parameterType = setter.getParameters()[0].getParameterizedType();

                    setter.setAccessible(true);

                    try {
                      value = super.convert(jsonValue, parameterType);
                    } catch (IllegalArgumentException ignored) {
                      log.debug("Failed to convert: {}", ignored);

                      continue;
                    }
                    try {
                      setter.invoke(parent, value);

                      break search;
                    } catch (IllegalAccessException | IllegalArgumentException ignored) {
                      log.debug("Setter method failed: {}", ignored);
                    } catch (InvocationTargetException invocationTargetException) {
                      throw new FailedToSetPropertyException("Setter method of " + parentClass.getCanonicalName() + " threw an exception: " + this.name, invocationTargetException);
                    }
                  }
                  this.checkFinal(field);

                  Object value;
                  Type fieldType = field.getGenericType();

                  try {
                    value = super.convert(jsonValue, fieldType);
                  } catch (IllegalArgumentException convertException) {
                    throw new FailedToSetPropertyException("Failed to convert JSON to " + fieldType.getTypeName() + ": " + this.name, convertException);
                  }
                  field.setAccessible(true);

                  try {
                    field.set(parent, value);

                    break search;
                  } catch (IllegalArgumentException | IllegalAccessException setException) {
                    throw new FailedToSetPropertyException("Failed to directly set property of " + parentClass.getCanonicalName() + ": " + this.name, setException);
                  }
                }
              }
            }
            for (Method method : clazz.getDeclaredMethods()) {
              XmlElement xmlElement = method.getAnnotation(XmlElement.class);
              String methodName = method.getName();

              if (xmlElement == null || method.getParameterCount() != 1 || !methodName.startsWith("set")) {
                continue;
              }
              String xmlElementName = xmlElement.name();
              String name = "##default".equals(xmlElementName) ? propertyName(method) : xmlElementName;

              if (this.name.equals(name)) {
                Object value;
                Type methodType = method.getParameters()[0].getParameterizedType();

                try {
                  value = super.convert(jsonValue, methodType);
                } catch (IllegalArgumentException illegalArgumentException) {
                  throw new FailedToSetPropertyException("Failed to convert JSON to " + methodType.getTypeName() + " in " + parentClass.getCanonicalName() + ": " + this.name);
                }
                method.setAccessible(true);
                method.invoke(parent, value);

                break search;
              }
            }
          }
          throw new PropertyDoesNotExistException("Property does not exist in " + parentClass.getCanonicalName() + ": " + this.name);
        } catch (IllegalArgumentException | IllegalAccessException unexpected) {
          throw new FailedToSetPropertyException("An unexpected exception occurred when accessing " + parentClass.getCanonicalName() + ": " + this.name, unexpected);
        } catch (InvocationTargetException setException) {
          throw new FailedToSetPropertyException("An exception was thrown when attempting to access " + parentClass.getCanonicalName() + ": " + this.name);
        }
      }
    }
  }

  public boolean test(Object parent, Type parentType, JsonNode jsonValue) throws PropertyDoesNotExistException, PropertyIsNullException, FailedToTestPropertyException {
    boolean result;
    Class<?> parentClass = parent.getClass();

    if (!isAnnotated(parentClass)) {
      throw new FailedToTestPropertyException(this.createNotAnnotatedMessage(parentClass));
    }
    try {
      if (super.next != null) {
        Property property = this.getChild(parent);
        Object child = property.getObject();

        if (child == null) {
          throw new PropertyIsNullException("Property in " + parentClass.getCanonicalName() + " is null: " + this.name);
        }
        Type childType = property.getType();
        result = super.next.test(child, childType, jsonValue);
      } else {
        Object child;
        Object value;
        Type valueType;

        search: {
          for (Class<?> clazz = parentClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
              if (Modifier.isStatic(field.getModifiers())) {
                continue;
              }
              XmlElement xmlElement = field.getAnnotation(XmlElement.class);

              if (xmlElement != null) {
                String xmlElementName = xmlElement.name();
                String fieldName = field.getName();
                String name = "##default".equals(xmlElementName) ? fieldName : xmlElementName;

                if (this.name.equals(name)) {
                  String getterName = getter(fieldName);

                  for (Method method : clazz.getDeclaredMethods()) {
                    if (isNotGetterMethod(method, getterName)) {
                      continue;
                    }
                    method.setAccessible(true);

                    child = method.invoke(parent);
                    valueType = method.getGenericReturnType();

                    break search;
                  }
                  field.setAccessible(true);

                  child = field.get(parent);
                  valueType = field.getGenericType();

                  break search;
                }
              }
            }
          }
          throw new PropertyDoesNotExistException("Property does not exist in " + parentClass.getCanonicalName() + ": " + this.name);
        }
        try {
          value = super.convert(jsonValue, valueType);
        } catch (IllegalArgumentException convertException) {
          throw new FailedToTestPropertyException("Failed to convert JSON to " + valueType.getTypeName() + ": " + this.name, convertException);
        }
        result = Objects.equals(child, value);
      }
    } catch (IllegalArgumentException | IllegalAccessException unexpected) {
      throw new FailedToTestPropertyException("An unexpected exception occurred when accessing " + parentClass.getCanonicalName() + ": " + this.name, unexpected);
    } catch (InvocationTargetException setException) {
      throw new FailedToTestPropertyException("An exception was thrown by a method when attempting to access " + parentClass.getCanonicalName() + ": " + this.name, setException);
    }
    return result;
  }

  private Property getChild(Object object) throws PropertyDoesNotExistException, PropertyIsNullException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Property child;
    Class<?> parentClass = object.getClass();

    search: {
      for (Class<?> clazz = parentClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
        for (Field field : clazz.getDeclaredFields()) {
          if (Modifier.isStatic(field.getModifiers())) {
            continue;
          }
          XmlElement xmlElement = field.getAnnotation(XmlElement.class);

          if (xmlElement == null) {
            continue;
          }
          String xmlElementName = xmlElement.name();
          String fieldName = field.getName();
          String name = "##default".equals(xmlElementName) ? fieldName : xmlElementName;

          if (this.name.equals(name)) {
            String getterName = getter(fieldName);

            for (Method method : clazz.getDeclaredMethods()) {
              if (isNotGetterMethod(method, getterName)) {
                continue;
              }
              method.setAccessible(true);

              Object childObject =  method.invoke(object);
              Type childType = method.getGenericReturnType();
              child = new Property(childObject, childType);

              break search;
            }
            field.setAccessible(true);

            Object childObject = field.get(object);
            Type childType = field.getGenericType();
            child = new Property(childObject, childType);

            break search;
          }
        }
        for (Method method : clazz.getDeclaredMethods()) {
          if (Modifier.isStatic(method.getModifiers())) {
            continue;
          }
          XmlElement xmlElement = method.getAnnotation(XmlElement.class);

          if (xmlElement == null || method.getParameterCount() != 0 || !method.getName().startsWith("get")) {
            continue;
          }
          String xmlElementName = xmlElement.name();
          String name = "##default".equals(xmlElementName) ? propertyName(method) : xmlElementName;

          if (this.name.equals(name)) {
            Object childObject = method.invoke(object);
            Type childType = method.getGenericReturnType();
            child = new Property(childObject, childType);

            break search;
          }
        }
      }
      throw new PropertyDoesNotExistException("Property does not exist in " + parentClass.getCanonicalName() + ": " + this.name);
    }
    return child;
  }

  private void setChild(Object parent, Object child) throws FailedToSetPropertyException {
    Class<?> parentClass = parent.getClass();

    try {
      search: {
        for (Class<?> clazz = parentClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
          for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
              continue;
            }
            XmlElement xmlElement = field.getAnnotation(XmlElement.class);

            if (xmlElement != null) {
              String xmlElementName = xmlElement.name();
              String fieldName = field.getName();
              String name = "##default".equals(xmlElementName) ? fieldName : xmlElementName;

              if (this.name.equals(name)) {
                String setterName = setter(fieldName);

                for (Method method : clazz.getDeclaredMethods()) {
                  if (isNotSetterMethod(method, setterName)) {
                    continue;
                  }
                  method.setAccessible(true);
                  method.invoke(parent, child);

                  break search;
                }
                this.checkFinal(field);
                field.setAccessible(true);
                field.set(parent, child);

                break search;
              }
            }
          }
          for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers())) {
              continue;
            }
            XmlElement xmlElement = method.getAnnotation(XmlElement.class);

            if (xmlElement == null ||  method.getParameterCount() != 1 || !method.getName().startsWith("set")) {
              continue;
            }
            String xmlElementName = xmlElement.name();
            String name = "##default".equals(xmlElementName) ? propertyName(method) : xmlElementName;

            if (this.name.equals(name)) {
              method.setAccessible(true);
              method.invoke(parent, child);

              break search;
            }
          }
        }
        throw new RuntimeException("Property should have been found: " + this.name);
      }
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unexpected) {
      throw new RuntimeException("Unexpected exception", unexpected);
    }
  }

  @Override
  public String toString() {
    return this.name;
  }

  private void checkFinal(Field field) throws FailedToSetPropertyException {
    if (Modifier.isFinal(field.getModifiers())) {
      throw new FailedToSetPropertyException("Property is final in " + field.getDeclaringClass().getCanonicalName() + ": " + this.name);
    }
  }

  private static String getter(String name) {
    String getter = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);

    return getter;
  }

  private static String propertyName(Method method) {
    String methodName = method.getName();
    String name = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);

    return name;
  }

  private static String setter(String name) {
    String setter = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);

    return setter;
  }

  private static boolean isAnnotated(Class<?> clazz) {
    boolean isAnnotated = false;

    for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
      isAnnotated = clazz.getAnnotation(XmlType.class) != null || clazz.getAnnotation(XmlRootElement.class) != null;

      if (isAnnotated) {
        break;
      }
    }
    return isAnnotated;
  }

  private String createNotAnnotatedMessage(Class<?> clazz) {
    String message = "Cannot set property of " + clazz.getCanonicalName() + ", : " + this.name;

    return message;
  }

  private static boolean isNotGetterMethod(Method method, String getterName) {
    boolean undesired = method.getParameterCount() != 0 || Modifier.isStatic(method.getModifiers()) || !getterName.equals(method.getName());

    return undesired;
  }

  private static boolean isNotSetterMethod(Method method, String setterName) {
    boolean undesired = method.getParameterCount() != 0 || Modifier.isStatic(method.getModifiers()) || !setterName.equals(method.getName());

    return undesired;
  }

}
