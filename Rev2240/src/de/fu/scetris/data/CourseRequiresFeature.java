/* CourseRequiresFeature.java / 2011-03-02+01:00
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
 * OO-Representation of the relationship-relation CourseRequiresFeature
 * <p>
 * 
			This relation tells us what kind and amount of Feature is required in order to be held.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "courseRequiresFeature", subject = Course.class, object = Feature.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"courseRequiresFeature\"", requiredSqlCols = {"quantity_min", "quantity_better"})
@de.fu.weave.xml.annotation.XmlElement("courseRequiresFeature")
public class CourseRequiresFeature extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Course = "course";
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
	Course _course;
	boolean changed_course = false;
	boolean fetched_course = false;
	int ref_course;
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
	
	
	
	Course subject;
	int subject_refid;
	Feature object;
	int object_refid;
	
	
	CourseRequiresFeature(RelationManager manager, Course subject, Feature object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_course = result.getInt("course");
			ref_feature = result.getInt("feature");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_quantityMin = result.getInt("quantity_min");
			fetched_quantityMin = true;
			_quantityBetter = result.getInt("quantity_better");
			fetched_quantityBetter = true;	
			this.subject_refid = ref_course;
			this.object_refid = ref_feature;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	CourseRequiresFeature(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			this.ref_course = result.getInt("\"course\"");
			this.ref_feature = result.getInt("\"feature\"");
			this._timekey = result.getTimestamp("\"timekey\"");
			this.fetched_timekey = true;
			this._quantityMin = result.getInt("\"quantity_min\"");
			this.fetched_quantityMin = true;
			this._quantityBetter = result.getInt("\"quantity_better\"");
			this.fetched_quantityBetter = true;
			this.subject_refid = ref_course;
			this.object_refid = ref_feature;
			this.subject = manager.getCourse(this.subject_refid);
			this.object = manager.getFeature(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.exists = true;
	}
	
	CourseRequiresFeature(RelationManager manager, Course subject, Feature object, int _quantityMin, int _quantityBetter) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._course = this.subject = subject;
		this.ref_course = this.subject_refid = subject.id();
		this._feature = this.object = object;
		this.ref_feature = this.object_refid = object.id();
		timekey(true);
		
		this._quantityMin = _quantityMin;
		this.fetched_quantityMin = true;
		this._quantityBetter = _quantityBetter;
		this.fetched_quantityBetter = true;
	}
	
	CourseRequiresFeature(RelationManager manager, boolean full, Course subject, Feature object, int _quantityMin, int _quantityBetter) {
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
	public Course subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getCourse(subject_refid);
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
		if (obj instanceof CourseRequiresFeature) {
			return equals((CourseRequiresFeature) obj);
		}
		return false;
	}
	
	public boolean equals(CourseRequiresFeature relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public CourseRequiresFeature create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"courseRequiresFeature\"" + " (\"course\", \"feature\","
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
		String query = "UPDATE " + "\"scetris\".\"courseRequiresFeature\"" + " SET \"quantity_min\" = ?, "
			+ "\"quantity_better\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"course\" = ?"
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
			stmt.setInt(i++, ref_course);
			stmt.setInt(i++, ref_feature);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"courseRequiresFeature\"" + '#' + id());
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
		String query = "DELETE FROM " + "\"scetris\".\"courseRequiresFeature\""
			+ " WHERE "
			+ " \"course\" = ? AND "
			+ " \"feature\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_course);
			stmt.setInt(i++, ref_feature);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("course")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "course", use = "subject", ref = Course.class)
	public Course getCourse() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_course) {
			_course = manager.getCourse(ref_course);
			fetched_course = true;
		}
		return _course;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCourseFetched() {
		return fetched_course;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseChanged() {
		return changed_course;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("course")
	public int refCourse() {
		return ref_course;
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
	 * Sets the value of <code>course</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCourseChanged()} will return true. If you specify
	 * the same value as the old value isCourseChanged() will return the same as it did
	 * before calling <code>setCourse(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>course</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseRequiresFeature setCourse(Course value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("course is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_course, value._id) == 0) return this;
		changed_course = true;
		ref_course = value._id;
		fetched_course = true;
		_course = value;
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
	public CourseRequiresFeature setFeature(Feature value) {
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
	public CourseRequiresFeature setQuantityMin(int value) {
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
	public CourseRequiresFeature setQuantityBetter(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("quantityBetter is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_quantityBetter, value) == 0) return this;
		changed_quantityBetter = true;
		_quantityBetter = value;
		return this;
	}

}
