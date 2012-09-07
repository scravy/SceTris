/* Allowance.aj / 3:37:01 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.aspects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;
import de.fu.weave.orm.DatabaseException;

/**
 *
 * @author Julian Fleischer
 * @since Iteration3
 */
public privileged aspect Allowance {
	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.grantPrivilege(String privilege) throws DatabaseException {
		String query = "SELECT allow(?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * 
	 * @param privilege
	 * @param target
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.grantPrivilege(String privilege, String target) throws DatabaseException {
		String query = "SELECT allow(?, ?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Role.grantPrivilege(String privilege) throws DatabaseException {
		String query = "SELECT roleallow(?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getName());
			stmt.setString(2, privilege);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * 
	 * @param privilege
	 * @param target
	 * @return
	 * @since Iteration3
	 */
	public boolean Role.grantPrivilege(String privilege, String target) throws DatabaseException {
		String query = "SELECT roleallow(?, ?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.grantPrivilege(Privilege privilege) throws DatabaseException {
		return grantPrivilege(privilege.getName());
	}
	
	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.revokePrivilege(String privilege) throws DatabaseException {
		String query = "SELECT disallow(?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @param privilege
	 * @param target
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.revokePrivilege(String privilege, String target) throws DatabaseException {
		String query = "SELECT disallow(?, ?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Role.revokePrivilege(String privilege) throws DatabaseException {
		String query = "SELECT disallow(?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getName());
			stmt.setString(2, privilege);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @param privilege
	 * @param target
	 * @return
	 * @since Iteration3
	 */
	public boolean Role.revokePrivilege(String privilege, String target) throws DatabaseException {
		String query = "SELECT disallow(?, ?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.revokePrivilege(Privilege privilege) throws DatabaseException {
		return revokePrivilege(privilege.getName());
	}

	/**
	 * 
	 * @param privilege
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.hasPrivilege(Privilege privilege) throws DatabaseException {
		return this.hasPrivilege(privilege.getName());
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.hasRole(Role role) {
		return false;
	}
	
	/**
	 * 
	 * @param roleName
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.hasRole(String roleName) {
		return false;
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.assignRole(String role) throws DatabaseException {
		String query = "SELECT assign(?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, role);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @param role
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.assignRole(Role role) throws DatabaseException {
		return assignRole(role.getName());
	}

	/**
	 * 
	 * @param role
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.withdrawRole(Role role) throws DatabaseException {
		return withdrawRole(role.getName());
	}
	
	/**
	 * 
	 * @param roleName
	 * @return
	 * @since Iteration3
	 */
	public boolean Person.withdrawRole(String role) throws DatabaseException {
		String query = "SELECT unassign(?, ?);";
		try {
			PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, role);
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getBoolean(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
	
	
	/**
	 * 
	 * @param person
	 * @return
	 * @since Iteration3
	 */
	public boolean Privilege.grantTo(Person person) throws DatabaseException {
		return person.grantPrivilege(this);
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 * @since Iteration3
	 */
	public boolean Role.assignTo(Person person) throws DatabaseException {
		return person.assignRole(this);
	}
	
	/**
	 * 
	 */
	public boolean Role.removeFrom(Person person) throws DatabaseException {
		return person.withdrawRole(this);
	}
}
