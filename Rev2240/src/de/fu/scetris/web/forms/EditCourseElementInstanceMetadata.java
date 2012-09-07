/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import org.apache.log4j.Logger;

import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Person;

public class EditCourseElementInstanceMetadata extends NewCourseElementInstanceMetadata {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;	
	
    @Field
    @Multiple
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)	
    public int[] ceiid = {};
	
	@Override
	public boolean commit() throws Exception {
        Logger $logger = Logger.getLogger(getClass());	
		$logger.info("commitChange");
		for(int $i=0; $i < ceiid.length; $i++ ) {
			$logger.info("\n HALLO :: "+ceiid[$i]);
			CourseElementInstance $x = manager().getCourseElementInstance(ceiid[$i]);
			CourseElement $ce = manager().getCourseElement(super.courseelement[$i]);
			Person $lect = manager().getPerson(super.lecturer[$i]);
			// List<Feature> $featureid = manager().get
			
			$x
				.setCourseElement($ce)
				.setLecturer($lect)
				.setDuration(super.duration[$i])
				.pushChanges();
				
		}

		return true;
	}
    
}
