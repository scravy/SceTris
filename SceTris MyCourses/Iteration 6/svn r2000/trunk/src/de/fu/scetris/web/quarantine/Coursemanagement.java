/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.quarantine;

import static de.fu.scetris.web.Functions.*;
import static de.fu.weave.orm.filters.Filters.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

import de.fu.junction.annotation.meta.Author;
import de.fu.junction.annotation.meta.Description;
import de.fu.junction.annotation.meta.L;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseElementType;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.ElementRequiresFeature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.filters.UnEq;

/**
 * 
 */
@Author("Andre Zoufahl")
@Description(@L("Coursemgmt panel"))
public class Coursemanagement extends FriggModule<RelationManager> {

	public Coursemanagement(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "coursemgmt/coursemgmt.menu.xsl")
	public void _default(final String[] path) {

	}

	@Action(name = "academictermedit", template = "coursemgmt/academicterm.new.xsl")
	public void editAcademicterm(final String[] path,
								 @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		put("data", manager().getAcademicTerm(data));
		put("crud", "update");
	}

	@Commit(action = "academictermedit", after = "academictermlist")
	public void editAcademicTermCommit(
		final String[] target,
									   @Arg(name = "data", intDefault = 0) final int data,
									   @Arg(name = "name", stringDefault = "") final String name,
									   @Arg(name = "start", stringDefault = "") final String start,
									   @Arg(name = "end", stringDefault = "") final String end,
									   @Arg(name = "startlesson", stringDefault = "") final String startlesson,
									   @Arg(name = "endlesson", stringDefault = "") final String endlesson)
					throws DatabaseException {
		AcademicTerm subj = manager().getAcademicTerm(data);
		if (validAcaTermDates(start, end, startlesson, endlesson, subj)) {

			if (!name.equals("")) {
				subj.setName(name);
			}
			if (!Date.valueOf(start).equals("")) {
				subj.setStart(Date.valueOf(start));
			}
			if (!Date.valueOf(end).equals("")) {
				subj.setEnd(Date.valueOf(end));
			}
			if (!Date.valueOf(start).equals("")) {
				subj.setStartLessons(Date.valueOf(startlesson));
			}
			if (!Date.valueOf(endlesson).equals("")) {
				subj.setEndLessons(Date.valueOf(endlesson));
			}
			subj.pushChanges();
			put("dbsuccess", "AT updated !");
		}
	}

	@Action(name = "courseedit", template = "coursemgmt/course.new.xsl")
	public void editCourse(final String[] path,
						   @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		if (data > 0) {
			Course tmp = manager().getCourse(data);
			put("crud", "update");
			put("data", tmp);
			put("ref", manager().getCourseElement(eq("part_of", data)));
		}
	}

	@Commit(action = "courseedit", after = "courselist")
	public void editCourseCommit(final String[] target,
								 @Arg(name = "data", intDefault = 0) final int data,
								 @Arg(name = "course", stringDefault = "") final String course)
			throws DatabaseException {
		if (data > 0) {
			Course tmp = manager().getCourse(data);
			tmp.setName(course);
			tmp.pushChanges();
			put("dbsuccess", "Course updated");
		}
	}

	@Action(name = "courseelementedit", template = "coursemgmt/courseelement.new.xsl")
	public void editCourseElement(final String[] path,
								  @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		CourseElement tmp = manager().getCourseElement(data);
		put("data", tmp);
		put("courses", manager().getCourse());
		put("courseelementtypes", manager().getCourseElementType());
		put("features", manager().getFeature());
		put("coursefeatures", tmp.whereSubjectOfElementRequiresFeature());
		put("crud", "update");
	}

	@Commit(action = "courseelementedit", after = "courseelementlist")
	public void editCourseElementCommit(
		final String[] target,
										@Arg(name = "data", intDefault = 0) final int data,
										@Arg(name = "courseelement", stringDefault = "") final String courseelement,
										@Arg(name = "type", intDefault = -1) final int type,
										@Arg(name = "course", intDefault = -1) final int course,
										@Arg(name = "duration", intDefault = 0) final int duration,
										@Arg(name = "required", booleanDefault = false) final boolean required,
										@Arg(name = "featurekey", stringDefault = "") final String[] featurekeys,
										@Arg(name = "featuremin", stringDefault = "") final String[] featuremin,
										@Arg(name = "featurebetter", stringDefault = "") final String[] featurebetter)
					throws DatabaseException {
		CourseElement subj = manager().getCourseElement(data);

		if (duration > 0) {
			subj.setDuration(duration);
		}
		if (!courseelement.isEmpty()) {
			subj.setName(courseelement);
		}
		if (course > 0) {
			Course cx = manager().getCourse(course);
			subj.setPartOf(cx);
		}
		if (type > 0) {
			CourseElementType cetx = manager().getCourseElementType(type);
			subj.setType(cetx);
		}

		subj.setRequired(required);
		subj.pushChanges();

		// for (int i = 0; i < featurekeys.length; i++) {
		// int quantity = 0;
		// int better = 0;
		// if (!featuremin[i].isEmpty()) {
		// quantity = Integer.parseInt(featuremin[i]);
		// }
		// if (!featurebetter[i].isEmpty()) {
		// better = Integer.parseInt(featurebetter[i]);
		// }
		// Feature feat = manager().getFeature(eq("name", featurekeys[i])).iterator().next();
		// Collection<ElementRequiresFeature> erf = manager()
		// .getElementRequiresFeature(subj, feat);
		// }

	}

	@Action(name = "courseinstanceedit", template = "coursemgmt/courseinstance.new.xsl")
	public void editCourseInstance(final String[] path,
									   @Arg(name = "data", intDefault = 0) final int data)
				throws DatabaseException {
		if (data > 0) {
			CourseInstance tmp = manager().getCourseInstance(data);
			put("crud", "update");
			put("data", tmp);
			put("ref", manager().getCourseElementInstance(eq("course_instance", tmp.id())));
		}
	}

	@Action(name = "programedit", template = "coursemgmt/program.new.xsl")
	public void editProgramStep1(final String[] path,
								 @Arg(name = "data", intDefault = -1) final int data)
			throws DatabaseException {
		put("academicterms", manager().getAcademicTerm());
		put("departments", manager().getDepartment());
		put("persons", manager().getPerson()); /* TODO:: only lectures */

		/* need postgres here, doing such things is ok but not good in java (not that important yet) */
		Collection<Course> pool = manager().getCourse();
		Collection<CourseInstance> booked = manager().getCourseInstance(eq("program", data));
		Collection<Course> stillavailable = new ArrayList<Course>();
		put("foobar", booked.size());
		for (Course x : pool) {
			Collection<CourseInstance> tmp = manager().getCourseInstance(eq("instance_of",
																					   x.id()));
			if (!booked.containsAll(tmp)) {
				stillavailable.add(x);
			}
		}
		put("bookedcourses", booked);
		put("courses", stillavailable);
		put("crud", "update");
	}

	@Action(name = "academictermlist", template = "coursemgmt/academicterm.list.xsl")
	public void listAcademicTerm(final String[] path,
								 @Arg(name = "page", intDefault = 0) final int pageNumber,
								 @Arg(name = "o_dir", intDefault = 0) final int o_dir,
								 @Arg(name = "o_col", intDefault = 0) final int o_col,
								 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getAcademicTerm(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getAcademicTerm().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "courselist", template = "coursemgmt/course.list.xsl")
	public void listCourse(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "o_dir", intDefault = 0) final int o_dir,
							 @Arg(name = "o_col", intDefault = 0) final int o_col,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getCourse(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getCourse().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "courseelementlist", template = "coursemgmt/courseelement.list.xsl")
	public void listCourseElement(final String[] path,
								  @Arg(name = "page", intDefault = 0) final int pageNumber,
								  @Arg(name = "o_dir", intDefault = 0) final int o_dir,
								  @Arg(name = "o_col", intDefault = 0) final int o_col,
								  @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getCourseElement(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getCourseElement().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "courseelementinstancelist", template = "coursemgmt/courseelementinstance.list.xsl")
	public void listCourseElementInstance(final String[] path,
										  @Arg(name = "page", intDefault = 0) final int pageNumber,
										  @Arg(name = "o_dir", intDefault = 0) final int o_dir,
										  @Arg(name = "o_col", intDefault = 0) final int o_col,
										  @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getCourseElementInstance(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getCourseElementInstance().size(),
												  perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "courseinstancelist", template = "coursemgmt/courseinstance.list.xsl")
	public void listCourseInstance(final String[] path,
								   @Arg(name = "page", intDefault = 0) final int pageNumber,
								   @Arg(name = "o_dir", intDefault = 0) final int o_dir,
								   @Arg(name = "o_col", intDefault = 0) final int o_col,
								   @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getCourseInstance(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getCourseInstance().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "programlist", template = "coursemgmt/program.list.xsl")
	public void listProgram(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "o_dir", intDefault = 0) final int o_dir,
							 @Arg(name = "o_col", intDefault = 0) final int o_col,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getProgram(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getProgram().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "academictermnew", template = "coursemgmt/academicterm.new.xsl")
	public void newAcademicTerm(final String[] path)
			throws DatabaseException {
		put("crud", "create");
	}

	@Commit(action = "academictermnew", after = "academictermlist")
	public void newAcademicTermCommit(
		final String[] target,
									  @Arg(name = "name", stringDefault = "") final String name,
									  @Arg(name = "start", stringDefault = "") final String start,
									  @Arg(name = "end", stringDefault = "") final String end,
									  @Arg(name = "startlesson", stringDefault = "") final String startlesson,
									  @Arg(name = "endlesson", stringDefault = "") final String endlesson)
			throws DatabaseException {
		if (!name.equals("") && !start.equals("") && !end.equals("")) {
			if (validAcaTermDates(start, end, startlesson, endlesson, null)) {
				AcademicTerm at = manager().fullyNewAcademicTerm(name, Date.valueOf(start),
																			Date.valueOf(end),
																			Date.valueOf(startlesson),
																			Date.valueOf(endlesson));
				at.create();
				put("dbsuccess", "AT created !");
			}
		} else {
			throw new DatabaseException();
		}
	}

	/**********
	 * /* STEP 1 *
	 **********/
	@Action(name = "courseelementinstancenew", template = "coursemgmt/courseelementinstance.new.xsl")
	public void newCEImStep1(final String[] path)
			throws DatabaseException {
		put("data", manager().getCourseInstance());
		put("crud", "create");
	}

	@Commit(action = "courseelementinstancenew", after = "courseelementinstancemeta")
	public void newCEIStep1Commit(
		final String[] target,
									  @Arg(name = "courseinstance", intDefault = -1) final int courseinstance,
									  @Arg(name = "numberofelements", intDefault = -1) final int numberofelements)
					throws DatabaseException {
		if ((courseinstance > 0) && (numberofelements > 0)) {
			CourseInstance ci = manager().getCourseInstance(courseinstance);
			Collection<CourseElement> ces = manager().getCourseElement(eq("part_of",
																					 ci.getInstanceOf()
																							 .getId()));
			if (ces.isEmpty()) {
				throw new DatabaseException("Course has no Elements");
			}
			CourseElement ce = ces.iterator().next();
			for (int i = 0; i < numberofelements; i++) {
				manager().createCourseElementInstance(ci, ce, 1);
			}

			put("dbsuccess", "CourseElementInstances created !");
		} else {
			throw new DatabaseException();
		}
	}

	/**********
	 * /* STEP 2 *
	 **********/
	@Action(name = "courseelementinstancemeta", template = "coursemgmt/courseelementinstance.meta.xsl")
	public void newCEIStep2(final String[] path,
								@Arg(name = "data", intDefault = -1) final int data)
			throws DatabaseException {
		CourseInstance tmp = manager().getCourseInstance(data);
		put("courseelementinstance",
			manager().getCourseElementInstance(eq("course_instance", data)));
		put("courseelements",
			manager().getCourseElement(eq("part_of", tmp.getInstanceOf().getId())));
		put("crud", "update");
	}

	@Commit(action = "courseelementinstancemeta", after = "courseelementinstancelist")
	public void newCEIStep2Commit(
		final String[] target,
									  @Arg(name = "cei", stringDefault = "") final String[] cei,
									  @Arg(name = "durations", stringDefault = "") final String[] durations,
									  @Arg(name = "courseelements", stringDefault = "") final String[] courseelements)
					throws DatabaseException {
		if (cei.length > 0) {
			for (int i = 0; i < cei.length; i++) {
				CourseElementInstance tmp = manager()
						.getCourseElementInstance(Integer.parseInt(cei[i]));
				tmp.setDuration(Integer.parseInt(durations[i]));
				tmp.setCourseElement(manager()
						.getCourseElement(Integer.parseInt(courseelements[i])));
				tmp.pushChanges();
				/* TODO :: check if durations are same as the elements needed (or more) */
			}
			put("dbsuccess", "CourseElementInstances updated !");

		} else {
			throw new DatabaseException("step 2 fail");
		}
	}

	@Action(name = "coursenew", template = "coursemgmt/course.new.xsl")
	public void newCourse(final String[] path)
			throws DatabaseException {
		put("crud", "create");
		put("courseattributes", manager().getCourseAttribute());
	}

	@Commit(action = "coursenew", after = "courselist")
	public void newCourseCommit(final String[] target,
								@Arg(name = "course", stringDefault = "") final String course)
								// @Arg(name = "caname", stringDefault = "") final String[] caname,
								// @Arg(name = "catext", stringDefault = "") final String[] catext,
								// @Arg(name = "catype", stringDefault = "") final String[] catype)
		throws DatabaseException {
		if (!course.isEmpty()) {
			Course tmp = relationManager.newCourse(course);

			/* Check courseattributes */
			/*
			 * for (int i = 0; i < caname.length; i++) { String text = null; if
			 * (!catext[i].isEmpty()) { text = catext[i]; } }
			 */

			tmp.create();
			put("dbsuccess", "Course created !");
		}
	}

	@Action(name = "courseelementnew", template = "coursemgmt/courseelement.new.xsl")
	public void newCourseElement(final String[] path)
			throws DatabaseException {
		put("courses", manager().getCourse());
		put("courseelementtypes", manager().getCourseElementType());
		put("features", manager().getFeature());
		put("crud", "create");
	}

	@Commit(action = "courseelementnew", after = "courseelementlist")
	public void newCourseElementCommit(
		final String[] target,
									   @Arg(name = "courseelement", stringDefault = "") final String courseelement,
									   @Arg(name = "type", intDefault = -1) final int type,
									   @Arg(name = "course", intDefault = -1) final int course,
									   @Arg(name = "duration", intDefault = 0) final int duration,
									   @Arg(name = "required", booleanDefault = false) final boolean required,
									   @Arg(name = "featurekey", stringDefault = "") final String[] featurekeys,
									   @Arg(name = "featuremin", stringDefault = "") final String[] featuremin,
									   @Arg(name = "featurebetter", stringDefault = "") final String[] featurebetter)
					throws DatabaseException {
		if (!courseelement.isEmpty() && (type > 0) && (course > 0) && (duration > 0)) {
			Course cx = manager().getCourse(course);
			CourseElementType cetx = manager().getCourseElementType(type);
			CourseElement tmp = manager().fullyNewCourseElement(courseelement, cx, duration,
																		   cetx, required);

			Collection<ElementRequiresFeature> coursefeatures = new ArrayList<ElementRequiresFeature>();
			// for (int i = 0; i < featurekeys.length; i++) {
			// int quantity = 0;
			// int better = 0;
			// if (!featuremin[i].isEmpty()) {
			// quantity = Integer.parseInt(featuremin[i]);
			// }
			// if (!featurebetter[i].isEmpty()) {
			// better = Integer.parseInt(featurebetter[i]);
			// }
			//
			// Feature feat = manager().getFeature(eq("name", featurekeys[i])).iterator()
			// .next();
			// }
			tmp.create();
			for (ElementRequiresFeature x : coursefeatures) {
				x.create();
			}

			put("dbsuccess", "CourseElement created !");
		} else {
			throw new DatabaseException(courseelement + "," + type + "," + course + "," + duration + ","
					+ required);
		}
	}

	@Action(name = "courseinstancenew", template = "coursemgmt/courseinstance.new.xsl")
	public void newCourseInstance(final String[] path)
			throws DatabaseException {
		put("programs", manager().getProgram());
		put("persons", manager().getPerson()); /* TODO:: only lectures */
		put("courses", manager().getCourse());
		put("crud", "create");
	}

	@Commit(action = "courseinstancenew", after = "programmeta")
	public void newCourseInstanceCommit(
		final String[] target,
										@Arg(name = "course", intDefault = -1) final int course,
										@Arg(name = "program", intDefault = -1) final int program,
										@Arg(name = "mainlecturer", intDefault = -1) final int mainlecturer)
					throws DatabaseException {
		if ((course > 0) && (program > 0) && (mainlecturer > 0)) {
			Course c = manager().getCourse(course);
			Program p = manager().getProgram(program);
			if (manager().getCourseInstance(all(eq("program", p.id()),
														   eq("instance_of", c.id())))
					.isEmpty()) {
				Person per = manager().getPerson(mainlecturer);
				CourseInstance tmp = manager()
						.fullyNewCourseInstance(p, c, p.getAcademicTerm().getStartLessons(),
												p.getAcademicTerm().getEndLessons(), per);
				tmp.create();
			} else {
				throw new DatabaseException("CI already exists");
			}
			put("dbsuccess", "CourseInstance created !");
		} else {
			throw new DatabaseException("CI createerror");
		}
	}

	/**********
	 * /* STEP 1 *
	 **********/
	@Action(name = "programnew", template = "coursemgmt/program.new.xsl")
	public void newProgramStep1(final String[] path)
			throws DatabaseException {
		put("academicterms", manager().getAcademicTerm());
		put("departments", manager().getDepartment());
		put("persons", manager().getPerson()); /* TODO:: only lectures */
		put("crud", "create");
	}

	@Commit(action = "programnew", after = "programselectcourses")
	public void newProgramStep1Commit(
		final String[] target,
									  @Arg(name = "academicterm", intDefault = -1) final int academicterm,
									  @Arg(name = "department", intDefault = -1) final int department,
									  @Arg(name = "pmanager", intDefault = -1) final int pmanager)
					throws DatabaseException {
		if ((academicterm > 0) && (department > 0) && (pmanager > 0)) {
			AcademicTerm at = manager().getAcademicTerm(academicterm);
			Department d = manager().getDepartment(department);
			Person p = manager().getPerson(pmanager);
			Program tmp = manager().fullyNewProgram(at, d, false, false, p);
			tmp.create();

			put("dbsuccess", "Program created !");
		} else {
			throw new DatabaseException();
		}
	}

	/**********
	 * /* STEP 2 *
	 **********/
	@Action(name = "programselectcourses", template = "coursemgmt/program.selectcourses.xsl")
	public void newProgramStep2(final String[] path,
								@Arg(name = "program", intDefault = -1) final int program)
			throws DatabaseException {
		/*
		 * this routine should get the courseinstances that are related to a program x when you
		 * create a new program, you get the courseinstances which have the highest(means latest,
		 * recently created) program
		 */
		int pnum = 0;
		if (program < 0) {
			Collection<Program> programs = manager().getProgram();
			for (Program x : programs) {
				if (x.id() > program) {
					pnum = x.id();
				}
			}
		} else {
			pnum = program;
		}
		put("courses", manager().getCourse());
		put("program", manager().getProgram(pnum));
		put("crud", "create");
	}

	@Commit(action = "programselectcourses", after = "programmeta")
	public void newProgramStep2Commit(final String[] target,
									  @Arg(name = "program", intDefault = -1) final int program,
									  @Arg(name = "courses", stringDefault = "") final String[] courses)
					throws DatabaseException {
		if (program > 0) {
			Program tmp = manager().getProgram(program);

			/* booleancheck */
			for (String x : courses) {
				int coursenum = Integer.parseInt(x);
				Course cx = manager().getCourse(coursenum);
				CourseInstance cix = manager().newCourseInstance(tmp,
																			cx,
																			tmp.getAcademicTerm()
																					.getStartLessons(),
																			tmp.getAcademicTerm()
																					.getEndLessons(),
																			tmp.getProgramManager());
				cix.create();
			}

			put("dbsuccess", "CourseInstances created created !");

		} else {
			throw new DatabaseException("step 2 fail");
		}
	}

	/**********
	 * /* STEP 3 *
	 **********/
	@Action(name = "programmeta", template = "coursemgmt/program.meta.xsl")
	public void newProgramStep3(final String[] path,
								@Arg(name = "program", intDefault = -1) final int program)
			throws DatabaseException {
		/*
		 * this routine should get the courseinstances that are related to a program x when you
		 * create a new program, you get the courseinstances which have the highest(means latest,
		 * recently created) program
		 */
		int pnum = 0;
		if (program < 0) {
			Collection<Program> programs = manager().getProgram();
			for (Program x : programs) {
				if (x.id() > program) {
					pnum = x.id();
				}
			}
		} else {
			pnum = program;
		}
		put("courseinstances", manager().getCourseInstance(eq("program", pnum)));
		put("persons", manager().getPerson()); /* TODO :: ONLY LECTURER */
		put("crud", "create");
	}

	@Commit(action = "programmeta", after = "programlist")
	public void newProgramStep3Commit(
		final String[] target,
									  @Arg(name = "mainlecturer", stringDefault = "") final String[] mainlecturer,
									  @Arg(name = "start", stringDefault = "") final String[] starttimes,
									  @Arg(name = "end", stringDefault = "") final String[] endtimes,
									  @Arg(name = "data", stringDefault = "") final String[] ids)
					throws DatabaseException {
		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);
			CourseInstance ci = manager().getCourseInstance(id);
			ci.setStart(Date.valueOf(starttimes[i]));
			ci.setEnd(Date.valueOf(endtimes[i]));
			ci.setMainLecturer(manager().getPerson(Integer.parseInt(mainlecturer[i])));
			ci.pushChanges();
		}
	}

	// TODO :: change dates of programs/CE that depend on this // or check if dates can be modified
	// at all (either if no program for this at or times are still within borders)
	private boolean validAcaTermDates(final String start, final String end,
									  final String startlesson, final String endlesson,
									  final AcademicTerm exclude) throws DatabaseException {

		UnEq filter = null;
		if (exclude != null) {
			filter = not(eq("id", exclude.id()));
		}
		Collection<AcademicTerm> rest = manager().getAcademicTerm(filter);
		for (AcademicTerm x : rest) {
			if (!(Date.valueOf(start).after(x.getEnd()) || Date.valueOf(end).before(x.getStart()))) {
				throw new InputMismatchException("Academic Term is interleaving with other ATs");
			}
		}

		if (Date.valueOf(startlesson).before(Date.valueOf(start)) ||
				Date.valueOf(endlesson).after(Date.valueOf(end)) ||
				Date.valueOf(end).before(Date.valueOf(start))) {
			throw new InputMismatchException("Start/End-Dates are invalid");
		}
		return true;
	}
}
