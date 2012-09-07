/* ArrayConverter.java / 11:44:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.junction.converter.StringConverter;

public class LoginNameSanitizer implements StringConverter<String> {
	@Override
	public String convert(final String $string) {
		return $string.trim().toLowerCase();
	}
}
