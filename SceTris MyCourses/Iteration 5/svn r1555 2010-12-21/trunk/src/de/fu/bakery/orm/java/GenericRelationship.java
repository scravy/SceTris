/* GenericRelationship.java / 2:41:41 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
abstract public class GenericRelationship extends GenericRelation implements Relationship {

	@Override
	public int compareTo(final Relation relation) {
		if (relation instanceof Relationship) {
			return compareTo((Relationship) relation);
		}
		return -2;
	}

	public int compareTo(final Relationship r) {
		Entity s = null;
		Entity o = null;
		try {
			s = r.subject();
		} catch (DatabaseException e) {
		}
		try {
			o = r.object();
		} catch (DatabaseException e) {
		}
		if (!r.getClass().equals(getClass())) {
			String name1 = r.getClass().getCanonicalName();
			String name2 = getClass().getCanonicalName();
			return name1.compareTo(name2);
		}
		if (equals(r)) {
			return 0;
		}
		try {
			if ((s.compareTo(r.subject()) < 0) && (o.compareTo(r.object()) < 0)) {
				return -1;
			}
		} catch (DatabaseException e) {
			return -1;
		}
		return 1;
	}

	public int id() {
		return hashCode();
	}
}
