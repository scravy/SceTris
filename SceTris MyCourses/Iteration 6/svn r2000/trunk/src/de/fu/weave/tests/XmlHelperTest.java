/* XmlHelperTest.java / 2:17:41 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.fu.junction.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class XmlHelperTest {

	protected static XmlHelper xmlHelper;

	/**
	 * @throws java.lang.Exception
	 * @since Iteration3
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws ParserConfigurationException {
		xmlHelper = new XmlHelper();
	}

	@Test
	public void testNewDocument() {
		assertNotNull(xmlHelper.newDocument());
	}

	@Test
	public void testNewDocumentFromByteArray() throws IOException, SAXException {
		String document = "<simple-test />\n";
		Document doc = xmlHelper.newDocument(document.getBytes(Charset.forName("UTF-8")));
		assertNotNull(doc);
		assertNotNull(doc.getDocumentElement());
		assertEquals("simple-test", doc.getDocumentElement().getNodeName());
	}
}
