package edu.psu.swe.commons.jaxrs.enumerations;

public enum RelationshipType
{
  ABOUT("about", "Refers to a resource that is the subject of the link's context."),
  ALTERNATIVE("alternate", "Refers to a substitute for this context"),
  APPENDIX("appendix", "Refers to an appendix."),
  ARCHIVES("archives", "Refers to a collection of records, documents, or other materials of historical interest."),
  AUTHOR("author", "Refers to the context's author."),
  BLOCKED_BY("blocked-by", "Identifies the entity blocking access to a resource folllowing on receipt of a legal demand."),
  BOOKMARK("bookmark", "Gives a permanent link to use for bookmarking purposes."),
  CANONICAL("canonical", "Designates the preferred version of a resource (the IRI and its contents)."),
  CHAPTER("chapter", "Refers to a chapter in a collection of resources."),
  COLLECTION("collection", "The target IRI points to a resource which represents the collection resource for the context IRI."),
  CONTENTS("contents", "Refers to a table of contents."),
  COPYRIGHT("copyright", "Refers to a copyright statement that applies to the link's context."),
  CREATE_FROM("create-form", "The target IRI points to a resource where a submission form can be obtained."),
  CURRENT("current", "Refers to a resource containing the most recent item(s) in a collection of resources."),
  DERIVED_FROM("derivedfrom", "The target IRI points to a resource from which this material was derived."), 
  DESCRIBED_BYP("describedby", "Refers to a resource providing information about the link's context."),
  DESCRIBES("describes", "The relationship A 'describes' B asserts that resource A provides a description of resource B. There are no constraints on the format or representation of either A or B, neither are there any further constraints on either resource.  This link relation type is the inverse of the 'describedby' relation type. While 'describedby' establishes a relation from the described resource back to the resource that describes it, 'describes' established a relation from the describing resource to the resource it describes. If B is 'describedby' A, then A 'describes' B."),
  DISCLOSURE("disclosure", "Refers to a list of patent disclosures made with respect to material for which 'disclosure' relation is specified."),
  DUPLICATE("duplicate", "Refers to a resource whose available representations are byte-for-byte identical with the corresponding representations of the context IRI. 	This relation is for static resources. That is, an HTTP GET request on any duplicate will return the same representation. It does not make sense for dynamic or POSTable resources and should not be used for them."),
  EDIT("edit", "Refers to a resource that can be used to edit the link's context."),
  EDIT_FORM("edit-form", "The target IRI points to a resource where a submission form for editing associated resource can be obtained."),
  EDIT_MEDIA("edit-media", "Refers to a resource that can be used to edit media associated with the link's context."),
  ENCLOSURE("enclosure", "Identifies a related resource that is potentially large and might require special handling."),
  FIRST("first", "An IRI that refers to the furthest preceding resource in a series of resources. This relation type registration did not indicate a reference. Originally requested by Mark Nottingham in December 2004."),
  GLOSSARY("glossary", "Refers to a glossary of terms."),
  HELP("help", "Refers to context-sensitive help."),
  HOSTS("hosts", "Refers to a resource hosted by the server indicated by the link context.  This relation is used in CoRE where links are retrieved as a \"/.well-known/core\" resource representation, and is the default relation type in the CoRE Link Format."),
  HUB("hub", "Refers to a hub that enables registration for notification of updates to the context. 	[http://pubsubhubbub.googlecode.com] 	This relation type was requested by Brett Slatkin."),
  ICON("icon", "Refers to an icon representing the link's context."),
  INDEX("index", "Refers to an index."),
  ITEM("item", "The target IRI points to a resource that is a member of the collection represented by the context IRI."),
  LAST("last", "An IRI that refers to the furthest following resource in a series of resources. This relation type registration did not indicate a reference. Originally requested by Mark Nottingham in December 2004."),
  LATEST_VERSION("latest-version", "Points to a resource containing the latest (e.g., current) version of the context."),
  LICENSE("license", "Refers to a license associated with this context.  For implications of use in HTML, see: http://www.w3.org/TR/html5/links.html#link-type-license"),
  LRDD("lrdd", "Refers to further information about the link's context, expressed as a LRDD (\"Link-based Resource Descriptor Document\") resource. See [RFC6415] for information about processing this relation type in host-meta documents. When used elsewhere, it refers to additional links and other metadata. Multiple instances indicate additional LRDD resources. LRDD resources MUST have an \"application/xrd+xml\" representation, and MAY have others."),
  MEMENTO("memento", "The Target IRI points to a Memento, a fixed resource that will not change state anymore.  A Memento for an Original Resource is a resource that encapsulates a prior state of the Original Resource."),
  MONITOR("monitor", "Refers to a resource that can be used to monitor changes in an HTTP resource."),
  MONITOR_GROUP("monitor-group", "Refers to a resource that can be used to monitor changes in a specified group of HTTP resources."), 
  NEXT("next", "Indicates that the link's context is a part of a series, and that the next in the series is the link target."), 
  NEXT_ARCHIVE("next-archive", "Refers to the immediately following archive resource."),
  NOFOLLOW("nofollow", "Indicates that the context’s original author or publisher does not endorse the link target."),
  NOREFERRER("noreferrer", "Indicates that no referrer information is to be leaked when following the link."),
  ORIGINAL("original", "The Target IRI points to an Original Resource.  An Original Resource is a resource that exists or used to exist, and for which access to one of its prior states may be required."),
  PAYMENT("payment", "Indicates a resource where payment is accepted.  This relation type registration did not indicate a reference. Requested by Joshua Kinberg and Robert Sayre. It is meant as a general way to facilitate acts of payment, and thus this specification makes no assumptions on the type of payment or transaction protocol. Examples may include a web page where donations are accepted or where goods and services are available for purchase. rel=\"payment\" is not intended to initiate an automated transaction. In Atom documents, a link element with a rel=\"payment\" attribute may exist at the feed/channel level and/or the entry/item level. For example, a rel=\"payment\" link at the feed/channel level may point to a \"tip jar\" URI, whereas an entry/ item containing a book review may include a rel=\"payment\" link that points to the location where the book may be purchased through an online retailer."),
  PREDECESSOR_VERSION("predecessor-version", "Points to a resource containing the predecessor version in the version history."), 
  PREFETCH("prefetch", "Indicates that the link target should be preemptively cached."),
  PREV("prev", "Indicates that the link's context is a part of a series, and that the previous in the series is the link target."),
  PREVIEW("preview", "Refers to a resource that provides a preview of the link's context."),
  PREVIOUS("previous", "Refers to the previous resource in an ordered series of resources. Synonym for \"prev\"."),
  PREV_ARCHIVE("prev-archive", "Refers to the immediately preceding archive resource."),
  PRIVACY_POLICY("privacy-policy", "Refers to a privacy policy associated with the link's context."),
  PROFILE("profile", "Identifying that a resource representation conforms to a certain profile, without affecting the non-profile semantics of the resource representation.  Profile URIs are primarily intended to be used as identifiers, and thus clients SHOULD NOT indiscriminately access profile URIs."),
  RELATED("related", "Identifies a related resource."),
  REPLIES("replies", "Identifies a resource that is a reply to the context of the link."),
  SEARCH("search", "Refers to a resource that can be used to search through the link's context and related resources."),
  SECTION("section", "Refers to a section in a collection of resources."),
  SELF("self", "Conveys an identifier for the link's context."),
  SERVICE("service", "Indicates a URI that can be used to retrieve a service document.  When used in an Atom document, this relation type specifies Atom Publishing Protocol service documents by default. Requested by James Snell."),
  START("start", "Refers to the first resource in a collection of resources."),
  STYLESHEET("stylesheet", "Refers to a stylesheet."),
  SUBSECTION("subsection", "Refers to a resource serving as a subsection in a collection of resources."),
  SUCCESSOR_VERSION("successor-version", "Points to a resource containing the successor version in the version history."),
  TAG("tag", "Gives a tag (identified by the given address) that applies to the current document."),
  TERMS_OF_SERVICE("terms-of-service", "Refers to the terms of service associated with the link's context."),
  TIMEGATE("timegate", "The Target IRI points to a TimeGate for an Original Resource. 	A TimeGate for an Original Resource is a resource that is capable of datetime negotiation to support access to prior states of the Original Resource."),
  TIMEMAP("timemap", "The Target IRI points to a TimeMap for an Original Resource.  A TimeMap for an Original Resource is a resource from which a list of URIs of Mementos of the Original Resource is available."),
  TYPE("type", "Refers to a resource identifying the abstract semantic type of which the link's context is considered to be an instance."),
  UP("up", "Refers to a parent document in a hierarchy of documents.  This relation type registration did not indicate a reference. Requested by Noah Slater."),
  VERSION_HISTORY("version-history", "Points to a resource containing the version history for the context."), 
  VIA("via", "Identifies a resource that is the source of the information in the link's context."),
  WORKING_COPY("working-copy", "Points to a working copy for this resource."),
  WORKING_COPY_OF("working-copy-of", "");

  private String relationship;
  private String description;
  
  RelationshipType(String rel, String description)
  {
    this.relationship = rel;
    this.description = description;
  }
  
  @Override
  public String toString()
  {
    return relationship;
  }
  
  public String getDescription()
  {
    return description;
  }
}
