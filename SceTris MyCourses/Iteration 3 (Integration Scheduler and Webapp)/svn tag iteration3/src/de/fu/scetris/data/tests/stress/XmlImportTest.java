/* Performance.java / 4:32:48 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests.stress;

import org.junit.Test;

import de.fu.weave.tests.DatabaseTestSkeleton;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class XmlImportTest extends DatabaseTestSkeleton {

	@Test
	public void testXmlImport() throws Exception {
		manager.importXML(xmlHelper
				.newDocument("/Users/julianfleischer/Desktop/SCORE-Iteration3/sampleExport.xml"));
	}

}
