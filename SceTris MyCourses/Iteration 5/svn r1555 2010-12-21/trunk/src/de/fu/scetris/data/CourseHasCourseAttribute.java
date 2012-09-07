/* CourseHasCourseAttribute.java / 2010-12-20+01:00
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
 * OO-Representation of the relationship-relation CourseHasCourseAttribute
 * <p>
 * 
			This relation tells us that the given Course has the given Attribute.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Relationship(name = "courseHasCourseAttribute", subject = Course.class, object = CourseAttribute.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"courseHasCourseAttribute\"", requiredSqlCols = {"editor"})
@de.fu.weave.xml.annotation.XmlElement("courseHasCourseAttribute")
public class CourseHasCourseAttribute extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Course = "course";
	public static final String Attribute = "attribute";
	public static final String Time = "time";
	public static final String Editor = "editor";
	public static final String Value = "value";
	public static final String Timekey = "timekey";
	

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
	CourseAttribute _attribute;
	boolean changed_attribute = false;
	boolean fetched_attribute = false;
	int ref_attribute;
	/**
	 * date of last edit
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _time;
	boolean changed_time = false;
	boolean fetched_time = false;
	
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _editor;
	boolean changed_editor = false;
	boolean fetched_editor = false;
	int ref_editor;
	/**
	 * value of the attribute
	 * <p>
	 * 
	 * @since Iteration2
	 */
	String _value = null;
	boolean changed_value = false;
	boolean fetched_value = false;
	
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
	CourseAttribute object;
	int object_refid;
	
	
	CourseHasCourseAttribute(RelationManager manager, Course subject, CourseAttribute object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_course = result.getInt("course");
			ref_attribute = result.getInt("attribute");
			_time = result.getTimestamp("time");
			fetched_time = true;
			ref_editor = result.getInt("editor");
			_value = result.getString("value");
			if (result.wasNull()) {
				_value = null;
			}
			fetched_value = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;	
			this.subject_refid = ref_course;
			this.object_refid = ref_attribute;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	CourseHasCourseAttribute(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_course = result.getInt("course");
			ref_attribute = result.getInt("attribute");
			_time = result.getTimestamp("time");
			fetched_time = true;
			ref_editor = result.getInt("editor");
			_value = result.getString("value");
			if (result.wasNull()) {
				_value = null;
			}
			fetched_value = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"course\"");
			this.object_refid = result.getInt("\"attribute\"");
			this.subject = manager.getCourse(this.subject_refid);
			this.object = manager.getCourseAttribute(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	CourseHasCourseAttribute(RelationManager manager, Course subject, CourseAttribute object, Person _editor) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._course = this.subject = subject;
		this.ref_course = this.subject_refid = subject.id();
		this._attribute = this.object = object;
		this.ref_attribute = this.object_refid = object.id();
		timekey(true);
		
		this._editor = _editor;
		this.ref_editor = _editor.id();
		this.fetched_editor = true;
	}
	
	CourseHasCourseAttribute(RelationManager manager, boolean full, Course subject, CourseAttribute object, java.sql.Timestamp _time, Person _editor, String _value) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
		this._time = _time;
		this.fetched_time = true;
		this._editor = _editor;
		this.ref_editor = _editor.id();
		this.fetched_editor = true;
		this._value = _value;
		this.fetched_value = true;
	}
	
	@Override
	public Course subject() throws de.fu.bakery.orm.java.DatabaseException {
		if (subject == null) {
			subject = manager.getCourse(subject_refid);
		}
		return subject;
	}
	
	@Override
	public CourseAttribute object() throws de.fu.bakery.orm.java.DatabaseException {
		if (object == null) {
			object = manager.getCourseAttribute(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CourseHasCourseAttribute) {
			return equals((CourseHasCourseAttribute) obj);
		}
		return false;
	}
	
	public boolean equals(CourseHasCourseAttribute relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"courseHasCourseAttribute\"" + " (\"course\", \"attribute\","
			+ "\"time\","
			+ "\"editor\","
			+ "\"value\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
			if (manager.isNull(_time)) {
				stmt.setTimestamp(i++, _time = new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _time);
			}
			
				stmt.setInt(i++, ref_editor);
			
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.bakery.orm.java.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"courseHasCourseAttribute\"" + " SET \"time\" = ?, "
			+ "\"editor\" = ?, "
			+ "\"value\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"course\" = ?"
			+ " AND \"attribute\" = ?"
			+ " AND \"time\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setTimestamp(i++, _time);
				stmt.setInt(i++, ref_editor);
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_course);
			stmt.setInt(i++, ref_attribute);
			stmt.setTimestamp(i++, _time);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException("\"scetris\".\"courseHasCourseAttribute\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"courseHasCourseAttribute\""
			+ " WHERE "
			+ " \"course\" = ? AND "
			+ " \"attribute\" = ? AND "
			+ " \"time\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_course);
			stmt.setInt(i++, ref_attribute);
			stmt.setTimestamp(i++, _time);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("course")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "course", use = "subject", ref = Course.class)
	public Course getCourse() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_course) {
			_course = manager.getCourse(ref_course);
			fetched_course = true;
		}
		return _course;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCourseFetched() {
		return fetched_course;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseChanged() {
		return changed_course;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("course")
	public int refCourse() {
		return ref_course;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("attribute")
	@de.fu.weave.xml.annotation.XmlDependency("isAttributeFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "attribute", use = "object", ref = CourseAttribute.class)
	public CourseAttribute getAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_attribute) {
			_attribute = manager.getCourseAttribute(ref_attribute);
			fetched_attribute = true;
		}
		return _attribute;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isAttributeFetched() {
		return fetched_attribute;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isAttributeChanged() {
		return changed_attribute;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("attribute")
	public int refAttribute() {
		return ref_attribute;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("time")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "time", serial = true)
	public java.sql.Timestamp getTime() {
		return _time;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTimeChanged() {
		return changed_time;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("editor")
	@de.fu.weave.xml.annotation.XmlDependency("isEditorFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "editor", serial = true, ref = Person.class)
	public Person getEditor() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_editor) {
			_editor = manager.getPerson(ref_editor);
			fetched_editor = true;
		}
		return _editor;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isEditorFetched() {
		return fetched_editor;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isEditorChanged() {
		return changed_editor;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("editor")
	public int refEditor() {
		return ref_editor;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("value")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "value", serial = true, nullable = true)
	public String getValue() {
		if (_value == null) {
			return null;
		}
		return _value;
	}
	
	public boolean hasValue() {
		return _value != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isValueChanged() {
		return changed_value;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "timekey", serial = true, hidden = true, use = "timestamp")
	public java.sql.Timestamp getTimekey() {
		return _timekey;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTimekeyChanged() {
		return changed_timekey;
	}
	
	public java.sql.Timestamp timekey() {
		return _timekey;
	}
	
	public boolean isValidTimekey(java.sql.Timestamp key) {
		return de.fu.bakery.orm.java.GenericRelationManager.compareValues(_timekey, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.bakery.orm.java.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.bakery.orm.java.OutdatedRecordException();
		}
	}

	
	
	
	/**
	 * Sets the value of <code>course</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCourseChanged()} will return true. If you specify
	 * the same value as the old value isCourseChanged() will return the same as it did
	 * before calling <code>setCourse(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>course</code> is not nullable)
	 * @since Iteration2
	 */
	public void setCourse(Course value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("course is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_course, value._id) == 0) return;
		changed_course = true;
		ref_course = value._id;
		fetched_course = true;
		_course = value;
	}

	
	/**
	 * Sets the value of <code>attribute</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isAttributeChanged()} will return true. If you specify
	 * the same value as the old value isAttributeChanged() will return the same as it did
	 * before calling <code>setAttribute(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>attribute</code> is not nullable)
	 * @since Iteration2
	 */
	public void setAttribute(CourseAttribute value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("attribute is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_attribute, value._id) == 0) return;
		changed_attribute = true;
		ref_attribute = value._id;
		fetched_attribute = true;
		_attribute = value;
	}

	
	/**
	 * Sets the value of <code>time</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isTimeChanged()} will return true. If you specify
	 * the same value as the old value isTimeChanged() will return the same as it did
	 * before calling <code>setTime(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>time</code> is not nullable)
	 * @since Iteration2
	 */
	public void setTime(java.sql.Timestamp value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("time is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_time, value) == 0) return;
		changed_time = true;
		_time = value;
	}

	
	/**
	 * Sets the value of <code>editor</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isEditorChanged()} will return true. If you specify
	 * the same value as the old value isEditorChanged() will return the same as it did
	 * before calling <code>setEditor(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>editor</code> is not nullable)
	 * @since Iteration2
	 */
	public void setEditor(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("editor is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_editor, value._id) == 0) return;
		changed_editor = true;
		ref_editor = value._id;
		fetched_editor = true;
		_editor = value;
	}

	
	/**
	 * Sets the value of <code>value</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isValueChanged()} will return true. If you specify
	 * the same value as the old value isValueChanged() will return the same as it did
	 * before calling <code>setValue(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setValue(String value) {
		
		if (value == null) {
			clearValue();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_value, value) == 0) return;
		changed_value = true;
		_value = value;
	}
	
	/**
	 * value may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearValue() {
		if (_value == null) {
			return;
		}
		_value = null;
		changed_value = true;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

}
