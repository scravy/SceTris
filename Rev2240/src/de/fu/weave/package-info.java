/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

/**
 * Weave is a tiny web-framework for Java which is developed along with Scetris myCourses during the SCORE Contest 2011. 
 * <p>
 * Weave is built around the Java Servlet API and thus runs without any other dependencies in any
 * Servlet Container, such as Tomcat or Jetty. The general idea is, that a Servlet (called Weavlet)
 * is acting as a controller which delegates requests to Modules, which contain the actual business logic.
 * Weave is designed to be as generic as possible, i.e. things are abstracted as much as possible.
 * 
 * <h3>Design</h3>
 * While the current implementation is focused on Java servlets, the design is intended to abstract
 * from the fact that the application is running as a web-application. In theory it is perfectly possible
 * to imagine a weave application to be run as a CLI (command line interface) or GUI (graphical user interface)
 * application. A Weave application is split
 * into Modules which are further separated into Actions. Actions are basically functions and should not
 * have any side effects. Modules are required to return an XML-Document (typically represented by
 * {@see org.w3c.dom.Document}) which is than processed by the {@see de.fu.weave.Controller}. A controller which is a
 * servlet is called a <q>Weavlet</q>.
 * <p>
 * Weave applications are primarily applications dealing with a database. Thus every component has access
 * to a {@see de.fu.weave.orm.RelationManager}, which is unique to every application.
 * 
 * <h4>Weavlet</h4>
 * A Weavlet is a Weave-Controller implemented as a Servlet. It features 
 * 
 * <h4>Forms</h4>
 * A major issue in decoupling business logic and controlling logic is how to deal with user data.
 * Thus, forms are represented as Java classes. The real forms are created by a form-builder to target
 * the desired output medium (GUI, CLI, PDF, XHTML, XForms, ...). See {@see de.fu.weave.Form}.
 * 
 * <h4>Future thoughts</h4>
 * In principle it does not matter whether modules are running inside a Weavlet or somewhere else.
 * A Weave-Controller could possibly be implemented using {@see javax.swing Swing}, thus turning a
 * web application into a gui application. Of course, the form-builder (which is realised via XSL-T
 * in the current implementation {@see de.fu.weave.impl.frigg}) will have to be newly created.
 * 
 * <h3>Contents</h3>
 * This package contains interfaces, definitions and default implementations (such as {@see Session}).
 * 
 */
@Author({ "Julian Fleischer", "André Zoufahl" })
package de.fu.weave;


import de.fu.junction.annotation.meta.Author;

