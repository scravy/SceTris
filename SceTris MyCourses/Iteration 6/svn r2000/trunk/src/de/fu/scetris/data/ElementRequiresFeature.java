/* ElementRequiresFeature.java / 2011-02-01+01:00
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
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"elementRequiresFeature\"", requiredSqlCols = {"quantity_min", "quantity_better"})
@de.fu.weave.xml.annotation.XmlElement("elementRequiresFeature")
public class ElementRequiresFeature extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String CourseElement = "course_element";
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
	
	
	
	CourseElement subject;
	int subject_refid;
	Feature object;
	int object_refid;
	
	
	ElementRequiresFeature(RelationManager manager, CourseElement subject, Feature object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_courseElement = result.getInt("course_element");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_quantityMin = result.getInt("quantity_min");
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			fetched_quantityBetter = true;	
			this.subject_refid = ref_courseElement;
			this.object_refid = ref_feature;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	ElementRequiresFeature(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_courseElement = result.getInt("course_element");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_quantityMin = result.getInt("quantity_min");
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			fetched_quantityBetter = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"course_element\"");
			this.object_refid = result.getInt("\"feature\"");
			this.subject = manager.getCourseElement(this.subject_refid);
			this.object = manager.getFeature(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	ElementRequiresFeature(RelationManager manager, CourseElement subject, Feature object, int _quantityMin, int _quantityBetter) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._courseElement = this.subject = subject;
		this.ref_courseElement = this.subject_refid = subject.id();
		this._feature = this.object = object;
		this.ref_feature = this.object_refid = object.id();
		timekey(true);
		
		this._quantityMin = _quantityMin;
		this.fetched_quantityMin = true;
		this._quantityBetter = _quantityBetter;
		this.fetched_quantityBetter = true;
	}
	
	ElementRequiresFeature(RelationManager manager, boolean full, CourseElement subject, Feature object, int _quantityMin, int _quantityBetter) {
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
	public CourseElement subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getCourseElement(subject_refid);
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
		if (obj instanceof ElementRequiresFeature) {
			return equals((ElementRequiresFeature) obj);
		}
		return false;
	}
	
	public boolean equals(ElementRequiresFeature relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public ElementRequiresFeature create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementRequiresFeature\"" + " (\"course_element\", \"feature\","
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
		String query = "UPDATE " + "\"scetris\".\"elementRequiresFeature\"" + " SET \"quantity_min\" = ?, "
			+ "\"quantity_better\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"course_element\" = ?"
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
			stmt.setInt(i++, ref_courseElement);
			stmt.setInt(i++, ref_feature);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"elementRequiresFeature\"" + '#' + id());
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
		String query = "DELETE FROM " + "\"scetris\".\"elementRequiresFeature\""
			+ " WHERE "
			+ " \"course_element\" = ? AND "
			+ " \"feature\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_courseElement);
			stmt.setInt(i++, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("courseElement")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseElementFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "course_element", use = "subject", ref = CourseElement.class)
	public CourseElement getCourseElement() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_courseElement) {
			_courseElement = manager.getCourseElement(ref_courseElement);
			fetched_courseElement = true;
		}
		return _courseElement;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCourseElementFetched() {
		return fetched_courseElement;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseElementChanged() {
		return changed_courseElement;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("courseElement")
	public int refCourseElement() {
		return ref_courseElement;
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
	 * Sets the value of <code>courseElement</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCourseElementChanged()} will return true. If you specify
	 * the same value as the old value isCourseElementChanged() will return the same as it did
	 * before calling <code>setCourseElement(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>courseElement</code> is not nullable)
	 * @since Iteration2
	 */
	public ElementRequiresFeature setCourseElement(CourseElement value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("courseElement is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_courseElement, value._id) == 0) return this;
		changed_courseElement = true;
		ref_courseElement = value._id;
		fetched_courseElement = true;
		_courseElement = value;
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
	public ElementRequiresFeature setFeature(Feature value) {
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
	public ElementRequiresFeature setQuantityMin(int value) {
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
	public ElementRequiresFeature setQuantityBetter(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("quantityBetter is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_quantityBetter, value) == 0) return this;
		changed_quantityBetter = true;
		_quantityBetter = value;
		return this;
	}

}
