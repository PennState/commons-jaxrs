package edu.psu.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.Digest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtilities
{
  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtilities.class);
  
  //This is a sparse set.  All visually confusing entries have been removed
  static final char [] LOWER_CASE_VALUES = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  static final char [] UPPER_CASE_VALUES = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
  static final char [] NUMBERS           = { '2', '3', '4', '6', '7', '8', '9' };
  
  static final char [][] RANDOM_VALUES = { LOWER_CASE_VALUES, UPPER_CASE_VALUES, NUMBERS };
  
  private static Digest messageDigest_;
  
  private static final Random RANDOM = new Random(new Date().getTime());
  
  public static String compressAndHashConvertString(String toBeHashed) throws UnsupportedEncodingException
  {
    LOGGER.debug("Encoding security answer: " + toBeHashed);
    String cleanVal = toBeHashed.replaceAll(" ", "").toLowerCase().trim();
    LOGGER.debug("Without whitespace and forced to lowercase: " + cleanVal);

    return hashConvertString(cleanVal);
  }
  
  public static String hashConvertString(String toBeHashed) throws UnsupportedEncodingException
  {
    byte[] hash = new byte[messageDigest_.getDigestSize()];
    messageDigest_.update(toBeHashed.getBytes("UTF-8"), 0, toBeHashed.length());
    messageDigest_.doFinal(hash, 0);
    byte[] encodedHash = Base64.encodeBase64(hash);
    
    String hashValue = new String(encodedHash, "US-ASCII");
    LOGGER.debug("Hashed and Base64 encoded: " + hashValue);
    
    return hashValue;
  }
  
  public static String generateRandomString(int size)
  {
    StringBuilder password = new StringBuilder();
    
    int list = -1;
    int index = -1;
    
    for (int i = 0; i < size; ++i)
    {
      list = RANDOM.nextInt();
      
      if (list == Integer.MIN_VALUE)
      {
        ++list;
      }
      
      list = Math.abs(list) % 3;
      
      index = RANDOM.nextInt();
      
      if (index == Integer.MIN_VALUE)
      {
        ++index;
      }
          
      index = Math.abs(index) % RANDOM_VALUES[list].length;   
          
      password.append(RANDOM_VALUES[list][index]);
    }
    
    return password.toString();
  }
}
