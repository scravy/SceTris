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
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.Filter;
import de.fu.bakery.orm.java.GenericRelationManager;
import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.Module;
import de.fu.weave.User;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggController;
import de.fu.weave.impl.frigg.FriggModule;
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
		public Collection<? extends User> fetchUsers(final Filter filters) throws DatabaseException {
			return null;
		}

	}

	@SuppressWarnings("serial")
	public static class SampleController extends FriggController<Manager> {
		SampleController() throws ControllerInstantiationException {
			super(Manager.class, SampleModule.class);
		}
	}

	@ModuleInfo(name = "someModule", requires = Requirement.DATABASE)
	public static class SampleModule extends FriggModule<Manager> {
		public int compareTo = 7;

		public SampleModule() throws Exception {
			super(new SampleController());
		}

		public SampleModule(final SampleController parent) {
			super(parent);
		}

		@Action(template = "error.xsl")
		public void _default(final String[] params) {

		}

		@Action(name = "someAction", template = "error.xsl")
		public void anyAction(final String[] params) {

		}

		@Commit(action = "someAction", after = "anyAction")
		public void commitAction(final String[] params) {

		}

		@Action(name = "invokeAction1", template = "error.xsl")
		public void invokeAction(final String[] params,
								 @Arg(name = "graf_zahl", intDefault = 3) final int num1,
								 @Arg(name = "zahl-o-mat", intDefault = 4) final int num2) {
			assertEquals(num1, num2);
			compareTo = 1;
		}

		@Action(name = "invokeAction2", template = "error.xsl")
		public void invokeAction2(@Arg(name = "graf_zahl", intDefault = 3) final int num1,
								  @Arg(name = "zahl-o-mat", intDefault = 4) final int num2) {
			assertEquals(num1, num2);
			compareTo = 2;
		}

		@Action(name = "invokeAction3", template = "error.xsl")
		public void invokeAction3(final String[] params,
								  @Arg(name = "graf_zahl", intDefault = 3) final int num) {
			assertEquals(compareTo, num);
			compareTo = 3;
		}

		@Action(name = "invokeAction4", template = "error.xsl")
		public void invokeAction4(@Arg(name = "graf_zahl", intDefault = 3) final int num) {
			assertEquals(compareTo, num);
			compareTo = 4;
		}

		@Action(name = "anyAction", template = "error.xsl")
		public void someAction(final String[] params) {

		}

		@Action(name = "someMoreAction", template = "error.xsl")
		public void someMoreAction() {

		}
	}

	private SampleController ctrl;
	private SampleModule mod;
	private ModuleReflector<? extends Module<Manager>> reflector;

	@SuppressWarnings("unchecked")
	@Before
	public void _setup() throws ControllerInstantiationException {
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
	public void testInvokeAction1WithArguments() throws Exception {
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("graf_zahl", new String[] { "21" });
		map.put("zahl-o-mat", new String[] { "21" });
		mod.invokeAction("invokeAction1", new String[0], map);
		assertEquals(1, mod.compareTo);
	}

	@Test
	public void testInvokeAction2WithArguments() throws Exception {
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("graf_zahl", new String[] { "22" });
		map.put("zahl-o-mat", new String[] { "22" });
		mod.invokeAction("invokeAction2", new String[0], map);
		assertEquals(2, mod.compareTo);
	}

	@Test
	public void testInvokeAction3WithArguments() throws Exception {
		mod.compareTo = 23;
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("graf_zahl", new String[] { "23" });
		mod.invokeAction("invokeAction3", new String[0], map);
		assertEquals(3, mod.compareTo);
	}

	@Test
	public void testInvokeAction4WithArguments() throws Exception {
		mod.compareTo = 24;
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("graf_zahl", new String[] { "24" });
		mod.invokeAction("invokeAction4", new String[0], map);
		assertEquals(4, mod.compareTo);
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
