/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import static de.fu.weave.orm.filters.Filters.*;

import java.sql.Timestamp;
import java.util.Collection;

import de.fu.scetris.data.*;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Andre Zoufahl
 * @since Iteration3
 */
@ModuleInfo(name = "ressources",
			author = "Andre Zoufahl",
			description = "Ressources",
			requires = Requirement.DATABASE)
public class Ressources extends GenericModule<RelationManager> {

	public Ressources(final Scetris parent) {
		super(parent);
	}

	@Action(template = "ressources/ressources.menu.xsl")
	public void _default(final String[] path) {
		
	}

	@Action(name = "buildinglist", template = "ressources/building.list.xsl")
	public void listBuilding(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getBuilding(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
	}

	@Action(name = "buildingnew", template = "ressources/building.new.xsl")
	public void newBuilding(final String[] path)
			throws DatabaseException {
		put("crud","create");
	}
	
	@Action(name = "buildingedit", template = "ressources/building.new.xsl")
	public void editBuilding(final String[] path,
						@Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		put("crud","update");
		Collection<Building> tmp = relationManager.getBuilding(eq("id", Integer.parseInt(data)));
		de.fu.scetris.data.Building build = tmp.iterator().next();
		put("building", build);
	} 

	@Action(name = "buildingvalidate", template = "ressources/ressources.menu.xsl")
	public void validateBuilding(final String[] path,
						@Arg(name = "crud", stringDefault = "") final String action,
						@Arg(name = "data", stringDefault = "") final String data,
						@Arg(name = "name", stringDefault = "") final String name,
						@Arg(name = "address", stringDefault = "") final String address)
			throws DatabaseException {
		if(action.equals("create")) {
			if (!address.isEmpty() && !name.isEmpty()) {
				Building tmp = relationManager.newBuilding(address);
				tmp.setName(name);
				tmp.setTimekey(new Timestamp(System.currentTimeMillis()));
				tmp.create();				
				put("dbsuccess","Building created !"); 
			}
		} else if (action.equals("update")) {
			Collection<Building> builds = relationManager.getBuilding(eq("id", Integer.parseInt(data)));
			Building build = builds.iterator().next();
			build.setName(name);
			build.setAddress(address);
			build.pushChanges();
			put("dbsuccess","Building updated");
		} else if (action.equals("delete")) {
			Collection<Building> builds = relationManager.getBuilding(eq("id", Integer.parseInt(data)));
			Building build = builds.iterator().next();
			Collection<Room> rooms = relationManager.getRoom(eq("building", build.getId()));
			if(rooms.isEmpty()) {
				build.delete();
				put("dbsuccess","Building deleted");
			} else {
				// building in use, do nothing
				put("dbfail","Building still in use");
			}
		}	
	}		
	
	
	@Action(name = "featurelist", template = "ressources/feature.list.xsl")
	public void listFeatures(final String[] path,
							 @Arg(name = "page", intDefault = 0) final int pageNumber,
							 @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("data", relationManager.getFeature(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
	}

	@Action(name = "featurenew", template = "ressources/feature.new.xsl")
	public void newFeature(final String[] path)
			throws DatabaseException {
		put("crud","create");
	}
	
	@Action(name = "featureedit", template = "ressources/feature.new.xsl")
	public void editFeature(final String[] path,
						@Arg(name = "data", stringDefault = "") final String data)
			throws DatabaseException {
		put("crud","update");
		Collection<Feature> tmp = relationManager.getFeature(eq("id", Integer.parseInt(data)));
		de.fu.scetris.data.Feature feat = tmp.iterator().next();
		put("feature", feat);
	} 
	
	@Action(name = "featurevalidate", template = "ressources/ressources.menu.xsl")
	public void validateFeature(final String[] path,
						@Arg(name = "crud", stringDefault = "") final String action,
						@Arg(name = "data", stringDefault = "") final String data,
						@Arg(name = "feature", stringDefault = "") final String feature,
						@Arg(name = "address", stringDefault = "") final String address)
			throws DatabaseException {
		if(action.equals("create")) {
			if (!feature.isEmpty()) {
				Feature tmp = relationManager.newFeature(feature);
				tmp.create();
				put("dbsuccess","Feature created");
			}
		} else if (action.equals("update")) {
			Collection<Feature> tmp = relationManager.getFeature(eq("id", Integer.parseInt(data)));
			Feature feat = tmp.iterator().next();
			feat.setName(feature);
			feat.pushChanges();
			put("dbsuccess","Feature updated");
		} else if (action.equals("delete")) {
			Collection<Feature> tmp = relationManager.getFeature(eq("id", Integer.parseInt(data)));
			Feature feat = tmp.iterator().next();
			Collection<RoomProvidesFeature> deletemefirst = feat.whereObjectOfRoomProvidesFeature();
			for(RoomProvidesFeature x : deletemefirst) {
				x.delete();
			}
			feat.delete();
			put("dbsuccess","Feature deleted");
		}	
	}	

	@Action(name = "roomlist", template = "ressources/room.list.xsl")
	public void listRooms(final String[] path,
						  @Arg(name = "page", intDefault = 0) int pageNumber,
						  @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		if(pageNumber<0)
			pageNumber=0;
		put("data", relationManager.getRoom(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
	}
	
	@Action(name = "roomedit", template = "ressources/room.new.xsl")
	public void editRoom(final String[] path,
						@Arg(name = "data", stringDefault = "") final String data,
						@Arg(name = "roomname", stringDefault = "") final String roomname,
						@Arg(name = "building", stringDefault = "") final String building,
						@Arg(name = "featurekey", stringDefault = "") final String[] featurekeys,
						@Arg(name = "feature", stringDefault = "") final String[] features)
			throws DatabaseException {
		put("crud","update");
		Collection<Room> rooms = relationManager.getRoom(eq("id", Integer.parseInt(data)));
		de.fu.scetris.data.Room room = rooms.iterator().next();
		put("room", room);
		put("buildings", relationManager.getBuilding());
		put("features", relationManager.getFeature());
		put("roomfeature", room.whereSubjectOfRoomProvidesFeature());
	} 
	
	@Action(name = "roomvalidate", template = "ressources/ressources.menu.xsl")
	public void validateRoom(final String[] path,
						@Arg(name = "crud", stringDefault = "") final String action,
						@Arg(name = "data", stringDefault = "") final String data,
						@Arg(name = "roomname", stringDefault = "") final String roomname,
						@Arg(name = "building", stringDefault = "") final String building,
						@Arg(name = "featurekey", stringDefault = "") final String[] featurekeys,
						@Arg(name = "feature", stringDefault = "") final String[] features)
			throws DatabaseException {
		if(action.equals("create")) {
			if (!roomname.equals("")) {
				int roomid = Integer.parseInt(building);
				Collection<de.fu.scetris.data.Building> tmp = relationManager.getBuilding(eq("id", roomid));
				de.fu.scetris.data.Room room =
						relationManager.newRoom(roomname.substring(0, roomname.length() % 3), tmp.iterator()
								.next());
				room.setName(roomname);
				room.setTimekey(new Timestamp(System.currentTimeMillis()));
				room.create();
				for (int i = 0; i < features.length; i++) {
					int quantity = 0;
					if(!features[i].isEmpty()) {
						quantity = Integer.parseInt(features[i]);
					}
					
					if (quantity > 0) {
						Collection<de.fu.scetris.data.Feature> feat = relationManager
								.getFeature(eq("name", featurekeys[i]));
						de.fu.scetris.data.RoomProvidesFeature prov = relationManager
								.newRoomProvidesFeature(room, feat.iterator()
										.next(), quantity);
						prov.create();
					}
				}
				put("roomcreated","1");
			} 
		} else if (action.equals("update")) {
			Collection<Room> rooms = relationManager.getRoom(eq("id", Integer.parseInt(data)));
			de.fu.scetris.data.Room room = rooms.iterator().next();
			room.setName(roomname);
			Collection<Building> tmp = relationManager.getBuilding(eq("id", Integer.parseInt(building)));
			room.setBuilding(tmp.iterator().next());
			room.pushChanges();
			
			for(int i = 0; i < features.length; i++) {
				int quantity = 0;
				if(!features[i].isEmpty()) {
					quantity = Integer.parseInt(features[i]);
				}
				
				Collection<Feature> fs = relationManager.getFeature(eq("name", featurekeys[i]));
				Feature f = fs.iterator().next();
				Collection<RoomProvidesFeature> rpf = relationManager.getRoomProvidesFeature(room, f);
				if(!rpf.isEmpty()) {
					RoomProvidesFeature rel = rpf.iterator().next();
					// room has this feature already, override it
					if(quantity > 0) {
						rel.setQuantity(quantity);
						rel.pushChanges();
					} else {
						rel.delete();
					}
				} else {
					if(quantity > 0) {
						RoomProvidesFeature prov = relationManager.newRoomProvidesFeature(room, f, quantity);
						prov.create();
					}
				}
			}
			put("roomupdated","1");
		} else if (action.equals("delete")) {
			Collection<Room> rooms = relationManager.getRoom(eq("id", Integer.parseInt(data)));
			de.fu.scetris.data.Room room = rooms.iterator().next();
			Collection<RoomProvidesFeature> deletemefirst = room.whereSubjectOfRoomProvidesFeature();
			for(RoomProvidesFeature x : deletemefirst)
				x.delete();
			room.delete();
			put("roomdeleted","1");
		}
	} 		
	
	@Action(name = "roomnew", template = "ressources/room.new.xsl")
	public void newRoom(final String[] path)
			throws DatabaseException {
		put("crud","create");
		put("buildings", relationManager.getBuilding());
		put("features", relationManager.getFeature());
	}
}
