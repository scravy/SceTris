/* ElementInstanceRequiresFeature.java / 2011-03-03+01:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

/**
 * @formatter:off
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
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"elementInstanceRequiresFeature\"", requiredSqlCols = {"quantity_min", "quantity_better"})
@de.fu.weave.xml.annotation.XmlElement("elementInstanceRequiresFeature")
public class ElementInstanceRequiresFeature extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String ElementInstance = "element_instance";
	public static final String Feature = "feature";
	public static final String Timekey = "timekey";
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
	 * minimum number of the feature required
	 * <p>
	 * 
	 * @since Iteration2
	 */
	int _quantityMin;
	boolean changed_quantityMin = false;
	boolean fetched_quantityMin = false;
	
	/**
	 * number of features that would be better
	 * <p>
	 * 
	 * @since Iteration2
	 */
	int _quantityBetter;
	boolean changed_quantityBetter = false;
	boolean fetched_quantityBetter = false;
	
	
	
	CourseElementInstance subject;
	int subject_refid;
	Feature object;
	int object_refid;
	
	
	ElementInstanceRequiresFeature(RelationManager manager, CourseElementInstance subject, Feature object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_quantityMin = result.getInt("quantity_min");
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			fetched_quantityBetter = true;	
			this.subject_refid = ref_elementInstance;
			this.object_refid = ref_feature;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			this.ref_elementInstance = result.getInt("\"element_instance\"");
			this.ref_feature = result.getInt("\"feature\"");
			this._timekey = result.getTimestamp("\"timekey\"");
			this.fetched_timekey = true;
			this._quantityMin = result.getInt("\"quantity_min\"");
			this.fetched_quantityMin = true;
			this._quantityBetter = result.getInt("\"quantity_better\"");
			this.fetched_quantityBetter = true;
			this.subject_refid = ref_elementInstance;
			this.object_refid = ref_feature;
			this.subject = manager.getCourseElementInstance(this.subject_refid);
			this.object = manager.getFeature(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.exists = true;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, CourseElementInstance subject, Feature object, int _quantityMin, int _quantityBetter) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._elementInstance = this.subject = subject;
		this.ref_elementInstance = this.subject_refid = subject.id();
		this._feature = this.object = object;
		this.ref_feature = this.object_refid = object.id();
		timekey(true);
		
		this._quantityMin = _quantityMin;
		this.fetched_quantityMin = true;
		this._quantityBetter = _quantityBetter;
		this.fetched_quantityBetter = true;
	}
	
	ElementInstanceRequiresFeature(RelationManager manager, boolean full, CourseElementInstance subject, Feature object, int _quantityMin, int _quantityBetter) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
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
	public ElementInstanceRequiresFeature create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementInstanceRequiresFeature\"" + " (\"element_instance\", \"feature\","
			+ "\"quantity_min\","
			+ "\"quantity_better\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, _quantityMin);
			
				stmt.setInt(i++, _quantityBetter);
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"elementInstanceRequiresFeature\"" + " SET \"quantity_min\" = ?, "
			+ "\"quantity_better\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"element_instance\" = ?"
			+ " AND \"feature\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _quantityMin);
				stmt.setInt(i++, _quantityBetter);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_feature);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"elementInstanceRequiresFeature\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"elementInstanceRequiresFeature\""
			+ " WHERE "
			+ " \"element_instance\" = ? AND "
			+ " \"feature\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("elementInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isElementInstanceFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "element_instance", use = "subject", ref = CourseElementInstance.class)
	public CourseElementInstance getElementInstance() throws de.fu.weave.orm.DatabaseException {
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
	@de.fu.weave.orm.annotation.Attribute(name = "feature", use = "object", ref = Feature.class)
	public Feature getFeature() throws de.fu.weave.orm.DatabaseException {
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
	
	public java.sql.Timestamp timekey() {
		return _timekey;
	}
	
	public boolean isValidTimekey(java.sql.Timestamp key) {
		return de.fu.weave.orm.GenericRelationManager.compareValues(_timekey, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.weave.orm.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.weave.orm.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantityMin")
	@de.fu.weave.orm.annotation.Attribute(name = "quantity_min", serial = true)
	public int getQuantityMin() {
		return _quantityMin;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isQuantityMinChanged() {
		return changed_quantityMin;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("quantityBetter")
	@de.fu.weave.orm.annotation.Attribute(name = "quantity_better", serial = true)
	public int getQuantityBetter() {
		return _quantityBetter;
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
	public ElementInstanceRequiresFeature setElementInstance(CourseElementInstance value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("elementInstance is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_elementInstance, value._id) == 0) return this;
		changed_elementInstance = true;
		ref_elementInstance = value._id;
		fetched_elementInstance = true;
		_elementInstance = value;
		return this;
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
	public ElementInstanceRequiresFeature setFeature(Feature value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("feature is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_feature, value._id) == 0) return this;
		changed_feature = true;
		ref_feature = value._id;
		fetched_feature = true;
		_feature = value;
		return this;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>quantityMin</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isQuantityMinChanged()} will return true. If you specify
	 * the same value as the old value isQuantityMinChanged() will return the same as it did
	 * before calling <code>setQuantityMin(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>quantityMin</code> is not nullable)
	 * @since Iteration2
	 */
	public ElementInstanceRequiresFeature setQuantityMin(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("quantityMin is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_quantityMin, value) == 0) return this;
		changed_quantityMin = true;
		_quantityMin = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>quantityBetter</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isQuantityBetterChanged()} will return true. If you specify
	 * the same value as the old value isQuantityBetterChanged() will return the same as it did
	 * before calling <code>setQuantityBetter(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>quantityBetter</code> is not nullable)
	 * @since Iteration2
	 */
	public ElementInstanceRequiresFeature setQuantityBetter(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("quantityBetter is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_quantityBetter, value) == 0) return this;
		changed_quantityBetter = true;
		_quantityBetter = value;
		return this;
	}

}
