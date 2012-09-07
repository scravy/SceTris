package de.fu.scetris.meta.meta.web.modules;

import de.fu.scetris.meta.meta.web.Module;
import de.fu.scetris.meta.meta.web.annotation.Action;
import de.fu.scetris.meta.meta.web.annotation.ModuleName;

/**
 * @author Julian
 */
@ModuleName("start")
public class Start extends Module {
	
	@Action(template="start.xsl")
	public void showStartPage() {
		
	}
}
