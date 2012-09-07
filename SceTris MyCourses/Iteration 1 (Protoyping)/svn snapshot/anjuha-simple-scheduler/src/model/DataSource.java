package model;

/**
 * A DataSource contains Data retrieved from a Database
 * such as an XML-File or a full-featured DBMS such as
 * PostgreSQL.
 *
 * TODO: The functionality from DataSource can be adapted
 *       from /branches/konrad-scheduler-prototype/src/xml/Builder.java
 *       The XML-Format would need to be slightly adapted to meet
 *       the definitions of the simple scheduler. It can be obtained
 *       form /branches/konrad-scheduler-prototype/xml/prototype.dtd
 */
class DataSource
{
	public DataSource() {
		
	}
	
	protected int numberOfChromosomesPerGeneration;
	
	protected int maximumNumberOfBestChromosomes;
	
	protected int numberOfRooms;
	
	protected int numberOfCourses;
	
	/**
	 *
	 */
	public int getNumberOfChromosomesPerGeneration() {
		return numberOfChromosomesPerGeneration;
	}
	
	/**
	 *
	 */
	public int getMaximumNumberOfBestChromosomes() {
		return maximumNumberOfBestChromosomes;
	}
	
	/**
	 *
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	
	/**
	 *
	 */
	public int getNumberOfCourses() {
		return numberOfCourses;
	}
	
}
