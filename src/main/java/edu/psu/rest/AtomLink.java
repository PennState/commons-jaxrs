package edu.psu.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Implements the Atom Link Model for HATEOAS resource references.
 * 
 * http://www.xml.com/pub/a/2004/06/16/dive.html
 * http://timelessrepo.com/haters-gonna-hateoas#hateoas
 * 
 * @author Steve Moyer {@literal (<smoyer@psu.edu>)}
 */
@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomLink {
  
  @XmlElement(name = "href")
  private String hyperlink_;
  
  @XmlElement(name = "rel")
  private String relation_;
  
  @XmlElement(name = "title", nillable = true)
  private String title_;
  
  @XmlElement(name = "type")
  private String type_;
  
  public AtomLink(){
	  
  }
  
  public AtomLink(String relation, String type, String hyperlink) {
    relation_ = relation;
    type_ = type;
    hyperlink_ = hyperlink;
  }
  
  public AtomLink(String relation, String type, String hyperlink, String title) {
    this(relation, type, hyperlink);
    title_ = title;
  }
  
  public String getHyperlink() {
    return hyperlink_;
  }

  public void setHyperlink(String hyperlink) {
    hyperlink_ = hyperlink;
  }

  public String getRelation() {
    return relation_;
  }

  public void setRelation(String relation) {
    relation_ = relation;
  }

  public String getTitle() {
    return title_;
  }

  public void setTitle(String title) {
    title_ = title;
  }

  public String getType() {
    return type_;
  }

  public void setType(String type) {
    type_ = type;
  }
  
}
