/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler;

import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.ScheduleScore;

/**
 * Modeling the schedule.
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 */
public interface Schedule extends Comparable<Schedule> {

	public ScheduleScore getScore();
	
	public Configuration getConfiguration();
}
