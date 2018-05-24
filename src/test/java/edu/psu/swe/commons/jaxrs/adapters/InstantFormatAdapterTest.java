package edu.psu.swe.commons.jaxrs.adapters;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import edu.psu.swe.commons.jaxrs.adapters.InstantFormatAdapter;

public class InstantFormatAdapterTest {
  private static final String DAYLIGHT_TS = "2018-05-24T12:24:36.123Z";
  
  private static final Instant DAYLIGHT_INSTANT = Instant.parse(DAYLIGHT_TS);
  private static final List<String> DAYLIGHT_TIMESTAMPS = Stream.of(DAYLIGHT_TS,
                                                                 "2018-05-24T12:24:36.123+00:00[UTC]",
                                                                 "2018-05-24T12:24:36.123+00:00[GMT]",
                                                                 "2018-05-24T08:24:36.123-04:00",
                                                                 "2018-05-24T08:24:36.123-04:00[America/New_York]",
                                                                 "2018-05-24T19:24:36.123+07:00")
                                                              .collect(Collectors.toList());
  
  private static final String STANDARD_TS = "2018-12-12T12:24:36.123Z";
  private static final Instant STANDARD_INSTANT = Instant.parse(STANDARD_TS);
  private static final List<String> STANDARD_TIMESTAMPS = Stream.of(STANDARD_TS,
                                                                    "2018-12-12T07:24:36.123-05:00",
                                                                    "2018-12-12T07:24:36.123-05:00[America/New_York]")
                                                                .collect(Collectors.toList());
  private InstantFormatAdapter adapter = new InstantFormatAdapter();
  
  @Test
  public void testUnmarshal() throws Exception {
    
    for(String ts : DAYLIGHT_TIMESTAMPS) {
      Instant instant = adapter.unmarshal(ts);
      Assert.assertEquals("Timestamp parsing failure for timestamp: " + ts, DAYLIGHT_INSTANT, instant);
    }
    
    for(String ts : STANDARD_TIMESTAMPS) {
      Instant instant = adapter.unmarshal(ts);
      Assert.assertEquals("Timestamp parsing failure for timestamp: " + ts, STANDARD_INSTANT, instant);
    }
  }
  
  @Test
  public void testMarshall() throws Exception {
    String ts = adapter.marshal(DAYLIGHT_INSTANT);
    Assert.assertEquals(DAYLIGHT_TS, ts);
    
    ts = adapter.marshal(STANDARD_INSTANT);
    Assert.assertEquals(STANDARD_TS, ts);
  }
}
