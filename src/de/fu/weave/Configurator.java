/* Configurator.java / 10:25:30 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import java.io.File;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.orm.RelationManager;

/**
 *
 */
@Author("Julian Fleischer")
public interface Configurator<W extends Weavlet<? extends RelationManager>> {
	/**
	 * 
	 */
	void configure(W $weavlet);

	/**
	 * 
	 * @param $item
	 * @param $default
	 * @return
	 */
	int get(String $item, int $default);

	/**
	 * 
	 * @param $item
	 * @param $default
	 * @return
	 */
	String get(String $item, String $default);

	/**
	 * 
	 * @return
	 */
	Class<? extends Module<? extends RelationManager>> getModules();

	/**
	 * 
	 * @param $xmlFile
	 */
	boolean loadXML(final File $xmlFile);

	/**
	 * 
	 * @param $xmlFile
	 */
	boolean loadXML(final String $xmlFile);
}
