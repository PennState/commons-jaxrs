package edu.psu.swe.commons.jaxrs;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalFormatAdapter extends XmlAdapter<String, BigDecimal>
{
  @Override
  public String marshal(BigDecimal value) throws Exception
  {
    if (value != null)
    {
      return value.toString();
    }
    return null;
  }

  @Override
  public BigDecimal unmarshal(String s) throws Exception
  {
    return new BigDecimal(s);
  }
}
