/* Configuration.aj / 5:17:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.aspects;

import de.fu.scetris.data.RelationManager;

/**
 *
 * @author Julian Fleischer
 * @since Iteration3
 */
public aspect Configuration {
	public String RelationManager.getConfig(String key, String defaultValue) {
		return defaultValue;
	}
	
	public int RelationManager.getConfig(String key, int defaultValue) {
		return defaultValue;
	}
	
	public double RelationManager.getConfig(String key, double defaultValue) {
		return defaultValue;
	}
	
	public float RelationManager.getConfig(String key, float defaultValue) {
		return defaultValue;
	}
	
	public long RelationManager.getConfig(String key, long defaultValue) {
		return defaultValue;
	}
	
	public void RelationManager.setConfig(String key, String defaultValue) {
		
	}
	
	public void RelationManager.setConfig(String key, int defaultValue) {
		
	}
	
	public void RelationManager.setConfig(String key, double defaultValue) {
		
	}
	
	public void RelationManager.setConfig(String key, float defaultValue) {
		
	}
	
	public void RelationManager.setConfig(String key, long defaultValue) {
		
	}
}
