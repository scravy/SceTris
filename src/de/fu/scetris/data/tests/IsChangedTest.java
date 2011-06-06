/* 
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.Test;

import static org.junit.Assert.*;

import de.fu.scetris.data.Person;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class IsChangedTest extends de.fu.scetris.data.tests.DatabaseTestSkeleton {
	@Test
	public void testIsChanged() {
		Person p = manager.newPerson("123", "456", "789", "101112");
		assertFalse(p.isIsSuperuserChanged());
		p.setIsSuperuser(p.getIsSuperuser());
		assertFalse(p.isIsSuperuserChanged());
		p.setIsSuperuser(!p.getIsSuperuser());
		assertTrue(p.getIsSuperuser());
	}

	@Test
	public void testIsChangedAfterClear() throws Exception {
		Person p = manager.newPerson("A", "B", "ab", "disabled");
		p.create();
		p.setAdditionalNames("har");
		assertTrue(p.isAdditionalNamesChanged());
		p.pushChanges();
		assertFalse(p.isAdditionalNamesChanged());
		p.clearAdditionalNames();
		assertTrue(p.isAdditionalNamesChanged());
	}

	@Test
	public void testIsChangedAfterClearWithoutDatabase() throws Exception {
		Person p = manager.newPerson("A", "B", "ab", "disabled");
		assertFalse(p.isAdditionalNamesChanged());
		p.setAdditionalNames("har");
		assertTrue(p.isAdditionalNamesChanged());
		p.clearAdditionalNames();
		assertTrue(p.isAdditionalNamesChanged());
	}

	@Test
	public void testIsChangedAfterCreate() throws Exception {
		Person p = manager.newPerson("A", "B", "ab", "disabled");
		p.setAdditionalNames("har");
		p.create();
	}

	@Test
	public void testIsChangedAfterPushChanges() throws Exception {
		Person p = manager.newPerson("A", "B", "ab", "disabled");
		p.setAdditionalNames("har");
		assertTrue(p.isAdditionalNamesChanged());
		p.create();
		assertFalse(p.isAdditionalNamesChanged());
		p.pushChanges();
		assertFalse(p.isAdditionalNamesChanged());
	}
}
