/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.modules;

import de.fu.scetris.Scetris;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * A module containing some showcases
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "showcase",
			author = "Julian Fleischer",
			description = "Start playing in the Sandbox (you won't get lemonade).")
public class Sandbox extends GenericModule<RelationManager> {

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
}
