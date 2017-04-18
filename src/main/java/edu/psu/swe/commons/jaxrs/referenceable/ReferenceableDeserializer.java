package edu.psu.swe.commons.jaxrs.referenceable;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

public class ReferenceableDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

  private static final Logger LOG = LoggerFactory.getLogger(ReferenceableDeserializer.class);

  private Class targetClass;

  public ReferenceableDeserializer() {
    super();
  }

  public ReferenceableDeserializer(Class targetClass) {
    super();
    this.targetClass = targetClass;
  }

  @Override
  public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    LOG.info("Deserializing referenceable atom links into referenceable");

    Referenceable r;
    String href = null;

    ObjectCodec oc = jsonParser.getCodec();
    JsonNode node = oc.readTree(jsonParser);
    try {
      r = (Referenceable) targetClass.newInstance();

      ReferenceableAtomLink al = new ReferenceableAtomLink();
      al.setId(node.get("id")
                   .asText());

      if (node.get("href") != null) {
        href = node.get("href")
                   .asText();
      }

      r.loadReferenceableType(al);

    } catch (InstantiationException | IllegalAccessException e) {
      LOG.error("Error creating referenceable: " + e.getMessage(), e);
      throw new JsonGenerationException(e.getMessage());
    }

    return r;
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    LOG.debug("Creating contextual referenceable deserialzer");

    return new ReferenceableDeserializer(ctxt.getContextualType()
                                             .getRawClass());
  }

}