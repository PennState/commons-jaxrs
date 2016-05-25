package edu.psu.swe.commons.jaxrs.hateoas.model;

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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import edu.psu.swe.commons.jaxrs.AtomLink;

public class HateoasModel {

  private List<AtomLink> links = new ArrayList<AtomLink>();

  public void addLink(AtomLink link) {
    links.add(link);
  }

  @XmlElement(name = "links")
  public List<AtomLink> getLinks() {
    return links;
  }

  public void setLinks(List<AtomLink> links) {
    this.links = links;
  }

}
