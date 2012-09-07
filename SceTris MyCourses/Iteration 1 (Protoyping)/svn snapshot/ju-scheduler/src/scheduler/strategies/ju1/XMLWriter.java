package scheduler.strategies.ju1;

import java.io.File;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Loads data form an XML file and creates objects in a given scheduler or creates a new scheduler
 */
public class XMLWriter {
	private DocumentBuilder xmlBuilder;
	private Document xmlDoc;
	private Element room, day, root;
	private Node kurs;
	
	/**
	 * The Individual which data is read from
	 */
	private IndividualImpl impl;
	private String filename;
	
	
	/**
	 * Uses an existing scheduler for laoding data into.
	 */
	XMLWriter(IndividualImpl impl, String filename) {
		this.impl = impl;
		this.filename = filename;
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
	 * Loads an XML-File which is specified by a File-Object.
	 */
	public boolean generate() {		
		try {
			DOMImplementation impl = xmlBuilder.getDOMImplementation();
			this.xmlDoc = impl.createDocument(null, "booking", null);
			
			this.fillWithListData();
			// this.fillWithRealData();
			// this.fillWithDummyData();
			
			try {
				DOMSource domSource = new DOMSource(xmlDoc);
				StreamResult streamResult = new StreamResult(new PrintWriter(new File(this.filename)));
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer serializer = tf.newTransformer();
				serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "booking.dtd");
				serializer.setOutputProperty(OutputKeys.INDENT, "yes");
				serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				serializer.transform(domSource, streamResult);
			} catch (Exception e) {
			  e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}
	
	/**
	 * 
	 */
	private void fillWithListData() {
		/**
		 * 1st dim: timeslots
		 * 2nd dim: rooms
		 * 3rd dim: meta information about this slot:
		 *	- 0: course id
		 *	- 1: lecturer id
		 *	- 2: quality of this gene
		 */
		this.root = xmlDoc.getDocumentElement();
		Element hour = xmlDoc.createElementNS(null , "hoursaday");
		hour.appendChild(xmlDoc.createTextNode(Integer.toString(impl.hours)));
		this.root.appendChild(hour);
		Element day = xmlDoc.createElementNS(null , "daysaweek");
		day.appendChild(xmlDoc.createTextNode(Integer.toString(impl.days)));
		this.root.appendChild(day);
		
		for(int j=0; j < impl.nextRoomID; j++) {
			this.room = xmlDoc.createElementNS(null , "room");
			this.room.setAttribute("name", impl.rooms[j].getName());
			// System.out.println("j :: "+j);
			for(int i=0; i < impl.schedule.length; i++) {
				if(impl.schedule[i][j][0] >= 0) {				
					Element course;
					course = xmlDoc.createElementNS(null, "course");
					course.setAttribute("time", Integer.toString(1+(i%impl.hours)));
					course.setAttribute("day", Integer.toString(1+(i/impl.hours)));
					course.setAttribute("name", impl.courses[impl.schedule[i][j][0]].getName());
					course.setAttribute("lecturer", impl.lecturers[impl.schedule[i][j][1]-1].getName());
					course.setAttribute("duration", Integer.toString(impl.courses[impl.schedule[i][j][0]].getDuration()));
					this.room.appendChild(course);
				} 
			}
			this.root.appendChild(this.room);
		}
		Node pi = xmlDoc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"list.xsl\"");
		xmlDoc.insertBefore(pi, this.root);

	}
	
	/**
	 * Fills the xml with the scheduling-data
	 * 
	 */
	private void fillWithRealData() {
		/**
		 * 1st dim: timeslots
		 * 2nd dim: rooms
		 * 3rd dim: meta information about this slot:
		 *	- 0: course id
		 *	- 1: lecturer id
		 *	- 2: quality of this gene
		 */
		this.root = xmlDoc.getDocumentElement();
		Element hour = xmlDoc.createElementNS(null , "hoursaday");
		hour.appendChild(xmlDoc.createTextNode(Integer.toString(impl.hours)));
		this.root.appendChild(hour);
		Element day = xmlDoc.createElementNS(null , "daysaweek");
		day.appendChild(xmlDoc.createTextNode(Integer.toString(impl.days)));
		this.root.appendChild(day);
		
		for(int j=0; j < impl.nextRoomID; j++) {
			this.room = xmlDoc.createElementNS(null , "room");
			this.room.setAttribute("name", impl.rooms[j].getName());
			// System.out.println("j :: "+j);
			for(int i=0; i < impl.schedule.length; i++) {
				this.day = xmlDoc.createElementNS(null, "timeslot");
				this.day.setAttribute("time", Integer.toString(1+(i%impl.hours)));
				//System.out.println("  i :: "+i);
				//for(int k=0; k < impl.schedule[i][j].length; k++) {
				//	System.out.println("    k :: "+k+" -> "+impl.schedule[i][j][k]);					
				//}
				Element course;
				if(impl.schedule[i][j][0] >= 0) {
					course = xmlDoc.createElementNS(null, "course");
					course.setAttribute("day", Integer.toString(1+(i/impl.hours)));
					course.setAttribute("name", impl.courses[impl.schedule[i][j][0]].getName());
					course.setAttribute("lecturer", impl.lecturers[impl.schedule[i][j][1]].getName());
					course.setAttribute("duration", Integer.toString(impl.courses[impl.schedule[i][j][0]].getDuration()));
					// this.kurs = xmlDoc.createTextNode(impl.courses[impl.schedule[i][j][0]].getName());
				} else {
					course = xmlDoc.createElementNS(null, "course");
					// this.kurs = xmlDoc.createTextNode("-");
				}
				this.day.appendChild(course);
				this.room.appendChild(this.day);
			}
			this.root.appendChild(this.room);

		}
		
		Node pi = xmlDoc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"output.xsl\"");
		xmlDoc.insertBefore(pi, this.root);

	}
	
	
	private void fillWithDummyData() {
		this.room = xmlDoc.createElementNS(null, "room");
		this.room.setAttribute("name", "Seminarraum");
		this.day = null;
		this.day = xmlDoc.createElementNS(null, "day");
		this.day.setAttribute("name", "monday");
		this.day.setAttribute("slot", "10");
		this.kurs = xmlDoc.createTextNode("Kurs XYZ");
		this.day.appendChild(this.kurs);
		this.room.appendChild(this.day);
		this.root = xmlDoc.getDocumentElement();
		this.root.appendChild(this.room);
	}
	
	
	/**
	 * Returns the associated Scheduler-Object.
	 */
	public IndividualImpl getIndividualImpl() {
		return impl;
	}
}
