/* AbstractForm.java / 10:43:55 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.formsupport;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.impl.frigg.FriggModule;

/**
 *
 */
abstract public class AbstractForm extends FriggModule.AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8130560199991723176L;

	@Override
	public RelationManager manager() {
		return (RelationManager) super.manager();
	}
}
