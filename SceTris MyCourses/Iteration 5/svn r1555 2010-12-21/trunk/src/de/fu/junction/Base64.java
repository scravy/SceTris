/* Base64.java / 12:54:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import static java.util.zip.Deflater.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 *
 */
public class Base64 {

	private final static char[] ALPHABET = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z',
			'0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9',
			'+', '/' };

	private static int[] toInt = new int[128];

	static {
		for (int i = 0; i < ALPHABET.length; i++) {
			toInt[ALPHABET[i]] = i;
		}
	}

	/**
	 * Translates the specified Base64 string into a byte array.
	 * 
	 * @param $string
	 *            The Base64 string (not null)
	 * @return The byte array (not null)
	 */
	public static byte[] decode(final String $string) {
		int delta = $string.endsWith("==") ? 2 : $string.endsWith("=") ? 1 : 0;
		byte[] buffer = new byte[$string.length() * 3 / 4 - delta];
		int mask = 0xFF;
		int index = 0;
		for (int i = 0; i < $string.length(); i += 4) {
			int c0 = toInt[$string.charAt(i)];
			int c1 = toInt[$string.charAt(i + 1)];
			buffer[index++] = (byte) (((c0 << 2) | (c1 >> 4)) & mask);
			if (index >= buffer.length) {
				return buffer;
			}
			int c2 = toInt[$string.charAt(i + 2)];
			buffer[index++] = (byte) (((c1 << 4) | (c2 >> 2)) & mask);
			if (index >= buffer.length) {
				return buffer;
			}
			int c3 = toInt[$string.charAt(i + 3)];
			buffer[index++] = (byte) (((c2 << 6) | c3) & mask);
		}
		return buffer;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T decode(final String $string, final Class<T> $type) {
		try {
			ByteArrayInputStream $bytes = new ByteArrayInputStream(decode($string));
			InputStream $filter = new InflaterInputStream($bytes);
			ObjectInputStream $in = new ObjectInputStream($filter);
			return (T) $in.readObject();
		} catch (Exception $exc) {
			return null;
		}
	}

	/**
	 * Translates the specified byte array into Base64 string.
	 * 
	 * @param $buffer
	 *            The byte array (not null)
	 * @return the translated Base64 string (not null)
	 */
	public static String encode(final byte[] $buffer) {
		int size = $buffer.length;
		char[] ar = new char[((size + 2) / 3) * 4];
		int a = 0;
		int i = 0;
		while (i < size) {
			byte b0 = $buffer[i++];
			byte b1 = (i < size) ? $buffer[i++] : 0;
			byte b2 = (i < size) ? $buffer[i++] : 0;

			int mask = 0x3F;
			ar[a++] = ALPHABET[(b0 >> 2) & mask];
			ar[a++] = ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & mask];
			ar[a++] = ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & mask];
			ar[a++] = ALPHABET[b2 & mask];
		}
		switch (size % 3) {
		case 1:
			ar[--a] = '=';
		case 2:
			ar[--a] = '=';
		}
		return new String(ar);
	}

	public static String encode(final Serializable $object) {
		try {
			ByteArrayOutputStream $bytes = new ByteArrayOutputStream();
			OutputStream $filter = new DeflaterOutputStream($bytes, new Deflater(BEST_COMPRESSION));
			ObjectOutputStream $out = new ObjectOutputStream($filter);
			$out.writeObject($object);
			$out.close();
			return encode($bytes.toByteArray());
		} catch (IOException $exc) {
			return null;
		}
	}

	public static String encode(final String $string) {
		try {
			return encode($string.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException $exc) {
			return encode($string.getBytes());
		}
	}
}
