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
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import de.fu.bakery.orm.java.Entity;
import de.fu.bakery.orm.java.OutdatedRecordException;
import de.fu.bakery.orm.java.Relation;
import de.fu.bakery.orm.java.Relationship;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;
import de.fu.junction.ClassesComparator;
import de.fu.junction.Dynamic;
import de.fu.junction.Strings;
import de.fu.weave.tests.util.TestStringGenerator;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class AllRelationsTest extends DatabaseTestSkeleton {

	protected ClassesComparator cc = ClassesComparator.comparator;

	protected Map<Class<?>,Method> relationCreators = new TreeMap<Class<?>,Method>(cc);

	List<Class<?>> avail = new ArrayList<Class<?>>();

	Map<Class<?>,Object> resources = new TreeMap<Class<?>,Object>(cc);

	@Override
	public void _init() throws Exception {

		Method[] methods = manager.getClass().getMethods();
		for (Method m : methods) {
			if (m.getName().startsWith("new")) {
				relationCreators.put(m.getReturnType(), m);
			}
		}

		Set<Class<?>> needs = new TreeSet<Class<?>>(cc);
		needs.addAll(relationCreators.keySet());

		avail.add(boolean.class);
		avail.add(int.class);
		avail.add(long.class);
		avail.add(float.class);
		avail.add(double.class);
		avail.add(Boolean.class);
		avail.add(Integer.class);
		avail.add(Long.class);
		avail.add(Float.class);
		avail.add(Double.class);
		avail.add(String.class);
		avail.add(Date.class);
		avail.add(Time.class);

		resources.put(boolean.class, true);
		resources.put(Boolean.class, true);
		resources.put(int.class, 74123);
		resources.put(Integer.class, 74123);
		resources.put(long.class, 9872334);
		resources.put(Long.class, 9872334);
		resources.put(float.class, Math.E);
		resources.put(Float.class, Math.E);
		resources.put(double.class, Math.PI);
		resources.put(Double.class, Math.PI);
		resources.put(Time.class, new Time(System.currentTimeMillis()));
		resources.put(Date.class, new Date(System.currentTimeMillis()));
		resources.put(String.class, new TestStringGenerator());

		for (Class<?> c : avail) {
			if (!resources.containsKey(c)) {
				resources.put(c, c.newInstance());
			}
		}

		while (!needs.isEmpty()) {
			Set<Class<?>> currentNeeds = new TreeSet<Class<?>>(cc);
			currentNeeds.addAll(needs);
			outer: for (Class<?> relation : currentNeeds) {
				Class<?>[] params = relationCreators.get(relation).getParameterTypes();
				for (Class<?> param : params) {
					if (!avail.contains(param)) {
						continue outer;
					}
				}
				avail.add(relation);
				needs.remove(relation);
			}
		}

		Class<?> clazz = null;
		List<Class<?>> list = new ArrayList<Class<?>>();
		for (Iterator<Class<?>> it = avail.iterator(); it.hasNext(); clazz = it.next()) {
			if (!relationCreators.containsKey(clazz)) {
				list.add(clazz);
			}
		}
		for (Class<?> claZZ : list) {
			avail.remove(claZZ);
		}
	}

	protected Object[] makeValues(final Method m) {
		Class<?>[] params = m.getParameterTypes();
		Object[] values = new Object[params.length];
		int i = 0;
		for (Class<?> p : params) {
			values[i] = resources.get(p);
			if (values[i] instanceof TestValueGenerator) {
				values[i] = ((TestValueGenerator<?>) values[i]).generate();
			}
			i++;
		}
		return values;
	}

	@Test
	public void testAllCreate() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
		}

	}

	@Test
	public void testAllDelete() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
		}

		Collections.reverse(avail);
		for (Class<?> clazz : avail) {
			Relation rx = (Relation) resources.get(clazz);
			rx.delete();
		}
	}

	@Test
	public void testAllFullyCreate() throws Exception {

		LinkedList<Exception> exc = new LinkedList<Exception>();

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
		}

		for (Method m : manager.getClass().getDeclaredMethods()) {
			if (m.getName().startsWith("fullyCreate")) {
				try {
					Object o = m.invoke(manager, makeValues(m));
					assertTrue(o instanceof Relation);
				} catch (InvocationTargetException e) {
					exc.add(e);
				}
			}
		}

		if ((exc.size() != 17) && (exc.size() > 0)) {
			throw new Exception(exc.size() + " Exceptions: " + Strings.implode(",\n", exc), exc.pop());
		}
	}

	@Test
	public void testAllFullyNew() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
		}

		for (Method m : manager.getClass().getDeclaredMethods()) {
			if (m.getName().startsWith("fullyNew")) {
				Object o = m.invoke(manager, makeValues(m));
				assertTrue(o instanceof Relation);
			}
		}
	}

	@Test
	public void testAllGettersAndSetters() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();

			Method[] methods = r.getClass().getMethods();
			for (Method method : methods) {
				String name = method.getName();
				if (name.startsWith("set")) {
					Method getter =
							r.getClass().getMethod("g" + name.substring(1));
					Object v = getter.invoke(r);
					boolean hasClearMethod = false;
					try {
						r.getClass().getMethod("clear" + name.substring(3));
						hasClearMethod = true;
					} catch (NoSuchMethodException e) {
					}
					if ((v != null) || hasClearMethod) {
						method.invoke(r, v);
						assertEquals(v, getter.invoke(r));
					}
				}
			}
		}
	}

	@Test
	public void testAllPushChanges() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();

			Method[] methods = r.getClass().getMethods();
			for (Method method : methods) {
				String name = method.getName();
				if (name.startsWith("set")) {
					Method getter =
							r.getClass().getMethod("g" + name.substring(1));
					Object v = getter.invoke(r);
					boolean hasClearMethod = false;
					try {
						r.getClass().getMethod("clear" + name.substring(3));
						hasClearMethod = true;
					} catch (NoSuchMethodException e) {
					}
					if ((v != null) || hasClearMethod) {
						method.invoke(r, v);
					}
				}
			}
			r.pushChanges();
		}
	}

	@Test
	public void testAllPushChangesWithoutChanges() throws Exception {

		LinkedList<OutdatedRecordException> outdated = new LinkedList<OutdatedRecordException>();
		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Class<?>[] params = m.getParameterTypes();
			Object[] values = new Object[params.length];
			int i = 0;
			for (Class<?> p : params) {
				values[i] = resources.get(p);
				if (values[i] instanceof TestValueGenerator) {
					values[i] = ((TestValueGenerator<?>) values[i]).generate();
				}
				i++;
			}
			Relation r = (Relation) m.invoke(manager, values);
			resources.put(c, r);
			r.create();
			try {
				r.pushChanges();
			} catch (OutdatedRecordException e) {
				outdated.add(e);
			}
		}
		if (outdated.size() > 0) {
			throw new Exception(outdated.size() + " OutdatedRecordExceptions "
					+ Strings.implode(",\n", outdated));
		}
	}

	@Test
	public void testAllSelectById() throws Exception {
		for (Class<?> c : avail) {
			if (Entity.class.isAssignableFrom(c)) {
				Method m = relationCreators.get(c);
				Relation r = (Relation) m.invoke(manager, makeValues(m));
				resources.put(c, r);
				r.create();

				Method idMethod = r.getClass().getMethod("id");
				Class<?> idType = idMethod.getReturnType();
				Method getById = manager.getClass().getMethod("get" + c.getSimpleName(), idType);
				Relation rNew = (Relation) getById.invoke(manager, idMethod.invoke(r));
				assertNotNull(rNew);
			}
		}
	}

	@Test
	public void testAllStubs() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();

			if (r instanceof Entity) {
				Dynamic.callback(r, "asStub").call();
			}
		}

	}

	@Test
	public void testAllStubsWithoutCreate() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);

			if (r instanceof Entity) {
				Dynamic.callback(r, "asStub").call();
			}
		}

	}

	@Test
	public void testComparable() throws Exception {
		Set<Entity> entities = new TreeSet<Entity>();
		Set<Relationship> relationships = new TreeSet<Relationship>();
		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			if (r instanceof Entity) {
				entities.add((Entity) r);
			} else if (r instanceof Relationship) {
				relationships.add((Relationship) r);
			}
			r.create();
			if (r instanceof Entity) {
				entities.add((Entity) r);
			} else if (r instanceof Relationship) {
				relationships.add((Relationship) r);
			}
		}
	}

	@Test
	public void testComparator() {
		ClassesComparator c = ClassesComparator.comparator;
		assertTrue(c.compare(String.class, Integer.class) > 0);
		assertTrue(c.compare(null, Double.class) > 0);
		assertTrue(c.compare(Float.class, null) < 0);
		assertTrue(c.equals(Float.class, new Float(Math.PI).getClass()));
	}

	@Test
	public void testInequality() throws Exception {
		Relation last = null;
		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
			assertFalse(r.equals(last));
			last = r;
		}
	}

	@Test
	public void testManagerCreate() throws Exception {
		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			manager.getClass().getMethod("create" + c.getSimpleName(), m.getParameterTypes());
		}
	}

	@Test
	public void testManagerDeleteById() throws Exception {

		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
		}

		Collections.reverse(avail);
		for (Class<?> c : avail) {
			if (Entity.class.isAssignableFrom(c)) {
				Entity e = (Entity) resources.get(c);
				Method idMethod = e.getClass().getMethod("id");
				Class<?> idType = idMethod.getReturnType();
				Method deleteMethod =
						manager.getClass().getMethod("delete" + c.getSimpleName(), idType);
				deleteMethod.invoke(manager, idMethod.invoke(e));
			}
		}
	}

	@Test
	public void testManagerSelectNone() throws Exception {
		for (Class<?> c : avail) {
			try {
				Method m = manager.getClass().getMethod("get" + c.getSimpleName());
				Object result = m.invoke(manager);
				assertNotNull(result);
			} catch (NoSuchMethodException e) {
				throw e;
			}
		}
	}

	@Test
	public void testSelfEquality() throws Exception {
		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			r.create();
			assertTrue(r.equals(r));
		}
	}

	@Test
	public void testSelfEqualityAfterCreate() throws Exception {
		for (Class<?> c : avail) {
			Method m = relationCreators.get(c);
			Relation r = (Relation) m.invoke(manager, makeValues(m));
			resources.put(c, r);
			assertTrue(r.equals(r));
		}
	}
}
