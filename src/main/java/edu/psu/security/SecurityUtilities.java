package edu.psu.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.Digest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtilities
{
  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtilities.class);
  
  private static Digest messageDigest_;
  
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
}
