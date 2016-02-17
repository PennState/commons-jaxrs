package edu.psu.persistence;

import org.junit.Assert;
import org.junit.Test;

public class CryptoConverterTest
{
  private static final String KEY_STRING = "Mydoghavehasfleasikolpwhwlowhsiw";
  
  private static final String PLAIN_TEXT_EQUAL_BLOCKSIZE = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient.";
  private static final String PLAIN_TEXT_NEEDS_PADDING = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec q";
  
  private CryptoConverter converter = new CryptoConverter(KEY_STRING.getBytes());

  @Test
  public void testConvertToEntityAttributeNoPaddingRequired()
  {
    String encrypted = converter.convertToDatabaseColumn(PLAIN_TEXT_EQUAL_BLOCKSIZE);    
    String decrypted = converter.convertToEntityAttribute(encrypted);    
    Assert.assertTrue(decrypted.equals(PLAIN_TEXT_EQUAL_BLOCKSIZE));
  }

  @Test
  public void testConvertToEntityAttributePaddingRequired()
  {
    String encrypted = converter.convertToDatabaseColumn(PLAIN_TEXT_NEEDS_PADDING);    
    String decrypted = converter.convertToEntityAttribute(encrypted);    
    Assert.assertTrue(decrypted.equals(PLAIN_TEXT_NEEDS_PADDING));
  }
}
