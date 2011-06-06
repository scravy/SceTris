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
import static de.fu.weave.orm.filters.Filters.isNull;
import static de.fu.weave.orm.filters.Filters.sort;
import static de.fu.weave.orm.filters.Filters.unEq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.fu.junction.Parse;
import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Configuration;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseAttribute;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseElementType;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Role;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Year;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.web.forms.ChangeCurrentAcademicTerm;
import de.fu.scetris.web.forms.DeleteRoles;
import de.fu.scetris.web.forms.DeleteUsers;
import de.fu.scetris.web.forms.EditAcademicTerm;
import de.fu.scetris.web.forms.EditBuilding;
import de.fu.scetris.web.forms.EditCET;
import de.fu.scetris.web.forms.EditCourse;
import de.fu.scetris.web.forms.EditCourseElementInstanceMetadata;
import de.fu.scetris.web.forms.EditDepartment;
import de.fu.scetris.web.forms.EditFeature;
import de.fu.scetris.web.forms.EditProgramStepCI;
import de.fu.scetris.web.forms.EditTimetableByLecturer;
import de.fu.scetris.web.forms.EditTimetableByRoom;
import de.fu.scetris.web.forms.EditUser;
import de.fu.scetris.web.forms.EditYear;
import de.fu.scetris.web.forms.NewAcademicTerm;
import de.fu.scetris.web.forms.NewAttribute;
import de.fu.scetris.web.forms.NewBuilding;
import de.fu.scetris.web.forms.NewCET;
import de.fu.scetris.web.forms.NewCourse;
import de.fu.scetris.web.forms.NewCourseAttribute;
import de.fu.scetris.web.forms.NewCourseElement;
import de.fu.scetris.web.forms.NewCourseElementInstance;
import de.fu.scetris.web.forms.NewCourseElementInstanceMetadata;
import de.fu.scetris.web.forms.NewDepartment;
import de.fu.scetris.web.forms.NewFeature;
import de.fu.scetris.web.forms.NewPrivilege;
import de.fu.scetris.web.forms.NewPrivileges;
import de.fu.scetris.web.forms.NewProgramStep1;
import de.fu.scetris.web.forms.NewProgramStepCI;
import de.fu.scetris.web.forms.NewProgramStepMainLecturer;
import de.fu.scetris.web.forms.NewRole;
import de.fu.scetris.web.forms.NewRoom;
import de.fu.scetris.web.forms.NewUser;
import de.fu.scetris.web.forms.NewYear;
import de.fu.scetris.web.forms.QuickEditBuildings;
import de.fu.scetris.web.forms.QuickEditDepartments;
import de.fu.scetris.web.forms.SchedulingControl;
import de.fu.scetris.web.forms.ShowTimetableByLecturer;
import de.fu.scetris.web.forms.ShowTimetableByRoom;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A module providing administrative tasks.
 * <p>
 * 
 */
@Author({ "Julian Fleischer", "Andre Zoufahl", "David Bialik" })
public class Admin extends FriggModule<RelationManager> {

    /**
     * The standard constructor.
     * 
     * @param parent
     */
    public Admin(final ScetrisServlet parent) {
        super(parent);
    }

    /**
     * The first page of the admin module.
     * <p>
     * This is the starting point for administrative tasks. Basically there is nothing on this page,
     * except that the menu is expanded over the whole page. However, since this is a presentational
     * issue the method does not contain any code. All important stuff is to be found in the
     * XSL-templates `admin/admin.xsl`.
     */
    @Action(template = "admin/admin.xsl")
    public void _default() {

    }

    /**
     * The administration pane for course management.
     */
    @Action(template = "admin/admin.courses.xsl")
    public void courses() {

    }

    /**
     * The page to create an {@see de.fu.scetris.data.AcademicTerm}.
     * 
     * @param $academicTerm
     *            A {@see de.fu.weave.Form} to create a new academic term.
     */
    @Action(template = "admin/admin.createAcademicTerm.xsl", requiresPrivilege = "admin.createAcademicTerm")
    public void createAcademicTerm(final NewAcademicTerm $academicTerm) {
        put("newAcademicTerm", $academicTerm);
    }

    /**
     * The page to create a new {@see de.fu.scetris.data.Attribute}.
     * 
     * @param $attribute
     *            A {@see de.fu.weave.Form} to create a new attribute.
     */
    @Action(template = "admin/admin.createAttribute.xsl", requiresPrivilege = "admin.createAttribute")
    public void createAttribute(final NewAttribute $attribute) {
        if ($attribute.isSuccessfullyCommitted()) {
            $attribute.reset();
            setRedirect("admin/sysconfig/");
        }
        put("newAttribute", $attribute);
    }

    /**
     * A page to create a new {@see de.fu.scetris.data.Building}.
     * 
     * @param $newBuildingForm
     *            A {@see de.fu.weave.Form} to create a new Building.
     */
    @Action(template = "admin/admin.createBuilding.xsl", requiresPrivilege = "admin.createBuilding")
    public void createBuilding(final NewBuilding $newBuildingForm) {
        put("newAttribute", $newBuildingForm);
    }

    /**
     * The page to create a new {@see de.fu.scetris.data.CourseElementType}.
     * 
     * @param $cet
     *            A {@see de.fu.weave.Form} to create a new CourseElementType.
     */
    @Action(template = "admin/admin.createCET.xsl", requiresPrivilege = "admin.createCET")
    public void createCET(final NewCET $cet) {
        if ($cet.isSuccessfullyCommitted()) {
            $cet.reset();
        }
        put("newCET", $cet);
    }

    /**
     * The page to create a new {@see de.fu.scetris.data.Course}.
     * 
     * @param $course
     *            A {@see de.fu.weave.Form} to create a new Course.
     */
    @Action(template = "admin/admin.createCourse.xsl", requiresPrivilege = "admin.createCourse")
    public void createCourse(final NewCourse $course) throws Exception {
        if ($course.isSuccessfullyCommitted()) {
            $course.reset();
        }
        put("newCourse", $course);
        put("features", manager().getFeature());
    }

    /**
     * The page to create a new {@see de.fu.scetris.data.CourseAttribute}.
     * 
     * @param $courseAttribute
     *            A {@see de.fu.weave.Form} to create a new CourseAttribute
     */
    @Action(template = "admin/admin.createCourseAttribute.xsl", requiresPrivilege = "admin.createCourseAttribute")
    public void createCourseAttribute(final NewCourseAttribute $courseAttribute) {
        if ($courseAttribute.isSuccessfullyCommitted()) {
            $courseAttribute.reset();
            setRedirect("admin/sysconfig/");
        }
        put("newCourseAttribute", $courseAttribute);
    }

    /**
     * A page to create a new {@see de.fu.scetris.data.CourseElement}.
     * 
     * @param $course
     *            A {@see de.fu.weave.Form} to create the new CourseElement.
     */
    @Action(template = "admin/admin.createCourseElement.xsl", requiresPrivilege = "admin.createCourseElement")
    public void createCourseElement(final NewCourseElement $course) {
        if ($course.isSuccessfullyCommitted()) {
            $course.reset();
        }
        put("newCourseElement", $course);
    }

    @Action(template = "admin/admin.createCourseElementInstances.xsl", requiresPrivilege = "admin.createCourseElementInstances")
    public void createCourseElementInstances(final String[] $path, final NewCourseElementInstance $cei)
        throws DatabaseException {

        Logger $logger = Logger.getLogger(getClass());
        $logger.info("sessiondata CREATE \n" +
                        "count " + (Integer) session().getValue("cei_count") + "\n" +
                        "jump " + (Integer) session().getValue("cei_jump") + "\n" +
                        "ci " + (Integer) session().getValue("cei_ci") + "\n"
                );

        if ($cei.isSuccessfullyCommitted()) {
            /* LEITE WEITER ZU STEP2 - DATEN ZU CEI BEARBEITEN */
            setRedirect("admin/editCourseElementInstances/" + $path[0]);
        }

        if ($path.length > 0) {
            List<CourseInstance> $cis = manager().getCourseInstance(
                any(eq(CourseInstance.Id, Parse.parseInt($path[0], -1))));
            if ($cis.size() > 0) {
                CourseInstance $ci = $cis.get(0);
                session().setValue("cei_ci", $ci.getId());
                put("step", 1);
            }
        }
    }

    /**
     * 
     * @param $privilege
     * @param $privileges
     */
    @Action(template = "admin/admin.createPrivilege.xsl", requiresPrivilege = "admin.createPrivilege")
    public void createPrivilege(final NewPrivilege $privilege,
            final NewPrivileges $privileges) {
        put("newPrivileges", $privileges);
        if ($privileges.isSuccessfullyCommitted()) {
            put("commitMessages", $privileges.$errors);
        } else {
            if ($privilege.isSuccessfullyCommitted()) {
                $privilege.reset();
            }
            put("newPrivilege", $privilege);
        }
    }

    /**
     * Idea: A person selects the data from 'program' and then in step2 (via redirect) enter data
     * about mainlecturer
     */
    @Action(template = "admin/admin.createProgram.xsl", requiresPrivilege = "admin.createProgram")
    public void createProgram(final NewProgramStep1 $program) {
        if ($program.isSuccessfullyCommitted()) {
            put("commitMessages", $program);
            /* LEITE WEITER ZU STEP2 - MAINLECTURER ANGEBEN */
            setRedirect("admin/createProgramCI/");
        }
        put("step", 1);
        put("newProgram", $program);
    }

    /**
     * A page to create a Program (goes over multiple pages and actions, {@see
     * #createProgram(NewProgramStep1)})
     * 
     * @param $program
     */
    @Action(template = "admin/admin.createProgram.xsl", requiresPrivilege = "admin.createProgram")
    public void createProgramCI(final NewProgramStepCI $program) {
        if ($program.isSuccessfullyCommitted()) {
            put("commitMessages", $program);
            /* SESSION LEEREN UND LEITE WEITER ZU STEP4 - MAINLECTURER ANGEBEN */
            session().setValue("newpro", null);
            setRedirect("admin/editProgramLecturer/" + $program.id);
        }
        put("step", 2);
        put("newProgram", $program);
    }

    /**
     * Create a new role
     * 
     * @param $role
     */
    @Action(template = "admin/admin.createRole.xsl", requiresPrivilege = "admin.createRole")
    public void createRole(final NewRole $role) {
        put("newRole", $role);
    }

    /**
     * Create a new room
     * 
     * @param $role
     */
    @Action(template = "admin/admin.createRoom.xsl", requiresPrivilege = "admin.createRoom")
    public void createRoom(final NewRoom $room) throws DatabaseException {
        put("newRoom", $room);
        put("features", manager().getFeature());
    }

    /**
     * 
     * @param $user
     */
    @Action(template = "admin/admin.createUser.xsl", requiresPrivilege = "admin.createUser")
    public void createUser(final NewUser $user) throws DatabaseException {
        if ($user.isSuccessfullyCommitted()) {
            $user.reset();
        }
        put("newUser", $user);
        put("attributes", manager().getAttribute());
    }

    @Action(template = "admin/admin.createYear.xsl", requiresPrivilege = "admin.createYear")
    public void createYear(final NewYear $role) {
        put("newYear", $role);
    }

    @Action(template = "admin/admin.editAcademicTerm.xsl", requiresPrivilege = "admin.editAcademicTerm")
    public void editAcademicTerm(final String[] $path,
            final EditAcademicTerm $form) throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            $form.setValues(manager().getAcademicTerm($form.id));
            put("editAcademicTerm", $form);
            return;
        }
        if ($path.length > 0) {
            List<AcademicTerm> $persons = manager().getAcademicTerm(
                    any(eq(AcademicTerm.Id, Parse.parseInt($path[0], -1)),
                            eq(AcademicTerm.Name, $path[0])));
            if ($persons.size() > 0) {
                put("editAcademicTerm", $form.setValues($persons.get(0)));
                return;
            }
        } else {
            put("editAcademicTerm", $form);
        }
        put("no-such-academicTerm");
    }

    @Action(template = "admin/admin.editBuilding.xsl", requiresPrivilege = "admin.editBuiding")
    public void editBuilding(final String[] $path, final EditBuilding $form)
            throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            $form.setValues(manager().getBuilding($form.id));
            put("editBuilding", $form);
            return;
        }
        if ($path.length > 0) {
            List<Building> $buildings = manager().getBuilding(
                    eq(Building.Id, Parse.parseInt($path[0], -1)));
            if ($buildings.size() > 0) {
                put("editBuilding", $form.setValues($buildings.get(0)));
                return;
            }
        } else {
            put("editBuilding", $form);
        }
        put("no-such-building");
    }

    @Action(template = "admin/admin.editCET.xsl", requiresPrivilege = "admin.editCET")
    public void editCET(final String[] $path, final EditCET $form)
            throws DatabaseException {
        if ($path.length > 0) {
            List<CourseElementType> $cets = manager().getCourseElementType(
                    eq(CourseElementType.Id, Parse.parseInt($path[0], -1)));
            if ($cets.size() > 0) {
                put("editCET", $form.setValues($cets.get(0)));
                return;
            }
        } else {
            put("editCET", $form);
        }
        put("no-such-courseElementType");
    }

    @Action(template = "admin/admin.editCourse.xsl", requiresPrivilege = "admin.editCourse")
    public void editCourse(final String[] $path, final EditCourse $form)
            throws DatabaseException {
        if ($path.length > 0) {
            List<Course> $courses = manager().getCourse(
                    eq(Course.Id, Parse.parseInt($path[0], -1)));
            if ($courses.size() > 0) {
                put("editCourse", $form.setValues($courses.get(0)));
                return;
            }
        } else {
            put("editCourse", $form);
        }
        put("no-such-course");
    }

    @Action(template = "admin/admin.createCourseElementInstances.xsl", requiresPrivilege = "admin.editCourseElementInstances")
    public void editCourseElementInstances(final String[] $path,
        final NewCourseElementInstanceMetadata $cei,
        final EditCourseElementInstanceMetadata $cei_x) throws DatabaseException {

        Logger $logger = Logger.getLogger(getClass());
        $logger.info("sessiondata EDIT \n" +
                        "count " + (Integer) session().getValue("cei_count") + "\n" +
                        "jump " + (Integer) session().getValue("cei_jump") + "\n" +
                        "ci " + (Integer) session().getValue("cei_ci") + "\n"
                );

        if ($cei.isSuccessfullyCommitted() || $cei_x.isSuccessfullyCommitted()) {
            /* FERTIG - SPRING ZUR LISTE DER CIs ZURÜCK */
            Integer tmp = (Integer) session().getValue("cei_jump");
            if (tmp != null) {
                session().setValue("cei_jump", null);
                session().setValue("cei_count", null);
                setRedirect("admin/listCourseInstanceFromProgram/" + tmp.intValue());
            }
        }

        if ($path.length > 0) {
            List<CourseInstance> $cis = manager().getCourseInstance(
                any(eq(CourseInstance.Id, Parse.parseInt($path[0], -1))));
            if ($cis.size() > 0) {
                CourseInstance $ci = $cis.get(0);

                List<CourseElement> $ces = manager().getCourseElement(
                    all(eq(CourseElement.PartOf, $ci.getInstanceOf().getId())));
                List<Person> $lecturer = manager().getPerson(
                    all(eq(Person.HomeDepartment, $ci.getProgram().getDepartment().getId())));
                Integer $count = (Integer) session().getValue("cei_count");

                List<Feature> $possiblefeatures = manager().getFeature();
                
                if (($ces.size() > 0) && ($lecturer.size() > 0)) {
                    put("courseelements", $ces);
                    put("lecturer", $lecturer);

                    if (($count != null) && ($count.intValue() > 0)) {
                        put("count", $count.intValue());
                        put("mode", "firstSetup");
                    } else {
                        List<CourseElementInstance> $ceis = manager().getCourseElementInstance(
                            eq(CourseElementInstance.CourseInstance, $ci.getId()));
                        List<ElementInstanceRequiresFeature> $needs = new ArrayList<ElementInstanceRequiresFeature>();
                        for(CourseElementInstance $x : $ceis) {
                        	$needs.addAll($x.whereSubjectOfElementInstanceRequiresFeature());
                        }
                        put("needs", $needs);
                        put("ceis", $ceis);
                        put("mode", "changeExisting");
                    }
                }
                put("posfeature", $possiblefeatures);
                put("durations", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
                put("step", 2);
            }
        }
    }

    @Action(template = "admin/admin.editDepartment.xsl", requiresPrivilege = "admin.editDepartment")
    public void editDepartment(final String[] $path,
            final EditDepartment $department) throws DatabaseException {
        if ($department.isSuccessfullyCommitted()) {
            put("editDepartment", $department);
            return;
        }
        if ($path.length > 0) {
            List<Department> $deps = manager().getDepartment(
                    eq(Person.Id, Parse.parseInt($path[0], -1)));
            if ($deps.size() > 0) {
                put("editDepartment", $department.setValues($deps.get(0)));
                return;
            }
        }
    }

    @Action(template = "admin/admin.createProgram.xsl", requiresPrivilege = "admin.editProgram")
    public void editProgram(final String[] $path, final EditProgramStepCI $form)
        throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            /* LEITE WEITER ZU STEP4 - MAINLECTURER ANGEBEN */
            setRedirect("admin/editProgramLecturer/" + $form.id);
            return;
        }

        if ($path.length > 0) {
            Program $program = manager().getProgram(Parse.parseInt($path[0], -1));
            if ($program != null) {
                $form.setValues($program);
                put("newProgram", $form);
            }
        }
        put("step", 2);
    }

    @Action(template = "admin/admin.createProgram.xsl", requiresPrivilege = "admin.editProgram")
    public void editProgramLecturer(final String[] $path, final NewProgramStepMainLecturer $form)
        throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            /* crazy stuff happens here maybe */
            put("ci", $form.ci);
            put("ml", $form.ml);
            setRedirect("admin/listPrograms/");
            return;
        }

        if ($path.length > 0) {
            Program $program = manager().getProgram(Parse.parseInt($path[0], -1));
            if ($program != null) {
                List<CourseInstance> $instances = manager().getCourseInstance(
                    all(eq(CourseInstance.Program, $program.getId())));
                for (CourseInstance $ini : $instances) {
                    $ini.getInstanceOf();
                    $ini.getProgram().getAcademicTerm();
                }
                put("courseinstance", $instances);
                put("lecturer",
                    manager().getPerson(
                        all(isNull(Person.Deleted),
                            eq(Person.HomeDepartment, $program.getDepartment().getId()),
                            sort(Person.LastName), eq(Person.IsLecturer, true))));
            }
        }
        put("step", 4);
    }

    @Action(template = "admin/admin.editRole.xsl", requiresPrivilege = "admin.editRole")
    public void editRole(final String[] $path, final EditUser $form)
            throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            put("editRole", $form);
            return;
        }
        if ($path.length > 0) {
            List<Person> $persons = manager().getPerson(
                    any(eq(Person.Id, Parse.parseInt($path[0], -1)),
                            eq(Person.LoginName, $path[0])));
            if ($persons.size() > 0) {
                put("editRole", $form.setValues($persons.get(0)));
                return;
            }
        }
        put("no-such-role");
    }

    @Action(template = "admin/admin.editRoom.xsl", requiresPrivilege = "admin.editRoom")
    public void editRoom() {

    }

    @Action(template = "admin/admin.editTimetable.lecturer.xsl", requiresPrivilege = "admin.editTimetable.lecturer")
    public void editTimetableByLecturer(final String[] $path, final EditTimetableByLecturer $form)
        throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            setRedirect("admin/showTimetableByLecturer/" + $path[0] + "#timetable");
        }

        if ($path.length > 0) {
            List<Person> $persons = manager().getPerson(
                any(eq(Person.Id, Parse.parseInt($path[0], -1)), eq(Person.LoginName, $path[0])));
            if ($persons.size() > 0) {

                Person $p = $persons.get(0);
                /* Person related */
                List<Privilege> $privileges = $p.objectsOfPersonHasPrivilege();
                List<Role> $roles = $p.objectsOfPersonHasRole();
                List<CourseInstance> $enrolledCourseInstances = $p
                        .objectsOfPersonEnrolledInCourseInstance();
                put("person", $p);
                put("privileges", $privileges);
                put("roles", $roles);
                put("enrolledCourseInstances", $enrolledCourseInstances);

                /* Lecturer related */
                List<CourseElementInstance> $ceis = manager().getCourseElementInstance(
                    any(eq("lecturer", $p.getId())));
                List<ProposedScheduling> $ps = new ArrayList<ProposedScheduling>();

                for (CourseElementInstance $cei : $ceis) {
                    $ps.addAll(manager()
                            .getProposedScheduling(any(eq("element_instance", $cei.getId()))));
                }

                for (ProposedScheduling $ps_i : $ps) {
                    $ps_i.getElementInstance().getCourseInstance().getInstanceOf();
                }
                put("proposedScheduling", $ps);

                /* TIMETABLEGRID-RELATED */
                put("days", manager().getDay());
                put("timeslots", manager().getTimeslot());

                put("enableEdit", 1);
                if (session().getValue("x_ps") != null) {
                    ProposedScheduling $tmp = (ProposedScheduling) session().getValue("x_ps");
                    $tmp.getElementInstance();
                    put("swapMe", $tmp);
                }
                return;
            }
        }
        put("no-such-user");
    }

    @Action(template = "admin/admin.editTimetable.room.xsl", requiresPrivilege = "admin.showTimetable.room")
    public void editTimetableByRoom(final String[] $path, final EditTimetableByRoom $form)
        throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            // if(session().getValue("x_newroom")!=null) {
            if ($form.changeRoom > 0) {
                // Room $r = (Room) session().getValue("x_newroom");
                $form.reset();
                setRedirect("admin/editTimetableByRoom/" + $form.changeRoom + "#timetable");
                return;
            } else {
                setRedirect("admin/showRoom/" + $path[0] + "#timetable");
            }
        }

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

                /* MAYBE-TODO:: filter possible rooms */
                put("availableRooms", manager().getRoom());

                put("editable", 1);
                if (session().getValue("x_ps") != null) {
                    ProposedScheduling $tmp = (ProposedScheduling) session().getValue("x_ps");
                    $tmp.getElementInstance();
                    put("swapMe", $tmp);
                }
                return;
            } else {
                setRedirect("admin/listRooms");
            }
        } else {
            setRedirect("admin/listRooms");
        }
    }

    @Action(template = "admin/admin.editUser.xsl", requiresPrivilege = "admin.editUser")
    public void editUser(final String[] $path, final EditUser $form)
            throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            $form.setValues(manager().getPerson($form.id));
            put("editUser", $form);
            return;
        }
        if ($path.length > 0) {
            List<Person> $persons = manager().getPerson(
                    any(eq(Person.Id, Parse.parseInt($path[0], -1)),
                            eq(Person.LoginName, $path[0])));
            if ($persons.size() > 0) {
                $form.setValues($persons.get(0));
                put("editUser", $form);
                return;
            }
        } else {
            put("editUser", $form);
        }
        put("no-such-user");
    }

    @Action(template = "admin/admin.editYear.xsl", requiresPrivilege = "admin.editYear")
    public void editYear(final String[] $path, final EditYear $form)
            throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            $form.setValues(manager().getYear($form.id));
            put("editYear", $form);
            return;
        }
        if ($path.length > 0) {
            List<Year> $persons = manager().getYear(
                    any(eq(Year.Id, Parse.parseInt($path[0], -1)),
                            eq(Year.Name, $path[0])));
            if ($persons.size() > 0) {
                $form.setValues($persons.get(0));
                put("editYear", $form);
                return;
            }
        }
        put("no-such-year");
    }

    @Action(template = "admin/admin.imex.xsl")
    public void export() throws SQLException, DatabaseException {
        setBinaryData(manager().export(false).getBinaryStream(), "text/xml", "export.xml");
    }

    @Action(template = "admin/admin.facilities.xsl", requiresPrivilege = "admin.facilities")
    public void facilities(final QuickEditDepartments $quickDep,
            final QuickEditBuildings $quickBuilding) throws DatabaseException {
        boolean $hasDepartments = manager().getDepartment(0, 1).size() > 0;
        boolean $hasBuildings = manager().getBuilding(0, 1).size() > 0;
        if ($quickDep.isSuccessfullyCommitted()) {
            setRedirect("admin/editDepartment/" + $quickDep.department);
            return;
        }
        if ($quickBuilding.isSuccessfullyCommitted()) {
            setRedirect("admin/editBuilding/" + $quickBuilding.building);
            return;
        }
        if ($hasDepartments) {
            put("quickEditDepartments", $quickDep);
        }
        if ($hasBuildings) {
            put("quickEditBuildings", $quickBuilding);
        }
    }

    @Action(template = "admin/admin.imex.xsl")
    public void imex() {

    }

    @Action(template = "admin/admin.listAcademicTerms.xsl", requiresPrivilege = "admin.listAcademicTerms")
    public void listAcademicTerms(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num)
        // final DeleteUsers $deleteUsers)
        throws DatabaseException {
        // put("deleteUsers", $deleteUsers);
        put("academicTerms", manager().getAcademicTerm($offset, $num));
    }

    @Action(template = "admin/admin.listAttributes.xsl", requiresPrivilege = "admin.listAttributes")
    public void listAttributes() {

    }

    @Action(template = "admin/admin.listBuildings.xsl", requiresPrivilege = "admin.listBuildings")
    public void listBuildings(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num,
            final NewBuilding $building) throws DatabaseException {
        if ($building.isSuccessfullyCommitted()) {
            $building.reset();
        }
        put("newBuilding", $building);
        // put("departments", manager().getDepartment());
        put("buildings",
                manager().getBuilding(
                        $offset,
                        $num,
                        all(
                            // FIXME: The following is a workarouond, since filters
                            // which do not contain a
                            // *real* filter (i.e. consisting of several
                            // sort()-Filters) are not yet applied
                            // correctly
                            unEq(Building.Id, -1), sort(Building.Department),
                                sort(Building.Address), sort(Building.Name))));
    }

    @Action(template = "admin/admin.listCET.xsl", requiresPrivilege = "admin.listCET")
    public void listCET(
         @Arg(name = "offset", intDefault = 0) final int $offset,
         @Arg(name = "perPage", intDefault = 100) final int $num) throws DatabaseException {
        put("cets",
                     manager().getCourseElementType($offset, $num, sort(CourseElementType.Name)));
    }

    @Action(template = "admin/admin.listCourseAttributes.xsl", requiresPrivilege = "admin.listCourseAttributes")
    public void listCourseAttributes(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num) throws DatabaseException {
        put("coursesAttributes",
             manager().getCourseAttribute($offset, $num, sort(CourseAttribute.Name)));
    }

    @Action(template = "admin/admin.listCourseElements.xsl", requiresPrivilege = "admin.listCourseElements")
    public void listCourseElements(
         @Arg(name = "offset", intDefault = 0) final int $offset,
         @Arg(name = "perPage", intDefault = 100) final int $num) throws DatabaseException {
        List<CourseElement> temp = manager().getCourseElement($offset, $num, sort(CourseElement.Name));
        for (CourseElement x : temp) {
            x.getPartOf();
            x.getType();
        }
        put("courseElements", temp);
    }

    @Action(template = "admin/admin.listCourseInstances.xsl", requiresPrivilege = "admin.editProgram")
    public void listCourseInstanceFromProgram(final String[] $path) throws DatabaseException {

        if ($path.length > 0) {
            Program $program = manager().getProgram(Parse.parseInt($path[0], -1));
            if ($program != null) {
                List<CourseInstance> $putme = manager().getCourseInstance(
                    eq(CourseInstance.Program, $program.getId()));
                for (CourseInstance $x : $putme) {
                    $x.getMainLecturer();
                    $x.getInstanceOf();
                }
                put("courseInstances", $putme);
                session().setValue("cei_count", null);
                session().setValue("cei_jump", Parse.parseInt($path[0], -1));
            }
        } else {
            setRedirect("admin/listCourseInstances");
        }
    }

    /* Works on proposedSchedulings */
    @Action(template = "admin/admin.listCourseInstance.xsl")
    public void listCourseInstances() throws DatabaseException {
        // TODO :: david plz
    }

    @Action(template = "admin/admin.listCourses.xsl", requiresPrivilege = "admin.listCourses")
    public void listCourses(
         @Arg(name = "offset", intDefault = 0) final int $offset,
         @Arg(name = "perPage", intDefault = 100) final int $num) throws DatabaseException {
        put("courses",
                     manager().getCourse($offset, $num, sort(Course.Name)));
        put("department", manager().getDepartment());
    }

    @Action(template = "admin/admin.listDepartments.xsl", requiresPrivilege = "admin.listDepartments")
    public void listDepartments(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num,
            final NewDepartment $department) throws DatabaseException {
        if ($department.isSuccessfullyCommitted()) {
            $department.reset();
        }
        put("newDepartment", $department);
        put("departments",
                manager().getDepartment($offset, $num, sort(Department.Name)));
    }

    /**
     * A page to list and create a new {@see de.fu.scetris.data.Feature}.
     * <p>
     * A feature is a property that a room may have in certain quantities (like 1 overhead projector
     * and 12 workstations).
     * 
     * @param $newFeature
     *            A {@see de.fu.weave.Form} to create the new Feature.
     */
    @Action(template = "admin/admin.listFeatures.xsl", requiresPrivilege = "admin.listFeatures")
    public void listFeatures(final String[] $path,
        final NewFeature $newFeature,
        final EditFeature $editFeature) throws DatabaseException {
        if (($path.length > 0) && !$editFeature.isSuccessfullyCommitted()) {
            $editFeature.setValues(manager().getFeature(Parse.parseInt($path[0], -1)));
            put("editFeature", $editFeature);
        } else {
            put("features", manager().getFeature(sort(Feature.Name)));
            put("newFeature", $newFeature);
            if ($editFeature.isSuccessfullyCommitted()) {
                put("featureChanged", $editFeature);
            }
        }
    }

    @Action(template = "admin/admin.listPrivileges.xsl", requiresPrivilege = "admin.listPrivileges")
    public void listPrivileges() throws DatabaseException {
        put("privileges", manager().getPrivilege(sort(Privilege.Name)));
    }

    @Action(template = "admin/admin.listPrograms.xsl", requiresPrivilege = "admin.listPrograms")
    public void listPrograms() throws DatabaseException {
        List<Program> $programs = manager().getProgram(sort(Program.AcademicTerm));
        for (Program $x : $programs) {
            $x.getAcademicTerm();
            $x.getDepartment();
        }
        put("programs", $programs);
    }

    @Action(template = "admin/admin.listRoles.xsl", requiresPrivilege = "admin.listRoles")
    public void listRoles(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num,
            final DeleteRoles $deleteRoles) throws DatabaseException {
        manager().beginTransaction();
        put("deleteRoles", $deleteRoles);
        List<Role> $roles = manager().getRole($offset, $num);
        put("roles", $roles);
        for (Role $role : $roles) {
            List<Privilege> $privileges = $role.objectsOfRoleImpliesPrivilege();
            put("role" + $role.id() + "privilege", $privileges);
        }
        manager().commitTransaction();
    }

    @Action(template = "admin/admin.listRooms.xsl", requiresPrivilege = "admin.listRooms")
    public void listRooms(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num)
            throws DatabaseException {
        put("rooms", manager().getRoom($offset, $num, all(
            // FIXME: The following is a workarouond, since filters
            // which do not contain a
            // *real* filter (i.e. consisting of several
            // sort()-Filters) are not yet applied
            // correctly
            unEq(Room.Id, -1), sort(Room.Building))));
    }

    @Action(template = "admin/admin.listConfig.xsl", requiresPrivilege = "admin.setSystemconfig")
    public void listSystemconfig(final String[] $path, final ChangeCurrentAcademicTerm $sc)
        throws DatabaseException {
        List<AcademicTerm> $tmp = manager().getAcademicTerm();
        put("ats", $tmp);
        List<Configuration> $name = manager()
                .getConfiguration(all(eq(Configuration.Key, "currentTerm")));
        if ($name.size() > 0) {
            List<AcademicTerm> $current = manager().getAcademicTerm(
                all(eq(AcademicTerm.Name, $name.get(0).getValue())));
            if ($current.size() > 0) {
                put("currentAT", $current.get(0));
            }
        }
    }

    @Action(template = "admin/admin.listUsers.xsl", requiresPrivilege = "admin.listUsers")
    public void listUsers(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num,
            @Arg(name = "pattern", stringDefault = "") final String $pattern,
            final DeleteUsers $deleteUsers) throws DatabaseException {

        List<Person> $mass;
        List<Person> $hits = new ArrayList<Person>();

        if (!$pattern.equals("")) {
            $mass = manager().getPerson(all(isNull(Person.Deleted), sort(Person.LoginName)));
            put("pattern", $pattern);
            for (Person $x : $mass) {
                if ($x.loginName().contains($pattern)) {
                    $hits.add($x);
                }
            }

        } else {
            $mass = manager().getPerson($offset, $num,
                    all(isNull(Person.Deleted), sort(Person.LoginName)));
            $hits = $mass;

        }

        put("deleteUsers", $deleteUsers);
        put("users", $hits);
    }

    @Action(template = "admin/admin.listYears.xsl", requiresPrivilege = "admin.listYears")
    public void listYears(
            @Arg(name = "offset", intDefault = 0) final int $offset,
            @Arg(name = "perPage", intDefault = 100) final int $num)
            throws DatabaseException {
        put("years", manager().getYear($offset, $num));
    }

    @Action(template = "admin/admin.scheduling.control.xsl", requiresPrivilege = "admin.scheduling.control")
    public void schedulerPanel(final String[] $path, final SchedulingControl $form,
            @Arg(name = "action", stringDefault = "") final String $action
            ) throws Exception {

        if ($form.isSuccessfullyCommitted()) {
            /* person choosed program by at/dept */
            setRedirect("admin/schedulerPanel/" + $form.$showMe.getId());
        }

        final SchedulerManager schedulerManager = SchedulerManager.getInstance(manager());
        if (schedulerManager.isRunning()) {
            /* scheduler is running, show progressbar */
            if ($action.equals("stop")) {
                schedulerManager.stopScheduler();
            }

            Program $current = schedulerManager.getCurrentProgramBeScheduling();
            $current.getAcademicTerm();
            $current.getDepartment();
            put("current", $current);

            put("mode", "scheduling");
        } else if (schedulerManager.isStopping()) {

        } else {
            /* no scheduling */
            if ($path.length > 0) {
                List<Program> $ps = manager().getProgram(eq(Program.Id, Parse.parseInt($path[0], -1)));
                if ($ps.size() > 0) {
                    /* existing program selected */
                    Program $p = $ps.get(0);

                    /* depending on action do this or that */
                    if ($action.equals("start")) {
                        schedulerManager.startScheduler($p, false);
                        setRedirect("admin/schedulerPanel");
                    } else if ($action.equals("resume")) {
                        schedulerManager.startScheduler($p, true);
                        setRedirect("admin/schedulerPanel");
                    } else if ($action.equals("freeze")) {
                        schedulerManager.freezeProgram($p);
                    } else if ($action.equals("publish")) {
                        schedulerManager.publishProgram($p);
                    }

                    put("current", $p);
                    put("mode", "showStatusInfo");

                    /* check whether scheduling for this program was once done */
                    List<CourseInstance> $x_ci = manager().getCourseInstance(0, 1,
                        eq(CourseInstance.Program, $p.getId()));
                    if ($x_ci.size() > 0) {
                        List<CourseElementInstance> $x_cei = manager().getCourseElementInstance(0, 1,
                            eq(CourseElementInstance.CourseInstance, $x_ci.get(0).getId()));
                        if ($x_cei.size() > 0) {
                            List<ProposedScheduling> $x_ps = manager().getProposedScheduling(0, 1,
                                eq(ProposedScheduling.ElementInstance, $x_cei.get(0).getId()));
                            if ($x_ps.size() > 0) {
                                /* this program was scheduled at least once, put some statistic info */
                                put("current_helper", "runonce");
                                put("lecturerOverlap", schedulerManager.getScheduleScore($p)
                                        .getCourseConflictsLecturer());
                                put("roomOverlap", schedulerManager.getScheduleScore($p)
                                        .getCourseConflictsRoom());
                                put("lecturer",
                                    manager().getPerson(
                                        all(isNull(Person.Deleted),
                                            eq(Person.HomeDepartment, $p.getDepartment().getId()),
                                            eq(Person.IsLecturer, true))));
                                List<Room> $r = manager().getRoom();
                                for (Room $x : $r) {
                                    $x.getBuilding();
                                }
                                put("room", $r);
                            }
                        }
                    }

                } else {
                    /* no program found, redirect to selectpage with empty path */
                    setRedirect("admin/schedulerPanel");
                }
            } else {
                /* no program found, let user choose dept/at */
                put("academicTerms", manager().getAcademicTerm());
                put("departments", manager().getDepartment());
                put("mode", "selectProgram");
            }
        }

        /*
         * System.out.println("current status in Admin :: " + $role.schedulerstatus); List<String>
         * $opt = new ArrayList<String>(); if (schedulerManager.isReady()) { $opt.add("Start");
         * $opt.add("Resume"); $opt.add("Conflicts"); $opt.add("Publish"); put("status", "ready");
         * put("options", $opt); if ($role.schedulerstatus.equals("showConflicts")) {
         * System.out.println("putting some conflictinfos"); put("lecturerOverlap",
         * schedulerManager.getScheduleScore($role.possibleProgram) .getCourseConflictsLecturer());
         * put("roomOverlap", schedulerManager.getScheduleScore($role.possibleProgram)
         * .getCourseConflictsRoom());
         * 
         * } } else if (schedulerManager.isStopping()) { schedulerManager.stopScheduler();
         * put("status", "stopping");
         * 
         * } else if (schedulerManager.isRunning()) { $opt.add("Stop"); put("status", "running");
         * put("options", $opt);
         * 
         * put("scheduleScore", schedulerManager.getCurrentSchedulingScore());
         * 
         * }
         */
    }

    @Action(template = "admin/admin.scheduling.score.xsl")
    public void schedulerPanelScore() throws Exception {
        final SchedulerManager schedulerManager = SchedulerManager.getInstance(manager());
        if (schedulerManager.isRunning()) {
            put("scheduleScore", schedulerManager.getCurrentSchedulingScore());
        } else {
            if (schedulerManager.isReady()) {
                if (SchedulerManager.lastScheduled != null) {
                    put("lastScheduled", SchedulerManager.lastScheduled);
                }
                put("status", "ready");
            } else {
                put("status", "busy");
            }
        }
    }

    /* Works on proposedSchedulings */
    @Action(template = "admin/admin.showCourseInstance.xsl")
    public void showCourseInstance(final String[] $path) throws DatabaseException {
        if ($path.length > 0) {
            List<CourseElementInstance> $list_cei_from_ci = manager().getCourseElementInstance(
                all(eq(CourseElementInstance.CourseInstance, Parse.parseInt($path[0], -1))));
            if ($list_cei_from_ci.size() > 0) {
                List<ProposedScheduling> $list_ps_for_cei = new ArrayList<ProposedScheduling>();
                for (CourseElementInstance $cei : $list_cei_from_ci) {
                    List<ProposedScheduling> $tmp = manager().getProposedScheduling(1,
                        all(eq(ProposedScheduling.ElementInstance, $cei.getId())));
                    if ($tmp.size() > 0) {
                        ProposedScheduling $ps = $tmp.get(0);
                        $ps.getRoom().getBuilding().getDepartment();
                        $ps.getElementInstance().getLecturer();
                        $ps.getElementInstance().getCourseElement().getType();
                        $ps.getTimeslot().getDay();
                        $list_ps_for_cei.add($ps);
                    }
                }
                CourseInstance $ci = manager().getCourseInstance(Parse.parseInt($path[0], -1));
                $ci.getMainLecturer();
                $ci.getInstanceOf();
                $ci.getProgram().getAcademicTerm();

                put("ci", $ci);
                put("proposals", $list_ps_for_cei);
            }
        } else {
            setRedirect("admin/listCourseInstances/");
        }
    }

    @Action(template = "admin/admin.showDepartment.xsl", requiresPrivilege = "admin.showDepartment")
    public void showDepartment() {

    }

    @Action(template = "admin/admin.showRole.xsl", requiresPrivilege = "admin.showRole")
    public void showRole() {

    }

    @Action(template = "admin/admin.showTimetable.room.xsl", requiresPrivilege = "admin.showTimetable.room")
    public void showRoom(final String[] $path, final ShowTimetableByRoom $form) throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            setRedirect("admin/editTimetableByRoom/" + $path[0] + "#timetable");
        }

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

                put("editable", 1);
                return;
            } else {
                setRedirect("admin/listRooms");
            }
        } else {
            setRedirect("admin/listRooms");
        }
    }

    @Action(template = "admin/admin.showTimetable.lecturer.xsl", requiresPrivilege = "admin.showTimetable.lecturer")
    public void showTimetableByLecturer(final String[] $path, final ShowTimetableByLecturer $form)
        throws DatabaseException {
        if ($form.isSuccessfullyCommitted()) {
            setRedirect("admin/editTimetableByLecturer/" + $path[0] + "#timetable");
        }

        if ($path.length > 0) {
            List<Person> $persons = manager().getPerson(
                any(eq(Person.Id, Parse.parseInt($path[0], -1)), eq(Person.LoginName, $path[0])));
            if ($persons.size() > 0) {
                Person $p = $persons.get(0);
                /* Person related */
                List<Privilege> $privileges = $p.objectsOfPersonHasPrivilege();
                List<Role> $roles = $p.objectsOfPersonHasRole();
                List<CourseInstance> $enrolledCourseInstances = $p
                        .objectsOfPersonEnrolledInCourseInstance();
                put("person", $p);
                put("privileges", $privileges);
                put("roles", $roles);
                put("enrolledCourseInstances", $enrolledCourseInstances);

                /* Lecturer related */
                List<CourseElementInstance> $ceis = manager().getCourseElementInstance(
                    any(eq("lecturer", $p.getId())));
                List<ProposedScheduling> $ps = new ArrayList<ProposedScheduling>();
                List<Room> $xroom = new ArrayList<Room>();

                for (CourseElementInstance $cei : $ceis) {
                    $ps.addAll(manager()
                            .getProposedScheduling(any(eq("element_instance", $cei.getId()))));
                }

                for (ProposedScheduling $ps_i : $ps) {
                    $ps_i.getElementInstance().getCourseInstance().getInstanceOf();
                    $ps_i.getElementInstance().getCourseElement().getType();
                    $xroom.add($ps_i.getRoom());
                }

                for (Room $tmp : $xroom) {
                    $tmp.getBuilding();
                }

                put("proposedScheduling", $ps);
                put("tpir", $xroom);

                /* TIMETABLEGRID-RELATED */
                put("days", manager().getDay());
                put("timeslots", manager().getTimeslot());

                put("enableEdit", 1);
                return;
            }
        }
        put("no-such-user");

    }

    @Action(template = "admin/admin.showUser.xsl", requiresPrivilege = "admin.showUser")
    public void showUser(final String[] $path) throws DatabaseException {
        if ($path.length > 0) {
            List<Person> $persons = manager().getPerson(
                    any(eq(Person.Id, Parse.parseInt($path[0], -1)),
                            eq(Person.LoginName, $path[0])));
            if ($persons.size() > 0) {
                Person $p = $persons.get(0);
                put("person", $p);
                List<Privilege> $privileges = $p.objectsOfPersonHasPrivilege();
                List<Role> $roles = $p.objectsOfPersonHasRole();
                List<Course> $courses = $p.objectsOfPersonGivesCourse();
                List<CourseInstance> $enrolledCourseInstances = $p
                        .objectsOfPersonEnrolledInCourseInstance();
                List<CourseInstance> $courseInstances = $p
                        .objectsOfPersonEnrolledInCourseInstance();
                put("privileges", $privileges);
                put("roles", $roles);
                put("courses", $courses);
                put("enrolledCourseInstances", $enrolledCourseInstances);
                put("courseInstances", $courseInstances);
                return;
            }
        }
        put("no-such-user");
    }

    @Action(template = "admin/admin.sysconfig.xsl", requiresPrivilege = "admin.sysconfig")
    public void sysconfig() {

    }

    @Action(template = "admin/admin.users.xsl", requiresPrivilege = "admin.users")
    public void users() {

    }
}
