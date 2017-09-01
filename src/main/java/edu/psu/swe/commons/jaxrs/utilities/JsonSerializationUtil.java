package edu.psu.swe.commons.jaxrs.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.psu.swe.commons.jaxrs.server.ObjectMapperContextResolver;

public class JsonSerializationUtil {
  
private static final ObjectMapper mapper;
  
  static {
    ObjectMapperContextResolver resolver = new ObjectMapperContextResolver();
    mapper = resolver.getContext(null);
  }
  
  public static String serialize(Object obj) throws IOException {
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, obj);
    return writer.toString();
  }
  
  public static <T> T deserialize(String json, T t) throws IOException {
    @SuppressWarnings("unchecked")
    T obj = (T) mapper.readValue(json, t.getClass());
    return obj;
  }
  
  public static <T> List<T> deserializeList(String json, Class<T> clazz) throws IOException {
    JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
    List<T> list = mapper.readValue(json, type);
    return list;
  }

}
