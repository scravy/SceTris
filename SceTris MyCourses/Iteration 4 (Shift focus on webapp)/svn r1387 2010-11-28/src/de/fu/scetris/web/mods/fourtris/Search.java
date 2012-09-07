/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.fourtris;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Fourtris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module to display a start-page.
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "search",
			author = "Julian Fleischer",
			description = "Sitemap of Scetris.")
public class Search extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Search(final Fourtris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "fuberlin/search.xsl")
	public void _default(final String[] target,
						 @Arg(name = "q") final String query,
						 @Arg(name = "ajax", booleanDefault = false) final boolean ajax)
			throws IOException {
		if (ajax) {
			final PipedOutputStream xml = new PipedOutputStream();
			setBinaryData(new PipedInputStream(xml), "text/xml");
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String a = query.length() < 7 ? "<person id=\"1\">Thomas Heinz</person>" : "";
						String b = query.length() < 6 ? "<person id=\"2\">Uwe Uwe</person>" : "";
						String c = query.length() < 4 ? "<lecture>Einführung in die Ingenieurswissenschaften</lecture>"
								: "";
						String d = query.length() < 3 ? "<lecture>Algorithmen und Programmieren MCCCXXXVII</lecture>"
								: "";
						xml.write(("<results>" +
								"<lecturers>" + a + b + "</lecturers>" + "<lectures>" + c + d
								+ "</lectures>" +
										"</results>").getBytes(Charset.forName("UTF-8")));
					} catch (IOException e) {
					} finally {
						try {
							xml.close();
						} catch (IOException e) {
						}
					}
				}
			}).start();
		} else {
			if (!query.isEmpty()) {

			}
		}
	}
}
