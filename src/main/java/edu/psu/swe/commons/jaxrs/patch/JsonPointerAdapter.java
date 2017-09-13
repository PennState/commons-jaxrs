package edu.psu.swe.commons.jaxrs.patch;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JsonPointerAdapter extends XmlAdapter<String, JsonPointer>{

  @Override
  public JsonPointer unmarshal(String v) throws Exception {
    if (v == null) {
      return null;
    }
    return new JsonPointer(v);
  }

  @Override
  public String marshal(JsonPointer v) throws Exception {
    if (v == null) {
      return null;
    }
    return v.toString();
  }

}
