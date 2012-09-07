/* RoleImpliesAttribute.java / 2010-11-04+01:00
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
 * OO-Representation of the relationship-relation RoleImpliesAttribute
 * <p>
 * 
			This relation keeps track of user-defined Attributes that are implied by a role.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "roleImpliesAttribute", subject = Role.class, object = Attribute.class)
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"roleImpliesAttribute\"")
@de.fu.weave.xml.annotation.XmlElement("roleImpliesAttribute")
public class RoleImpliesAttribute extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Role _role;
	boolean changed_role = false;
	boolean fetched_role = false;
	int ref_role;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Attribute _attribute;
	boolean changed_attribute = false;
	boolean fetched_attribute = false;
	int ref_attribute;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	/**
	 * default
	 * <p>
	 * 
				Describes whether or not this attribute is set by default.
			
	 * @since Iteration2
	 */
	String _default = null;
	boolean changed_default = false;
	boolean fetched_default = false;
	
	/**
	 * required
	 * <p>
	 * 
				Whether or not this Attribute is required or not.
			
	 * @since Iteration2
	 */
	boolean _required;
	boolean changed_required = false;
	boolean fetched_required = false;
	
	
	
	Role subject;
	int subject_refid;
	Attribute object;
	int object_refid;
	
	
	RoleImpliesAttribute(RelationManager manager, Role subject, Attribute object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_role = result.getInt("role");
			ref_attribute = result.getInt("attribute");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_default = result.getString("default");
			fetched_default = true;
			_required = result.getBoolean("required");
			fetched_required = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	RoleImpliesAttribute(RelationManager manager, Role subject, Attribute object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
	}
	
	RoleImpliesAttribute(RelationManager manager, boolean full, Role subject, Attribute object, String _default, boolean _required) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._default = _default;
		this.fetched_default = true;
		this._required = _required;
		this.fetched_required = true;
	}
	
	@Override
	public Role subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getRole(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Attribute object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getAttribute(object_refid);
		}
		return object;
	}
	
	@Deprecated
	public Role getSubject() throws de.fu.weave.orm.DatabaseException {
		return subject();
	}
	
	@Deprecated
	public Attribute getObject() throws de.fu.weave.orm.DatabaseException {
		return object();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleImpliesAttribute) {
			return equals((RoleImpliesAttribute) obj);
		}
		return false;
	}
	
	public boolean equals(RoleImpliesAttribute relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"roleImpliesAttribute\"" + " (\"role\", \"attribute\", \"default\", \"required\")"
			+ " VALUES (?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
			if (_default != null) {
				stmt.setString(i++, _default);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _required);
			}
			
			
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
	
		String query = "UPDATE " + "\"scetris\".\"roleImpliesAttribute\"" + " SET \"default\" = ?, \"required\" = ? WHERE "
			+ " \"role\" = ?  AND "
			+ " \"attribute\" = ? ";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			if (_default != null) {
				stmt.setString(++i, _default);
			} else {
				stmt.setNull(++i, java.sql.Types.VARCHAR);
			}
				stmt.setBoolean(++i, _required);
			stmt.setInt(++i, ref_role);
			stmt.setInt(++i, ref_attribute);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"roleImpliesAttribute\""
			+ " WHERE "
			+ " \"role\" = ? AND "
			+ " \"attribute\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_role);
			stmt.setInt(++i, ref_attribute);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	@de.fu.weave.orm.annotation.Attribute(name = "role", use = "subject", ref = Role.class)
	public Role getRole() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_role) {
			_role = manager.getRole(ref_role);
			fetched_role = true;
		}
		return _role;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isRoleChanged() {
		return changed_role;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("role")
	public int refRole() {
		return ref_role;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "attribute", use = "object", ref = Attribute.class)
	public Attribute getAttribute() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_attribute) {
			_attribute = manager.getAttribute(ref_attribute);
			fetched_attribute = true;
		}
		return _attribute;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("default")
	@de.fu.weave.orm.annotation.Attribute(name = "default", serial = true, nullable = true)
	public String getDefault() {
		if (_default == null) {
			return null;
		}
		return _default;
	}
	
	public boolean hasDefault() {
		return _default != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDefaultChanged() {
		return changed_default;
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
	public void setRole(Role value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("role is not nullable, null given");
		}
		changed_role = true;
		ref_role = value._id;
		fetched_role = true;
		_role = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setAttribute(Attribute value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("attribute is not nullable, null given");
		}
		changed_attribute = true;
		ref_attribute = value._id;
		fetched_attribute = true;
		_attribute = value;
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
	public void setDefault(String value) {
		if (value == null) {
			_default = null;
			return;
		}
		
		changed_default = true;
		_default = value;
	}
	
	/**
	 * default may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearDefault() {
		_default = null;
		changed_default = true;
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
