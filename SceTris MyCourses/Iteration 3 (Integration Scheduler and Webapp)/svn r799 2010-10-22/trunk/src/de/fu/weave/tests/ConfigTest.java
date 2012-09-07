/* ConfigTest.java / 11:16:05 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.fu.weave.Config;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class ConfigTest {

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
	public void fromXML() throws ParserConfigurationException, IOException, SAXException {
		XmlHelper xmlHelper = new XmlHelper();

		String xmlString =
				"<config xmlns=\"http://technodrom.scravy.de/2010/scetris/config\">"
						+ "  <de.fu.weave.Test>"
						+ "    <section>"
						+ "      <item key=\"value\" />"
						+ "    </section>"
						+ "  </de.fu.weave.Test>"
						+ "</config>";

		Document doc = xmlHelper.newDocument(xmlString.getBytes(Charset.forName("UTF-8")));

		Config conf = new Config(xmlHelper, doc, "de.fu.weave.Test/section");

		assertEquals(conf.get("item/key"), "value");
	}
}
