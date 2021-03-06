/* Any.java / 2:33:54 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.filters;

import de.fu.bakery.orm.java.Filter;
import de.fu.junction.Strings;
import de.fu.junction.functional.C;
import de.fu.junction.functional.F;

/**
 * 
 * @author Julian Fleischer
 */
public class All implements Filter {
	public final Filter[] filters;

	public All(final Filter... filters) {
		this.filters = filters;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		Filter[] drained = F.drain(C.is(Sort.class), filters);
		if (drained.length == 1) {
			return filters[0].toString();
		}
		return "(" + Strings.implode(") AND (", drained) + ")";
	}
}
