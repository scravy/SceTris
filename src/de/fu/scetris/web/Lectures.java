/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import static de.fu.weave.orm.filters.Filters.all;
import static de.fu.weave.orm.filters.Filters.any;
import static de.fu.weave.orm.filters.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import de.fu.junction.Parse;
import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.web.forms.Enrollment;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 */
@Author({ "Julian Fleischer", "Andre Zoufahl" })
public class Lectures extends FriggModule<RelationManager> {

	public Lectures(final ScetrisServlet parent) {
		super(parent);
	}

	/* 
	 * Displays all CourseInstances for the current academicTerm for each department */
	@Action(template = "lectures/lectures.xsl")
	public void _default(final String[] target,
    		@Arg(name = "action", stringDefault = "") final String $action) throws DatabaseException {
		List<Course> $courses = manager().getCourse();
		List<CourseInstance> $ci = manager().getCourseInstance();
		for(CourseInstance $x : $ci) {
			$x.getMainLecturer();
			$x.getProgram();
		}
			
		put("courses", $courses);
		put("courseinstances", $ci);
		
		put("at", manager().getAcademicTerm());
		put("dept", manager().getDepartment());
		put("action", $action);
		put("mode", "all");
	}
	
	@Action(template = "lectures/lectures.myinstitute.xsl")
	public void myInstitute(final String[] target) throws DatabaseException {
		Person $p = (Person) $session.getUser();
		if($p.getHomeDepartment()==null || $p.getHomeDepartment().getId()<1) {
			setRedirect("lectures");
			return;
		} 
		List<Course> $courses = manager().getCourse(eq(Course.Department, $p.getHomeDepartment().getId()));
		List<CourseInstance> $ci = manager().getCourseInstance();
		for(CourseInstance $x : $ci) {
			$x.getMainLecturer();
			$x.getProgram();
		}
			
		put("mydept", $p.getHomeDepartment());
		put("courses", $courses);
		put("courseinstances", $ci);
		
		put("at", manager().getAcademicTerm());
		put("dept", manager().getDepartment());
		put("mode", "all");
	}

	/* NOT YET IMPLEMENTED */
	@Action(template = "lectures/lectures.myrecommondations.xsl")
	public void myRecommendations(final String[] target) throws DatabaseException {
	
	
	}
	
	
   /* Works on CEI (after publishing) */
    @Action(template = "lectures/lectures.showCourseInstance.xsl")
    public void showCourseInstance(final String[] $path,
    		@Arg(name = "action", stringDefault = "") final String $action,
    		final Enrollment $form) throws DatabaseException {
    	
    	if($form.isSuccessfullyCommitted()) {
    		setRedirect("lectures/showCourseInstance/"+$path[0]);
 
    	}
    	
        if ($path.length > 0) {
		    List<CourseInstance> $cis = manager().getCourseInstance(
		            all(eq(CourseInstance.Id, Parse.parseInt($path[0], -1))));
		    if ($cis.size() > 0) {
		        CourseInstance $ci = $cis.get(0);
		
		        $ci.getInstanceOf();
		        $ci.getMainLecturer();
		        $ci.getProgram().getAcademicTerm();
		        List<CourseElement> $ce_req = manager().getCourseElement(all(eq(CourseElement.PartOf, $ci.getInstanceOf().getId())));
		        for(CourseElement $ce : $ce_req) {
		        	$ce.getType();
		        }
		        
		        /* CEI related */
		        List<CourseElementInstance> $ceis = manager().getCourseElementInstance(
		            all(eq(CourseElementInstance.CourseInstance, $ci.getId())));
		        List<ElementInstanceTakesPlaceInRoom> $eitpir = new ArrayList<ElementInstanceTakesPlaceInRoom>();
		        for (CourseElementInstance $cei_i : $ceis) {
		            $cei_i.getLecturer();
		            $cei_i.getCourseElement().getType();
		            $cei_i.getStartingTimeslot().getDay();
		            $eitpir.addAll($cei_i.whereSubjectOfElementInstanceTakesPlaceInRoom());
		        }
		        for(ElementInstanceTakesPlaceInRoom $x : $eitpir) {
		        	$x.getRoom().getBuilding().getDepartment();
		        }
		        
		        put("ci", $ci);
		        put("ce_req", $ce_req);
		        
		        put("ceis", $ceis);
		        put("eitpir", $eitpir);
		        
	        	Person $p = (Person) session().getUser();
	        	put("myenrolls", $p.whereSubjectOfPersonTakesPartInElementInstance());
	        	if($action.equals("enroll")) {
		        	put("mode", "buildForm");
		        }
		        return;
		    }
		} else {
		    setRedirect("admin/listCourseInstances/");
		}
    }
    
    
    @Action(template = "admin/admin.showTimetable.room.xsl")
    public void showRoom(final String[] $path) throws DatabaseException {

        if ($path.length > 0) {
            List<Room> $rooms = manager().getRoom(
                    any(eq(Room.Id, Parse.parseInt($path[0], -1)),
                            eq(Room.Name, $path[0])));
            if ($rooms.size() > 0) {
                /* Roomrelated */
                Room $r = $rooms.get(0);
                $r.getBuilding().getDepartment();
                put("room", $r);
                put("myroomfeature", $r.whereSubjectOfRoomProvidesFeature());
                put("feature", manager().getFeature());

                /* CEI related (PS, CI, LECTURER) */
                List<ProposedScheduling> $ps = manager().getProposedScheduling(eq("room", $r.getId()));

                for (ProposedScheduling $ps_i : $ps) {
                    $ps_i.getElementInstance().getCourseInstance().getInstanceOf();
                    $ps_i.getElementInstance().getCourseElement().getType();
                    $ps_i.getElementInstance().getLecturer();
                }
                put("proposedScheduling", $ps);

                /* TIMETABLEGRID-RELATED */
                put("days", manager().getDay());
                put("timeslots", manager().getTimeslot());

                put("editable", 0);
                return;
            } else {
            }
        } else {
        }
    }    
}
