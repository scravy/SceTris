/* CourseRequiresCourse.java / 2010-10-22+02:00
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
 * OO-Representation of the relationship-relation CourseRequiresCourse
 * <p>
 * 
			This relation tells us that it is neccessary to take part in the first Course in order to take part in the second Course.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "courseRequiresCourse", subject = Course.class, object = Course.class)
@de.fu.weave.xml.annotation.XmlElement("courseRequiresCourse")
public class CourseRequiresCourse extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Course _course;
	boolean changed_course = false;
	boolean fetched_course = false;
	int ref_course;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Course _dependency;
	boolean changed_dependency = false;
	boolean fetched_dependency = false;
	int ref_dependency;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	
	
	Course subject;
	int subject_refid;
	Course object;
	int object_refid;
	
	
	CourseRequiresCourse(RelationManager manager, Course subject, Course object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_course = result.getInt("course");
			ref_dependency = result.getInt("dependency");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	CourseRequiresCourse(RelationManager manager, Course subject, Course object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
	}
	
	CourseRequiresCourse(RelationManager manager, boolean full, Course subject, Course object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
	}
	
	@Override
	public Course getSubject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getCourse(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Course getObject() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getCourse(object_refid);
		}
		return object;
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"courseRequiresCourse\"" + " (Course, Course, )"
			+ " VALUES ();";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, subject.id());
			stmt.setInt(2, object.id());
			int i = 3;
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"courseRequiresCourse\"" + " SET  WHERE "
			+ " AND course = ?"
			+ " AND dependency = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_course);
			stmt.setInt(++i, ref_dependency);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"courseRequiresCourse\""
			+ " WHERE "
			+ " AND course = ?"
			+ " AND dependency = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_course);
			stmt.setInt(++i, ref_dependency);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public Course getCourse() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_course) {
			_course = manager.getCourse(ref_course);
			fetched_course = true;
		}
		return _course;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedCourse() {
		return changed_course;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("course")
	public int refCourse() {
		return ref_course;
	}

	
	public Course getDependency() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_dependency) {
			_dependency = manager.getCourse(ref_dependency);
			fetched_dependency = true;
		}
		return _dependency;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedDependency() {
		return changed_dependency;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("dependency")
	public int refDependency() {
		return ref_dependency;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	public java.sql.Timestamp getTimekey() {
		return _timekey;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedTimekey() {
		return changed_timekey;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setCourse(Course value) {
		
		changed_course = true;
		ref_course = value._id;
		fetched_course = true;
		_course = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setDependency(Course value) {
		
		changed_dependency = true;
		ref_dependency = value._id;
		fetched_dependency = true;
		_dependency = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

}
