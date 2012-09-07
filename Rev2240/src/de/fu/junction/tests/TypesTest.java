/* TypesTest.java / 1:18:47 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static de.fu.junction.Types.*;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 *
 */
public class TypesTest {
	@Test
	public void testIsFloating() {
		assertTrue(isFloating(7e4));
	}

	@Test
	public void testIsNatural() {
		assertTrue(isNatural(7));
		assertTrue(isNatural(7L));
		assertTrue(isNatural(7.0));
		assertFalse(isNatural(7.7));
		assertFalse(isNatural(7.7f));
		assertTrue(isNatural(new Double("7.0")));
		assertTrue(isNatural(new Float("7.0")));
		assertFalse(isNatural(new Double("7.7")));
		assertTrue(isNatural(new Long(7)));
	}

	@Test
	public void testIsNumberClass() {
		assertTrue(isNumber(Integer.class));
		assertTrue(isNumber(Float.class));
		assertTrue(isNumber(BigDecimal.class));
		assertTrue(isNumber(BigInteger.class));
		assertTrue(isNumber(Double.class));
		assertTrue(isNumber(Long.class));
		assertTrue(isNumber(Short.class));
		assertTrue(isNumber(Byte.class));
		assertFalse(isNumber(String.class));
		assertFalse(isNumber(Character.class));
	}

	@Test
	public void testIsNumberObject() {
		assertTrue(isNumber(new Integer("3")));
		assertTrue(isNumber(new Float(Math.PI)));
		assertTrue(isNumber(new BigDecimal("3")));
		assertTrue(isNumber(new BigInteger("7")));
		assertTrue(isNumber(new Double(Math.E)));
		assertTrue(isNumber(new Long(7)));
		assertTrue(isNumber(new Short("123")));
		assertTrue(isNumber(new Byte("8")));
		assertFalse(isNumber(new String()));
		assertFalse(isNumber(new Character('x')));
	}

	@Test
	public void testIsNumberPrimitives() {
		assertTrue(isNumber(3));
		assertTrue(isNumber(Math.PI));
		assertTrue(isNumber(3.0));
		assertTrue(isNumber(3f));
		assertTrue(isNumber(3L));
		assertFalse(isNumber(""));
		assertFalse(isNumber('x'));
	}

	@Test
	public void testIsNumberPrimitivesClass() {
		assertTrue(isNumber(int.class));
		assertTrue(isNumber(float.class));
		assertTrue(isNumber(double.class));
		assertTrue(isNumber(long.class));
		assertTrue(isNumber(short.class));
		assertTrue(isNumber(byte.class));
		assertFalse(isNumber(char.class));
	}

	@Test
	public void testIsPrimitive() {
		assertTrue(isPrimitive(int.class));
		assertFalse(isPrimitive(Object.class));
		assertFalse(isPrimitive(Serializable.class));
		assertFalse(isPrimitive(Iterable.class));
		assertTrue(isPrimitive(boolean.class));
		assertTrue(isPrimitive(int.class));
		assertTrue(isPrimitive(long.class));
		assertTrue(isPrimitive(short.class));
		assertTrue(isPrimitive(byte.class));
		assertTrue(isPrimitive(double.class));
		assertTrue(isPrimitive(float.class));
		assertFalse(isPrimitive(Integer.class));
		assertFalse(isPrimitive(BigInteger.class));
		assertFalse(isPrimitive(String.class));
		assertFalse(isPrimitive(Character.class));
		assertFalse(isPrimitive(Boolean.class));
		assertFalse(isPrimitive(Long.class));
		assertFalse(isPrimitive(Double.class));
		assertFalse(isPrimitive(Float.class));
		assertFalse(isPrimitive(getClass()));
	}

	@Test
	public void testIsString() {
		assertTrue(isString(""));
		assertFalse(isString(8));
	}

	@Test
	public void testIsStringClass() {
		assertTrue(isString("".getClass()));
		assertTrue(isString(String.class));
		assertFalse(isString(new Character('c').getClass()));
		assertFalse(isString(Character.class));
	}
}
