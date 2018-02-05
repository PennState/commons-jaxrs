package edu.psu.swe.commons.jaxrs.patch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@XmlType(propOrder={"operation", "path", "value"})
@XmlAccessorType(XmlAccessType.NONE)
public class PatchOperation {

  /**
   * Create a generic `add` PatchOperation.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation add(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.ADD, jsonPointer, value);
  }

  /**
   * Create an `add` PatchOperation for booleans.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation add(JsonPointer jsonPointer, boolean value) {
    return add(jsonPointer, JsonNodeFactory.instance.booleanNode(value));
  }

  /**
   * Create an `add` PatchOperation for longs/ints.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation add(JsonPointer jsonPointer, long value) {
    return add(jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create an `add` PatchOperation for doubles/floats.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation add(JsonPointer jsonPointer, double value) {
    return add(jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create an `add` PatchOperation for Strings.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation add(JsonPointer jsonPointer, String value) {
    return add(jsonPointer, JsonNodeFactory.instance.textNode(value));
  }

  /**
   * Create an `add` PatchOperation for Objects.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation add(JsonPointer jsonPointer, Object value) {
    return add(jsonPointer, JsonNodeFactory.instance.pojoNode(value));
  }

  /**
   * Create a generic `remove` PatchOperation.
   * @param jsonPointer
   * @return
   */
  public static PatchOperation remove(JsonPointer jsonPointer) {
    return new PatchOperation(Type.REMOVE, jsonPointer);
  }

  /**
   * Create a generic `remove` PatchOperation where the last property is an index into a Set.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation remove(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.REMOVE, jsonPointer, value);
  }

  /**
   * Create a `remove` PatchOperation for longs/ints where the last property is an index into a Set.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation remove(JsonPointer jsonPointer, long value) {
    return new PatchOperation(Type.REMOVE, jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create a `remove` PatchOperation for doubles/floats where the last property is an index into a Set.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation remove(JsonPointer jsonPointer, double value) {
    return new PatchOperation(Type.REMOVE, jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create a `remove` PatchOperation for Strings where the last property is an index into a Set.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation remove(JsonPointer jsonPointer, String value) {
    return new PatchOperation(Type.REMOVE, jsonPointer, JsonNodeFactory.instance.textNode(value));
  }

  /**
   * Create a `remove` PatchOperation for Objects where the last property is an index into a Set.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation remove(JsonPointer jsonPointer, Object value) {
    return new PatchOperation(Type.REMOVE, jsonPointer, JsonNodeFactory.instance.pojoNode(value));
  }

  /**
   * Create a generic `replace` PatchOperation.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation replace(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.REPLACE, jsonPointer, value);
  }

  /**
   * Create a `replace` PatchOperation for booleans.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation replace(JsonPointer jsonPointer, boolean value) {
    return replace(jsonPointer, JsonNodeFactory.instance.booleanNode(value));
  }

  /**
   * Create a `replace` PatchOperation for longs/ints.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation replace(JsonPointer jsonPointer, long value) {
    return replace(jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create a `replace` PatchOperation for doubles/floats.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation replace(JsonPointer jsonPointer, double value) {
    return replace(jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create a `replace` PatchOperation for Strings.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation replace(JsonPointer jsonPointer, String value) {
    return replace(jsonPointer, JsonNodeFactory.instance.textNode(value));
  }

  /**
   * Create a `replace` PatchOperation for Objects.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation replace(JsonPointer jsonPointer, Object value) {
    return replace(jsonPointer, JsonNodeFactory.instance.pojoNode(value));
  }

  /**
   * Create a generic `test` PatchOperation.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation test(JsonPointer jsonPointer, JsonNode value) {
    return new PatchOperation(Type.TEST, jsonPointer, value);
  }

  /**
   * Create a `test` PatchOperation for booleans.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation test(JsonPointer jsonPointer, boolean value) {
    return test(jsonPointer, JsonNodeFactory.instance.booleanNode(value));
  }

  /**
   * Create a `test` PatchOperation for ints/longs.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation test(JsonPointer jsonPointer, long value) {
    return test(jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create a `test` PatchOperation for doubles/floats.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation test(JsonPointer jsonPointer, double value) {
    return test(jsonPointer, JsonNodeFactory.instance.numberNode(value));
  }

  /**
   * Create a `test` PatchOperation for Strings.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation test(JsonPointer jsonPointer, String value) {
    return test(jsonPointer, JsonNodeFactory.instance.textNode(value));
  }

  /**
   * Create a `test` PatchOperation for Objects.
   * @param jsonPointer
   * @param value
   * @return
   */
  public static PatchOperation test(JsonPointer jsonPointer, Object value) {
    return test(jsonPointer, JsonNodeFactory.instance.pojoNode(value));
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

  protected PatchOperation() {
  }

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
