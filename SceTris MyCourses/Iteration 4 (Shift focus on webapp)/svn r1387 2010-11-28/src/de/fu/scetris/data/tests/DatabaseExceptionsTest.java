/* AllRelationsTest.java / 9:03:35 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.Entity;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.util.ClassesComparator;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
@RunWith(Theories.class)
public class DatabaseExceptionsTest extends DatabaseTestSkeleton {

	@DataPoints
	public static Class<?>[] relationClasses;

	static {
		Method[] methods = RelationManager.class.getMethods();
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Method m : methods) {
			if (m.getName().startsWith("new")) {
				classes.add(m.getReturnType());
			}
		}
		relationClasses = classes.toArray(new Class<?>[0]);
	}

	@Override
	protected boolean _doInstall() {
		return false;
	}

	@Override
	protected void _init() {

	}

	@Theory
	public void testExceptionOnExport(final Class<?> c) throws Throwable {
		manager.dropSchema();

		Method m = manager.getClass().getMethod("export" + c.getSimpleName(), boolean.class);

		try {
			m.invoke(manager, new Object[] { new Boolean(false) });
			fail("No DatabaseException");
		} catch (InvocationTargetException e) {
			if (!e.getCause().getClass().equals(DatabaseException.class)) {
				throw e.getCause();
			}
		}
	}

	@Theory
	public void testExceptionOnGetById(final Class<?> c) throws Throwable {
		if (!Entity.class.isAssignableFrom(c)) {
			return;
		}

		manager.dropSchema();

		Method idMethod = c.getMethod("id");
		Class<?> idType = idMethod.getReturnType();

		Method m = manager.getClass().getMethod("get" + c.getSimpleName(), idType);
		Map<Class<?>,Object> values = new TreeMap<Class<?>,Object>(ClassesComparator.comparator);
		values.put(int.class, 1);
		values.put(Integer.class, 1);
		values.put(String.class, "");

		try {
			m.invoke(manager, new Object[] { values.get(idType) });
			fail("No DatabaseException");
		} catch (InvocationTargetException e) {
			if (!e.getCause().getClass().equals(DatabaseException.class)) {
				throw e.getCause();
			}
		}
	}

	@Theory
	public void testExceptionsOnSelect(final Class<?> c) throws Throwable {
		if (!Entity.class.isAssignableFrom(c)) {
			return;
		}
		manager.dropSchema();

		Method m = manager.getClass().getMethod("get" + c.getSimpleName());
		try {
			m.invoke(manager);
			fail("No DatabaseException");
		} catch (InvocationTargetException e) {
			if (!e.getCause().getClass().equals(DatabaseException.class)) {
				throw e.getCause();
			}
		}
	}
}
