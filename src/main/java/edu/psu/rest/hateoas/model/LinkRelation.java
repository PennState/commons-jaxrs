package edu.psu.rest.hateoas.model;

public enum LinkRelation {
  ABOUT("about","Refers to a resource that is the subject of the link's context."),
  ALTERNATE("alternate","Refers to a substitute for this context"),
  APPENDIX("appendix","Refers to an appendix."),
  ARCHIVES("archives","Refers to a collection of records, documents, or other materials of historical interest."),
  AUTHOR("author","Refers to the context's author."),
  BOOKMARK("bookmark","Gives a permanent link to use for bookmarking purposes."),
  CANONICAL("canonical","Designates the preferred version of a resource (the IRI and its contents),."),
  CHAPTER("chapter","Refers to a chapter in a collection of resources."),
  COLLECTION("collection","The target IRI points to a resource which represents the collection resource for the context IRI."),
  CONTENTS("contents","Refers to a table of contents."),
  COPYRIGHT("copyright","Refers to a copyright statement that applies to the link's context."),
  CREATE_FORM("create-form","The target IRI points to a resource where a submission form can be obtained."),
  CURRENT("current","Refers to a resource containing the most recent item(s) in a collection of resources."),
  DERIVEDFROM("derivedfrom","The target IRI points to a resource from which this material was derived."),
  DESCRIBEDBY("describedby","Refers to a resource providing information about the link's context."),
  DESCRIBES("describes","The relationship A 'describes' B asserts that resource A provides a description of resource B. There are no constraints on the format or representation of either A or B, neither are there any further constraints on either resource."),
  DISCLOSURE("disclosure","Refers to a list of patent disclosures made with respect to material for which 'disclosure' relation is specified."),
  DUPLICATE("duplicate","Refers to a resource whose available representations are byte-for-byte identical with the corresponding representations of the context IRI."),
  EDIT("edit","Refers to a resource that can be used to edit the link's context."),
  EDIT_FORM("edit-form","The target IRI points to a resource where a submission form for editing associated resource can be obtained."),
  EDIT_MEDIA("edit-media","Refers to a resource that can be used to edit media associated with the link's context."),
  ENCLOSURE("enclosure","Identifies a related resource that is potentially large and might require special handling."),
  FIRST("first","An IRI that refers to the furthest preceding resource in a series of resources."),
  GLOSSARY("glossary","Refers to a glossary of terms."),
  HELP("help","Refers to context-sensitive help."),
  HOSTS("hosts","Refers to a resource hosted by the server indicated by the link context."),
  HUB("hub","Refers to a hub that enables registration for notification of updates to the context."),
  ICON("icon","Refers to an icon representing the link's context."),
  INDEX("index","Refers to an index."),
  ITEM("item","The target IRI points to a resource that is a member of the collection represented by the context IRI."),
  LAST("last","An IRI that refers to the furthest following resource in a series of resources."),
  LATEST_VERSION("latest-version","Points to a resource containing the latest (e.g., current), version of the context."),
  LICENSE("license","Refers to a license associated with this context."),
  LRDD("lrdd","Refers to further information about the link's context, expressed as a LRDD (Link-based Resource Descriptor Document), resource. See [RFC6415] for information about processing this relation type in host-meta documents. When used elsewhere, it refers to additional links and other metadata. Multiple instances indicate additional LRDD resources. LRDD resources MUST have an application/xrd+xml representation, and MAY have others."),
  MEMENTO("memento","The Target IRI points to a Memento, a fixed resource that will not change state anymore."),
  MONITOR("monitor","Refers to a resource that can be used to monitor changes in an HTTP resource."),
  MONITOR_GROUP("monitor-group","Refers to a resource that can be used to monitor changes in a specified group of HTTP resources."),
  NEXT("next","Indicates that the link's context is a part of a series, and that the next in the series is the link target."),
  NEXT_ARCHIVE("next-archive","Refers to the immediately following archive resource."),
  NOFOLLOW("nofollow","Indicates that the context’s original author or publisher does not endorse the link target."),
  NOREFERRER("noreferrer","Indicates that no referrer information is to be leaked when following the link."),
  ORIGINAL("original","The Target IRI points to an Original Resource."),
  PAYMENT("payment","Indicates a resource where payment is accepted."),
  PREDECESSOR_VERSION("predecessor-version","Points to a resource containing the predecessor version in the version history."),
  PREFETCH("prefetch","Indicates that the link target should be preemptively cached."),
  PREV("prev","Indicates that the link's context is a part of a series, and that the previous in the series is the link target."),
  PREVIEW("preview","Refers to a resource that provides a preview of the link's context."),
  PREVIOUS("previous","Refers to the previous resource in an ordered series of resources. Synonym for prev."),
  PREV_ARCHIVE("prev-archive","Refers to the immediately preceding archive resource."),
  PRIVACY_POLICY("privacy-policy","Refers to a privacy policy associated with the link's context."),
  PROFILE("profile","Identifying that a resource representation conforms to a certain profile, without affecting the non-profile semantics of the resource representation."),
  RELATED("related","Identifies a related resource."),
  REPLIES("replies","Identifies a resource that is a reply to the context of the link."),
  SEARCH("search","Refers to a resource that can be used to search through the link's context and related resources."),
  SECTION("section","Refers to a section in a collection of resources."),
  SELF("self","Conveys an identifier for the link's context."),
  SERVICE("service","Indicates a URI that can be used to retrieve a service document."),
  START("start","Refers to the first resource in a collection of resources."),
  STYLESHEET("stylesheet","Refers to a stylesheet."),
  SUBSECTION("subsection","Refers to a resource serving as a subsection in a collection of resources."),
  SUCCESSOR_VERSION("successor-version","Points to a resource containing the successor version in the version history."),
  TAG("tag","Gives a tag (identified by the given address), that applies to the current document."),
  TERMS_OF_SERVICE("terms-of-service","Refers to the terms of service associated with the link's context."),
  TIMEGATE("timegate","The Target IRI points to a TimeGate for an Original Resource."),
  TIMEMAP("timemap","The Target IRI points to a TimeMap for an Original Resource."),
  TYPE("type","Refers to a resource identifying the abstract semantic type of which the link's context is considered to be an instance."),
  UP("up","Refers to a parent document in a hierarchy of documents."),
  VERSION_HISTORY("version-history","Points to a resource containing the version history for the context."),
  VIA("via","Identifies a resource that is the source of the information in the link's context."),
  WORKING_COPY("working-copy","Points to a working copy for this resource."),
  WORKING_COPY_OF("working-copy-of","Points to the versioned resource from which this working copy was obtained.");
      
  public String rel;
  public String description;

  private LinkRelation(String rel, String desscription) {
    this.rel = rel;
    this.description = desscription;
  }
  
  public String rel() {
    return rel;
  }
  
  public String description() {
    return description;
  }
}
