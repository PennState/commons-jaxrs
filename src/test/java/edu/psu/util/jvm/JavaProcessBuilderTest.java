package edu.psu.util.jvm;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class JavaProcessBuilderTest {
  
  public static final String ECHO_TEST_STRING = "This is an echo test";
  
  public static final String ERROR_NO_SUCH_METHOD = "Exception in thread \"main\" java.lang.NoSuchMethodError: main";
  
  private JavaProcessBuilder builder_;

  @Before
  public void setUp() throws Exception {
    builder_ = new JavaProcessBuilder(MainClass.class, (List<String>) null);
  }
  
  @SuppressWarnings("unused")
  private Object[] parametersForTestArgumentsArePassed() {
    return $(
        $("No arguments", null),
        $("One arguments", Arrays.asList(new String[]{"Arg1"})),
        $("Three arguments", Arrays.asList(new String[]{"Arg1", "Arg2", "Arg3"}))
    );
  }
  
  @Test
  @Parameters
  public void testArgumentsArePassed(String testName, List<String> arguments) throws IOException, InterruptedException {
    builder_.clazz(EchoArgumentsAsString.class);
    builder_.arguments(arguments);
    Process p = builder_.start();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals(EchoArgumentsAsString.getJoinedArguments(arguments), inReader.readLine());
    inReader.close();
    p.waitFor();
    assertEquals(0, p.exitValue());
  }
  
  @SuppressWarnings("unused")
  private Object[] parametersForTestArgumentsAreReturned() {
    return $(
        $("No arguments", null),
        $("One arguments", Arrays.asList(new String[]{"Arg1"})),
        $("Three arguments", Arrays.asList(new String[]{"Arg1", "Arg2", "Arg3"}))
    );
  }
  
  @Test
  @Parameters
  public void testArgumentsAreReturned(String testName, List<String> arguments) throws IOException {
    JavaProcessBuilder builder = new JavaProcessBuilder(MainClass.class, arguments);
    assertEquals(arguments, builder.arguments());
  }
  
  @Test
  public void testClassIsReturned() {
    assertEquals(MainClass.class, builder_.clazz());
  }

  @Test
  public void testClasspathMatches() {
    String classpath = System.getProperty(JavaProcessBuilder.ENV_JAVA_CLASS_PATH);
    assertEquals(classpath, builder_.classpath());
  }
  
  @Test
  public void testClasspathCanBeExpanded() {
    String newClasspath = System.getProperty(JavaProcessBuilder.ENV_JAVA_CLASS_PATH)
        + File.pathSeparator + ".";
    builder_.classpath(newClasspath);
    assertEquals(newClasspath, builder_.classpath());
  }
  
  @Test
  @Ignore
  public void testClassWithNoMainMethod() throws IOException, InterruptedException {
    builder_.clazz(NoMain.class);
    Process p = builder_.start();
    BufferedReader errReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    assertEquals(ERROR_NO_SUCH_METHOD, errReader.readLine());
    errReader.close();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertNull(inReader.readLine());
    inReader.close();
    p.waitFor();
    assertEquals(1, p.exitValue());
  }
  
  @Test
  public void testCombinedOutAndErrorStreams() throws IOException, InterruptedException {
    builder_.redirectErrorStream(true);
    Process p = builder_.start();
    assertTrue(builder_.redirectErrorStream());
    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals(MainClass.ERR_STRING, reader.readLine());
    assertEquals(MainClass.OUT_STRING, reader.readLine());
    p.waitFor();
    assertEquals(0, p.exitValue());
  }
  
  @Test
  public void testDirectoryMatchesParentJvmsWorkingDirectory() throws IOException, InterruptedException {
    File workingDirectory = new File(System.getProperty("user.dir"));
    builder_.directory(workingDirectory);
    assertTrue(workingDirectory.equals(builder_.directory()));
    testSeparateOutAndErrorStreams();
  }

  @Test
  public void testEchoFromErrorToOutputStream() throws IOException, InterruptedException {
    builder_.clazz(StdinStderrEcho.class);
    Process p = builder_.start();
    PrintStream outWriter = new PrintStream(p.getOutputStream());
    outWriter.println(ECHO_TEST_STRING);
    outWriter.flush();
    outWriter.close();
    BufferedReader errReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals(ECHO_TEST_STRING, errReader.readLine());
    errReader.close();
    p.waitFor();
    assertEquals(0, p.exitValue());
  }

  @Test
  public void testEchoFromInputToOutputStream() throws IOException, InterruptedException {
    builder_.clazz(StdinStdoutEcho.class);
    Process p = builder_.start();
    PrintStream outWriter = new PrintStream(p.getOutputStream());
    outWriter.println(ECHO_TEST_STRING);
    outWriter.flush();
    outWriter.close();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals(ECHO_TEST_STRING, inReader.readLine());
    inReader.close();
    p.waitFor();
    assertEquals(0, p.exitValue());
  }
  
  @Test
  public void testEnvironmentMatchesParentJvms() {
    Map<String, String> parentEnvironment = System.getenv();
    assertNotNull(parentEnvironment);
    Map<String, String> childEnvironment = builder_.environment();
    assertTrue(parentEnvironment.equals(childEnvironment));
  }

  @Test
  public void testSeparateOutAndErrorStreams() throws IOException, InterruptedException {
    Process p = builder_.start();
    BufferedReader errReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    assertEquals(MainClass.ERR_STRING, errReader.readLine());
    errReader.close();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals(MainClass.OUT_STRING, inReader.readLine());
    inReader.close();
    p.waitFor();
    assertEquals(0, p.exitValue());
  }
  
  @Test
  public void testSettingCommandWithVariableArguments() throws IOException, InterruptedException {
    builder_.command(EchoArgumentsAsString.class, "Test1", "Test2");
    Process p = builder_.start();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals("Test1,Test2,", inReader.readLine());
    inReader.close();
    p.waitFor();
    assertEquals(0, p.exitValue());
  }
  
  @Test
  public void testVariableArgumentConstructor() throws IOException, InterruptedException {
    builder_ = new JavaProcessBuilder(EchoArgumentsAsString.class, "Test1", "Test2");
    Process p = builder_.start();
    BufferedReader inReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals("Test1,Test2,", inReader.readLine());
    inReader.close();
    p.waitFor();
    assertEquals(0, p.exitValue());
  }
  
}
