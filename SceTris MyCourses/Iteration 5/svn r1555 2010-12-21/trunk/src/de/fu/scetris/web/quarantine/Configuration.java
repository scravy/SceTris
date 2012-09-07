/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.quarantine;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.scetris.util.Functions.*;

import java.util.Collection;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseAttribute;
import de.fu.scetris.data.CourseElementType;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.annotation.meta.Description;
import de.fu.weave.annotation.meta.L;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 */
@Author("Andre Zoufahl")
@Description(@L("Configuration of the system"))
public class Configuration extends FriggModule<RelationManager> {

	public Configuration(final Scetris parent) {
		super(parent);
	}

	@Action(template = "configuration/configuration.menu.xsl")
	public void _default(final String[] path) {

	}

	@Action(name = "cetedit", template = "configuration/cet.new.xsl")
	public void editCET(final String[] path,
						@Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		put("crud", "update");
		Collection<CourseElementType> tmp = relationManager.getCourseElementType(eq("id", Integer
				.parseInt(data)));
		CourseElementType feat = tmp.iterator().next();
		put("coursetype", feat);
	}

	@Commit(action = "cetedit", after = "cetlist")
	public void editCETCommit(final String[] target,
							  @Arg(name = "data", stringDefault = "") final String data,
							  @Arg(name = "coursetype", stringDefault = "") final String coursetype)
			throws DatabaseException {
		Collection<CourseElementType> tmp = relationManager.getCourseElementType(eq("id", Integer
				.parseInt(data)));
		CourseElementType feat = tmp.iterator().next();
		feat.setName(coursetype);
		feat.pushChanges();
		put("dbsuccess", "CET updated");
	}

	@Action(name = "courseattributeedit", template = "configuration/courseattribute.new.xsl")
	public void editCourseattribute(final String[] path,
									@Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		put("crud", "update");
		put("data", getRelationManager().getCourseAttribute(data));
	}

	@Commit(action = "courseattributeedit", after = "courseattributelist")
	public void editCourseattributeCommit(final String[] target,
										  @Arg(name = "data", intDefault = 0) final int data,
										  @Arg(name = "name", stringDefault = "") final String name,
										  @Arg(name = "type", stringDefault = "") final String type,
										  @Arg(name = "unique", booleanDefault = false) final boolean unique,
										  @Arg(name = "required", booleanDefault = false) final boolean required)
			throws DatabaseException {
		CourseAttribute tmp = getRelationManager().getCourseAttribute(data);
		tmp.setName(name);
		/*
		 * TODO :: check all attributes and either deny the update or remove/transform values (so
		 * constraints are fulfilled)
		 */
		tmp.setRequired(required);
		tmp.setUniqueValue(unique);
		if ((type.equals("String")) || (type.equals("Integer"))) {
			tmp.setType(type);
		}
		tmp.pushChanges();
		put("dbsuccess", "Courseattribute updated");
	}

	@Action(name = "cetlist", template = "configuration/cet.list.xsl")
	public void listCET(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getCourseElementType(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getCourseElementType().size(),
												  perPage, pageNumber)));
	}

	@Action(name = "courseattributelist", template = "configuration/courseattribute.list.xsl")
	public void listCourse(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "o_dir", intDefault = 0) final int o_dir,
							 @Arg(name = "o_col", intDefault = 0) final int o_col,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getCourseAttribute(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getCourseAttribute().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "cetnew", template = "configuration/cet.new.xsl")
	public void newCET(final String[] path)
			throws DatabaseException {
		put("crud", "create");
	}

	@Commit(action = "cetnew", after = "cetlist")
	public void newCETCommit(final String[] target,
							 @Arg(name = "data", stringDefault = "") final String data,
							 @Arg(name = "coursetype", stringDefault = "") final String coursetype)
			throws DatabaseException {
		if (!coursetype.isEmpty()) {
			CourseElementType tmp = relationManager.newCourseElementType(coursetype);
			tmp.create();
			put("dbsuccess", "CET created");
		}
	}

	@Commit(action = "courseattributenew", after = "courseattributelist")
	public void newCouraseattrubuteCommit(final String[] target,
										  @Arg(name = "data", intDefault = 0) final int data,
										  @Arg(name = "name", stringDefault = "") final String name,
										  @Arg(name = "type", stringDefault = "") final String type,
										  @Arg(name = "unique", booleanDefault = false) final boolean unique,
										  @Arg(name = "required", booleanDefault = false) final boolean required)
			throws DatabaseException {
		if (name.length() > 0) {
			CourseAttribute tmp = getRelationManager().newCourseAttribute(name);
			if ((type.equals("String")) || (type.equals("Integer"))) {
				tmp.setType(type);
			}
			tmp.setUniqueValue(unique);
			tmp.setRequired(required);
			tmp.create();
			put("dbsuccess", "Courseattribute updated");
		}
	}

	@Action(name = "courseattributenew", template = "configuration/courseattribute.new.xsl")
	public void newCourseattribute(final String[] path,
								   @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		put("crud", "create");
		put("data", getRelationManager().getCourseAttribute(data));
	}

	@Action(name = "featurevalidate", template = "configuration/cet.new.xsl")
	public void validateFeature(final String[] path,
								@Arg(name = "crud", stringDefault = "") final String action,
								@Arg(name = "data", stringDefault = "") final String data,
								@Arg(name = "feature", stringDefault = "") final String feature)
			throws DatabaseException {
		if (action.equals("delete")) {
			Collection<CourseElementType> tmp = relationManager.getCourseElementType(eq("id", Integer
					.parseInt(data)));
			CourseElementType feat = tmp.iterator().next();
			feat.delete();
			put("dbsuccess", "CET deleted");
		}
	}

}
