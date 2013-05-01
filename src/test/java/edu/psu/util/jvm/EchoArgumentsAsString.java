package edu.psu.util.jvm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EchoArgumentsAsString {
  
  public static String getJoinedArguments(List<String> args) {
    StringBuilder builder = new StringBuilder();
    if(args == null || args.size() == 0) {
      builder.append("null");
    } else {
      for(String arg: args) {
        builder.append(arg);
        builder.append(",");
      }
    }
    return builder.toString();
  }
  
  public static void main(String[] args) throws IOException {
    System.out.println(getJoinedArguments(Arrays.asList(args)));
    System.exit(0);
  }

}
