/**
 * 
 */
package ait.common.enumeration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.psu.enumeration.Prefix;

/**
 * @author ses44
 *
 */
@RunWith(JUnitParamsRunner.class)
public class PrefixTest
{

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }

  /**
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringNull()
  {
    Prefix p = Prefix.fromPrettyString(null);
    assertNull(p);
  }

  /**
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  public void testFromPrettyStringEmptyString()
  {
    String s = "";
    Prefix p = Prefix.fromPrettyString(s);
    assertEquals(p, Prefix.NONE);
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters({
    "Prefix MR,    Mr.,  MR,   true",
    "Prefix MS,    Ms.,  MS,   true",
    "Prefix MRS,   Mrs., MRS,  true",
    "Prefix DR,    Dr.,  DR,   true"
  })
  public void testFromPrettyStringValid(String testName, String prettyPrefix, String prefix, boolean passes)
  {
    Prefix p1 = Prefix.fromPrettyString(prettyPrefix);
    Prefix p2 = Prefix.valueOf(prefix);
    assertEquals(p1, p2);
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Prefix#toString()}.
   */
  @Test
  public void testToStringEmptyString()
  {
    Prefix p = Prefix.NONE;
    assertTrue(p.toString().trim().isEmpty());
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Prefix#toString()}.
   */
  @Test
  @Parameters({
    "Prefix MR,    MR,   Mr.,  true",
    "Prefix MS,    MS,   Ms.,  true",
    "Prefix MRS,   MRS,  Mrs., true",
    "Prefix DR,    DR,   Dr.,  true"
  })
  public void testToStringValid(String testName, String prefix, String prettyPrefix, boolean passes)
  {
    Prefix g = Prefix.valueOf(prefix);
    assertTrue(g.toString().equals(prettyPrefix));
  }
  
  /**
   * Test method for {@link edu.psu.fps.model.enums.Prefix#fromPrettyString(java.lang.String)}.
   */
  @Test
  @Parameters({
    "Prefix MR,    Mr.,  MR,   true",
    "Prefix MR,    MR,   MR,   true",
    "Prefix MS,    Ms.,  MS,   true",
    "Prefix MS,    MS,   MS,   true",
    "Prefix MRS,   Mrs., MRS,  true",
    "Prefix MRS,   MRS,  MRS,  true",
    "Prefix DR,    Dr.,  DR,   true",
    "Prefix DR,    DR,   DR,   true",
  })
  public void testEnumValue(String testName, String prefixString, String prefix, boolean passes)
  {
    Prefix p1 = Prefix.enumValue(prefixString);
    Prefix p2 = Prefix.valueOf(prefix);
    assertEquals(p1, p2);
  }
}
