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


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateTimeParamWrapper {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  private LocalDateTime localDateTime;

  public LocalDateTimeParamWrapper(String s) {
    if (s != null) {
      localDateTime = LocalDateTime.parse(s);
    }
  }

  public LocalDateTimeParamWrapper(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }
  
  @Override
  public String toString() {
    return localDateTime != null ? FORMATTER.format(localDateTime) : "";
  }

  public Optional<LocalDateTime> getLocalDateTime() {
    return Optional.ofNullable(localDateTime);
  }
}
