/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

/**
 * Schedule is only a marker interface.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 */
public interface Schedule extends Comparable<Schedule> {
    
    public double getHardFitness();
    
    public double getSoftFitness();
    
}
