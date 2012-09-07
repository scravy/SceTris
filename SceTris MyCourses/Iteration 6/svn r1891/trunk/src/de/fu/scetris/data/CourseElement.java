/* CourseElement.java / 2011-01-15+01:00
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
 * OO-Representation of the entity-relation CourseElement
 * <p>
 * 
			A CourseElement is a type of event at a school or university, like a lecture or a seminar. A CourseElement has a duration and it may take place several times in a week (of which each occurrence has a certain duration). A CourseElement may have some dependencies regarding the Features the Room it is held in has. A CourseElement is part of a Course. It is of a certain type (e.g. lecture, seminar, discussion, meeting, ...).
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "CourseElement")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"CourseElement\"", requiredSqlCols = {"part_of", "duration"})
@de.fu.weave.xml.annotation.XmlElement("CourseElement")
public class CourseElement extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String Name = "name";
	public static final String PartOf = "part_of";
	public static final String Duration = "duration";
	public static final String Type = "type";
	public static final String Required = "required";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 1651587L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("name")
		@de.fu.weave.Form.ActiveValidator("name$validator")
		@de.fu.weave.Form.ActiveConverter("name$converter")
		public String name;
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("partOf") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("partOf$validator")
		@de.fu.weave.Form.ActiveConverter("partOf$converter")
		@de.fu.weave.Form.Alternatives("partOf$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int partOf;
		
		public java.util.Map<Integer,java.lang.String> partOf$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(5)
		@de.fu.weave.Form.Field("duration") @de.fu.weave.Form.Required
		@de.fu.weave.Form.Min(1)
		@de.fu.weave.Form.ActiveValidator("duration$validator")
		@de.fu.weave.Form.ActiveConverter("duration$converter")
		public int duration;
		
		@de.fu.weave.Form.Pos(6)
		@de.fu.weave.Form.Field("type")
		@de.fu.weave.Form.ActiveValidator("type$validator")
		@de.fu.weave.Form.ActiveConverter("type$converter")
		@de.fu.weave.Form.Alternatives("type$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer type;
		
		public java.util.Map<Integer,java.lang.String> type$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(7)
		@de.fu.weave.Form.Field("required") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("required$validator")
		@de.fu.weave.Form.ActiveConverter("required$converter")
		public boolean required;
		
		
		public Form setValues(CourseElement $object) {
			
			id = $object.getId();
			timekey = $object.getTimekey();
			name = $object.getName();
			partOf = $object.refPartOf();
			duration = $object.getDuration();
			type = $object.refType();
			required = $object.getRequired();
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
	 * name of the course element
	 * <p>
	 * 
				The name of the Course Element.
			
	 */
	String _name = null;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * part of
	 * <p>
	 * 
				The Course this Course Elements belongs to.
			
	 */
	Course _partOf;
	boolean changed_partOf = false;
	boolean fetched_partOf = false;
	int ref_partOf;/**
	 * duration
	 * <p>
	 * 
				The duration of the Course Element.
			
	 */
	int _duration;
	boolean changed_duration = false;
	boolean fetched_duration = false;/**
	 * type of lessons
	 * <p>
	 * 
				The type of the Course Element.
			
	 */
	CourseElementType _type = null;
	boolean changed_type = false;
	boolean fetched_type = false;
	Integer ref_type;/**
	 * obligatory
	 * <p>
	 * 
				Whether this Course Element is obligatory or not.
			
	 */
	boolean _required;
	boolean changed_required = false;
	boolean fetched_required = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	CourseElement(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_name = result.getString("name");
			if (result.wasNull()) {
				_name = null;
			}
			fetched_name = true;
			ref_partOf = result.getInt("part_of");
			_duration = result.getInt("duration");
			fetched_duration = true;
			ref_type = result.getInt("type");
			if (result.wasNull()) {
				ref_type = null;
			}
			_required = result.getBoolean("required");
			fetched_required = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity CourseElement and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _partOf 
				The Course this Course Elements belongs to.
			
	 * @param _duration 
				The duration of the Course Element.
			
	 * @since Iteration2
	 */
	CourseElement(RelationManager manager, Course _partOf, int _duration) {
		timekey(true);
		this.manager = manager;
		this._partOf = _partOf;
		this.ref_partOf = _partOf.id();
		this.fetched_partOf = true;
		this._duration = _duration;
		this.fetched_duration = true;
	}
	
	/**
	 * Constructs the entity CourseElement and initializes all attributes.
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
			
	 * @since Iteration2
	 */
	CourseElement(RelationManager manager, final boolean full, String _name, Course _partOf, int _duration, CourseElementType _type, boolean _required) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._partOf = _partOf;
			this.ref_partOf = _partOf.id();
			this.fetched_partOf = true;
			this._duration = _duration;
			this.fetched_duration = true;
			this._type = _type;
			this.ref_type = _type.id();
			this.fetched_type = true;
			this._required = _required;
			this.fetched_required = true;
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
		return "CourseElement: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseElement} rather than {@link RelationManager#createCourseElement).
	 * @since Iteration2
	 */
	@Override
	public CourseElement create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseElement\"" + " (\"name\", \"part_of\", \"duration\", \"type\", \"required\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (_name != null) {
				stmt.setString(i++, _name);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setInt(i++, ref_partOf);
			
				stmt.setInt(i++, _duration);
			
			if (ref_type != null) {
				stmt.setInt(i++, ref_type);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, _required = true);
			} else {
				stmt.setBoolean(i++, _required);
			}
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_partOf = false;
			changed_duration = false;
			changed_type = false;
			changed_required = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void pushChanges() throws de.fu.bakery.orm.java.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"CourseElement\"" + " SET \"name\" = ?"
			+ ", \"part_of\" = ?"
			+ ", \"duration\" = ?"
			+ ", \"type\" = ?"
			+ ", \"required\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (_name != null) {
				stmt.setString(i++, _name);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setInt(i++, ref_partOf);
			
				stmt.setInt(i++, _duration);
			
			if (ref_type != null) {
				stmt.setInt(i++, ref_type);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, true);
			} else {
				stmt.setBoolean(i++, _required);
			}
			
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_name = false;
			changed_partOf = false;
			changed_duration = false;
			changed_type = false;
			changed_required = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 */
	public void pullChanges() throws de.fu.bakery.orm.java.DatabaseException {
		pullChanges(0);
	}
	
	/**
	 * @since Iteration4
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.bakery.orm.java.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseElement\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = false;
	}

	@Override
	public boolean exists() {
		return exists;
	}
	
	public int compareTo(CourseElement entity) {
		if (!exists) {
			if (this == entity) return 0;
			if (this.hashCode() <= entity.hashCode()) return -7;
			return 7;
		}
		if (entity == null) return -4711;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareValues(id(), entity.id());
	}
	
	public int compareTo(de.fu.bakery.orm.java.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof CourseElement) {
			return compareTo((CourseElement) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseElement entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof CourseElement) {
			return equals((CourseElement) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementRequiresFeature.
	 * <p>
	 * This is the subject of ElementRequiresFeature.
	 
	 * @return The object regarding ElementRequiresFeature where this (the java-object you call objectsOfElementRequiresFeature on) is the subject.
	 * @since Iteration3
	 */
	public Feature objectOfElementRequiresFeature() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Feature> coll = objectsOfElementRequiresFeature();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via ElementRequiresFeature. If you know that there will be only
	 * 1:1 relationships of the type ElementRequiresFeature, {@link #objectOfElementRequiresFeature} is what you are looking for.
	 * <p>
	 * This is the subject of ElementRequiresFeature.
	 * @return The object regarding ElementRequiresFeature where this (the java-object you call objectsOfElementRequiresFeature on) is the subject.
	 * @since Iteration3
	 */
	public java.util.List<Feature> objectsOfElementRequiresFeature() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\""
			+ " WHERE \"id\" IN (SELECT \"feature\" FROM "
			+ "\"scetris\".\"elementRequiresFeature\"" + " WHERE \"course_element\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Feature> ret = new java.util.ArrayList<Feature>();
			while (result.next()) {
				ret.add(new Feature(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<ElementRequiresFeature> whereSubjectOfElementRequiresFeature() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementRequiresFeature\""
			+ " WHERE \"course_element\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementRequiresFeature> ret = new java.util.ArrayList<ElementRequiresFeature>();
			while (result.next()) {
				ret.add(new ElementRequiresFeature(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "name", serial = true, nullable = true)
	public String getName() {
		if (_name == null) {
			return null;
		}
		return _name;
	}
	
	public boolean hasName() {
		return _name != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isNameChanged() {
		return changed_name;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("partOf")
	@de.fu.weave.xml.annotation.XmlDependency("isPartOfFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "part_of", serial = true, ref = Course.class)
	public Course getPartOf() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_partOf) {
			_partOf = manager.getCourse(ref_partOf);
			fetched_partOf = true;
		}
		return _partOf;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isPartOfFetched() {
		return fetched_partOf;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isPartOfChanged() {
		return changed_partOf;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("partOf")
	public int refPartOf() {
		return ref_partOf;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("duration")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "duration", serial = true)
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("type")
	@de.fu.weave.xml.annotation.XmlDependency("isTypeFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "type", serial = true, nullable = true, ref = CourseElementType.class)
	public CourseElementType getType() throws de.fu.bakery.orm.java.DatabaseException {
		if (ref_type == null) {
			return null;
		}
		if (!fetched_type) {
			_type = manager.getCourseElementType(ref_type);
			fetched_type = true;
		}
		return _type;
	}
	
	public boolean hasType() {
		return _type != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isTypeFetched() {
		return fetched_type;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTypeChanged() {
		return changed_type;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("type")
	public Integer refType() {
		return ref_type;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("required")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "required", serial = true)
	public boolean getRequired() {
		return _required;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isRequiredChanged() {
		return changed_required;
	}
	

	
	
	
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>name</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isNameChanged()} will return true. If you specify
	 * the same value as the old value isNameChanged() will return the same as it did
	 * before calling <code>setName(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public CourseElement setName(String value) {
		
		if (value == null) {
			clearName();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_name, value) == 0) return this;
		changed_name = true;
		_name = value;
		return this;
	}
	
	/**
	 * name may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public CourseElement clearName() {
		if (_name == null) {
			return this;
		}
		_name = null;
		changed_name = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>partOf</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isPartOfChanged()} will return true. If you specify
	 * the same value as the old value isPartOfChanged() will return the same as it did
	 * before calling <code>setPartOf(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>partOf</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseElement setPartOf(Course value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("partOf is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_partOf, value._id) == 0) return this;
		changed_partOf = true;
		ref_partOf = value._id;
		fetched_partOf = true;
		_partOf = value;
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
	public CourseElement setDuration(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("duration is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_duration, value) == 0) return this;
		changed_duration = true;
		_duration = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>type</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isTypeChanged()} will return true. If you specify
	 * the same value as the old value isTypeChanged() will return the same as it did
	 * before calling <code>setType(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public CourseElement setType(CourseElementType value) {
		
		if (value == null) {
			clearType();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_type, value._id) == 0) return this;
		changed_type = true;
		ref_type = value._id;
		fetched_type = true;
		_type = value;
		return this;
	}
	
	/**
	 * type may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public CourseElement clearType() {
		if (_type == null && ref_type == null) {
			return this;
		}
		_type = null;
		ref_type = null;
		changed_type = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>required</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isRequiredChanged()} will return true. If you specify
	 * the same value as the old value isRequiredChanged() will return the same as it did
	 * before calling <code>setRequired(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>required</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseElement setRequired(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("required is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_required, value) == 0) return this;
		changed_required = true;
		_required = value;
		return this;
	}

}
