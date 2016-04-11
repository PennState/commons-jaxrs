package edu.psu.rest;

import java.net.MalformedURLException;

import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.rest.enumerations.RelationshipType;
import lombok.Data;

/**
 * Implements the Atom Link Model for HATEOAS resource references.
 * 
 * http://www.xml.com/pub/a/2004/06/16/dive.html
 * http://timelessrepo.com/haters-gonna-hateoas#hateoas
 * 
 */
@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class AtomLink {
  
  private static final Logger LOG = LoggerFactory.getLogger(AtomLink.class);
  
  @XmlElement(name = "href")
  private String hyperlink;
  
  @XmlElement(name = "rel")
  private String relation;
  
  @XmlElement(name = "title")
  private String title;
  
  @XmlElement(name = "type")
  private String mimeType;
  
  public AtomLink(){
	  
  }
  
  public AtomLink(String relation, String type, String hyperlink) {
    this.relation = relation;
    mimeType = type;
    this.hyperlink = hyperlink;
  }
  
  public AtomLink(String relation, String type, String hyperlink, String title) {
    this(relation, type, hyperlink);
    this.title = title;
  }
  
  public static AtomLink createSelfLink(UriInfo uriInfo)
  {
    String url = null;

    try
    {
      url = UriUtilities.urlAsString(uriInfo, true);
    }
    catch(MalformedURLException mue)
    {
      LOG.info("Failed to convert url " + mue.getLocalizedMessage());
    }

    AtomLink self = new AtomLink();
    self.setRelation(RelationshipType.SELF.toString());
    self.setHyperlink(url);
    self.setMimeType(Constants.PSU_CONTENT_TYPE_V1);
    return self;
  }

  
}
