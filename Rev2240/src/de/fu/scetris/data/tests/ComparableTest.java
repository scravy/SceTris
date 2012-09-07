/* EntitiesAndRelationships.java / 12:52:53 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import de.fu.scetris.data.Attribute;
import de.fu.scetris.data.Configuration;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Person;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.Relation;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class ComparableTest extends de.fu.scetris.data.tests.DatabaseTestSkeleton {

	@Test
	public void testCompareTo() throws DatabaseException {
		Person a = manager.newPerson("A", "A", "a", "a");
		Person b = manager.newPerson("B", "B", "b", "b");
		assertTrue(a.compareTo(b) != 0);
		assertTrue(a.compareTo(a) == 0);
	}

	@Test
	public void testWithMap() throws DatabaseException {
		Map<Relation,Relation> treeMap = new TreeMap<Relation,Relation>();

		Day day0 = manager.newDay("Der Tag an dem die Erde still stand");
		Day day1 = manager.newDay("Orm-pimpen-Tag");
		Day day2 = manager.newDay("Internationaler Alkoholiker-Tag");

		assertTrue(day2.equals(day2));
		assertTrue(day0.compareTo(day0) == 0);
		assertTrue(day1.compareTo(day1) == 0);
		assertTrue(day2.compareTo(day2) == 0);
		int comparison = day1.compareTo(day2);
		assertTrue(comparison != 0);
		assertEquals(-comparison, day2.compareTo(day1));

		Attribute a1 = manager.newAttribute("foo");
		Attribute a2 = manager.newAttribute("bar");

		Course c1 = manager.newCourse("How to accept the weather");

		Configuration cfg = manager.fullyNewConfiguration("answer", "with loooots of alcohol");

		Person p1 = manager.newPerson("A", "A", "a", "");
		Person p2 = manager.newPerson("B", "B", "b", "");

		treeMap.put(day0, p1);
		treeMap.put(day1, a1);
		treeMap.put(day2, a2);
		treeMap.put(day0, p2);
		treeMap.put(c1, cfg);

		Set<Relation> keys = treeMap.keySet();
		Collection<Relation> values = treeMap.values();
		assertEquals(4, keys.size());
		assertEquals(4, values.size());
	}
}
