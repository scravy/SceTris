/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import java.sql.SQLException;

import org.w3c.dom.Document;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A module to import and export data.
 */
@Author("Julian Fleischer")
public class ImportExport extends FriggModule<RelationManager> {

	public ImportExport(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "imex/imex.xsl")
	public void _default() {

	}

	@Action(name = "export", template = "imex/imex.export.xsl")
	public void doExport(final String[] target) throws DatabaseException, SQLException {
		if ((target.length > 0) && target[0].equals("~all")) {
			setBinaryData(manager().export(false).getBinaryStream(), "text/xml", "export.xml");
		}
	}

	@Action(name = "import", template = "imex/imex.import.xsl")
	public void doImport() {

	}

	@Commit(action = "import", after = "")
	public void doImportCommit(@Arg(name = "import-data") final String data)
			throws Exception {
		Document xmlData = getParent().getXmlHelper().newDocument(data.getBytes("UTF-8"));
		manager().importXML(xmlData);
	}
}
