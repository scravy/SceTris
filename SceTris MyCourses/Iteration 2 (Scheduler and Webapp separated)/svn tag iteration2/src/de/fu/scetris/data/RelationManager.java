/* RelationManager.java / 2010-10-30+02:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

package de.fu.scetris.data;


/**
 *
 * @since Iteration2
 */
public class RelationManager extends de.fu.weave.orm.GenericRelationManager {
	
	/**
	 * 
	 * @since Iteration2
	 */
	public RelationManager() {
		super();
	}
	
	/**
	 * 
	 * @since Iteration2
	 */
	public RelationManager(de.fu.weave.orm.ConnectionManager connectionManager) {
		super(connectionManager);
	}
	
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<CourseHasCourseAttribute> getCourseHasCourseAttribute(Course subject, CourseAttribute object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("course", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("attribute", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"courseHasCourseAttribute\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseHasCourseAttribute> resultCollection = new java.util.ArrayList<CourseHasCourseAttribute>();
			while (result.next()) {
				resultCollection.add(new CourseHasCourseAttribute(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseHasCourseAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"courseHasCourseAttribute\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseHasCourseAttribute(Course subject, CourseAttribute object) throws de.fu.weave.orm.DatabaseException {
		deleteCourseHasCourseAttribute(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("course", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("attribute", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _time 
	 * @param _editor 
	 * @param _value 
	 * @since Iteration3
	 */
	public CourseHasCourseAttribute fullyNewCourseHasCourseAttribute(Course subject, CourseAttribute object, java.sql.Timestamp _time, Person _editor, String _value) {
		return new CourseHasCourseAttribute(this, true, subject, object, _time, _editor, _value);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _time 
	 * @param _editor 
	 * @param _value 
	 * @since Iteration3
	 */
	public CourseHasCourseAttribute fullyCreateCourseHasCourseAttribute(Course subject, CourseAttribute object, java.sql.Timestamp _time, Person _editor, String _value) throws de.fu.weave.orm.DatabaseException {
		CourseHasCourseAttribute obj = fullyNewCourseHasCourseAttribute(subject, object, _time, _editor, _value);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _editor 
	 * @since Iteration3
	 */
	public CourseHasCourseAttribute newCourseHasCourseAttribute(Course subject, CourseAttribute object, Person _editor) {
		return new CourseHasCourseAttribute(this, subject, object, _editor);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _editor 
	 * @since Iteration3
	 */
	public CourseHasCourseAttribute createCourseHasCourseAttribute(Course subject, CourseAttribute object, Person _editor) throws de.fu.weave.orm.DatabaseException {
		CourseHasCourseAttribute obj = newCourseHasCourseAttribute(subject, object, _editor);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseHasCourseAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"courseHasCourseAttribute\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<CourseRecommendedForYear> getCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("course", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("year", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"courseRecommendedForYear\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseRecommendedForYear> resultCollection = new java.util.ArrayList<CourseRecommendedForYear>();
			while (result.next()) {
				resultCollection.add(new CourseRecommendedForYear(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseRecommendedForYear(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"courseRecommendedForYear\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException {
		deleteCourseRecommendedForYear(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("course", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("year", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @since Iteration3
	 */
	public CourseRecommendedForYear fullyNewCourseRecommendedForYear(Course subject, Year object) {
		return new CourseRecommendedForYear(this, true, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public CourseRecommendedForYear fullyCreateCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException {
		CourseRecommendedForYear obj = fullyNewCourseRecommendedForYear(subject, object);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public CourseRecommendedForYear newCourseRecommendedForYear(Course subject, Year object) {
		return new CourseRecommendedForYear(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public CourseRecommendedForYear createCourseRecommendedForYear(Course subject, Year object) throws de.fu.weave.orm.DatabaseException {
		CourseRecommendedForYear obj = newCourseRecommendedForYear(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseRecommendedForYear(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"courseRecommendedForYear\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<CourseRequiresCourse> getCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("course", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("dependency", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"courseRequiresCourse\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseRequiresCourse> resultCollection = new java.util.ArrayList<CourseRequiresCourse>();
			while (result.next()) {
				resultCollection.add(new CourseRequiresCourse(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseRequiresCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"courseRequiresCourse\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException {
		deleteCourseRequiresCourse(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("course", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("dependency", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @since Iteration3
	 */
	public CourseRequiresCourse fullyNewCourseRequiresCourse(Course subject, Course object) {
		return new CourseRequiresCourse(this, true, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public CourseRequiresCourse fullyCreateCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException {
		CourseRequiresCourse obj = fullyNewCourseRequiresCourse(subject, object);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public CourseRequiresCourse newCourseRequiresCourse(Course subject, Course object) {
		return new CourseRequiresCourse(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public CourseRequiresCourse createCourseRequiresCourse(Course subject, Course object) throws de.fu.weave.orm.DatabaseException {
		CourseRequiresCourse obj = newCourseRequiresCourse(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseRequiresCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"courseRequiresCourse\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstancePrefersRoom> getElementInstancePrefersRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("element_instance", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("room", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersRoom\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<ElementInstancePrefersRoom> resultCollection = new java.util.ArrayList<ElementInstancePrefersRoom>();
			while (result.next()) {
				resultCollection.add(new ElementInstancePrefersRoom(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstancePrefersRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"elementInstancePrefersRoom\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstancePrefersRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException {
		deleteElementInstancePrefersRoom(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("element_instance", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("room", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersRoom fullyNewElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority) {
		return new ElementInstancePrefersRoom(this, true, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersRoom fullyCreateElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority) throws de.fu.weave.orm.DatabaseException {
		ElementInstancePrefersRoom obj = fullyNewElementInstancePrefersRoom(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersRoom newElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority) {
		return new ElementInstancePrefersRoom(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersRoom createElementInstancePrefersRoom(CourseElementInstance subject, Room object, int _priority) throws de.fu.weave.orm.DatabaseException {
		ElementInstancePrefersRoom obj = newElementInstancePrefersRoom(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportElementInstancePrefersRoom(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"elementInstancePrefersRoom\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstancePrefersTimeslot> getElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("room", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("timeslot", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<ElementInstancePrefersTimeslot> resultCollection = new java.util.ArrayList<ElementInstancePrefersTimeslot>();
			while (result.next()) {
				resultCollection.add(new ElementInstancePrefersTimeslot(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstancePrefersTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object) throws de.fu.weave.orm.DatabaseException {
		deleteElementInstancePrefersTimeslot(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("room", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("timeslot", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersTimeslot fullyNewElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority) {
		return new ElementInstancePrefersTimeslot(this, true, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersTimeslot fullyCreateElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException {
		ElementInstancePrefersTimeslot obj = fullyNewElementInstancePrefersTimeslot(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersTimeslot newElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority) {
		return new ElementInstancePrefersTimeslot(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstancePrefersTimeslot createElementInstancePrefersTimeslot(CourseElementInstance subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException {
		ElementInstancePrefersTimeslot obj = newElementInstancePrefersTimeslot(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportElementInstancePrefersTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"elementInstancePrefersTimeslot\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstanceRequiresFeature> getElementInstanceRequiresFeature(CourseElementInstance subject, Feature object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("element_instance", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("feature", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceRequiresFeature\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<ElementInstanceRequiresFeature> resultCollection = new java.util.ArrayList<ElementInstanceRequiresFeature>();
			while (result.next()) {
				resultCollection.add(new ElementInstanceRequiresFeature(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstanceRequiresFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"elementInstanceRequiresFeature\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstanceRequiresFeature(CourseElementInstance subject, Feature object) throws de.fu.weave.orm.DatabaseException {
		deleteElementInstanceRequiresFeature(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("element_instance", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("feature", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @param _quantityMin 
	 * @param _quantityBetter 
	 * @since Iteration3
	 */
	public ElementInstanceRequiresFeature fullyNewElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _priority, Integer _quantityMin, Integer _quantityBetter) {
		return new ElementInstanceRequiresFeature(this, true, subject, object, _priority, _quantityMin, _quantityBetter);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @param _quantityMin 
	 * @param _quantityBetter 
	 * @since Iteration3
	 */
	public ElementInstanceRequiresFeature fullyCreateElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _priority, Integer _quantityMin, Integer _quantityBetter) throws de.fu.weave.orm.DatabaseException {
		ElementInstanceRequiresFeature obj = fullyNewElementInstanceRequiresFeature(subject, object, _priority, _quantityMin, _quantityBetter);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstanceRequiresFeature newElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _priority) {
		return new ElementInstanceRequiresFeature(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementInstanceRequiresFeature createElementInstanceRequiresFeature(CourseElementInstance subject, Feature object, int _priority) throws de.fu.weave.orm.DatabaseException {
		ElementInstanceRequiresFeature obj = newElementInstanceRequiresFeature(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportElementInstanceRequiresFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"elementInstanceRequiresFeature\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstanceTakesPlaceInRoom> getElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("element_instance", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("room", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<ElementInstanceTakesPlaceInRoom> resultCollection = new java.util.ArrayList<ElementInstanceTakesPlaceInRoom>();
			while (result.next()) {
				resultCollection.add(new ElementInstanceTakesPlaceInRoom(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstanceTakesPlaceInRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object) throws de.fu.weave.orm.DatabaseException {
		deleteElementInstanceTakesPlaceInRoom(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("element_instance", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("room", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _timeslot 
				The Timeslot in which the Course Element Instance will take place in the given Room.
			
	 * @since Iteration3
	 */
	public ElementInstanceTakesPlaceInRoom fullyNewElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot) {
		return new ElementInstanceTakesPlaceInRoom(this, true, subject, object, _timeslot);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _timeslot 
				The Timeslot in which the Course Element Instance will take place in the given Room.
			
	 * @since Iteration3
	 */
	public ElementInstanceTakesPlaceInRoom fullyCreateElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException {
		ElementInstanceTakesPlaceInRoom obj = fullyNewElementInstanceTakesPlaceInRoom(subject, object, _timeslot);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _timeslot 
				The Timeslot in which the Course Element Instance will take place in the given Room.
			
	 * @since Iteration3
	 */
	public ElementInstanceTakesPlaceInRoom newElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot) {
		return new ElementInstanceTakesPlaceInRoom(this, subject, object, _timeslot);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _timeslot 
				The Timeslot in which the Course Element Instance will take place in the given Room.
			
	 * @since Iteration3
	 */
	public ElementInstanceTakesPlaceInRoom createElementInstanceTakesPlaceInRoom(CourseElementInstance subject, Room object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException {
		ElementInstanceTakesPlaceInRoom obj = newElementInstanceTakesPlaceInRoom(subject, object, _timeslot);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportElementInstanceTakesPlaceInRoom(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"elementInstanceTakesPlaceInRoom\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<ElementRequiresFeature> getElementRequiresFeature(CourseElement subject, Feature object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("course_element", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("feature", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"elementRequiresFeature\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<ElementRequiresFeature> resultCollection = new java.util.ArrayList<ElementRequiresFeature>();
			while (result.next()) {
				resultCollection.add(new ElementRequiresFeature(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteElementRequiresFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"elementRequiresFeature\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteElementRequiresFeature(CourseElement subject, Feature object) throws de.fu.weave.orm.DatabaseException {
		deleteElementRequiresFeature(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("course_element", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("feature", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @param _quantityMin 
	 * @param _quantityBetter 
	 * @since Iteration3
	 */
	public ElementRequiresFeature fullyNewElementRequiresFeature(CourseElement subject, Feature object, int _priority, Integer _quantityMin, Integer _quantityBetter) {
		return new ElementRequiresFeature(this, true, subject, object, _priority, _quantityMin, _quantityBetter);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @param _quantityMin 
	 * @param _quantityBetter 
	 * @since Iteration3
	 */
	public ElementRequiresFeature fullyCreateElementRequiresFeature(CourseElement subject, Feature object, int _priority, Integer _quantityMin, Integer _quantityBetter) throws de.fu.weave.orm.DatabaseException {
		ElementRequiresFeature obj = fullyNewElementRequiresFeature(subject, object, _priority, _quantityMin, _quantityBetter);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementRequiresFeature newElementRequiresFeature(CourseElement subject, Feature object, int _priority) {
		return new ElementRequiresFeature(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public ElementRequiresFeature createElementRequiresFeature(CourseElement subject, Feature object, int _priority) throws de.fu.weave.orm.DatabaseException {
		ElementRequiresFeature obj = newElementRequiresFeature(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportElementRequiresFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"elementRequiresFeature\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonBelongsToGroup> getPersonBelongsToGroup(Person subject, Group object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("group", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personBelongsToGroup\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonBelongsToGroup> resultCollection = new java.util.ArrayList<PersonBelongsToGroup>();
			while (result.next()) {
				resultCollection.add(new PersonBelongsToGroup(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonBelongsToGroup(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personBelongsToGroup\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonBelongsToGroup(Person subject, Group object) throws de.fu.weave.orm.DatabaseException {
		deletePersonBelongsToGroup(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("group", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @since Iteration3
	 */
	public PersonBelongsToGroup fullyNewPersonBelongsToGroup(Person subject, Group object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) {
		return new PersonBelongsToGroup(this, true, subject, object, _ctime, _mtime, _createdBy);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @since Iteration3
	 */
	public PersonBelongsToGroup fullyCreatePersonBelongsToGroup(Person subject, Group object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) throws de.fu.weave.orm.DatabaseException {
		PersonBelongsToGroup obj = fullyNewPersonBelongsToGroup(subject, object, _ctime, _mtime, _createdBy);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public PersonBelongsToGroup newPersonBelongsToGroup(Person subject, Group object) {
		return new PersonBelongsToGroup(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public PersonBelongsToGroup createPersonBelongsToGroup(Person subject, Group object) throws de.fu.weave.orm.DatabaseException {
		PersonBelongsToGroup obj = newPersonBelongsToGroup(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonBelongsToGroup(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personBelongsToGroup\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonEnrolledInCourseInstance> getPersonEnrolledInCourseInstance(Person subject, CourseInstance object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("course_instance", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personEnrolledInCourseInstance\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonEnrolledInCourseInstance> resultCollection = new java.util.ArrayList<PersonEnrolledInCourseInstance>();
			while (result.next()) {
				resultCollection.add(new PersonEnrolledInCourseInstance(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonEnrolledInCourseInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personEnrolledInCourseInstance\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonEnrolledInCourseInstance(Person subject, CourseInstance object) throws de.fu.weave.orm.DatabaseException {
		deletePersonEnrolledInCourseInstance(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("course_instance", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @since Iteration3
	 */
	public PersonEnrolledInCourseInstance fullyNewPersonEnrolledInCourseInstance(Person subject, CourseInstance object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) {
		return new PersonEnrolledInCourseInstance(this, true, subject, object, _ctime, _mtime, _createdBy);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @since Iteration3
	 */
	public PersonEnrolledInCourseInstance fullyCreatePersonEnrolledInCourseInstance(Person subject, CourseInstance object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) throws de.fu.weave.orm.DatabaseException {
		PersonEnrolledInCourseInstance obj = fullyNewPersonEnrolledInCourseInstance(subject, object, _ctime, _mtime, _createdBy);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public PersonEnrolledInCourseInstance newPersonEnrolledInCourseInstance(Person subject, CourseInstance object) {
		return new PersonEnrolledInCourseInstance(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public PersonEnrolledInCourseInstance createPersonEnrolledInCourseInstance(Person subject, CourseInstance object) throws de.fu.weave.orm.DatabaseException {
		PersonEnrolledInCourseInstance obj = newPersonEnrolledInCourseInstance(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonEnrolledInCourseInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personEnrolledInCourseInstance\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonGivesCourse> getPersonGivesCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("person", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("course", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personGivesCourse\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonGivesCourse> resultCollection = new java.util.ArrayList<PersonGivesCourse>();
			while (result.next()) {
				resultCollection.add(new PersonGivesCourse(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonGivesCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personGivesCourse\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonGivesCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException {
		deletePersonGivesCourse(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("person", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("course", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonGivesCourse fullyNewPersonGivesCourse(Person subject, Course object, int _priority) {
		return new PersonGivesCourse(this, true, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonGivesCourse fullyCreatePersonGivesCourse(Person subject, Course object, int _priority) throws de.fu.weave.orm.DatabaseException {
		PersonGivesCourse obj = fullyNewPersonGivesCourse(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonGivesCourse newPersonGivesCourse(Person subject, Course object, int _priority) {
		return new PersonGivesCourse(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonGivesCourse createPersonGivesCourse(Person subject, Course object, int _priority) throws de.fu.weave.orm.DatabaseException {
		PersonGivesCourse obj = newPersonGivesCourse(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonGivesCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personGivesCourse\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonHasAttribute> getPersonHasAttribute(Person subject, Attribute object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("attribute", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personHasAttribute\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonHasAttribute> resultCollection = new java.util.ArrayList<PersonHasAttribute>();
			while (result.next()) {
				resultCollection.add(new PersonHasAttribute(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonHasAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personHasAttribute\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonHasAttribute(Person subject, Attribute object) throws de.fu.weave.orm.DatabaseException {
		deletePersonHasAttribute(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("attribute", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _time 
	 * @param _editor 
	 * @param _value 
	 * @since Iteration3
	 */
	public PersonHasAttribute fullyNewPersonHasAttribute(Person subject, Attribute object, java.sql.Timestamp _time, Person _editor, String _value) {
		return new PersonHasAttribute(this, true, subject, object, _time, _editor, _value);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _time 
	 * @param _editor 
	 * @param _value 
	 * @since Iteration3
	 */
	public PersonHasAttribute fullyCreatePersonHasAttribute(Person subject, Attribute object, java.sql.Timestamp _time, Person _editor, String _value) throws de.fu.weave.orm.DatabaseException {
		PersonHasAttribute obj = fullyNewPersonHasAttribute(subject, object, _time, _editor, _value);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _editor 
	 * @since Iteration3
	 */
	public PersonHasAttribute newPersonHasAttribute(Person subject, Attribute object, Person _editor) {
		return new PersonHasAttribute(this, subject, object, _editor);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _editor 
	 * @since Iteration3
	 */
	public PersonHasAttribute createPersonHasAttribute(Person subject, Attribute object, Person _editor) throws de.fu.weave.orm.DatabaseException {
		PersonHasAttribute obj = newPersonHasAttribute(subject, object, _editor);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonHasAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personHasAttribute\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonHasPrivilege> getPersonHasPrivilege(Person subject, Privilege object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("privilege", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personHasPrivilege\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonHasPrivilege> resultCollection = new java.util.ArrayList<PersonHasPrivilege>();
			while (result.next()) {
				resultCollection.add(new PersonHasPrivilege(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonHasPrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personHasPrivilege\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonHasPrivilege(Person subject, Privilege object) throws de.fu.weave.orm.DatabaseException {
		deletePersonHasPrivilege(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("privilege", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _target 
				The target of this relation (should be a Person) will gain the new Privilege.
			
	 * @param _becauseOfRole 
				This is a explanation because of which Role the Privilege was gained.
			
	 * @since Iteration3
	 */
	public PersonHasPrivilege fullyNewPersonHasPrivilege(Person subject, Privilege object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _target, Boolean _becauseOfRole) {
		return new PersonHasPrivilege(this, true, subject, object, _ctime, _mtime, _createdBy, _target, _becauseOfRole);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _target 
				The target of this relation (should be a Person) will gain the new Privilege.
			
	 * @param _becauseOfRole 
				This is a explanation because of which Role the Privilege was gained.
			
	 * @since Iteration3
	 */
	public PersonHasPrivilege fullyCreatePersonHasPrivilege(Person subject, Privilege object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _target, Boolean _becauseOfRole) throws de.fu.weave.orm.DatabaseException {
		PersonHasPrivilege obj = fullyNewPersonHasPrivilege(subject, object, _ctime, _mtime, _createdBy, _target, _becauseOfRole);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public PersonHasPrivilege newPersonHasPrivilege(Person subject, Privilege object) {
		return new PersonHasPrivilege(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public PersonHasPrivilege createPersonHasPrivilege(Person subject, Privilege object) throws de.fu.weave.orm.DatabaseException {
		PersonHasPrivilege obj = newPersonHasPrivilege(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonHasPrivilege(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personHasPrivilege\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonHasRole> getPersonHasRole(Person subject, Role object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("role", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personHasRole\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonHasRole> resultCollection = new java.util.ArrayList<PersonHasRole>();
			while (result.next()) {
				resultCollection.add(new PersonHasRole(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonHasRole(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personHasRole\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonHasRole(Person subject, Role object) throws de.fu.weave.orm.DatabaseException {
		deletePersonHasRole(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("role", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @since Iteration3
	 */
	public PersonHasRole fullyNewPersonHasRole(Person subject, Role object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) {
		return new PersonHasRole(this, true, subject, object, _ctime, _mtime, _createdBy);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @since Iteration3
	 */
	public PersonHasRole fullyCreatePersonHasRole(Person subject, Role object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) throws de.fu.weave.orm.DatabaseException {
		PersonHasRole obj = fullyNewPersonHasRole(subject, object, _ctime, _mtime, _createdBy);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public PersonHasRole newPersonHasRole(Person subject, Role object) {
		return new PersonHasRole(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public PersonHasRole createPersonHasRole(Person subject, Role object) throws de.fu.weave.orm.DatabaseException {
		PersonHasRole obj = newPersonHasRole(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonHasRole(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personHasRole\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonPrefersTimeslot> getPersonPrefersTimeslot(Person subject, Timeslot object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("timeslot", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personPrefersTimeslot\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonPrefersTimeslot> resultCollection = new java.util.ArrayList<PersonPrefersTimeslot>();
			while (result.next()) {
				resultCollection.add(new PersonPrefersTimeslot(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonPrefersTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personPrefersTimeslot\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonPrefersTimeslot(Person subject, Timeslot object) throws de.fu.weave.orm.DatabaseException {
		deletePersonPrefersTimeslot(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("timeslot", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonPrefersTimeslot fullyNewPersonPrefersTimeslot(Person subject, Timeslot object, int _priority) {
		return new PersonPrefersTimeslot(this, true, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonPrefersTimeslot fullyCreatePersonPrefersTimeslot(Person subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException {
		PersonPrefersTimeslot obj = fullyNewPersonPrefersTimeslot(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonPrefersTimeslot newPersonPrefersTimeslot(Person subject, Timeslot object, int _priority) {
		return new PersonPrefersTimeslot(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public PersonPrefersTimeslot createPersonPrefersTimeslot(Person subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException {
		PersonPrefersTimeslot obj = newPersonPrefersTimeslot(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonPrefersTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personPrefersTimeslot\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonSuccessfullyPassedCourse> getPersonSuccessfullyPassedCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("course", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonSuccessfullyPassedCourse> resultCollection = new java.util.ArrayList<PersonSuccessfullyPassedCourse>();
			while (result.next()) {
				resultCollection.add(new PersonSuccessfullyPassedCourse(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonSuccessfullyPassedCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonSuccessfullyPassedCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException {
		deletePersonSuccessfullyPassedCourse(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("course", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _grade 
				The grade the Person got in the specified Course.
			
	 * @param _notes 
				Notes regarding the Person in question.
			
	 * @since Iteration3
	 */
	public PersonSuccessfullyPassedCourse fullyNewPersonSuccessfullyPassedCourse(Person subject, Course object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _grade, String _notes) {
		return new PersonSuccessfullyPassedCourse(this, true, subject, object, _ctime, _mtime, _createdBy, _grade, _notes);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _grade 
				The grade the Person got in the specified Course.
			
	 * @param _notes 
				Notes regarding the Person in question.
			
	 * @since Iteration3
	 */
	public PersonSuccessfullyPassedCourse fullyCreatePersonSuccessfullyPassedCourse(Person subject, Course object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _grade, String _notes) throws de.fu.weave.orm.DatabaseException {
		PersonSuccessfullyPassedCourse obj = fullyNewPersonSuccessfullyPassedCourse(subject, object, _ctime, _mtime, _createdBy, _grade, _notes);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public PersonSuccessfullyPassedCourse newPersonSuccessfullyPassedCourse(Person subject, Course object) {
		return new PersonSuccessfullyPassedCourse(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public PersonSuccessfullyPassedCourse createPersonSuccessfullyPassedCourse(Person subject, Course object) throws de.fu.weave.orm.DatabaseException {
		PersonSuccessfullyPassedCourse obj = newPersonSuccessfullyPassedCourse(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonSuccessfullyPassedCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personSuccessfullyPassedCourse\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<PersonTakesPartInElementInstance> getPersonTakesPartInElementInstance(Person subject, CourseElementInstance object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("element_instance", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"personTakesPartInElementInstance\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<PersonTakesPartInElementInstance> resultCollection = new java.util.ArrayList<PersonTakesPartInElementInstance>();
			while (result.next()) {
				resultCollection.add(new PersonTakesPartInElementInstance(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deletePersonTakesPartInElementInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"personTakesPartInElementInstance\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deletePersonTakesPartInElementInstance(Person subject, CourseElementInstance object) throws de.fu.weave.orm.DatabaseException {
		deletePersonTakesPartInElementInstance(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("user", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("element_instance", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _timeslot 
				The Timeslot in which the Person will take part in the given Course Element Instance
			
	 * @since Iteration3
	 */
	public PersonTakesPartInElementInstance fullyNewPersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot) {
		return new PersonTakesPartInElementInstance(this, true, subject, object, _timeslot);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _timeslot 
				The Timeslot in which the Person will take part in the given Course Element Instance
			
	 * @since Iteration3
	 */
	public PersonTakesPartInElementInstance fullyCreatePersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException {
		PersonTakesPartInElementInstance obj = fullyNewPersonTakesPartInElementInstance(subject, object, _timeslot);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _timeslot 
				The Timeslot in which the Person will take part in the given Course Element Instance
			
	 * @since Iteration3
	 */
	public PersonTakesPartInElementInstance newPersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot) {
		return new PersonTakesPartInElementInstance(this, subject, object, _timeslot);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _timeslot 
				The Timeslot in which the Person will take part in the given Course Element Instance
			
	 * @since Iteration3
	 */
	public PersonTakesPartInElementInstance createPersonTakesPartInElementInstance(Person subject, CourseElementInstance object, Timeslot _timeslot) throws de.fu.weave.orm.DatabaseException {
		PersonTakesPartInElementInstance obj = newPersonTakesPartInElementInstance(subject, object, _timeslot);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPersonTakesPartInElementInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"personTakesPartInElementInstance\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<RoleImpliesAttribute> getRoleImpliesAttribute(Role subject, Attribute object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("role", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("attribute", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"roleImpliesAttribute\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<RoleImpliesAttribute> resultCollection = new java.util.ArrayList<RoleImpliesAttribute>();
			while (result.next()) {
				resultCollection.add(new RoleImpliesAttribute(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteRoleImpliesAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"roleImpliesAttribute\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteRoleImpliesAttribute(Role subject, Attribute object) throws de.fu.weave.orm.DatabaseException {
		deleteRoleImpliesAttribute(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("role", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("attribute", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _default 
				Describes whether or not this attribute is set by default.
			
	 * @param _required 
				Whether or not this Attribute is required or not.
			
	 * @since Iteration3
	 */
	public RoleImpliesAttribute fullyNewRoleImpliesAttribute(Role subject, Attribute object, String _default, boolean _required) {
		return new RoleImpliesAttribute(this, true, subject, object, _default, _required);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _default 
				Describes whether or not this attribute is set by default.
			
	 * @param _required 
				Whether or not this Attribute is required or not.
			
	 * @since Iteration3
	 */
	public RoleImpliesAttribute fullyCreateRoleImpliesAttribute(Role subject, Attribute object, String _default, boolean _required) throws de.fu.weave.orm.DatabaseException {
		RoleImpliesAttribute obj = fullyNewRoleImpliesAttribute(subject, object, _default, _required);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public RoleImpliesAttribute newRoleImpliesAttribute(Role subject, Attribute object) {
		return new RoleImpliesAttribute(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public RoleImpliesAttribute createRoleImpliesAttribute(Role subject, Attribute object) throws de.fu.weave.orm.DatabaseException {
		RoleImpliesAttribute obj = newRoleImpliesAttribute(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportRoleImpliesAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"roleImpliesAttribute\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<RoleImpliesPrivilege> getRoleImpliesPrivilege(Role subject, Privilege object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("role", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("privilege", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"roleImpliesPrivilege\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<RoleImpliesPrivilege> resultCollection = new java.util.ArrayList<RoleImpliesPrivilege>();
			while (result.next()) {
				resultCollection.add(new RoleImpliesPrivilege(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteRoleImpliesPrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"roleImpliesPrivilege\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteRoleImpliesPrivilege(Role subject, Privilege object) throws de.fu.weave.orm.DatabaseException {
		deleteRoleImpliesPrivilege(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("role", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("privilege", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _target 
				The target of this relation (should be a Role) will gain the new Privilege.
			
	 * @since Iteration3
	 */
	public RoleImpliesPrivilege fullyNewRoleImpliesPrivilege(Role subject, Privilege object, String _target) {
		return new RoleImpliesPrivilege(this, true, subject, object, _target);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _target 
				The target of this relation (should be a Role) will gain the new Privilege.
			
	 * @since Iteration3
	 */
	public RoleImpliesPrivilege fullyCreateRoleImpliesPrivilege(Role subject, Privilege object, String _target) throws de.fu.weave.orm.DatabaseException {
		RoleImpliesPrivilege obj = fullyNewRoleImpliesPrivilege(subject, object, _target);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @since Iteration3
	 */
	public RoleImpliesPrivilege newRoleImpliesPrivilege(Role subject, Privilege object) {
		return new RoleImpliesPrivilege(this, subject, object);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @since Iteration3
	 */
	public RoleImpliesPrivilege createRoleImpliesPrivilege(Role subject, Privilege object) throws de.fu.weave.orm.DatabaseException {
		RoleImpliesPrivilege obj = newRoleImpliesPrivilege(subject, object);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportRoleImpliesPrivilege(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"roleImpliesPrivilege\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<RoomPrefersTimeslot> getRoomPrefersTimeslot(Room subject, Timeslot object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("room", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("timeslot", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"roomPrefersTimeslot\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<RoomPrefersTimeslot> resultCollection = new java.util.ArrayList<RoomPrefersTimeslot>();
			while (result.next()) {
				resultCollection.add(new RoomPrefersTimeslot(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteRoomPrefersTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"roomPrefersTimeslot\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteRoomPrefersTimeslot(Room subject, Timeslot object) throws de.fu.weave.orm.DatabaseException {
		deleteRoomPrefersTimeslot(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("room", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("timeslot", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public RoomPrefersTimeslot fullyNewRoomPrefersTimeslot(Room subject, Timeslot object, int _priority) {
		return new RoomPrefersTimeslot(this, true, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public RoomPrefersTimeslot fullyCreateRoomPrefersTimeslot(Room subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException {
		RoomPrefersTimeslot obj = fullyNewRoomPrefersTimeslot(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public RoomPrefersTimeslot newRoomPrefersTimeslot(Room subject, Timeslot object, int _priority) {
		return new RoomPrefersTimeslot(this, subject, object, _priority);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _priority 
	 * @since Iteration3
	 */
	public RoomPrefersTimeslot createRoomPrefersTimeslot(Room subject, Timeslot object, int _priority) throws de.fu.weave.orm.DatabaseException {
		RoomPrefersTimeslot obj = newRoomPrefersTimeslot(subject, object, _priority);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportRoomPrefersTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"roomPrefersTimeslot\"", with_schema);
	}
	/**
	 * @since Iteration3
	 */
	public java.util.Collection<RoomProvidesFeature> getRoomProvidesFeature(Room subject, Feature object) throws de.fu.weave.orm.DatabaseException {
		de.fu.weave.orm.Filter[] filters = {
			de.fu.weave.orm.filters.Filters.eq("room", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("feature", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"roomProvidesFeature\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<RoomProvidesFeature> resultCollection = new java.util.ArrayList<RoomProvidesFeature>();
			while (result.next()) {
				resultCollection.add(new RoomProvidesFeature(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
	 * Deletes the relationships matching the given filters.
	 *
	 * @since Iteration3
	 */
	public void deleteRoomProvidesFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"roomProvidesFeature\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the relationship identified by the given subject and object.
	 *
	 * @since Iteration3
	 */
	public void deleteRoomProvidesFeature(Room subject, Feature object) throws de.fu.weave.orm.DatabaseException {
		deleteRoomProvidesFeature(de.fu.weave.orm.filters.Filters.all(
			de.fu.weave.orm.filters.Filters.eq("room", subject.id()),
			de.fu.weave.orm.filters.Filters.eq("feature", object.id())));
	}
	
	/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * 
	 * @param _quantity 
				The quantity in which the Room provides the Feature of question.
			
	 * @since Iteration3
	 */
	public RoomProvidesFeature fullyNewRoomProvidesFeature(Room subject, Feature object, int _quantity) {
		return new RoomProvidesFeature(this, true, subject, object, _quantity);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _quantity 
				The quantity in which the Room provides the Feature of question.
			
	 * @since Iteration3
	 */
	public RoomProvidesFeature fullyCreateRoomProvidesFeature(Room subject, Feature object, int _quantity) throws de.fu.weave.orm.DatabaseException {
		RoomProvidesFeature obj = fullyNewRoomProvidesFeature(subject, object, _quantity);
		obj.create();
		return obj;
	}
	
	/**
     * 
	 * @param _quantity 
				The quantity in which the Room provides the Feature of question.
			
	 * @since Iteration3
	 */
	public RoomProvidesFeature newRoomProvidesFeature(Room subject, Feature object, int _quantity) {
		return new RoomProvidesFeature(this, subject, object, _quantity);
	}
	
	/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * 
	 * @param _quantity 
				The quantity in which the Room provides the Feature of question.
			
	 * @since Iteration3
	 */
	public RoomProvidesFeature createRoomProvidesFeature(Room subject, Feature object, int _quantity) throws de.fu.weave.orm.DatabaseException {
		RoomProvidesFeature obj = newRoomProvidesFeature(subject, object, _quantity);
		obj.create();
		return obj;
	}
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportRoomProvidesFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"roomProvidesFeature\"", with_schema);
	}
	
	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportAcademicTerm(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"AcademicTerm\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link AcademicTerm#create()}) a AcademicTerm with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _start 
				The date at which the Academic turn begins.
			
	 * @param _end 
				The date at which the Academic turn ends.
			
	 * @param _startLessons 
				The date of the first Course Element Instance taking place.
			
	 * @param _endLessons 
				The date of the last Course Element Instance taking place.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public AcademicTerm newAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) {
		return new AcademicTerm(this, _name, _start, _end, _startLessons, _endLessons);
	}
	
	/**
	 * Creates a AcademicTerm with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _start 
				The date at which the Academic turn begins.
			
	 * @param _end 
				The date at which the Academic turn ends.
			
	 * @param _startLessons 
				The date of the first Course Element Instance taking place.
			
	 * @param _endLessons 
				The date of the last Course Element Instance taking place.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public AcademicTerm createAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) throws de.fu.weave.orm.DatabaseException {
		AcademicTerm entity = newAcademicTerm(_name, _start, _end, _startLessons, _endLessons);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a AcademicTerm and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _start 
				The date at which the Academic turn begins.
			
	 * @param _end 
				The date at which the Academic turn ends.
			
	 * @param _startLessons 
				The date of the first Course Element Instance taking place.
			
	 * @param _endLessons 
				The date of the last Course Element Instance taking place.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public AcademicTerm fullyCreateAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) throws de.fu.weave.orm.DatabaseException {
		AcademicTerm entity = fullyNewAcademicTerm(_name, _start, _end, _startLessons, _endLessons);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link AcademicTerm#create()}) a AcademicTerm and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 *
	 * @param _start 
				The date at which the Academic turn begins.
			
	 *
	 * @param _end 
				The date at which the Academic turn ends.
			
	 *
	 * @param _startLessons 
				The date of the first Course Element Instance taking place.
			
	 *
	 * @param _endLessons 
				The date of the last Course Element Instance taking place.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public AcademicTerm fullyNewAcademicTerm(String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) throws de.fu.weave.orm.DatabaseException {
		AcademicTerm entity = new AcademicTerm(this, true, _name, _start, _end, _startLessons, _endLessons);
		return entity;
	}
	
	/**
	 * Gets AcademicTerm entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<AcademicTerm> getAcademicTerm(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"AcademicTerm\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<AcademicTerm> resultCollection = new java.util.ArrayList<AcademicTerm>();
			while (result.next()) {
				resultCollection.add(new AcademicTerm(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets AcademicTerm entities matching the given filters. Essentially {@link #getAcademicTerm(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<AcademicTerm> getAcademicTerm(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getAcademicTerm(0, limit, filters);
	}
	
	/**
     * Gets AcademicTerm entities matching the given filters. Essentially {@link #getAcademicTerm(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<AcademicTerm> getAcademicTerm(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getAcademicTerm(0, -1, filters);
	}
	
	/**
	 * Gets a single AcademicTerm entity by its ID.
	 *
	 * @param id The id of the AcademicTerm retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public AcademicTerm getAcademicTerm(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"AcademicTerm\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new AcademicTerm(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteAcademicTerm(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"AcademicTerm\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteAcademicTerm(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"AcademicTerm\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Attribute\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Attribute#create()}) a Attribute with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Attribute newAttribute(String _name) {
		return new Attribute(this, _name);
	}
	
	/**
	 * Creates a Attribute with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Attribute createAttribute(String _name) throws de.fu.weave.orm.DatabaseException {
		Attribute entity = newAttribute(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Attribute and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _type 
	 * @param _uniqueValue 
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Attribute fullyCreateAttribute(String _name, String _type, boolean _uniqueValue) throws de.fu.weave.orm.DatabaseException {
		Attribute entity = fullyNewAttribute(_name, _type, _uniqueValue);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Attribute#create()}) a Attribute and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 *
	 * @param _type 
	 *
	 * @param _uniqueValue 
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Attribute fullyNewAttribute(String _name, String _type, boolean _uniqueValue) throws de.fu.weave.orm.DatabaseException {
		Attribute entity = new Attribute(this, true, _name, _type, _uniqueValue);
		return entity;
	}
	
	/**
	 * Gets Attribute entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Attribute> getAttribute(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Attribute\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Attribute> resultCollection = new java.util.ArrayList<Attribute>();
			while (result.next()) {
				resultCollection.add(new Attribute(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Attribute entities matching the given filters. Essentially {@link #getAttribute(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Attribute> getAttribute(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getAttribute(0, limit, filters);
	}
	
	/**
     * Gets Attribute entities matching the given filters. Essentially {@link #getAttribute(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Attribute> getAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getAttribute(0, -1, filters);
	}
	
	/**
	 * Gets a single Attribute entity by its ID.
	 *
	 * @param id The id of the Attribute retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Attribute getAttribute(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Attribute\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Attribute(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Attribute\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteAttribute(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Attribute\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportBuilding(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Building\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Building#create()}) a Building with only required attributes.
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Building newBuilding(String _address) {
		return new Building(this, _address);
	}
	
	/**
	 * Creates a Building with only required attributes.
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Building createBuilding(String _address) throws de.fu.weave.orm.DatabaseException {
		Building entity = newBuilding(_address);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Building and initializes all attributes.
	 * @param _name 
				The name of Building has to be unique so it identifies the Building.
			
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Building fullyCreateBuilding(String _name, String _address) throws de.fu.weave.orm.DatabaseException {
		Building entity = fullyNewBuilding(_name, _address);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Building#create()}) a Building and initializes all attributes.
	 *
	 * @param _name 
				The name of Building has to be unique so it identifies the Building.
			
	 *
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Building fullyNewBuilding(String _name, String _address) throws de.fu.weave.orm.DatabaseException {
		Building entity = new Building(this, true, _name, _address);
		return entity;
	}
	
	/**
	 * Gets Building entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Building> getBuilding(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Building\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Building> resultCollection = new java.util.ArrayList<Building>();
			while (result.next()) {
				resultCollection.add(new Building(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Building entities matching the given filters. Essentially {@link #getBuilding(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Building> getBuilding(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getBuilding(0, limit, filters);
	}
	
	/**
     * Gets Building entities matching the given filters. Essentially {@link #getBuilding(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Building> getBuilding(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getBuilding(0, -1, filters);
	}
	
	/**
	 * Gets a single Building entity by its ID.
	 *
	 * @param id The id of the Building retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Building getBuilding(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Building\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Building(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteBuilding(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Building\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteBuilding(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Building\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportConfiguration(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Configuration\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Configuration#create()}) a Configuration with only required attributes.
	 * @param _key 
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Configuration newConfiguration(String _key) {
		return new Configuration(this, _key);
	}
	
	/**
	 * Creates a Configuration with only required attributes.
	 * @param _key 
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Configuration createConfiguration(String _key) throws de.fu.weave.orm.DatabaseException {
		Configuration entity = newConfiguration(_key);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Configuration and initializes all attributes.
	 * @param _key 
	 * @param _value 
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Configuration fullyCreateConfiguration(String _key, String _value) throws de.fu.weave.orm.DatabaseException {
		Configuration entity = fullyNewConfiguration(_key, _value);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Configuration#create()}) a Configuration and initializes all attributes.
	 *
	 * @param _key 
	 *
	 * @param _value 
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Configuration fullyNewConfiguration(String _key, String _value) throws de.fu.weave.orm.DatabaseException {
		Configuration entity = new Configuration(this, true, _key, _value);
		return entity;
	}
	
	/**
	 * Gets Configuration entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Configuration> getConfiguration(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Configuration\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Configuration> resultCollection = new java.util.ArrayList<Configuration>();
			while (result.next()) {
				resultCollection.add(new Configuration(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Configuration entities matching the given filters. Essentially {@link #getConfiguration(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Configuration> getConfiguration(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getConfiguration(0, limit, filters);
	}
	
	/**
     * Gets Configuration entities matching the given filters. Essentially {@link #getConfiguration(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Configuration> getConfiguration(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getConfiguration(0, -1, filters);
	}
	
	/**
	 * Gets a single Configuration entity by its ID.
	 *
	 * @param id The id of the Configuration retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Configuration getConfiguration(String id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Configuration\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Configuration(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteConfiguration(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Configuration\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteConfiguration(String id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Configuration\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourse(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Course\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Course#create()}) a Course with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Course newCourse(String _name) {
		return new Course(this, _name);
	}
	
	/**
	 * Creates a Course with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Course createCourse(String _name) throws de.fu.weave.orm.DatabaseException {
		Course entity = newCourse(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Course and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Course fullyCreateCourse(String _name) throws de.fu.weave.orm.DatabaseException {
		Course entity = fullyNewCourse(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Course#create()}) a Course and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Course fullyNewCourse(String _name) throws de.fu.weave.orm.DatabaseException {
		Course entity = new Course(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Course entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Course> getCourse(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Course\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Course> resultCollection = new java.util.ArrayList<Course>();
			while (result.next()) {
				resultCollection.add(new Course(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Course entities matching the given filters. Essentially {@link #getCourse(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Course> getCourse(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourse(0, limit, filters);
	}
	
	/**
     * Gets Course entities matching the given filters. Essentially {@link #getCourse(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Course> getCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourse(0, -1, filters);
	}
	
	/**
	 * Gets a single Course entity by its ID.
	 *
	 * @param id The id of the Course retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Course getCourse(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Course(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteCourse(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Course\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteCourse(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Course\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseAttribute(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"CourseAttribute\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link CourseAttribute#create()}) a CourseAttribute with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public CourseAttribute newCourseAttribute(String _name) {
		return new CourseAttribute(this, _name);
	}
	
	/**
	 * Creates a CourseAttribute with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public CourseAttribute createCourseAttribute(String _name) throws de.fu.weave.orm.DatabaseException {
		CourseAttribute entity = newCourseAttribute(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a CourseAttribute and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _type 
	 * @param _uniqueValue 
	 * @param _required 
				Whether or not this Course Attribute is obligatory.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public CourseAttribute fullyCreateCourseAttribute(String _name, String _type, boolean _uniqueValue, boolean _required) throws de.fu.weave.orm.DatabaseException {
		CourseAttribute entity = fullyNewCourseAttribute(_name, _type, _uniqueValue, _required);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link CourseAttribute#create()}) a CourseAttribute and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 *
	 * @param _type 
	 *
	 * @param _uniqueValue 
	 *
	 * @param _required 
				Whether or not this Course Attribute is obligatory.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public CourseAttribute fullyNewCourseAttribute(String _name, String _type, boolean _uniqueValue, boolean _required) throws de.fu.weave.orm.DatabaseException {
		CourseAttribute entity = new CourseAttribute(this, true, _name, _type, _uniqueValue, _required);
		return entity;
	}
	
	/**
	 * Gets CourseAttribute entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseAttribute> getCourseAttribute(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"CourseAttribute\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseAttribute> resultCollection = new java.util.ArrayList<CourseAttribute>();
			while (result.next()) {
				resultCollection.add(new CourseAttribute(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets CourseAttribute entities matching the given filters. Essentially {@link #getCourseAttribute(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseAttribute> getCourseAttribute(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseAttribute(0, limit, filters);
	}
	
	/**
     * Gets CourseAttribute entities matching the given filters. Essentially {@link #getCourseAttribute(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseAttribute> getCourseAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseAttribute(0, -1, filters);
	}
	
	/**
	 * Gets a single CourseAttribute entity by its ID.
	 *
	 * @param id The id of the CourseAttribute retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public CourseAttribute getCourseAttribute(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseAttribute\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new CourseAttribute(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteCourseAttribute(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"CourseAttribute\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseAttribute(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseAttribute\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseElement(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"CourseElement\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link CourseElement#create()}) a CourseElement with only required attributes.
	 * @param _partOf 
				The Course this Course Elements belongs to.
			
	 * @param _duration 
				The duration of the Course Element.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public CourseElement newCourseElement(Course _partOf, int _duration) {
		return new CourseElement(this, _partOf, _duration);
	}
	
	/**
	 * Creates a CourseElement with only required attributes.
	 * @param _partOf 
				The Course this Course Elements belongs to.
			
	 * @param _duration 
				The duration of the Course Element.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public CourseElement createCourseElement(Course _partOf, int _duration) throws de.fu.weave.orm.DatabaseException {
		CourseElement entity = newCourseElement(_partOf, _duration);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a CourseElement and initializes all attributes.
	 * @param _name 
				The name of the Course Element.
			
	 * @param _partOf 
				The Course this Course Elements belongs to.
			
	 * @param _duration 
				The duration of the Course Element.
			
	 * @param _type 
				The type of the Course Element.
			
	 * @param _required 
				Whether this Course Element is obligatory or not.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public CourseElement fullyCreateCourseElement(String _name, Course _partOf, int _duration, CourseElementType _type, boolean _required) throws de.fu.weave.orm.DatabaseException {
		CourseElement entity = fullyNewCourseElement(_name, _partOf, _duration, _type, _required);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link CourseElement#create()}) a CourseElement and initializes all attributes.
	 *
	 * @param _name 
				The name of the Course Element.
			
	 *
	 * @param _partOf 
				The Course this Course Elements belongs to.
			
	 *
	 * @param _duration 
				The duration of the Course Element.
			
	 *
	 * @param _type 
				The type of the Course Element.
			
	 *
	 * @param _required 
				Whether this Course Element is obligatory or not.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public CourseElement fullyNewCourseElement(String _name, Course _partOf, int _duration, CourseElementType _type, boolean _required) throws de.fu.weave.orm.DatabaseException {
		CourseElement entity = new CourseElement(this, true, _name, _partOf, _duration, _type, _required);
		return entity;
	}
	
	/**
	 * Gets CourseElement entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElement> getCourseElement(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"CourseElement\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseElement> resultCollection = new java.util.ArrayList<CourseElement>();
			while (result.next()) {
				resultCollection.add(new CourseElement(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets CourseElement entities matching the given filters. Essentially {@link #getCourseElement(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElement> getCourseElement(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseElement(0, limit, filters);
	}
	
	/**
     * Gets CourseElement entities matching the given filters. Essentially {@link #getCourseElement(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElement> getCourseElement(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseElement(0, -1, filters);
	}
	
	/**
	 * Gets a single CourseElement entity by its ID.
	 *
	 * @param id The id of the CourseElement retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public CourseElement getCourseElement(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElement\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new CourseElement(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteCourseElement(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"CourseElement\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseElement(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseElement\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseElementInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"CourseElementInstance\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link CourseElementInstance#create()}) a CourseElementInstance with only required attributes.
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public CourseElementInstance newCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, int _duration) {
		return new CourseElementInstance(this, _courseInstance, _courseElement, _duration);
	}
	
	/**
	 * Creates a CourseElementInstance with only required attributes.
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public CourseElementInstance createCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, int _duration) throws de.fu.weave.orm.DatabaseException {
		CourseElementInstance entity = newCourseElementInstance(_courseInstance, _courseElement, _duration);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a CourseElementInstance and initializes all attributes.
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 * @param _startingTimeslot 
				The starting Timeslot this Course Element Instance is beginning at.
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 * @param _schedulableLesson 
				Whether or not this Course Element Instance is scheduleable.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public CourseElementInstance fullyCreateCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, Timeslot _startingTimeslot, int _duration, boolean _schedulableLesson) throws de.fu.weave.orm.DatabaseException {
		CourseElementInstance entity = fullyNewCourseElementInstance(_courseInstance, _courseElement, _startingTimeslot, _duration, _schedulableLesson);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link CourseElementInstance#create()}) a CourseElementInstance and initializes all attributes.
	 *
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 *
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 *
	 * @param _startingTimeslot 
				The starting Timeslot this Course Element Instance is beginning at.
			
	 *
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 *
	 * @param _schedulableLesson 
				Whether or not this Course Element Instance is scheduleable.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public CourseElementInstance fullyNewCourseElementInstance(CourseInstance _courseInstance, CourseElement _courseElement, Timeslot _startingTimeslot, int _duration, boolean _schedulableLesson) throws de.fu.weave.orm.DatabaseException {
		CourseElementInstance entity = new CourseElementInstance(this, true, _courseInstance, _courseElement, _startingTimeslot, _duration, _schedulableLesson);
		return entity;
	}
	
	/**
	 * Gets CourseElementInstance entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElementInstance> getCourseElementInstance(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseElementInstance> resultCollection = new java.util.ArrayList<CourseElementInstance>();
			while (result.next()) {
				resultCollection.add(new CourseElementInstance(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets CourseElementInstance entities matching the given filters. Essentially {@link #getCourseElementInstance(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElementInstance> getCourseElementInstance(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseElementInstance(0, limit, filters);
	}
	
	/**
     * Gets CourseElementInstance entities matching the given filters. Essentially {@link #getCourseElementInstance(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElementInstance> getCourseElementInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseElementInstance(0, -1, filters);
	}
	
	/**
	 * Gets a single CourseElementInstance entity by its ID.
	 *
	 * @param id The id of the CourseElementInstance retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public CourseElementInstance getCourseElementInstance(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new CourseElementInstance(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteCourseElementInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"CourseElementInstance\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseElementInstance(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseElementInstance\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseElementType(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"CourseElementType\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link CourseElementType#create()}) a CourseElementType with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public CourseElementType newCourseElementType(String _name) {
		return new CourseElementType(this, _name);
	}
	
	/**
	 * Creates a CourseElementType with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public CourseElementType createCourseElementType(String _name) throws de.fu.weave.orm.DatabaseException {
		CourseElementType entity = newCourseElementType(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a CourseElementType and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public CourseElementType fullyCreateCourseElementType(String _name) throws de.fu.weave.orm.DatabaseException {
		CourseElementType entity = fullyNewCourseElementType(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link CourseElementType#create()}) a CourseElementType and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public CourseElementType fullyNewCourseElementType(String _name) throws de.fu.weave.orm.DatabaseException {
		CourseElementType entity = new CourseElementType(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets CourseElementType entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElementType> getCourseElementType(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"CourseElementType\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseElementType> resultCollection = new java.util.ArrayList<CourseElementType>();
			while (result.next()) {
				resultCollection.add(new CourseElementType(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets CourseElementType entities matching the given filters. Essentially {@link #getCourseElementType(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElementType> getCourseElementType(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseElementType(0, limit, filters);
	}
	
	/**
     * Gets CourseElementType entities matching the given filters. Essentially {@link #getCourseElementType(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseElementType> getCourseElementType(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseElementType(0, -1, filters);
	}
	
	/**
	 * Gets a single CourseElementType entity by its ID.
	 *
	 * @param id The id of the CourseElementType retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public CourseElementType getCourseElementType(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementType\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new CourseElementType(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteCourseElementType(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"CourseElementType\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseElementType(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseElementType\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportCourseInstance(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"CourseInstance\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link CourseInstance#create()}) a CourseInstance with only required attributes.
	 * @param _program 
				The Program this Course Instance is running in.
			
	 * @param _instanceOf 
	 * @param _start 
				The start date of the Course Instance.
			
	 * @param _end 
				The end date of the Course Instance.
			
	 * @param _mainLecturer 
				The docent for this Course Element.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public CourseInstance newCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) {
		return new CourseInstance(this, _program, _instanceOf, _start, _end, _mainLecturer);
	}
	
	/**
	 * Creates a CourseInstance with only required attributes.
	 * @param _program 
				The Program this Course Instance is running in.
			
	 * @param _instanceOf 
	 * @param _start 
				The start date of the Course Instance.
			
	 * @param _end 
				The end date of the Course Instance.
			
	 * @param _mainLecturer 
				The docent for this Course Element.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public CourseInstance createCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) throws de.fu.weave.orm.DatabaseException {
		CourseInstance entity = newCourseInstance(_program, _instanceOf, _start, _end, _mainLecturer);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a CourseInstance and initializes all attributes.
	 * @param _program 
				The Program this Course Instance is running in.
			
	 * @param _instanceOf 
	 * @param _start 
				The start date of the Course Instance.
			
	 * @param _end 
				The end date of the Course Instance.
			
	 * @param _mainLecturer 
				The docent for this Course Element.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public CourseInstance fullyCreateCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) throws de.fu.weave.orm.DatabaseException {
		CourseInstance entity = fullyNewCourseInstance(_program, _instanceOf, _start, _end, _mainLecturer);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link CourseInstance#create()}) a CourseInstance and initializes all attributes.
	 *
	 * @param _program 
				The Program this Course Instance is running in.
			
	 *
	 * @param _instanceOf 
	 *
	 * @param _start 
				The start date of the Course Instance.
			
	 *
	 * @param _end 
				The end date of the Course Instance.
			
	 *
	 * @param _mainLecturer 
				The docent for this Course Element.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public CourseInstance fullyNewCourseInstance(Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) throws de.fu.weave.orm.DatabaseException {
		CourseInstance entity = new CourseInstance(this, true, _program, _instanceOf, _start, _end, _mainLecturer);
		return entity;
	}
	
	/**
	 * Gets CourseInstance entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseInstance> getCourseInstance(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"CourseInstance\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<CourseInstance> resultCollection = new java.util.ArrayList<CourseInstance>();
			while (result.next()) {
				resultCollection.add(new CourseInstance(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets CourseInstance entities matching the given filters. Essentially {@link #getCourseInstance(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseInstance> getCourseInstance(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseInstance(0, limit, filters);
	}
	
	/**
     * Gets CourseInstance entities matching the given filters. Essentially {@link #getCourseInstance(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<CourseInstance> getCourseInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getCourseInstance(0, -1, filters);
	}
	
	/**
	 * Gets a single CourseInstance entity by its ID.
	 *
	 * @param id The id of the CourseInstance retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public CourseInstance getCourseInstance(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseInstance\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new CourseInstance(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteCourseInstance(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"CourseInstance\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteCourseInstance(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseInstance\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportDay(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Day\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Day#create()}) a Day with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Day newDay(String _name) {
		return new Day(this, _name);
	}
	
	/**
	 * Creates a Day with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Day createDay(String _name) throws de.fu.weave.orm.DatabaseException {
		Day entity = newDay(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Day and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Day fullyCreateDay(String _name) throws de.fu.weave.orm.DatabaseException {
		Day entity = fullyNewDay(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Day#create()}) a Day and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Day fullyNewDay(String _name) throws de.fu.weave.orm.DatabaseException {
		Day entity = new Day(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Day entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Day> getDay(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Day\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Day> resultCollection = new java.util.ArrayList<Day>();
			while (result.next()) {
				resultCollection.add(new Day(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Day entities matching the given filters. Essentially {@link #getDay(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Day> getDay(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getDay(0, limit, filters);
	}
	
	/**
     * Gets Day entities matching the given filters. Essentially {@link #getDay(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Day> getDay(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getDay(0, -1, filters);
	}
	
	/**
	 * Gets a single Day entity by its ID.
	 *
	 * @param id The id of the Day retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Day getDay(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Day\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Day(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteDay(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Day\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteDay(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Day\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportDepartment(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Department\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Department#create()}) a Department with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Department newDepartment(String _name) {
		return new Department(this, _name);
	}
	
	/**
	 * Creates a Department with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Department createDepartment(String _name) throws de.fu.weave.orm.DatabaseException {
		Department entity = newDepartment(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Department and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _superordinateDepartment 
				The superordinated department of this department.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Department fullyCreateDepartment(String _name, Department _superordinateDepartment) throws de.fu.weave.orm.DatabaseException {
		Department entity = fullyNewDepartment(_name, _superordinateDepartment);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Department#create()}) a Department and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 *
	 * @param _superordinateDepartment 
				The superordinated department of this department.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Department fullyNewDepartment(String _name, Department _superordinateDepartment) throws de.fu.weave.orm.DatabaseException {
		Department entity = new Department(this, true, _name, _superordinateDepartment);
		return entity;
	}
	
	/**
	 * Gets Department entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Department> getDepartment(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Department\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Department> resultCollection = new java.util.ArrayList<Department>();
			while (result.next()) {
				resultCollection.add(new Department(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Department entities matching the given filters. Essentially {@link #getDepartment(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Department> getDepartment(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getDepartment(0, limit, filters);
	}
	
	/**
     * Gets Department entities matching the given filters. Essentially {@link #getDepartment(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Department> getDepartment(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getDepartment(0, -1, filters);
	}
	
	/**
	 * Gets a single Department entity by its ID.
	 *
	 * @param id The id of the Department retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Department getDepartment(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Department\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Department(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteDepartment(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Department\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteDepartment(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Department\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportFeature(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Feature\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Feature#create()}) a Feature with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Feature newFeature(String _name) {
		return new Feature(this, _name);
	}
	
	/**
	 * Creates a Feature with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Feature createFeature(String _name) throws de.fu.weave.orm.DatabaseException {
		Feature entity = newFeature(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Feature and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Feature fullyCreateFeature(String _name) throws de.fu.weave.orm.DatabaseException {
		Feature entity = fullyNewFeature(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Feature#create()}) a Feature and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Feature fullyNewFeature(String _name) throws de.fu.weave.orm.DatabaseException {
		Feature entity = new Feature(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Feature entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Feature> getFeature(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Feature\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Feature> resultCollection = new java.util.ArrayList<Feature>();
			while (result.next()) {
				resultCollection.add(new Feature(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Feature entities matching the given filters. Essentially {@link #getFeature(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Feature> getFeature(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getFeature(0, limit, filters);
	}
	
	/**
     * Gets Feature entities matching the given filters. Essentially {@link #getFeature(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Feature> getFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getFeature(0, -1, filters);
	}
	
	/**
	 * Gets a single Feature entity by its ID.
	 *
	 * @param id The id of the Feature retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Feature getFeature(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Feature(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteFeature(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Feature\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteFeature(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Feature\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportGroup(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Group\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Group#create()}) a Group with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Group newGroup(String _name) {
		return new Group(this, _name);
	}
	
	/**
	 * Creates a Group with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Group createGroup(String _name) throws de.fu.weave.orm.DatabaseException {
		Group entity = newGroup(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Group and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Group fullyCreateGroup(String _name) throws de.fu.weave.orm.DatabaseException {
		Group entity = fullyNewGroup(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Group#create()}) a Group and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Group fullyNewGroup(String _name) throws de.fu.weave.orm.DatabaseException {
		Group entity = new Group(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Group entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Group> getGroup(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Group\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Group> resultCollection = new java.util.ArrayList<Group>();
			while (result.next()) {
				resultCollection.add(new Group(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Group entities matching the given filters. Essentially {@link #getGroup(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Group> getGroup(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getGroup(0, limit, filters);
	}
	
	/**
     * Gets Group entities matching the given filters. Essentially {@link #getGroup(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Group> getGroup(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getGroup(0, -1, filters);
	}
	
	/**
	 * Gets a single Group entity by its ID.
	 *
	 * @param id The id of the Group retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Group getGroup(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Group\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Group(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteGroup(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Group\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteGroup(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Group\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPerson(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Person\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Person#create()}) a Person with only required attributes.
	 * @param _firstName 
				The first name of a Person.
			
	 * @param _lastName 
				The last name of a Person.
			
	 * @param _loginName 
				The name that is used to log into the system. It has to be unique.
			
	 * @param _loginPassword 
				The password that is used together with the login name in order to log in into the system.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Person newPerson(String _firstName, String _lastName, String _loginName, String _loginPassword) {
		return new Person(this, _firstName, _lastName, _loginName, _loginPassword);
	}
	
	/**
	 * Creates a Person with only required attributes.
	 * @param _firstName 
				The first name of a Person.
			
	 * @param _lastName 
				The last name of a Person.
			
	 * @param _loginName 
				The name that is used to log into the system. It has to be unique.
			
	 * @param _loginPassword 
				The password that is used together with the login name in order to log in into the system.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Person createPerson(String _firstName, String _lastName, String _loginName, String _loginPassword) throws de.fu.weave.orm.DatabaseException {
		Person entity = newPerson(_firstName, _lastName, _loginName, _loginPassword);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Person and initializes all attributes.
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _firstName 
				The first name of a Person.
			
	 * @param _additionalNames 
				Additional names of a Person.
			
	 * @param _lastName 
				The last name of a Person.
			
	 * @param _emailAddress 
				The email address of a Person. It has to be unique.
			
	 * @param _loginName 
				The name that is used to log into the system. It has to be unique.
			
	 * @param _loginPassword 
				The password that is used together with the login name in order to log in into the system.
			
	 * @param _isSuperuser 
				Attribute that shows whether the Person is a superuser or not.
			
	 * @param _deleted 
				Attribute that shows on which date a Person was deleted from the system.
			
	 * @param _deletedBy 
				Attribute that shows by which Person the User was deleted.
			
	 * @param _workingHours 
				This attribute shows the number of working hours of a Person.
			
	 * @param _studentId 
				The number identifies the Person (student) uniquely.
			
	 * @param _staffId 
				If part of the staff the staff ID will identify the Persion uniquely.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Person fullyCreatePerson(java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, Integer _workingHours, String _studentId, String _staffId) throws de.fu.weave.orm.DatabaseException {
		Person entity = fullyNewPerson(_ctime, _mtime, _createdBy, _firstName, _additionalNames, _lastName, _emailAddress, _loginName, _loginPassword, _isSuperuser, _deleted, _deletedBy, _workingHours, _studentId, _staffId);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Person#create()}) a Person and initializes all attributes.
	 *
	 * @param _ctime 
	 *
	 * @param _mtime 
	 *
	 * @param _createdBy 
	 *
	 * @param _firstName 
				The first name of a Person.
			
	 *
	 * @param _additionalNames 
				Additional names of a Person.
			
	 *
	 * @param _lastName 
				The last name of a Person.
			
	 *
	 * @param _emailAddress 
				The email address of a Person. It has to be unique.
			
	 *
	 * @param _loginName 
				The name that is used to log into the system. It has to be unique.
			
	 *
	 * @param _loginPassword 
				The password that is used together with the login name in order to log in into the system.
			
	 *
	 * @param _isSuperuser 
				Attribute that shows whether the Person is a superuser or not.
			
	 *
	 * @param _deleted 
				Attribute that shows on which date a Person was deleted from the system.
			
	 *
	 * @param _deletedBy 
				Attribute that shows by which Person the User was deleted.
			
	 *
	 * @param _workingHours 
				This attribute shows the number of working hours of a Person.
			
	 *
	 * @param _studentId 
				The number identifies the Person (student) uniquely.
			
	 *
	 * @param _staffId 
				If part of the staff the staff ID will identify the Persion uniquely.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Person fullyNewPerson(java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, Integer _workingHours, String _studentId, String _staffId) throws de.fu.weave.orm.DatabaseException {
		Person entity = new Person(this, true, _ctime, _mtime, _createdBy, _firstName, _additionalNames, _lastName, _emailAddress, _loginName, _loginPassword, _isSuperuser, _deleted, _deletedBy, _workingHours, _studentId, _staffId);
		return entity;
	}
	
	/**
	 * Gets Person entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Person> getPerson(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Person\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Person> resultCollection = new java.util.ArrayList<Person>();
			while (result.next()) {
				resultCollection.add(new Person(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Person entities matching the given filters. Essentially {@link #getPerson(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Person> getPerson(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getPerson(0, limit, filters);
	}
	
	/**
     * Gets Person entities matching the given filters. Essentially {@link #getPerson(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Person> getPerson(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getPerson(0, -1, filters);
	}
	
	/**
	 * Gets a single Person entity by its ID.
	 *
	 * @param id The id of the Person retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Person getPerson(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Person(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deletePerson(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Person\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deletePerson(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Person\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Returns a list of Person as Users. Basically this is the same as {@link #getPerson(de.fu.weave.orm.Filter...)}.
	 * <p>
	 * This method is required by {@see de.fu.weave} for basic actions like login and logout. It differs from <code>getPerson(Filter...)</code> in that it does not return -objects but User-objects.
	 *
	 * @since Iteration2
	 */
	@Override
	public java.util.Collection<? extends de.fu.weave.User> fetchUsers(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getPerson(filters);
	}
	
	/**
	 * Returns a User identified by the given id. Basically this is the same as {@link #getPerson(int)}. It differs from getPerson(int) in that it does not return a -objects but a User-object.
	 * @since Iteration2
	 */
	@Override
	public de.fu.weave.User fetchUser(int id) throws de.fu.weave.orm.DatabaseException {
		return getPerson(id);
	}	
	/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportPrivilege(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Privilege\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Privilege#create()}) a Privilege with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Privilege newPrivilege(String _name) {
		return new Privilege(this, _name);
	}
	
	/**
	 * Creates a Privilege with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Privilege createPrivilege(String _name) throws de.fu.weave.orm.DatabaseException {
		Privilege entity = newPrivilege(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Privilege and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Privilege fullyCreatePrivilege(String _name) throws de.fu.weave.orm.DatabaseException {
		Privilege entity = fullyNewPrivilege(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Privilege#create()}) a Privilege and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Privilege fullyNewPrivilege(String _name) throws de.fu.weave.orm.DatabaseException {
		Privilege entity = new Privilege(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Privilege entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Privilege> getPrivilege(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Privilege\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Privilege> resultCollection = new java.util.ArrayList<Privilege>();
			while (result.next()) {
				resultCollection.add(new Privilege(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Privilege entities matching the given filters. Essentially {@link #getPrivilege(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Privilege> getPrivilege(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getPrivilege(0, limit, filters);
	}
	
	/**
     * Gets Privilege entities matching the given filters. Essentially {@link #getPrivilege(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Privilege> getPrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getPrivilege(0, -1, filters);
	}
	
	/**
	 * Gets a single Privilege entity by its ID.
	 *
	 * @param id The id of the Privilege retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Privilege getPrivilege(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Privilege\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Privilege(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deletePrivilege(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Privilege\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deletePrivilege(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Privilege\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportProgram(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Program\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Program#create()}) a Program with only required attributes.
	 * @param _academicTerm 
				The academic turn this Program is running it.
			
	 * @param _department 
				The department to which this Program is belonging.
			
	 * @param _programManager 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Program newProgram(AcademicTerm _academicTerm, Department _department, Person _programManager) {
		return new Program(this, _academicTerm, _department, _programManager);
	}
	
	/**
	 * Creates a Program with only required attributes.
	 * @param _academicTerm 
				The academic turn this Program is running it.
			
	 * @param _department 
				The department to which this Program is belonging.
			
	 * @param _programManager 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Program createProgram(AcademicTerm _academicTerm, Department _department, Person _programManager) throws de.fu.weave.orm.DatabaseException {
		Program entity = newProgram(_academicTerm, _department, _programManager);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Program and initializes all attributes.
	 * @param _academicTerm 
				The academic turn this Program is running it.
			
	 * @param _department 
				The department to which this Program is belonging.
			
	 * @param _freezed 
				Whether or not the Program is freezed i.e. no Courses Element Instances will change their place in the Shedule any more.
			
	 * @param _published 
				Whether or not the Program is already released or not.
			
	 * @param _programManager 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Program fullyCreateProgram(AcademicTerm _academicTerm, Department _department, boolean _freezed, boolean _published, Person _programManager) throws de.fu.weave.orm.DatabaseException {
		Program entity = fullyNewProgram(_academicTerm, _department, _freezed, _published, _programManager);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Program#create()}) a Program and initializes all attributes.
	 *
	 * @param _academicTerm 
				The academic turn this Program is running it.
			
	 *
	 * @param _department 
				The department to which this Program is belonging.
			
	 *
	 * @param _freezed 
				Whether or not the Program is freezed i.e. no Courses Element Instances will change their place in the Shedule any more.
			
	 *
	 * @param _published 
				Whether or not the Program is already released or not.
			
	 *
	 * @param _programManager 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Program fullyNewProgram(AcademicTerm _academicTerm, Department _department, boolean _freezed, boolean _published, Person _programManager) throws de.fu.weave.orm.DatabaseException {
		Program entity = new Program(this, true, _academicTerm, _department, _freezed, _published, _programManager);
		return entity;
	}
	
	/**
	 * Gets Program entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Program> getProgram(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Program\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Program> resultCollection = new java.util.ArrayList<Program>();
			while (result.next()) {
				resultCollection.add(new Program(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Program entities matching the given filters. Essentially {@link #getProgram(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Program> getProgram(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getProgram(0, limit, filters);
	}
	
	/**
     * Gets Program entities matching the given filters. Essentially {@link #getProgram(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Program> getProgram(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getProgram(0, -1, filters);
	}
	
	/**
	 * Gets a single Program entity by its ID.
	 *
	 * @param id The id of the Program retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Program getProgram(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Program\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Program(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteProgram(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Program\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteProgram(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Program\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportProposedScheduling(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"ProposedScheduling\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link ProposedScheduling#create()}) a ProposedScheduling with only required attributes.
	 * @param _priority 
	 * @param _elementInstance 
	 * @param _timeslot 
	 * @param _room 
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public ProposedScheduling newProposedScheduling(int _priority, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) {
		return new ProposedScheduling(this, _priority, _elementInstance, _timeslot, _room);
	}
	
	/**
	 * Creates a ProposedScheduling with only required attributes.
	 * @param _priority 
	 * @param _elementInstance 
	 * @param _timeslot 
	 * @param _room 
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public ProposedScheduling createProposedScheduling(int _priority, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) throws de.fu.weave.orm.DatabaseException {
		ProposedScheduling entity = newProposedScheduling(_priority, _elementInstance, _timeslot, _room);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a ProposedScheduling and initializes all attributes.
	 * @param _priority 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _elementInstance 
	 * @param _timeslot 
	 * @param _room 
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public ProposedScheduling fullyCreateProposedScheduling(int _priority, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) throws de.fu.weave.orm.DatabaseException {
		ProposedScheduling entity = fullyNewProposedScheduling(_priority, _ctime, _mtime, _createdBy, _elementInstance, _timeslot, _room);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link ProposedScheduling#create()}) a ProposedScheduling and initializes all attributes.
	 *
	 * @param _priority 
	 *
	 * @param _ctime 
	 *
	 * @param _mtime 
	 *
	 * @param _createdBy 
	 *
	 * @param _elementInstance 
	 *
	 * @param _timeslot 
	 *
	 * @param _room 
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public ProposedScheduling fullyNewProposedScheduling(int _priority, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) throws de.fu.weave.orm.DatabaseException {
		ProposedScheduling entity = new ProposedScheduling(this, true, _priority, _ctime, _mtime, _createdBy, _elementInstance, _timeslot, _room);
		return entity;
	}
	
	/**
	 * Gets ProposedScheduling entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<ProposedScheduling> getProposedScheduling(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"ProposedScheduling\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<ProposedScheduling> resultCollection = new java.util.ArrayList<ProposedScheduling>();
			while (result.next()) {
				resultCollection.add(new ProposedScheduling(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets ProposedScheduling entities matching the given filters. Essentially {@link #getProposedScheduling(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<ProposedScheduling> getProposedScheduling(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getProposedScheduling(0, limit, filters);
	}
	
	/**
     * Gets ProposedScheduling entities matching the given filters. Essentially {@link #getProposedScheduling(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<ProposedScheduling> getProposedScheduling(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getProposedScheduling(0, -1, filters);
	}
	
	/**
	 * Gets a single ProposedScheduling entity by its ID.
	 *
	 * @param id The id of the ProposedScheduling retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public ProposedScheduling getProposedScheduling(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"ProposedScheduling\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new ProposedScheduling(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteProposedScheduling(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"ProposedScheduling\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteProposedScheduling(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"ProposedScheduling\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportRole(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Role\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Role#create()}) a Role with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Role newRole(String _name) {
		return new Role(this, _name);
	}
	
	/**
	 * Creates a Role with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Role createRole(String _name) throws de.fu.weave.orm.DatabaseException {
		Role entity = newRole(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Role and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Role fullyCreateRole(String _name) throws de.fu.weave.orm.DatabaseException {
		Role entity = fullyNewRole(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Role#create()}) a Role and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Role fullyNewRole(String _name) throws de.fu.weave.orm.DatabaseException {
		Role entity = new Role(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Role entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Role> getRole(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Role\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Role> resultCollection = new java.util.ArrayList<Role>();
			while (result.next()) {
				resultCollection.add(new Role(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Role entities matching the given filters. Essentially {@link #getRole(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Role> getRole(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getRole(0, limit, filters);
	}
	
	/**
     * Gets Role entities matching the given filters. Essentially {@link #getRole(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Role> getRole(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getRole(0, -1, filters);
	}
	
	/**
	 * Gets a single Role entity by its ID.
	 *
	 * @param id The id of the Role retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Role getRole(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Role\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Role(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteRole(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Role\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteRole(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Role\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportRoom(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Room\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Room#create()}) a Room with only required attributes.
	 * @param _number 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 * @param _building 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Room newRoom(String _number, Building _building) {
		return new Room(this, _number, _building);
	}
	
	/**
	 * Creates a Room with only required attributes.
	 * @param _number 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 * @param _building 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Room createRoom(String _number, Building _building) throws de.fu.weave.orm.DatabaseException {
		Room entity = newRoom(_number, _building);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Room and initializes all attributes.
	 * @param _name 
				The Roomname is unique per Building and identifies the Room in this Building.
			
	 * @param _number 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 * @param _building 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Room fullyCreateRoom(String _name, String _number, Building _building) throws de.fu.weave.orm.DatabaseException {
		Room entity = fullyNewRoom(_name, _number, _building);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Room#create()}) a Room and initializes all attributes.
	 *
	 * @param _name 
				The Roomname is unique per Building and identifies the Room in this Building.
			
	 *
	 * @param _number 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 *
	 * @param _building 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Room fullyNewRoom(String _name, String _number, Building _building) throws de.fu.weave.orm.DatabaseException {
		Room entity = new Room(this, true, _name, _number, _building);
		return entity;
	}
	
	/**
	 * Gets Room entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Room> getRoom(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Room\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Room> resultCollection = new java.util.ArrayList<Room>();
			while (result.next()) {
				resultCollection.add(new Room(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Room entities matching the given filters. Essentially {@link #getRoom(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Room> getRoom(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getRoom(0, limit, filters);
	}
	
	/**
     * Gets Room entities matching the given filters. Essentially {@link #getRoom(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Room> getRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getRoom(0, -1, filters);
	}
	
	/**
	 * Gets a single Room entity by its ID.
	 *
	 * @param id The id of the Room retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Room getRoom(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Room(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteRoom(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Room\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteRoom(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Room\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportTimeslot(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Timeslot\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Timeslot#create()}) a Timeslot with only required attributes.
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @param _startingTime 
				Time on which the COurseElementInstance will begin.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Timeslot newTimeslot(Day _day, java.sql.Time _startingTime) {
		return new Timeslot(this, _day, _startingTime);
	}
	
	/**
	 * Creates a Timeslot with only required attributes.
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @param _startingTime 
				Time on which the COurseElementInstance will begin.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Timeslot createTimeslot(Day _day, java.sql.Time _startingTime) throws de.fu.weave.orm.DatabaseException {
		Timeslot entity = newTimeslot(_day, _startingTime);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Timeslot and initializes all attributes.
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @param _startingTime 
				Time on which the COurseElementInstance will begin.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Timeslot fullyCreateTimeslot(Day _day, java.sql.Time _startingTime) throws de.fu.weave.orm.DatabaseException {
		Timeslot entity = fullyNewTimeslot(_day, _startingTime);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Timeslot#create()}) a Timeslot and initializes all attributes.
	 *
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 *
	 * @param _startingTime 
				Time on which the COurseElementInstance will begin.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Timeslot fullyNewTimeslot(Day _day, java.sql.Time _startingTime) throws de.fu.weave.orm.DatabaseException {
		Timeslot entity = new Timeslot(this, true, _day, _startingTime);
		return entity;
	}
	
	/**
	 * Gets Timeslot entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Timeslot> getTimeslot(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Timeslot> resultCollection = new java.util.ArrayList<Timeslot>();
			while (result.next()) {
				resultCollection.add(new Timeslot(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Timeslot entities matching the given filters. Essentially {@link #getTimeslot(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Timeslot> getTimeslot(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getTimeslot(0, limit, filters);
	}
	
	/**
     * Gets Timeslot entities matching the given filters. Essentially {@link #getTimeslot(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Timeslot> getTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getTimeslot(0, -1, filters);
	}
	
	/**
	 * Gets a single Timeslot entity by its ID.
	 *
	 * @param id The id of the Timeslot retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Timeslot getTimeslot(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Timeslot(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteTimeslot(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Timeslot\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteTimeslot(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Timeslot\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * @since Iteration3
	 */
	public java.sql.SQLXML exportYear(boolean with_schema) throws de.fu.weave.orm.DatabaseException {
		return export("\"scetris\".\"Year\"", with_schema);
	}
	
	/**
	 * Instantiates (but does not {@link Year#create()}) a Year with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public Year newYear(String _name) {
		return new Year(this, _name);
	}
	
	/**
	 * Creates a Year with only required attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public Year createYear(String _name) throws de.fu.weave.orm.DatabaseException {
		Year entity = newYear(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Creates a Year and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public Year fullyCreateYear(String _name) throws de.fu.weave.orm.DatabaseException {
		Year entity = fullyNewYear(_name);
		entity.create();
		return entity;
	}
	
	/**
	 * Instantiates (but does not {@link Year#create()}) a Year and initializes all attributes.
	 *
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public Year fullyNewYear(String _name) throws de.fu.weave.orm.DatabaseException {
		Year entity = new Year(this, true, _name);
		return entity;
	}
	
	/**
	 * Gets Year entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 * @since Iteration2
	 */
	public java.util.Collection<Year> getYear(int offset, int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit > -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset > 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = filters.length > 0 ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "SELECT * FROM " + "\"scetris\".\"Year\"" + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			java.sql.ResultSet result = assignFilters(stmt, filters).executeQuery();
			java.util.Collection<Year> resultCollection = new java.util.ArrayList<Year>();
			while (result.next()) {
				resultCollection.add(new Year(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new de.fu.weave.orm.DatabaseException("Database is not available (is it set up?)", e);
		}
	}
	
	/**
     * Gets Year entities matching the given filters. Essentially {@link #getYear(int, int, de.fu.weave.orm.Filter...)} with an offset of zero.
	 * 
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Year> getYear(int limit, de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getYear(0, limit, filters);
	}
	
	/**
     * Gets Year entities matching the given filters. Essentially {@link #getYear(int, int, de.fu.weave.orm.Filter...)} with an offset of zero and no limit.
     *
     * @param filters Any number (including zero) of filters.
	 * @since Iteration2
	 */
	public java.util.Collection<Year> getYear(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		return getYear(0, -1, filters);
	}
	
	/**
	 * Gets a single Year entity by its ID.
	 *
	 * @param id The id of the Year retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 * @since Iteration2
	 */
	public Year getYear(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Year\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			java.sql.ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			return new Year(this, result);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entities matching the given Filters
	 *
	 * @since Iteration3
	 */
	public void deleteYear(de.fu.weave.orm.Filter... filters) throws de.fu.weave.orm.DatabaseException {
		String whereString = (filters.length > 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + "\"scetris\".\"Year\"" + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Deletes the entity that is identified by the given id.
	 *
	 * @since Iteration3
	 */
	public void deleteYear(int id) throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Year\"" + " WHERE "
			+ "id" + " = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 *
	 * @since Iteration3
	 */
	public boolean install() throws de.fu.weave.orm.DatabaseException {
		try {
			beginTransaction();
			java.sql.Statement stmt = connectionManager.getConnection().createStatement();
			stmt.executeUpdate("SET client_min_messages TO warning;");
			stmt.executeUpdate("DROP SCHEMA IF EXISTS \"scetris\" CASCADE;");
			stmt.executeUpdate("CREATE SCHEMA \"scetris\"; ");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"AcademicTerm\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"start\" DATE NOT NULL, 	\"end\" DATE NOT NULL, 	\"start_lessons\" DATE NOT NULL, 	\"end_lessons\" DATE NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Attribute\" ( 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"type\" VARCHAR NOT NULL DEFAULT 'string', 	\"unique_value\" BOOLEAN NOT NULL DEFAULT false, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Building\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"name\" VARCHAR, 	\"address\" VARCHAR NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Configuration\" ( 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"key\" VARCHAR NOT NULL, 	\"value\" VARCHAR, 	PRIMARY KEY (\"key\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Course\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"CourseAttribute\" ( 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"type\" VARCHAR NOT NULL DEFAULT 'string', 	\"unique_value\" BOOLEAN NOT NULL DEFAULT false, 	\"required\" BOOLEAN NOT NULL DEFAULT false, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"CourseElement\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"name\" VARCHAR, 	\"part_of\" INTEGER NOT NULL, 	\"duration\" INTEGER NOT NULL, 	\"type\" INTEGER, 	\"required\" BOOLEAN NOT NULL DEFAULT true, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"CourseElementInstance\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"course_instance\" INTEGER NOT NULL, 	\"course_element\" INTEGER NOT NULL, 	\"starting_timeslot\" INTEGER, 	\"duration\" INTEGER NOT NULL, 	\"schedulable_lesson\" BOOLEAN NOT NULL DEFAULT true, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"CourseElementType\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"CourseInstance\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"program\" INTEGER NOT NULL, 	\"instance_of\" INTEGER NOT NULL, 	\"start\" DATE NOT NULL, 	\"end\" DATE NOT NULL, 	\"main_lecturer\" INTEGER NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Day\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Department\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"superordinate_department\" INTEGER, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Feature\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Group\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Person\" ( 	\"id\" SERIAL NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	\"first_name\" VARCHAR NOT NULL, 	\"additional_names\" VARCHAR, 	\"last_name\" VARCHAR NOT NULL, 	\"email_address\" VARCHAR, 	\"login_name\" VARCHAR NOT NULL, 	\"login_password\" VARCHAR(32) NOT NULL, 	\"is_superuser\" BOOLEAN NOT NULL DEFAULT false, 	\"deleted\" TIMESTAMP, 	\"deleted_by\" INTEGER, 	\"working_hours\" INTEGER, 	\"student_id\" VARCHAR, 	\"staff_id\" VARCHAR, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Privilege\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Program\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"academic_term\" INTEGER NOT NULL, 	\"department\" INTEGER NOT NULL, 	\"freezed\" BOOLEAN NOT NULL DEFAULT false, 	\"published\" BOOLEAN NOT NULL DEFAULT false, 	\"program_manager\" INTEGER NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"ProposedScheduling\" ( 	\"id\" SERIAL NOT NULL, 	\"priority\" INTEGER NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	\"element_instance\" INTEGER NOT NULL, 	\"timeslot\" INTEGER NOT NULL, 	\"room\" INTEGER NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Role\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Room\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"name\" VARCHAR, 	\"number\" VARCHAR NOT NULL, 	\"building\" INTEGER NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Timeslot\" ( 	\"id\" SERIAL NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"day\" INTEGER NOT NULL, 	\"startingTime\" TIME NOT NULL, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"Year\" ( 	\"id\" SERIAL NOT NULL, 	\"name\" VARCHAR NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"id\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"courseHasCourseAttribute\" ( 	\"course\" INTEGER NOT NULL, 	\"attribute\" INTEGER NOT NULL, 	\"time\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"editor\" INTEGER NOT NULL, 	\"value\" VARCHAR, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"course\", \"attribute\", \"time\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"courseRecommendedForYear\" ( 	\"course\" INTEGER NOT NULL, 	\"year\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"course\", \"year\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"courseRequiresCourse\" ( 	\"course\" INTEGER NOT NULL, 	\"dependency\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"course\", \"dependency\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"elementInstancePrefersRoom\" ( 	\"element_instance\" INTEGER NOT NULL, 	\"room\" INTEGER NOT NULL, 	\"priority\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"element_instance\", \"room\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"elementInstancePrefersTimeslot\" ( 	\"room\" INTEGER NOT NULL, 	\"timeslot\" INTEGER NOT NULL, 	\"priority\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"room\", \"timeslot\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"elementInstanceRequiresFeature\" ( 	\"element_instance\" INTEGER NOT NULL, 	\"feature\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"priority\" INTEGER NOT NULL, 	\"quantity_min\" INTEGER, 	\"quantity_better\" INTEGER, 	PRIMARY KEY (\"element_instance\", \"feature\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"elementInstanceTakesPlaceInRoom\" ( 	\"element_instance\" INTEGER NOT NULL, 	\"room\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"timeslot\" INTEGER NOT NULL, 	PRIMARY KEY (\"element_instance\", \"room\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"elementRequiresFeature\" ( 	\"course_element\" INTEGER NOT NULL, 	\"feature\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"priority\" INTEGER NOT NULL, 	\"quantity_min\" INTEGER, 	\"quantity_better\" INTEGER, 	PRIMARY KEY (\"course_element\", \"feature\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personBelongsToGroup\" ( 	\"user\" INTEGER NOT NULL, 	\"group\" INTEGER NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	PRIMARY KEY (\"user\", \"group\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personEnrolledInCourseInstance\" ( 	\"user\" INTEGER NOT NULL, 	\"course_instance\" INTEGER NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	PRIMARY KEY (\"user\", \"course_instance\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personGivesCourse\" ( 	\"person\" INTEGER NOT NULL, 	\"course\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"priority\" INTEGER NOT NULL, 	PRIMARY KEY (\"person\", \"course\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personHasAttribute\" ( 	\"user\" INTEGER NOT NULL, 	\"attribute\" INTEGER NOT NULL, 	\"time\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"editor\" INTEGER NOT NULL, 	\"value\" VARCHAR, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"user\", \"attribute\", \"time\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personHasPrivilege\" ( 	\"user\" INTEGER NOT NULL, 	\"privilege\" INTEGER NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	\"target\" VARCHAR, 	\"because_of_role\" BOOLEAN, 	PRIMARY KEY (\"user\", \"privilege\", \"target\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personHasRole\" ( 	\"user\" INTEGER NOT NULL, 	\"role\" INTEGER NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	PRIMARY KEY (\"user\", \"role\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personPrefersTimeslot\" ( 	\"user\" INTEGER NOT NULL, 	\"timeslot\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"priority\" INTEGER NOT NULL, 	PRIMARY KEY (\"user\", \"timeslot\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personSuccessfullyPassedCourse\" ( 	\"user\" INTEGER NOT NULL, 	\"course\" INTEGER NOT NULL, 	\"ctime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"mtime\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"created_by\" INTEGER, 	\"modified_by\" INTEGER, 	\"grade\" VARCHAR, 	\"notes\" TEXT, 	PRIMARY KEY (\"user\", \"course\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"personTakesPartInElementInstance\" ( 	\"user\" INTEGER NOT NULL, 	\"element_instance\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"timeslot\" INTEGER NOT NULL, 	PRIMARY KEY (\"user\", \"element_instance\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"roleImpliesAttribute\" ( 	\"role\" INTEGER NOT NULL, 	\"attribute\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"default\" VARCHAR, 	\"required\" BOOLEAN NOT NULL DEFAULT false, 	PRIMARY KEY (\"role\", \"attribute\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"roleImpliesPrivilege\" ( 	\"role\" INTEGER NOT NULL, 	\"privilege\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"target\" VARCHAR, 	PRIMARY KEY (\"role\", \"privilege\", \"target\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"roomPrefersTimeslot\" ( 	\"room\" INTEGER NOT NULL, 	\"timeslot\" INTEGER NOT NULL, 	\"priority\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	PRIMARY KEY (\"room\", \"timeslot\") );");
			stmt.executeUpdate("CREATE TABLE \"scetris\".\"roomProvidesFeature\" ( 	\"room\" INTEGER NOT NULL, 	\"feature\" INTEGER NOT NULL, 	\"timekey\" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 	\"quantity\" INTEGER NOT NULL, 	PRIMARY KEY (\"room\", \"feature\") );");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35820454 ON \"scetris\".\"AcademicTerm\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35820610 ON \"scetris\".\"Attribute\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35820741 ON \"scetris\".\"Building\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35820748 ON \"scetris\".\"Building\" (\"address\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35820910 ON \"scetris\".\"Course\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35821096 ON \"scetris\".\"CourseAttribute\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35821656 ON \"scetris\".\"CourseElementType\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35821970 ON \"scetris\".\"Day\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822112 ON \"scetris\".\"Department\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822229 ON \"scetris\".\"Feature\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822343 ON \"scetris\".\"Group\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822876 ON \"scetris\".\"Person\" (\"email_address\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822884 ON \"scetris\".\"Person\" (\"login_name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822891 ON \"scetris\".\"Person\" (\"student_id\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35822898 ON \"scetris\".\"Person\" (\"staff_id\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35823013 ON \"scetris\".\"Privilege\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35823505 ON \"scetris\".\"ProposedScheduling\" (\"element_instance\", \"timeslot\", \"room\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35823627 ON \"scetris\".\"Role\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35823791 ON \"scetris\".\"Room\" (\"name\", \"building\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35824049 ON \"scetris\".\"Year\" (\"name\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35824758 ON \"scetris\".\"elementInstanceTakesPlaceInRoom\" (\"room\", \"timeslot\"); ");
			stmt.executeUpdate("CREATE UNIQUE INDEX uniq_id35826386 ON \"scetris\".\"personTakesPartInElementInstance\" (\"user\", \"timeslot\"); ");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseElement\" ADD FOREIGN KEY (\"part_of\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseElement\" ADD FOREIGN KEY (\"type\") REFERENCES \"scetris\".\"CourseElementType\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseElementInstance\" ADD FOREIGN KEY (\"course_instance\") REFERENCES \"scetris\".\"CourseInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseElementInstance\" ADD FOREIGN KEY (\"course_element\") REFERENCES \"scetris\".\"CourseElement\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseElementInstance\" ADD FOREIGN KEY (\"starting_timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseInstance\" ADD FOREIGN KEY (\"program\") REFERENCES \"scetris\".\"Program\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseInstance\" ADD FOREIGN KEY (\"instance_of\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"CourseInstance\" ADD FOREIGN KEY (\"main_lecturer\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Department\" ADD FOREIGN KEY (\"superordinate_department\") REFERENCES \"scetris\".\"Department\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Person\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Person\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Person\" ADD FOREIGN KEY (\"deleted_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Program\" ADD FOREIGN KEY (\"academic_term\") REFERENCES \"scetris\".\"AcademicTerm\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Program\" ADD FOREIGN KEY (\"department\") REFERENCES \"scetris\".\"Department\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Program\" ADD FOREIGN KEY (\"program_manager\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"ProposedScheduling\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"ProposedScheduling\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"ProposedScheduling\" ADD FOREIGN KEY (\"element_instance\") REFERENCES \"scetris\".\"CourseElementInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"ProposedScheduling\" ADD FOREIGN KEY (\"timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"ProposedScheduling\" ADD FOREIGN KEY (\"room\") REFERENCES \"scetris\".\"Room\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Room\" ADD FOREIGN KEY (\"building\") REFERENCES \"scetris\".\"Building\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"Timeslot\" ADD FOREIGN KEY (\"day\") REFERENCES \"scetris\".\"Day\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseHasCourseAttribute\" ADD FOREIGN KEY (\"course\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseHasCourseAttribute\" ADD FOREIGN KEY (\"attribute\") REFERENCES \"scetris\".\"CourseAttribute\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseHasCourseAttribute\" ADD FOREIGN KEY (\"editor\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseRecommendedForYear\" ADD FOREIGN KEY (\"course\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseRecommendedForYear\" ADD FOREIGN KEY (\"year\") REFERENCES \"scetris\".\"Year\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseRequiresCourse\" ADD FOREIGN KEY (\"course\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"courseRequiresCourse\" ADD FOREIGN KEY (\"dependency\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstancePrefersRoom\" ADD FOREIGN KEY (\"element_instance\") REFERENCES \"scetris\".\"CourseElementInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstancePrefersRoom\" ADD FOREIGN KEY (\"room\") REFERENCES \"scetris\".\"Room\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstancePrefersTimeslot\" ADD FOREIGN KEY (\"room\") REFERENCES \"scetris\".\"CourseElementInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstancePrefersTimeslot\" ADD FOREIGN KEY (\"timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstanceRequiresFeature\" ADD FOREIGN KEY (\"element_instance\") REFERENCES \"scetris\".\"CourseElementInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstanceRequiresFeature\" ADD FOREIGN KEY (\"feature\") REFERENCES \"scetris\".\"Feature\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstanceTakesPlaceInRoom\" ADD FOREIGN KEY (\"element_instance\") REFERENCES \"scetris\".\"CourseElementInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstanceTakesPlaceInRoom\" ADD FOREIGN KEY (\"room\") REFERENCES \"scetris\".\"Room\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementInstanceTakesPlaceInRoom\" ADD FOREIGN KEY (\"timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementRequiresFeature\" ADD FOREIGN KEY (\"course_element\") REFERENCES \"scetris\".\"CourseElement\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"elementRequiresFeature\" ADD FOREIGN KEY (\"feature\") REFERENCES \"scetris\".\"Feature\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personBelongsToGroup\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personBelongsToGroup\" ADD FOREIGN KEY (\"group\") REFERENCES \"scetris\".\"Group\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personBelongsToGroup\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personBelongsToGroup\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personEnrolledInCourseInstance\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personEnrolledInCourseInstance\" ADD FOREIGN KEY (\"course_instance\") REFERENCES \"scetris\".\"CourseInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personEnrolledInCourseInstance\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personEnrolledInCourseInstance\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personGivesCourse\" ADD FOREIGN KEY (\"person\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personGivesCourse\" ADD FOREIGN KEY (\"course\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasAttribute\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasAttribute\" ADD FOREIGN KEY (\"attribute\") REFERENCES \"scetris\".\"Attribute\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasAttribute\" ADD FOREIGN KEY (\"editor\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasPrivilege\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasPrivilege\" ADD FOREIGN KEY (\"privilege\") REFERENCES \"scetris\".\"Privilege\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasPrivilege\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasPrivilege\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasRole\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasRole\" ADD FOREIGN KEY (\"role\") REFERENCES \"scetris\".\"Role\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasRole\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personHasRole\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personPrefersTimeslot\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personPrefersTimeslot\" ADD FOREIGN KEY (\"timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personSuccessfullyPassedCourse\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personSuccessfullyPassedCourse\" ADD FOREIGN KEY (\"course\") REFERENCES \"scetris\".\"Course\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personSuccessfullyPassedCourse\" ADD FOREIGN KEY (\"created_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personSuccessfullyPassedCourse\" ADD FOREIGN KEY (\"modified_by\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personTakesPartInElementInstance\" ADD FOREIGN KEY (\"user\") REFERENCES \"scetris\".\"Person\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personTakesPartInElementInstance\" ADD FOREIGN KEY (\"element_instance\") REFERENCES \"scetris\".\"CourseElementInstance\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"personTakesPartInElementInstance\" ADD FOREIGN KEY (\"timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roleImpliesAttribute\" ADD FOREIGN KEY (\"role\") REFERENCES \"scetris\".\"Role\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roleImpliesAttribute\" ADD FOREIGN KEY (\"attribute\") REFERENCES \"scetris\".\"Attribute\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roleImpliesPrivilege\" ADD FOREIGN KEY (\"role\") REFERENCES \"scetris\".\"Role\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roleImpliesPrivilege\" ADD FOREIGN KEY (\"privilege\") REFERENCES \"scetris\".\"Privilege\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roomPrefersTimeslot\" ADD FOREIGN KEY (\"room\") REFERENCES \"scetris\".\"Room\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roomPrefersTimeslot\" ADD FOREIGN KEY (\"timeslot\") REFERENCES \"scetris\".\"Timeslot\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roomProvidesFeature\" ADD FOREIGN KEY (\"room\") REFERENCES \"scetris\".\"Room\" ON DELETE CASCADE;");
			stmt.executeUpdate("ALTER TABLE \"scetris\".\"roomProvidesFeature\" ADD FOREIGN KEY (\"feature\") REFERENCES \"scetris\".\"Feature\" ON DELETE CASCADE;");
			stmt.executeUpdate("DROP LANGUAGE IF EXISTS plpgsql CASCADE;");
			stmt.executeUpdate("CREATE LANGUAGE plpgsql;");
			stmt.executeUpdate("CREATE OR REPLACE FUNCTION ping () RETURNS BOOLEAN AS $BAZINGA$ BEGIN 	RETURN TRUE; END $BAZINGA$ LANGUAGE 'plpgsql';");
			stmt.executeUpdate(" -- Schreibt eine Person in eine CourseInstance? ein, das heißt: Setzt personEnrolledInCourseInstance.  -- Die Mindestanforderungen an die Funktion ist, dass die CourseInstance? gebucht ist. -- Darüber hinaus werden zwei weitere Requirements unterschieden:  -- Req1: Die Funktion bucht automatisch CourseElementInstances? zu denen es keine Alternativen gibt.  -- Dies ist genau dann der Fall, wenn die Summe der Eigenschaft duration aller CourseElementInstances? die zu einem CourseElement?  -- gehören gleich der Eigenschaft duration dieses CourseElements? ist. Buchen heißt hier, dass sie personTakesPartInElementInstance setzt.  -- Req2: Die Funktion prüft, ob die benötigten Kurse erfolgreich abgeschlossen wurden – also, ob alle Courses die von dem  -- Course der zu buchenden CourseInstance? via courseRequiresCourse als Vorraussetzung definiert wurden von der buchenden Person  -- via personSuccessfullyPassedCourse abgeschlossen wurden. -- -- Min: { Person, CourseInstance?, personEnrolledInCourseInstance } -- Req1: + { personTakesPartInElementInstance, CourseElement? } -- Req2: + { personSuccessfullyPassedCourse, courseRequiresCourse, Course }   CREATE OR REPLACE FUNCTION enroll (_person VARCHAR, _coinid INT) RETURNS VOID AS $FOO$ 		DECLARE persid INT; 		DECLARE celocal RECORD; 		DECLARE req RECORD; 		BEGIN 			SELECT INTO persid id FROM \"scetris\".\"Person\" AS P WHERE P.\"login_name\"=_person; 			RAISE NOTICE 'Userid here is %', persid;  			-- req2 			SELECT INTO req CRC.* FROM \"scetris\".\"courseRequiresCourse\" AS CRC  						JOIN \"scetris\".\"CourseInstance\" AS CI ON CRC.course = CI.instance_of 						WHERE CI.id = _coinid AND CRC.dependency NOT IN  						( 							SELECT PC.course FROM \"scetris\".\"personSuccessfullyPassedCourse\" AS PC  								WHERE PC.user=persid 						); 			IF FOUND THEN 				RAISE EXCEPTION 'Userid does not fulfill all requirements'; 			ELSE 				RAISE NOTICE 'Userid fulfills all requirements'; 			END IF;   			-- min 			INSERT INTO \"scetris\".\"personEnrolledInCourseInstance\" (\"user\",\"course_instance\") VALUES (persid, _coinid); 			 			-- req1 			FOR celocal IN  				SELECT CEIX.* FROM \"CourseElementInstance\" AS CEIX,  					(	SELECT CEI.course_element, SUM(CEI.duration), CE.duration  						FROM \"CourseElement\" AS CE  						JOIN \"CourseElementInstance\" AS CEI ON CE.id = CEI.course_element  						WHERE CEI.course_instance = _coinid 						GROUP BY CEI.course_element, CE.duration  						HAVING SUM(CEI.duration) = CE.duration) AS TMP  					WHERE TMP.course_element=CEIX.course_element  			LOOP 				INSERT INTO \"scetris\".\"personTakesPartInElementInstance\" (\"user\",\"element_instance\",\"timeslot\") VALUES (persid, celocal.id, celocal.starting_timeslot); 			END LOOP; 			  		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION may (_person VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		BEGIN  			PERFORM * FROM \"scetris\".\"personHasPrivilege\" AS pHP  										JOIN \"scetris\".\"Person\" AS P ON pHP.user=P.id  										JOIN \"scetris\".\"Privilege\" AS Pr ON pHP.\"privilege\" = Pr.\"id\"  										WHERE P.\"login_name\"=_person AND Pr.\"name\"=_privilege AND (pHP.\"target\"=_target OR pHP.\"target\"='');  			IF FOUND THEN 				RETURN true; 			END IF; 			PERFORM * FROM \"scetris\".\"Person\" AS P 										JOIN \"scetris\".\"personHasRole\" AS pHR ON P.\"id\"=pHR.\"user\" 										JOIN \"scetris\".\"Role\" AS R ON pHR.\"role\"=R.\"id\" 										JOIN \"scetris\".\"roleImpliesPrivilege\" AS rIP ON R.\"id\"=rIP.role 										JOIN \"scetris\".\"Privilege\" AS Pr ON rIP.\"privilege\" = Pr.id 										WHERE P.\"login_name\"=_person AND Pr.\"name\"=_privilege AND (rIP.\"target\"=_target OR rIP.\"target\"=''); 			IF FOUND THEN 				RETURN true; 			END IF; 			RETURN false; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION personHasPrivilege (_personid INT, _privid INT, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		BEGIN 			PERFORM * FROM \"scetris\".\"personHasPrivilege\" WHERE \"user\"=_personid AND \"privilege\"=_privid AND \"target\"=_target; 			IF FOUND THEN 				RETURN TRUE; 			ELSE 				RETURN FALSE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION personHasAnyPrivilege (_personid INT, _privid INT, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		BEGIN 			PERFORM * FROM \"scetris\".\"personHasPrivilege\" WHERE \"user\"=_personid AND \"privilege\"=_privid; 			IF FOUND THEN 				RETURN TRUE; 			ELSE 				RETURN FALSE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate("	 CREATE OR REPLACE FUNCTION allow (_person VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		DECLARE userid INT; 		DECLARE privid INT; 		BEGIN 			SELECT INTO userid id FROM \"scetris\".\"Person\" AS P WHERE P.\"login_name\"=_person; 			SELECT INTO privid id FROM \"scetris\".\"Privilege\" AS P WHERE P.\"name\"=_privilege; 			IF personHasPrivilege(userid, privid, _target) THEN 				RETURN FALSE; 			ELSE  				INSERT INTO \"scetris\".\"personHasPrivilege\" (\"user\",\"privilege\", \"target\") VALUES (userid, privid, _target); 				RETURN TRUE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION disallow (_person VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		DECLARE userid INT; 		DECLARE privid INT; 		BEGIN 			SELECT INTO userid id FROM \"scetris\".\"Person\" AS P WHERE P.\"login_name\"=_person; 			SELECT INTO privid id FROM \"scetris\".\"Privilege\" AS P WHERE P.\"name\"=_privilege;  			IF personHasAnyPrivilege(userid, privid) THEN 				IF (_target = '') THEN 					DELETE FROM \"scetris\".\"personHasPrivilege\" WHERE \"user\"=userid AND \"privilege\"=privid; 					RETURN TRUE; 				ELSIF personHasPrivilege(userid, privid, _target)	THEN 					DELETE FROM \"scetris\".\"personHasPrivilege\" WHERE \"user\"=userid AND \"privilege\"=privid AND \"target\"=_target; 					RETURN TRUE; 				END IF;				 			END IF; 			RETURN FALSE; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION roleImplPrivilege (_role VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		DECLARE roleid INT; 		DECLARE privid INT; 		BEGIN 			SELECT INTO roleid id FROM \"scetris\".\"Role\" AS R WHERE R.\"name\"=_role;  			SELECT INTO privid id FROM \"scetris\".\"Privilege\" AS P WHERE P.\"name\"=_privilege;  			RETURN roleImplPrivilege(roleid, privid, _target); 		END; $FOO$	 		LANGUAGE 'plpgsql';	 ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION roleImplPrivilege (_roleid INT, _privid INT, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		BEGIN 			PERFORM * FROM \"scetris\".\"roleImpliesPrivilege\" WHERE \"privilege\"=_privid AND \"role\"=_roleid AND (\"target\"=_target OR \"target\"=''); 			IF FOUND THEN 				RETURN TRUE; 			ELSE 				RETURN FALSE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate("	 CREATE OR REPLACE FUNCTION roleallow (_role VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		DECLARE roleid INT; 		DECLARE privid INT; 		BEGIN 			SELECT INTO roleid id FROM \"scetris\".\"Role\" AS R WHERE R.\"name\"=_role;  			SELECT INTO privid id FROM \"scetris\".\"Privilege\" AS P WHERE P.\"name\"=_privilege;  			IF roleImplPrivilege(roleid, privid, _target) THEN 				RETURN FALSE; 			ELSE 				INSERT INTO \"scetris\".\"roleImpliesPrivilege\" (\"role\",\"privilege\", \"target\") VALUES (roleid, privid, _target); 				RETURN TRUE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate("	 CREATE OR REPLACE FUNCTION roledisallow (_role VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$ 		DECLARE roleid INT; 		DECLARE privid INT; 		BEGIN 			SELECT INTO roleid id FROM \"scetris\".\"Role\" AS R WHERE R.\"name\"=_role;  			SELECT INTO privid id FROM \"scetris\".\"Privilege\" AS P WHERE P.\"name\"=_privilege;  			IF roleImplPrivilege(roleid, privid, _target) THEN 				DELETE FROM \"scetris\".\"roleImpliesPrivilege\" WHERE \"role\"=roleid AND \"privilege\"=privid AND (\"target\"=_target OR _target=''); 				RETURN TRUE; 			ELSE 				RETURN FALSE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION personHasRole (_personid INT, _roleid INT) RETURNS BOOLEAN AS $FOO$ 		BEGIN 			PERFORM * FROM \"scetris\".\"personHasRole\" WHERE \"user\"=_personid AND \"role\"=_roleid; 			IF FOUND THEN 				RETURN TRUE; 			ELSE 				RETURN FALSE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION assign (_person VARCHAR, _role VARCHAR) RETURNS BOOLEAN AS $FOO$ 		DECLARE userid INT; 		DECLARE roleid INT; 		BEGIN 			SELECT INTO userid id FROM \"scetris\".\"Person\" AS P WHERE P.\"login_name\"=_person; 			SELECT INTO roleid id FROM \"scetris\".\"Role\" AS R WHERE R.\"name\"=_role;  			IF personHasRole(userid, roleid) THEN 				RETURN FALSE; 			ELSE 				INSERT INTO \"scetris\".\"personHasRole\" (\"user\",\"role\") VALUES (userid, roleid); 				RETURN TRUE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql'; ");
			stmt.executeUpdate(" CREATE OR REPLACE FUNCTION unassign (_person VARCHAR, _role VARCHAR) RETURNS BOOLEAN AS $FOO$ 		DECLARE userid INT; 		DECLARE roleid INT; 		BEGIN 			SELECT INTO userid id FROM \"scetris\".\"Person\" AS P WHERE P.\"login_name\"=_person; 			SELECT INTO roleid id FROM \"scetris\".\"Role\" AS R WHERE R.\"name\"=_role; 			IF personHasRole(userid, roleid) THEN 				DELETE FROM \"scetris\".\"personHasRole\" WHERE \"user\"=userid AND \"role\"=roleid; 				RETURN TRUE; 			ELSE 				RETURN FALSE; 			END IF; 		END; $FOO$  		LANGUAGE 'plpgsql';					 ");
			stmt.executeUpdate(" --CREATE OR REPLACE FUNCTION allow (_person VARCHAR, _privilege VARCHAR) RETURNS VOID AS $FOO$ --		BEGIN -- --		END; $FOO$  --		LANGUAGE 'plpgsql'; ");
			
			commitTransaction();
			return true;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 *
	 * @since Iteration3
	 */
	public boolean checkInstallation() throws de.fu.weave.orm.DatabaseException {
		return false;
	}
	
	/**
	 *
	 * @since Iteration3
	 */
	public boolean dropSchema() throws de.fu.weave.orm.DatabaseException {
		return false;
	}
	
	/**
	 *
	 * @since Iteration3
	 */
	public boolean flushSchema() throws de.fu.weave.orm.DatabaseException {
		return false;
	}
}
