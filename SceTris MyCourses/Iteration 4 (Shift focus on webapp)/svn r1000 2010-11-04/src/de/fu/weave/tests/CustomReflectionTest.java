/* CustomReflectionTest.java / 12:15:49 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;

import de.fu.weave.Module;
import de.fu.weave.User;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.ControllerException;
import de.fu.weave.impl.frigga.GenericController;
import de.fu.weave.impl.frigga.GenericModule;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.Filter;
import de.fu.weave.orm.GenericRelationManager;
import de.fu.weave.reflect.ActionReflector;
import de.fu.weave.reflect.ModuleReflector;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class CustomReflectionTest {

	public static class Manager extends GenericRelationManager {

		@Override
		public User fetchUser(final int id) throws DatabaseException {
			return null;
		}

		@Override
		public Collection<? extends User> fetchUsers(final Filter... filters) throws DatabaseException {
			return null;
		}

	}

	@SuppressWarnings("serial")
	public static class SampleController extends GenericController<Manager> {
		SampleController() throws ControllerException, ParserConfigurationException {
			super(Manager.class, SampleModule.class);
		}
	}

	@ModuleInfo(name = "someModule", requires = Requirement.DATABASE)
	public static class SampleModule extends GenericModule<Manager> {
		public SampleModule() throws Exception {
			super(new SampleController());
		}

		public SampleModule(final SampleController parent) {
			super(parent);
		}

		@Action(template = "error.xsl")
		public void _default(final String params[]) {

		}

		@Action(name = "someAction", template = "error.xsl")
		public void anyAction(final String params[]) {

		}

		@Commit(action = "someAction", after = "anyAction")
		public void commitAction(final String params[]) {

		}

		@Action(name = "anyAction", template = "error.xsl")
		public void someAction(final String params[]) {

		}
	}

	private SampleController ctrl;
	private SampleModule mod;
	private ModuleReflector<? extends Module<Manager>> reflector;

	@SuppressWarnings("unchecked")
	@Before
	public void _setup() throws ControllerException, ParserConfigurationException {
		ctrl = new SampleController();
		mod = new SampleModule(ctrl);
		reflector = (ModuleReflector<? extends Module<Manager>>) mod.getReflector();
	}

	@Test
	public void testActionGetAfterCommit() throws Exception {
		ActionReflector<? extends Module<Manager>> action = reflector.getAction("someAction");
		assertEquals("anyAction", action.getAfterCommit());
	}

	@Test
	public void testActionGetCommitMethod() throws Exception {
		ActionReflector<? extends Module<Manager>> action = reflector.getAction("someAction");
		Method method = action.getCommitMethod();
		assertNotNull(method);
		assertEquals("commitAction", method.getName());

		action = reflector.getAction("anyAction");
		method = action.getCommitMethod();
		assertNull(method);
	}

	@Test
	public void testActionGetTemplateName() throws Exception {
		ActionReflector<? extends Module<Manager>> action = reflector.getAction("someAction");
		assertEquals("error.xsl", action.getTemplateName());
	}

	@Test
	public void testActionHasCommitAction() throws Exception {
		ActionReflector<? extends Module<Manager>> action1 = reflector.getAction("anyAction");
		assertFalse(action1.hasCommitAction());

		ActionReflector<? extends Module<Manager>> action2 = reflector.getAction("someAction");
		assertTrue(action2.hasCommitAction());
	}

	@Test
	public void testInstantiateModule() throws Exception {
		reflector.newInstance(ctrl);
	}

	@Test
	public void testInvokeAction() throws Exception {
		SampleModule module = (SampleModule) reflector.newInstance(ctrl);
		module.invokeAction("anyAction", new String[0], new TreeMap<String,String[]>());
	}

	@Test
	public void testInvokeCommitAction() throws Exception {
		SampleModule module = (SampleModule) reflector.newInstance(ctrl);
		module.invokeCommitAction("someAction", new String[0], new TreeMap<String,String[]>());
	}

	@Test
	public void testModuleInfo() throws Exception {
		assertEquals("someModule", reflector.getName());
	}

	@Test
	public void testModuleRequirements() throws Exception {
		assertTrue(reflector.isDatabaseRequired());
		assertFalse(reflector.isLoginRequired());
	}
}
