/* ElementRequiresFeature.java / 2010-10-22+02:00
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
 * OO-Representation of the relationship-relation ElementRequiresFeature
 * <p>
 * 
			This relation tells us what kind and amount of Feature is required in order to be held.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "elementRequiresFeature", subject = CourseElement.class, object = Feature.class)
@de.fu.weave.xml.annotation.XmlElement("elementRequiresFeature")
public class ElementRequiresFeature extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	CourseElement _courseElement;
	boolean changed_courseElement = false;
	boolean fetched_courseElement = false;
	int ref_courseElement;
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
	
	
	
	CourseElement subject;
	int subject_refid;
	Feature object;
	int object_refid;
	
	
	ElementRequiresFeature(RelationManager manager, CourseElement subject, Feature object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_courseElement = result.getInt("course_element");
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
	
	ElementRequiresFeature(RelationManager manager, CourseElement subject, Feature object, int _priority) {
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
	
	ElementRequiresFeature(RelationManager manager, boolean full, CourseElement subject, Feature object, int _priority, Integer _quantityMin, Integer _quantityBetter) {
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
	public CourseElement getSubject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getCourseElement(subject_refid);
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
		String query = "INSERT INTO " + "\"scetris\".\"elementRequiresFeature\"" + " (CourseElement, Feature, , \"priority\", \"quantity_min\", \"quantity_better\")"
			+ " VALUES (?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, subject.id());
			stmt.setInt(2, object.id());
			int i = 3;
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
		String query = "UPDATE " + "\"scetris\".\"elementRequiresFeature\"" + " SET \"priority\" = ?, \"quantity_min\" = ?, \"quantity_better\" = ? WHERE "
			+ " AND courseElement = ?"
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
			stmt.setInt(++i, ref_courseElement);
			stmt.setInt(++i, ref_feature);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"elementRequiresFeature\""
			+ " WHERE "
			+ " AND courseElement = ?"
			+ " AND feature = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_courseElement);
			stmt.setInt(++i, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
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
	public boolean isChangedCourseElement() {
		return changed_courseElement;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("courseElement")
	public int refCourseElement() {
		return ref_courseElement;
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
	public void setCourseElement(CourseElement value) {
		
		changed_courseElement = true;
		ref_courseElement = value._id;
		fetched_courseElement = true;
		_courseElement = value;
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

}