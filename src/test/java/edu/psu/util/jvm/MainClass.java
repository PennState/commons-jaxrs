package edu.psu.util.jvm;

public class MainClass {
  
  public static final String ERR_STRING = "This is stderr";
  public static final String OUT_STRING = "This is stdout";

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.err.println(ERR_STRING);
    System.out.println(OUT_STRING);
    System.exit(0);
  }

}
