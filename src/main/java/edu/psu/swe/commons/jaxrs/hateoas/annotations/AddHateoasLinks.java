package edu.psu.swe.commons.jaxrs.hateoas.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;
import java.lang.annotation.RetentionPolicy;

@NameBinding
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddHateoasLinks {

}
