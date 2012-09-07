/* DatabaseLogicTest.java / 1:36:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.Test;


import static org.junit.Assert.*;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class IntegratedProceduresTest extends DatabaseTestSkeleton {

	Person rjhacker;
	Privilege view;
	Privilege edit;
	Privilege create;
	Role guest;
	Role admin;

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
	public void assignRole() throws DatabaseException {
		rjhacker.assignRole(guest);
		rjhacker.assignRole(guest);
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void assignRoleByString() throws DatabaseException {
		rjhacker.assignRole("guest");
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void fullTest() throws DatabaseException {
		assertFalse(rjhacker.hasPrivilege(view));
		assertFalse(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege(create));
		rjhacker.grantPrivilege(view);
		assertTrue(rjhacker.hasPrivilege(view));

		rjhacker.assignRole(admin);
		assertTrue(rjhacker.hasPrivilege(view));
		assertFalse(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege(create));

		admin.grantPrivilege(edit);
		admin.grantPrivilege(view);
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege(create));

		rjhacker.revokePrivilege(view);
		// he will still have the privilege, since he’s an admin
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege(create));

		rjhacker.grantPrivilege(edit);
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege(create));

		admin.grantPrivilege(create);
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege(edit));
		assertTrue(rjhacker.hasPrivilege(create));

		admin.revokePrivilege(edit);
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege(edit));
		assertTrue(rjhacker.hasPrivilege(create));

		rjhacker.withdrawRole(admin);
		assertFalse(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege(create));
	}

	
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void grantPrivilege() throws DatabaseException {
		assertFalse(rjhacker.hasPrivilege(view));
		assertFalse(rjhacker.hasPrivilege("view"));
		rjhacker.grantPrivilege(view);
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege("view"));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void grantPrivilegeByString() throws DatabaseException {
		assertFalse(rjhacker.hasPrivilege(view));
		assertFalse(rjhacker.hasPrivilege("view"));
		rjhacker.grantPrivilege("view");
		assertTrue(rjhacker.hasPrivilege(view));
		assertTrue(rjhacker.hasPrivilege("view"));
		assertFalse(rjhacker.hasPrivilege(edit));
		assertFalse(rjhacker.hasPrivilege("edit"));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void grantPrivilegeToRoleByString() throws DatabaseException {
		admin.grantPrivilege(create);
		assertTrue(admin.hasPrivilege(create));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void revokePrivilege() throws DatabaseException {
		grantPrivilege();
		rjhacker.revokePrivilege(view);
		assertFalse(rjhacker.hasPrivilege("view"));
		assertFalse(rjhacker.hasPrivilege(view));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void revokePrivilegeByString() throws DatabaseException {
		grantPrivilegeByString();
		rjhacker.revokePrivilege("view");
		assertFalse(rjhacker.hasPrivilege("view"));
		assertFalse(rjhacker.hasPrivilege(view));
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void withdrawRole() throws DatabaseException {
		assignRole();
		rjhacker.withdrawRole(guest);
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void withdrawRoleByString() throws DatabaseException {
		assignRole();
		rjhacker.withdrawRole("guest");
	}
}
