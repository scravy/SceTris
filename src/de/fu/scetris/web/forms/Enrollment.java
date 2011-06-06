/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import static de.fu.weave.orm.filters.Filters.*;

import java.util.List;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonTakesPartInElementInstance;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.data.formsupport.AbstractForm;

public class Enrollment extends AbstractForm {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    @Field
    @Multiple
    public int[] cei = {};
    
    @Field
    public int ci = -1;

    @Override
    public boolean commit() throws Exception {
    	CourseInstance $ci = manager().getCourseInstance(ci);
    	Person $p = (Person) session().getUser();
    	if($ci!=null && $p!=null) {
    		manager().beginTransaction();
    		/* remove all previous data about this user with this ci and the ceis and reset them */
    		manager().deletePersonEnrolledInCourseInstance($p, $ci);    		
    		List<CourseElementInstance> $cei_for_ci = manager().getCourseElementInstance(eq(CourseElementInstance.CourseInstance, $ci.getId()));
    		for(CourseElementInstance $check : $cei_for_ci) {
    			manager().deletePersonTakesPartInElementInstance(all(eq(PersonTakesPartInElementInstance.User, $p.getId()), eq(PersonTakesPartInElementInstance.ElementInstance, $check.getId())));
    		}  		
    		
    		/* reset all enroll/takesPart data*/
    		for(int $i=0; $i<cei.length; $i++) {
    			CourseElementInstance $cei = manager().getCourseElementInstance(cei[$i]);
    			if($cei!=null) {
    				Timeslot $ts = manager().getTimeslot($cei.getStartingTimeslot().getId());
    				manager().createPersonTakesPartInElementInstance($p, $cei, $ts);
    			}
    		}
    		manager().createPersonEnrolledInCourseInstance($p, $ci);
    		manager().commitTransaction();
    		return true;
    	}
    	
        return false;
    }
}
