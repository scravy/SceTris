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
public class Triple<A, B, C> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4007173696628778713L;

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
	 */
	public C trd;

	/**
	 * 
	 * @param $a
	 * @param $b
	 * @param $c
	 */
	public Triple(final A $a, final B $b, final C $c) {
		this.fst = $a;
		this.snd = $b;
		this.trd = $c;
	}

	/**
	 * 
	 * @param $trip
	 * @return
	 */
	public boolean equals(final Triple<A,B,C> $trip) {
		return (fst.equals($trip.fst) && snd.equals($trip.snd) && trd.equals($trip.trd));
	}
}
