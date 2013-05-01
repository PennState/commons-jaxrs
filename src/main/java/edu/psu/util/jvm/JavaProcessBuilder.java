package edu.psu.util.jvm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class is used to run a Java class in a new operating system process.
 * 
 * The JavaProcessBuilder uses the ProcessBuilder to actually construct the
 * Process returned by the start() method, but allows the new JVM to inherit
 * the classpath and executing environment of the launching JVM.
 * 
 * Starting a new process can be accomplished by passing the class to be
 * launched (it must contain a main() method) and the argument list as
 * follows:
 * 
 * <pre>
 *        Process p = new JavaProcessBuilder(edu.psu.Example.class, "arg1", arg2").start();
 * </pre>
 *
 * @see java.lang.ProcessBuilder
 * @author swm16
 */
public class JavaProcessBuilder {

  public static final String ENV_JAVA_HOME = "java.home";
  public static final String ENV_JAVA_CLASS_PATH = "java.class.path";
  
  private List<String> arguments_;
  private String binpath_;
  private ProcessBuilder builder_;
  private String classpath_;
  private Class<?> clazz_;
  
  /**
   * Constructs a Java process builder with the specified Java and arguments.
   * This constructor does not make a copy of the arguments list. Subsequent updates to the
   * list will be reflected in the state of the process builder.  The child process'
   * classpath and JVM binary (java) are taken from the parent's environment.
   * 
   * @param clazz the Java class to run (it must have a static main() method)
   * @param arguments a list of String objects that will become the String[] args
   * passed to main().
   */
  public JavaProcessBuilder(Class<?> clazz, List<String> arguments) {
    // Save arguments and clazz in case they're changed later
    arguments_ = arguments;
    this.clazz_ = clazz;
    
    // Find the execument and class paths of the current JVM
    String javaHome = System.getProperty(ENV_JAVA_HOME);
    binpath_ = javaHome + File.separator + "bin" + File.separator + "java";
    classpath_ = System.getProperty(ENV_JAVA_CLASS_PATH);

    // Create a process builder to do the work under the hood
    builder_ = new ProcessBuilder(buildCommand());
  }
  
  /**
   * Constructs a Java process builder with the specified Java class and
   * arguments. This is a convenience constructor that simply converts the
   * argument parameter to and array of strings and calls the previously defined
   * constructor.
   * 
   * @param clazz the Java class to run (it must have a static main() method)
   * @param arguments a list of String objects that will become the String[] args
   * passed to main().
   */
  public JavaProcessBuilder(Class<?> clazz, String... arguments) {
    this(clazz, Arrays.asList(arguments));
  }

  /**
   * Gets the list of arguments that will be passed to this Java process builder's
   * Class<?> when the new JVM is spawned.
   * 
   * @return the list of arguments
   */
  public List<String> arguments() {
    return arguments_;
  }
  
  /**
   * Sets the list of arguments that will be passed to this Java process builder's
   * Class<?> when the new JVM is spawned.
   * 
   * @param arguments
   * @return
   */
  public JavaProcessBuilder arguments(List<String> arguments) {
    return command(clazz_, arguments);
  }
  
  /*
   * Converts the Class<?>, arguments command-line used by this builder to the
   * pure List<String> version used by the underlying process builder.
   */
  private List<String> buildCommand() {
    List<String> command = new ArrayList<String>();
    command.add(binpath_);
    command.add("-cp");
    command.add(classpath_);
    command.add(clazz_.getCanonicalName());
    if(arguments_ != null) {
      command.addAll(arguments_);
    }
    return command;
  }

  /**
   * Gets the classpath that will be used by the executed Class<?> when the
   * new JVM is spawned.
   * 
   * @return a String containing the complete classpath
   */
  public String classpath() {
    return classpath_;
  }
  
  /**
   * Sets the classpath that will be used by the executed Class<?> when the
   * new JVM is spawned.
   * 
   * @param classpath
   * @return this Java process builder
   */
  public JavaProcessBuilder classpath(String classpath) {
    this.classpath_ = classpath;
    return command(clazz_, arguments_);
  }
  
  /**
   * Gets the Class<?> that will be executed by this Java process builder.
   * 
   * @return the Class<?> object
   */
  public Class<?> clazz() {
    return clazz_;
  }
  
  /**
   * Sets the Class<?> that will be executed by this Java process builder. Note
   * that this class must have a static void main(String[] args) method.
   * 
   * @param clazz the Class<?> to be executed.
   * @return this Java process builder
   */
  public JavaProcessBuilder clazz(Class<?> clazz) {
    return command(clazz, arguments_);
  }
  
  /**
   * Sets this Java process builder's Java class and arguments. This method does
   * not make a copy of the arguments list. Subsequent updates to the list will be
   * reflected in the state of the Java process builder.
   * 
   * @param clazz the Java class to run (it must have a static main() method)
   * @param arguments a list of String objects that will become the String[] args
   * passed to main().
   * @return this Java process builder
   */
  public JavaProcessBuilder command(Class<?> clazz, List<String> arguments) {
    arguments_ = arguments;
    clazz_ = clazz;
    builder_.command(buildCommand());
    return this;
  }
  
  /**
   * Sets this Java process builder's Java class and arguments. This is a convenience
   * method that sets the arguments to a string list containing the same strings as
   * the arguments array, in the same order.
   * 
   * @param clazz the Java class to run (it must have a static main() method)
   * @param arguments a list of String objects that will become the String[] args
   * passed to main().
   * @return this Java process builder
   */
  public JavaProcessBuilder command(Class<?> clazz, String... arguments) {
    return this.command(clazz, Arrays.asList(arguments));
  }
  
  /**
   * Returns this Java process builder's working directory. Subprocesses subsequently
   * started by this object's start() method will use this as their working directory.
   * The returned value may be null -- this means to use the working directory of the
   * current Java process, usually the directory named by the system property
   * user.dir, as the working directory of the child process.
   * 
   * @return this process builder's working directory
   */
  public File directory() {
    return builder_.directory();
  }
  
  /**
   * Sets this Java process builder's working directory. Subprocesses subsequently
   * started by this object's start() method will use this as their working directory.
   * The argument may be null -- this means to use the working directory of the
   * current Java process, usually the directory named by the system property user.dir,
   * as the working directory of the child process.
   * 
   * @param directory a valid directory on the executing host's file system
   * @return this Java process builder
   */
  public JavaProcessBuilder directory(File directory) {
    builder_.directory(directory);
    return this;
  }
  
  /**
   * Returns a string map view of this process builder's environment. Whenever
   * a process builder is created, the environment is initialized to a copy of
   * the current process environment (see System.getenv()). Subprocesses
   * subsequently started by this object's start() method will use this map as
   * their environment.
   * 
   * The returned object may be modified using ordinary Map operations. These
   * modifications will be visible to subprocesses started via the start()
   * method. Two ProcessBuilder instances always contain independent process
   * environments, so changes to the returned map will never be reflected in
   * any other ProcessBuilder instance or the values returned by System.getenv.
   * 
   * @see java.lang.ProcessBuilder#environment()
   * @return this Java process builder's environment
   */
  public Map<String, String> environment() {
    return builder_.environment();
  }
  
  /**
   * Tells whether this Java process builder merges standard error and standard
   * output.
   * 
   * If this property is true, then any error output generated by
   * subprocesses subsequently started by this object's start() method will be
   * merged with the standard output, so that both can be read using the
   * Process.getInputStream() method. This makes it easier to correlate error
   * messages with the corresponding output. The initial value is false.
   * 
   * @return this process builder's redirectErrorStream property
   */
  public boolean redirectErrorStream() {
    return builder_.redirectErrorStream();
  }
  
  /**
   * Sets this Java process builder's redirectErrorStream property.
   * 
   * If this property is true, then any error output generated by subprocesses
   * subsequently started by this object's start() method will be merged with
   * the standard output, so that both can be read using the
   * Process.getInputStream() method. This makes it easier to correlate error
   * messages with the corresponding output. The initial value is false.
   * 
   * @param redirectErrorStream the new property value
   * @return this process builder
   */
  public JavaProcessBuilder redirectErrorStream(boolean redirectErrorStream) {
    builder_.redirectErrorStream(redirectErrorStream);
    return this;
  }
  
  /**
   * Starts a new process using the attributes of this process builder.
   * 
   * The new process will execute the main() method of the specified class with
   * the arguments given by arguments(), in a working directory as given by
   * directory(), with a process environment as given by environment().
   * 
   * @see java.lang.ProcessBuilder#start()
   * @return a new Process object for managing the subprocess
   * @throws NullPointerException if an element of the command list is null
   * @throws SecurityException if a security manager exists and its checkExec
   * method doesn't allow creation of the subprocess
   * @throws IOException if an I/O error occurs
   */
  public Process start() throws IOException {
    return builder_.start();
  }

}
