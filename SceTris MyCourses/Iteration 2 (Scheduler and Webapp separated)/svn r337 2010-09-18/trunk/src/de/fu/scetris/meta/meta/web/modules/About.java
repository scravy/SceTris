package de.fu.scetris.meta.meta.web.modules;

import java.util.Map;

import de.fu.scetris.meta.meta.web.Module;
import de.fu.scetris.meta.meta.web.annotation.Action;
import de.fu.scetris.meta.meta.web.annotation.ModuleName;

/**
 * @author scravy
 */
@ModuleName("about")
public class About extends Module {
	
	@Action(name="", template="about.xsl")
	public void aboutScetris() {
		put("title", "Hello, World!");
		Map<String,Class<? extends Module>> modules = parent.getModules();
		
		for (String name : modules.keySet()) {
			put("module", modules.get(name).getCanonicalName()).setAttribute("name", name);
		}
	}
	
	@Action(name="module", template="about.xsl")
	public void aboutModule(String module) {
		Class<? extends Module> moduleClass = parent.getModules().get(module);
		put("message", moduleClass.getCanonicalName());
		
	}
}
