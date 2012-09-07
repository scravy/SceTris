/* ClassesComparator.java / 10:40:41 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.util;

import java.util.Comparator;

public class ClassesComparator implements Comparator<Class<?>> {
	@Override
	public int compare(final Class<?> one, final Class<?> two) {
		if (one == null) {
			return 1;
		} else if (two == null) {
			return -1;
		}
		return one.getCanonicalName().compareTo(two.getCanonicalName());
	}

	public boolean equals(final Class<?> one, final Class<?> two) {
		return compare(one, two) == 0;
	}
}