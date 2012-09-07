/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.formsupport.AbstractForm;

public class NewCourseElementInstanceMetadata extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;	
	
    @Field
    @Multiple
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)	
    public int[] courseelement = {};
	
	@Field
    @Multiple
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)	
    public int[] lecturer = {};		
	
	@Field
    @Multiple
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)	
    public int[] duration = {};	
	
	@Override
	public boolean commit() throws Exception {
		
		Integer count = (Integer) session().getValue("cei_count");
		Integer ci = (Integer) session().getValue("cei_ci");
		if(count!= null && ci!=null) {
			CourseInstance $ci = manager().getCourseInstance(ci.intValue());
			for(int $i=0; $i<count.intValue(); $i++) {
				
				CourseElement $ce = manager().getCourseElement(courseelement[$i]);
				Person $lect = manager().getPerson(lecturer[$i]);
				CourseElementInstance $x = manager().newCourseElementInstance($ci, $ce, duration[$i]);
				$x
					.setLecturer($lect)
					.create();
			}
		}
		return true;
	}
    
}
