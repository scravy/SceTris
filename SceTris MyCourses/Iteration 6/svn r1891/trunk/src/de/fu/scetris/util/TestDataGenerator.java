/* TestDataGenerator.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.util;

import static de.fu.bakery.orm.java.filters.Filters.eq;
import static de.fu.junction.MD5.md5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

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

	public static void main(final String args[]) throws DatabaseException,
			IOException, SAXException, ParserConfigurationException {

		int numberOfAcademicTerms = 4;
		int numberOfDepartments = 4;
		int numberOfRoomsPerBuilding = 5;
		double percentageOfSoftConstraints = 0.05;
		double percentageOfHardConstraints = 0.2;
		long seed = new Random().nextLong();
		Random random = new Random(seed);

		if (args != null && args.length > 0) {
			
			if (args[0].equals("help")) {
				BufferedReader in = new BufferedReader(new FileReader("./testdata/help.txt"));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
				
				System.exit(0);
			}

			for (int i = 0; i < args.length; ++i) {

				if (args[i].equals("-a")) {
					numberOfAcademicTerms = Integer.parseInt(args[i + 1]);
				}

				if (args[i].equals("-d")) {
					numberOfAcademicTerms = Integer.parseInt(args[i + 1]);
				}

				if (args[i].equals("-r")) {
					numberOfRoomsPerBuilding = Integer.parseInt(args[i + 1]);
				}

				if (args[i].equals("-s")) {
					random.setSeed(Long.parseLong(args[i + 1]));
				}

				if (args[i].equals("-h")) {
					percentageOfHardConstraints = Integer.parseInt(args[i + 1]) / 100.0;
				}

				if (args[i].equals("-s")) {
					percentageOfSoftConstraints = Integer.parseInt(args[i + 1]) / 100.0;
				}
			}
		}

		System.out.println("Test data generator configuration:");
		System.out
				.println("Number of academic terms: " + numberOfAcademicTerms);
		System.out.println("Number of departments: " + numberOfDepartments);
		System.out.println("Number of rooms per building: "
				+ numberOfRoomsPerBuilding);
		System.out.println("Percent of hard constraints: "
				+ percentageOfHardConstraints * 100);
		System.out.println("Percent of soft constraints: "
				+ percentageOfSoftConstraints * 100);
		System.out.println("Random seed: " + seed);
		System.out.println("Generating test data...\n");

		TestDataGenerator test = new TestDataGenerator(random, true);

		test.generateAllPresentData(numberOfAcademicTerms, numberOfDepartments,
				numberOfRoomsPerBuilding, percentageOfHardConstraints,
				percentageOfSoftConstraints);

		System.out.println("Test data inserted:");
		System.out.println("Courses: " + test.courseMap.size());
		System.out.println("CourseElements: " + test.courseElementList.size());
		System.out.println("CourseInstances: " + test.courseInstanceMap.size());
		System.out.println("CourseElementInstances: "
				+ test.courseElementInstanceList.size());
		System.out.println("Lecturers: " + test.lecturerList.size());
		System.out.println("Students: " + test.studentList.size());
		System.out.println("Tutors: " + test.tutorList.size());
	}

	RelationManager relationManager;

	Random random;
	List<AcademicTerm> academicTermList;
	List<Department> departmentList;
	List<Person> lecturerList;
	List<Person> studentList;
	List<Person> tutorList;
	List<Program> programList;
	List<Building> builingList;
	List<Room> roomList;
	List<Feature> featureList;
	Map<String, CourseElementType> courseElementTypeList;
	Map<String, Course> courseMap;
	List<CourseElement> courseElementList;
	Map<Program, CourseInstance> courseInstanceMap;
	List<Day> dayList;
	List<Timeslot> timeSlotList;
	List<ProposedScheduling> proposedSchedulingList;
	List<CourseRequiresCourse> courseRequiresCourseList;

	List<CourseElementInstance> courseElementInstanceList;
	int timeSlotsPerDay[];
	List<CourseElementInstance> remainingCoursesForRoomPreference;
	List<CourseElementInstance> remainingCoursesForTimeSlotPreference;
	Map<CourseElementInstance, List<Feature>> remainingFeaturesPerCourse;
	List<Room> remainingRooms;

	Map<Person, List<Timeslot>> remainingTimeSlotsPerPerson;
	List<String> academicTermDataList;
	List<String> departmentDataList;
	List<String> personDataList;
	List<String> buildingDataList;
	List<String> featureDataList;
	List<String> courseElementTypeDataList;
	Map<String, List<String>> courseDataList;

	List<String> courseRequiresCourseDataList;

	public TestDataGenerator(final Random random,
			final boolean installDatabaseSchema) throws IOException,
			SAXException, DatabaseException, ParserConfigurationException {
		relationManager = TestDataSetup
				.createRelationManager(installDatabaseSchema);

		this.random = random;

		academicTermDataList = TestDataSetup
				.convertTextFileToList("./testdata/academicTerms.csv");
		departmentDataList = TestDataSetup
				.convertTextFileToList("./testdata/departments.csv");
		personDataList = TestDataSetup
				.convertTextFileToList("./testdata/persons.csv");
		buildingDataList = TestDataSetup
				.convertTextFileToList("./testdata/buildings.csv");
		featureDataList = TestDataSetup
				.convertTextFileToList("./testdata/features.csv");
		courseElementTypeDataList = TestDataSetup
				.convertTextFileToList("./testdata/courseElementTypes.csv");

		courseDataList = TestDataSetup
				.convertDirectoryToLists("./testdata/department");

		courseRequiresCourseDataList = TestDataSetup
				.convertTextFileToList("./testdata/courseRequiresCourse.csv");

		remainingCoursesForRoomPreference = new ArrayList<CourseElementInstance>();
		remainingCoursesForTimeSlotPreference = new ArrayList<CourseElementInstance>();
		remainingFeaturesPerCourse = new TreeMap<CourseElementInstance, List<Feature>>();
		remainingTimeSlotsPerPerson = new TreeMap<Person, List<Timeslot>>();

		academicTermList = new ArrayList<AcademicTerm>();
		departmentList = new ArrayList<Department>();
		lecturerList = new ArrayList<Person>();
		studentList = new ArrayList<Person>();
		tutorList = new ArrayList<Person>();
		programList = new ArrayList<Program>();
		builingList = new ArrayList<Building>();
		roomList = new ArrayList<Room>();
		featureList = new ArrayList<Feature>();
		courseElementTypeList = new HashMap<String, CourseElementType>();
		courseMap = new HashMap<String, Course>();
		courseElementList = new ArrayList<CourseElement>();
		courseInstanceMap = new HashMap<Program, CourseInstance>();
		courseElementInstanceList = new ArrayList<CourseElementInstance>();
		dayList = new ArrayList<Day>();
		timeSlotList = new ArrayList<Timeslot>();
		proposedSchedulingList = new ArrayList<ProposedScheduling>();
		courseRequiresCourseList = new ArrayList<CourseRequiresCourse>();
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

	public void generateAllPresentData(final int numberOfAcademicTerms,
			final int numberOfDepartments, final int numberOfRoomsPerBuilding,
			final double percentageOfHardConstraints,
			final double percentageOfSoftConstraints) throws DatabaseException,
			IOException, SAXException, ParserConfigurationException {

		relationManager.beginTransaction();
		generateAcademicTerms(numberOfAcademicTerms);
		generateDepartments(numberOfDepartments);
		generatePersons(personDataList.size(), 0.3, 0.5, 0.1);

		for (AcademicTerm academicTerm : academicTermList) {
			for (Department department : departmentList) {
				generateProgram(academicTerm, department);
			}
		}

		generateBuildings(buildingDataList.size());
		generateFeatures();
		generatingRooms(numberOfRoomsPerBuilding, 3);
		remainingRooms = new ArrayList<Room>(roomList);
		generateCourseElementTypes();
		generateDays();
		generateTimeSlots();

		for (Department department : departmentList) {
			generateCourses(department, courseDataList.size(), 2);
		}

		generateCourseRequiresCourse();

		generateIndividualConstraints(percentageOfSoftConstraints, true);
		generateIndividualConstraints(percentageOfHardConstraints, false);

		relationManager.commitTransaction();
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
							buildingData[j++], buildingData[j++], department);
					builingList.add(building);
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
			courseElementTypeList.put(courseElementTypeData[0],
					courseElementType);
		}
	}

	public void generateCourseRequiresCourse() throws DatabaseException {

		for (int i = 0; i < courseRequiresCourseDataList.size(); ++i) {
			String courseRequiresCourseData[] = courseRequiresCourseDataList
					.get(i).split(",");

			Course course = null;
			Course dependency = null;

			for (Course courseFromList : courseMap.values()) {

				if (courseFromList.getName()
						.equals(courseRequiresCourseData[0])) {
					course = courseFromList;
				} else if (courseFromList.getName().equals(
						courseRequiresCourseData[1])) {
					dependency = courseFromList;
				}
			}

			relationManager.createCourseRequiresCourse(course, dependency);

		}

	}

	/**
	 * Creates Course, CourseInstance, CourseElement and CourseElementInstance.
	 * <p>
	 * Selects those course data which belongs to the given {@link Department}.
	 * <p>
	 * Creates as many CourseInstances as repetitions were specified.
	 * 
	 * @throws NumberFormatException
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generateCourses(final Department department,
			int numberOfCourses, final int numberOfLecturerPerCourse)
			throws NumberFormatException, DatabaseException {

		List<String> courseDataList = this.courseDataList.get(department
				.getName());

		if (courseDataList == null) {
			return;
		}

		int max = courseDataList.size();
		numberOfCourses = numberOfCourses > max ? max : numberOfCourses;
		List<String> relevantCoursesData = this.courseDataList.get(department
				.getName());

		if (relevantCoursesData == null) {
			return;
		}

		for (String relevantCourseData : relevantCoursesData) {
			String relevantCourseDataSplitted[] = relevantCourseData.split(",");

			Course course = courseMap.get(relevantCourseDataSplitted[0]);

			if (course == null) {
				course = relationManager
						.fullyCreateCourse(relevantCourseDataSplitted[0]);
				courseMap.put(relevantCourseDataSplitted[0], course);
			}

			int requiredDuration = Integer
					.parseInt(relevantCourseDataSplitted[4]);
			String courseElementName = course.getName()
					+ " "
					+ relevantCourseDataSplitted[2].substring(0, 2)
							.toUpperCase();

			CourseElementType courseElementType = courseElementTypeList
					.get(relevantCourseDataSplitted[2]);

			CourseElement courseElement = relationManager
					.fullyCreateCourseElement(courseElementName, course,
							requiredDuration, courseElementType, false);
			courseElementList.add(courseElement);

			int repititions = Integer.parseInt(relevantCourseDataSplitted[1]);
			List<Program> programListOfDepartment = relationManager
					.getProgram(eq("department", department.getId()));

			for (int i = 0; i < academicTermList.size(); i += repititions) {

				Program program = programListOfDepartment.get(i);
				AcademicTerm academicTerm = program.getAcademicTerm();
				Person mainLecturer = lecturerList.get(random
						.nextInt(lecturerList.size()));

				CourseInstance courseInstance = courseInstanceMap.get(program);

				if (courseInstance == null) {
					courseInstance = relationManager.fullyCreateCourseInstance(
							program, course, academicTerm.getStartLessons(),
							academicTerm.getEndLessons(), mainLecturer);
					courseInstanceMap.put(program, courseInstance);
				}

				int unitDuration = Integer
						.parseInt(relevantCourseDataSplitted[3]);
				int numberOfCourseOffer = Integer
						.parseInt(relevantCourseDataSplitted[5]);

				for (int j = 0; j < numberOfCourseOffer; ++j) {
					CourseElementInstance courseElementInstance = relationManager
							.createCourseElementInstance(courseInstance,
									courseElement, unitDuration);
					courseElementInstance.setLecturer(mainLecturer);
					courseElementInstance.setSchedulableLesson(true);
					courseElementInstance.pushChanges();
					courseElementInstanceList.add(courseElementInstance);
				}

			}
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
	 * Generates the following soft constraints:
	 * 
	 * CourseElementInstance prefers Room
	 * 
	 * @throws DatabaseException
	 * 
	 */
	private void generateIndividualConstraints(final double percentage,
			final boolean isHardConstraints) throws DatabaseException {

		int percentageOfCourses = (int) (courseElementInstanceList.size() * percentage);
		int percentageOfLecturers = (int) (lecturerList.size() * percentage);
		int percentageOfRooms = (int) (roomList.size() * percentage);

		int priority = 50;
		int quantity = 0;

		if (isHardConstraints) {
			priority = 100;
			quantity = 1;
		}

		for (int i = 0; (i < percentageOfCourses)
				&& (remainingCoursesForRoomPreference.size() > 0); ++i) {

			CourseElementInstance courseElementInstance;
			int courseIndex = random.nextInt(remainingCoursesForRoomPreference
					.size());

			courseElementInstance = remainingCoursesForRoomPreference
					.remove(courseIndex);

			Room room = roomList.get(random.nextInt(roomList.size()));
			relationManager.createElementInstancePrefersRoom(
					courseElementInstance, room, priority);
		}

		for (int i = 0; (i < percentageOfCourses)
				&& (remainingCoursesForTimeSlotPreference.size() > 0); ++i) {

			CourseElementInstance courseElementInstance;
			int courseIndex = random
					.nextInt(remainingCoursesForTimeSlotPreference.size());

			courseElementInstance = remainingCoursesForTimeSlotPreference
					.remove(courseIndex);

			int dayIndex = random.nextInt(dayList.size());
			int latestPossibleStartSlot;

			do {
				latestPossibleStartSlot = timeSlotsPerDay[dayIndex]
						- courseElementInstance.getDuration();
			} while (latestPossibleStartSlot < 0);

			int timeSlotIndex = random.nextInt(latestPossibleStartSlot + 1);

			for (int j = 0; j < dayIndex; ++j) {
				timeSlotIndex += timeSlotsPerDay[j];
			}

			relationManager.createElementInstancePrefersTimeslot(
					courseElementInstance, timeSlotList.get(timeSlotIndex),
					priority);
		}

		for (int i = 0; (i < percentageOfCourses)
				&& (remainingFeaturesPerCourse.size() > 0); ++i) {

			CourseElementInstance courseElementInstance = courseElementInstanceList
					.get(random.nextInt(courseElementInstanceList.size()));
			List<Feature> featureList = remainingFeaturesPerCourse
					.get(courseElementInstance);

			if (featureList.size() <= 1) {
				continue;
			}

			Feature feature = featureList.remove(random.nextInt(featureList
					.size() - 1) + 1);
			relationManager.fullyCreateElementInstanceRequiresFeature(
					courseElementInstance, feature, 0, quantity);
		}

		for (int i = 0; i < percentageOfLecturers; ++i) {
			Person lecturer = lecturerList.get(random.nextInt(lecturerList
					.size()));
			List<Timeslot> timeSlotList = remainingTimeSlotsPerPerson
					.get(lecturer);
			Timeslot timeSlot = timeSlotList.remove(random.nextInt(timeSlotList
					.size()));
			relationManager.createPersonPrefersTimeslot(lecturer, timeSlot,
					priority);
		}

		for (int i = 0; (i < percentageOfRooms) && (remainingRooms.size() > 0); ++i) {
			Room room = remainingRooms.remove(random.nextInt(remainingRooms
					.size()));
			Timeslot timeSlot = timeSlotList.get(random.nextInt(timeSlotList
					.size()));
			relationManager.createRoomPrefersTimeslot(room, timeSlot, priority);
		}

	}

	/**
	 * 
	 * @param numberOfPersons
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public void generatePersons(int numberOfPersons, double percentLecturer,
			double percentStudents, double percentStudentLecturer)
			throws DatabaseException {

		if (numberOfPersons > personDataList.size()) {
			numberOfPersons = personDataList.size();
		}

		int numberOfLecturer = (int) (((double) numberOfPersons) * percentLecturer);
		int numberOfStudents = (int) (((double) numberOfPersons) * percentStudents);
		int numberOfStudentsLecturer = (int) (((double) numberOfPersons) * percentStudentLecturer);

		for (int i = 0; i < numberOfPersons; ++i) {
			String personData[] = personDataList.get(i).split(",");
			int j = 0;
			Person person = relationManager.createPerson(personData[j++],
					personData[j++], personData[j++], md5(personData[j++]));

			if (i <= numberOfLecturer) {
				person.setIsLecturer(true);
				lecturerList.add(person);
			} else if (i >= numberOfLecturer
					&& i <= numberOfLecturer + numberOfStudents) {
				person.setIsStudent(true);
				studentList.add(person);
			} else if (i >= numberOfLecturer + numberOfStudents
					&& i <= numberOfLecturer + numberOfStudents
							+ numberOfStudentsLecturer) {
				person.setIsLecturer(true);
				person.setIsLecturer(true);
				tutorList.add(person);
			}
			person.pushChanges();

			remainingTimeSlotsPerPerson.put(person, new ArrayList<Timeslot>());
		}

	}

	/**
	 * 
	 * @param academicTerm
	 * @param department
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public Program generateProgram(final AcademicTerm academicTerm,
			final Department department) throws DatabaseException {

		Program program = relationManager.fullyCreateProgram(academicTerm,
				department, false, false,
				lecturerList.get(new Random().nextInt(lecturerList.size())));

		programList.add(program);

		return program;
	}

	public void generateTimeSlots() throws DatabaseException {
		int j = 0;
		int h = 8;

		timeSlotsPerDay = new int[dayList.size()];

		for (int i = 0; i < 60; ++i) {

			Timeslot timeSlot = relationManager.createTimeslot(
					dayList.get(j),
					Time.valueOf("0".concat(new Integer(h).toString()).concat(
							":00:00")));
			timeSlotList.add(timeSlot);
			++h;

			if (((i + 1) % 12) == 0) {
				timeSlotsPerDay[j] = 12;
				++j;
				h = 8;
			}
		}

		for (Entry<Person, List<Timeslot>> entry : remainingTimeSlotsPerPerson
				.entrySet()) {
			remainingTimeSlotsPerPerson.put(entry.getKey(),
					new ArrayList<Timeslot>(timeSlotList));
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

				for (int j = 1; j < featureList.size(); ++j) {
					relationManager.createRoomProvidesFeature(room,
							featureList.get(j), 1);
				}
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

	public RelationManager getRelationManager() {
		return relationManager;
	}

}
