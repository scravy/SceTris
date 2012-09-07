/* Ariog.java / 5:45:26 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.tests;


import javax.xml.transform.TransformerFactory;

import org.junit.Test;

/**
 *
 */
public class AriogTest {

    /**
     * @throws java.lang.Exception
     */
    @Test
    public void setUp() throws Exception {
        System.out.println(TransformerFactory.newInstance().getClass());
    }

}
