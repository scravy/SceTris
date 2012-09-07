/* EntitiesAndRelationships.java / 12:52:53 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.Group;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonSuccessfullyPassedCourse;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class PushChangesTest extends de.fu.bakery.orm.java.tests.DatabaseTestSkeleton {

	@Test
	public void testTwice() throws DatabaseException {
		Group gang = manager.createGroup("Vitamine");
		gang.setName("Redifine");
		gang.pushChanges();
		assertEquals(gang.getName(), "Redifine");
		gang.setName("Magic Medicine");
		gang.pushChanges();
		assertEquals(gang.getName(), "Magic Medicine");
	}

	@Test
	public void testTwiceWithNewlyFetchedPerson() throws DatabaseException {
		Person gang = manager.createPerson("A", "A", "aa", "a");
		gang.setFirstName("Redifine");
		gang.pushChanges();
		assertEquals(gang.getFirstName(), "Redifine");
		gang = manager.getPerson(gang.id());
		gang.setFirstName("Magic Medicine");
		gang.pushChanges();
		assertEquals(gang.getFirstName(), "Magic Medicine");
	}

	@Test
	public void testTwiceWithNewlyFetchedRelationships() throws DatabaseException {
		Course course = manager.createCourse("The art of failing");
		Person person = manager.createPerson("Web", "Mistress", "webmistress", "invalid");
		PersonSuccessfullyPassedCourse psfpcNew;
		@SuppressWarnings("unused")
		PersonSuccessfullyPassedCourse psfpc = manager.createPersonSuccessfullyPassedCourse(person,
																							course);
		psfpcNew = manager.getPersonSuccessfullyPassedCourse(person, course).iterator().next();
		psfpcNew.setGrade("A+");
		psfpcNew.pushChanges();
	}

	@Test
	public void testTwiceWithPerson() throws DatabaseException {
		Person gang = manager.createPerson("A", "A", "aa", "a");
		gang.setFirstName("Redifine");
		gang.pushChanges();
		assertEquals(gang.getFirstName(), "Redifine");
		gang.setFirstName("Magic Medicine");
		gang.pushChanges();
		assertEquals(gang.getFirstName(), "Magic Medicine");
	}

	@Test
	public void testTwiceWithRelationships() throws DatabaseException {
		Course course = manager.createCourse("The art of failing");
		Person person = manager.createPerson("Web", "Mistress", "webmistress", "invalid");
		PersonSuccessfullyPassedCourse psfpcNew;
		PersonSuccessfullyPassedCourse psfpc = manager.createPersonSuccessfullyPassedCourse(person,
																							course);
		psfpcNew = manager.getPersonSuccessfullyPassedCourse(person, course).iterator().next();
		assertEquals(psfpc.timekey(), psfpcNew.timekey());
		assertEquals(psfpc.subject().id(), psfpcNew.subject().id());
		assertEquals(psfpc.object().id(), psfpcNew.object().id());
		psfpc.setGrade("A+");
		psfpc.pushChanges();
		psfpc.setGrade("C+");
		psfpc.pushChanges();
	}
}
