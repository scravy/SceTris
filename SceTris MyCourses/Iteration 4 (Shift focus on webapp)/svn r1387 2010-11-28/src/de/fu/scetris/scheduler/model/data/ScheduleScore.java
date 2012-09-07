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

	int numberOfHardsConstraints;
	int numberOfHardConstraintsSatisfied;

	public ScheduleScore(int numberOfSoftConstraints,
			int numberOfSoftConstraintsSatisfied, int numberOfHardsConstraints,
			int numberOfHardConstraintsSatisfied) {
		super();
		this.numberOfSoftConstraints = numberOfSoftConstraints;
		this.numberOfSoftConstraintsSatisfied = numberOfSoftConstraintsSatisfied;
		this.numberOfHardsConstraints = numberOfHardsConstraints;
		this.numberOfHardConstraintsSatisfied = numberOfHardConstraintsSatisfied;
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
	public int getNumberOfHardsConstraints() {
		return numberOfHardsConstraints;
	}

	@XmlAttribute("numberOfHardConstraintsSatisfied")
	public int getNumberOfHardConstraintsSatisfied() {
		return numberOfHardConstraintsSatisfied;
	}

}
