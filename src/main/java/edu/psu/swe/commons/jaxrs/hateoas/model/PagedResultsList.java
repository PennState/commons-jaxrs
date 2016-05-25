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


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.psu.swe.commons.jaxrs.hateoas.annotations.Link;

@Link(rel="self", path="{getPath}?page[number]={getCurrentPage}&page[size]={getPageSize}")
@Link(rel="first", path="{getPath}?page[number]={getFirstPage}&page[size]={getPageSize}")
@Link(rel="prev", path="{getPath}?page[number]={getPreviousPage}&page[size]={getPageSize}")
@Link(rel="next", path="{getPath}?page[number]={getNextPage}&page[size]={getPageSize}")
@Link(rel="last", path="{getPath}?page[number]={getLastPage}&page[size]={getPageSize}")
@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.NONE)
public class PagedResultsList<T> extends HateoasModel {

  @XmlElementWrapper(name = "results")
  @XmlElement(name = "result")
  @JsonProperty("results")
  private List<T> results;

  private Integer firstPage;

  private Integer previousPage;

  private Integer currentPage;

  private Integer nextPage;

  private Integer lastPage;
  
  private Integer pageSize;
  
  private String path = "";

  public List<T> getResults() {
    return results;
  }

  public void setResults(List<T> results) {
    this.results = results;
  }

  public Integer getFirstPage() {
    return firstPage;
  }

  public void setFirstPage(Integer firstPage) {
    this.firstPage = firstPage;
  }

  public Integer getPreviousPage() {
    return previousPage;
  }

  public void setPreviousPage(Integer previousPage) {
    this.previousPage = previousPage;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getNextPage() {
    return nextPage;
  }

  public void setNextPage(Integer nextPage) {
    this.nextPage = nextPage;
  }

  public Integer getLastPage() {
    return lastPage;
  }

  public void setLastPage(Integer lastPage) {
    this.lastPage = lastPage;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
