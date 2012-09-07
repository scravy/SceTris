/* AcademicTerm.java / 2010-10-22+02:00
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
 * OO-Representation of the entity-relation AcademicTerm
 * <p>
 * 
			All Programs happen in a Period. A Period is has a start and end date. No start date of a Program may be set before the periods start Program and no end date of Program may be set after the Periods end date. One can say: A period is the program of the university, where the university consists of all departments.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "AcademicTerm")
@de.fu.weave.xml.annotation.XmlElement("AcademicTerm")
public class AcademicTerm extends de.fu.weave.orm.GenericEntity implements Comparable<de.fu.weave.orm.Entity> {
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
	 * unique name
	 * <p>
	 * 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	String _name;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * start date
	 * <p>
	 * 
				The date at which the Academic turn begins.
			
	 * @since Iteration2
	 */
	java.sql.Date _start;
	boolean changed_start = false;
	boolean fetched_start = false;/**
	 * end date
	 * <p>
	 * 
				The date at which the Academic turn ends.
			
	 * @since Iteration2
	 */
	java.sql.Date _end;
	boolean changed_end = false;
	boolean fetched_end = false;/**
	 * courses starting date
	 * <p>
	 * 
				The date of the first Course Element Instance taking place.
			
	 * @since Iteration2
	 */
	java.sql.Date _startLessons;
	boolean changed_startLessons = false;
	boolean fetched_startLessons = false;/**
	 * courses ending date
	 * <p>
	 * 
				The date of the last Course Element Instance taking place.
			
	 * @since Iteration2
	 */
	java.sql.Date _endLessons;
	boolean changed_endLessons = false;
	boolean fetched_endLessons = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	AcademicTerm(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_start = result.getDate("start");
			fetched_start = true;
			_end = result.getDate("end");
			fetched_end = true;
			_startLessons = result.getDate("start_lessons");
			fetched_startLessons = true;
			_endLessons = result.getDate("end_lessons");
			fetched_endLessons = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity AcademicTerm and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
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
			
	 * @since Iteration2
	 */
	AcademicTerm(RelationManager manager, String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) {
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
		this._start = _start;
		this.fetched_start = true;
		this._end = _end;
		this.fetched_end = true;
		this._startLessons = _startLessons;
		this.fetched_startLessons = true;
		this._endLessons = _endLessons;
		this.fetched_endLessons = true;
	}
	
	/**
	 * Constructs the entity AcademicTerm and initializes all attributes.
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
			
	 * @since Iteration2
	 */
	AcademicTerm(RelationManager manager, final boolean full, String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._start = _start;
			this.fetched_start = true;
			this._end = _end;
			this.fetched_end = true;
			this._startLessons = _startLessons;
			this.fetched_startLessons = true;
			this._endLessons = _endLessons;
			this.fetched_endLessons = true;
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
		return "AcademicTerm: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newAcademicTerm} rather than {@link RelationManager#createAcademicTerm).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"AcademicTerm\"" + " (\"name\" , \"start\" , \"end\" , \"start_lessons\" , \"end_lessons\" )"
			+ " VALUES (?, ?, ?, ?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
				stmt.setDate(i++, _start);
				stmt.setDate(i++, _end);
				stmt.setDate(i++, _startLessons);
				stmt.setDate(i++, _endLessons);
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
		String query = "UPDATE " + "\"scetris\".\"AcademicTerm\"" + " SET \"name\" = ?, \"start\" = ?, \"end\" = ?, \"start_lessons\" = ?, \"end_lessons\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
				stmt.setDate(i++, _start);
				stmt.setDate(i++, _end);
				stmt.setDate(i++, _startLessons);
				stmt.setDate(i++, _endLessons);
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
		String query = "DELETE FROM " + "\"scetris\".\"AcademicTerm\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(AcademicTerm entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof AcademicTerm) {
			return compareTo((AcademicTerm) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(AcademicTerm entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	public int getId() {
		return _id;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedId() {
		return changed_id;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	public String getName() {
		return _name;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedName() {
		return changed_name;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("start")
	public java.sql.Date getStart() {
		return _start;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedStart() {
		return changed_start;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("end")
	public java.sql.Date getEnd() {
		return _end;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedEnd() {
		return changed_end;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("startLessons")
	public java.sql.Date getStartLessons() {
		return _startLessons;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedStartLessons() {
		return changed_startLessons;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("endLessons")
	public java.sql.Date getEndLessons() {
		return _endLessons;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedEndLessons() {
		return changed_endLessons;
	}

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setId(int value) {
		
		changed_id = true;
		_id = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setName(String value) {
		
		changed_name = true;
		_name = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setStart(java.sql.Date value) {
		
		changed_start = true;
		_start = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setEnd(java.sql.Date value) {
		
		changed_end = true;
		_end = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setStartLessons(java.sql.Date value) {
		
		changed_startLessons = true;
		_startLessons = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setEndLessons(java.sql.Date value) {
		
		changed_endLessons = true;
		_endLessons = value;
	}

}
