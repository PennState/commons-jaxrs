package edu.psu.persistence;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface KeyFile
{
  String file() default "app_key";
}
