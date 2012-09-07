/* Plain.java / 5:26:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.output;

import java.io.FileNotFoundException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class LaTeX extends AbstractOutputConverter {

	public LaTeX() throws ParserConfigurationException, TransformerConfigurationException,
			FileNotFoundException {
		super(true, "text/plain", "UTF-8", "LaTeX.xsl");
		transformer.setOutputProperty(OutputKeys.METHOD, "text");
	}
}
