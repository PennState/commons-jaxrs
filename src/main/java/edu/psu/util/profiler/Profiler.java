package edu.psu.util.profiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Profiler {

  private Map<String,Integer> patternDepth_ = new HashMap<String,Integer>();
  private String currentTree_ = "";
  private List<String> patterns_ = new ArrayList<String>();
  private Map<String, Profile> profiles_ = new HashMap<String, Profile>();

  private int depth_ = 0;

  // ------------------------------------------------------------------------------------------ //

  public Profiler( ) {
    //reset();
  }

  // ------------------------------------------------------------------------------------------ //

  public void reset()
  {
    patternDepth_.clear();
    currentTree_ = "";
    patterns_.clear();
    profiles_.clear();
  }

  // ------------------------------------------------------------------------------------------ //

  public void start( String pattern )
  {
    ++depth_;

    if( currentTree_.equals("") ) 
    {
      currentTree_ = pattern;
    }
    else
    {
      currentTree_ = currentTree_ + " -- " + pattern;
    }
    
    if( ! patterns_.contains( currentTree_ ) )
    {
      patterns_.add( currentTree_ );
      profiles_.put( currentTree_, new Profile() );
      patternDepth_.put( currentTree_, depth_ );
    }
    profiles_.get(currentTree_).start();
  }

  // ------------------------------------------------------------------------------------------ //

  public void stop( String pattern )
  {
    if( profiles_.containsKey( currentTree_ ) )
    {
      profiles_.get(currentTree_).stop();
    }

    int lastIndex_ = currentTree_.lastIndexOf(" -- ");
    if( lastIndex_ > 0 )
    {
      currentTree_ = currentTree_.substring( 0, lastIndex_ );
    }
    else
    {
      currentTree_ = "";
    }
    --depth_;
  }

  // ------------------------------------------------------------------------------------------ //

  public String generateReport( )
  {
    return generateReport( 8 );
  }

  // ------------------------------------------------------------------------------------------ //

  public String generateReport( int max_maxDepth_ )
  {
    String report = "";
    String displayKey = "";

    report = "  PATTERN                                                      :     COUNT *    TIME PER =  TOTAL TIME\r\n";
  
    for( String key : patterns_ ) {
      depth_ = patternDepth_.get(key);

      if( depth_ <= max_maxDepth_ ) {

        if( key.lastIndexOf(" -- ") > -1 )
        {
          displayKey = key.substring( key.lastIndexOf(" -- ") + 4);
        }
        else
        {
          displayKey = key;
        }

        if( displayKey.length() > 60 - 2 * depth_ ) 
        {
          displayKey = key.substring( 0, 60 - 2 * depth_ );
        }

        report += String.format(" %1$"+(2*depth_)+"s %2$-"+(60-2*depth_)+"s : ", "", displayKey) + profiles_.get( key ).generateReport() + "\r\n";
      }

    }
    return( report );
  }
}

