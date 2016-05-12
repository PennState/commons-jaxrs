package edu.psu.util.string;

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


public class StringUtils
{
  private StringUtils()
  {}
  
  //The following two methods are base on code found here - http://www.rgagnon.com/javadetails/java-0448.html
  public static String padRight(String s, int n) 
  {
    return String.format("%1$-" + n + "s", s);
  }

  public static String padLeft(String s, int n) 
  {
    return String.format("%1$" + n + "s", s);
  }
}
