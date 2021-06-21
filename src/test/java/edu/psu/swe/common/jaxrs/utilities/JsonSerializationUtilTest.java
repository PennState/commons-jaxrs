package edu.psu.swe.common.jaxrs.utilities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import edu.psu.swe.commons.jaxrs.utilities.JsonSerializationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class JsonSerializationUtilTest {
  
  private static final String HELLO_WORLD = "Hello World";
  private static final String EMPTY = "";
  private static final String EMPTY_LIST = "[]";
  private static final String EXAMPLE_OBJECT_STR = "{\"str\":\"Hello World\",\"num\":5}";
  private static final String EXAMPLE_LIST_STR = "[{\"str\":\"Hello World\",\"num\":5},{\"str\":\"Second Time\",\"num\":10}]";
  private static final SerialObject exampleObject1;
  private static final SerialObject exampleObject2;
  private static final List<SerialObject> exampleList;
  public static final String A = "A";
  public static final String B = "B";
  private static final MessageA messageA;
  private static final MessageB messageB;
  private static final String MESSAGE_A_STRING = "{\"message\":\"Hello World\",\"amessage\":\"Message A\",\"type\":\"A\"}";
  private static final String MESSAGE_B_STRING = "{\"message\":\"Hello World\",\"bmessage\":\"Message B\",\"type\":\"B\"}";
  private static final String MESSAGE_A_LIST_STR = "[{\"message\":\"Hello World\",\"amessage\":\"0\",\"type\":\"A\"}," +
    "{\"message\":\"Hello World\",\"amessage\":\"1\",\"type\":\"A\"}]";
  private static final String MESSAGE_B_LIST_STR = "[{\"message\":\"Hello World\",\"bmessage\":\"0\",\"type\":\"B\"}," +
    "{\"message\":\"Hello World\",\"bmessage\":\"1\",\"type\":\"B\"}]";
  
  static {
    exampleObject1 = new SerialObject();
    exampleObject1.setStr(HELLO_WORLD);
    exampleObject1.setNum(5);
    
    exampleObject2 = new SerialObject();
    exampleObject2.setStr("Second Time");
    exampleObject2.setNum(10);
    
    exampleList = new ArrayList<>();
    exampleList.add(exampleObject1);
    exampleList.add(exampleObject2);
    
    messageA = new MessageA();
    messageA.setMessage(HELLO_WORLD);
    messageA.setAmessage("Message A");
    
    
    messageB = new MessageB();
    messageB.setMessage(HELLO_WORLD);
    messageB.setBmessage("Message B");
  }

  
  @Test
  public void testSerializeObject() throws IOException {
    //Test Serialize Object
    Optional<String> optional = JsonSerializationUtil.serialize(exampleObject1);
    Assert.assertTrue(optional.isPresent());
    Assert.assertEquals(EXAMPLE_OBJECT_STR, optional.get());
    
    //Test Serialize List
    optional = JsonSerializationUtil.serialize(exampleList);
    Assert.assertTrue(optional.isPresent());
    Assert.assertEquals(EXAMPLE_LIST_STR, optional.get());
    
    //Test Null String
    optional = JsonSerializationUtil.serialize(null);
    Assert.assertTrue(!optional.isPresent());
  }
  
  @Test
  public void testDeserializeObject() throws IOException {
    Optional<SerialObject> optional = JsonSerializationUtil.deserialize(EXAMPLE_OBJECT_STR, SerialObject.class);
    Assert.assertTrue(optional.isPresent());
    Assert.assertEquals(exampleObject1, optional.get());
    
    //Test Null String
    optional = JsonSerializationUtil.deserialize(null, SerialObject.class);
    Assert.assertTrue(!optional.isPresent());
    
    //Test Empty String
    optional = JsonSerializationUtil.deserialize(EMPTY, SerialObject.class);
    Assert.assertTrue(!optional.isPresent());
  }
  
  @Test
  public void testDeserializeList() throws IOException {
    List<SerialObject> list = JsonSerializationUtil.deserializeList(EXAMPLE_LIST_STR, SerialObject.class);
    Assert.assertNotNull(list);
    Assert.assertEquals(exampleList, list);
    
    //Test Empty List String
    list = JsonSerializationUtil.deserializeList(EMPTY_LIST, SerialObject.class);
    Assert.assertNotNull(list);
    Assert.assertTrue(list.isEmpty());
    
    //Test Null String
    list = JsonSerializationUtil.deserializeList(null, SerialObject.class);
    Assert.assertNotNull(list);
    Assert.assertTrue(list.isEmpty());
    
    //Test Empty String
    list = JsonSerializationUtil.deserializeList(EMPTY, SerialObject.class);
    Assert.assertNotNull(list);
    Assert.assertTrue(list.isEmpty());
  }
  
  @Test
  public void testSerializationAbstractClass() throws IOException {
    //Test Serialize Object
    Optional<String> optional = JsonSerializationUtil.serialize(messageA);
    Assert.assertTrue(optional.isPresent());
    Assert.assertEquals(MESSAGE_A_STRING, optional.get());
    
    optional = JsonSerializationUtil.serialize(messageB);
    Assert.assertTrue(optional.isPresent());
    Assert.assertEquals(MESSAGE_B_STRING, optional.get());
  }
  
  @Test
  public void testDeserializationAbstractClass() throws IOException {
    BaseMessage message = JsonSerializationUtil.deserialize(MESSAGE_A_STRING, BaseMessage.class)
      .orElseThrow(()-> new IllegalArgumentException("Unable to deserialize message"));
    Assert.assertNotNull(message);
    Assert.assertTrue(message instanceof MessageA);
    Assert.assertEquals(A, message.getMessageType());
    Assert.assertEquals(HELLO_WORLD, message.getMessage());
    Assert.assertEquals("Message A", ((MessageA)message).getAmessage());
    
    message = JsonSerializationUtil.deserialize(MESSAGE_B_STRING, BaseMessage.class)
      .orElseThrow(()-> new IllegalArgumentException("Unable to deserialize message"));
    Assert.assertNotNull(message);
    Assert.assertTrue(message instanceof MessageB);
    Assert.assertEquals(B, message.getMessageType());
    Assert.assertEquals(HELLO_WORLD, message.getMessage());
    Assert.assertEquals("Message B", ((MessageB)message).getBmessage());
  }
  
  @Test
  public void testDeserializationAbstractClassList() throws IOException {
    List<BaseMessage> list = JsonSerializationUtil.deserializeList(MESSAGE_A_LIST_STR, BaseMessage.class);
    Assert.assertNotNull(list);
    Assert.assertTrue(!list.isEmpty());
    for(int i = 0; i < list.size(); i++) {
      BaseMessage message = list.get(i);
      Assert.assertNotNull(message);
      Assert.assertEquals(HELLO_WORLD, message.getMessage());
      Assert.assertTrue(message instanceof MessageA);
      MessageA messageA = (MessageA)message;
      Assert.assertEquals(Integer.toString(i), messageA.getAmessage());
    }
    
    list = JsonSerializationUtil.deserializeList(MESSAGE_B_LIST_STR, BaseMessage.class);
    Assert.assertNotNull(list);
    Assert.assertTrue(!list.isEmpty());
    for(int i = 0; i < list.size(); i++) {
      BaseMessage message = list.get(i);
      Assert.assertNotNull(message);
      Assert.assertEquals(HELLO_WORLD, message.getMessage());
      MessageB messageB = (MessageB)message;
      Assert.assertTrue(message instanceof MessageB);
      Assert.assertEquals(Integer.toString(i), messageB.getBmessage());
    }
  }
  
  
  
  @Data
  private static class SerialObject implements Serializable {
    
    private static final long serialVersionUID = -1325200022332517446L;

    @XmlElement
    private String str;
    
    @XmlElement
    private int num;
    
  }
  
  //Abstract Class for testing
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @XmlRootElement
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
                include = JsonTypeInfo.As.EXISTING_PROPERTY,
                property = "type")
  @JsonSubTypes(value={
      @JsonSubTypes.Type(value = MessageA.class, name = MessageA.TYPE),
      @JsonSubTypes.Type(value = MessageB.class, name = MessageB.TYPE),
  })
  @XmlAccessorType(XmlAccessType.NONE)
  private static abstract class BaseMessage implements Serializable {
    private static final long serialVersionUID = -4357328435581976053L;
    
    @XmlElement
    private String message;
    
    @XmlElement(name = "type")
    public abstract String getMessageType();
  }
  
  //Implementation of abstract class 
  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper=true)
  @ToString(callSuper=true)
  @XmlRootElement
  @XmlAccessorType(XmlAccessType.NONE)
  //@JsonTypeName(MessageA.TYPE)
  private static class MessageA extends BaseMessage {
    private static final long serialVersionUID = 2065948225257774025L;
    public static final String TYPE = A;
    
    @XmlElement
    private String amessage;
    
    @Override
    @XmlElement(name="type")
    public String getMessageType() {
      return TYPE;
    }
  }
  
  //Other implementation of abstract class
  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper=true)
  @ToString(callSuper=true)
  @XmlRootElement
  @XmlAccessorType(XmlAccessType.NONE)
  //@JsonTypeName(MessageB.TYPE)
  private static class MessageB extends BaseMessage {
    private static final long serialVersionUID = 1983452033783170454L;
    public static final String TYPE = B;

    @XmlElement
    private String bmessage;
    
    @Override
    @XmlElement(name="type")
    public String getMessageType() {
      return TYPE;
    }
  }
}

