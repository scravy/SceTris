/* PrintableException.java / 5:33:40 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class PrintableException extends Exception {

	/**
	 * 
	 * @since Iteration2
	 */
	private final Throwable originalException;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4864624108757297729L;

	/**
	 * Constructs a new PrintableException from an already existing Exception
	 * 
	 * @param e
	 *            The Exception
	 * @since Iteration2
	 */
	public PrintableException(final Throwable e) {
		if ((e instanceof ServletException) || (e instanceof InvocationTargetException)) {
			originalException = e.getCause();
		} else {
			originalException = e;
		}
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlCollection("cause")
	public Collection<ExceptionWrapper> getCauses() {

		Collection<ExceptionWrapper> causes = new ArrayList<ExceptionWrapper>();
		Throwable e = originalException;
		do {
			causes.add(new ExceptionWrapper(e));
			e = e.getCause();
		} while (e != null);
		return causes;

	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@Override
	@XmlAttribute("message")
	public String getMessage() {
		return originalException.getMessage();
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("name")
	public String getName() {
		return originalException.getClass().getSimpleName();
	}

	/**
	 * @return The originalException.
	 * @since Iteration2
	 */
	public Throwable getOriginalException() {
		return originalException;
	}

}
