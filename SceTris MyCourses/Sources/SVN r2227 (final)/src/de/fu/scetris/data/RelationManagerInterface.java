/* RelationManagerInterface.java / 2011-03-03+01:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

/**
 * @formatter:off
 */
package de.fu.scetris.data;


public interface RelationManagerInterface extends de.fu.weave.orm.RelationManager {
	public java.sql.SQLXML exportAcademicTerm(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public AcademicTerm newAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons);
	
	public AcademicTerm createAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) throws de.fu.weave.orm.DatabaseException;
	
	public AcademicTerm fullyCreateAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) throws de.fu.weave.orm.DatabaseException;
	
	public AcademicTerm fullyNewAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<AcademicTerm> getAcademicTerm(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<AcademicTerm> getAcademicTerm(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<AcademicTerm> getAcademicTerm(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<AcademicTerm> getAcademicTerm(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<AcademicTerm> getAcademicTerm(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<AcademicTerm> getAcademicTerm() throws de.fu.weave.orm.DatabaseException;
	
	public AcademicTerm getAcademicTerm(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteAcademicTerm(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteAcademicTerm(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Attribute newAttribute(String _name);
	
	public Attribute createAttribute(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Attribute fullyCreateAttribute(String _name, String _type, boolean _uniqueValue) throws de.fu.weave.orm.DatabaseException;
	
	public Attribute fullyNewAttribute(String _name, String _type, boolean _uniqueValue) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Attribute> getAttribute(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Attribute> getAttribute(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Attribute> getAttribute(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Attribute> getAttribute(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Attribute> getAttribute(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Attribute> getAttribute() throws de.fu.weave.orm.DatabaseException;
	
	public Attribute getAttribute(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteAttribute(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportBuilding(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Building newBuilding(String _address);
	
	public Building createBuilding(String _address) throws de.fu.weave.orm.DatabaseException;
	
	public Building fullyCreateBuilding(String _name, String _address, Department _department) throws de.fu.weave.orm.DatabaseException;
	
	public Building fullyNewBuilding(String _name, String _address, Department _department) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Building> getBuilding(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Building> getBuilding(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Building> getBuilding(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Building> getBuilding(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Building> getBuilding(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Building> getBuilding() throws de.fu.weave.orm.DatabaseException;
	
	public Building getBuilding(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteBuilding(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteBuilding(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportConfiguration(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Configuration newConfiguration(String _key);
	
	public Configuration createConfiguration(String _key) throws de.fu.weave.orm.DatabaseException;
	
	public Configuration fullyCreateConfiguration(String _key, String _value) throws de.fu.weave.orm.DatabaseException;
	
	public Configuration fullyNewConfiguration(String _key, String _value) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Configuration> getConfiguration(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Configuration> getConfiguration(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Configuration> getConfiguration(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Configuration> getConfiguration(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Configuration> getConfiguration(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Configuration> getConfiguration() throws de.fu.weave.orm.DatabaseException;
	
	public Configuration getConfiguration(String id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteConfiguration(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteConfiguration(String id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Course newCourse(String _name);
	
	public Course createCourse(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Course fullyCreateCourse(String _name, Department _department) throws de.fu.weave.orm.DatabaseException;
	
	public Course fullyNewCourse(String _name, Department _department) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Course> getCourse(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Course> getCourse(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Course> getCourse(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Course> getCourse(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Course> getCourse(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Course> getCourse() throws de.fu.weave.orm.DatabaseException;
	
	public Course getCourse(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourse(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportCourseAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public CourseAttribute newCourseAttribute(String _name);
	
	public CourseAttribute createCourseAttribute(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public CourseAttribute fullyCreateCourseAttribute(String _name, String _type, boolean _uniqueValue, boolean _required) throws de.fu.weave.orm.DatabaseException;
	
	public CourseAttribute fullyNewCourseAttribute(String _name, String _type, boolean _uniqueValue, boolean _required) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseAttribute> getCourseAttribute(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseAttribute> getCourseAttribute(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseAttribute> getCourseAttribute(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseAttribute> getCourseAttribute(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseAttribute> getCourseAttribute(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseAttribute> getCourseAttribute() throws de.fu.weave.orm.DatabaseException;
	
	public CourseAttribute getCourseAttribute(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseAttribute(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportCourseElement(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElement newCourseElement(Course _partOf, int _duration);
	
	public CourseElement createCourseElement(Course _partOf, int _duration) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElement fullyCreateCourseElement(String _name, Course _partOf, int _duration, CourseElementType _type, boolean _required) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElement fullyNewCourseElement(String _name, Course _partOf, int _duration, CourseElementType _type, boolean _required) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElement> getCourseElement(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElement> getCourseElement(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElement> getCourseElement(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElement> getCourseElement(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElement> getCourseElement(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElement> getCourseElement() throws de.fu.weave.orm.DatabaseException;
	
	public CourseElement getCourseElement(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseElement(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseElement(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportCourseElementInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementInstance newCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, int _duration);
	
	public CourseElementInstance createCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, int _duration) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementInstance fullyCreateCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, Timeslot _startingTimeslot, Person _lecturer, int _duration, boolean _schedulableLesson) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementInstance fullyNewCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, Timeslot _startingTimeslot, Person _lecturer, int _duration, boolean _schedulableLesson) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementInstance> getCourseElementInstance(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementInstance> getCourseElementInstance(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementInstance> getCourseElementInstance(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementInstance> getCourseElementInstance(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementInstance> getCourseElementInstance(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementInstance> getCourseElementInstance() throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementInstance getCourseElementInstance(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseElementInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseElementInstance(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportCourseElementType(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementType newCourseElementType(String _name);
	
	public CourseElementType createCourseElementType(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementType fullyCreateCourseElementType(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementType fullyNewCourseElementType(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementType> getCourseElementType(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementType> getCourseElementType(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementType> getCourseElementType(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementType> getCourseElementType(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementType> getCourseElementType(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseElementType> getCourseElementType() throws de.fu.weave.orm.DatabaseException;
	
	public CourseElementType getCourseElementType(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseElementType(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseElementType(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportCourseInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public CourseInstance newCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer);
	
	public CourseInstance createCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) throws de.fu.weave.orm.DatabaseException;
	
	public CourseInstance fullyCreateCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) throws de.fu.weave.orm.DatabaseException;
	
	public CourseInstance fullyNewCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseInstance> getCourseInstance(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseInstance> getCourseInstance(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseInstance> getCourseInstance(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseInstance> getCourseInstance(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseInstance> getCourseInstance(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseInstance> getCourseInstance() throws de.fu.weave.orm.DatabaseException;
	
	public CourseInstance getCourseInstance(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseInstance(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportDay(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Day newDay(String _name);
	
	public Day createDay(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Day fullyCreateDay(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Day fullyNewDay(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Day> getDay(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Day> getDay(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Day> getDay(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Day> getDay(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Day> getDay(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Day> getDay() throws de.fu.weave.orm.DatabaseException;
	
	public Day getDay(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteDay(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteDay(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportDepartment(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Department newDepartment(String _name);
	
	public Department createDepartment(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Department fullyCreateDepartment(String _name, Department _superordinateDepartment) throws de.fu.weave.orm.DatabaseException;
	
	public Department fullyNewDepartment(String _name, Department _superordinateDepartment) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Department> getDepartment(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Department> getDepartment(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Department> getDepartment(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Department> getDepartment(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Department> getDepartment(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Department> getDepartment() throws de.fu.weave.orm.DatabaseException;
	
	public Department getDepartment(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteDepartment(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteDepartment(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Feature newFeature(String _name);
	
	public Feature createFeature(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Feature fullyCreateFeature(String _name, String _description) throws de.fu.weave.orm.DatabaseException;
	
	public Feature fullyNewFeature(String _name, String _description) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Feature> getFeature(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Feature> getFeature(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Feature> getFeature(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Feature> getFeature(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Feature> getFeature(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Feature> getFeature() throws de.fu.weave.orm.DatabaseException;
	
	public Feature getFeature(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteFeature(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportPerson(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Person newPerson(String _firstName, String _lastName, String _loginName, String _loginPassword);
	
	public Person createPerson(String _firstName, String _lastName, String _loginName, String _loginPassword) throws de.fu.weave.orm.DatabaseException;
	
	public Person fullyCreatePerson(java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, Department _homeDepartment, Year _currentYear, java.sql.Timestamp _lastLogin, int _loginCount, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, boolean _isStudent, boolean _isLecturer) throws de.fu.weave.orm.DatabaseException;
	
	public Person fullyNewPerson(java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, Department _homeDepartment, Year _currentYear, java.sql.Timestamp _lastLogin, int _loginCount, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, boolean _isStudent, boolean _isLecturer) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Person> getPerson(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Person> getPerson(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Person> getPerson(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Person> getPerson(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Person> getPerson(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Person> getPerson() throws de.fu.weave.orm.DatabaseException;
	
	public Person getPerson(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePerson(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePerson(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportPrivilege(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Privilege newPrivilege(String _name);
	
	public Privilege createPrivilege(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Privilege fullyCreatePrivilege(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Privilege fullyNewPrivilege(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Privilege> getPrivilege(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Privilege> getPrivilege(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Privilege> getPrivilege(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Privilege> getPrivilege(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Privilege> getPrivilege(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Privilege> getPrivilege() throws de.fu.weave.orm.DatabaseException;
	
	public Privilege getPrivilege(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePrivilege(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportProgram(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Program newProgram(AcademicTerm _academicTerm, Department _department, Person _programManager);
	
	public Program createProgram(AcademicTerm _academicTerm, Department _department, Person _programManager) throws de.fu.weave.orm.DatabaseException;
	
	public Program fullyCreateProgram(AcademicTerm _academicTerm, Department _department, boolean _freezed, boolean _published, Person _programManager) throws de.fu.weave.orm.DatabaseException;
	
	public Program fullyNewProgram(AcademicTerm _academicTerm, Department _department, boolean _freezed, boolean _published, Person _programManager) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Program> getProgram(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Program> getProgram(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Program> getProgram(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Program> getProgram(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Program> getProgram(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Program> getProgram() throws de.fu.weave.orm.DatabaseException;
	
	public Program getProgram(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteProgram(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteProgram(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportProposedScheduling(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public ProposedScheduling newProposedScheduling(int _priority, CourseElementInstance _elementInstance);
	
	public ProposedScheduling createProposedScheduling(int _priority, CourseElementInstance _elementInstance) throws de.fu.weave.orm.DatabaseException;
	
	public ProposedScheduling fullyCreateProposedScheduling(int _priority, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) throws de.fu.weave.orm.DatabaseException;
	
	public ProposedScheduling fullyNewProposedScheduling(int _priority, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ProposedScheduling> getProposedScheduling(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ProposedScheduling> getProposedScheduling(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ProposedScheduling> getProposedScheduling(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ProposedScheduling> getProposedScheduling(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ProposedScheduling> getProposedScheduling(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ProposedScheduling> getProposedScheduling() throws de.fu.weave.orm.DatabaseException;
	
	public ProposedScheduling getProposedScheduling(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteProposedScheduling(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteProposedScheduling(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportRole(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Role newRole(String _name);
	
	public Role createRole(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Role fullyCreateRole(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Role fullyNewRole(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Role> getRole(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Role> getRole(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Role> getRole(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Role> getRole(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Role> getRole(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Role> getRole() throws de.fu.weave.orm.DatabaseException;
	
	public Role getRole(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRole(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRole(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportRoom(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Room newRoom(String _number, Building _building);
	
	public Room createRoom(String _number, Building _building) throws de.fu.weave.orm.DatabaseException;
	
	public Room fullyCreateRoom(String _name, String _number, Building _building) throws de.fu.weave.orm.DatabaseException;
	
	public Room fullyNewRoom(String _name, String _number, Building _building) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Room> getRoom(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Room> getRoom(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Room> getRoom(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Room> getRoom(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Room> getRoom(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Room> getRoom() throws de.fu.weave.orm.DatabaseException;
	
	public Room getRoom(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoom(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Timeslot newTimeslot(Day _day, java.sql.Time _startingTime);
	
	public Timeslot createTimeslot(Day _day, java.sql.Time _startingTime) throws de.fu.weave.orm.DatabaseException;
	
	public Timeslot fullyCreateTimeslot(Day _day, java.sql.Time _startingTime) throws de.fu.weave.orm.DatabaseException;
	
	public Timeslot fullyNewTimeslot(Day _day, java.sql.Time _startingTime) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Timeslot> getTimeslot(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Timeslot> getTimeslot(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Timeslot> getTimeslot(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Timeslot> getTimeslot(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Timeslot> getTimeslot(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Timeslot> getTimeslot() throws de.fu.weave.orm.DatabaseException;
	
	public Timeslot getTimeslot(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteTimeslot(int id) throws de.fu.weave.orm.DatabaseException;

	public java.sql.SQLXML exportYear(boolean with_schema) throws de.fu.weave.orm.DatabaseException;
	
	public Year newYear(String _name);
	
	public Year createYear(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Year fullyCreateYear(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public Year fullyNewYear(String _name) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Year> getYear(int offset, int limit, de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Year> getYear(int offset, int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Year> getYear(int limit, de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Year> getYear(de.fu.weave.orm.Filter filter, String... joinColumns) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Year> getYear(de.fu.weave.orm.Filter filters) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<Year> getYear() throws de.fu.weave.orm.DatabaseException;
	
	public Year getYear(int id) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteYear(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteYear(int id) throws de.fu.weave.orm.DatabaseException;


	public java.util.List<CourseHasCourseAttribute> getCourseHasCourseAttribute(Course subject, CourseAttribute object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseHasCourseAttribute> getCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseHasCourseAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseHasCourseAttribute(Course subject, CourseAttribute object) throws de.fu.weave.orm.DatabaseException;
	
	public CourseHasCourseAttribute fullyNewCourseHasCourseAttribute(Course subject, CourseAttribute object, java.sql.Timestamp _time, String _value);
	
	public CourseHasCourseAttribute fullyCreateCourseHasCourseAttribute(Course subject, CourseAttribute object, java.sql.Timestamp _time, String _value) throws de.fu.weave.orm.DatabaseException;
	
	public CourseHasCourseAttribute newCourseHasCourseAttribute(Course subject, CourseAttribute object, String _value);
	
	public CourseHasCourseAttribute createCourseHasCourseAttribute(Course subject, CourseAttribute object, String _value) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportCourseHasCourseAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<CourseRecommendedForYear> getCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseRecommendedForYear> getCourseRecommendedForYear() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseRecommendedForYear(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException;
	
	public CourseRecommendedForYear fullyNewCourseRecommendedForYear(Course subject, Year object);
	
	public CourseRecommendedForYear fullyCreateCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException;
	
	public CourseRecommendedForYear newCourseRecommendedForYear(Course subject, Year object);
	
	public CourseRecommendedForYear createCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportCourseRecommendedForYear(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<CourseRequiresCourse> getCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<CourseRequiresCourse> getCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseRequiresCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public CourseRequiresCourse fullyNewCourseRequiresCourse(Course subject, Course object);
	
	public CourseRequiresCourse fullyCreateCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public CourseRequiresCourse newCourseRequiresCourse(Course subject, Course object);
	
	public CourseRequiresCourse createCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportCourseRequiresCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<ElementInstancePrefersRoom> getElementInstancePrefersRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ElementInstancePrefersRoom> getElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstancePrefersRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstancePrefersRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstancePrefersRoom fullyNewElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority);
	
	public ElementInstancePrefersRoom fullyCreateElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstancePrefersRoom newElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority);
	
	public ElementInstancePrefersRoom createElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportElementInstancePrefersRoom(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<ElementInstancePrefersTimeslot> getElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ElementInstancePrefersTimeslot> getElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstancePrefersTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstancePrefersTimeslot fullyNewElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority);
	
	public ElementInstancePrefersTimeslot fullyCreateElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstancePrefersTimeslot newElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority);
	
	public ElementInstancePrefersTimeslot createElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportElementInstancePrefersTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<ElementInstanceRequiresFeature> getElementInstanceRequiresFeature(CourseElementInstance subject, Feature object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ElementInstanceRequiresFeature> getElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstanceRequiresFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstanceRequiresFeature(CourseElementInstance subject, Feature object) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstanceRequiresFeature fullyNewElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _quantityMin, int _quantityBetter);
	
	public ElementInstanceRequiresFeature fullyCreateElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _quantityMin, int _quantityBetter) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstanceRequiresFeature newElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _quantityMin, int _quantityBetter);
	
	public ElementInstanceRequiresFeature createElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _quantityMin, int _quantityBetter) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportElementInstanceRequiresFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<ElementInstanceTakesPlaceInRoom> getElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ElementInstanceTakesPlaceInRoom> getElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstanceTakesPlaceInRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstanceTakesPlaceInRoom fullyNewElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot);
	
	public ElementInstanceTakesPlaceInRoom fullyCreateElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException;
	
	public ElementInstanceTakesPlaceInRoom newElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot);
	
	public ElementInstanceTakesPlaceInRoom createElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportElementInstanceTakesPlaceInRoom(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<ElementRequiresFeature> getElementRequiresFeature(CourseElement subject, Feature object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<ElementRequiresFeature> getElementRequiresFeature() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementRequiresFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteElementRequiresFeature(CourseElement subject, Feature object) throws de.fu.weave.orm.DatabaseException;
	
	public ElementRequiresFeature fullyNewElementRequiresFeature(CourseElement subject, Feature object, int _quantityMin, int _quantityBetter);
	
	public ElementRequiresFeature fullyCreateElementRequiresFeature(CourseElement subject, Feature object, int _quantityMin, int _quantityBetter) throws de.fu.weave.orm.DatabaseException;
	
	public ElementRequiresFeature newElementRequiresFeature(CourseElement subject, Feature object, int _quantityMin, int _quantityBetter);
	
	public ElementRequiresFeature createElementRequiresFeature(CourseElement subject, Feature object, int _quantityMin, int _quantityBetter) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportElementRequiresFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonEnrolledInCourseInstance> getPersonEnrolledInCourseInstance(Person subject, CourseInstance object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonEnrolledInCourseInstance> getPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonEnrolledInCourseInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonEnrolledInCourseInstance(Person subject, CourseInstance object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonEnrolledInCourseInstance fullyNewPersonEnrolledInCourseInstance(Person subject, CourseInstance object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy);
	
	public PersonEnrolledInCourseInstance fullyCreatePersonEnrolledInCourseInstance(Person subject, CourseInstance object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy) throws de.fu.weave.orm.DatabaseException;
	
	public PersonEnrolledInCourseInstance newPersonEnrolledInCourseInstance(Person subject, CourseInstance object);
	
	public PersonEnrolledInCourseInstance createPersonEnrolledInCourseInstance(Person subject, CourseInstance object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonEnrolledInCourseInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonGivesCourse> getPersonGivesCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonGivesCourse> getPersonGivesCourse() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonGivesCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonGivesCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonGivesCourse fullyNewPersonGivesCourse(Person subject, Course object, int _priority);
	
	public PersonGivesCourse fullyCreatePersonGivesCourse(Person subject, Course object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public PersonGivesCourse newPersonGivesCourse(Person subject, Course object, int _priority);
	
	public PersonGivesCourse createPersonGivesCourse(Person subject, Course object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonGivesCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonHasAttribute> getPersonHasAttribute(Person subject, Attribute object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonHasAttribute> getPersonHasAttribute() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonHasAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonHasAttribute(Person subject, Attribute object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonHasAttribute fullyNewPersonHasAttribute(Person subject, Attribute object, java.sql.Timestamp _time, String _value);
	
	public PersonHasAttribute fullyCreatePersonHasAttribute(Person subject, Attribute object, java.sql.Timestamp _time, String _value) throws de.fu.weave.orm.DatabaseException;
	
	public PersonHasAttribute newPersonHasAttribute(Person subject, Attribute object, String _value);
	
	public PersonHasAttribute createPersonHasAttribute(Person subject, Attribute object, String _value) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonHasAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonHasPrivilege> getPersonHasPrivilege(Person subject, Privilege object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonHasPrivilege> getPersonHasPrivilege() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonHasPrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonHasPrivilege(Person subject, Privilege object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonHasPrivilege fullyNewPersonHasPrivilege(Person subject, Privilege object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _target, Boolean _becauseOfRole);
	
	public PersonHasPrivilege fullyCreatePersonHasPrivilege(Person subject, Privilege object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _target, Boolean _becauseOfRole) throws de.fu.weave.orm.DatabaseException;
	
	public PersonHasPrivilege newPersonHasPrivilege(Person subject, Privilege object);
	
	public PersonHasPrivilege createPersonHasPrivilege(Person subject, Privilege object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonHasPrivilege(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonHasRole> getPersonHasRole(Person subject, Role object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonHasRole> getPersonHasRole() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonHasRole(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonHasRole(Person subject, Role object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonHasRole fullyNewPersonHasRole(Person subject, Role object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy);
	
	public PersonHasRole fullyCreatePersonHasRole(Person subject, Role object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy) throws de.fu.weave.orm.DatabaseException;
	
	public PersonHasRole newPersonHasRole(Person subject, Role object);
	
	public PersonHasRole createPersonHasRole(Person subject, Role object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonHasRole(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonPrefersTimeslot> getPersonPrefersTimeslot(Person subject, Timeslot object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonPrefersTimeslot> getPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonPrefersTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonPrefersTimeslot(Person subject, Timeslot object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonPrefersTimeslot fullyNewPersonPrefersTimeslot(Person subject, Timeslot object, int _priority);
	
	public PersonPrefersTimeslot fullyCreatePersonPrefersTimeslot(Person subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public PersonPrefersTimeslot newPersonPrefersTimeslot(Person subject, Timeslot object, int _priority);
	
	public PersonPrefersTimeslot createPersonPrefersTimeslot(Person subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonPrefersTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonSuccessfullyPassedCourse> getPersonSuccessfullyPassedCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonSuccessfullyPassedCourse> getPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonSuccessfullyPassedCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonSuccessfullyPassedCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonSuccessfullyPassedCourse fullyNewPersonSuccessfullyPassedCourse(Person subject, Course object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _grade, String _notes);
	
	public PersonSuccessfullyPassedCourse fullyCreatePersonSuccessfullyPassedCourse(Person subject, Course object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _grade, String _notes) throws de.fu.weave.orm.DatabaseException;
	
	public PersonSuccessfullyPassedCourse newPersonSuccessfullyPassedCourse(Person subject, Course object);
	
	public PersonSuccessfullyPassedCourse createPersonSuccessfullyPassedCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonSuccessfullyPassedCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<PersonTakesPartInElementInstance> getPersonTakesPartInElementInstance(Person subject, CourseElementInstance object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<PersonTakesPartInElementInstance> getPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonTakesPartInElementInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deletePersonTakesPartInElementInstance(Person subject, CourseElementInstance object) throws de.fu.weave.orm.DatabaseException;
	
	public PersonTakesPartInElementInstance fullyNewPersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot);
	
	public PersonTakesPartInElementInstance fullyCreatePersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException;
	
	public PersonTakesPartInElementInstance newPersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot);
	
	public PersonTakesPartInElementInstance createPersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportPersonTakesPartInElementInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<RoleImpliesAttribute> getRoleImpliesAttribute(Role subject, Attribute object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<RoleImpliesAttribute> getRoleImpliesAttribute() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoleImpliesAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoleImpliesAttribute(Role subject, Attribute object) throws de.fu.weave.orm.DatabaseException;
	
	public RoleImpliesAttribute fullyNewRoleImpliesAttribute(Role subject, Attribute object, String _default, boolean _required);
	
	public RoleImpliesAttribute fullyCreateRoleImpliesAttribute(Role subject, Attribute object, String _default, boolean _required) throws de.fu.weave.orm.DatabaseException;
	
	public RoleImpliesAttribute newRoleImpliesAttribute(Role subject, Attribute object);
	
	public RoleImpliesAttribute createRoleImpliesAttribute(Role subject, Attribute object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportRoleImpliesAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<RoleImpliesPrivilege> getRoleImpliesPrivilege(Role subject, Privilege object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<RoleImpliesPrivilege> getRoleImpliesPrivilege() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoleImpliesPrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoleImpliesPrivilege(Role subject, Privilege object) throws de.fu.weave.orm.DatabaseException;
	
	public RoleImpliesPrivilege fullyNewRoleImpliesPrivilege(Role subject, Privilege object, String _target);
	
	public RoleImpliesPrivilege fullyCreateRoleImpliesPrivilege(Role subject, Privilege object, String _target) throws de.fu.weave.orm.DatabaseException;
	
	public RoleImpliesPrivilege newRoleImpliesPrivilege(Role subject, Privilege object);
	
	public RoleImpliesPrivilege createRoleImpliesPrivilege(Role subject, Privilege object) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportRoleImpliesPrivilege(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<RoomPrefersTimeslot> getRoomPrefersTimeslot(Room subject, Timeslot object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<RoomPrefersTimeslot> getRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoomPrefersTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoomPrefersTimeslot(Room subject, Timeslot object) throws de.fu.weave.orm.DatabaseException;
	
	public RoomPrefersTimeslot fullyNewRoomPrefersTimeslot(Room subject, Timeslot object, int _priority);
	
	public RoomPrefersTimeslot fullyCreateRoomPrefersTimeslot(Room subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public RoomPrefersTimeslot newRoomPrefersTimeslot(Room subject, Timeslot object, int _priority);
	
	public RoomPrefersTimeslot createRoomPrefersTimeslot(Room subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportRoomPrefersTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException;

	public java.util.List<RoomProvidesFeature> getRoomProvidesFeature(Room subject, Feature object) throws de.fu.weave.orm.DatabaseException;
	
	public java.util.List<RoomProvidesFeature> getRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoomProvidesFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException;
	
	public void deleteRoomProvidesFeature(Room subject, Feature object) throws de.fu.weave.orm.DatabaseException;
	
	public RoomProvidesFeature fullyNewRoomProvidesFeature(Room subject, Feature object, int _quantity);
	
	public RoomProvidesFeature fullyCreateRoomProvidesFeature(Room subject, Feature object, int _quantity) throws de.fu.weave.orm.DatabaseException;
	
	public RoomProvidesFeature newRoomProvidesFeature(Room subject, Feature object, int _quantity);
	
	public RoomProvidesFeature createRoomProvidesFeature(Room subject, Feature object, int _quantity) throws de.fu.weave.orm.DatabaseException;
	
	public java.sql.SQLXML exportRoomProvidesFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException;


	public static class RelationManagerFactory {
		private final de.fu.weave.orm.ConnectionManager $connectionManager;
	
		RelationManagerFactory() {
			this.$connectionManager = null;
		}
		
		RelationManagerFactory(de.fu.weave.orm.ConnectionManager $connectionManager) {
			this.$connectionManager = $connectionManager;
		}
	
		public static RelationManagerFactory newFactory() {
			return new RelationManagerFactory();
		}
		
		public static RelationManagerFactory newFactory(de.fu.weave.orm.ConnectionManager $connectionManager) {
			if ($connectionManager == null) return newFactory();
			return new RelationManagerFactory($connectionManager);
		}
	
		public RelationManagerInterface newInstance() {
			if ($connectionManager == null) {
				return new RelationManager();
			} else {
				return new RelationManager($connectionManager);
			}
		}
		
		public static RelationManagerInterface newInstance(de.fu.weave.orm.ConnectionManager $connectionManager) {
			return new RelationManager($connectionManager);
		}
	}
}
