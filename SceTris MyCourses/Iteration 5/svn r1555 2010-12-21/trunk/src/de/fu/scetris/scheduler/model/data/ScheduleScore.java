package de.fu.scetris.scheduler.model.data;

import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * Provides information about the schedule score.
 * 
 * @author Konrad Reiche
 * 
 */
public class ScheduleScore {

	int numberOfSoftConstraints;
	int numberOfSoftConstraintsSatisfied;
	int sumSoftConstraints;
	int sumSoftConstraintsSatisfied;

	int numberOfHardConstraints;
	int numberOfHardConstraintsSatisfied;
	int sumHardConstraints;
	int sumHardConstraintsSatisfied;
	
	public ScheduleScore(int numberOfSoftConstraints,
			int numberOfSoftConstraintsSatisfied, int numberOfHardsConstraints,
			int numberOfHardConstraintsSatisfied, int sumSoftConstraints,
			int sumSoftConstraintsSatisfied, int sumHardConstraints,
			int sumHardConstraintsSatisfied) {
		super();
		this.numberOfSoftConstraints = numberOfSoftConstraints;
		this.numberOfSoftConstraintsSatisfied = numberOfSoftConstraintsSatisfied;
		this.numberOfHardConstraints = numberOfHardsConstraints;
		this.numberOfHardConstraintsSatisfied = numberOfHardConstraintsSatisfied;
		
		this.sumSoftConstraints = sumSoftConstraints;
		this.sumSoftConstraintsSatisfied = sumSoftConstraintsSatisfied;
		this.sumHardConstraints = sumHardConstraints;
		this.sumHardConstraintsSatisfied = sumHardConstraintsSatisfied;
	}

	@XmlAttribute("numberOfSoftConstraints")
	public int getNumberOfSoftConstraints() {
		return numberOfSoftConstraints;
	}

	@XmlAttribute("numberOfSoftConstraintsSatisfied")
	public int getNumberOfSoftConstraintsSatisfied() {
		return numberOfSoftConstraintsSatisfied;
	}

	@XmlAttribute("numberOfHardConstraints")
	public int getNumberOfHardConstraints() {
		return numberOfHardConstraints;
	}

	@XmlAttribute("numberOfHardConstraintsSatisfied")
	public int getNumberOfHardConstraintsSatisfied() {
		return numberOfHardConstraintsSatisfied;
	}
	
	@XmlAttribute("sumSoftConstraints")
	public int getSumSoftConstraints() {
		return sumSoftConstraints;
	}

	@XmlAttribute("sumSoftConstraintsSatisfied")	
	public int getSumSoftConstraintsSatisfied() {
		return sumSoftConstraintsSatisfied;
	}

	@XmlAttribute("sumHardConstraints")
	public int getSumHardConstraints() {
		return sumHardConstraints;
	}

	@XmlAttribute("sumHardConstraintsSatisfied")
	public int getSumHardConstraintsSatisfied() {
		return sumHardConstraintsSatisfied;
	}

}
