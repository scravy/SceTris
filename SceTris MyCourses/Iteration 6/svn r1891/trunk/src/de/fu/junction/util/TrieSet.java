/* TrieSet.java / 10:18:18 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.util;

import java.util.AbstractSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class TrieSet<E> extends AbstractSet<E[]> implements Trie<E[]> {

	private static class Elem<E> {
		boolean $val = false;
		Map<E,Elem<E>> $next = new TreeMap<E,Elem<E>>();
	}

	static class Iterator<E> implements java.util.Iterator<E[]> {

		LinkedList<java.util.Iterator<Elem<E>>> $stack = new LinkedList<java.util.Iterator<Elem<E>>>();

		Iterator(final Elem<E> $elem) {
			$stack.push($elem.$next.values().iterator());
			while ($stack.peek().hasNext()) {
				System.out.println();
				$stack.push($stack.peek().next().$next.values().iterator());
			}
		}

		@Override
		public boolean hasNext() {

			return false;
		}

		@Override
		public E[] next() {

			return null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private final Elem<E> $root = new Elem<E>();

	private int $size = 0;

	@SuppressWarnings("unchecked")
	public boolean add(final char[] $string) {
		Elem<E> $elem = $root;
		for (int $i = 0; $i < $string.length; $i++) {
			Character $e = $string[$i];
			if (!$elem.$next.containsKey($e)) {
				$elem.$next.put((E) $e, new Elem<E>());
			}
			$elem = $elem.$next.get($string[$i]);
		}
		if (!$elem.$val) {
			$size++;
		}
		return $elem.$val = true;
	}

	@Override
	public boolean add(final E[] $string) {
		Elem<E> $elem = $root;
		for (E $e : $string) {
			if (!$elem.$next.containsKey($e)) {
				$elem.$next.put($e, new Elem<E>());
			}
			$elem = $elem.$next.get($e);
		}
		if (!$elem.$val) {
			$size++;
		}
		return $elem.$val = true;
	}

	public boolean add(final String $string) {
		return add($string.toCharArray());
	}

	public boolean contains(final char[] $string) {
		Elem<E> $elem = $root;
		for (Character $e : $string) {
			if (!$elem.$next.containsKey($e)) {
				return false;
			}
			$elem = $elem.$next.get($e);
		}
		return $elem.$val;
	}

	@Override
	public boolean contains(final Object $o) {
		if ($o instanceof char[]) {
			return contains((char[]) $o);
		} else if ($o instanceof String) {
			return contains((String) $o);
		}
		@SuppressWarnings("unchecked")
		E[] $string = (E[]) $o;
		Elem<E> $elem = $root;
		for (E $e : $string) {
			if (!$elem.$next.containsKey($e)) {
				return false;
			}
			$elem = $elem.$next.get($e);
		}
		return $elem.$val;
	}

	public boolean contains(final String $o) {
		return contains($o.toCharArray());
	}

	public boolean hasPrefix(final char[] $prefix) {
		Elem<E> $elem = $root;
		for (Character $e : $prefix) {
			if (!$elem.$next.containsKey($e)) {
				return false;
			}
			$elem = $elem.$next.get($e);
		}
		return true;
	}

	@Override
	public boolean hasPrefix(final E[] $prefix) {
		Elem<E> $elem = $root;
		for (E $e : $prefix) {
			if (!$elem.$next.containsKey($e)) {
				return false;
			}
			$elem = $elem.$next.get($e);
		}
		return true;
	}

	public boolean hasPrefix(final String $prefix) {
		return hasPrefix($prefix.toCharArray());
	}

	@Override
	public java.util.Iterator<E[]> iterator() {
		return new Iterator<E>($root);
	}

	@Override
	public int size() {
		return $size;
	}

}
