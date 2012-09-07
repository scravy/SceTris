/* CourseElementInstance.java / 2010-11-04+01:00
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
 * OO-Representation of the entity-relation CourseElementInstance
 * <p>
 * 
			A CourseElementInstance is created within a Program belonging to a CourseInstance. It is a CourseElement actually taking place.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "CourseElementInstance")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"CourseElementInstance\"")
@de.fu.weave.xml.annotation.XmlElement("CourseElementInstance")
public class CourseElementInstance extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;

	/**
	 * numeric identifier
	 * <p>
	 * 
				An artificial key introduced to allow easy referencing of an entity.
				Once an id has been set it won’t change, therfore avoiding update anomalies.
			
	 * @since Iteration2
	 */
	int _id;
	boolean changed_id = false;
	boolean fetched_id = false;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * is a part of
	 * <p>
	 * 
				The Course this Course Element Instance is part of.
			
	 * @since Iteration2
	 */
	CourseInstance _courseInstance;
	boolean changed_courseInstance = false;
	boolean fetched_courseInstance = false;
	int ref_courseInstance;/**
	 * derived from
	 * <p>
	 * 
				The Course Element this Course Element Instance is derived from.
			
	 * @since Iteration2
	 */
	CourseElement _courseElement;
	boolean changed_courseElement = false;
	boolean fetched_courseElement = false;
	int ref_courseElement;/**
	 * begin
	 * <p>
	 * 
				The starting Timeslot this Course Element Instance is beginning at.
			
	 * @since Iteration2
	 */
	Timeslot _startingTimeslot = null;
	boolean changed_startingTimeslot = false;
	boolean fetched_startingTimeslot = false;
	Integer ref_startingTimeslot;/**
	 * duration
	 * <p>
	 * 
				The duration of this Course Element Instance.
			
	 * @since Iteration2
	 */
	int _duration;
	boolean changed_duration = false;
	boolean fetched_duration = false;/**
	 * scheduleable
	 * <p>
	 * 
				Whether or not this Course Element Instance is scheduleable.
			
	 * @since Iteration2
	 */
	boolean _schedulableLesson;
	boolean changed_schedulableLesson = false;
	boolean fetched_schedulableLesson = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	CourseElementInstance(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_courseInstance = result.getInt("course_instance");
			ref_courseElement = result.getInt("course_element");
			ref_startingTimeslot = result.getInt("starting_timeslot");
			_duration = result.getInt("duration");
			fetched_duration = true;
			_schedulableLesson = result.getBoolean("schedulable_lesson");
			fetched_schedulableLesson = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity CourseElementInstance and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _courseInstance 
				The Course this Course Element Instance is part of.
			
	 * @param _courseElement 
				The Course Element this Course Element Instance is derived from.
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 * @since Iteration2
	 */
	CourseElementInstance(RelationManager manager, CourseInstance _courseInstance, CourseElement _courseElement, int _duration) {
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
			
	 * @param _duration 
				The duration of this Course Element Instance.
			
	 * @param _schedulableLesson 
				Whether or not this Course Element Instance is scheduleable.
			
	 * @since Iteration2
	 */
	CourseElementInstance(RelationManager manager, final boolean full, CourseInstance _courseInstance, CourseElement _courseElement, Timeslot _startingTimeslot, int _duration, boolean _schedulableLesson) {
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
			this._duration = _duration;
			this.fetched_duration = true;
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
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseElementInstance} rather than {@link RelationManager#createCourseElementInstance).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseElementInstance\"" + " (\"course_instance\" , \"course_element\" , \"starting_timeslot\" , \"duration\" , \"schedulable_lesson\" )"
			+ " VALUES (?, ?, ?, ?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_courseInstance);
			
				stmt.setInt(i++, ref_courseElement);
			
			if (ref_startingTimeslot != null) {
				stmt.setInt(i++, ref_startingTimeslot);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, _duration);
			
			if (manager.isNull(_schedulableLesson)) {
				stmt.setBoolean(i++, true);
			} else {
				stmt.setBoolean(i++, _schedulableLesson);
			}
			
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 * Updates the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"CourseElementInstance\"" + " SET \"course_instance\" = ?, \"course_element\" = ?, \"starting_timeslot\" = ?, \"duration\" = ?, \"schedulable_lesson\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_courseInstance);
			
				stmt.setInt(i++, ref_courseElement);
			
			if (ref_startingTimeslot != null) {
				stmt.setInt(i++, ref_startingTimeslot);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, _duration);
			
			if (manager.isNull(_schedulableLesson)) {
				stmt.setBoolean(i++, true);
			} else {
				stmt.setBoolean(i++, _schedulableLesson);
			}
			
			stmt.setInt(i, this.id());
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 * Deletes the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseElementInstance\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(CourseElementInstance entity) {
		if (entity == null) return -1;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof CourseElementInstance) {
			return compareTo((CourseElementInstance) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseElementInstance entity) {
		if (entity == null) return false;
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
		java.util.Collection<Room> coll = objectsOfElementInstancePrefersRoom();
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
	 * @since Iteration3
	 */
	public java.util.Collection<Room> objectsOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE id IN (SELECT room FROM "
			+ "\"scetris\".\"elementInstancePrefersRoom\"" + " WHERE element_instance = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Room> ret = new java.util.ArrayList<Room>();
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
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstancePrefersRoom> whereSubjectOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersRoom\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstancePrefersRoom> ret = new java.util.ArrayList<ElementInstancePrefersRoom>();
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
		java.util.Collection<Timeslot> coll = objectsOfElementInstancePrefersTimeslot();
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
	 * @since Iteration3
	 */
	public java.util.Collection<Timeslot> objectsOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE id IN (SELECT timeslot FROM "
			+ "\"scetris\".\"elementInstancePrefersTimeslot\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Timeslot> ret = new java.util.ArrayList<Timeslot>();
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
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstancePrefersTimeslot> whereSubjectOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\""
			+ " WHERE \"room\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstancePrefersTimeslot> ret = new java.util.ArrayList<ElementInstancePrefersTimeslot>();
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
		java.util.Collection<Feature> coll = objectsOfElementInstanceRequiresFeature();
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
	 * @since Iteration3
	 */
	public java.util.Collection<Feature> objectsOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\""
			+ " WHERE id IN (SELECT feature FROM "
			+ "\"scetris\".\"elementInstanceRequiresFeature\"" + " WHERE element_instance = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Feature> ret = new java.util.ArrayList<Feature>();
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
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstanceRequiresFeature> whereSubjectOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceRequiresFeature\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstanceRequiresFeature> ret = new java.util.ArrayList<ElementInstanceRequiresFeature>();
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
		java.util.Collection<Room> coll = objectsOfElementInstanceTakesPlaceInRoom();
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
	 * @since Iteration3
	 */
	public java.util.Collection<Room> objectsOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE id IN (SELECT room FROM "
			+ "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + " WHERE element_instance = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Room> ret = new java.util.ArrayList<Room>();
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
	 * @since Iteration3
	 */
	public java.util.Collection<ElementInstanceTakesPlaceInRoom> whereSubjectOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\""
			+ " WHERE \"element_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstanceTakesPlaceInRoom> ret = new java.util.ArrayList<ElementInstanceTakesPlaceInRoom>();
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
		java.util.Collection<Person> coll = subjectsOfPersonTakesPartInElementInstance();
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
	 * @since Iteration3
	 */
	public java.util.Collection<Person> subjectsOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT user FROM "
			+ "\"scetris\".\"personTakesPartInElementInstance\"" + " WHERE element_instance = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Person> ret = new java.util.ArrayList<Person>();
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
	public java.util.Collection<PersonTakesPartInElementInstance> whereObjectOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personTakesPartInElementInstance\""
			+ " WHERE element_instance = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonTakesPartInElementInstance> ret = new java.util.ArrayList<PersonTakesPartInElementInstance>();
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
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isIdChanged() {
		return changed_id;
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
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseInstanceChanged() {
		return changed_courseInstance;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("courseInstance")
	public int refCourseInstance() {
		return ref_courseInstance;
	}

	
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
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseElementChanged() {
		return changed_courseElement;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("courseElement")
	public int refCourseElement() {
		return ref_courseElement;
	}

	
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
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStartingTimeslotChanged() {
		return changed_startingTimeslot;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("startingTimeslot")
	public Integer refStartingTimeslot() {
		return ref_startingTimeslot;
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

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setId(int value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("id is not nullable, null given");
		}
		changed_id = true;
		_id = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("timekey is not nullable, null given");
		}
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCourseInstance(CourseInstance value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("courseInstance is not nullable, null given");
		}
		changed_courseInstance = true;
		ref_courseInstance = value._id;
		fetched_courseInstance = true;
		_courseInstance = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCourseElement(CourseElement value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("courseElement is not nullable, null given");
		}
		changed_courseElement = true;
		ref_courseElement = value._id;
		fetched_courseElement = true;
		_courseElement = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setStartingTimeslot(Timeslot value) {
		if (value == null) {
			_startingTimeslot = null;
			ref_startingTimeslot = null;
			return;
		}
		
		changed_startingTimeslot = true;
		ref_startingTimeslot = value._id;
		fetched_startingTimeslot = true;
		_startingTimeslot = value;
	}
	
	/**
	 * startingTimeslot may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearStartingTimeslot() {
		_startingTimeslot = null;
		ref_startingTimeslot = null;
		changed_startingTimeslot = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setDuration(int value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("duration is not nullable, null given");
		}
		changed_duration = true;
		_duration = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setSchedulableLesson(boolean value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("schedulableLesson is not nullable, null given");
		}
		changed_schedulableLesson = true;
		_schedulableLesson = value;
	}

}
