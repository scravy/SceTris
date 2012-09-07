/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.fourtris;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.scetris.web.Functions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.filters.UnEq;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementType;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.ElementRequiresFeature;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Fourtris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 * @author Andre Zoufahl
 * @since Iteration3
 */
@ModuleInfo(name = "coursemgmt",
			author = "Andre Zoufahl",
			description = "Coursemgmt panel",
			requires = Requirement.DATABASE)
public class Coursemanagement extends FriggModule<RelationManager> {

	public Coursemanagement(final Fourtris parent) {
		super(parent);
	}

	@Action(template = "coursemgmt/coursemgmt.menu.xsl")
	public void _default(final String[] path) {

	}

	@Action(name = "academictermedit", template = "coursemgmt/academicterm.new.xsl")
	public void editAcademicterm(final String[] path,
								 @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		put("data", getRelationManager().getAcademicTerm(data));
		put("crud", "update");
	}

	@Commit(action = "academictermedit", after = "academictermlist")
	public void editAcademicTermCommit(final String[] target,
									   @Arg(name = "data", intDefault = 0) final int data,
									   @Arg(name = "name", stringDefault = "") final String name,
									   @Arg(name = "start", stringDefault = "") final String start,
									   @Arg(name = "end", stringDefault = "") final String end,
									   @Arg(name = "startlesson", stringDefault = "") final String startlesson,
									   @Arg(name = "endlesson", stringDefault = "") final String endlesson)
					throws DatabaseException {
		AcademicTerm subj = getRelationManager().getAcademicTerm(data);
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
			Course tmp = getRelationManager().getCourse(data);
			put("crud", "update");
			put("data", tmp);
			put("ref", getRelationManager().getCourseElement(eq("part_of", data)));
		}
	}

	@Commit(action = "courseedit", after = "courselist")
	public void editCourseCommit(final String[] target,
								 @Arg(name = "data", intDefault = 0) final int data,
								 @Arg(name = "course", stringDefault = "") final String course)
			throws DatabaseException {
		if (data > 0) {
			Course tmp = getRelationManager().getCourse(data);
			tmp.setName(course);
			tmp.pushChanges();
			put("dbsuccess", "Course updated");
		}
	}

	@Action(name = "courseelementedit", template = "coursemgmt/courseelement.new.xsl")
	public void editCourseElement(final String[] path,
								  @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		CourseElement tmp = getRelationManager().getCourseElement(data);
		put("data", tmp);
		put("courses", getRelationManager().getCourse());
		put("courseelementtypes", getRelationManager().getCourseElementType());
		put("features", getRelationManager().getFeature());
		put("coursefeatures", tmp.whereSubjectOfElementRequiresFeature());
		put("crud", "update");
	}

	@Commit(action = "courseelementedit", after = "courseelementlist")
	public void editCourseElementCommit(final String[] target,
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
		CourseElement subj = getRelationManager().getCourseElement(data);

		if (duration > 0) {
			subj.setDuration(duration);
		}
		if (!courseelement.isEmpty()) {
			subj.setName(courseelement);
		}
		if (course > 0) {
			Course cx = getRelationManager().getCourse(course);
			subj.setPartOf(cx);
		}
		if (type > 0) {
			CourseElementType cetx = getRelationManager().getCourseElementType(type);
			subj.setType(cetx);
		}

		subj.setRequired(required);
		subj.pushChanges();

		for (int i = 0; i < featurekeys.length; i++) {
			int quantity = 0;
			int better = 0;
			if (!featuremin[i].isEmpty()) {
				quantity = Integer.parseInt(featuremin[i]);
			}
			if (!featurebetter[i].isEmpty()) {
				better = Integer.parseInt(featurebetter[i]);
			}
			Feature feat = getRelationManager().getFeature(eq("name", featurekeys[i])).iterator().next();
			Collection<ElementRequiresFeature> erf = getRelationManager()
					.getElementRequiresFeature(subj, feat);
			if (erf.isEmpty()) {
				if ((quantity > 0) || (better > 0)) {
					getRelationManager().fullyCreateElementRequiresFeature(subj, feat, 1, quantity,
																		   better);
				}
			} else {
				ElementRequiresFeature tmp = erf.iterator().next();
				if (quantity > 0) {
					tmp.setQuantityMin(quantity);
				} else {
					tmp.clearQuantityMin();
				}
				if (better > 0) {
					tmp.setQuantityBetter(better);
				} else {
					tmp.clearQuantityBetter();
				}
				tmp.pushChanges();
				if ((quantity == 0) && (better == 0)) {
					tmp.delete();
				}

			}

		}

		put("dbsuccess", "CourseElement updated !");
	}

	@Action(name = "courseinstanceedit", template = "coursemgmt/courseinstance.new.xsl")
	public void editCourseInstance(final String[] path,
								   @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		if (data > 0) {
			CourseInstance tmp = getRelationManager().getCourseInstance(data);
			put("crud", "update");
			put("data", tmp);
			put("ref", getRelationManager().getCourseElementInstance(eq("course_instance", tmp.id())));
		}
	}

	@Action(name = "programedit", template = "coursemgmt/program.new.xsl")
	public void editProgramStep1(final String[] path,
								 @Arg(name = "data", intDefault = -1) final int data)
			throws DatabaseException {
		put("academicterms", getRelationManager().getAcademicTerm());
		put("departments", getRelationManager().getDepartment());
		put("persons", getRelationManager().getPerson()); /* TODO:: only lectures */

		/* need postgres here, doing such things is ok but not good in java (not that important yet) */
		Collection<Course> pool = getRelationManager().getCourse();
		Collection<CourseInstance> booked = getRelationManager().getCourseInstance(eq("program", data));
		Collection<Course> stillavailable = new ArrayList<Course>();
		put("foobar", booked.size());
		for (Course x : pool) {
			Collection<CourseInstance> tmp = getRelationManager().getCourseInstance(eq("instance_of",
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
	public void newAcademicTermCommit(final String[] target,
									  @Arg(name = "name", stringDefault = "") final String name,
									  @Arg(name = "start", stringDefault = "") final String start,
									  @Arg(name = "end", stringDefault = "") final String end,
									  @Arg(name = "startlesson", stringDefault = "") final String startlesson,
									  @Arg(name = "endlesson", stringDefault = "") final String endlesson)
			throws DatabaseException {
		if (!name.equals("") && !start.equals("") && !end.equals("")) {
			if (validAcaTermDates(start, end, startlesson, endlesson, null)) {
				AcademicTerm at = getRelationManager().fullyNewAcademicTerm(name, Date.valueOf(start),
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

	@Action(name = "coursenew", template = "coursemgmt/course.new.xsl")
	public void newCourse(final String[] path)
			throws DatabaseException {
		put("crud", "create");
		put("courseattributes", getRelationManager().getCourseAttribute());
	}

	@Commit(action = "coursenew", after = "courselist")
	public void newCourseCommit(final String[] target,
								@Arg(name = "course", stringDefault = "") final String course,
								@Arg(name = "caname", stringDefault = "") final String[] caname,
								@Arg(name = "catext", stringDefault = "") final String[] catext,
								@Arg(name = "catype", stringDefault = "") final String[] catype)
			throws DatabaseException {
		if (!course.isEmpty()) {
			Course tmp = relationManager.newCourse(course);

			/* Check courseattributes */
			for (int i = 0; i < caname.length; i++) {
				String text = null;
				if (!catext[i].isEmpty()) {
					text = catext[i];
				}
			}

			tmp.create();
			put("dbsuccess", "Course created !");
		}
	}

	@Action(name = "courseelementnew", template = "coursemgmt/courseelement.new.xsl")
	public void newCourseElement(final String[] path)
			throws DatabaseException {
		put("courses", getRelationManager().getCourse());
		put("courseelementtypes", getRelationManager().getCourseElementType());
		put("features", getRelationManager().getFeature());
		put("crud", "create");
	}

	@Commit(action = "courseelementnew", after = "courseelementlist")
	public void newCourseElementCommit(final String[] target,
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
			Course cx = getRelationManager().getCourse(course);
			CourseElementType cetx = getRelationManager().getCourseElementType(type);
			CourseElement tmp = getRelationManager().fullyNewCourseElement(courseelement, cx, duration,
																		   cetx, required);

			Collection<ElementRequiresFeature> coursefeatures = new ArrayList<ElementRequiresFeature>();
			for (int i = 0; i < featurekeys.length; i++) {
				int quantity = 0;
				int better = 0;
				if (!featuremin[i].isEmpty()) {
					quantity = Integer.parseInt(featuremin[i]);
				}
				if (!featurebetter[i].isEmpty()) {
					better = Integer.parseInt(featurebetter[i]);
				}

				Feature feat = getRelationManager().getFeature(eq("name", featurekeys[i])).iterator()
						.next();
				if ((quantity > 0) || (better > 0)) {
					ElementRequiresFeature erf = getRelationManager().newElementRequiresFeature(tmp,
																								feat, 1);
					if (quantity > 0) {
						erf.setQuantityMin(quantity);
					}
					if (better > 0) {
						erf.setQuantityBetter(better);
					}

					coursefeatures.add(erf);

				}
			}
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
		put("programs", getRelationManager().getProgram());
		put("persons", getRelationManager().getPerson()); /* TODO:: only lectures */
		put("courses", getRelationManager().getCourse());
		put("crud", "create");
	}

	@Commit(action = "courseinstancenew", after = "programmeta")
	public void newCourseInstanceCommit(final String[] target,
										@Arg(name = "course", intDefault = -1) final int course,
										@Arg(name = "program", intDefault = -1) final int program,
										@Arg(name = "mainlecturer", intDefault = -1) final int mainlecturer)
					throws DatabaseException {
		if ((course > 0) && (program > 0) && (mainlecturer > 0)) {
			Course c = getRelationManager().getCourse(course);
			Program p = getRelationManager().getProgram(program);
			if (getRelationManager().getCourse(all(eq("program", p.id()), eq("instance_of", c.id())))
					.isEmpty()) {
				Person per = getRelationManager().getPerson(mainlecturer);
				CourseInstance tmp = getRelationManager()
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

	@Action(name = "programnew", template = "coursemgmt/program.new.xsl")
	public void newProgramStep1(final String[] path)
			throws DatabaseException {
		put("academicterms", getRelationManager().getAcademicTerm());
		put("departments", getRelationManager().getDepartment());
		put("persons", getRelationManager().getPerson()); /* TODO:: only lectures */
		put("courses", getRelationManager().getCourse());
		put("crud", "create");
	}

	@Commit(action = "programnew", after = "programmeta")
	public void newProgramStep1Commit(final String[] target,
									  @Arg(name = "academicterm", intDefault = -1) final int academicterm,
									  @Arg(name = "department", intDefault = -1) final int department,
									  @Arg(name = "freezed", booleanDefault = false) final boolean freezed,
									  @Arg(name = "published", booleanDefault = false) final boolean published,
									  @Arg(name = "pmanager", intDefault = -1) final int pmanager,
									  @Arg(name = "courses", stringDefault = "") final String[] courses)
					throws DatabaseException {
		if ((academicterm > 0) && (department > 0) && (pmanager > 0)) {
			AcademicTerm at = getRelationManager().getAcademicTerm(academicterm);
			Department d = getRelationManager().getDepartment(department);
			Person p = getRelationManager().getPerson(pmanager);
			Program tmp = getRelationManager().fullyNewProgram(at, d, freezed, published, p);
			tmp.create();

			for (String x : courses) {
				int coursenum = Integer.parseInt(x);
				Course cx = getRelationManager().getCourse(coursenum);
				CourseInstance cix = getRelationManager().newCourseInstance(tmp, cx,
																			at.getStartLessons(),
																			at.getEndLessons(), p);
				cix.create();
			}

			put("dbsuccess", "Program created !");
		} else {
			String error = "";
			for (String x : courses) {
				error += ", " + x;
			}
			throw new DatabaseException(error);
		}
	}

	@Action(name = "programmeta", template = "coursemgmt/program.meta.xsl")
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
			Collection<Program> programs = getRelationManager().getProgram();
			for (Program x : programs) {
				if (x.id() > program) {
					pnum = x.id();
				}
			}
		} else {
			pnum = program;
		}
		put("courseinstances", getRelationManager().getCourseInstance(eq("program", pnum)));
		put("persons", getRelationManager().getPerson());
		put("crud", "create");
	}

	@Commit(action = "programmeta", after = "programlist")
	public void newProgramStep2Commit(final String[] target,
									  @Arg(name = "mainlecturer", stringDefault = "") final String[] mainlecturers,
									  @Arg(name = "start", stringDefault = "") final String[] starttimes,
									  @Arg(name = "end", stringDefault = "") final String[] endtimes,
									  @Arg(name = "data", stringDefault = "") final String[] ids)
					throws DatabaseException {
		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);
			CourseInstance ci = getRelationManager().getCourseInstance(id);
			ci.setStart(Date.valueOf(starttimes[i]));
			ci.setEnd(Date.valueOf(endtimes[i]));
			ci.setMainLecturer(getRelationManager().getPerson(Integer.parseInt(mainlecturers[i])));
			ci.pushChanges();
		}

		put("dbsuccess", "Programmeta updated !");
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
		Collection<AcademicTerm> rest = getRelationManager().getAcademicTerm(filter);
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
