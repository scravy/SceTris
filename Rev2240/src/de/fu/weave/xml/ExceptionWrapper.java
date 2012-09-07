/* ExceptionWrapper.java / 1:09:24 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import java.util.ArrayList;
import java.util.Collection;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
class ExceptionWrapper {
	/**
	 * 
	 * @since Iteration2
	 */
	final Throwable exception;

	/**
	 * @param e
	 * @since Iteration2
	 */
	ExceptionWrapper(final Throwable e) {
		exception = e;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("class")
	public String getClassName() {
		return exception.getClass().getName();
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("message")
	public String getMessage() {
		return exception.getMessage();
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	@XmlCollection("trace")
	public Collection<StackTraceElementWrapper> stackTrace() {
		ArrayList<StackTraceElementWrapper> trace = new ArrayList<StackTraceElementWrapper>();
		for (StackTraceElement e : exception.getStackTrace()) {
			trace.add(new StackTraceElementWrapper(e));
		}
		return trace;
	}
}
