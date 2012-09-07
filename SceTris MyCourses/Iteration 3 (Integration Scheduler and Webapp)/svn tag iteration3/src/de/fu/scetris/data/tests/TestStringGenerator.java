/* StringGenerator.java / 10:41:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

public class TestStringGenerator implements TestValueGenerator<String> {
	@Override
	public String generate() {
		return "jammiJammi" + Math.random();
	}
}