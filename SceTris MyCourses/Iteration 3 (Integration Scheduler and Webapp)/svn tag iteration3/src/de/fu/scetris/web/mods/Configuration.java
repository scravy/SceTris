/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;


import static de.fu.weave.orm.filters.Filters.any;
import static de.fu.weave.orm.filters.Filters.eq;
import static de.fu.weave.orm.filters.Filters.like;
import static de.fu.weave.util.MD5.md5;

import java.sql.Timestamp;
import java.util.Collection;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Andre Zoufahl
 * @since Iteration3
 */
@SuppressWarnings("unused")
@ModuleInfo(name = "configuration",
			author = "Andre Zoufahl",
			description = "Configuration of the system",
			requires = Requirement.DATABASE)
public class Configuration extends GenericModule<RelationManager> {

	public Configuration(final Scetris parent) {
		super(parent);
	}

	@Action(template = "configuration/configuration.menu.xsl", requiresPrivilege = "superuser")
	public void _default(final String[] path) {

	}

}
