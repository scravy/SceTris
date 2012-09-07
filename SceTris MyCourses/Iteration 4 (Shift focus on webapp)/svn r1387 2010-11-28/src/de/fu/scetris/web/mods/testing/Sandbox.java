/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.testing;

import java.io.FileInputStream;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module containing some showcases
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "showcase",
			author = "Julian Fleischer",
			description = "Start playing in the Sandbox (you won't get lemonade).",
			requires = Requirement.DATABASE)
public class Sandbox extends FriggModule<RelationManager> {

	/**
	 * 
	 * @param parent
	 *            The Controller that instantiated this Module
	 */
	public Sandbox(final Scetris parent) {
		super(parent);
	}

	@Action(template = "sandbox.xsl")
	public void _default(final String[] target) {

	}

	/**
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(name = "test4", template = "sandbox.test4.xsl", handles = {
			IllegalArgumentException.class, RuntimeException.class })
	public void demonstrateThePower(final String[] target) {
		throw new NullPointerException("I AM A BANANA! (and i H.A.T.E. NullPointerExceptions)");
	}

	/**
	 * This is a showcase to demonstrate the automatic handling of string to number conversion and
	 * default values
	 * 
	 * @param target
	 *            See description of {@link Action}
	 * @param completelyDifferent
	 *            Just a sample annotated parameter. Not that this one is a double.
	 * @param color
	 *            Just a sample annotated parameter
	 * @param modules
	 *            Just a sample annotated parameter. Note that this one is an array.
	 * @param num
	 *            Just a sample annotated parameter. Not that this one is an integer.
	 * @throws Exception
	 *             -
	 */
	@Action(name = "test2", template = "sandbox.test2.xsl")
	public void showGENERIC_META_Page_MUAHARHARHAR(final String[] target,
												   @Arg(name = "something") final double completelyDifferent,
												   @Arg(name = "color", stringDefault = "black") final String color,
												   @Arg(name = "modules", stringDefault = "start") final String[] modules,
												   @Arg(name = "yippie", intDefault = -1) final Integer num)
			throws Exception {
		put("target", target);
		put("something", completelyDifferent);
		put("color", color);
		put("modules", modules);
		put("yippie", num);
	}

	/**
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(name = "test1", template = "sandbox.test1.xsl")
	public void showTestPage(final String[] target) {
		put("target", target);
	}

	/**
	 * 
	 * @param target
	 *            See description of {@link Action}
	 * @throws Exception
	 *             -
	 */
	@Action(name = "test3", template = "sandbox.test3.xsl", handles = Exception.class)
	public void throwAnException(final String[] target) throws Exception {
		try {
			throw new Exception("Something happened!");
		} catch (Exception e) {
			throw new Exception("What happened when David asked what was happening?", e);
		}
	}

	@Action(name = "testCommit", template = "sandbox.testCommit.xsl")
	public void z1(final String[] target) throws Exception {

	}

	@Commit(action = "testCommit", after = "commitSuccessfull")
	public void z2(final String[] target,
				   @Arg(name = "foobar", stringDefault = "something") final String theString)
			throws Exception {
		if (relationManager == null) {
			throw new RuntimeException("relationManger inul");
		}
		relationManager.createDepartment(theString + Math.random());
	}

	@Action(name = "commitSuccessfull", template = "sandbox.commitSuccessfull.xsl")
	public void z3(final String[] target) throws Exception {

	}

	@Action(name = "getLogo", template = "error.xsl")
	public void z4(final String[] target) throws Exception {
		this.setBinaryData(new FileInputStream(getFile("gfx/logo_scetris.png")), "image/png");
	}
}
