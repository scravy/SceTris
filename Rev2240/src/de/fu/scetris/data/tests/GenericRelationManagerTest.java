/* GenericRelationManagerTest.java / 3:47:47 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.weave.orm.filters.Filters.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import de.fu.junction.Dynamic;
import de.fu.junction.Strings;
import de.fu.junction.functional.F;
import de.fu.weave.orm.GenericRelationManager;
import de.fu.weave.orm.filters.Sort;

/**
 *
 */
public class GenericRelationManagerTest {

	public static String getClassName(final Object $o) {
		return $o.getClass().getCanonicalName();
	}

	@Test
	public void testAllSortIgnored() {
		assertEquals(all(eq("hello", 7)).toString(), all(eq("hello", 7), sort("bye")).toString());
	}

	@Test
	public void testAllToString() {
		assertEquals("\"hello\" = ?", all(eq("hello", 7)).toString());
		assertEquals("\"hello\" = ?", all(eq("hello", 7), sort("bye")).toString());
	}

	@Test
	public void testAnySortIgnored() {
		assertEquals(any(eq("hello", 7)).toString(), any(eq("hello", 7), sort("bye")).toString());
	}

	@Test
	public void testAnyToString() {
		assertEquals("\"hello\" = ?", any(eq("hello", 7)).toString());
		assertEquals("\"hello\" = ?", any(eq("hello", 7), sort("bye")).toString());
	}

	@Test
	public void testMakeWhereString() throws Exception {
		String $whereString = GenericRelationManager.makeWhereString(all(eq("hello", 7),
			sort("byte", "bite")));
		System.out.println($whereString);

		LinkedList<Sort> $list = new LinkedList<Sort>();
		GenericRelationManager.extractSortFilters(all(sort("hello"), any(sort("byte", "bite"))).filters,
			$list);
		System.out.println(Strings.implode("\n",
			F.map(Dynamic.function(getClass(), "getClassName", Object.class, String.class), $list)));

		System.out.println(GenericRelationManager.makeSortString($list));
	}
}
