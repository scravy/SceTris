/* Files.java / 2:21:29 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import de.fu.junction.annotation.meta.Author;

/**
 *
 */
@Author("Julian Fleischer")
public class Files {
	/**
	 * 
	 * @param $filename
	 * @return
	 */
	public static byte[] getBytes(final String $filename) {
		File $file = new File($filename);
		long $size = $file.length();
		try {
			if ($size <= Integer.MAX_VALUE) {
				byte[] $bytes = new byte[(int) $size];
				int $c, $i = 0;
				InputStream $in = new FileInputStream($file);
				while (($c = $in.read()) >= 0)
					$bytes[$i++] = (byte) ($c - 128);
				return $bytes;

			}
		} catch (Exception $exc) {
		}
		return null;
	}

	public static String getContents(final String $filename) {
		byte[] $bytes = getBytes($filename);
		return $bytes == null ? null : new String($bytes);
	}

	public static String getContents(final String $filename, final Charset $charset) {
		byte[] $bytes = getBytes($filename);
		return $bytes == null ? null : new String($bytes, $charset);
	}

	public static String getContents(final String $filename, final String $charset) {
		byte[] $bytes = getBytes($filename);
		try {
			return new String($bytes, $charset);
		} catch (Exception $exc) {
			return null;
		}
	}

	public static boolean isDir(final String $filename) {
		return new File($filename).isDirectory();
	}

	public static boolean isFile(final String $filename) {
		return new File($filename).isFile();
	}
}
