/* DatabaseLogicTest.java / 1:36:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.Test;


import static org.junit.Assert.*;

import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class IntegratedProceduresWithTargetsTest extends DatabaseTestSkeleton {

	Person rjhacker;
	Privilege view;
	Privilege edit;
	Privilege create;
	Role guest;
	Role admin;
	final String kaffee = "kaffee";
	final String saft = "saft";
	final String tee = "tee";

	@Override
	protected void _init() throws Exception {
		rjhacker = manager.createPerson("Random", "Hacker", "rjhacker", "");
		view = manager.createPrivilege("view");
		edit = manager.createPrivilege("edit");
		create = manager.createPrivilege("create");
		guest = manager.createRole("guest");
		admin = manager.createRole("admin");
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void _testSetup() throws DatabaseException {
		assertNotNull(admin);
		assertNotNull(guest);
		assertNotNull(create);
		assertNotNull(view);
		assertNotNull(edit);
		assertNotNull(rjhacker);
		assertTrue(admin.id() > 0);
		assertTrue(guest.id() > 0);
		assertTrue(manager.getRole(admin.id()).equals(admin));
		assertTrue(manager.getRole(guest.id()).equals(guest));
		assertTrue(manager.getPerson(rjhacker.id()).equals(rjhacker));
		assertTrue(manager.getPrivilege(view.id()).equals(view));
		assertTrue(manager.getPrivilege(edit.id()).equals(edit));
		assertTrue(manager.getPrivilege(create.id()).equals(create));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void grantCreateForKaffee() throws DatabaseException {
		rjhacker.grantPrivilege(create.getName(), kaffee);
		assertTrue(rjhacker.hasPrivilege(create.getName(), kaffee));
		assertFalse(rjhacker.hasPrivilege(create.getName(), tee));
	}
	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void revokeCreateForKaffee() throws DatabaseException {
		grantCreateForKaffee();
		assertTrue(rjhacker.hasPrivilege(create.getName(), kaffee));
		rjhacker.revokePrivilege(create.getName(), kaffee);
		assertFalse(rjhacker.hasPrivilege(create.getName(), kaffee));
	}	
	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void revokeCreateForEverything() throws DatabaseException {
		grantCreateForKaffee();
		assertTrue(rjhacker.hasPrivilege(create.getName(), kaffee));
		rjhacker.revokePrivilege(create.getName());
		assertFalse(rjhacker.hasPrivilege(create.getName(), kaffee));
	}	
	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void grantRoleForSaft() throws DatabaseException {
		rjhacker.assignRole(admin.getName());
		admin.grantPrivilege(create.getName(), saft);
		assertFalse(rjhacker.hasPrivilege(create.getName()));
		assertTrue(rjhacker.hasPrivilege(create.getName(), saft));
	}	
	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void withdrawRoleFromPerson() throws DatabaseException {
		grantRoleForSaft();
		rjhacker.withdrawRole(admin.getName());
		assertFalse(rjhacker.hasPrivilege(saft));
	}	
	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void revokeSimplePrivilegeFromRole() throws DatabaseException {
		grantRoleForSaft();
		admin.grantPrivilege(create.getName(), kaffee);
		admin.revokePrivilege(create);
		assertFalse(rjhacker.hasPrivilege(saft));
		assertFalse(rjhacker.hasPrivilege(kaffee));
	}	
	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void revokeComplexPrivilegeFromRole() throws DatabaseException {
		grantRoleForSaft();
		admin.grantPrivilege(create.getName(), kaffee);
		admin.revokePrivilege(create.getName(), saft);
		assertFalse(rjhacker.hasPrivilege(create.getName(), saft));
		assertTrue(rjhacker.hasPrivilege(create.getName(), kaffee));
	}	
}
