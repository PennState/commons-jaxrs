package edu.psu.util.logging;

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


import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TraceGenerator
{
  private TraceGenerator()
  {}

  private static final Logger logger_ = LoggerFactory.getLogger(TraceGenerator.class);

  /**
   *  Format a message to be logged.
   *
   *  @param details The log message that is to be formatted
   *
   *  @return A formatted String containing the details, timestamp, threadId, and location in code that the message came from.
  */
  public static String formatLogMessage( String details ) {
    final SimpleDateFormat ymdhms = new SimpleDateFormat( "yyyyMMdd HHmmss.SSSZ"); 

    StringBuilder message = new StringBuilder();
    Thread currentThread = Thread.currentThread();
    StackTraceElement[] stea = currentThread.getStackTrace();

    message.append( ymdhms.format( new java.util.Date() ) );

    message.append( " - " );
    message.append( currentThread.getId() );
    message.append( " - " );
    message.append( details.replaceAll( "(.+)([\r\n]+)$", "$1" ) );
    message.append( " - " );
    message.append( stea[2].getClassName() );
    message.append( "." );
    message.append( stea[2].getMethodName() );
    message.append( "()" );
    message.append( " at " );
    message.append( stea[2].getFileName() );
    message.append( ", line " );
    message.append( stea[2].getLineNumber() );
    message.append( "\n" );

    return message.toString() ;
  }



  /**
   *  Returns a String that documents the current stack
   *
   *  @return A formatted String listing the stack depth, class/method, file, and line number of the calls.
  */
  public static String getStackTrace() {
    StringBuilder message = new StringBuilder();
    message.append( "util.log.get_stack_trace() at " );
    message.append( new java.util.Date().toString() );
    message.append( "\n" );

    int count = 0;
    Thread currentThread = Thread.currentThread();
    StackTraceElement[] stea = currentThread.getStackTrace();
    for ( StackTraceElement ste : stea ) {
      message.append( "  " );
      message.append( count++ );
      message.append( " - " );
      message.append( ste.getClassName() );
      message.append( "." );
      message.append( ste.getMethodName() );
      message.append( "() at " );
      message.append( ste.getFileName() );
      message.append( ", line " );
      message.append( ste.getLineNumber() );
      message.append( "\n" );
    }
    return message.toString();
  }



  /**
   *  Dumps the current stack (from get_stack_trace()) to standard error
  */
  public static void dumpStackTrace() {
    logger_.error( getStackTrace() );
  }
}
 
