/* Password.java / 2:26:46 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

import static de.fu.junction.MD5.*;
import de.fu.junction.annotation.meta.Author;

/**
 * A Password.
 */
@Author("Julian Fleischer")
public class Password implements CharSequence {

	private final String $password;

	/**
	 * 
	 * @param $password
	 */
	public Password(final String $password) {
		this.$password = $password;
	}

	@Override
	public char charAt(final int $pos) {
		return $password.charAt($pos);
	}

	/**
	 * Check whether two passwords equal each other.
	 * 
	 * @param $o
	 * @return
	 */
	public boolean equalsMD5(final Password $o) {
		String $myMD5 = getMD5();
		String $herMD5 = ((Password) $o).getMD5();
		boolean $equals = true;
		for (int $i = 0; $i < 32; $i++)
			if ($myMD5.charAt($i) != $herMD5.charAt($i)) $equals = false;
		return $equals;
	}

	/**
	 * Get the MD5-hash of this password.
	 * 
	 * @return
	 */
	public String getMD5() {
		return md5($password);
	}

	/**
	 * Returns the password that this Password object holds as a String
	 * <p>
	 * Use with care, it might be better suited to use getMD5().
	 * 
	 * @return The password stored in this object as a String
	 */
	public String getPassword() {
		return $password;
	}

	@Override
	public int length() {
		return $password.length();
	}

	@Override
	public CharSequence subSequence(final int $beginIndex, final int $endIndex) {
		return $password.subSequence($beginIndex, $endIndex);
	}

	/**
	 * Return always the empty String.
	 * <p>
	 * Since the Password class is for pure convenience and only eases Javas expressivity its sole
	 * use is to represent the value password. A password should never be shown, but the toString()
	 * method is typically used to return the String value of an object (for printing it in a form
	 * for example). This should not happend with a password, thus it returns the empty string.
	 * 
	 * @return A string of zero length
	 */
	@Override
	public String toString() {
		return "";
	}
}
