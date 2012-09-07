/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import static de.fu.junction.Generics.*;
import static de.fu.junction.Tuple.*;

import java.util.Map;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module providing administrative tasks.
 * <p>
 * The sole purpose of this module is to show option-panes and forms, the actual logic is contained
 * in {@see UserMgmt}, {@see ImportExport}, ...
 */
@Author({ "Julian Fleischer", "André Zoufahl", "David Bialik" })
public class Admin extends FriggModule<RelationManager> {

	public static class QuickEditBuildings extends AbstractForm {

		private static final long serialVersionUID = 2249881694407211194L;

		@Field
		@Alternatives("buildings")
		public int building;

		public Map<Integer,String> buildings;

		@SuppressWarnings("unchecked")
		public QuickEditBuildings(final RelationManager $manager) {
			buildings = mapping(
				t(1, "Henry-Ford-Bau"),
				t(2, "Silberlaube")
				);
		}

		@Override
		public void commit() throws Exception {

		}

	}

	public static class QuickEditDepartments extends AbstractForm {

		private static final long serialVersionUID = 4389605158990029474L;

		@Field
		@Alternatives("departments")
		public int department;

		public Map<Integer,String> departments;

		@SuppressWarnings("unchecked")
		public QuickEditDepartments(final RelationManager $manager) {
			departments = mapping(
				t(1, "Fachbereich Mathematik und Informatik"),
				t(2, "Fachbereich Geisteswissenschaften")
				);
		}

		@Override
		public void commit() throws Exception {

		}

	}

	public Admin(final Scetris parent) {
		super(parent);
	}

	@Action(template = "admin/admin.xsl")
	public void _default() {

	}

	@Action(template = "admin/admin.courses.xsl")
	public void courses() {

	}

	@Action(template = "admin/admin.facilities.xsl")
	public void facilities() {

		put("quickEditDepartments", new QuickEditDepartments(getRelationManager()));
		put("quickEditBuildings", new QuickEditBuildings(getRelationManager()));
	}

	@Action(template = "admin/admin.imex.xsl")
	public void imex() {

	}

	@Action(template = "admin/admin.sysconfig.xsl")
	public void sysconfig() {

	}

	@Action(template = "admin/admin.users.xsl")
	public void users() {

	}
}
