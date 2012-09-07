package xml;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import model.data.*;

import java.util.*;

/**
 * last time modified Andre
 * @author Julian
 * @version Iteration 1
 */
public class Builder {

	protected DocumentBuilder xmlBuilder;
	protected Exception lastException;
	
	protected Map<Long,Lecturer> professorsMap = new TreeMap<Long,Lecturer>();
	protected Map<Long,Course> coursesMap = new TreeMap<Long,Course>();
	protected Map<Long,Room> roomsMap = new TreeMap<Long,Room>();
	
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
		String file = null;
		if (args.length == 1) {
			file = args[0];
		} else {
			file = "xml/test_compare.xml";
		}
		Builder b = new Builder();
		if (b.load(file)) {			// Logic of the Builder, imports everything
			System.out.println("Success!");
			System.out.println("== The following rooms were imported:");
			for (Room r : b.getRooms()) {
				System.out.println(r.getId());
			}
			System.out.println("== The following professors including courses were imported:");
			for (Lecturer p : b.getProfessors()) {
				System.out.println(p.getName());
				for(Course c : p.getCourses()) {
					System.out.println("   ["+c.getShortInfo()+"]");
				}
 			}
//			System.out.println("== The following courses were imported:");
//			for (Course c : b.getCourses()) {
//				System.out.println(c.getShortInfo());
//			}

		} else {
			throw b.getLastException();
		}
	}
	
	public List<Lecturer> getProfessors() {
		return new ArrayList<Lecturer>(professorsMap.values());
	}
	
	public List<Room> getRooms() {
		return new ArrayList<Room>(roomsMap.values());
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
				} else if (e.getTagName().equals("courseClasses")) {
					importCourseClasses(e.getChildNodes());
				} else if (e.getTagName().equals("courses")) {
					importCourseNames(e.getChildNodes());
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
			int id = Integer.parseInt(e.getAttribute("id").substring(1));
			
			professorsMap.put((long)id, new Lecturer(id, e.getFirstChild().getNodeValue()));
		}
	}
	
	/**
	 * @internal done
	 */
	protected void importCourseNames(NodeList nodes) {
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!(n instanceof Element)) continue;
			Element e = (Element) n;
			// int id = Integer.parseInt(e.getAttribute("id").substring(1));
			String id = e.getAttribute("id").substring(1);
			String name = e.getFirstChild().getNodeValue();
			
			for(Map.Entry<Long,Course> c: coursesMap.entrySet()) {
				if(c.getValue().name.equals(id)) {
					c.getValue().name = name;
				}
			}
			// coursesMap.put((long)id, new Course(id, e.getFirstChild().getNodeValue()));
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
			
			Long pid = Long.parseLong(e.getAttribute("professor").substring(1));
			Lecturer p = professorsMap.get(pid);
			
			Long cid = Long.parseLong(e.getAttribute("course").substring(1));
			// Course c = coursesMap.get(cid);
			
			int seats = Integer.parseInt(e.getAttribute("requiredSeats"));
			// boolean lab = e.getAttribute("lab").equals("true");
			int duration = Integer.parseInt(e.getAttribute("durationInHours"));
				
			// c.setAmountOfHours(duration);
			// c.setAmountOfSeatsRequired(seats);
			
			Course c = new Course (Long.toString(cid) , duration, seats, p);
			coursesMap.put((long)i, c);
			
			if(!p.getCourses().contains(c))
				p.addCourses(c);
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
			
			int id = Integer.parseInt(e.getAttribute("id").substring(1));
			int numberOfSeats = Integer.parseInt(e.getAttribute("numberOfSeats"));
			
			roomsMap.put((long)id, new Room(id, numberOfSeats, e.getAttribute("lab").equals("true")));
		}
	}
	
	/**
	 * @return Exception Returns the last Exception that was catched by the underlying XML-processing
	 */
	public Exception getLastException() {
		return lastException;
	}
}
