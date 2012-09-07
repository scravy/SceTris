/* CallbacksTest.java / 7:31:20 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static de.fu.junction.dynamic.D.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.junction.dynamic.Callable;
import de.fu.junction.dynamic.Callback;
import de.fu.junction.dynamic.DynamicInvocationTargetException;
import de.fu.junction.functional.Function;
import de.fu.junction.functional.Function2;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class CallbacksTest {

	public static String append(final String string) {
		return string + " appendix";
	}

	public static String appendAnything(final String string, final String anything) {
		return string + anything;
	}

	public static int overloadedMethod(final Integer arg1) {
		return arg1 * 2;
	}

	public static int overloadedMethod(final Integer arg1, final Integer arg2) {
		return arg1 + arg2;
	}

	public static int overloadedMethod(final Integer arg1, final Integer arg2, final Integer arg3) {
		return arg1 * arg2 * arg3;
	}

	public static char overloadedMethod(final String string) {
		return string.charAt(0);
	}

	@Test
	public void testCallback() {
		Object result = callback(getClass(), "append").call("something");
		assertEquals(result, "something appendix");
	}

	@Test
	public void testCallbackObject() {
		Callback c = new Callback(this, "append");
		Object x = c.call("prefix");
		assertEquals(x.getClass().getCanonicalName(), "java.lang.String");
		assertEquals(x, "prefix appendix");
	}

	@Test
	public void testCallbackStatic() {
		Callback c = new Callback(getClass(), "append");
		Object x = c.call("prefix");
		assertEquals(x.getClass().getCanonicalName(), "java.lang.String");
		assertEquals(x, "prefix appendix");
	}

	@Test
	public void testCallbackWithArgumentsObject() {
		Callback c = new Callback(this, "appendAnything");
		Object x = c.call("a", "b");
		assertEquals(x, "ab");
	}

	@Test
	public void testCallbackWithArgumentsStatic() {
		Callback c = new Callback(getClass(), "appendAnything");
		Object x = c.call("a", "b");
		assertEquals(x, "ab");
	}

	@Test
	public void testFunction2FromCallback() throws Exception {
		Function2<String,String,?> f = new Callback(getClass(), "appendAnything")
				.getFunction2(String.class, String.class);
		assertEquals("HelloWorld", f.call("Hello", "World"));
	}

	@Test
	public void testFunction2FromCallbackWithKnownReturnType() throws Exception {
		Function2<String,String,String> f = new Callback(getClass(), "appendAnything")
				.getFunction2(String.class, String.class, String.class);
		assertEquals("HelloWorld", f.call("Hello", "World"));
	}

	@Test
	public void testFunctionFromCallback() throws Exception {
		Function<String,?> f = new Callback(getClass(), "append").getFunction(String.class);
		assertEquals("something appendix", f.call("something"));
	}

	@Test
	public void testFunctionFromCallbackWithKnownReturnType() throws Exception {
		Function<String,String> f = new Callback(getClass(), "append").getFunction(String.class,
																				   String.class);
		assertEquals("something appendix", f.call("something"));
	}

	@Test(expected = DynamicInvocationTargetException.class)
	public void testNoSuchMethod1() {
		callback(getClass(), "missing").call();
	}

	@Test(expected = DynamicInvocationTargetException.class)
	public void testNoSuchMethod2() {
		callback(getClass(), "overloadedMethod").call();
	}

	@Test(expected = DynamicInvocationTargetException.class)
	public void testNoSuchMethod3() {
		callback(this, "overloadedMethod").call('c', 7);
	}

	@Test
	public void testOverloadedCallback() {
		Object result;
		Callable<?> callback = callback(this, "overloadedMethod");
		result = callback.call("Hello World");
		assertEquals("H", result.toString());
		result = callback.call(7);
		assertEquals(14, result);
		result = callback.call(23, 42);
		assertEquals(65, result);
		result = callback.call(2, 2, 2);
		assertEquals(8, result);
	}

	@Test
	public void testOverloadedStaticMethodCallback() {
		Object result;
		Callable<?> callback = callback(getClass(), "overloadedMethod");
		result = callback.call("Hello World");
		assertEquals("H", result.toString());
		result = callback.call(7);
		assertEquals(14, result);
		result = callback.call(23, 42);
		assertEquals(65, result);
		result = callback.call(2, 2, 2);
		assertEquals(8, result);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testUnknownFunction2FromCallback() throws Exception {
		new Callback(getClass(), "appendAnything")
				.getFunction2(String.class, int.class);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testUnknownFunctionFromCallback() throws Exception {
		new Callback(getClass(), "append").getFunction(int.class);
	}
}
