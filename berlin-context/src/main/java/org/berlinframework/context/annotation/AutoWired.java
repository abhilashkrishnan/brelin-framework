package org.berlinframework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Abhilash Krishnan
 * Default mode is auto wire by Type
 * If you want to auto wire by Name use Qualifier along with AutoWired
 */

@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD, ElementType.FIELD} )
public @interface AutoWired {
}
