package edu.psu.enumeration;

public enum DegreeType 
{
  BA("B.A.", "Baccalareut of Arts"),
  BS("B.S.", "Baccalareut of Science");
  
  private String prettyShort_;
  private String prettyString_;
  
  DegreeType(String prettyShort, String prettyString)
  {
    prettyShort_ = prettyShort;
    prettyString_ = prettyString;
  }
  
  public String getPrettyShortString()
  {
    return prettyShort_;
  }
  
  public String getPrettyString()
  {
    return prettyString_;
  }
}
