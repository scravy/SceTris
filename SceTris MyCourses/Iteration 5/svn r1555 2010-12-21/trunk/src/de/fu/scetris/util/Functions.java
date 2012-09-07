/* Functions.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.scetris.util;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import de.fu.weave.annotation.meta.Author;

@Author("André Zoufahl")
public class Functions {
	/** TODO :: COMMENTS */
	public static SortedSet<Integer> getSiteNumbers(final int datasum, final int limit,
													final int currentpage) {
		SortedSet<Integer> set = new TreeSet<Integer>();
		if (datasum <= limit) {
			return set;
		}
		final int range = 3;
		int maxpage = (int) Math.ceil((double) datasum / limit); /*
																  * to get float.divison but still
																  * int-result
																  */
		if (currentpage < range) {
			int tmp = range + 2;
			if (maxpage < range + 2) {
				tmp = maxpage;
			}
			for (int i = 0; i < tmp; i++) {
				set.add(i);
			}
		} else if (currentpage + range > maxpage) {
			int tmp = range + 2;
			if (maxpage < range + 2) {
				tmp = maxpage;
			}
			for (int i = maxpage - tmp; i < maxpage; i++) {
				set.add(i);
			}
		} else {
			for (int i = -range + 1; i < range; i++) {
				set.add(i + currentpage);
			}
		}
		set.add(0);
		set.add(maxpage - 1);
		return set;
	}

	/** TODO :: COMMENTS */
	public static List<String> getSiteNumbersWithDots(final SortedSet<Integer> listWithoutDots) {
		List<String> newlist = new LinkedList<String>();
		if (listWithoutDots.isEmpty()) {
			return newlist;
		}
		int pre = listWithoutDots.first();
		for (int x : listWithoutDots) {
			if (x - pre > 1) {
				newlist.add("..");
			}
			newlist.add(x + "");
			pre = x;
		}
		return newlist;
	}
}
