package edu.psu.swe.commons.jaxrs;

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


import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class InstantParamWrapper {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_INSTANT;

  private Instant instant;

  public InstantParamWrapper(String s) {
    if (s != null) {
      instant = Instant.parse(s);
    }
  }
  
  public InstantParamWrapper(Instant instant) {
    this.instant = instant;
  }

  @Override
  public String toString() {
    return getInstant() != null ? FORMATTER.format(instant) : null;
  }

  public Optional<Instant> getInstant() {
    return Optional.ofNullable(instant);
  }

}
