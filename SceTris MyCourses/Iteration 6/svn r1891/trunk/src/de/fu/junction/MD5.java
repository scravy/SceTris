/* MessageDigest.java / 7:19:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.security.NoSuchAlgorithmException;

/**
 * Provides a PHP-like implementation for md5. Should be statically imported.
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class MD5 {

	/**
	 * 
	 * @since Iteration2
	 */
	private static java.security.MessageDigest algorithm;

	static {
		try {
			algorithm = java.security.MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			algorithm = null;
		}
	}

	/**
	 * Import this method using "import static "
	 * 
	 * @param string
	 * @return
	 * @since Iteration2
	 */
	public static String md5(final String string) {
		algorithm.reset();
		algorithm.update(string.getBytes());
		byte messageDigest[] = algorithm.digest();

		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < messageDigest.length; i++) {
			String hex = Integer.toHexString(0xFF & messageDigest[i]);
			if (hex.length() < 2) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

}
