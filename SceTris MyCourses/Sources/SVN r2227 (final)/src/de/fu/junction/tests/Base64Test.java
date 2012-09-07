/* Base64Test.java / 1:20:50 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import java.io.Serializable;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import de.fu.junction.Base64;
import de.fu.junction.Tuple;

/**
 *
 */
@RunWith(Theories.class)
public class Base64Test {

	public static class SomeObject implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public String $string;
		public int $integer;
		public double $double;

		public SomeObject $object;

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			SomeObject other = (SomeObject) obj;
			if (Double.doubleToLongBits($double) != Double.doubleToLongBits(other.$double)) {
				return false;
			}
			if ($integer != other.$integer) {
				return false;
			}
			if ($object == null) {
				if (other.$object != null) {
					return false;
				}
			} else if (!$object.equals(other.$object)) {
				return false;
			}
			if ($string == null) {
				if (other.$string != null) {
					return false;
				}
			} else if (!$string.equals(other.$string)) {
				return false;
			}
			return true;
		}

	}

	public static final String $string = "Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure.";

	public static final String $base64 = "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlzIHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2YgdGhlIG1pbmQsIHRoYXQgYnkgYSBwZXJzZXZlcmFuY2Ugb2YgZGVsaWdodCBpbiB0aGUgY29udGludWVkIGFuZCBpbmRlZmF0aWdhYmxlIGdlbmVyYXRpb24gb2Yga25vd2xlZGdlLCBleGNlZWRzIHRoZSBzaG9ydCB2ZWhlbWVuY2Ugb2YgYW55IGNhcm5hbCBwbGVhc3VyZS4=";

	@DataPoints
	public static String[] data() {
		return new String[] {
				"a",
				"hallo",
				"Ästhetik",
				"Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure." };
	}

	@Theory
	public void encodeDecode(final String $val) throws Exception {
		String $val2 = new String(Base64.decode(Base64.encode($val)));
		assertEquals($val, $val2);
	}

	@Test
	public void testEncode() throws Exception {
		String $encoded = Base64.encode($string.getBytes("UTF-8"));
		assertEquals($base64, $encoded);
	}

	@Test
	public void testSerialization() {
		SomeObject $object = new SomeObject();
		$object.$double = Math.PI;
		$object.$string = "Hello World: ";
		$object.$integer = 4711;
		$object.$object = new SomeObject();
		$object.$object.$string = "Good afternoon, Mr. Twinkle";
		$object.$object.$object = new SomeObject();
		SomeObject $zombie = Base64.decode(Base64.encode($object), SomeObject.class);
		assertEquals($object.$double, $zombie.$double, 0);
		assertEquals($object.$string, $zombie.$string);
		assertEquals($object.$integer, $zombie.$integer);
		assertEquals($object.$object, $zombie.$object);
		assertEquals($object, $zombie);
	}

	@Theory
	public void testSerializationTuple(final String $data) {
		String $intermediate = Base64.encode(Tuple.t(1, $data));
		assertEquals($data, Base64.decode($intermediate, Tuple.class).snd);
	}
}
