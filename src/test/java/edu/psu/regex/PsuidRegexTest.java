/*
 * 
 */
package edu.psu.regex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PsuidRegexTest {
  
  private static final Pattern PSU_ID_PATTERN = Pattern.compile(RegexConstants.PSU_ID_REGEX);

  @SuppressWarnings("unused")
  private Object[] parametersForTestPsuidRegexWithValidPsuid() {
    List<String> parameters = new ArrayList<String>();
    Random random = new Random(new Date().getTime());

    // Get the disallowed psuid numbers
    HashSet<String> disallowed = new HashSet<String>(Arrays.asList(parametersForTestPsuidRegexWithInvalidPsuid()));

    // Create 50 random and valid psuids that aren't in the disallowed set
    int idNumberCount = 0;
    while(idNumberCount < 50) {
      String idNumber = "9";
      for(int i = 0; i < 8; i++) {
        idNumber += (int) (random.nextDouble() * 10);
      }
      if(!disallowed.contains(idNumber)) {
        parameters.add(idNumber);
        idNumberCount += 1;
      }
    }
    
    return (Object[]) parameters.toArray();
  }
  
  @Test
  @Parameters
  public void testPsuidRegexWithValidPsuid(String idNumber) {
    assertTrue(isStringMatchingPsuidRegex(idNumber));
  }
  
  /*
   * These parameters are the complete list of disallowed psuid numbers as defined
   * by the document at: <a href="https://wikispaces.psu.edu/display/IdSExternal/IAM+CIDR+Functionality" />
   * 
   * @return the list of disallowed psuid numbers
   */
  private String[] parametersForTestPsuidRegexWithInvalidPsuid() {
    List<String> parameters = new ArrayList<String>();
    
    // Add numbers that start with 99999999
    for(int i = 0; i <= 9; i++) {
      String idNumber = "99999999" + i;
      parameters.add(idNumber);
    }
    
    // Add numbers that start with 9 and have the same number in digits 2 through 9
    for(int i = 0; i <= 9; i++) {
      String idNumber = "9" + i + i + i + i + i + i + i + i;
      parameters.add(idNumber);
    }
    
    // Add numbers that don't start with a 9
    for(int i = 0; i < 9; i++) {
      String idNumber = "" + i + "12345678";
      parameters.add(idNumber);
    }
    
    // Add everyone's favorite id number
    parameters.add("987654321");
    
    return parameters.toArray(new String[0]);
  }
  
  /*
   * 
   */
  @Test
  @Parameters
  public void testPsuidRegexWithInvalidPsuid(String idNumber) {
    assertFalse(isStringMatchingPsuidRegex(idNumber));
  }
  
  @Test
  public void testPsuidRegexWithShortPsuid() {
    assertFalse(isStringMatchingPsuidRegex("98765432"));
  }
  
  @Test
  public void testPsuidRegexWithLongPsuid() {
    assertFalse(isStringMatchingPsuidRegex("9876543210"));
  }
  
  @Test
  public void testPsuidRegexWithAString() {
    assertFalse(isStringMatchingPsuidRegex("abcdefghi"));
  }
  
  @Test
  public void testPsuidRegexWithAnEmbeddedCharacter() {
    assertFalse(isStringMatchingPsuidRegex("9a1234567"));
  }
  
  @Test
  public void testPsuidRegexWithEmbeddedPsuid() {
    assertFalse(isStringMatchingPsuidRegex("abc912345678xyz"));
  }
  
  @Test
  public void testPsuidRegexWithLeadingWhitespace() {
    assertFalse(isStringMatchingPsuidRegex(" 912345678"));
  }
  
  @Test
  public void testPsuidRegexWithTrailingWhitespace() {
    assertFalse(isStringMatchingPsuidRegex("912345678 "));
  }
  
  @Test
  public void testPsuidRegexWithLeadingAndTrailingWhitespace() {
    assertFalse(isStringMatchingPsuidRegex(" 912345678 "));
  }
  
  private boolean isStringMatchingPsuidRegex(String idNumber) {
    Matcher matcher = PSU_ID_PATTERN.matcher(idNumber);
    return matcher.matches();
  }
  
}
