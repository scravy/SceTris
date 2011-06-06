/* QuickEditBuildings.java / 7:20:29 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.weave.orm.DatabaseException;

@Author("Julian Fleischer")
public class QuickEditBuildings extends AbstractForm {

	private static final long serialVersionUID = 2249881694407211194L;

	@Field
	@Alternatives("buildings")
	public int building;

	public Map<Integer,String> buildings = new TreeMap<Integer,String>();

	@Override
	public boolean commit() {
		return true;
	}

	@Override
	public void init() {
		try {
			List<Building> $deps = manager().getBuilding();
			for (Building $dep : $deps) {
				buildings.put($dep.id(),
					($dep.hasName() ? $dep.getName() + ", " : "") + $dep.getAddress());
			}
		} catch (DatabaseException $exc) {

		}
	}
}
