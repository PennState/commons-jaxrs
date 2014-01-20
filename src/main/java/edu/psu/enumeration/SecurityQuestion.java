/**
 * 
 */
package edu.psu.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static edu.psu.enumeration.Constants.BARE_QUESTION_LOOKUP_REGEX;

/**
 * This enumeration is a representation of all valid security questions that can be asked within the FPS system
 */
public enum SecurityQuestion 
{
	NOT_SELECTED("NOT SELECTED"),
  MATERNAL_GRANDFATHER_JOB("What did your maternal grandfather do for a living?"),
  FAVORITE_BOOK("What is the name of your favorite book?"),
  NEW_FIRST_NAME("If you could choose a new first name, what would it be?"),
  FAVORITE_HIGH_SCHOOL_MUSICAL_ARTIST("Who was your favorite musical artist in high school?"),
  FAVORITE_AUTHORS_LAST_NAME("What is your favorite authors last name?"),
  MATERNAL_GRANDFATHER_FIRST_NAME("What is your maternal grandfather's first name?"),
  MATERNAL_GRANDMOTHER_FIRST_NAME("What is your maternal grandmother's first name?"),
  PATERNAL_GRANDFATHER_FIRST_NAME("What is your paternal grandfather's first name?"),
  PATERNAL_GRANDMOTHER_FIRST_NAME("What is your paternal grandmother's first name?"),
  MOTHER_BIRTH_CITY("In what city was your mother born?"),
  FATHER_BIRTH_CITY("In what city was your father born?"),
  MOTHER_MIDDLE_NAME("What is your mothers middle name?"),
  FATHER_MIDDLE_NAME("What is your fathers middle name?"),
  MOTHER_MAIDEN_NAME("What is your mothers maiden name?"),
  CHILDHOOD_NEIGHBOR_LAST_NAME("When you were a child, what was your neighbor's last name?"),
  FAVORITY_HISTORICAL_PERSON("Who is your favorite historical person?"),
  CHILDHOOD_STREET("When you were a child, what street did you live on?"),
  MOST_FAMOUS_PERSON_YOU_HAVE_MET("Who is the most famous person you've ever met?"),
  CHILDHOOD_BEST_FRIEND_FIRST_NAME("What was your childhood best friends first name?"),
  FIRST_RELATIONSHIP_FIRST_NAME("What was your first girlfriend or boyfriend's first name?"),
  FIRST_RELATIONSHIP_LAST_NAME("What was your first girlfriend or boyfriend's last name?"),
  CHILDHOOD_FATHERS_COMPANY("When you were a child, what company did your father work for?"),
  CHILDHOOD_MOTHERS_COMPANY("When you were a child, what company did your mother work for?"),
  HIGHSCHOOL_MASCOT("What was your high school mascot?"),
  FIRST_PETS_NAME("What was your first pets name?"),
  FAVORITE_MOVIE("What is your all-time favorite movie?"),
  FIRST_GRADE_TEACHER_LAST_NAME("What was your first grade teachers last name?"),
  FAVORITE_FICTIONAL_CHARACTER("Who is your favorite fictional character?"),
  FIRST_BOSS_NAME("At your first job, what was your boss's last name?"),
  FAVORITE_TEACHER_LAST_NAME("What was your favorite teachers last name?"),
  ELEMENTARY_SCHOOL("What elementary school did you go to?"),
  FIRST_COMPANY_NAME("What was the name of the first company you worked for?"),
  FIRST_CAR_MAKE_MODEL("What make and model was your first car?"),
  MARRIAGE_CITY("In what city were you married?"),
  BEST_MAN_LAST_NAME("What was your best mans first name?"),
  MAID_OF_HONOR_FIRST_NAME("What was your maid of honors first name?"),
  HONEYMOON_CITY("In what city did you honeymoon?"),
  OLDEST_SIBLING_FIRST_NAME("What is your oldest sibling's first name?"),
  YOUNGEST_SIBLING_FIRST_NAME("What is your youngest sibling's first name?"),
  FIRST_SCHOOL("What is the name of your first school?"),
  PETS_NAME("What is your pet's name?"),    
  SCHOOL_MASCOT("What was your school's mascot?"),
  HOME_COUNTY("What is your home county?"),
  PHONE_NUMBER_LAST_FOUR("What are the last four digits of your phone number?");    

  private static Map<String, SecurityQuestion> reverseLookup_ = new HashMap<String, SecurityQuestion>();

  
  private String prettyString_;
  
  private static String ILLEGAL_ARGUMENT_MESSAGE = null;
  
  static
  {
    EnumSet<SecurityQuestion> set = EnumSet.allOf(SecurityQuestion.class);
    
    StringBuilder sb = new StringBuilder();
    Iterator<SecurityQuestion> iter = set.iterator();
    
    while(iter.hasNext())
    {
      SecurityQuestion s = iter.next();

      reverseLookup_.put(s.prettyString_,  s);
      reverseLookup_.put(s.prettyString_.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase(), s);

      sb.append(s.name());
      sb.append(", ");
      sb.append(s.prettyString_);
      if (iter.hasNext())
      {
        sb.append(", ");
      }            
    }
     
    ILLEGAL_ARGUMENT_MESSAGE = "The value you passed for Security Question was illegal, legal values are: " + sb.toString();
  }
  
  /**
   * Constructs a SecurityQuestion with a "presentation" version
   * @param prettyQuestion
   */
  SecurityQuestion(String prettyQuestion)
  {
    prettyString_ = prettyQuestion;
  }

  /**
   * Attempts to turn a "presentation" version into the enumerated value
   * @param prettyString
   * @return SecurityQuestion if valid, null otherwise
   */
  public static SecurityQuestion fromPrettyString(String prettyString)
  {
    return reverseLookup_.get(prettyString.trim());
  }
  
  @Override
  public String toString()
  {
    return prettyString_;
  }
  
  public static SecurityQuestion enumValue(String stringValue)
  {
    if (stringValue == null)
    {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }
    
    SecurityQuestion s = null;
    String trimmedValue = stringValue.trim();
    try
    {
      s = SecurityQuestion.valueOf(trimmedValue.toUpperCase());
    }
    catch(IllegalArgumentException e)
    {
      //Try from pretty string
      s = SecurityQuestion.fromPrettyString(trimmedValue);
      if (s == null)
      { 
        s = reverseLookup_.get(stringValue.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase());
        
        if (s == null)
        {
          throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }
      }
    }
    
    return s;
  }
}
