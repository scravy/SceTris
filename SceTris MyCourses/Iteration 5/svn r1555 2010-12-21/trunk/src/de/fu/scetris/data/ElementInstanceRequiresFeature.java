/* ElementInstanceRequiresFeature.java / 2010-12-20+01:00
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
@de.fu.bakery.orm.java.annotation.Relationship(name = "elementInstanceRequiresFeature", subject = CourseElementInstance.class, object = Feature.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"elementInstanceRequiresFeature\"", requiredSqlCols = {"priority"})
@de.fu.weave.xml.annotation.XmlElement("elementInstanceRequiresFeature")
public class ElementInstanceRequiresFeature extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String ElementInstance = "element_instance";
	public static final String Feature = "feature";
	public static final String Timekey = "timekey";
	public static final String Priority = "priority";
	public static final String QuantityMin = "quantity_min";
	public static final String QuantityBetter = "quantity_better";
	

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
	
	
	ElementInstanceRequiresFeature(RelationManager manager, CourseElementInstance subject, Feature object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_priority = result.getInt("priority");
			fetched_priority = true;
			_quantityMin = result.getInt("quantity_min");
			if (result.wasNull()) {
				_quantityMin = null;
			}
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			if (result.wasNull()) {
				_quantityBetter = null;
			}
			fetched_quantityBetter = true;	
			this.subject_refid = ref_elementInstance;
			this.object_refid = ref_feature;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_priority = result.getInt("priority");
			fetched_priority = true;
			_quantityMin = result.getInt("quantity_min");
			if (result.wasNull()) {
				_quantityMin = null;
			}
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			if (result.wasNull()) {
				_quantityBetter = null;
			}
			fetched_quantityBetter = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"element_instance\"");
			this.object_refid = result.getInt("\"feature\"");
			this.subject = manager.getCourseElementInstance(this.subject_refid);
			this.object = manager.getFeature(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, CourseElementInstance subject, Feature object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._elementInstance = this.subject = subject;
		this.ref_elementInstance = this.subject_refid = subject.id();
		this._feature = this.object = object;
		this.ref_feature = this.object_refid = object.id();
		timekey(true);
		
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
		timekey(true);
		
		this._priority = _priority;
		this.fetched_priority = true;
		this._quantityMin = _quantityMin;
		this.fetched_quantityMin = true;
		this._quantityBetter = _quantityBetter;
		this.fetched_quantityBetter = true;
	}
	
	@Override
	public CourseElementInstance subject() throws de.fu.bakery.orm.java.DatabaseException {
		if (subject == null) {
			subject = manager.getCourseElementInstance(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Feature object() throws de.fu.bakery.orm.java.DatabaseException {
		if (object == null) {
			object = manager.getFeature(object_refid);
		}
		return object;
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
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementInstanceRequiresFeature\"" + " (\"element_instance\", \"feature\","
			+ "\"priority\","
			+ "\"quantity_min\","
			+ "\"quantity_better\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?);";
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
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.bakery.orm.java.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"elementInstanceRequiresFeature\"" + " SET \"priority\" = ?, "
			+ "\"quantity_min\" = ?, "
			+ "\"quantity_better\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"element_instance\" = ?"
			+ " AND \"feature\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
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
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_feature);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException("\"scetris\".\"elementInstanceRequiresFeature\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"elementInstanceRequiresFeature\""
			+ " WHERE "
			+ " \"element_instance\" = ? AND "
			+ " \"feature\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("elementInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isElementInstanceFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "element_instance", use = "subject", ref = CourseElementInstance.class)
	public CourseElementInstance getElementInstance() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_elementInstance) {
			_elementInstance = manager.getCourseElementInstance(ref_elementInstance);
			fetched_elementInstance = true;
		}
		return _elementInstance;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isElementInstanceFetched() {
		return fetched_elementInstance;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isElementInstanceChanged() {
		return changed_elementInstance;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("elementInstance")
	public int refElementInstance() {
		return ref_elementInstance;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("feature")
	@de.fu.weave.xml.annotation.XmlDependency("isFeatureFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "feature", use = "object", ref = Feature.class)
	public Feature getFeature() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_feature) {
			_feature = manager.getFeature(ref_feature);
			fetched_feature = true;
		}
		return _feature;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isFeatureFetched() {
		return fetched_feature;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isFeatureChanged() {
		return changed_feature;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("feature")
	public int refFeature() {
		return ref_feature;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("priority")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "priority", serial = true)
	public int getPriority() {
		return _priority;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isPriorityChanged() {
		return changed_priority;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantityMin")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "quantity_min", serial = true, nullable = true)
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
	public boolean isQuantityMinChanged() {
		return changed_quantityMin;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantityBetter")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "quantity_better", serial = true, nullable = true)
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
	public boolean isQuantityBetterChanged() {
		return changed_quantityBetter;
	}
	

	
	
	
	/**
	 * Sets the value of <code>elementInstance</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isElementInstanceChanged()} will return true. If you specify
	 * the same value as the old value isElementInstanceChanged() will return the same as it did
	 * before calling <code>setElementInstance(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>elementInstance</code> is not nullable)
	 * @since Iteration2
	 */
	public void setElementInstance(CourseElementInstance value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("elementInstance is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_elementInstance, value._id) == 0) return;
		changed_elementInstance = true;
		ref_elementInstance = value._id;
		fetched_elementInstance = true;
		_elementInstance = value;
	}

	
	/**
	 * Sets the value of <code>feature</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isFeatureChanged()} will return true. If you specify
	 * the same value as the old value isFeatureChanged() will return the same as it did
	 * before calling <code>setFeature(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>feature</code> is not nullable)
	 * @since Iteration2
	 */
	public void setFeature(Feature value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("feature is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_feature, value._id) == 0) return;
		changed_feature = true;
		ref_feature = value._id;
		fetched_feature = true;
		_feature = value;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>priority</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isPriorityChanged()} will return true. If you specify
	 * the same value as the old value isPriorityChanged() will return the same as it did
	 * before calling <code>setPriority(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>priority</code> is not nullable)
	 * @since Iteration2
	 */
	public void setPriority(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("priority is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_priority, value) == 0) return;
		changed_priority = true;
		_priority = value;
	}

	
	/**
	 * Sets the value of <code>quantityMin</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isQuantityMinChanged()} will return true. If you specify
	 * the same value as the old value isQuantityMinChanged() will return the same as it did
	 * before calling <code>setQuantityMin(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setQuantityMin(Integer value) {
		
		if (value == null) {
			clearQuantityMin();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_quantityMin, value) == 0) return;
		changed_quantityMin = true;
		_quantityMin = value;
	}
	
	/**
	 * quantityMin may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearQuantityMin() {
		if (_quantityMin == null) {
			return;
		}
		_quantityMin = null;
		changed_quantityMin = true;
	}

	
	/**
	 * Sets the value of <code>quantityBetter</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isQuantityBetterChanged()} will return true. If you specify
	 * the same value as the old value isQuantityBetterChanged() will return the same as it did
	 * before calling <code>setQuantityBetter(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setQuantityBetter(Integer value) {
		
		if (value == null) {
			clearQuantityBetter();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_quantityBetter, value) == 0) return;
		changed_quantityBetter = true;
		_quantityBetter = value;
	}
	
	/**
	 * quantityBetter may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearQuantityBetter() {
		if (_quantityBetter == null) {
			return;
		}
		_quantityBetter = null;
		changed_quantityBetter = true;
	}

}