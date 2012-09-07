/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import static de.fu.weave.orm.filters.Filters.*;
import static de.fu.junction.functional.F.*;

import java.util.ArrayList;
import java.util.List;

import de.fu.junction.Parse;
import de.fu.junction.annotation.meta.Author;
import de.fu.junction.functional.Function;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Role;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Year;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.web.forms.DeleteRoles;
import de.fu.scetris.web.forms.DeleteUsers;
import de.fu.scetris.web.forms.EditAcademicTerm;
import de.fu.scetris.web.forms.EditBuilding;
import de.fu.scetris.web.forms.EditDepartment;
import de.fu.scetris.web.forms.EditUser;
import de.fu.scetris.web.forms.EditYear;
import de.fu.scetris.web.forms.NewAcademicTerm;
import de.fu.scetris.web.forms.NewAttribute;
import de.fu.scetris.web.forms.NewBuilding;
import de.fu.scetris.web.forms.NewCourseAttribute;
import de.fu.scetris.web.forms.NewDepartment;
import de.fu.scetris.web.forms.NewFeature;
import de.fu.scetris.web.forms.NewPrivilege;
import de.fu.scetris.web.forms.NewPrivileges;
import de.fu.scetris.web.forms.NewRole;
import de.fu.scetris.web.forms.NewRoom;
import de.fu.scetris.web.forms.NewUser;
import de.fu.scetris.web.forms.NewYear;
import de.fu.scetris.web.forms.QuickEditBuildings;
import de.fu.scetris.web.forms.QuickEditDepartments;
import de.fu.scetris.web.forms.SchedulingControl;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.Filter;

/**
 * A module providing administrative tasks.
 * <p>
 * The sole purpose of this module is to show option-panes and forms, the actual logic is contained
 * in {@see UserMgmt}, {@see ImportExport}, ...
 */
@Author({ "Julian Fleischer", "Andre Zoufahl", "David Bialik" })
public class Admin extends FriggModule<RelationManager> {

	public Admin(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "admin/admin.xsl")
	public void _default() {

	}

	@Action(template = "admin/admin.courses.xsl")
	public void courses() {

	}

	@Action(template = "admin/admin.createAcademicTerm.xsl")
	public void createAcademicTerm(final NewAcademicTerm $academicTerm) {
		put("newAcademicTerm", $academicTerm);
	}

	@Action(template = "admin/admin.createAttribute.xsl")
	public void createAttribute(final NewAttribute $attribute) {
		put("newAttribute", $attribute);
	}

	@Action(template = "admin/admin.createBuilding.xsl")
	public void createBuilding(final NewBuilding $attribute) {
		put("newAttribute", $attribute);
	}

	@Action(template = "admin/admin.createCourseAttribute.xsl")
	public void createCourseAttribute(final NewCourseAttribute $courseAttribute) {
		if ($courseAttribute.isSuccessfullyCommitted()) {
			$courseAttribute.reset();
			setRedirect("admin/sysconfig/");
		}
		put("newCourseAttribute", $courseAttribute);
	}

	@Action(template = "admin/admin.createFeature.xsl")
	public void createFeature(final NewFeature $feature) {
		put("newFeature", $feature);
	}

	@Action(template = "admin/admin.createPrivilege.xsl")
	public void createPrivilege(final NewPrivilege $privilege,
			final NewPrivileges $privileges) {
		put("newPrivileges", $privileges);
		if ($privileges.isSuccessfullyCommitted())
			put("commitMessages", $privileges.$errors);
		else {
			if ($privilege.isSuccessfullyCommitted())
				$privilege.reset();
			put("newPrivilege", $privilege);
		}
	}

	@Action(template = "admin/admin.createRole.xsl")
	public void createRole(final NewRole $role) {
		put("newRole", $role);
	}

	@Action(template = "admin/admin.createRoom.xsl")
	public void createRoom(final NewRoom $role) {
		put("newRoom", $role);
	}

	@Action(template = "admin/admin.createUser.xsl")
	public void createUser(final NewUser $user) {
		if ($user.isSuccessfullyCommitted())
			$user.reset();
		put("newUser", $user);
	}

	@Action(template = "admin/admin.createYear.xsl")
	public void createYear(final NewYear $role) {
		put("newYear", $role);
	}

	@Action(template = "admin/admin.editAcademicTerm.xsl")
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
		} else
			put("editAcademicTerm", $form);
		put("no-such-academicTerm");
	}

	@Action(template = "admin/admin.editBuilding.xsl")
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
		} else
			put("editBuilding", $form);
		put("no-such-building");
	}

	@Action(template = "admin/admin.editDepartment.xsl")
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

	@Action(template = "admin/admin.editRole.xsl")
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

	@Action(template = "admin/admin.editRoom.xsl")
	public void editRoom() {

	}

	@Action(template = "admin/admin.editUser.xsl")
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
		} else
			put("editUser", $form);
		put("no-such-user");
	}

	@Action(template = "admin/admin.editYear.xsl")
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

	@Action(template = "admin/admin.facilities.xsl")
	public void facilities(final QuickEditDepartments $quickDep,
			final QuickEditBuildings $quickBuilding) {
		if ($quickDep.isSuccessfullyCommitted()) {
			setRedirect("admin/editDepartment/" + $quickDep.department);
			return;
		}
		if ($quickBuilding.isSuccessfullyCommitted()) {
			setRedirect("admin/editBuilding/" + $quickBuilding.building);
			return;
		}

		put("quickEditDepartments", $quickDep);
		put("quickEditBuildings", $quickBuilding);
	}

	@Action(template = "admin/admin.imex.xsl")
	public void imex() {

	}

	@Action(template = "admin/admin.listAcademicTerms.xsl")
	public void listAcademicTerms(
			@Arg(name = "offset", intDefault = 0) final int $offset,
			@Arg(name = "perPage", intDefault = 100) final int $num)
		// final DeleteUsers $deleteUsers)
		throws DatabaseException {
		// put("deleteUsers", $deleteUsers);
		put("academicTerms", manager().getAcademicTerm($offset, $num));
	}

	@Action(template = "admin/admin.listAttributes.xsl")
	public void listAttributes() {

	}

	@Action(template = "admin/admin.listBuildings.xsl")
	public void listBuildings(
			@Arg(name = "offset", intDefault = 0) final int $offset,
			@Arg(name = "perPage", intDefault = 100) final int $num,
			final NewBuilding $building) throws DatabaseException {
		if ($building.isSuccessfullyCommitted())
			$building.reset();
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

	@Action(template = "admin/admin.listCourseAttributes.xsl")
	public void listCourseAttributes() {

	}

	@Action(template = "admin/admin.listCourses.xsl")
	public void listCourses() {

	}

	@Action(template = "admin/admin.listDepartments.xsl")
	public void listDepartments(
			@Arg(name = "offset", intDefault = 0) final int $offset,
			@Arg(name = "perPage", intDefault = 100) final int $num,
			final NewDepartment $department) throws DatabaseException {
		if ($department.isSuccessfullyCommitted())
			$department.reset();
		put("newDepartment", $department);
		put("departments",
				manager().getDepartment($offset, $num, sort(Department.Name)));
	}

	@Action(template = "admin/admin.listFeatures.xsl")
	public void listFeatures() {

	}

	@Action(template = "admin/admin.listPrivileges.xsl")
	public void listPrivileges() throws DatabaseException {
		put("privileges", manager().getPrivilege(sort(Privilege.Name)));
	}

	@Action(template = "admin/admin.listRoles.xsl")
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

	@Action(template = "admin/admin.listRooms.xsl")
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

	@Action(template = "admin/admin.listUsers.xsl")
	public void listUsers(
			@Arg(name = "offset", intDefault = 0) final int $offset,
			@Arg(name = "perPage", intDefault = 100) final int $num,
			final DeleteUsers $deleteUsers) throws DatabaseException {
		put("deleteUsers", $deleteUsers);
		put("users",
				manager().getPerson($offset, $num,
						all(isNull(Person.Deleted), sort(Person.LoginName))));
	}

	@Action(template = "admin/admin.listYears.xsl")
	public void listYears(
			@Arg(name = "offset", intDefault = 0) final int $offset,
			@Arg(name = "perPage", intDefault = 100) final int $num)
			throws DatabaseException {
		put("years", manager().getYear($offset, $num));
	}

	@Action(template = "admin/admin.scheduling.control.xsl")
	public void schedulerPanel(final SchedulingControl $role) throws Exception {
		// put("quickSchedulingControl", $role);

		
		final SchedulerManager schedulerManager = SchedulerManager.getInstance(manager());

		put("academicTerms", manager().getAcademicTerm());
		put("departments", manager().getDepartment());

		System.out.println("current status in Admin :: "
				+ $role.schedulerstatus);
		List<String> $opt = new ArrayList<String>();
		if (schedulerManager.isReady()) {
			$opt.add("Start");
			$opt.add("Resume");
			$opt.add("Conflicts");
			$opt.add("Publish");
			put("status", "ready");
			put("options", $opt); 
			if ($role.schedulerstatus.equals("showConflicts")) {
				System.out.println("putting some conflictinfos");
				put("sthnice", "foobar");
				
				put("lecturerOverlap", schedulerManager.getScheduleScore($role.possibleProgram).getlecturerToLecturerOverlap()); 
			}
		} else if (schedulerManager.isStopping()) {
			schedulerManager.stopScheduler();
			put("status", "stopping");

		} else if (schedulerManager.isRunning()) {
			$opt.add("Stop");
			put("status", "running");
			put("options", $opt);

			put("scheduleScore", schedulerManager.getCurrentSchedulingScore());
			//put("lecturerOverlap", schedulerManager.getCurrentSchedulingScore().getlecturerToLecturerOverlap());

		}
	}

	@Action(template = "admin/admin.showDepartment.xsl")
	public void showDepartment() {

	}

	@Action(template = "admin/admin.showRole.xsl")
	public void showRole() {

	}

	@Action(template = "admin/admin.showRoom.xsl")
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
				
				
				/* CEI related (PS, CI, LECTURER)*/
				List<ProposedScheduling> $ps = manager().getProposedScheduling(eq("room", $r.getId()));
				
				for(ProposedScheduling $ps_i : $ps) {
					$ps_i.getElementInstance().getCourseInstance().getInstanceOf();
					$ps_i.getElementInstance().getCourseElement().getType();
					$ps_i.getElementInstance().getLecturer();
				}				
				put("proposedScheduling", $ps);
				
				/* TIMETABLEGRID-RELATED */
				put("days", manager().getDay());
				put("timeslots", manager().getTimeslot());
				
				//OLD - USED FOR LIST OF OTHER ROOMS put("rooms", manager().getRoom());
				return;
			} else {
				setRedirect("admin/listRooms");				
			}
		} else {
			setRedirect("admin/listRooms");
		}
	}

	@Action(template = "admin/admin.showUser.xsl")
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

	@Action(template = "admin/admin.sysconfig.xsl")
	public void sysconfig() {

	}

	@Action(template = "admin/admin.users.xsl")
	public void users() {

	}
}
