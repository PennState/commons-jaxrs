package edu.psu.security;

import java.util.Base64;

public final class BasicAuthTokenHandler
{
  private static final String PREFIX = "Basic ";
  
  public static class User
  {
    private String userid_;
    private String password_;
    
    public String getUserid()
    {
      return userid_;
    }
    
    public void setUserid(String userid)
    {
      userid_ = userid;
    }
    
    public String getPassword()
    {
      return password_;
    }
    
    public void setPassword(String password)
    {
      password_ = password;
    }
  }
  
  public static User parseToken(String authString)
  {
  //This should decode everything past the 'Basic ' tag
    String decoded = new String(Base64.getDecoder().decode(authString.substring(authString.indexOf(' ') + 1)));

    BasicAuthTokenHandler.User user = new BasicAuthTokenHandler.User();
    user.setUserid(decoded.substring(0, decoded.indexOf(':')));
    user.setPassword(decoded.substring(decoded.indexOf(':') + 1));

    return user;
  }
  
  public static String createToken(String userid, String password)
  {
    String combined = userid + ":" + password;
    String encoded = Base64.getEncoder().encodeToString(combined.getBytes());
    
    return PREFIX + encoded;
  }
  
  private BasicAuthTokenHandler()
  {}
}
