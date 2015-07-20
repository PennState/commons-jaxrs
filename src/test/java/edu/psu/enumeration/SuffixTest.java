/*
 * 
 */
package edu.psu.enumeration;

import static edu.psu.enumeration.Constants.BARE_QUESTION_LOOKUP_REGEX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.psu.enumeration.Suffix;


@RunWith(JUnitParamsRunner.class)
public class SuffixTest
{

  /*
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }

  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringNull()
  {
    Suffix p = Suffix.fromPrettyString(null);
    assertNull(p);
  }

  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringEmptyString()
  {
    String s = "";
    Suffix p = Suffix.fromPrettyString(s);
    assertEquals(p, Suffix.NONE);
  }
  
  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters({
    "Suffix JR,    Jr.,  JR,   true",
    "Suffix SR,    Sr.,  SR,   true",
    "Suffix II,    II,   II,   true",
    "Suffix III,   III,  III,  true",
    "Suffix IV,    IV,   IV,   true",
    "Suffix V,     V,    V,    true",
    "Suffix VI,    VI,   VI,   true",
    "Suffix VII,   VII,  VII,  true",
    "Suffix VIII,  VIII, VIII, true",
    "Suffix IX,    IX,   IX,   true",
    "Suffix X,     X,    X,    true"
  })
  public void testFromPrettyStringValid(String testName, String prettyPrefix, String prefix, boolean passes)
  {
    Suffix p1 = Suffix.fromPrettyString(prettyPrefix);
    Suffix p2 = Suffix.valueOf(prefix);
    assertEquals(p1, p2);
  }
  
  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters({
    "Suffix JR,    Jr.,  JR,   true",
    "Suffix SR,    Sr.,  SR,   true",
    "Suffix II,    II,   II,   true",
    "Suffix III,   III,  III,  true",
    "Suffix IV,    IV,   IV,   true",
    "Suffix V,     V,    V,    true",
    "Suffix VI,    VI,   VI,   true",
    "Suffix VII,   VII,  VII,  true",
    "Suffix VIII,  VIII, VIII, true",
    "Suffix IX,    IX,   IX,   true",
    "Suffix X,     X,    X,    true"
  })
  public void testFromPrettyStringMinimizedValid(String testName, String prettyPrefix, String prefix, boolean passes)
  {
    Suffix p1 = Suffix.fromPrettyString(prettyPrefix.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase());
    Suffix p2 = Suffix.valueOf(prefix);
    assertEquals(p1, p2);
  }
  
  @SuppressWarnings("unused")
  private Object[] getLowerCasedNames() {
    List<Object> parameterSet = new ArrayList<Object>();
    for(Suffix suffix: Suffix.values()) {
      Object[] parameters = new Object[3];
      String name = suffix.name();
      parameters[0] = "Suffix " + name;
      parameters[1] = name.toLowerCase();
      parameters[2] = Boolean.TRUE;
      parameterSet.add(parameters);
    }
    return parameterSet.toArray();
  }
  
  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters(method = "getLowerCasedNames")
  public void testFromNameLowerCase(String testName, String suffix, Boolean passes)
  {
    Suffix p1 = Suffix.enumValue(suffix);
    Suffix p2 = Suffix.valueOf(suffix.toUpperCase());
    assertEquals(p1, p2);
  }
  
  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#toString()}.
   */
  @Test
  public void testToStringEmptyString()
  {
    Suffix p = Suffix.NONE;
    assertTrue(p.toString().trim().isEmpty());
  }
  
  /*
   * Test method for {@link edu.psu.fps.model.enums.Prefix#toString()}.
   */
  @Test
  @Parameters({
    "Suffix JR,    JR,   Jr.,  true",
    "Suffix SR,    SR,   Sr.,  true",
    "Suffix II,    II,   II,   true",
    "Suffix III,   III,  III,  true",
    "Suffix IV,    IV,   IV,   true",
    "Suffix V,     V,    V,    true",
    "Suffix VI,    VI,   VI,   true",
    "Suffix VII,   VII,  VII,  true",
    "Suffix VIII,  VIII, VIII, true",
    "Suffix IX,    IX,   IX,   true",
    "Suffix X,     X,    X,    true"
  })
  public void testToStringValid(String testName, String suffix, String prettySuffix, boolean passes)
  {
    Suffix g = Suffix.valueOf(suffix);
    assertTrue(g.toString().equals(prettySuffix));
  }
  
  @SuppressWarnings("unused")
  private Object[] getEmaciatedNames() {
    List<Object> parameterSet = new ArrayList<Object>();
    for(Suffix suffix: Suffix.values()) {
      Object[] parameters = new Object[4];
      String name = suffix.name();
      parameters[0] = "Suffix " + name;
      parameters[1] = name;
      parameters[2] = suffix.toString().replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase();
      parameters[3] = Boolean.TRUE;
    }
    return parameterSet.toArray();
  }
  
  @Test
  @Parameters(method = "getEmaciatedNames")
  public void testEmaciatedNames(String testName, String suffix, String emaciatedName, boolean passes)
  {
    Suffix s1 = Suffix.enumValue(emaciatedName);
    Suffix s2 = Suffix.valueOf(suffix);
    assertEquals(s1, s2);
  }
  
  @SuppressWarnings("unused")
  private Object[] getMalformedNames() {
    List<Object> parameterSet = new ArrayList<Object>();
    
    Object [] nullMalformed = new Object[2];
    nullMalformed[0] = "Prefix Null";
    parameterSet.add(nullMalformed);
    
    Object [] malformed = new Object[2];
    malformed[0] = "Suffix Malfored";
    malformed[1] = "Shamwow";
    parameterSet.add(malformed);
    
    return parameterSet.toArray();
  }
  
  @Test(expected = IllegalArgumentException.class)
  @Parameters(method = "getMalformedNames")
  public void testIllegalValues(String testName, String illegalValue)
  {
    Suffix.enumValue(illegalValue);
  }
}
