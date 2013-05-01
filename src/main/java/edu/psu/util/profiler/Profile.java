package edu.psu.util.profiler;

import java.util.ArrayList;
import java.util.List;

public class Profile
{
  private List<Long> startTime_ = new ArrayList<Long>();
  private List<Long> stopTime_ = new ArrayList<Long>();

  // ------------------------------------------------------------------------------------------ //

  public void start()
  {
//    start_time_.add( ((Long)System.currentTimeMillis()) );
    startTime_.add( ((Long)System.nanoTime()) );
  }

  // ------------------------------------------------------------------------------------------ //

  public void stop()
  {
//    stop_time_.add( ((Long)System.currentTimeMillis()) );
    stopTime_.add( ((Long)System.nanoTime()) );
  }
  
  // ------------------------------------------------------------------------------------------ //

/*
  public String generateReport()
  {
    long count = 0;
    long delta = 0;
    
    for( int i=0 ; i<stop_time_.size() ; ++i )
    {
      ++count;
      delta += stop_time_.get(i) - start_time_.get(i);
    }
    
    if( count > 0 ) 
      // %[argument_index$][flags][width][.precision]conversion
      return( String.format(" %1$ 8d * %2$ 13d ns = %3$ 13d ns", count, (delta / count), delta ) );

    else
      return( "(no completed profiles taken)" );
  }
*/

  public String generateReport()
  {
    long count = 0;
    double delta = 0;
    double perDelta = 0;
    String perUnit = "";
    String totalUnit = "";
    
    for( int i=0 ; i<stopTime_.size() ; ++i )
    {
      ++count;
      delta += (double) stopTime_.get(i) - (double) startTime_.get(i);
    }
    
    if( count > 0 ) 
    {
      perDelta = delta / (double)count;

      if( delta < 999 )
      {
        totalUnit = "ns";
      }
      else if( delta < 999999 )
      {
        totalUnit = "µs";
        delta /= 1000;
      }
      else if( delta < 999999999 )
      {
        totalUnit = "ms";
        delta /= 1000000;
      }
      else if( delta > 999 )
      {
        totalUnit = " s";
        delta /= 1000000000;
      }

      if( perDelta < 999 )
      {
        perUnit = "ns";
      }
      else if( perDelta < 999999 )
      {
        perUnit = "µs";
        perDelta /= 1000;
      }
      else if( perDelta < 999999999 )
      {
        perUnit = "ms";
        perDelta /= 1000000;
      }

      else if( perDelta > 999 )
      {
        perUnit = " s";
        perDelta /= 1000000000;
      }

      // %[argument_index$][flags][width][.precision]conversion
      return( String.format(" %1$ 8d * %2$ 8.3f "+perUnit+" = %3$ 8.3f "+totalUnit, count, perDelta, delta ) );
    }
    else
    {
      return( "(no completed profiles taken)" );
    }
  }

  // ------------------------------------------------------------------------------------------ //
}

