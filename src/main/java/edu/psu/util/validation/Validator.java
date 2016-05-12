package edu.psu.util.validation;

/*
 * The Pennsylvania State University © 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.util.Properties;
import java.util.Set;
import java.util.HashSet;

import java.util.Collections;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.exception.UnableToLoadException;

public final class Validator
{
  private Validator()
  {}

  private static final Logger logger_ = LoggerFactory.getLogger(Validator.class);

  private static Set<String> dirtyWords_ = null;
  private static String validEmailAddressRegex_ = null;
  private static String dirtyWordsFile_;
  private static Pattern validEmailRegexPattern_ = null;

  public static final int MIN_PASSWORD_LENGTH = 8;

  public static enum PasswordCheckResult { NO_ALPHA_CHARACTERS("Validation: Passwords must contain at least one alphabetic character"), 
                                           NO_NUMERIC_CHARACTERS("Validation: Passwords must contain at least one numeric character"), 
                                           HAS_SPACES("Validation: Passwords cannot contain spaces"), 
                                           HAS_QUOTES("Validation: Passwords cannot contain quotes"), 
                                           HAS_SPECIAL_CHARACTERS("Validation: Passwords cannot contain certain special characters, such as & ( ) | < or >."), 
                                           HAS_EMBEDDED_USERID("Passwords cannot contain your userid"),
                                           NO_EQUALITY_VALIDATION_MATCH("Validation: The new password and its verification did not match"),
                                           TOO_SHORT("Validation: Passwords must be at least " + MIN_PASSWORD_LENGTH + " characters"),
                                           SAME_AS_EXISTING("Validation: The new password must be different than your current password"),
                                           SUCCESS("Validation: The password you selected is valid");

     PasswordCheckResult(String errorMessage)
     {
       errorMessage_ = errorMessage;
     }

     private String errorMessage_;

     public String message()
     {
       return errorMessage_;
     }

     public String toString()
     {
       return message();
     }
  }

  public static void setEmailValidationRegex(String regex) 
  {
    validEmailAddressRegex_ = regex;

    if (validEmailAddressRegex_ != null)
    {
      validEmailRegexPattern_ = Pattern.compile(validEmailAddressRegex_);
    }
  }
  
  public static boolean isValidEmailAddr(String addr) throws UnableToLoadException
  {
    if (addr == null)
    {
      return false;
    }

    if(validEmailAddressRegex_  == null)
    {
      loadEmailRegex();
    }

    Matcher m = validEmailRegexPattern_.matcher(addr);

    return m.matches();
  }

  public static void setDirtyWordFile(String file) throws UnableToLoadException
  {
    dirtyWordsFile_ = file;

    loadDirtyWords();
  }

  public static boolean passesDirtyWordCheck(String word) throws UnableToLoadException
  {
    final String delimiters = "[\\:\\_\\-\\.\\/\\,\\|]";
    String lower = word.toLowerCase(); 

    if (lower.contains("fuck"))
    {
      return false;
    }

    if (dirtyWords_ == null)
    {
      loadDirtyWords();
    }

    String [] words = lower.split(delimiters);
    
    for (String s : words)
    {
      if(dirtyWords_.contains(s))
      {
        return false;
      }
    }

    return true;
  }

  public static Set<String> getDirtyWords()
  {
    return Collections.unmodifiableSet(dirtyWords_);
  }

  public static PasswordCheckResult passesPasswordStrengthCheck(String userID, String newPassword, String oldPassword)
  {
  //                                         HAS_SPECIAL_CHARACTERS("Passwords cannot contain certain special characters, such as & ( ) | < or >."), 
  //                                         NO_EQUALITY_VALIDATION_MATCH("The new password and its verification did not match"),

    if (newPassword.length() < MIN_PASSWORD_LENGTH)
    {
      return PasswordCheckResult.TOO_SHORT;
    }

    if (oldPassword != null && newPassword.equals(oldPassword)) 
    {
      return PasswordCheckResult.SAME_AS_EXISTING;
    }

    if (newPassword.contains(" ") || newPassword.contains("\t"))
    {
      return PasswordCheckResult.HAS_SPACES;
    }

    if (newPassword.contains("\"") || newPassword.contains("\'") || newPassword.contains("`"))
    {
      return PasswordCheckResult.HAS_QUOTES;
    }

    if (userID != null && !userID.isEmpty())
    {
      if (newPassword.toLowerCase().contains(userID.toLowerCase()))
      {
        return PasswordCheckResult.HAS_EMBEDDED_USERID;
      }
    }
    
    Pattern alpha = Pattern.compile("[a-zA-Z]");
    Matcher matcher = alpha.matcher(newPassword);
    if (!matcher.find())
    {
      return PasswordCheckResult.NO_ALPHA_CHARACTERS;
    }

    //These chars are bad only because they match shell scripting
    //characters and were problematic in certain Sysadmin tasks
    Pattern badChars = Pattern.compile("[&()|<>]");
    matcher = badChars.matcher(newPassword);

    if (matcher.find())
    {
      return PasswordCheckResult.HAS_SPECIAL_CHARACTERS;
    }

    //System.out.println("NO special on first check");
    //Get rid of extended ascii and invisible chars
    //Pattern visible = Pattern.compile("[\\u0000-\\u001F]|[\\u007F-\\u03FF]");
    //Pattern visible = Pattern.compile("[a-zA-Z0-9!#$%*+,-\\./:;=?@\\^{}\\|~]");
    //matcher = visible.matcher(newPassword);

    //if (matcher.find())
    //  return PasswordCheckResult.HAS_SPECIAL_CHARACTERS;

    Pattern numeric = Pattern.compile("[0-9]");
    matcher = numeric.matcher(newPassword);

    if (!matcher.find())
    {
      return PasswordCheckResult.NO_NUMERIC_CHARACTERS;
    }

    return PasswordCheckResult.SUCCESS;
  }

  private static void loadDirtyWords() throws UnableToLoadException
  {
    BufferedReader wordReader = null;

    try
    {
      if (dirtyWordsFile_.isEmpty())
      {
        String commonLib = System.getenv("COMMON_LIB_ROOT");
        if (commonLib == null)
        {
          throw new UnableToLoadException("COMMON_LIB_ROOT is not defined");
        }

        dirtyWordsFile_ = commonLib + "config/dirty_words.txt";
      }

      InputStream stream = Validator.class.getClassLoader().getResourceAsStream(dirtyWordsFile_);

      if (stream == null)
      {
        logger_.error("Resource " + dirtyWordsFile_ + " was not found, unable to continue");
        throw new UnableToLoadException(dirtyWordsFile_ + " was not found, unable to continue");
      }

      DataInputStream in = new DataInputStream(stream);
      wordReader = new BufferedReader(new InputStreamReader(in));

     
      dirtyWords_ = new HashSet<String>();

      String word;
      while ((word = wordReader.readLine()) != null)
      {
        if (!word.isEmpty() && word.charAt(0) != '#')
        {
          dirtyWords_.add(word.trim().toLowerCase());
        }
      }
    }
    catch(IOException ioe)
    {
      logger_.error("IOException caugth: " + ioe);
      throw new UnableToLoadException("Couldn't load the regex", ioe);
    }
    finally
    {
      try
      {
        if (wordReader != null)
        {
          wordReader.close();
        }
      }
      catch(Exception e)
      {
        logger_.error("Exception caught closing the BufferedReader (No programatic impact): " + e);
      } 
    }
  }

  private static void loadEmailRegex() throws UnableToLoadException
  {
    InputStream stream = null;
    try
    {
      if (validEmailAddressRegex_ == null)
      {
        Properties props = new Properties();
        stream = Validator.class.getClassLoader().getResourceAsStream("config/validation.config");
    
        props.load(stream);

        validEmailAddressRegex_ = (String)props.get("email.validation.regex");
        validEmailRegexPattern_ = Pattern.compile(validEmailAddressRegex_);
      }
    }
    catch(IOException ioe)
    {
      logger_.error("Failed to load the regex: " + ioe);
      throw new UnableToLoadException("Couldn't load the regex", ioe);
    }
    finally
    {
      try
      {
        if (stream != null)
        {
          stream.close();
        }
      }
      catch(Exception e)
      {
        logger_.error("Exception caught closing the BufferedReader (No programatic impact): " + e);
        //Don't Care
      } 
    }
  }  
}
