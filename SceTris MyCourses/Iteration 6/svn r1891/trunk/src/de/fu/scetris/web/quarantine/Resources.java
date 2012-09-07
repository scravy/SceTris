/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.quarantine;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.scetris.util.Functions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomProvidesFeature;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 */
@Author("André Zoufahl")
public class Resources extends FriggModule<RelationManager> {

	public Resources(final Scetris parent) {
		super(parent);
	}

	@Action(template = "resources/resources.menu.xsl")
	public void _default(final String[] path) {

	}

	@Action(name = "buildingvalidate", template = "resources/resources.menu.xsl")
	public void deleteBuilding(final String[] path,
							   @Arg(name = "crud", stringDefault = "") final String action,
							   @Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		if (action.equals("delete")) {
			Collection<Building> builds = relationManager.getBuilding(eq("id", Integer.parseInt(data)));
			Building build = builds.iterator().next();
			Collection<Room> rooms = relationManager.getRoom(eq("building", build.id()));
			if (rooms.isEmpty()) {
				build.delete();
				put("dbsuccess", "Building deleted");
			} else {
				// building in use, do nothing
				put("dbfail", "Building still in use");
			}
		}
	}

	@Action(name = "departmentvalidate", template = "resources/resources.menu.xsl")
	public void deleteDepartment(final String[] path,
								 @Arg(name = "crud", stringDefault = "") final String action,
								 @Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		if (action.equals("delete")) {
			Collection<Department> departs = relationManager.getDepartment(eq("id",
																			  Integer.parseInt(data)));
			Department depart = departs.iterator().next();
			Collection<Building> builds = relationManager.getBuilding(eq("department", depart.id()));
			if (builds.isEmpty()) {
				depart.delete();
				put("dbsuccess", "Department deleted");
			} else {
				// department in use, do nothing
				put("dbfail", "Department still in use");
			}
		}
	}

	@Action(name = "roomvalidate", template = "resources/resources.menu.xsl")
	public void deleteRoom(final String[] path,
						   @Arg(name = "crud", stringDefault = "") final String action,
						   @Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		if (action.equals("delete")) {
			Collection<Room> rooms = relationManager.getRoom(eq("id", Integer.parseInt(data)));
			de.fu.scetris.data.Room room = rooms.iterator().next();
			Collection<RoomProvidesFeature> deletemefirst = room.whereSubjectOfRoomProvidesFeature();
			for (RoomProvidesFeature x : deletemefirst) {
				x.delete();
			}
			room.delete();
			put("roomdeleted", "1");
		}
	}

	@Action(name = "buildingedit", template = "resources/building.new.xsl")
	public void editBuilding(final String[] path,
							 @Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		Collection<Building> tmp = relationManager.getBuilding(eq("id", Integer.parseInt(data)));
		de.fu.scetris.data.Building build = tmp.iterator().next();
		put("departments", relationManager.getDepartment());
		put("crud", "update");
		put("building", build);
	}

	@Commit(action = "buildingedit", after = "buildinglist")
	public void editBuildingCommit(final String[] target,
								   @Arg(name = "data", stringDefault = "") final String data,
								   @Arg(name = "name", stringDefault = "") final String name,
								   @Arg(name = "address", stringDefault = "") final String address,
								   @Arg(name = "departments", intDefault = 0) final int departmentID)
			throws DatabaseException {
		Building build = relationManager.getBuilding(Integer.parseInt(data));
		build.setName(name);
		build.setAddress(address);
		build.setDepartment(relationManager.getDepartment(departmentID));
		build.pushChanges();
		put("dbsuccess", "Building updated");
	}

	@Action(name = "departmentedit", template = "resources/department.new.xsl")
	public void editDepartment(final String[] path,
							   @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {

		put("departments", manager().getDepartment(not(eq("id", data))));
		put("crud", "update");
		put("data", manager().getDepartment(data));
	}

	@Commit(action = "departmentedit", after = "departmentlist")
	public void editDepartmentCommit(final String[] target,
									 @Arg(name = "data", stringDefault = "") final String data,
									 @Arg(name = "name", stringDefault = "") final String name,
									 @Arg(name = "superordinateDepartment", intDefault = 0) final int superDep)
			throws DatabaseException {
		Collection<Department> departs = relationManager.getDepartment(eq("id", Integer.parseInt(data)));
		Department depart = departs.iterator().next();
		depart.setName(name);
		if (superDep > 0) {
			Department superDepartment = manager().getDepartment(superDep);
			if (hasCircularReference(depart, superDepartment)) {
				return;
			}
			depart.setSuperordinateDepartment(superDepartment);
		} else {
			depart.clearSuperordinateDepartment();
		}
		depart.pushChanges();
		put("dbsuccess", "Department updatet");
	}

	@Action(name = "featureedit", template = "resources/feature.new.xsl")
	public void editFeature(final String[] path,
							@Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		put("crud", "update");
		Collection<Feature> tmp = relationManager.getFeature(eq("id", Integer.parseInt(data)));
		de.fu.scetris.data.Feature feat = tmp.iterator().next();
		put("feature", feat);
	}

	@Commit(action = "featureedit", after = "featurelist")
	public void editFeatureCommit(final String[] target,
								  @Arg(name = "data", stringDefault = "") final String data,
								  @Arg(name = "feature", stringDefault = "") final String feature)
			throws DatabaseException {
		Collection<Feature> tmp = relationManager.getFeature(eq("id", Integer.parseInt(data)));
		Feature feat = tmp.iterator().next();
		feat.setName(feature);
		feat.pushChanges();
		put("dbsuccess", "Feature updated");
	}

	@Action(name = "roomedit", template = "resources/room.new.xsl")
	public void editRoom(final String[] path,
						 @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException {
		put("crud", "update");
		Room room = relationManager.getRoom(data);
		put("room", room);
		put("buildings", relationManager.getBuilding());
		put("features", relationManager.getFeature());
		put("roomfeature", room.whereSubjectOfRoomProvidesFeature());
	}

	@Commit(action = "roomedit", after = "roomlist")
	public void editRoomCommit(final String[] target,
							   @Arg(name = "data", intDefault = 0) final int data,
							   @Arg(name = "roomname", stringDefault = "") final String roomname,
							   @Arg(name = "building", stringDefault = "") final String building,
							   @Arg(name = "featurekey", stringDefault = "") final String[] featurekeys,
							   @Arg(name = "feature", stringDefault = "") final String[] features)
			throws DatabaseException {
		Room room = relationManager.getRoom(data);
		room.setName(roomname);
		Collection<Building> tmp = relationManager.getBuilding(eq("id", Integer.parseInt(building)));
		room.setBuilding(tmp.iterator().next());
		room.pushChanges();

		for (int i = 0; i < features.length; i++) {
			int quantity = 0;
			if (!features[i].isEmpty()) {
				quantity = Integer.parseInt(features[i]);
			}

			Collection<Feature> fs = relationManager.getFeature(eq("name", featurekeys[i]));
			if (fs.isEmpty()) {
				break;
			}
			Feature f = fs.iterator().next();
			Collection<RoomProvidesFeature> rpf = relationManager.getRoomProvidesFeature(room, f);
			if (!rpf.isEmpty()) {
				RoomProvidesFeature rel = rpf.iterator().next();
				// room has this feature already, override it
				if (quantity > 0) {
					rel.setQuantity(quantity);
					rel.pushChanges();
				} else {
					rel.delete();
				}
			} else {
				if (quantity > 0) {
					RoomProvidesFeature prov = relationManager.newRoomProvidesFeature(room, f, quantity);
					prov.create();
				}
			}
		}
		put("roomupdated", "1");
	}

	private boolean hasCircularReference(final Department depToCheck, final Department overDep)
			throws DatabaseException {
		if (overDep == null) {
			return false;
		} else if (depToCheck.id() == overDep.id()) {
			return true;
		} else {
			return hasCircularReference(depToCheck, overDep.getSuperordinateDepartment());
		}
	}

	@Action(name = "buildinglist", template = "resources/building.list.xsl")
	public void listBuilding(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "o_dir", intDefault = 0) final int o_dir,
							 @Arg(name = "o_col", intDefault = 0) final int o_col,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		List<Department> deps = relationManager.getDepartment(pageNumber * perPage, perPage);
		put("deps", String.valueOf(deps));
		put("data", relationManager.getBuilding(pageNumber * perPage, perPage));
		put("department", relationManager.getDepartment(pageNumber * perPage, perPage));
		put("a1", pageNumber);
		put("a2", perPage);
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getBuilding().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "departmentlist", template = "resources/department.list.xsl")
	public void listDepartment(final String[] path,
								@Arg(name = "page", intDefault = 0) final int pageNumber,
								@Arg(name = "o_dir", intDefault = 0) final int o_dir,
								@Arg(name = "o_col", intDefault = 0) final int o_col,
								@Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getDepartment(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getDepartment().size(), perPage,
												  pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "featurelist", template = "resources/feature.list.xsl")
	public void listFeatures(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getFeature(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getFeature().size(), perPage,
												  pageNumber)));
	}

	@Action(name = "roomlist", template = "resources/room.list.xsl")
	public void listRooms(final String[] path,
						  @Arg(name = "page", intDefault = 0) int pageNumber,
							 @Arg(name = "o_dir", stringDefault = "ascending") final String o_dir,
							 @Arg(name = "o_col", stringDefault = "") final String o_col,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		if (pageNumber < 0) {
			pageNumber = 0;
		}
		put("data", relationManager.getRoom(pageNumber * perPage, perPage));
		put("building", relationManager.getBuilding(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
		put("pages",
			getSiteNumbersWithDots(getSiteNumbers(relationManager.getRoom().size(), perPage, pageNumber)));
		put("o_dir", o_dir);
		put("o_col", o_col);
	}

	@Action(name = "buildingnew", template = "resources/building.new.xsl")
	public void newBuilding(final String[] path)
			throws DatabaseException {
		put("departments", relationManager.getDepartment());
		put("crud", "create");
	}

	@Commit(action = "buildingnew", after = "buildinglist")
	public void newBuildingCommit(final String[] target,
								  @Arg(name = "name", stringDefault = "") final String name,
								  @Arg(name = "address", stringDefault = "") final String address,
								  @Arg(name = "departments", intDefault = 0) final int departmentID)
			throws DatabaseException {
		if (!address.isEmpty() && !name.isEmpty()) {
			Building tmp = relationManager.newBuilding(address);
			tmp.setName(name);
			tmp.setDepartment(relationManager.getDepartment(departmentID));
			// XXX: Timekeys should be set by ORM-Manager
			// tmp.setTimekey(new Timestamp(System.currentTimeMillis()));
			tmp.create();
			put("dbsuccess", "Building created !");
		}
	}

	@Action(name = "departmentnew", template = "resources/department.new.xsl")
	public void newDepartment(final String[] path)
			throws DatabaseException {
		put("departments", relationManager.getDepartment());
		put("crud", "create");
	}

	@Commit(action = "departmentnew", after = "departmentlist")
	public void newDepartmentCommit(final String[] target,
									@Arg(name = "name", stringDefault = "") final String name,
									@Arg(name = "superordinateDepartment", intDefault = 0) final int superDepartment)
			throws DatabaseException {
		if (!name.isEmpty()) {
			Department tmp = relationManager.newDepartment(name);
			if (superDepartment > 0) {
				tmp.setSuperordinateDepartment(manager().getDepartment(superDepartment));
			} else {
				tmp.clearSuperordinateDepartment();
			}
			// XXX: Timekeys should be set by ORM-Manager
			// tmp.setTimekey(new Timestamp(System.currentTimeMillis()));
			tmp.create();
			put("dbsuccess", "Department created!");
		}
	}

	@Action(name = "featurenew", template = "resources/feature.new.xsl")
	public void newFeature(final String[] path)
			throws DatabaseException {
		put("crud", "create");
	}

	@Commit(action = "featurenew", after = "featurelist")
	public void newFeatureCommit(final String[] target,
								 @Arg(name = "data", stringDefault = "") final String data,
								 @Arg(name = "feature", stringDefault = "") final String feature)
			throws DatabaseException {
		if (!feature.isEmpty()) {
			Feature tmp = relationManager.newFeature(feature);
			tmp.create();
			put("dbsuccess", "Feature created");
		}
	}

	@Action(name = "roomnew", template = "resources/room.new.xsl")
	public void newRoom(final String[] path)
			throws DatabaseException {
		put("crud", "create");
		put("buildings", relationManager.getBuilding());
		put("features", relationManager.getFeature());
	}

	@Commit(action = "roomnew", after = "roomlist")
	public void newRoomCommit(final String[] target,
							  @Arg(name = "roomname", stringDefault = "") final String roomname,
							  @Arg(name = "building", stringDefault = "") final String building,
							  @Arg(name = "featurekey", stringDefault = "") final String[] featurekeys,
							  @Arg(name = "feature", stringDefault = "") final String[] features)
			throws DatabaseException {
		if (!roomname.equals("")) {
			int roomid = Integer.parseInt(building);
			Collection<Building> tmp = relationManager.getBuilding(eq("id", roomid));
			Room room =
					relationManager.newRoom(roomname.substring(0, roomname.length() % 3), tmp.iterator()
							.next());
			room.setName(roomname);
			// XXX: Timekeys should be set by ORM-Manager
			// room.setTimekey(new Timestamp(System.currentTimeMillis()));
			Collection<RoomProvidesFeature> roomfeatures = new ArrayList<RoomProvidesFeature>();
			for (int i = 0; i < features.length; i++) {
				int quantity = 0;
				if (!features[i].isEmpty()) {
					quantity = Integer.parseInt(features[i]);
				}

				if (quantity > 0) {
					Collection<Feature> feat = relationManager
							.getFeature(eq("name", featurekeys[i]));
					roomfeatures.add(relationManager
							.newRoomProvidesFeature(room, feat.iterator()
									.next(), quantity));
				}
			}
			room.create();
			for (RoomProvidesFeature x : roomfeatures) {
				x.create();
			}
			put("roomcreated", "1");
		}
	}

	@Action(name = "roomtimetable", template = "resources/roomtimetable.xsl")
	public void roomtimetable(final String[] target,
							  @Arg(name = "data", intDefault = 0) final int data)
			throws DatabaseException, SQLException {

		if (data > 0) {
			Room tmp = manager().getRoom(data);
			put("days", manager().getDay());
			put("proposedScheduling", manager()
					.getProposedScheduling(eq("room", tmp.getId())));
			put("metaceis", manager().getCourseElementInstance());
			put("timeslots", manager().getTimeslot());
			put("rooms", manager().getRoom());
			put("myroomfeature", tmp.whereSubjectOfRoomProvidesFeature());
		} else {
			Collection<Department> deps = manager().getDepartment();
			TreeMap<Integer,Integer> firstroomindeparment = new TreeMap<Integer,Integer>();
			int id = 0;
			for (Department x : deps) {
				Collection<Building> buildings = manager().getBuilding(eq("department",
																					 x.getId()));
				if (!buildings.isEmpty()) {
					id = buildings.iterator().next().getId();
					Collection<Room> rooms = manager().getRoom(eq("building", id));
					if (!rooms.isEmpty()) {
						id = rooms.iterator().next().getId();
					} else {
						id = 0;
					}
				} else {
					id = 0;
				}
				firstroomindeparment.put(x.getId(), id);
			}
			put("departments", deps);
			put("firstroom", firstroomindeparment);
		}
	}

	@Action(name = "roomtimetableedit", template = "resources/roomtimetable.edit.xsl")
	public void roomtimetableedit(final String[] target,
								  @Arg(name = "data", intDefault = 0) final int data,
								  @Arg(name = "oldtime", intDefault = 0) final int oldtime,
								  @Arg(name = "newtime", intDefault = 0) final int newtime)
			throws DatabaseException, SQLException {
		System.out.println("cei :: " + oldtime + " swaps to spot : " + newtime);
		if ((oldtime > 0) && (newtime > 0)) {
			System.out.println("spwapping-start");
			Collection<ProposedScheduling> pss = manager()
					.getProposedScheduling(eq("element_instance", oldtime));
			if (!pss.isEmpty()) {
				ProposedScheduling ps = pss.iterator().next();
				/** TODO :: wie viele slots hat ein tag -> momentan 12 default */
				if (ps.getElementInstance().getDuration() + ((newtime - 1) % 12) <= 12) { /*
																						   * dont go
																						   * over
																						   * dayborder
																						   */
					ps.setTimeslot(manager().getTimeslot(newtime));
					ps.pushChanges();
					put("foobar", ps.getTimeslot().getId());
					System.out.println("swapping out");
				} else {
					put("dbfail", "course can't be placed here, since there is not enough time");
				}
			}
		}
		put("days", manager().getDay());
		put("proposedScheduling", manager().getProposedScheduling(eq("room", data)));
		put("metaceis", manager().getCourseElementInstance());
		put("timeslots", manager().getTimeslot());
		put("rooms", manager().getRoom());
	}

	@Action(name = "featurevalidate", template = "resources/resources.menu.xsl")
	public void validateFeature(final String[] path,
								@Arg(name = "crud", stringDefault = "") final String action,
								@Arg(name = "data", stringDefault = "") final String data,
								@Arg(name = "feature", stringDefault = "") final String feature)
			throws DatabaseException {
		if (action.equals("delete")) {
			Collection<Feature> tmp = relationManager.getFeature(eq("id", Integer.parseInt(data)));
			Feature feat = tmp.iterator().next();
			Collection<RoomProvidesFeature> deletemefirst = feat.whereObjectOfRoomProvidesFeature();
			for (RoomProvidesFeature x : deletemefirst) {
				x.delete();
			}
			feat.delete();
			put("dbsuccess", "Feature deleted");
		}
	}
}
