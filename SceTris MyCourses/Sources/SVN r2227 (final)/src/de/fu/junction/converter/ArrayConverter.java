/* ArrayConverter.java / 11:44:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.util.LinkedList;
import java.util.List;

public class ArrayConverter implements StringConverter<String[]> {
	@Override
	public String[] convert(String $string) {
		$string = $string.trim() + "\n";
		List<String> $list = new LinkedList<String>();
		int $i = 0;
		int $j;
		while (($j = $string.indexOf("\n", $i)) >= 0) {
			String $line = $string.substring($i, $j).trim();
			if ($line.length() > 0) $list.add($line);
			$i = $j + 1;
		}
		return $list.toArray(new String[$list.size()]);
	}

}
