/* Static.java / 3:50:46 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.filters;

import java.util.ArrayList;
import java.util.List;

import de.fu.weave.orm.Filter;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Filters {
	/**
	 * 
	 * @param filters
	 * @return
	 * @since Iteration2
	 */
	public static All all(final Filter... filters) {
		return new All(filters);
	}

	/**
	 * 
	 * @param filters
	 * @return
	 * @since Iteration2
	 */
	public static Any any(final Filter... filters) {
		return new Any(filters);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static Eq eq(final String name, final Object value) {
		return new Eq(name, value);
	}

	/**
	 * 
	 * @param filter
	 * @return
	 * @since Iteration2
	 */
	public static List<Object> extractValues(final Filter filter) {
		List<Object> values = new ArrayList<Object>();
		if (filter instanceof Any) {
			Any any = (Any) filter;
			values.addAll(extractValues(any.filters));
		} else if (filter instanceof All) {
			All all = (All) filter;
			values.addAll(extractValues(all.filters));
		} else if (filter instanceof Eq) {
			Eq eq = (Eq) filter;
			values.add(eq.compareValue);
		} else if (filter instanceof UnEq) {
			UnEq uneq = (UnEq) filter;
			values.add(uneq.compareValue);
		} else if (filter instanceof AbstractFilter) {
			AbstractFilter f = (AbstractFilter) filter;
			values.add(f.compareValue);
		}
		return values;
	}

	/**
	 * 
	 * @param filters
	 * @return
	 * @since Iteration2
	 */
	public static List<Object> extractValues(final Filter[] filters) {
		List<Object> values = new ArrayList<Object>();
		for (Filter filter : filters) {
			values.addAll(extractValues(filter));
		}
		return values;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static Gt gt(final String name, final Comparable<?> value) {
		return new Gt(name, value);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static GtEq gtEq(final String name, final Comparable<?> value) {
		return new GtEq(name, value);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @since Iteration2
	 */
	public static Id id(final int id) {
		return new Id(id);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static Like like(final String name, final String value) {
		return new Like(name, value);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static Lt lt(final String name, final Comparable<?> value) {
		return new Lt(name, value);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static LtEq ltEq(final String name, final Comparable<?> value) {
		return new LtEq(name, value);
	}

	/**
	 * 
	 * @param eq
	 * @return
	 * @since Iteration2
	 */
	public static UnEq not(final Eq eq) {
		return new UnEq(eq.fieldName, eq.compareValue);
	}

	/**
	 * 
	 * @param gt
	 * @return
	 * @since Iteration2
	 */
	public static LtEq not(final Gt gt) {
		return new LtEq(gt.fieldName, gt.compareValue);
	}

	/**
	 * 
	 * @param gteq
	 * @return
	 * @since Iteration2
	 */
	public static Lt not(final GtEq gteq) {
		return new Lt(gteq.fieldName, gteq.compareValue);
	}

	/**
	 * 
	 * @param lt
	 * @return
	 * @since Iteration2
	 */
	public static GtEq not(final Lt lt) {
		return new GtEq(lt.fieldName, lt.compareValue);
	}

	/**
	 * 
	 * @param lteq
	 * @return
	 * @since Iteration2
	 */
	public static Gt not(final LtEq lteq) {
		return new Gt(lteq.fieldName, lteq.compareValue);
	}

	/**
	 * 
	 * @param uneq
	 * @return
	 * @since Iteration2
	 */
	public static Eq not(final UnEq uneq) {
		return new Eq(uneq.fieldName, uneq.compareValue);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public static UnEq unEq(final String name, final Object value) {
		return new UnEq(name, value);
	}
}
