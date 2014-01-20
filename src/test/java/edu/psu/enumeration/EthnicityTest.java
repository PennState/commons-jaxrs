/**
 * 
 */
package edu.psu.enumeration;

import static edu.psu.enumeration.Constants.BARE_QUESTION_LOOKUP_REGEX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author ses44
 *
 */
@RunWith(JUnitParamsRunner.class)
public class EthnicityTest
{

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }

  /**
   * Test method for {@link edu.psu.fps.model.enums.Ethnicity#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringNull()
  {
    Ethnicity p = Ethnicity.fromPrettyString(null);
    assertNull(p);
  }

  /**
   * Test method for {@link edu.psu.fps.model.enums.Ethnicity#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringEmptyString()
  {
    String s = "";
    Ethnicity p = Ethnicity.fromPrettyString(s);
    assertEquals(p, Ethnicity.NO_RESPONSE);
  }
  
  @SuppressWarnings("unused")
  private Object[] getPrettyStrings() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (Ethnicity e : Ethnicity.values())
    {
      Object [] params = new Object[4];
      String name = e.name();
      params[0] = "Ethnicity " + name;
      params[1] = e.toString();
      params[2] = e;
      params[3] = Boolean.TRUE;
      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Ethnicity#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getPrettyStrings")
  public void testFromPrettyStringValid(String testName, String prettyEthnicity, Ethnicity expected, boolean passes)
  {
    Ethnicity p1 = Ethnicity.fromPrettyString(prettyEthnicity);
    assertEquals(p1, expected);
  }
  
  @SuppressWarnings("unused")
  private Object[] getEmaciatedStrings() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (Ethnicity e : Ethnicity.values())
    {
      Object [] params = new Object[3];
      String name = e.name();
      params[0] = "Ethnicity " + name;
      params[1] = e.toString().replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase();
      params[2] = e;
      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Ethnicity#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getEmaciatedStrings")
  public void testFromPrettyStringMinimizedValid(String testName, String emaciatedPrettyString, Ethnicity expected)
  {
    Ethnicity p1 = Ethnicity.enumValue(emaciatedPrettyString);
    assertEquals(p1, expected);
  }
  
  @SuppressWarnings("unused")
  private Object[] getLowerNameValues() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (Ethnicity e : Ethnicity.values())
    {
      Object [] params = new Object[3];
      String name = e.name();
      params[0] = "Ethnicity " + name;
      params[1] = name.toLowerCase();;
      params[2] = e;
      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Ethnicity#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getLowerNameValues")
  public void testFromNameLowerCase(String testName, String lowerValue, Ethnicity expected)
  {
    Ethnicity p1 = Ethnicity.enumValue(lowerValue);
    assertEquals(p1, expected);
  }
  
  @SuppressWarnings("unused")
  private Object[] getMalformedNames() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    Object [] nullMalformed = new Object[2];
    nullMalformed[0] = "Ethnicity Null";
    parameterSet.add(nullMalformed);
    
    Object [] malformed = new Object[2];
    malformed[0] = "Ethnicity Malformed";
    malformed[1] = "Shamwow";
    parameterSet.add(malformed);
    
    return parameterSet.toArray();
  }
  
  @Test(expected = IllegalArgumentException.class)
  @Parameters(method = "getMalformedNames")
  public void testIllegalValues(String testName, String illegalValue)
  {
    Ethnicity.enumValue(illegalValue);
  }
  
  @SuppressWarnings("unused")
  private Object [] getValidValues()
  {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (Ethnicity e : Ethnicity.values())
    {
      Object [] param = new Object[4];
      
      String name = e.name();
      param[0] = "Ethnicity " + name;
      param[1] = e;
      param[2] = name;
      param[3] = Boolean.FALSE;
      
      parameterSet.add(param);
    }
     
    return parameterSet.toArray();
  }
  
  @Test
  @Parameters(method = "getValidValues")
  public void testEnumValueValid(String testName, Ethnicity ethnicityIn, String value, boolean valid)
  {
    Ethnicity e = Ethnicity.enumValue(value);
    assertEquals(e, ethnicityIn); 
  }
}
