/* Building.java / 2010-11-04+01:00
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
 * OO-Representation of the entity-relation Building
 * <p>
 * 
A Building> has a unique street address and belongs to a Department. Some Buildings have a special name (“Henry-Ford-Building”) which is unique across the whole university.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Building")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Building\"")
@de.fu.weave.xml.annotation.XmlElement("Building")
public class Building extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	 * name of the building
	 * <p>
	 * 
				The name of Building has to be unique so it identifies the Building.
			
	 * @since Iteration2
	 */
	String _name = null;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * street adress
	 * <p>
	 * 
				The address of the Building has to be unique.
			
	 * @since Iteration2
	 */
	String _address;
	boolean changed_address = false;
	boolean fetched_address = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Building(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_name = result.getString("name");
			fetched_name = true;
			_address = result.getString("address");
			fetched_address = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity Building and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @since Iteration2
	 */
	Building(RelationManager manager, String _address) {
		this.manager = manager;
		this._address = _address;
		this.fetched_address = true;
	}
	
	/**
	 * Constructs the entity Building and initializes all attributes.
	 * @param _name 
				The name of Building has to be unique so it identifies the Building.
			
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @since Iteration2
	 */
	Building(RelationManager manager, final boolean full, String _name, String _address) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._address = _address;
			this.fetched_address = true;
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
		return "Building: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newBuilding} rather than {@link RelationManager#createBuilding).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Building\"" + " (\"name\" , \"address\" )"
			+ " VALUES (?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (_name != null) {
				stmt.setString(i++, _name);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _address);
			
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
		String query = "UPDATE " + "\"scetris\".\"Building\"" + " SET \"name\" = ?, \"address\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (_name != null) {
				stmt.setString(i++, _name);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _address);
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Building\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Building entity) {
		if (entity == null) return -1;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof Building) {
			return compareTo((Building) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Building entity) {
		if (entity == null) return false;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Building) {
			return equals((Building) obj);
		}
		return false;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("address")
	@de.fu.weave.orm.annotation.Attribute(name = "address", serial = true)
	public String getAddress() {
		return _address;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isAddressChanged() {
		return changed_address;
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
	public void setAddress(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("address is not nullable, null given");
		}
		changed_address = true;
		_address = value;
	}

}
