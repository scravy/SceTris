/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import java.io.File;
import java.util.WeakHashMap;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import de.fu.weave.xml.XmlHelper;

/**
 * Loads and caches XSL-Templates
 * <p>
 * The idea is, that a TemplateManager should load and precompile Templates but
 * keep them using WeakReferences. This is accomplished by using a WeakHashMap.
 * Templates are loaded via {@link #loadTemplate(String)} and retrieved via
 * {@link #getTemplate(String)}.
 * 
 * @author Julian Fleischer
 */
public class TemplateManager {
	/**
	 * 
	 */
	final private XmlHelper xmlHelper;

	/**
	 * This TemplateManagers base-directory.
	 * <p>
	 * 
	 * @see #getBaseDir()
	 */
	private String baseDir = "";

	/**
	 * 
	 */
	private final WeakHashMap<String, Transformer> templates = new WeakHashMap<String, Transformer>();

	/**
	 * Creates a new TemplateManager
	 * 
	 * @param xmlHelper
	 *            an XMLHelper that is to be used for processing XML
	 */
	public TemplateManager(final XmlHelper xmlHelper) {
		this.xmlHelper = xmlHelper;
	}

	/**
	 * Retrieve this TemplateManagers base-directory
	 * <p>
	 * The base-directory is the place where to look up templates.
	 * 
	 * @return The path of the base-directory
	 */
	public String getBaseDir() {
		return baseDir;
	}

	/**
	 * Retrieve a template by its name.
	 * <p>
	 * The template is first looked up by its name internally (typically they
	 * are cached). If it is not found (e.g. since the Garbage Collector removed
	 * it) it is loaded on the fly.
	 * 
	 * @param filename
	 *            The name of the template (typically a filename without path)
	 * @return An XSL-Transformer
	 * @throws TemplateManagerException
	 *             If the template could not be loaded because it was either not
	 *             valid or not found.
	 */
	public Transformer getTemplate(final String filename)
			throws TemplateManagerException {
		Transformer template = templates.get(filename);
		if (template == null) {
			return loadTemplate(filename);
		}
		return template;
	}

	/**
	 * Loads a template into this TemplateManager
	 * <p>
	 * All templates are loaded with respect to the base-directory which can be
	 * set using setBaseDir. This will always load a Template, even if it is
	 * already loaded. If a template is already loaded and an attempt to load it
	 * again fails the old template will not have been discarded.
	 * 
	 * @param filename
	 *            The name of the template
	 * @return An XSL-Transformer
	 * @throws TemplateManagerException
	 *             If the template to be loaded could not be found or contains
	 *             errors
	 */
	public Transformer loadTemplate(final String filename)
			throws TemplateManagerException {
		File file = new File(baseDir + '/' + filename);
		if (!file.exists()) {
			throw new TemplateManagerException("File does not exist: \""
					+ filename + "\" (baseDir is \"" + baseDir + "\")");
		}
		try {
			Transformer transformer = xmlHelper.newTransformer(file);
			templates.put(filename, transformer);
			return transformer;
		} catch (TransformerConfigurationException e) {
			throw new TemplateManagerException("Errors in " + filename, e);
		}
	}

	/**
	 * Set the base directory
	 * <p>
	 * The base directory is used to look up templates which not have been
	 * loaded already
	 * 
	 * @param directory
	 *            The path of the directory to be set
	 */
	public void setBaseDir(final String directory) {
		baseDir = directory;
	}
}
