/* RoomProvidesFeature.java / 2010-10-22+02:00
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
 * OO-Representation of the relationship-relation RoomProvidesFeature
 * <p>
 * 
			This relation tells us which Feature is provided by this room.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "roomProvidesFeature", subject = Room.class, object = Feature.class)
@de.fu.weave.xml.annotation.XmlElement("roomProvidesFeature")
public class RoomProvidesFeature extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Room _room;
	boolean changed_room = false;
	boolean fetched_room = false;
	int ref_room;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Feature _feature;
	boolean changed_feature = false;
	boolean fetched_feature = false;
	int ref_feature;
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
	 * quantity
	 * <p>
	 * 
				The quantity in which the Room provides the Feature of question.
			
	 * @since Iteration2
	 */
	int _quantity;
	boolean changed_quantity = false;
	boolean fetched_quantity = false;
	
	
	
	Room subject;
	int subject_refid;
	Feature object;
	int object_refid;
	
	
	RoomProvidesFeature(RelationManager manager, Room subject, Feature object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_room = result.getInt("room");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_quantity = result.getInt("quantity");
			fetched_quantity = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	RoomProvidesFeature(RelationManager manager, Room subject, Feature object, int _quantity) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
		this._quantity = _quantity;
		this.fetched_quantity = true;
	}
	
	RoomProvidesFeature(RelationManager manager, boolean full, Room subject, Feature object, int _quantity) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._quantity = _quantity;
		this.fetched_quantity = true;
	}
	
	@Override
	public Room getSubject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getRoom(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Feature getObject() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getFeature(object_refid);
		}
		return object;
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"roomProvidesFeature\"" + " (Room, Feature, , \"quantity\")"
			+ " VALUES (?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, subject.id());
			stmt.setInt(2, object.id());
			int i = 3;
				stmt.setInt(i++, _quantity);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"roomProvidesFeature\"" + " SET \"quantity\" = ? WHERE "
			+ " AND room = ?"
			+ " AND feature = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setInt(++i, _quantity);
			stmt.setInt(++i, ref_room);
			stmt.setInt(++i, ref_feature);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"roomProvidesFeature\""
			+ " WHERE "
			+ " AND room = ?"
			+ " AND feature = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_room);
			stmt.setInt(++i, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public Room getRoom() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_room) {
			_room = manager.getRoom(ref_room);
			fetched_room = true;
		}
		return _room;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedRoom() {
		return changed_room;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("room")
	public int refRoom() {
		return ref_room;
	}

	
	public Feature getFeature() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_feature) {
			_feature = manager.getFeature(ref_feature);
			fetched_feature = true;
		}
		return _feature;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedFeature() {
		return changed_feature;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("feature")
	public int refFeature() {
		return ref_feature;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantity")
	public int getQuantity() {
		return _quantity;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedQuantity() {
		return changed_quantity;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setRoom(Room value) {
		
		changed_room = true;
		ref_room = value._id;
		fetched_room = true;
		_room = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setFeature(Feature value) {
		
		changed_feature = true;
		ref_feature = value._id;
		fetched_feature = true;
		_feature = value;
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
	public void setQuantity(int value) {
		
		changed_quantity = true;
		_quantity = value;
	}

}
