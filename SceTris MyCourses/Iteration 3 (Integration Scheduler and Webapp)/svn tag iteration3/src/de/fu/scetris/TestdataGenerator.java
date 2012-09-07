/* TestdataGenerator.java / 17:37:31 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, Andre Zoufahl
 */package de.fu.scetris;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.weave.xml.XmlHelper;

public class TestdataGenerator {

	/**
	 * @param args
	 * @since Iteration3
	 */
	public static void main(final String[] args) throws ParserConfigurationException {
		new TestdataGenerator().run();
	}

	XmlHelper helper;

	TestdataGenerator() throws ParserConfigurationException {
		helper = new XmlHelper();
	}

	private void run() {
		// create the Document
		Document doc = helper.newDocument();
		// create the root node and append it
		Element rootElement = doc.createElement("scetris");
		doc.appendChild(rootElement);
		// create elements that are subnodes of root and append them
		Element academicTerm = doc.createElement("AcademicTerm");
		rootElement.appendChild(academicTerm);
		Element attribute = doc.createElement("Attribute");
		rootElement.appendChild(attribute);
		Element building = doc.createElement("Building");
		rootElement.appendChild(building);
		Element configuration = doc.createElement("Configuration");
		rootElement.appendChild(configuration);
		Element course = doc.createElement("Course");
		rootElement.appendChild(course);
		Element courseAttribute = doc.createElement("CourseAttribute");
		rootElement.appendChild(courseAttribute);
		Element courseElement = doc.createElement("CourseElement");
		rootElement.appendChild(courseElement);
		Element courseElementInstance = doc.createElement("CourseElementInstance");
		rootElement.appendChild(courseElementInstance);
		Element courseElementType = doc.createElement("CourseElementType");
		rootElement.appendChild(courseElementType);
		Element courseInstance = doc.createElement("CourseInstance");
		rootElement.appendChild(courseInstance);
		Element day = doc.createElement("Day");
		rootElement.appendChild(day);
		Element department = doc.createElement("Department");
		rootElement.appendChild(department);
		Element feature = doc.createElement("Feature");
		rootElement.appendChild(feature);
		Element group = doc.createElement("Group");
		rootElement.appendChild(group);
		Element person = doc.createElement("Person");
		rootElement.appendChild(person);
		Element privilege = doc.createElement("Privilege");
		rootElement.appendChild(privilege);
		Element program = doc.createElement("Program");
		rootElement.appendChild(program);
		Element proposedScheduling = doc.createElement("ProposedScheduling");
		rootElement.appendChild(proposedScheduling);
		Element role = doc.createElement("Role");
		rootElement.appendChild(role);
		Element room = doc.createElement("Room");
		rootElement.appendChild(room);
		Element timeslot = doc.createElement("Timeslot");
		rootElement.appendChild(timeslot);
		Element year = doc.createElement("Year");
		rootElement.appendChild(year);
		Element courseHasAttribute = doc.createElement("courseHasAttribute");
		rootElement.appendChild(courseHasAttribute);
		Element courseRecommendedForYear = doc.createElement("courseRecommendedForYear");
		rootElement.appendChild(courseRecommendedForYear);
		Element courseRequiresCourse = doc.createElement("courseRequiresCourse");
		rootElement.appendChild(courseRequiresCourse);
		Element elementInstancePrefersRoom = doc.createElement("elementInstancePrefersRoom");
		rootElement.appendChild(elementInstancePrefersRoom);
		Element elementInstancePrefersTimeSlot = doc.createElement("elementInstancePrefersTimeSlot");
		rootElement.appendChild(elementInstancePrefersTimeSlot);
		Element elementInstanceRequiresFeature = doc.createElement("elementInstanceRequiresFeature");
		rootElement.appendChild(elementInstanceRequiresFeature);
		Element elementInstanceTakesPlaceInRoom = doc.createElement("elementInstanceTakesPlaceInRoom");
		rootElement.appendChild(elementInstanceTakesPlaceInRoom);
		Element elementRequiresFeature = doc.createElement("elementRequiresFeature");
		rootElement.appendChild(elementRequiresFeature);
		Element personBelongsToGroup = doc.createElement("personBelongsToGroup");
		rootElement.appendChild(personBelongsToGroup);
		Element personEnrolledInCourseInstance = doc.createElement("personEnrolledInCourseInstance");
		rootElement.appendChild(personEnrolledInCourseInstance);
		Element personGivesCourse = doc.createElement("personGivesCourse");
		rootElement.appendChild(personGivesCourse);
		Element personHasAttribute = doc.createElement("personHasAttribute");
		rootElement.appendChild(personHasAttribute);
		Element personHasPrivilege = doc.createElement("personHasPrivilege");
		rootElement.appendChild(personHasPrivilege);
		Element personHasRole = doc.createElement("personHasRole");
		rootElement.appendChild(personHasRole);
		Element personPrefersTimeSlot = doc.createElement("personPrefersTimeSlot");
		rootElement.appendChild(personPrefersTimeSlot);
		Element personSuccessfullyPassedCourse = doc.createElement("personSuccessfullyPassedCourse");
		rootElement.appendChild(personSuccessfullyPassedCourse);
		Element personTakesPartInElementInstance = doc.createElement("personTakesPartInElementInstance");
		rootElement.appendChild(personTakesPartInElementInstance);
		Element roleImpliesAttribute = doc.createElement("roleImpliesAttribute");
		rootElement.appendChild(roleImpliesAttribute);
		Element roleImpliesPrivilege = doc.createElement("roleImpliesPrivilege");
		rootElement.appendChild(roleImpliesPrivilege);
		Element roomPrefersTimeslot = doc.createElement("roomPrefersTimeslot");
		rootElement.appendChild(roomPrefersTimeslot);
		Element roomProvidesFeature = doc.createElement("roomProvidesFeature");
		rootElement.appendChild(roomProvidesFeature);

	}
}
