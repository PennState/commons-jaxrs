package edu.psu.hateoas.interceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.hateoas.annotations.AddHateoasLinks;
import edu.psu.hateoas.annotations.Link;
import edu.psu.hateoas.annotations.Links;
import edu.psu.hateoas.model.HateoasModel;
import edu.psu.rest.AtomLink;

/**
 * This class intercepts outgoing JAX-RS responses and looks for Links annotations on the class to add hateos links.
 */
@Provider
@AddHateoasLinks
public class HateoasWriteInterceptor implements WriterInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(HateoasWriteInterceptor.class);
	
	@Context
	private UriInfo uriInfo_;
	
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		LOGGER.debug("Entered HatoesWriteInterceptor");
		
		//check if class extends HateosModel
		if(context.getEntity() instanceof HateoasModel){						
			processLinkAnnotations(context.getEntity());			
		}
		//if a list, need to check each object in the list
		else if(context.getEntity() instanceof List){
			List objects = (List)context.getEntity();
			
			for(Object o : objects){
				if(o instanceof HateoasModel){
					processLinkAnnotations(o);
				}
			}
		}
			
		//proceed
		context.proceed();		
	}
	
	/**
	 * Looks for hateos link annotations and adds links to object. Object must extned HateosModel
	 * @param object
	 */
	private void processLinkAnnotations(Object object){
		Annotation[] annotations = object.getClass().getAnnotations();
		
		//find Links annotation
		//TODO: cache annotation data for each class?
		for(Annotation annotation : annotations){																
		    if(annotation instanceof Links){
		    	Links linksAnnotation = (Links) annotation;
		    	
		    	for(Link linkAnnotation : linksAnnotation.value()){
			        this.addAtomLink(linkAnnotation, object);				        
		    	}
		    }
		}
	}
	
	/**
	 * Add atom link to object
	 * @param linkAnnotation
	 * @param object
	 */
	private void addAtomLink(Link linkAnnotation, Object object){
		HateoasModel instance = (HateoasModel) object;
		
		AtomLink atomLink = new AtomLink();
        atomLink.setRelation(linkAnnotation.rel());
        
        if(atomLink.getTitle() != null){
        	atomLink.setTitle(linkAnnotation.title());
        }
        
        String path = linkAnnotation.path();
        
        //find all occurences of {methodName} and replace with value from object
        String pattern = "\\{(.*?)\\}";
        
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(path);
        			       
        while (m.find()) {
            String methodName = m.group(1);		            
        
            try{
            	Method method = object.getClass().getMethod(methodName);
            	//get value
            	Object value = method.invoke(object);            	
            	
            	// If we can't template the link, skip this link.  
            	if (value == null) {
            	  return;
            	}
            	
              //replace in path
            	String methodPattern = "\\{" + methodName + "\\}";
            	path = path.replaceAll(methodPattern, value.toString());
            }
            catch(Exception e){
            	LOGGER.error("Error find method " + methodName + " : " + e.getMessage());
            	return;
            }
        }
        			        			        			        
        atomLink.setHyperlink(uriInfo_.getBaseUri().getPath() + path);			        
        instance.getLinks().add(atomLink);
	}

}
