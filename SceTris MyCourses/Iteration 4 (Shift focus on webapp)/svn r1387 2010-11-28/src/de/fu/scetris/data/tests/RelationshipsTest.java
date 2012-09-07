/* Relationships.java / 1:38:18 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.weave.util.MD5.*;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseAttribute;
import de.fu.scetris.data.CourseHasCourseAttribute;
import de.fu.scetris.data.CourseRequiresCourse;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonGivesCourse;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class RelationshipsTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test(expected = DatabaseException.class)
	public void checkConstraints() throws DatabaseException {
		Course course1 = manager.createCourse("Course1");
		Course course2 = manager.createCourse("Course2");
		assertNotNull(course1);
		assertNotNull(course2);
		assertFalse(course1.equals(course2));
		assertFalse(course1.id() == course2.id());
		assertNotNull(manager.createCourseRequiresCourse(course1, course2));
		manager.createCourseRequiresCourse(course1, course2);
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void createAndGet() throws DatabaseException {
		// create some entities
		Course course = manager.createCourse("Course");
		CourseAttribute attribute = manager.createCourseAttribute("Attribute");
		Person editor = manager.createPerson("Some", "Random", "editor", md5("having a password"));

		// declare a relationship between them
		CourseHasCourseAttribute hasAttribute =
				manager.createCourseHasCourseAttribute(course, attribute, editor);

		assertNotNull(hasAttribute.subject());
		assertNotNull(hasAttribute.object());
		assertNotNull(hasAttribute.getEditor());
		assertTrue(hasAttribute.subject().equals(course));
		assertTrue(hasAttribute.object().equals(attribute));
		assertTrue(hasAttribute.getEditor().equals(editor));

		// check the database
		Collection<CourseHasCourseAttribute> attributes =
				manager.getCourseHasCourseAttribute(course, attribute);
		assertEquals(attributes.size(), 1);

		CourseHasCourseAttribute stillHasAttribute = attributes.iterator().next();
		assertTrue(hasAttribute.equals(stillHasAttribute));
		assertTrue(hasAttribute.subject().equals(stillHasAttribute.subject()));
		assertTrue(hasAttribute.object().equals(stillHasAttribute.object()));
		assertTrue(stillHasAttribute.subject().equals(course));
		assertTrue(stillHasAttribute.object().equals(attribute));
		assertTrue(stillHasAttribute.getEditor().equals(editor));
		assertEquals("editor", stillHasAttribute.getEditor().getLoginName());
		assertEquals("Some", stillHasAttribute.getEditor().getFirstName());
		assertEquals("Random", stillHasAttribute.getEditor().getLastName());
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void createCourseRequirements() throws DatabaseException {
		Course[] courses = new Course[10];
		for (int i = 0; i < 10; i++) {
			courses[i] = manager.createCourse("C" + i);
			assertNotNull(courses[i]);
		}
		assertNotNull(manager.createCourseRequiresCourse(courses[3], courses[0]));
		assertNotNull(manager.createCourseRequiresCourse(courses[3], courses[1]));
		assertNotNull(manager.createCourseRequiresCourse(courses[3], courses[2]));
		assertNotNull(manager.createCourseRequiresCourse(courses[6], courses[3]));
		assertNotNull(manager.createCourseRequiresCourse(courses[6], courses[4]));
		assertNotNull(manager.createCourseRequiresCourse(courses[6], courses[5]));
		assertNotNull(manager.createCourseRequiresCourse(courses[7], courses[6]));
		assertNotNull(manager.createCourseRequiresCourse(courses[8], courses[6]));
		assertNotNull(manager.createCourseRequiresCourse(courses[9], courses[7]));
		assertNotNull(manager.createCourseRequiresCourse(courses[9], courses[8]));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void createRelationship() throws DatabaseException {
		Course course = manager.createCourse("Analysis");
		Person admin = manager.newPerson("Random", "Superuser", "root", md5("does not matter"));
		admin.setIsSuperuser(true);
		admin.create();
		assertNotNull(admin);
		Person lecturer =
				manager.newPerson("Random", "Lecturer", "prof",
									 md5("do you know the whateverest?"));
		assertNotNull(admin);
		lecturer.setCreatedBy(admin);
		lecturer.create();
		PersonGivesCourse relationship = manager.createPersonGivesCourse(lecturer, course, 0);
		assertNotNull(relationship);
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void getObjects() throws DatabaseException {
		// create some entities
		Course course = manager.createCourse("Course");
		CourseAttribute attribute1 = manager.createCourseAttribute("Attribute1");
		CourseAttribute attribute2 = manager.createCourseAttribute("Attribute2");
		assertNotNull(attribute1);
		assertNotNull(attribute2);
		Person editor = manager.createPerson("Some", "Random", "editor", md5("having a password"));
		assertNotNull(editor);

		// declare a relationship between them
		CourseHasCourseAttribute hasAttribute1 =
				manager.createCourseHasCourseAttribute(course, attribute1, editor);

		CourseHasCourseAttribute hasAttribute2 =
				manager.createCourseHasCourseAttribute(course, attribute2, editor);

		assertNotNull(hasAttribute1.subject());
		assertNotNull(hasAttribute1.object());
		assertNotNull(hasAttribute2.subject());
		assertNotNull(hasAttribute2.object());
		assertNotNull(hasAttribute1.getEditor());
		assertNotNull(hasAttribute2.getEditor());
		assertTrue(hasAttribute1.subject().equals(course));
		assertTrue(hasAttribute2.subject().equals(course));
		assertTrue(hasAttribute1.object().equals(attribute1));
		assertTrue(hasAttribute2.object().equals(attribute2));
		assertFalse(hasAttribute1.object().equals(attribute2));
		assertFalse(hasAttribute2.object().equals(attribute1));
		assertTrue(hasAttribute1.getEditor().equals(editor));
		assertTrue(hasAttribute2.getEditor().equals(editor));
		assertTrue(hasAttribute1.getEditor().equals(hasAttribute2.getEditor()));

		// check the database
		Collection<CourseAttribute> attributes = course.objectsOfCourseHasCourseAttribute();
		assertEquals(2, attributes.size());

		assertTrue(attributes.contains(attribute1));
		assertTrue(attributes.contains(attribute2));
	}

	@Test
	public void objectsOf() throws DatabaseException {
		createCourseRequirements();
		Course c6 = retrieve("C6");
		Collection<Course> courses = c6.objectsOfCourseRequiresCourse();
		assertNotNull(courses);
		assertEquals(3, courses.size());
		Set<String> names = new TreeSet<String>();
		for (Course course : courses) {
			assertNotNull(course);
			names.add(course.getName());
		}
		assertTrue(names.contains("C3"));
		assertTrue(names.contains("C4"));
		assertTrue(names.contains("C5"));
	}

	protected Course retrieve(final String name) throws DatabaseException {
		Collection<Course> courses = manager.getCourse(eq("name", name));
		assertNotNull(courses);
		assertEquals(1, courses.size());
		Course c = courses.iterator().next();
		assertNotNull(c);
		assertEquals(name, c.getName());
		return c;
	}

	@Test
	public void subjectsOf() throws DatabaseException {
		createCourseRequirements();
		Course c6 = retrieve("C6");
		Collection<Course> courses = c6.subjectsOfCourseRequiresCourse();
		assertNotNull(courses);
		assertEquals(2, courses.size());
		Set<String> names = new TreeSet<String>();
		for (Course course : courses) {
			assertNotNull(course);
			names.add(course.getName());
		}
		assertTrue(names.contains("C7"));
		assertTrue(names.contains("C8"));
	}

	@Test
	public void whereObjectOf() throws DatabaseException {
		createCourseRequirements();
		Course c6 = retrieve("C6");

		Collection<CourseRequiresCourse> req1 = c6.whereObjectOfCourseRequiresCourse();
		assertNotNull(req1);
		assertEquals(2, req1.size());
	}

	@Test
	public void whereSubjectOf() throws DatabaseException {
		createCourseRequirements();
		Course c6 = retrieve("C6");

		Collection<CourseRequiresCourse> req2 = c6.whereSubjectOfCourseRequiresCourse();
		assertNotNull(req2);
		assertEquals(3, req2.size());
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void whereSubjectOfCourseHasCourseAttribute() throws DatabaseException {
		// create some entities
		Course course = manager.createCourse("Course");
		CourseAttribute attribute1 = manager.createCourseAttribute("Attribute1");
		CourseAttribute attribute2 = manager.createCourseAttribute("Attribute2");
		assertNotNull(attribute1);
		assertNotNull(attribute2);
		Person editor = manager.createPerson("Some", "Random", "editor", md5("having a password"));
		assertNotNull(editor);

		// declare a relationship between them
		CourseHasCourseAttribute hasAttribute1 =
				manager.createCourseHasCourseAttribute(course, attribute1, editor);
		assertNotNull(hasAttribute1);

		CourseHasCourseAttribute hasAttribute2 =
				manager.createCourseHasCourseAttribute(course, attribute2, editor);
		assertNotNull(hasAttribute2);

		// check the database
		Collection<CourseHasCourseAttribute> relationships =
				course.whereSubjectOfCourseHasCourseAttribute();
		assertNotNull(relationships);
		assertEquals(2, relationships.size());

		assertTrue(relationships.contains(hasAttribute1));
		assertTrue(relationships.contains(hasAttribute2));

		for (CourseHasCourseAttribute relationship : relationships) {
			assertNotNull(relationship);
			assertNotNull(relationship.subject());
			assertNotNull(relationship.object());
		}
	}
}
