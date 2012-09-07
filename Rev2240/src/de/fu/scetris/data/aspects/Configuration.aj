/* Configuration.aj / 5:17:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.aspects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.orm.DatabaseException;

/**
 *
 * @author Julian Fleischer
 */
public aspect Configuration {
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String RelationManager.getConfig(String key, String defaultValue) throws DatabaseException {
		String query = "SELECT \"key\", \"value\" FROM \"scetris\".\"Configuration\" WHERE \"key\" = ?;";
		try {
			PreparedStatement stmt = this.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, key);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString(2);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return defaultValue;
	}
	
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int RelationManager.getConfig(String key, int defaultValue) throws DatabaseException {
		String value = getConfig(key, "");
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public double RelationManager.getConfig(String key, double defaultValue) throws DatabaseException {
		String value = getConfig(key, "");
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public float RelationManager.getConfig(String key, float defaultValue) throws DatabaseException {
		String value = getConfig(key, "");
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long RelationManager.getConfig(String key, long defaultValue) throws DatabaseException {
		String value = getConfig(key, "");
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
    /**
     * 
     * @param key
     * @param defaultValue
     * @return
     * @throws DatabaseException
     */
	public boolean RelationManager.getConfig(String key, boolean defaultValue) throws DatabaseException {
		String value = getConfig(key, "");
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 */
	public boolean RelationManager.setConfig(String key, Object value) throws Exception {
		String query = "SELECT set_config(?, ?);";
		try {
			PreparedStatement stmt = this.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, key);
			stmt.setString(2, value.toString());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			throw e;
			//return false;
		}
	}
}
