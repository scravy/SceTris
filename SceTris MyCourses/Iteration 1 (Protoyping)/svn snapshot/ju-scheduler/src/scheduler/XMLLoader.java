package scheduler;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Loads data from an XML file and creates objects in a given scheduler or creates a new scheduler
 */
public class XMLLoader {
	private DocumentBuilder xmlBuilder;
	
	/**
	 * The scheduler which data is loaded into
	 */
	private Scheduler scheduler;
	
	
	/**
	 * Creates a new Scheduler for loading data into.
	 */
	XMLLoader() {
		this.scheduler = new Scheduler();
		this.initBuilder();
	}
	
	/**
	 * Uses an existing scheduler for laoding data into.
	 */
	XMLLoader(Scheduler scheduler) {
		this.scheduler = scheduler;
		this.initBuilder();
	}
	
	/**
	 * Initializes the xmlBuilder
	 */
	private void initBuilder() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			this.xmlBuilder = factory.newDocumentBuilder();
		} catch (Exception exc) {
			throw new RuntimeException("Man, you seriously got screwed");
		}
	}
	
	/**
	 * Loads an XML-File from a given Filename.
	 */
	public boolean load(String filename) {
		return load(new File(filename));
	}
	
	/**
	 * Loads an XML-File which is specified by a File-Object.
	 */
	public boolean load(File file) {
		try {
			// first, we need a document
			if(!file.isFile())
				System.out.println("'"+file.getAbsolutePath()+"' ist kein valider Pfad");
			Document doc = this.xmlBuilder.parse(file);
			Element docEl = doc.getDocumentElement();
//			daysNum = Integer.parseInt(docEl.getAttribute("daysNum"));
//			daysHours = Integer.parseInt(docEl.getAttribute("daysHours"));
//			numberOfRooms = Integer.parseInt(docEl.getAttribute("numberOfRooms"));
//			numberOfClasses = Integer.parseInt(docEl.getAttribute("numberOfClasses"));
			NodeList topLevelElements = docEl.getChildNodes();
			for (int i = 0; i < topLevelElements.getLength(); i++) {
				Node n = topLevelElements.item(i);
				if (!(n instanceof Element)) continue;
				Element e = (Element) n;
				if (e.getTagName().equals("professors")) {
					importProfessors(e.getChildNodes());
				} else if (e.getTagName().equals("courses")) {
					importCourses(e.getChildNodes());
				} else if (e.getTagName().equals("rooms")) {
					importRooms(e.getChildNodes());
				}
			}	
			System.out.println("--- IMPORT DONE ---");
		} catch (RuntimeException exc) {
			throw exc;
		} catch (Exception exc) {
			exc.printStackTrace();
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
			
			// int id = Integer.parseInt(e.getAttribute("id").substring(1));		/* not yet used */
			this.scheduler.createLecturer(e.getFirstChild().getNodeValue());
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
			
			// int id = Integer.parseInt(e.getAttribute("id").substring(1));		/* not yet used */
			int reqSeats = Integer.parseInt(e.getAttribute("requiredSeats"));
			int dura = Integer.parseInt(e.getAttribute("durationInHours"));
			int prof = Integer.parseInt(e.getAttribute("professor").substring(1));	// to drop the p in profref 'p123' in the xml-file 
			this.scheduler.createCourse(e.getFirstChild().getNodeValue(), this.scheduler.findLecturerById(prof), reqSeats, dura);
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
			
			// int id = Integer.parseInt(e.getAttribute("id").substring(1));		/* not yet used */
			int numberOfSeats = Integer.parseInt(e.getAttribute("numberOfSeats"));
			this.scheduler.createRoom(e.getFirstChild().getNodeValue(), numberOfSeats);
		}
	}
	
	
	
	/**
	 * Returns the associated Scheduler-Object.
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}
}
