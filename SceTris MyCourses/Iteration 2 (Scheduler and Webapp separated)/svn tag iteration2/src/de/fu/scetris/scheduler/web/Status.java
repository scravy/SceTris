/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.web;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@ModuleInfo(name = "start",
			author = "Konrad Reiche",
			description = "Does *essentially* nothing",
			requires = { Requirement.DATABASE })
public class Status extends GenericModule<RelationManager> {

	public Status(final Scheduling parent) {
		super(parent);
	}

	@Action(template = "scheduling.status.xsl")
	public void _default(final String[] path,
						 @Arg(name = "start") final boolean doStart) {
		if (doStart) {
			// issue IPC call here
		}
		put("status", ((Scheduling) getParent()).getStatus());
	}
}
