package view;

import model.*;
import xml.Builder;

/**
 *
 */
public class Main {

    private static String DAYS[] = { "MON", "THU", "WEN", "THR", "FRI" };

    /**
	 *
	 */
    private static Algorithm algorithm;

    public static void main(String[] args) {

	if (args.length >= 2 && args[0].equals("-d")) {
	    Builder xmlBuilder = new Builder();
	    Schedule.setXmlBuild(xmlBuilder);

	    if (!xmlBuilder.load(args[1])) {
		System.out.println("Error: Loading data failed.");
		System.exit(-1);
	    }
	    System.out.println("Data has been initialised.");
	    algorithm = Algorithm.getInstance();
	    algorithm.start();
	    System.out.println("Algorithm has finished.");
	} else {
	    System.out.println("Usage \"Main -d [path]\"");
	}
	System.out.println(printSchedule(algorithm, algorithm
		.getBestChromosome()));
    }

    public static String printSchedule(Algorithm algorithm, Schedule schedule) {

	String result = "";

	result = result.concat("Generation: "
		+ algorithm.getCurrentGeneration() + "\n");
	result = result.concat("Fitness: " + schedule.getFitness() + "\n");

	int nr = schedule.xmlBuild.getNumberOfRooms();

	for (int j = 0; j < nr; j++) {
	    result = result.concat("Room     ");
	    result = result.concat("R"
		    + schedule.xmlBuild.getRoomByID(j).getId() + ": ");

	    for (int i = 0; i < schedule.xmlBuild.getDaysNum(); i++) {
		result = result.concat(DAYS[i] + " ");
	    }

	    result = result.concat("\n");

	    for (int k = 0; k < schedule.xmlBuild.getDaysHours(); k++) {

		if (9 + k < 10) {
		    result = result.concat(" ");
		}

		result = result.concat("    ");
		result = result.concat(9 + k + " - " + (9 + k + 1) + "  ");

		for (int i = 0; i < schedule.xmlBuild.getDaysNum(); i++) {

		    if (schedule.getSlots()
			    .elementAt(
				    (i * nr + j)
					    * schedule.xmlBuild.getDaysHours()
					    + k) == null) {
			continue;
		    }

		    switch (schedule.getSlots()
			    .elementAt(
				    (i * nr + j)
					    * schedule.xmlBuild.getDaysHours()
					    + k).size()) {

		    case 0:
			result = result.concat("    ");
			break;

		    case 1:
			CourseClass c = schedule.getSlots().elementAt(
				(i * nr + j) * schedule.xmlBuild.getDaysHours()
					+ k).get(0);
			result = result.concat("  ");
			result = result.concat(new Long(c.getCourse().getId())
				.toString());
			result = result.concat(c.isLabRequired() ? "L" : " ");
			break;

		    default:
			result = result.concat("MUL");
			break;
		    }
		    result = result.concat(" ");
		}
		result = result.concat("\n");
	    }
	}

	return result;
    }
}