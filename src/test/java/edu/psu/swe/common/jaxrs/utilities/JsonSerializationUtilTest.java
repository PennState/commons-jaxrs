package edu.psu.swe.common.jaxrs.utilities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlElement;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.swe.commons.jaxrs.utilities.JsonSerializationUtil;
import lombok.Data;

public class JsonSerializationUtilTest {
  
  private static final String EMPTY = "";
  private static final String EMPTY_LIST = "[]";
  private static final String EXAMPLE_OBJECT_STR = "{\"str\":\"Hello World\",\"num\":5}";
  private static final String EXAMPLE_LIST_STR = "[{\"str\":\"Hello World\",\"num\":5},{\"str\":\"Second Time\",\"num\":10}]";
  private static final SerialObject exampleObject1;
  private static final SerialObject exampleObject2;
  private static final List<SerialObject> exampleList;
  
  static {
    exampleObject1 = new SerialObject();
    exampleObject1.setStr("Hello World");
    exampleObject1.setNum(5);
    
    exampleObject2 = new SerialObject();
    exampleObject2.setStr("Second Time");
    exampleObject2.setNum(10);
    
    exampleList = new ArrayList<>();
    exampleList.add(exampleObject1);
    exampleList.add(exampleObject2);
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
    Optional<List<SerialObject>> optional = JsonSerializationUtil.deserializeList(EXAMPLE_LIST_STR, SerialObject.class);
    Assert.assertTrue(optional.isPresent());
    Assert.assertEquals(exampleList, optional.get());
    
    //Test Empty List String
    optional = JsonSerializationUtil.deserializeList(EMPTY_LIST, SerialObject.class);
    Assert.assertTrue(!optional.isPresent());
    
    //Test Null String
    optional = JsonSerializationUtil.deserializeList(null, SerialObject.class);
    Assert.assertTrue(!optional.isPresent());
    
    //Test Empty String
    optional = JsonSerializationUtil.deserializeList(EMPTY, SerialObject.class);
    Assert.assertTrue(!optional.isPresent());
  }
  
  @Data
  private static class SerialObject implements Serializable {
    
    private static final long serialVersionUID = -1325200022332517446L;

    @XmlElement
    private String str;
    
    @XmlElement
    private int num;
    
  }
}

