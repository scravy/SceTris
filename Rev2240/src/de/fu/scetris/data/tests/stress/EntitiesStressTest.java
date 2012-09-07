/* EntitiesAndRelationships.java / 12:52:53 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests.stress;

import static de.fu.junction.MD5.*;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;


import de.fu.scetris.data.Building;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.util.MockupNames;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class EntitiesStressTest extends de.fu.scetris.data.tests.DatabaseTestSkeleton {

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
	@Test
	public void stressTest() throws DatabaseException {

		Random rand = new Random(System.currentTimeMillis());
		Person[] persons = new Person[PERSONS];
		Department[] department = new Department[DEPARTMENTS];
		Building[] buildings = new Building[BUILDINGS];

		MockupNames names = new MockupNames(rand);
		for (int i = 0; i < DEPARTMENTS; i++) {
			MockupNames.Name n1 = names.nextName();
			department[i] =
							manager.createDepartment(n1.firstName
									+ "-" + n1.lastName + "-" +
									 "Institute of Applied Sciences");
		}
		for (int i = 0; i < BUILDINGS; i++) {
			MockupNames.Name n1 = names.nextName();
			buildings[i] =
					manager.newBuilding(n1.firstName + "-" + n1.lastName + "-Street");
			if (i % 3 == 0) {
				MockupNames.Name n2 = names.nextName();
				buildings[i].setName(n2.firstName + "-" + n2.lastName + "-Building");
			}
			buildings[i].create();
			for (int j = 0; j < (MIN_ROOMS + rand.nextInt(MAX_ROOMS - MIN_ROOMS)); j++) {
				manager.createRoom("R" + j, buildings[i]);
			}
		}
		for (int i = 1; i < BUILDINGS; i += 2) {
			buildings[i].delete();
		}
		Set<Integer> uniqIDs = new TreeSet<Integer>();
		for (int i = 0; i < PERSONS; i++) {
			persons[i] =
					manager.newPerson(MockupNames.firstNames[rand.nextInt(MockupNames.firstNames.length)],
										 MockupNames.lastNames[rand.nextInt(MockupNames.lastNames.length)],
										 "user" + i,
										 md5(i + "pw" + Math.random()));
			if ((i % 42 == 7) || (i % 7 == 0)) {
				persons[i].setAdditionalNames(MockupNames.firstNames[rand
						.nextInt(MockupNames.firstNames.length)]);
			}
			persons[i].create();
			assertTrue(persons[i].id() > 0);
			assertFalse(uniqIDs.contains(persons[i].id()));
			uniqIDs.add(persons[i].id());
		}
		for (int i = 0; i < DEPARTMENTS; i++) {
			department[i].delete();
		}
		for (int i = 0; i < BUILDINGS; i += 2) {
			buildings[i].delete();
		}
		for (int i = 0; i < PERSONS; i += 3) {
			persons[i].setAdditionalNames(MockupNames.firstNames[rand.nextInt(MockupNames.firstNames.length)]);
			persons[i].pushChanges();
		}
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void stressTestWithTransaction() throws DatabaseException {
		manager.beginTransaction();
		stressTest();
		manager.commitTransaction();
	}

}
