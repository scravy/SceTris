/* CourseElement.java / 2010-11-04+01:00
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
@de.fu.weave.orm.annotation.Entity(name = "CourseElement")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"CourseElement\"")
@de.fu.weave.xml.annotation.XmlElement("CourseElement")
public class CourseElement extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	 * name of the course element
	 * <p>
	 * 
				The name of the Course Element.
			
	 * @since Iteration2
	 */
	String _name = null;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * part of
	 * <p>
	 * 
				The Course this Course Elements belongs to.
			
	 * @since Iteration2
	 */
	Course _partOf;
	boolean changed_partOf = false;
	boolean fetched_partOf = false;
	int ref_partOf;/**
	 * duration
	 * <p>
	 * 
				The duration of the Course Element.
			
	 * @since Iteration2
	 */
	int _duration;
	boolean changed_duration = false;
	boolean fetched_duration = false;/**
	 * type of lessons
	 * <p>
	 * 
				The type of the Course Element.
			
	 * @since Iteration2
	 */
	CourseElementType _type = null;
	boolean changed_type = false;
	boolean fetched_type = false;
	Integer ref_type;/**
	 * obligatory
	 * <p>
	 * 
				Whether this Course Element is obligatory or not.
			
	 * @since Iteration2
	 */
	boolean _required;
	boolean changed_required = false;
	boolean fetched_required = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	CourseElement(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_name = result.getString("name");
			fetched_name = true;
			ref_partOf = result.getInt("part_of");
			_duration = result.getInt("duration");
			fetched_duration = true;
			ref_type = result.getInt("type");
			_required = result.getBoolean("required");
			fetched_required = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._partOf = _partOf;
			this.ref_partOf = _partOf.id();
			this.fetched_partOf = true;
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
		return "CourseElement: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseElement} rather than {@link RelationManager#createCourseElement).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseElement\"" + " (\"name\" , \"part_of\" , \"duration\" , \"type\" , \"required\" )"
			+ " VALUES (?, ?, ?, ?, ?) RETURNING id;";
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
		String query = "UPDATE " + "\"scetris\".\"CourseElement\"" + " SET \"name\" = ?, \"part_of\" = ?, \"duration\" = ?, \"type\" = ?, \"required\" = ? WHERE id = ?;";
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
		String query = "DELETE FROM " + "\"scetris\".\"CourseElement\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(CourseElement entity) {
		if (entity == null) return -1;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof CourseElement) {
			return compareTo((CourseElement) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseElement entity) {
		if (entity == null) return false;
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
	public Feature objectOfElementRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Feature> coll = objectsOfElementRequiresFeature();
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
	public java.util.Collection<Feature> objectsOfElementRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\""
			+ " WHERE id IN (SELECT feature FROM "
			+ "\"scetris\".\"elementRequiresFeature\"" + " WHERE course_element = ?);";
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
	public java.util.Collection<ElementRequiresFeature> whereSubjectOfElementRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementRequiresFeature\""
			+ " WHERE \"course_element\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementRequiresFeature> ret = new java.util.ArrayList<ElementRequiresFeature>();
			while (result.next()) {
				ret.add(new ElementRequiresFeature(manager, this, null, result));
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	@de.fu.weave.orm.annotation.Attribute(name = "name", serial = true, nullable = true)
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

	
	@de.fu.weave.orm.annotation.Attribute(name = "part_of", serial = true, ref = Course.class)
	public Course getPartOf() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_partOf) {
			_partOf = manager.getCourse(ref_partOf);
			fetched_partOf = true;
		}
		return _partOf;
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

	
	@de.fu.weave.orm.annotation.Attribute(name = "type", serial = true, nullable = true, ref = CourseElementType.class)
	public CourseElementType getType() throws de.fu.weave.orm.DatabaseException {
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
	@de.fu.weave.orm.annotation.Attribute(name = "required", serial = true)
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
	public void setName(String value) {
		if (value == null) {
			_name = null;
			return;
		}
		
		changed_name = true;
		_name = value;
	}
	
	/**
	 * name may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearName() {
		_name = null;
		changed_name = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setPartOf(Course value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("partOf is not nullable, null given");
		}
		changed_partOf = true;
		ref_partOf = value._id;
		fetched_partOf = true;
		_partOf = value;
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
	public void setType(CourseElementType value) {
		if (value == null) {
			_type = null;
			ref_type = null;
			return;
		}
		
		changed_type = true;
		ref_type = value._id;
		fetched_type = true;
		_type = value;
	}
	
	/**
	 * type may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearType() {
		_type = null;
		ref_type = null;
		changed_type = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setRequired(boolean value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("required is not nullable, null given");
		}
		changed_required = true;
		_required = value;
	}

}
