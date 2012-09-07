/* PersonHasAttribute.java / 2010-10-22+02:00
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
 * OO-Representation of the relationship-relation PersonHasAttribute
 * <p>
 * 
			This relation tells us that the Person mentioned has the Attribute given.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "personHasAttribute", subject = Person.class, object = Attribute.class)
@de.fu.weave.xml.annotation.XmlElement("personHasAttribute")
public class PersonHasAttribute extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _user;
	boolean changed_user = false;
	boolean fetched_user = false;
	int ref_user;
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
	 * date of last edit
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _time;
	boolean changed_time = false;
	boolean fetched_time = false;
	
	/**
	 * initially created by
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
	
	
	
	Person subject;
	int subject_refid;
	Attribute object;
	int object_refid;
	
	
	PersonHasAttribute(RelationManager manager, Person subject, Attribute object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_user = result.getInt("user");
			ref_attribute = result.getInt("attribute");
			_time = result.getTimestamp("time");
			fetched_time = true;
			ref_editor = result.getInt("editor");
			_value = result.getString("value");
			fetched_value = true;
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
	
	PersonHasAttribute(RelationManager manager, Person subject, Attribute object, Person _editor) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
		this._editor = _editor;
		this.ref_editor = _editor.id();
		this.fetched_editor = true;
	}
	
	PersonHasAttribute(RelationManager manager, boolean full, Person subject, Attribute object, java.sql.Timestamp _time, Person _editor, String _value) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._time = _time;
		this.fetched_time = true;
		this._editor = _editor;
		this.ref_editor = _editor.id();
		this.fetched_editor = true;
		this._value = _value;
		this.fetched_value = true;
	}
	
	@Override
	public Person getSubject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Attribute getObject() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getAttribute(object_refid);
		}
		return object;
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personHasAttribute\"" + " (Person, Attribute, , \"time\", \"editor\", \"value\")"
			+ " VALUES (?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, subject.id());
			stmt.setInt(2, object.id());
			int i = 3;
				stmt.setTimestamp(i++, _time);
				stmt.setInt(i++, ref_editor);
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"personHasAttribute\"" + " SET \"time\" = ?, \"editor\" = ?, \"value\" = ? WHERE "
			+ " AND user = ?"
			+ " AND attribute = ?"
			+ " AND time = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setTimestamp(++i, _time);
				stmt.setInt(++i, ref_editor);
			if (_value != null) {
				stmt.setString(++i, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_attribute);
			stmt.setTimestamp(++i, _time);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personHasAttribute\""
			+ " WHERE "
			+ " AND user = ?"
			+ " AND attribute = ?"
			+ " AND time = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_attribute);
			stmt.setTimestamp(++i, _time);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public Person getUser() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_user) {
			_user = manager.getPerson(ref_user);
			fetched_user = true;
		}
		return _user;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedUser() {
		return changed_user;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("user")
	public int refUser() {
		return ref_user;
	}

	
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
	public boolean isChangedAttribute() {
		return changed_attribute;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("attribute")
	public int refAttribute() {
		return ref_attribute;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("time")
	public java.sql.Timestamp getTime() {
		return _time;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedTime() {
		return changed_time;
	}

	
	public Person getEditor() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_editor) {
			_editor = manager.getPerson(ref_editor);
			fetched_editor = true;
		}
		return _editor;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedEditor() {
		return changed_editor;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("editor")
	public int refEditor() {
		return ref_editor;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("value")
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
	public boolean isChangedValue() {
		return changed_value;
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
	public void setUser(Person value) {
		
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setAttribute(Attribute value) {
		
		changed_attribute = true;
		ref_attribute = value._id;
		fetched_attribute = true;
		_attribute = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTime(java.sql.Timestamp value) {
		
		changed_time = true;
		_time = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setEditor(Person value) {
		
		changed_editor = true;
		ref_editor = value._id;
		fetched_editor = true;
		_editor = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setValue(String value) {
		if (value == null) {
			_value = null;
			return;
		}
		changed_value = true;
		_value = value;
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
