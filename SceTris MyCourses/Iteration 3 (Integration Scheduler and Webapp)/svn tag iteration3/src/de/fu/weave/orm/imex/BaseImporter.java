/* BaseImporter.java / 8:59:02 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.imex;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import de.fu.scetris.data.tests.TestStringGenerator;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.util.ClassesComparator;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
abstract public class BaseImporter<T> implements Importer<T> {

	/**
	 * 
	 */
	protected RelationManager manager;

	/**
	 * 
	 * @param resources
	 * @return
	 * @since Iteration4
	 */
	protected List<Class<?>> initDependencies(final Map<Class<?>,Object> resources) {

		List<Class<?>> avail = new ArrayList<Class<?>>();

		ClassesComparator cc = new ClassesComparator();
		Map<Class<?>,Method> relationCreators = new TreeMap<Class<?>,Method>(cc);

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
				try {
					resources.put(c, c.newInstance());
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				} catch (InstantiationException e) {
					e.printStackTrace(System.err);
				}
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

		return avail;
	}

	/**
	 * 
	 */
	@Override
	public void setManager(final RelationManager relationManager) {
		manager = relationManager;
	}

}
