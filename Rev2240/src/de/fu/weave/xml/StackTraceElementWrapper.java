/* TraceElement.java / 1:13:26 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlElement;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@XmlElement("trace-element")
class StackTraceElementWrapper {

	/**
	 * {@link #getClassName()}
	 */
	private final String className;

	/**
	 * {@link #getLineNumber()}
	 */
	private final int lineNumber;

	/**
	 * {@link #getFileName()}
	 */
	private final String fileName;

	/**
	 * {@link #getMethodName()}
	 */
	private final String methodName;

	/**
	 * 
	 * @param e
	 * @since Iteration2
	 */
	StackTraceElementWrapper(final StackTraceElement e) {
		className = e.getClassName();
		lineNumber = e.getLineNumber();
		fileName = e.getFileName();
		methodName = e.getMethodName();
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("class")
	public String getClassName() {
		return className;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("file")
	public String getFileName() {
		return fileName;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("line")
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("method")
	public String getMethodName() {
		return methodName;
	}

}
