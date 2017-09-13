package edu.psu.swe.commons.jaxrs.referenceable;

import java.io.IOException;

import javax.enterprise.inject.spi.CDI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReferenceableSerializer extends JsonSerializer<Referenceable> {

  private static final Logger LOG = LoggerFactory.getLogger(ReferenceableSerializer.class);

  @Override
  public void serialize(Referenceable value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    LOG.debug("Serializing list of referenceable into referenceable atom links");

    ReferenceableAtomLink ral = value.convertToReferenceableType();
    String basePath = null;
    try {
      RequestBean requestBean = CDI.current()
                                   .select(RequestBean.class)
                                   .get();
      basePath = requestBean.getRequest()
                            .getScheme()
          + "://" + requestBean.getRequest()
                               .getServerName()
          + ":" + requestBean.getRequest()
                             .getServerPort()
          + requestBean.getRequest()
                       .getContextPath()
          + "/resources";
    } catch (IllegalStateException ex) {
      log.warn("no access to CDI");
    }
    gen.writeStartObject();
    gen.writeStringField("id", ral.getId());
    gen.writeStringField("type", ral.getMimeType());
    if (basePath != null) {
      gen.writeStringField("href", basePath + ral.getHyperlink());
    }
    gen.writeEndObject();

  }

  @Override
  public void serializeWithType(Referenceable value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
    LOG.debug("type Serializing list of referenceable into referenceable atom links");

    ReferenceableAtomLink ral = value.convertToReferenceableType();
    String basePath = null;
    try {
      RequestBean requestBean = CDI.current()
                                   .select(RequestBean.class)
                                   .get();
      basePath = requestBean.getRequest()
                                   .getScheme()
          + "://" + requestBean.getRequest()
                               .getServerName()
          + ":" + requestBean.getRequest()
                             .getServerPort()
          + requestBean.getRequest()
                       .getContextPath()
          + "/resources";
    } catch (IllegalStateException ex) {
      log.warn("no access to CDI");
    }
    gen.writeStartObject();
    gen.writeStringField("id", ral.getId());
    gen.writeStringField("type", ral.getMimeType());
    if (basePath != null) {
      gen.writeStringField("href", basePath + ral.getHyperlink());
    }
    gen.writeEndObject();
  }

}
