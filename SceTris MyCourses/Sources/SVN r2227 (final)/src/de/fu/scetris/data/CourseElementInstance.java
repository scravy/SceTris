/* CourseElementInstance.java / 2011-03-03+01:00
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

/**
 * OO-Representation of the entity-relation CourseElementInstance
 * <p>
 * 
			A CourseElementInstance is created within a Program belonging to a CourseInstance. It is a CourseElement actually taking place.
		
 */
@de.fu.weave.orm.annotation.Entity(name = "CourseElementInstance")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"CourseElementInstance\"", requiredSqlCols = {"course_instance", "course_element", "duration"})
@de.fu.weave.xml.annotation.XmlElement("CourseElementInstance")
public class CourseElementInstance extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String CourseInstance = "course_instance";
	public static final String CourseElement = "course_element";
	public static final String StartingTimeslot = "starting_timeslot";
	public static final String Lecturer = "lecturer";
	public static final String Duration = "duration";
	public static final String SchedulableLesson = "schedulable_lesson";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 1887536L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("courseInstance") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("courseInstance$validator")
		@de.fu.weave.Form.ActiveConverter("courseInstance$converter")
		@de.fu.weave.Form.Alternatives("courseInstance$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int courseInstance;
		
		public java.util.Map<Integer,java.lang.String> courseInstance$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("courseElement") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("courseElement$validator")
		@de.fu.weave.Form.ActiveConverter("courseElement$converter")
		@de.fu.weave.Form.Alternatives("courseElement$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int courseElement;
		
		public java.util.Map<Integer,java.lang.String> courseElement$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(5)
		@de.fu.weave.Form.Field("startingTimeslot")
		@de.fu.weave.Form.ActiveValidator("startingTimeslot$validator")
		@de.fu.weave.Form.ActiveConverter("startingTimeslot$converter")
		@de.fu.weave.Form.Alternatives("startingTimeslot$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer startingTimeslot;
		
		public java.util.Map<Integer,java.lang.String> startingTimeslot$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(6)
		@de.fu.weave.Form.Field("lecturer")
		@de.fu.weave.Form.ActiveValidator("lecturer$validator")
		@de.fu.weave.Form.ActiveConverter("lecturer$converter")
		@de.fu.weave.Form.Alternatives("lecturer$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer lecturer;
		
		public java.util.Map<Integer,java.lang.String> lecturer$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(7)
		@de.fu.weave.Form.Field("duration") @de.fu.weave.Form.Required
		@de.fu.weave.Form.Min(1)
		@de.fu.weave.Form.ActiveValidator("duration$validator")
		@de.fu.weave.Form.ActiveConverter("duration$converter")
		public int duration;
		
		@de.fu.weave.Form.Pos(8)
		@de.fu.weave.Form.Field("schedulableLesson") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("schedulableLesson$validator")
		@de.fu.weave.Form.ActiveConverter("schedulableLesson$converter")
		public boolean schedulableLesson;
		
		
		public Form setValues(CourseElementInstance $object) {
			
			id = $object.getId();
			timekey = $object.getTimekey();
			courseInstance = $object.refCourseInstance();
			courseElement = $object.refCourseElement();
			startingTimeslot = $object.refStartingTimeslot();
			lecturer = $object.refLecturer();
			duration = $object.getDuration();
			schedulableLesson = $object.getSchedulableLesson();
			return this;
		}
	}
	
	/**
	 * numeric identifier
	 * <p>
	 * 
				An artificial key introduced to allow easy referencing of an entity.
				Once an id has been set it won’t change, therfore avoiding update anomalies.
			
	 */
	int _id;
	boolean changed_id = false;
	boolean fetched_id = false;/**
	 * 
	 * <p>
	 * 
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * is a part of
	 * <p>
	 * 
				The Course this Course Element Instance is part of.
			
	 */
	CourseInstance _courseInstance;
	boolean changed_courseInstance = false;
	boolean fetched_courseInstance = false;
	int ref_courseInstance;/**
	 * derived from
	 * <p>
	 * 
				The Course Element this Course Element Instance is derived from.
			
	 */
	CourseElement _courseElement;
	boolean changed_courseElement = false;
	boolean fetched_courseElement = false;
	int ref_courseElement;/**
	 * begin
	 * <p>
	 * 
				The starting Timeslot this Course Element Instance is beginning at.
			
	 */
	Timeslot _startingTimeslot = null;
	boolean changed_startingTimeslot = false;
	boolean fetched_startingTimeslot = false;
	Integer ref_startingTimeslot;/**
	 * docent
	 * <p>
	 * 
				The teacher for this Course Element Instance.
			
	 */
	Person _lecturer = null;
	boolean changed_lecturer = false;
	boolean fetched_lecturer = false;
	Integer ref_lecturer;/**
	 * duration
	 * <p>
	 * 
				The duration of this Course Element Instance.
			
	 */
	int _duration;
	boolean changed_duration = false;
	boolean fetched_duration = false;/**
	 * scheduleable
	 * <p>
	 * 
				Whether or not this Course Element Instance is scheduleable.
			
	 */
	boolean _schedulableLesson;
	boolean changed_schedulableLesson = false;
	boolean fetched_schedulableLesson = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	CourseElementInstance(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_courseInstance = result.getInt("course_instance");
			ref_courseElement = result.getInt("course_element");
			ref_startingTimeslot = result.getInt("starting_timeslot");
			if (result.wasNull()) {
				ref_startingTimeslot = null;
			}
			ref_lecturer = result.getInt("lecturer");
			if (result.wasNull()) {
				ref_lecturer = null;
			}
			_duration = result.getInt("duration");
			fetched_duration = true;
			_schedulableLesson = result.getBoolean("schedulable_lesson");
			fetched_schedulableLesson = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity CourseElementInstance and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 */
	CourseElementInstance(RelationManager manager, CourseInstance _courseInstance, CourseElement _courseElement, int _duration) {
		timekey(true);
		this.manager = manager;
		this._courseInstance = _courseInstance;
		this.ref_courseInstance = _courseInstance.id();
		this.fetched_courseInstance = true;
		this._courseElement = _courseElement;
		this.ref_courseElement = _courseElement.id();
		this.fetched_courseElement = true;
		this._duration = _duration;
		this.fetched_duration = true;
	}
	
	/**
	 * Constructs the entity CourseElementInstance and initializes all attributes.
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 * @param _startingTimeslot 
				The starting Timeslot this Course Element Instance is beginning at.
			
	 * @param _lecturer 
				The teacher for this Course Element Instance.
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 * @param _schedulableLesson 
				Whether or not this Course Element Instance is scheduleable.
			
	 */
	CourseElementInstance(RelationManager manager, final boolean full, CourseInstance _courseInstance, CourseElement _courseElement, Timeslot _startingTimeslot, Person _lecturer, int _duration, boolean _schedulableLesson) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._courseInstance = _courseInstance;
			this.ref_courseInstance = _courseInstance.id();
			this.fetched_courseInstance = true;
			this._courseElement = _courseElement;
			this.ref_courseElement = _courseElement.id();
			this.fetched_courseElement = true;
			this._startingTimeslot = _startingTimeslot;
			this.ref_startingTimeslot = _startingTimeslot.id();
			this.fetched_startingTimeslot = true;
			this._lecturer = _lecturer;
			this.ref_lecturer = _lecturer.id();
			this.fetched_lecturer = true;
			this._duration = _duration;
			this.fetched_duration = true;
			this._schedulableLesson = _schedulableLesson;
			this.fetched_schedulableLesson = true;
		}
	}
	
	/**
	 * Returns the unique identifier for this entity.
	 * @since Iteration2
	 */
	public int id() {
		return _id;
	}
	
	@Override
	public String toString() {
		return "CourseElementInstance: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return de.fu.weave.orm.GenericRelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseElementInstance} rather than {@link RelationManager#createCourseElementInstance).
	 */
	@Override
	public CourseElementInstance create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseElementInstance\"" + " (\"course_instance\", \"course_element\", \"starting_timeslot\", \"lecturer\", \"duration\", \"schedulable_lesson\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_courseInstance);
			
				stmt.setInt(i++, ref_courseElement);
			
			if (ref_startingTimeslot != null) {
				stmt.setInt(i++, ref_startingTimeslot);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_lecturer != null) {
				stmt.setInt(i++, ref_lecturer);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, _duration);
			
			if (manager.isNull(_schedulableLesson)) {
				stmt.setBoolean(i++, _schedulableLesson = true);
			} else {
				stmt.setBoolean(i++, _schedulableLesson);
			}
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
			changed_courseInstance = false;
			changed_courseElement = false;
			changed_startingTimeslot = false;
			changed_lecturer = false;
			changed_duration = false;
			changed_schedulableLesson = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"CourseElementInstance\"" + " SET \"course_instance\" = ?"
			+ ", \"course_element\" = ?"
			+ ", \"starting_timeslot\" = ?"
			+ ", \"lecturer\" = ?"
			+ ", \"duration\" = ?"
			+ ", \"schedulable_lesson\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_courseInstance);
			
				stmt.setInt(i++, ref_courseElement);
			
			if (ref_startingTimeslot != null) {
				stmt.setInt(i++, ref_startingTimeslot);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_lecturer != null) {
				stmt.setInt(i++, ref_lecturer);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, _duration);
			
			if (manager.isNull(_schedulableLesson)) {
				stmt.setBoolean(i++, true);
			} else {
				stmt.setBoolean(i++, _schedulableLesson);
			}
			
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_courseInstance = false;
			changed_courseElement = false;
			changed_startingTimeslot = false;
			changed_lecturer = false;
			changed_duration = false;
			changed_schedulableLesson = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 */
	public void pullChanges() throws de.fu.weave.orm.DatabaseException {
		pullChanges(0);
	}
	
	/**
	 *
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.weave.orm.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseElementInstance\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = false;
	}

	@Override
	public boolean exists() {
		return exists;
	}
	
	public int compareTo(CourseElementInstance entity) {
		if (!exists) {
			if (this == entity) return 0;
			if (this.hashCode() <= entity.hashCode()) return -7;
			return 7;
		}
		if (entity == null) return -4711;
		if (equals(entity)) {
			return 0;
		}
		return de.fu.weave.orm.GenericRelationManager.compareValues(id(), entity.id());
	}
	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof CourseElementInstance) {
			return compareTo((CourseElementInstance) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseElementInstance entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof CourseElementInstance) {
			return equals((CourseElementInstance) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstancePrefersRoom.
	 * <p>
	 * This is the subject of ElementInstancePrefersRoom.
	 
	 * @return The object regarding ElementInstancePrefersRoom where this (the java-object you call objectsOfElementInstancePrefersRoom on) is the subject.
	 * @since Iteration3
	 */
	public Room objectOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Room> coll = objectsOfElementInstancePrefersRoom();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via ElementInstancePrefersRoom. If you know that there will be only
	 * 1:1 relationships of the type ElementInstancePrefersRoom, {@link #objectOfElementInstancePrefersRoom} is what you are looking for.
	 * <p>
	 * This is the subject of ElementInstancePrefersRoom.
	 * @return The object regarding ElementInstancePrefersRoom where this (the java-object you call objectsOfElementInstancePrefersRoom on) is the subject.
	 */
	public java.util.List<Room> objectsOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE \"id\" IN (SELECT \"room\" FROM "
			+ "\"scetris\".\"elementInstancePrefersRoom\"" + " WHERE \"element_instance\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Room> ret = new java.util.ArrayList<Room>();
			while (result.next()) {
				ret.add(new Room(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 */
	public java.util.List<ElementInstancePrefersRoom> whereSubjectOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersRoom\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementInstancePrefersRoom> ret = new java.util.ArrayList<ElementInstancePrefersRoom>();
			while (result.next()) {
				ret.add(new ElementInstancePrefersRoom(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstancePrefersTimeslot.
	 * <p>
	 * This is the subject of ElementInstancePrefersTimeslot.
	 
	 * @return The object regarding ElementInstancePrefersTimeslot where this (the java-object you call objectsOfElementInstancePrefersTimeslot on) is the subject.
	 * @since Iteration3
	 */
	public Timeslot objectOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Timeslot> coll = objectsOfElementInstancePrefersTimeslot();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via ElementInstancePrefersTimeslot. If you know that there will be only
	 * 1:1 relationships of the type ElementInstancePrefersTimeslot, {@link #objectOfElementInstancePrefersTimeslot} is what you are looking for.
	 * <p>
	 * This is the subject of ElementInstancePrefersTimeslot.
	 * @return The object regarding ElementInstancePrefersTimeslot where this (the java-object you call objectsOfElementInstancePrefersTimeslot on) is the subject.
	 */
	public java.util.List<Timeslot> objectsOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE \"id\" IN (SELECT \"timeslot\" FROM "
			+ "\"scetris\".\"elementInstancePrefersTimeslot\"" + " WHERE \"element_instance\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Timeslot> ret = new java.util.ArrayList<Timeslot>();
			while (result.next()) {
				ret.add(new Timeslot(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 */
	public java.util.List<ElementInstancePrefersTimeslot> whereSubjectOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementInstancePrefersTimeslot> ret = new java.util.ArrayList<ElementInstancePrefersTimeslot>();
			while (result.next()) {
				ret.add(new ElementInstancePrefersTimeslot(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstanceRequiresFeature.
	 * <p>
	 * This is the subject of ElementInstanceRequiresFeature.
	 
	 * @return The object regarding ElementInstanceRequiresFeature where this (the java-object you call objectsOfElementInstanceRequiresFeature on) is the subject.
	 * @since Iteration3
	 */
	public Feature objectOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Feature> coll = objectsOfElementInstanceRequiresFeature();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via ElementInstanceRequiresFeature. If you know that there will be only
	 * 1:1 relationships of the type ElementInstanceRequiresFeature, {@link #objectOfElementInstanceRequiresFeature} is what you are looking for.
	 * <p>
	 * This is the subject of ElementInstanceRequiresFeature.
	 * @return The object regarding ElementInstanceRequiresFeature where this (the java-object you call objectsOfElementInstanceRequiresFeature on) is the subject.
	 */
	public java.util.List<Feature> objectsOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\""
			+ " WHERE \"id\" IN (SELECT \"feature\" FROM "
			+ "\"scetris\".\"elementInstanceRequiresFeature\"" + " WHERE \"element_instance\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Feature> ret = new java.util.ArrayList<Feature>();
			while (result.next()) {
				ret.add(new Feature(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 */
	public java.util.List<ElementInstanceRequiresFeature> whereSubjectOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceRequiresFeature\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementInstanceRequiresFeature> ret = new java.util.ArrayList<ElementInstanceRequiresFeature>();
			while (result.next()) {
				ret.add(new ElementInstanceRequiresFeature(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstanceTakesPlaceInRoom.
	 * <p>
	 * This is the subject of ElementInstanceTakesPlaceInRoom.
	 
	 * @return The object regarding ElementInstanceTakesPlaceInRoom where this (the java-object you call objectsOfElementInstanceTakesPlaceInRoom on) is the subject.
	 * @since Iteration3
	 */
	public Room objectOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Room> coll = objectsOfElementInstanceTakesPlaceInRoom();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via ElementInstanceTakesPlaceInRoom. If you know that there will be only
	 * 1:1 relationships of the type ElementInstanceTakesPlaceInRoom, {@link #objectOfElementInstanceTakesPlaceInRoom} is what you are looking for.
	 * <p>
	 * This is the subject of ElementInstanceTakesPlaceInRoom.
	 * @return The object regarding ElementInstanceTakesPlaceInRoom where this (the java-object you call objectsOfElementInstanceTakesPlaceInRoom on) is the subject.
	 */
	public java.util.List<Room> objectsOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE \"id\" IN (SELECT \"room\" FROM "
			+ "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + " WHERE \"element_instance\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Room> ret = new java.util.ArrayList<Room>();
			while (result.next()) {
				ret.add(new Room(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 */
	public java.util.List<ElementInstanceTakesPlaceInRoom> whereSubjectOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementInstanceTakesPlaceInRoom> ret = new java.util.ArrayList<ElementInstanceTakesPlaceInRoom>();
			while (result.next()) {
				ret.add(new ElementInstanceTakesPlaceInRoom(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonTakesPartInElementInstance.
	 * <p>
	 * This is the object of PersonTakesPartInElementInstance.
	 * @return The subject regarding PersonTakesPartInElementInstance where this (the java-object you call subjectsOfPersonTakesPartInElementInstance on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Person> coll = subjectsOfPersonTakesPartInElementInstance();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via PersonTakesPartInElementInstance. If you know that there will be only
	 * 1:1 relationships of the type PersonTakesPartInElementInstance, {@link #subjectOfPersonTakesPartInElementInstance} is what you are looking for.
	 * <p>
	 * This is the object of PersonTakesPartInElementInstance.
	 * @return A Collection of subjects regarding PersonTakesPartInElementInstance where this (the java-object you call subjectsOfPersonTakesPartInElementInstance on) is the object.
	 */
	public java.util.List<Person> subjectsOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE \"id\" IN (SELECT \"user\" FROM "
			+ "\"scetris\".\"personTakesPartInElementInstance\"" + " WHERE \"element_instance\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Person> ret = new java.util.ArrayList<Person>();
			while (result.next()) {
				ret.add(new Person(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<PersonTakesPartInElementInstance> whereObjectOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personTakesPartInElementInstance\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonTakesPartInElementInstance> ret = new java.util.ArrayList<PersonTakesPartInElementInstance>();
			while (result.next()) {
				ret.add(new PersonTakesPartInElementInstance(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	@de.fu.weave.orm.annotation.Attribute(name = "timekey", serial = true, hidden = true, use = "timestamp")
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
		return de.fu.weave.orm.GenericRelationManager.compareValues(_timekey, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.weave.orm.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.weave.orm.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlElement("courseInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseInstanceFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "course_instance", serial = true, ref = CourseInstance.class)
	public CourseInstance getCourseInstance() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_courseInstance) {
			_courseInstance = manager.getCourseInstance(ref_courseInstance);
			fetched_courseInstance = true;
		}
		return _courseInstance;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCourseInstanceFetched() {
		return fetched_courseInstance;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseInstanceChanged() {
		return changed_courseInstance;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("courseInstance")
	public int refCourseInstance() {
		return ref_courseInstance;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("courseElement")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseElementFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "course_element", serial = true, ref = CourseElement.class)
	public CourseElement getCourseElement() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_courseElement) {
			_courseElement = manager.getCourseElement(ref_courseElement);
			fetched_courseElement = true;
		}
		return _courseElement;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCourseElementFetched() {
		return fetched_courseElement;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseElementChanged() {
		return changed_courseElement;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("courseElement")
	public int refCourseElement() {
		return ref_courseElement;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("startingTimeslot")
	@de.fu.weave.xml.annotation.XmlDependency("isStartingTimeslotFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "starting_timeslot", serial = true, nullable = true, ref = Timeslot.class)
	public Timeslot getStartingTimeslot() throws de.fu.weave.orm.DatabaseException {
		if (ref_startingTimeslot == null) {
			return null;
		}
		if (!fetched_startingTimeslot) {
			_startingTimeslot = manager.getTimeslot(ref_startingTimeslot);
			fetched_startingTimeslot = true;
		}
		return _startingTimeslot;
	}
	
	public boolean hasStartingTimeslot() {
		return _startingTimeslot != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isStartingTimeslotFetched() {
		return fetched_startingTimeslot;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStartingTimeslotChanged() {
		return changed_startingTimeslot;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("startingTimeslot")
	public Integer refStartingTimeslot() {
		return ref_startingTimeslot;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("lecturer")
	@de.fu.weave.xml.annotation.XmlDependency("isLecturerFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "lecturer", serial = true, nullable = true, ref = Person.class)
	public Person getLecturer() throws de.fu.weave.orm.DatabaseException {
		if (ref_lecturer == null) {
			return null;
		}
		if (!fetched_lecturer) {
			_lecturer = manager.getPerson(ref_lecturer);
			fetched_lecturer = true;
		}
		return _lecturer;
	}
	
	public boolean hasLecturer() {
		return _lecturer != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isLecturerFetched() {
		return fetched_lecturer;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isLecturerChanged() {
		return changed_lecturer;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("lecturer")
	public Integer refLecturer() {
		return ref_lecturer;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("duration")
	@de.fu.weave.orm.annotation.Attribute(name = "duration", serial = true)
	public int getDuration() {
		return _duration;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDurationChanged() {
		return changed_duration;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("schedulableLesson")
	@de.fu.weave.orm.annotation.Attribute(name = "schedulable_lesson", serial = true)
	public boolean getSchedulableLesson() {
		return _schedulableLesson;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isSchedulableLessonChanged() {
		return changed_schedulableLesson;
	}
	

	
	
	
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>courseInstance</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCourseInstanceChanged()} will return true. If you specify
	 * the same value as the old value isCourseInstanceChanged() will return the same as it did
	 * before calling <code>setCourseInstance(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>courseInstance</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseElementInstance setCourseInstance(CourseInstance value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("courseInstance is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_courseInstance, value._id) == 0) return this;
		changed_courseInstance = true;
		ref_courseInstance = value._id;
		fetched_courseInstance = true;
		_courseInstance = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>courseElement</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCourseElementChanged()} will return true. If you specify
	 * the same value as the old value isCourseElementChanged() will return the same as it did
	 * before calling <code>setCourseElement(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>courseElement</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseElementInstance setCourseElement(CourseElement value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("courseElement is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_courseElement, value._id) == 0) return this;
		changed_courseElement = true;
		ref_courseElement = value._id;
		fetched_courseElement = true;
		_courseElement = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>startingTimeslot</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isStartingTimeslotChanged()} will return true. If you specify
	 * the same value as the old value isStartingTimeslotChanged() will return the same as it did
	 * before calling <code>setStartingTimeslot(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public CourseElementInstance setStartingTimeslot(Timeslot value) {
		
		if (value == null) {
			clearStartingTimeslot();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_startingTimeslot, value._id) == 0) return this;
		changed_startingTimeslot = true;
		ref_startingTimeslot = value._id;
		fetched_startingTimeslot = true;
		_startingTimeslot = value;
		return this;
	}
	
	/**
	 * startingTimeslot may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public CourseElementInstance clearStartingTimeslot() {
		if (_startingTimeslot == null && ref_startingTimeslot == null) {
			return this;
		}
		_startingTimeslot = null;
		ref_startingTimeslot = null;
		changed_startingTimeslot = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>lecturer</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isLecturerChanged()} will return true. If you specify
	 * the same value as the old value isLecturerChanged() will return the same as it did
	 * before calling <code>setLecturer(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public CourseElementInstance setLecturer(Person value) {
		
		if (value == null) {
			clearLecturer();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_lecturer, value._id) == 0) return this;
		changed_lecturer = true;
		ref_lecturer = value._id;
		fetched_lecturer = true;
		_lecturer = value;
		return this;
	}
	
	/**
	 * lecturer may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public CourseElementInstance clearLecturer() {
		if (_lecturer == null && ref_lecturer == null) {
			return this;
		}
		_lecturer = null;
		ref_lecturer = null;
		changed_lecturer = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>duration</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDurationChanged()} will return true. If you specify
	 * the same value as the old value isDurationChanged() will return the same as it did
	 * before calling <code>setDuration(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>duration</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseElementInstance setDuration(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("duration is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_duration, value) == 0) return this;
		changed_duration = true;
		_duration = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>schedulableLesson</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isSchedulableLessonChanged()} will return true. If you specify
	 * the same value as the old value isSchedulableLessonChanged() will return the same as it did
	 * before calling <code>setSchedulableLesson(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>schedulableLesson</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseElementInstance setSchedulableLesson(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("schedulableLesson is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_schedulableLesson, value) == 0) return this;
		changed_schedulableLesson = true;
		_schedulableLesson = value;
		return this;
	}

}
