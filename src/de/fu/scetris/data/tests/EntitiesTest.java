/* EntitiesAndRelationships.java / 12:52:53 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.junction.MD5.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class EntitiesTest extends de.fu.scetris.data.tests.DatabaseTestSkeleton {

	final int DEPARTMENTS = 50;
	final int PERSONS = 20000;
	final int BUILDINGS = 500;
	final int MIN_ROOMS = 20;
	final int MAX_ROOMS = 100;

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test(expected = DatabaseException.class)
	public void checkUniqueConstraints() throws DatabaseException {
		manager.createPerson("Single", "Ton", "singleton", md5("baseline"));
		manager.createPerson("Double", "Ass", "singleton", md5("violone"));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void createTwo() throws DatabaseException {
		manager.createPerson("Todo", "Troglodyte", "troglo", md5("milleniumGeneration"));
		int id = manager.createPerson("Bodoäüö", "Binocular", "bino", md5("twoDglasses")).id();
		assertEquals("Bodoäüö", manager.getPerson(id).getFirstName());
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void deleteEntity() throws DatabaseException {
		Department department = manager.createDepartment("Some Department");
		int id = department.id();
		assertNotNull(manager.getDepartment(id));
		department.delete();
		assertNull(manager.getDepartment(id));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void lazyFetch() throws DatabaseException {
		manager.createPerson("One", "A", "a1", md5("One1"));
		Person admin = manager.createPerson("Two", "B", "b2", md5("Two2"));
		Person person = manager.createPerson("Three", "C", "c3", md5("Three3"));
		person.setCreatedBy(admin);
		person.pushChanges();
		int id = person.id();
		Person samePerson = manager.getPerson(id); // lazy fetching is done here
		assertTrue(person.equals(samePerson));
		assertTrue(samePerson.getCreatedBy().equals(admin));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void oneStepCreate() throws DatabaseException {
		Person person = manager.createPerson("Random", "Hacker", "rjhacker", md5("theEmptyString"));
		assertNotNull(person);
	}

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	@Test
	public void twoStepCreate() throws DatabaseException {
		Person person =
				manager.newPerson("Random", "Hacker", "rjhacker", md5("anotherEmptyString"));
		assertEquals(person.id(), 0);
		person.create();
		assertTrue(person.id() > 0);
	}
}
