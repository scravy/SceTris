/* ElementInstanceRequiresFeature.java / 2010-10-30+02:00
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
 * OO-Representation of the relationship-relation ElementInstanceRequiresFeature
 * <p>
 * 
			This relation tells us what kind of Feature is needed or whished by the Person which holds this particular CourseElementInstance.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "elementInstanceRequiresFeature", subject = CourseElementInstance.class, object = Feature.class)
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"elementInstanceRequiresFeature\"")
@de.fu.weave.xml.annotation.XmlElement("elementInstanceRequiresFeature")
public class ElementInstanceRequiresFeature extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	CourseElementInstance _elementInstance;
	boolean changed_elementInstance = false;
	boolean fetched_elementInstance = false;
	int ref_elementInstance;
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
	 * priority
	 * <p>
	 * 
	 * @since Iteration2
	 */
	int _priority;
	boolean changed_priority = false;
	boolean fetched_priority = false;
	
	/**
	 * minimum number of the feature required
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Integer _quantityMin = null;
	boolean changed_quantityMin = false;
	boolean fetched_quantityMin = false;
	
	/**
	 * number of features that would be better
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Integer _quantityBetter = null;
	boolean changed_quantityBetter = false;
	boolean fetched_quantityBetter = false;
	
	
	
	CourseElementInstance subject;
	int subject_refid;
	Feature object;
	int object_refid;
	
	
	ElementInstanceRequiresFeature(RelationManager manager, CourseElementInstance subject, Feature object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_priority = result.getInt("priority");
			fetched_priority = true;
			_quantityMin = result.getInt("quantity_min");
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			fetched_quantityBetter = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, CourseElementInstance subject, Feature object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
		this._priority = _priority;
		this.fetched_priority = true;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, boolean full, CourseElementInstance subject, Feature object, int _priority, Integer _quantityMin, Integer _quantityBetter) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._priority = _priority;
		this.fetched_priority = true;
		this._quantityMin = _quantityMin;
		this.fetched_quantityMin = true;
		this._quantityBetter = _quantityBetter;
		this.fetched_quantityBetter = true;
	}
	
	@Override
	public CourseElementInstance subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getCourseElementInstance(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Feature object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getFeature(object_refid);
		}
		return object;
	}
	
	@Deprecated
	public CourseElementInstance getSubject() throws de.fu.weave.orm.DatabaseException {
		return subject();
	}
	
	@Deprecated
	public Feature getObject() throws de.fu.weave.orm.DatabaseException {
		return object();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ElementInstanceRequiresFeature) {
			return equals((ElementInstanceRequiresFeature) obj);
		}
		return false;
	}
	
	public boolean equals(ElementInstanceRequiresFeature relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementInstanceRequiresFeature\"" + " (\"element_instance\", \"feature\", \"priority\", \"quantity_min\", \"quantity_better\")"
			+ " VALUES (?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, _priority);
			
			if (_quantityMin != null) {
				stmt.setInt(i++, _quantityMin);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_quantityBetter != null) {
				stmt.setInt(i++, _quantityBetter);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"elementInstanceRequiresFeature\"" + " SET \"priority\" = ?, \"quantity_min\" = ?, \"quantity_better\" = ? WHERE "
			+ " AND elementInstance = ?"
			+ " AND feature = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setInt(++i, _priority);
			if (_quantityMin != null) {
				stmt.setInt(++i, _quantityMin);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
			}
			if (_quantityBetter != null) {
				stmt.setInt(++i, _quantityBetter);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
			}
			stmt.setInt(++i, ref_elementInstance);
			stmt.setInt(++i, ref_feature);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"elementInstanceRequiresFeature\""
			+ " WHERE "
			+ " AND elementInstance = ?"
			+ " AND feature = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_elementInstance);
			stmt.setInt(++i, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public CourseElementInstance getElementInstance() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_elementInstance) {
			_elementInstance = manager.getCourseElementInstance(ref_elementInstance);
			fetched_elementInstance = true;
		}
		return _elementInstance;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedElementInstance() {
		return changed_elementInstance;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("elementInstance")
	public int refElementInstance() {
		return ref_elementInstance;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("priority")
	public int getPriority() {
		return _priority;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedPriority() {
		return changed_priority;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantityMin")
	public Integer getQuantityMin() {
		if (_quantityMin == null) {
			return null;
		}
		return _quantityMin;
	}
	
	public boolean hasQuantityMin() {
		return _quantityMin != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedQuantityMin() {
		return changed_quantityMin;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantityBetter")
	public Integer getQuantityBetter() {
		if (_quantityBetter == null) {
			return null;
		}
		return _quantityBetter;
	}
	
	public boolean hasQuantityBetter() {
		return _quantityBetter != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedQuantityBetter() {
		return changed_quantityBetter;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setElementInstance(CourseElementInstance value) {
		
		changed_elementInstance = true;
		ref_elementInstance = value._id;
		fetched_elementInstance = true;
		_elementInstance = value;
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
	public void setPriority(int value) {
		
		changed_priority = true;
		_priority = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setQuantityMin(Integer value) {
		if (value == null) {
			_quantityMin = null;
			return;
		}
		changed_quantityMin = true;
		_quantityMin = value;
	}
	
	/**
	 * quantityMin may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearQuantityMin() {
		_quantityMin = null;
		changed_quantityMin = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setQuantityBetter(Integer value) {
		if (value == null) {
			_quantityBetter = null;
			return;
		}
		changed_quantityBetter = true;
		_quantityBetter = value;
	}
	
	/**
	 * quantityBetter may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearQuantityBetter() {
		_quantityBetter = null;
		changed_quantityBetter = true;
	}

}
