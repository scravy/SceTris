/* StringToDOMDocument.java / 12:53:19 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import org.w3c.dom.Document;

import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToDOMDocument implements StringConverter<Document> {

	private final XmlHelper xmlHelper;

	StringToDOMDocument() throws ConverterInstantiationException {
		try {
			xmlHelper = new XmlHelper();
		} catch (Exception e) {
			throw new ConverterInstantiationException(e);
		}
	}

	@Override
	public Document convert(final String value) {
		try {
			return xmlHelper.newDocument(value.getBytes());
		} catch (Exception e) {
			return null;
		}
	}
}
