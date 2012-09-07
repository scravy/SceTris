/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.fourtris;

import java.sql.SQLException;

import org.w3c.dom.Document;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Fourtris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module to display a start-page.
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "imex",
			author = "Julian Fleischer",
			description = "Sitemap of Scetris.",
			requires = Requirement.DATABASE)
public class ImportExport extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public ImportExport(final Fourtris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "fuberlin/imex.xsl")
	public void _default(final String[] target) {

	}

	@Action(name = "export", template = "fuberlin/imex.export.xsl")
	public void doExport(final String[] target) throws DatabaseException, SQLException {
		if ((target.length > 0) && target[0].equals("~all")) {
			setBinaryData(getRelationManager().export(false).getBinaryStream(), "text/xml", "export.xml");
		}
	}

	@Action(name = "import", template = "fuberlin/imex.import.xsl")
	public void doImport(final String[] target) {

	}

	@Commit(action = "import", after = "")
	public void doImportCommit(final String[] target, @Arg(name = "import-data") final String data)
			throws Exception {
		Document xmlData = getParent().getXmlHelper().newDocument(data.getBytes("UTF-8"));
		getRelationManager().importXML(xmlData);
	}
}
