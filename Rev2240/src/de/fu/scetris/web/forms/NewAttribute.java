/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.Attribute;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

public class NewAttribute extends Attribute.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	public Attribute $createdA;

    public Validator<String> type$validator = new Validator<String>() {
        @Override
        public boolean check(final String $name) throws Exception {
            if (!($name.toLowerCase().equals("string") || $name.toLowerCase().equals("int"))) {
                throw new ValidationException("unsupported Type, only String and Int are supported");
            }
            return true;
        }
    };
	
	@Override
	public boolean commit() throws Exception {
		$createdA = manager().newAttribute(name)
			.setType(type)
			.setUniqueValue(uniqueValue)
			.create();
		return true;
	}
}
