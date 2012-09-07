/* SessionConfig.java / 8:59:33 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

public class SessionConfig {
	private String defaultStylesheet = "weave.css";
	private String printStylesheet = "weave-print.css";
	private String defaultLanguage = "en";
	private String defaulType = "html";
	private final String[] availableTypes = new String[] {
			"html", "html5", "xhtml", "xsl", "plain", "json",
			"pdf", "latex", "xml", "text"
		};
	private String[] visualContentTypes =
		new String[] { "html", "html5", "xhtml", "xsl", "thc" };

	public String[] getAvailableTypes() {
		return availableTypes;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public String getDefaultStylesheet() {
		return defaultStylesheet;
	}

	public String getDefaulType() {
		return defaulType;
	}

	public String getPrintStylesheet() {
		return printStylesheet;
	}

	public String[] getVisualContentTypes() {
		return visualContentTypes;
	}

	public void setDefaultLanguage(final String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public void setDefaultStylesheet(final String defaultStylesheet) {
		this.defaultStylesheet = defaultStylesheet;
	}

	public void setDefaulType(final String defaulType) {
		this.defaulType = defaulType;
	}

	public void setPrintStylesheet(final String printStylesheet) {
		this.printStylesheet = printStylesheet;
	}

	public void setVisualContentTypes(final String[] visualContentTypes) {
		this.visualContentTypes = visualContentTypes;
	}
}