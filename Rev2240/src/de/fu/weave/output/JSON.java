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
public class JSON extends AbstractOutputConverter {

	public JSON() throws ParserConfigurationException, TransformerConfigurationException,
			FileNotFoundException {
		super(false, "application/json", "UTF-8", "JSON.xsl");
		transformer.setOutputProperty(OutputKeys.METHOD, "text");
	}
}
