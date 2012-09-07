/* ArrayConverter.java / 11:44:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import de.fu.junction.converter.StringConverter;

public class ArrayConverter implements StringConverter<String[]> {
	@Override
	public String[] convert(String $string) {
		$string = $string.trim() + "\n";
		LinkedList<String> $list = new LinkedList<String>();
		int $i = 0;
		int $j;
		while (($j = $string.indexOf("\n", $i)) >= 0) {
			String $line = $string.substring($i, $j).trim();
			if ($line.length() > 0) {
				$list.add($line);
			}
			$i = $j + 1;
		}
		return $list.toArray(new String[0]);
	}

	@Test
	public void testArrayConverter() {
		String[] $s = new ArrayConverter().convert("   Hallo\n  Welt\t\nDa\n\nDraußen");
		assertEquals($s[0], "Hallo");
		assertEquals($s[1], "Welt");
		assertEquals($s[2], "Da");
		assertEquals($s[3], "Draußen");
	}
}