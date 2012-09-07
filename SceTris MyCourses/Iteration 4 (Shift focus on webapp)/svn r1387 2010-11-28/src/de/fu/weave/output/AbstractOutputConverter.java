/* Plain.java / 5:26:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.output;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import de.fu.weave.OutputConverter;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class AbstractOutputConverter implements OutputConverter {

	private final boolean isAppliedAfterNativeTemplate;
	private final String mimeType;
	private final String charset;

	/**
	 * 
	 */
	final protected Transformer transformer;

	public AbstractOutputConverter(final boolean nativeTemplates, final String mimeType,
			final String charset, final String template)
			throws ParserConfigurationException, TransformerConfigurationException,
			FileNotFoundException {
		if (mimeType == null) {
			throw new IllegalArgumentException();
		}
		isAppliedAfterNativeTemplate = nativeTemplates;
		this.mimeType = mimeType;
		this.charset = charset;
		XmlHelper xmlHelper = new XmlHelper();
		InputStream resource = getClass().getResourceAsStream(template);
		if (resource == null) {
			throw new FileNotFoundException(template);
		}
		transformer = xmlHelper.newTransformer(resource);
	}

	@Override
	public void convert(final Source source, final StreamResult result) throws TransformerException {
		transformer.transform(source, result);
	}

	@Override
	final public String getCharacterEncoding() {
		return charset;
	}

	@Override
	final public String getMimeType() {
		return mimeType;
	}

	@Override
	final public boolean isAppliedAfterNativeTemplate() {
		return isAppliedAfterNativeTemplate;
	}
}
