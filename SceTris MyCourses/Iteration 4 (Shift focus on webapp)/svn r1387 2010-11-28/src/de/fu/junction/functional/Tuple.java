/* Tupel.java / 2:44:23 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Tuple<A, B> {

	/**
	 * 
	 * @param <A>
	 * @param <B>
	 * @param a
	 * @param b
	 * @return
	 * @since Iteration4
	 */
	public static <A, B> Tuple<A,B> t(final A a, final B b) {
		return new Tuple<A,B>(a, b);
	}

	/**
	 * 
	 */
	public A fst;

	/**
	 * 
	 */
	public B snd;

	/**
	 * 
	 * @param a
	 * @param b
	 * @since Iteration4
	 */
	public Tuple(final A a, final B b) {
		this.fst = a;
		this.snd = b;
	}

	/**
	 * 
	 * @param tup
	 * @return
	 * @since Iteration4
	 */
	public boolean equals(final Tuple<A,B> tup) {
		return (fst.equals(tup.fst) && snd.equals(tup.snd));
	}
}
