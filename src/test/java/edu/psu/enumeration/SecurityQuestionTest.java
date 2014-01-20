/**
 * 
 */
package edu.psu.enumeration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author stevemoyer
 *
 */
@RunWith(JUnitParamsRunner.class)
public class SecurityQuestionTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Provides parameters for tests that require a pretty string
   * @return
   */
  @SuppressWarnings("unused")
  private Object[] getPrettyStrings() {
    List<Object> parametersList = new ArrayList<Object>();
    
    // Add all the pretty strings
    for(SecurityQuestion question: SecurityQuestion.values()) {
      Object[] parameters = new Object[4];
      String name = question.name();
      parameters[0] = "Security question (pretty string): " + name;
      parameters[1] = question;
      parameters[2] = question.toString();
      parameters[3] = Boolean.TRUE;
      parametersList.add(parameters);
    }
    
    // Make a fake pretty string
    Object[] parameters = new Object[4];
    String fakePrettyString = "Is this a fake security question?";
    parameters[0] = "Security Question (fake pretty string): " + fakePrettyString;
    parameters[2] = fakePrettyString;
    parameters[3] = Boolean.FALSE;
    parametersList.add(parameters);
    
    return parametersList.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getPrettyStrings")
  public void testFromPrettyString(String testName, SecurityQuestion expected, String prettyString, Boolean valid) {
    SecurityQuestion actual = SecurityQuestion.fromPrettyString(prettyString);
    if(valid) {
      assertEquals(expected, actual);
    } else {
      assertNull(actual);
    }
  }

  /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#toString()}.
   */
  @Test
  @Parameters(method = "getPrettyStrings")
  public void testToString(String testName, SecurityQuestion question, String expected, Boolean valid) {
    try {
      String actual = question.toString();
      assertTrue(valid);
      assertEquals(expected, actual);
    } catch(NullPointerException e) {
      assertFalse(valid);
    } catch(Throwable t) {
      fail("Should not have caught: " + t.getClass().getName());
    }
  }

  /**
   * Provides parameters for tests that require a lowercase pretty string
   * @return
   */
  @SuppressWarnings("unused")
  private Object[] getLowercasePrettyStrings() {
    List<Object> parametersList = new ArrayList<Object>();
    
    // Add all the pretty strings
    for(SecurityQuestion question: SecurityQuestion.values()) {
      Object[] parameters = new Object[4];
      String name = question.name();
      parameters[0] = "Security question (lowercase pretty string): " + name;
      parameters[1] = question;
      parameters[2] = question.toString().toLowerCase();
      parameters[3] = Boolean.TRUE;
      parametersList.add(parameters);
    }
    
    // Make a fake pretty string
    Object[] parameters = new Object[4];
    String fakePrettyString = "Is this a fake security question?";
    parameters[0] = "Security Question (fake lowercase pretty string): " + fakePrettyString;
    parameters[2] = fakePrettyString.toLowerCase();
    parameters[3] = Boolean.FALSE;
    parametersList.add(parameters);
    
    return parametersList.toArray();
  }
  
    /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#enumValue(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getLowercasePrettyStrings")
  public void testEnumValueWithLowercasePrettyString(String testName, SecurityQuestion expected, String prettyString, Boolean valid) {
    try {
      SecurityQuestion actual = SecurityQuestion.enumValue(prettyString);
      assertTrue(valid);
      assertEquals(expected, actual);
    } catch(IllegalArgumentException e) {
      assertFalse(valid);
    } catch(Throwable t) {
      fail("Should not have caught: " + t.getClass().getName());
    }
  }
  
  /**
   * Provides parameters for tests that require a lowercase pretty string
   * @return
   */
  @SuppressWarnings("unused")
  private Object[] getNames() {
    List<Object> parametersList = new ArrayList<Object>();
    
    // Add all the pretty strings
    for(SecurityQuestion question: SecurityQuestion.values()) {
      Object[] parameters = new Object[4];
      String name = question.name();
      parameters[0] = "Security question (name): " + name;
      parameters[1] = question;
      parameters[2] = question.name();
      parameters[3] = Boolean.TRUE;
      parametersList.add(parameters);
    }
    
    // Make a fake pretty string
    Object[] parameters = new Object[4];
    String fakeName = "IS_THIS_A_FAKE_NAME";
    parameters[0] = "Security Question (fake name): " + fakeName;
    parameters[2] = fakeName;
    parameters[3] = Boolean.FALSE;
    parametersList.add(parameters);
    
    return parametersList.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#enumValue(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getNames")
  public void testEnumValueWithName(String testName, SecurityQuestion expected, String name, Boolean valid) {
    try {
      SecurityQuestion actual = SecurityQuestion.enumValue(name);
      assertTrue(valid);
      assertEquals(expected, actual);
    } catch(IllegalArgumentException e) {
      assertFalse(valid);
    } catch(Throwable t) {
      fail("Should not have caught: " + t.getClass().getName());
    }
  }
  
  /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#enumValue(java.lang.String)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEnumValueWithNull() {
    SecurityQuestion.enumValue(null);
  }
  
  /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#enumValue(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getPrettyStrings")
  public void testEnumValueWithPrettyString(String testName, SecurityQuestion expected, String prettyString, Boolean valid) {
    try {
      SecurityQuestion actual = SecurityQuestion.enumValue(prettyString);
      assertTrue(valid);
      assertEquals(expected, actual);
    } catch(IllegalArgumentException e) {
      assertFalse(valid);
    } catch(Throwable t) {
      fail("Should not have caught: " + t.getClass().getName());
    }
  }
  
  /**
   * Provides parameters for tests that require a lowercase pretty string
   * @return
   */
  @SuppressWarnings("unused")
  private Object[] getUppercasePrettyStrings() {
    List<Object> parametersList = new ArrayList<Object>();
    
    // Add all the pretty strings
    for(SecurityQuestion question: SecurityQuestion.values()) {
      Object[] parameters = new Object[4];
      String name = question.name();
      parameters[0] = "Security question (uppercase pretty string): " + name;
      parameters[1] = question;
      parameters[2] = question.toString().toUpperCase();
      parameters[3] = Boolean.TRUE;
      parametersList.add(parameters);
    }
    
    // Make a fake pretty string
    Object[] parameters = new Object[4];
    String fakePrettyString = "Is this a fake security question?";
    parameters[0] = "Security Question (fake uppercase pretty string): " + fakePrettyString;
    parameters[2] = fakePrettyString.toUpperCase();
    parameters[3] = Boolean.FALSE;
    parametersList.add(parameters);
    
    return parametersList.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.enumeration.SecurityQuestion#enumValue(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getUppercasePrettyStrings")
  public void testEnumValueWithUppercasePrettyString(String testName, SecurityQuestion expected, String prettyString, Boolean valid) {
    try {
      SecurityQuestion actual = SecurityQuestion.enumValue(prettyString);
      assertTrue(valid);
      assertEquals(expected, actual);
    } catch(IllegalArgumentException e) {
      assertFalse(valid);
    } catch(Throwable t) {
      fail("Should not have caught: " + t.getClass().getName());
    }
  }

}
