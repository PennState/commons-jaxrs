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
public class StateTest
{

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }

  /**
   * Test method for {@link edu.psu.fps.model.enums.State#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringNull()
  {
    State p = State.fromPrettyString(null);
    assertNull(p);
  }

  @Test
  public void testFromAbbreviationNull()
  {
    State p = State.fromAbbreviation(null);
    assertNull(p);
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.State#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringEmptyString()
  {
    String s = "";
    State p = State.fromPrettyString(s);
    assertNull(p);
  }
  
  @SuppressWarnings("unused")
  private Object[] getPrettyStrings() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (State e : State.values())
    {
      Object [] params = new Object[3];
      String name = e.name();
      params[0] = "State " + name;
      params[1] = e.toString();
      params[2] = e;
      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.State#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getPrettyStrings")
  public void testFromPrettyStringValid(String testName, String prettyState, State expected)
  {
    State p1 = State.fromPrettyString(prettyState);
    assertEquals(p1, expected);
  }
  
  @SuppressWarnings("unused")
  private Object[] getEmaciatedStrings() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (State e : State.values())
    {
      Object [] params = new Object[3];
      String name = e.name();
      params[0] = "State " + name;
      params[1] = e.toString().replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase();
      params[2] = e;
      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.State#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getEmaciatedStrings")
  public void testFromPrettyStringMinimizedValid(String testName, String emaciatedPrettyString, State expected)
  {
    State p1 = State.enumValue(emaciatedPrettyString);
    assertEquals(p1, expected);
  }
  
  @SuppressWarnings("unused")
  private Object[] getLowerNameValues() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (State e : State.values())
    {
      Object [] params = new Object[3];
      String name = e.name();
      params[0] = "State " + name;
      params[1] = name.toLowerCase();;
      params[2] = e;
      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.State#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getLowerNameValues")
  public void testFromNameLowerCase(String testName, String lowerValue, State expected)
  {
    State p1 = State.enumValue(lowerValue);
    assertEquals(p1, expected);
  }
  
  @SuppressWarnings("unused")
  private Object[] getMalformedNames() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    Object [] nullMalformed = new Object[2];
    nullMalformed[0] = "State Null";
    parameterSet.add(nullMalformed);
    
    Object [] malformed = new Object[2];
    malformed[0] = "State Malformed";
    malformed[1] = "Shamwow";
    parameterSet.add(malformed);
    
    return parameterSet.toArray();
  }
  
  @Test(expected = IllegalArgumentException.class)
  @Parameters(method = "getMalformedNames")
  public void testIllegalValues(String testName, String illegalValue)
  {
    State.enumValue(illegalValue);
  }
  
  @SuppressWarnings("unused")
  private Object [] getValidValues()
  {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (State e : State.values())
    {
      Object [] param = new Object[4];
      
      String name = e.name();
      param[0] = "State " + name;
      param[1] = e;
      param[2] = name;
      param[3] = Boolean.FALSE;
      
      parameterSet.add(param);
    }
     
    return parameterSet.toArray();
  }
  
  @Test
  @Parameters(method = "getValidValues")
  public void testEnumValueValid(String testName, State stateIn, String value, boolean valid)
  {
    State e = State.enumValue(value);
    assertEquals(e, stateIn); 
  }
  
  @SuppressWarnings("unused")
  private Object[] getShortStrings() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    for (State e : State.values())
    {
      Object [] params = new Object[3];
      String name = e.name();
      params[0] = "State " + name;
      params[1] = e;
      params[2] = e.asAbbreviation();

      
      parameterSet.add(params);
    }
    
    return parameterSet.toArray();
  }
  
  @Test
  @Parameters(method = "getShortStrings")
  public void testShortStringValid(String testName, State stateIn, String value)
  {
    State e = State.fromAbbreviation(value);
    assertEquals(e, stateIn); 
  }
}
