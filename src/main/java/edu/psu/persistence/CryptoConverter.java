package edu.psu.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Stream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import edu.psu.util.string.StringUtils;

@Converter
public class CryptoConverter implements AttributeConverter<String, String>
{
  private static final String ALGORITHM = "AES/CBC/NoPadding";
  private static final int BLOCK_SIZE = 16;
  private static Key KEY = null;
  
  private byte [] baseKey = null;

  public CryptoConverter() throws IOException
  {
    Path path = Paths.get(System.getProperty("jboss.server.config.dir"), "app_key");
    //The stream hence file will also be closed here
    try(Stream<String> lines = Files.lines(path))
    {
       Optional<String> hasPassword = lines.findFirst();
       if (hasPassword.isPresent())
       {
         baseKey = hasPassword.get().getBytes("UTF-8");
         KEY = new SecretKeySpec(baseKey, "AES");
       }
       else
       {
         throw new IllegalStateException("No ecryption key can be found in " + System.getProperty("jboss.server.config.dir") + "/app_key");
       }
    }
  }
  
  @Override
  public String convertToDatabaseColumn(String value)
  {
    // do some encryption
    String encryptedValue = "";
    Cipher c;
    try
    {
      c = Cipher.getInstance(ALGORITHM, "SunJCE");
      c.init(Cipher.ENCRYPT_MODE, KEY);
      
      int blocks = (value.length() / BLOCK_SIZE) + 1;

      int calculatedSize = BLOCK_SIZE * blocks;
      int finalLength = calculatedSize  - value.length();
      
      String paddedValue = StringUtils.padRight(value, finalLength);
      
      encryptedValue = Base64.getEncoder().encodeToString((c.doFinal(paddedValue.getBytes())));
    }
    catch (NoSuchAlgorithmException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchProviderException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return encryptedValue;
  }

  @Override
  public String convertToEntityAttribute(String dbData)
  {
    // do some decryption
    String decrypted = "";

    Cipher c;
    try
    {
      c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.DECRYPT_MODE, KEY);
      decrypted = new String(c.doFinal(Base64.getDecoder().decode(dbData)));
    }
    catch (NoSuchAlgorithmException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return decrypted.trim();
  }
}