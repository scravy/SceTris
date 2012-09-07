/**
 * 
 */
package de.fu.weave.xml.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Julian Fleischer
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface XmlAttribute {
	
	/**
	 * 
	 * @return
	 */
	String value();
}
