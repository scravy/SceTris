/* ConfigTest.java / 11:16:05 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.fu.junction.xml.XmlHelper;
import de.fu.weave.util.Config;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class ConfigTest {

	private XmlHelper xmlHelper;

	@Before
	public void _setup() throws ParserConfigurationException {
		xmlHelper = new XmlHelper();
	}

	@Test
	public void fromMap() {
		Map<String,String> map = new TreeMap<String,String>();

		map.put("key", "value");
		map.put("foo", "bar");

		Config conf = new Config(map);
		assertEquals(conf.get("key"), map.get("key"));
		assertEquals(conf.get("foo"), map.get("foo"));
	}

	@Test
	public void fromXML() throws IOException, SAXException {
		String xmlString =
				"<configuration xmlns=\"http://technodrom.scravy.de/2010/scetris/configuration\">"
						+ "  <de.fu.weave.Test>"
						+ "    <section>"
						+ "      <item key=\"value\" />"
						+ "    </section>"
						+ "  </de.fu.weave.Test>"
						+ "</configuration>";

		Document doc = xmlHelper.newDocument(xmlString.getBytes(Charset.forName("UTF-8")));

		Config conf = new Config(xmlHelper, doc, "de.fu.weave.Test/section");

		assertEquals(conf.get("item/key"), "value");
	}

	@Test
	public void fromXMLDocumentElement() throws IOException, SAXException {
		String xmlConfig = "<configuration><database>\n"
				+ "<user>postgres</user>"
				+ "<password></password>"
				+ "<host>localhost</host>"
				+ "<name>postgres</name>"
				+ "</database></configuration>\n";
		Config conf =
				new Config(xmlHelper, xmlHelper.newDocument(xmlConfig.getBytes(Charset
						.forName("UTF-8"))).getDocumentElement());

		assertEquals("localhost", conf.get("host"));
		assertEquals("", conf.get("password"));
		assertNull(conf.get("unknown-key"));
	}
}
