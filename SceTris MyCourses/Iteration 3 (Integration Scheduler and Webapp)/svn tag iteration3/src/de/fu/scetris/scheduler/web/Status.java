/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.web;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.web.Scheduling.State;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Konrad Reiche
 * @since Iteration3
 */
@ModuleInfo(name = "status", author = "Konrad Reiche", description = "Does *essentially* nothing", requires = { Requirement.DATABASE })
public class Status extends GenericModule<RelationManager> {

	public Status(final Scheduling parent) {
		super(parent);
	}

	@Action(template = "scheduling.status.xsl")
	public void _default(final String[] path,
			@Arg(name = "start", stringDefault = "idle") final String arg)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {

		State status = ((Scheduling) getParent()).getStatus();

		if (arg.equals("idle")) {
			put("idle");
		} else if (arg.equals("Start")) {
			put("Start", "");
			((Scheduling) getParent()).start(relationManager);
		} else if (arg.equals("stop")) {
			put("idle", "start");
		}

		put("status", status);

		if (status == State.FINISHED) {

			de.fu.weave.orm.Filter[] noFilter = {};
			List<Room> roomList = (List<Room>) relationManager.getRoom(noFilter);

			for (Room room : roomList) {

				RoomTimetable roomTimetable = ((Scheduling) getParent())
						.getRoomTimeTable(room);
				put("roomTimetable", roomTimetable);
			}

		}

	}

}
