/* Tupel.java / 2:44:23 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.io.Serializable;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Tuple<A, B> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899016882968209075L;

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
	 * @param <A>
	 * @param <B>
	 * @param <C>
	 * @param $a
	 * @param $b
	 * @param $c
	 * @return
	 */
	public static <A, B, C> Triple<A,B,C> t(final A $a, final B $b, final C $c) {
		return new Triple<A,B,C>($a, $b, $c);
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
