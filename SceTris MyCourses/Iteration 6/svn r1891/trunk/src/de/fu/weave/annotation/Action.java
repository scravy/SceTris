/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.fu.weave.TemplateManager;

/**
 * Denotes a Method as an action accessible via Web by a given name.
 * <p>
 * This annotation is applicable to methods in a Module. The annotated methods must follow these
 * rules:
 * <ol>
 * <li>The first parameter MUST be of the type <tt>String[]</tt></li>
 * <li>All other paramters must be annotated using {@link Arg}.</li>
 * <li>The annotated method MUST be public</li>
 * </ol>
 * 
 * @since Iteration2
 * @author Julian Fleischer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Action {

	/**
	 * Exceptions to be left to the Template.
	 * <p>
	 * Any Exceptions listed here will catched by the Controller of the Actions Module and passed to
	 * the Template Engine, whereas the Controller would handle it otherwise. The Exception will be
	 * added as an element "Exception" in the main namespace.
	 * 
	 * @since Iteration2
	 * @return An array of Exceptions-Classes ( <code>{NameOfException.class, ...}</code>)
	 */
	Class<? extends Exception>[] handles() default {};

	/**
	 * The name of the action.
	 * <p>
	 * The action will be accessible via the name given here. Naturally this needs to be a unique
	 * identifier, therefore the Controller will throw an Exception when attempting to load a Module
	 * that defines a name twice. Typically the action will be located at
	 * <tt>/<servlet-path>/<module-name>/<action-name></tt>
	 * <p>
	 * If no name is given at all (which defaults to the empty string) the action is the default
	 * action. This arises logically from the semantics of the path itself. By convention the name
	 * of the annotated method (it's Java name) should be "_default".
	 * <p>
	 * NOTE: You may specify any name possible here, but try to choose a simple name which can be
	 * remembered and typed easily, since it identifies the action in the world wide web (via its
	 * domain and path).
	 * 
	 * @since Iteration2
	 * @return The name of the action
	 */
	String name() default "";

	/**
	 * The name of a permission required to execute this action.
	 * <p>
	 * This optional value defaults to the empty string, which means: no permission required. If it
	 * is not the empty string (that is, when its length() is greather than zero) the Controller
	 * automatically checks if the current user has the specified Permission and deny the execution
	 * of the action.
	 * <p>
	 * Since permissions may be bound to a certain target it is possible to mark one parameter as
	 * that target using {@link Arg#isTarget()}. If such a Parameter exists the execution is granted
	 * only if the user is allowed to do the associated action on the target given by the content of
	 * the annotated param.
	 * 
	 * @since Iteration2
	 * @return the Name of the required permission, or the empty string
	 */
	String[] requiresPrivilege() default {};

	/**
	 * The name of the template to be used by this action
	 * <p>
	 * Exactly one template needs to be specified. The Template is automatically loaded during
	 * initialization of the Servlet. If the template does not exist or if contains errors (so that
	 * it can not be compiled) an Exception will be thrown by the {@link TemplateManager} which
	 * attempted to load the Template. If that again happens during initialization of a Controller
	 * which is loading its Modules, the initialization will fail.
	 * 
	 * @since Iteration2
	 * @return the Name of the template.
	 */
	String template();
}
