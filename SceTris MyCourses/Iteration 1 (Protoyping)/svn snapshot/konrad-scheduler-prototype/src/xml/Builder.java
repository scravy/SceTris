package xml;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import model.*;
import java.util.*;

/**
 * @author Julian
 * @version Iteration 1
 */
public class Builder {

	protected DocumentBuilder xmlBuilder;
	protected Exception lastException;
	
	protected Map<Long,Professor> professorsMap = new TreeMap<Long,Professor>();
	protected Map<Long,StudentsGroup> studentsGroupsMap = new TreeMap<Long,StudentsGroup>();
	protected Map<Long,Course> coursesMap = new TreeMap<Long,Course>();
	protected Map<Long,Room> roomsMap = new TreeMap<Long,Room>();
	protected List<CourseClass> courseClasses = new ArrayList<CourseClass>();
	
	protected int daysNum;
	protected int daysHours;
	protected int numberOfRooms;
	protected int numberOfClasses;
	
	
	public int getDaysNum() {
		return daysNum;
	}
	
	public int getDaysHours() {
		return daysHours;
	}
	
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	
	public static void main(String... args) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage: java Builder <file>");
			System.exit(0);
		}
		Builder b = new Builder();
		if (b.load(args[0])) {
			System.out.println("Success!");
			System.out.println("== The following courses were imported:");
			for (Course c : b.getCourses()) {
				System.out.println(c.getName());
			}
			System.out.println("== The following professors were imported:");
			for (Professor p : b.getProfessors()) {
				System.out.println(p.getName());
				for (CourseClass c : p.getCourseClasses()) {
					System.out.print("["+c.getCourse().getName()+"]");
				}
				System.out.println();
			}
		} else {
			throw b.getLastException();
		}
	}
	
	public List<Professor> getProfessors() {
		return new ArrayList<Professor>(professorsMap.values());
	}
	
	public List<CourseClass> getCourseClasses() {
		return courseClasses;
	}
	
	public List<Room> getRooms() {
		return new ArrayList<Room>(roomsMap.values());
	}
	
	public List<StudentsGroup> getStudentsGroups() {
		return new ArrayList<StudentsGroup>(studentsGroupsMap.values());
	}
	
	public List<Course> getCourses() {
		return new ArrayList<Course>(coursesMap.values());
	}
	
	/**
	 * Standard-Constructor
	 * @throws RuntimeException Should only happen if your java-Installation is seriously fucked up
	 */
	public Builder() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			xmlBuilder = factory.newDocumentBuilder();
		} catch (Exception exc) {
			throw new RuntimeException("Man, you seriously got screwed");
		}
	}
	
	public Room getRoomByID(long id) {
		return roomsMap.get(id);
	}
	
	/**
	 * @return boolean whether the document was successfully loaded or not
	 */
	public boolean load(String filename) {
		try {
			// first, we need a document
			Document doc = xmlBuilder.parse(new File(filename));
			Element docEl = doc.getDocumentElement();
			daysNum = Integer.parseInt(docEl.getAttribute("daysNum"));
			daysHours = Integer.parseInt(docEl.getAttribute("daysHours"));
			numberOfRooms = Integer.parseInt(docEl.getAttribute("numberOfRooms"));
			numberOfClasses = Integer.parseInt(docEl.getAttribute("numberOfClasses"));
			NodeList topLevelElements = docEl.getChildNodes();
			for (int i = 0; i < topLevelElements.getLength(); i++) {
				Node n = topLevelElements.item(i);
				if (!(n instanceof Element)) continue;
				Element e = (Element) n;
				
				if (e.getTagName().equals("professors")) {
					importProfessors(e.getChildNodes());
				} else if (e.getTagName().equals("studentsGroups")) {
					importStudentsGroups(e.getChildNodes());
				} else if (e.getTagName().equals("courseClasses")) {
					importCourseClasses(e.getChildNodes());
				} else if (e.getTagName().equals("courses")) {
					importCourses(e.getChildNodes());
				} else if (e.getTagName().equals("rooms")) {
					importRooms(e.getChildNodes());
				}
			}
			
		} catch (RuntimeException exc) {
			throw exc;
		} catch (Exception exc) {
			lastException = exc;
			return false;
		}
		return true;
	}
	
	/**
	 * @internal done
	 */
	protected void importProfessors(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!(n instanceof Element)) continue;
			Element e = (Element) n;
			long id = Long.parseLong(e.getAttribute("id").substring(1));
			
			professorsMap.put(id, new Professor(id, e.getFirstChild().getNodeValue(), new ArrayList<CourseClass>()));
		}
	}
	
	/**
	 * @internal done
	 */
	protected void importCourses(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!(n instanceof Element)) continue;
			Element e = (Element) n;
			long id = Long.parseLong(e.getAttribute("id").substring(1));
			
			coursesMap.put(id, new Course(id, e.getFirstChild().getNodeValue()));
		}
	}
	
	/**
	 *
	 */
	protected void importCourseClasses(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!(n instanceof Element)) continue;
			Element e = (Element) n;
			
			long pid = Long.parseLong(e.getAttribute("professor").substring(1));
			Professor p = professorsMap.get(pid);
			
			long cid = Long.parseLong(e.getAttribute("course").substring(1));
			Course c = coursesMap.get(cid);
			
			int seats = Integer.parseInt(e.getAttribute("requiredSeats"));
			boolean lab = e.getAttribute("lab").equals("true");
			int duration = Integer.parseInt(e.getAttribute("durationInHours"));
			
			ArrayList<StudentsGroup> studentsGroups = new ArrayList<StudentsGroup>();
			String[] groupIDs = e.getAttribute("studentsGroups").split("\\s+");
			long[] groups = new long[groupIDs.length];
			int j = 0;
			for (String str : groupIDs) {
				long gid = Long.parseLong(str.substring(1));
				studentsGroups.add(studentsGroupsMap.get(gid));
			}
			CourseClass cc = new CourseClass(p, c, studentsGroups, seats, lab, duration);
			p.getCourseClasses().add(cc);
			for (StudentsGroup g : studentsGroups) {
				g.getAttendedClasses().add(cc);
			}
			courseClasses.add(cc);
		}
		/*
		public CourseClass(Professor professor, Course couse,
	    List<StudentsGroup> attendedStudentGroups, int requiredSeats,
	    boolean requiresLab, int durationInHours)
	    */
	}
	
	/**
	 * @internal done
	 */
	protected void importStudentsGroups(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!(n instanceof Element)) continue;
			Element e = (Element) n;
			
			long id = Long.parseLong(e.getAttribute("id").substring(1));
			
			studentsGroupsMap.put(id, new StudentsGroup(id,
				e.getFirstChild().getNodeValue(),
				Integer.parseInt(e.getAttribute("numberOfStudents")),
				new ArrayList<CourseClass>()
			));
				
		}
	}
	
	/**
	 * @internal done
	 */
	protected void importRooms(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!(n instanceof Element)) continue;
			Element e = (Element) n;
			
			long id = Long.parseLong(e.getAttribute("id").substring(1));
			int numberOfSeats = Integer.parseInt(e.getAttribute("numberOfSeats"));
			
			roomsMap.put(id, new Room(id, numberOfSeats, e.getAttribute("lab").equals("true")));
		}
	}
	
	/**
	 * @return Exception Returns the last Exception that was catched by the underlying XML-processing
	 */
	public Exception getLastException() {
		return lastException;
	}
}