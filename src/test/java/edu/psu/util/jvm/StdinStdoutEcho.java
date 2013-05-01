package edu.psu.util.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdinStdoutEcho {
  
  public static void main(String[] args) throws IOException {
    BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(inReader.readLine());
    inReader.close();
    System.out.println("got here");
    System.exit(0);
  }

}
