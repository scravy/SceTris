/* XmlImportTest.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests.stress;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;
import org.w3c.dom.Document;


import de.fu.bakery.orm.java.imex.Importer;
import de.fu.bakery.orm.java.imex.XmlImporter;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;
import de.fu.weave.xml.Namespaces;
import de.fu.weave.xml.XmlWriter;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class XmlImportTest extends DatabaseTestSkeleton {

	/**
	 * End of message
	 * 
	 * @author Julian Fleischer
	 * @since Iteration4
	 */
	private final static class EOM {
	}

	@Test
	public void testImExImEx() throws Exception {
		Document originalDoc = xmlHelper.newDocument("sampleExport.xml");
		int noRowsOriginal = originalDoc.getDocumentElement()
				.getElementsByTagNameNS(Namespaces.XMLNS_EXPORT, "row").getLength();
		manager.importXML(originalDoc);

		Document doc1 = xmlHelper.newDocument(manager.export(false).getBinaryStream());
		int noRows1 = doc1.getDocumentElement()
				.getElementsByTagNameNS(Namespaces.XMLNS_EXPORT, "row").getLength();
		manager.dropSchema();
		manager.install();
		manager.importXML(doc1);

		Document doc2 = xmlHelper.newDocument(manager.export(false).getBinaryStream());
		int noRows2 = doc2.getDocumentElement()
						.getElementsByTagNameNS(Namespaces.XMLNS_EXPORT, "row").getLength();

		assertEquals(noRows1, noRows2);
		assertTrue(noRows1 >= noRowsOriginal);
	}

	@Test
	public void testXmlExImExImEx() throws Exception {
		manager.importXML(xmlHelper.newDocument("sampleExport.xml"));
		Document doc = xmlHelper.newDocument(manager.export(false).getBinaryStream());

		manager.importXML(doc);
		Document doc1 = xmlHelper.newDocument(manager.export(false).getBinaryStream());

		manager.importXML(doc1);
		Document doc2 = xmlHelper.newDocument(manager.export(false).getBinaryStream());

		StringWriter xml1 = new StringWriter();
		StringWriter xml2 = new StringWriter();
		new XmlWriter(new PrintWriter(xml1)).write(doc1);
		new XmlWriter(new PrintWriter(xml2)).write(doc2);
		String str1 = xml1.toString();
		String str2 = xml2.toString();
		assertEquals(str1.length(), str2.length());
		assertEquals(str1, str2);
	}

	@Test
	public void testXmlExportTwice() throws Exception {
		manager.importXML(xmlHelper.newDocument("sampleExport.xml"));

		Document doc1 = xmlHelper.newDocument(manager.export(false).getBinaryStream());
		Document doc2 = xmlHelper.newDocument(manager.export(false).getBinaryStream());

		StringWriter xml1 = new StringWriter();
		StringWriter xml2 = new StringWriter();
		new XmlWriter(new PrintWriter(xml1)).write(doc1);
		new XmlWriter(new PrintWriter(xml2)).write(doc2);
		String str1 = xml1.toString();
		String str2 = xml2.toString();
		assertEquals(str1.length(), str2.length());
		assertEquals(str1, str2);
	}

	@Test
	public void testXmlImport() throws Exception {
		long start = System.currentTimeMillis();
		manager.importXML(xmlHelper.newDocument("sampleExport.xml"));
		System.out.println((System.currentTimeMillis() - start) / 1000.0);
	}

	@Test
	public void testXmlImportWithMessages() throws Exception {
		XmlImporter importer = new XmlImporter(manager);
		final BlockingQueue<Object> messages = importer.getMessages();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Object message;
				try {
					while (!((message = messages.take()) instanceof EOM)) {
						if (message instanceof Throwable) {
							((Throwable) message).printStackTrace(System.err);
						} else if (message instanceof Importer.Warning) {
							System.err.println(message.toString());
						} else {
							System.out.println(message.toString());
						}
					}
				} catch (InterruptedException e) {
					System.err.println("Interrupted.");
				}
				System.out.println();
			}
		}).start();
		long start = System.currentTimeMillis();
		manager.importCustom(xmlHelper.newDocument("sampleExport.xml"), importer);
		System.out.println((System.currentTimeMillis() - start) / 1000.0);
		messages.offer(new EOM());
	}
}
