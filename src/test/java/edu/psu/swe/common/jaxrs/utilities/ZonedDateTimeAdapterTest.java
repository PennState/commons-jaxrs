package edu.psu.swe.common.jaxrs.utilities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.swe.commons.jaxrs.adapters.ZonedDateTimeAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZonedDateTimeAdapterTest {

  ZonedDateTimeAdapter adapter = new ZonedDateTimeAdapter();
  
  @Test
  public void marshallNow() throws Exception {
    ZonedDateTime timeToMarshal = ZonedDateTime.now();

    String dateTimeString = adapter.marshal(timeToMarshal);
    log.info("Marshalled String: {}", dateTimeString);
  } 

  @Test
  public void marshallTimeNoMilliseconds() throws Exception {
    Instant i = Instant.ofEpochSecond(1552494870);
    ZonedDateTime z = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
    
    String dateTimeString = adapter.marshal(z);
    log.info("Marshalled String: {}", dateTimeString);
    Assert.assertEquals("2019-03-13T16:34:30.000Z", dateTimeString);
  }
  
}
