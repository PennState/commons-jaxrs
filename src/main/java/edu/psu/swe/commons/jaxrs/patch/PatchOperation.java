package edu.psu.swe.commons.jaxrs.patch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@XmlType(propOrder={"operation", "path", "value"})
@XmlAccessorType(XmlAccessType.NONE)
public class PatchOperation {

  public static PatchOperation add(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.ADD, jsonPointer, value);
  }

  public static PatchOperation remove(JsonPointer jsonPointer) {
    return new PatchOperation(Type.REMOVE, jsonPointer);
  }

  public static PatchOperation replace(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.REPLACE, jsonPointer, value);
  }

  public static PatchOperation test(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.TEST, jsonPointer, value);
  }

  @XmlEnum(String.class)
  public enum Type {
    @XmlEnumValue("add") ADD,
    @XmlEnumValue("copy") COPY,
    @XmlEnumValue("move") MOVE,
    @XmlEnumValue("remove") REMOVE,
    @XmlEnumValue("replace") REPLACE,
    @XmlEnumValue("test") TEST;
  }

  @XmlElement(name="op")
  private Type operation;

  @XmlElement
  @XmlJavaTypeAdapter(JsonPointerAdapter.class)
  private JsonPointer path;

  @XmlElement
  private JsonNode value;

  @XmlElement
  @XmlJavaTypeAdapter(JsonPointerAdapter.class)
  private JsonPointer from;

  protected PatchOperation(Type type, JsonPointer pointer, JsonNode value) {
    this.operation = type;
    this.path = pointer;
    this.value = value;
  }

  protected PatchOperation(Type type, JsonPointer pointer) {
    this.operation = type;
    this.path = pointer;
  }
}
