package edu.psu.swe.commons.jaxrs.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.psu.swe.commons.jaxrs.server.ObjectMapperContextResolver;

public class JsonSerializationUtil {
  
private static final ObjectMapper mapper;
  
  static {
    ObjectMapperContextResolver resolver = new ObjectMapperContextResolver();
    mapper = resolver.getContext(null);
  }
  
  public static Optional<String> serialize(Object obj) throws IOException {
    if (obj == null) {
      return Optional.empty();
    }
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, obj);
    return Optional.ofNullable(writer.toString());
  }
  
  public static <T> Optional<T> deserialize(String json, Class<T> clazz) throws IOException {
    if (json == null || json.trim().isEmpty()) {
      return Optional.empty();
    }
    @SuppressWarnings("unchecked")
    T obj = (T) mapper.readValue(json, clazz);
    return Optional.of(obj);
  }
  
  public static <T> Optional<List<T>> deserializeList(String json, Class<T> clazz) throws IOException {
    if (json == null || json.trim().isEmpty()) {
      return Optional.empty();
    }
    JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
    List<T> list = mapper.readValue(json, type);
    if (list.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(list);
  }

}

