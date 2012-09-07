/* TestDataGenerator.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.util;

import static de.fu.junction.MD5.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseElementType;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.CourseRequiresCourse;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;

/**
 * Application to generated arbitrary test data.
 * 
 * @author Konrad Reiche
 * 
 */
public class TestDataGenerator {

	RelationManager relationManager;
	List<AcademicTerm> academicTermList;
	List<Department> departmentList;
	List<Person> lecturerList;
	List<Program> programList;
	List<Building> builingList;
	List<Room> roomList;
	List<Feature> featureList;
	List<CourseElementType> courseElementTypeList;
	List<Course> courseList;
	List<CourseElement> courseElementList;
	List<CourseInstance> courseInstanceList;
	List<Day> dayList;
	List<Timeslot> timeSlotList;
	List<ProposedScheduling> proposedSchedulingList;
	List<CourseRequiresCourse> courseRequiresCourseList;

	List<CourseElementInstance> courseElementInstanceList;
	List<String> academicTermDataList;
	List<String> departmentDataList;
	List<String> personDataList;
	List<String> programDataList;
	List<String> buildingDataList;
	List<String> featureDataList;
	List<String> courseElementTypeDataList;
	List<String> courseDataList;
	List<String> courseElementDataList;
	List<String> courseElementInstanceDataList;
	List<String> courseRequiresCourseDataList;

	public TestDataGenerator(final boolean installDatabaseSchema)
			throws IOException, SAXException, DatabaseException,
			ParserConfigurationException {
		relationManager = TestDataSetup
				.createRelationManager(installDatabaseSchema);

		academicTermDataList = TestDataSetup
				.convertTextFileToList("./testdata/academicTerms.csv");
		departmentDataList = TestDataSetup
				.convertTextFileToList("./testdata/departments.csv");
		programDataList = TestDataSetup
				.convertTextFileToList("./testdata/programs.csv");
		personDataList = TestDataSetup
				.convertTextFileToList("./testdata/persons.csv");
		buildingDataList = TestDataSetup
				.convertTextFileToList("./testdata/buildings.csv");
		featureDataList = TestDataSetup
				.convertTextFileToList("./testdata/features.csv");
		courseElementTypeDataList = TestDataSetup
				.convertTextFileToList("./testdata/courseElementTypes.csv");
		courseDataList = TestDataSetup
				.convertTextFileToList("./testdata/courses.csv");
		courseElementDataList = TestDataSetup
				.convertTextFileToList("./testdata/courseElements.csv");
		courseElementInstanceDataList = TestDataSetup
				.convertTextFileToList("./testdata/courseElementInstances.csv");
		courseRequiresCourseDataList = TestDataSetup
				.convertTextFileToList("./testdata/courseRequiresCourse.csv");

		academicTermList = new ArrayList<AcademicTerm>();
		departmentList = new ArrayList<Department>();
		lecturerList = new ArrayList<Person>();
		programList = new ArrayList<Program>();
		builingList = new ArrayList<Building>();
		roomList = new ArrayList<Room>();
		featureList = new ArrayList<Feature>();
		courseElementTypeList = new ArrayList<CourseElementType>();
		courseList = new ArrayList<Course>();
		courseElementList = new ArrayList<CourseElement>();
		courseInstanceList = new ArrayList<CourseInstance>();
		courseElementInstanceList = new ArrayList<CourseElementInstance>();
		dayList = new ArrayList<Day>();
		timeSlotList = new ArrayList<Timeslot>();
		proposedSchedulingList = new ArrayList<ProposedScheduling>();
		courseRequiresCourseList = new ArrayList<CourseRequiresCourse>();
	}

	public static void main(String args[]) throws DatabaseException,
			IOException, SAXException, ParserConfigurationException {

		TestDataGenerator test = new TestDataGenerator(true);
		test.generateAllPresentData(5);
		System.out.println("Test data was inserted.");

	}

	public void generateAllPresentData(int numberOfRoomsPerBuilding)
			throws DatabaseException, IOException, SAXException,
			ParserConfigurationException {

		relationManager.beginTransaction();
		generateAcademicTerms(academicTermDataList.size());
		generateDepartments(departmentDataList.size());
		generatePersons(personDataList.size());
		generateBuildings(buildingDataList.size());
		generateFeatures();
		generatingRooms(numberOfRoomsPerBuilding, 3);
		generateCourseElementTypes();
		generateCourses(courseDataList.size());
		generateCourseElements(courseElementDataList.size());
		generateDays();
		generateTimeSlots();

		for (AcademicTerm academicTerm : academicTermList) {
			for (Department department : departmentList) {
				generateProgram(academicTerm, department);
			}
		}

		generateCourseInstance(programList.get(0));
		generateCourseElementInstances(courseElementInstanceDataList.size(), 2);
		generateCourseRequiresCourse();
		relationManager.commitTransaction();
	}

	/**
	 * 
	 * @param numberOfAcademicTerms
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateAcademicTerms(int numberOfAcademicTerms)
			throws DatabaseException {

		if (numberOfAcademicTerms > academicTermDataList.size()) {
			numberOfAcademicTerms = academicTermDataList.size();
		}

		for (int i = 0; i < numberOfAcademicTerms; ++i) {
			String academicTermData[] = academicTermDataList.get(i).split(",");
			int j = 0;
			AcademicTerm academicTerm = relationManager
					.fullyCreateAcademicTerm(academicTermData[j++],
							Date.valueOf(academicTermData[j++]),
							Date.valueOf(academicTermData[j++]),
							Date.valueOf(academicTermData[j++]),
							Date.valueOf(academicTermData[j++]));
			academicTermList.add(academicTerm);
		}
	}

	/**
	 * 
	 * @param numberOfBuildings
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateBuildings(int numberOfBuildings)
			throws DatabaseException {

		if (numberOfBuildings > buildingDataList.size()) {
			numberOfBuildings = buildingDataList.size();
		}

		if (departmentList.isEmpty()) {
			generateDepartments(departmentDataList.size());
		}

		for (int i = 0; i < numberOfBuildings; ++i) {
			String buildingData[] = buildingDataList.get(i).split(",");

			for (Department department : departmentList) {
				if (department.getName().equals(buildingData[0])) {
					int j = 1;
					Building building = relationManager.fullyCreateBuilding(
							department, buildingData[j++], buildingData[j++]);
					builingList.add(building);
				}
			}
		}
	}

	/**
	 * 
	 * @throws NumberFormatException
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateCourseElementInstances(
			int numberOfCourseElementInstances, int numberOfLecturerPerCourse)
			throws NumberFormatException, DatabaseException {

		if (numberOfCourseElementInstances > courseElementInstanceDataList
				.size()) {
			numberOfCourseElementInstances = courseElementInstanceDataList
					.size();
		}

		if (courseList.isEmpty()) {
			generateCourseElements(courseElementDataList.size());
		}

		if (courseInstanceList.isEmpty()) {
			for (Program program : programList) {
				generateCourseInstance(program);
			}
		}

		int lecturerIndex = 0;
		int lecturerCount = 0;
		for (int i = 0; i < numberOfCourseElementInstances; ++i) {

			String courseElementInstanceData[] = courseElementInstanceDataList
					.get(i).split(",");
			for (CourseInstance courseInstance : courseInstanceList) {

				if (courseInstance.getInstanceOf().getName()
						.equals(courseElementInstanceData[0])) {
					for (CourseElement courseElement : courseElementList) {

						if (courseElement.getType().getName()
								.equals(courseElementInstanceData[1])
								&& courseElement.getName().equals(
										courseElementInstanceData[0])) {

							CourseElementInstance courseElementInstance = relationManager
									.createCourseElementInstance(
											courseInstance,
											courseElement,
											Integer.parseInt(courseElementInstanceData[2]));

							courseElementInstance.setLecturer(lecturerList
									.get(lecturerIndex));
							courseElementInstance.pushChanges();

							ProposedScheduling proposedScheduling = relationManager
									.createProposedScheduling(100,
											courseElementInstance);

							courseElementInstanceList
									.add(courseElementInstance);

							++lecturerCount;

							if (lecturerCount % numberOfLecturerPerCourse == 0) {
								++lecturerIndex;
							}

							proposedSchedulingList.add(proposedScheduling);

							if (courseElement.getType().getName()
									.equals("Lecture")) {

								relationManager
										.fullyCreateElementInstanceRequiresFeature(
												courseElementInstance,
												featureList.get(0), 100, 180,
												200);

							} else {

								relationManager
										.fullyCreateElementInstanceRequiresFeature(
												courseElementInstance,
												featureList.get(0), 100, 50, 50);

							}

							break;
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @throws NumberFormatException
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateCourseElements(int numberOfCourseElements)
			throws NumberFormatException, DatabaseException {

		if (numberOfCourseElements > courseElementDataList.size()) {
			numberOfCourseElements = courseElementDataList.size();
		}

		if (courseList.isEmpty()) {
			generateCourses(courseDataList.size());
		}

		for (int i = 0; i < courseElementDataList.size(); ++i) {
			String courseElementData[] = courseElementDataList.get(i)
					.split(",");
			for (Course course : courseList) {
				if (course.getName().equals(courseElementData[0])) {

					for (CourseElementType courseElementType : courseElementTypeList) {
						if (courseElementType.getName().equals(
								courseElementData[1])) {
							CourseElement courseElement = relationManager
									.fullyCreateCourseElement(
											course.getName(),
											course,
											Integer.parseInt(courseElementData[2]),
											courseElementType, false);
							courseElementList.add(courseElement);
							break;
						}
					}
					break;
				}
			}
		}
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateCourseElementTypes() throws DatabaseException {

		for (int i = 0; i < courseElementTypeDataList.size(); ++i) {
			String courseElementTypeData[] = courseElementTypeDataList.get(i)
					.split(",");
			int j = 0;
			CourseElementType courseElementType = relationManager
					.fullyCreateCourseElementType(courseElementTypeData[j++]);
			courseElementTypeList.add(courseElementType);
		}
	}

	/**
	 * 
	 * @param program
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateCourseInstance(Program program)
			throws DatabaseException {

		if (courseList.isEmpty()) {
			generateCourses(courseDataList.size());
		}

		int lecturerIndex = 0;
		for (Course course : courseList) {
			CourseInstance courseInstance = relationManager
					.createCourseInstance(program, course, program
							.getAcademicTerm().getStartLessons(), program
							.getAcademicTerm().getEndLessons(), lecturerList
							.get(lecturerIndex));
			lecturerIndex = (lecturerIndex + 1) % lecturerList.size();
			courseInstanceList.add(courseInstance);
		}
	}

	/**
	 * 
	 * @param numberOfCourses
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateCourses(int numberOfCourses) throws DatabaseException {

		if (numberOfCourses > courseDataList.size()) {
			numberOfCourses = courseDataList.size();
		}

		for (int i = 0; i < numberOfCourses; ++i) {
			String courseData[] = courseDataList.get(i).split(",");
			int j = 0;
			Course course = relationManager.fullyCreateCourse(courseData[j++]);
			courseList.add(course);
		}

	}

	/**
	 * 
	 * @param numberOfDepartments
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateDepartments(int numberOfDepartments)
			throws DatabaseException {

		if (numberOfDepartments > departmentDataList.size()) {
			numberOfDepartments = departmentDataList.size();
		}

		for (int i = 0; i < numberOfDepartments; ++i) {
			String departmentData[] = departmentDataList.get(i).split(",");
			int j = 0;
			Department department = relationManager
					.createDepartment(departmentData[j++]);
			departmentList.add(department);
		}

	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateFeatures() throws DatabaseException {

		for (int i = 0; i < featureDataList.size(); ++i) {
			String featureData[] = featureDataList.get(i).split(",");
			int j = 0;
			Feature feature = relationManager
					.fullyCreateFeature(featureData[j++]);
			featureList.add(feature);
		}

	}

	/**
	 * 
	 * @param numberOfPersons
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generatePersons(int numberOfPersons) throws DatabaseException {

		if (numberOfPersons > personDataList.size()) {
			numberOfPersons = personDataList.size();
		}

		for (int i = 0; i < numberOfPersons; ++i) {
			String personData[] = personDataList.get(i).split(",");
			int j = 0;
			Person person = relationManager.createPerson(personData[j++],
					personData[j++], personData[j++], md5(personData[j++]));
			lecturerList.add(person);
		}

	}

	/**
	 * 
	 * @param academicTerm
	 * @param department
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateProgram(final AcademicTerm academicTerm,
			final Department department) throws DatabaseException {

		for (String programData : programDataList) {
			if (programData.contains(academicTerm.getName())
					&& programData.contains(department.getName())) {

				Program program = relationManager
						.fullyCreateProgram(academicTerm, department, false,
								false, lecturerList.get(new Random()
										.nextInt(lecturerList.size())));
				programList.add(program);

			}
		}

	}

	/**
	 * 
	 * @param numberOfRoomsPerBuilding
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generatingRooms(final int numberOfRoomsPerBuilding,
			final int numberOfLectureHallPerBuilding) throws DatabaseException {

		if (builingList.isEmpty()) {
			generateBuildings(buildingDataList.size());
		}

		for (Building building : builingList) {
			Integer roomNumber = 100;
			List<Room> intermediateRoomList = new ArrayList<Room>();
			for (int i = 0; i < numberOfRoomsPerBuilding; ++i) {
				Room room = relationManager.fullyCreateRoom(
						roomNumber.toString(), roomNumber.toString(), building);
				intermediateRoomList.add(room);
				++roomNumber;
			}

			for (int i = 0; i < numberOfLectureHallPerBuilding; ++i) {
				relationManager.createRoomProvidesFeature(
						intermediateRoomList.get(i), featureList.get(0), 200);
			}

			for (int i = numberOfLectureHallPerBuilding; i < intermediateRoomList
					.size(); ++i) {
				relationManager.createRoomProvidesFeature(
						intermediateRoomList.get(i), featureList.get(0), 50);
			}

			roomList.addAll(intermediateRoomList);
			intermediateRoomList.clear();
		}
	}

	public void generateDays() throws DatabaseException {
		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday", "Sunday" };

		for (int i = 0; i < 5; ++i) {
			Day day = relationManager.createDay(weekdays[i]);
			dayList.add(day);
		}
	}

	public void generateTimeSlots() throws DatabaseException {
		int j = 0;
		int h = 8;
		for (int i = 0; i < 60; ++i) {

			Timeslot timeSlot = relationManager.createTimeslot(
					dayList.get(j),
					Time.valueOf("0".concat(new Integer(h).toString()).concat(
							":00:00")));
			timeSlotList.add(timeSlot);
			++h;

			if (((i + 1) % 12) == 0) {
				++j;
				h = 8;
			}
		}
	}

	public void generateCourseRequiresCourse() throws DatabaseException {

		for (int i = 0; i < courseRequiresCourseDataList.size(); ++i) {
			String courseRequiresCourseData[] = courseRequiresCourseDataList
					.get(i).split(",");

			Course course = null;
			Course dependency = null;

			for (Course courseFromList : courseList) {

				if (courseFromList.getName()
						.equals(courseRequiresCourseData[0])) {
					course = courseFromList;
				} else if (courseFromList.getName()
						.equals(courseRequiresCourseData[1])) {
					dependency = courseFromList;
				}
			}
			
			relationManager.createCourseRequiresCourse(course,dependency);

		}

	}

	public RelationManager getRelationManager() {
		return relationManager;
	}

}