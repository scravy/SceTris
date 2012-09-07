package de.fu.scetris.scheduler.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.controller.SchedulerController;
import de.fu.scetris.scheduler.controller.SchedulerTask;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.GeneticFactory;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;
import de.fu.scetris.util.TestDataGenerator;

/**
 * Hummingbird is a simple benchmark running the Scheduler with different scales
 * of data, the use of different seeds and checking their total execution time.
 * 
 * @author Konrad Reiche
 * 
 */
public class Hummingbird {

	static RelationManager relationManager;
	static Configuration configuration;
	static AlgorithmConfiguration algorithmConfiguration;
	static Algorithm algorithm;

	static final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH-mm";

	public static void main(String args[]) throws InterruptedException,
			DatabaseException, IOException, SAXException,
			ParserConfigurationException {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		FileOutputStream log = new FileOutputStream(
				"D:/Documents/My Dropbox/Public/hummingbird_log("
						+ sdf.format(Calendar.getInstance().getTime())
						+ ").txt");

		long seeds[] = { 24, 4 };
		float mutationSizes[] = { 0.3f };
		float mutationProbabilities[] = { 0.03f, 0.1f };
		float crossoverProbabilities[] = { 0.85f };
		float crossoverPoints[] = { 0.5f };
		int trackBests[] = { 5, 25, 50 };
		int replacebyGenerations[] = { 8 };
		int populationSizes[] = { 100 };

		runGeneticAlgorithm(seeds, log, mutationSizes, mutationProbabilities,
				crossoverProbabilities, crossoverPoints, trackBests,
				replacebyGenerations, populationSizes);

		log.close();

	}

	public static void runGeneticAlgorithm(long seed[], FileOutputStream log,
			float mutationSize[], float mutationProbability[],
			float crossoverProbability[], float crossoverPoints[],
			int trackBest[], int replaceByGeneration[], int populationSize[])
			throws DatabaseException, ParserConfigurationException,
			IOException, SAXException, InterruptedException {

		for (int i = 0; i < seed.length; ++i) {
			for (int j = 0; j < mutationSize.length; ++j) {
				for (int k = 0; k < mutationProbability.length; ++k) {
					for (int l = 0; l < crossoverProbability.length; ++l) {
						for (int m = 0; m < crossoverPoints.length; ++m) {
							for (int n = 0; n < trackBest.length; ++n) {
								for (int o = 0; o < replaceByGeneration.length; ++o) {
									for (int p = 0; p < populationSize.length; ++p) {

										if (trackBest[n] >= populationSize[p]) {
											continue;
										}

										TestDataGenerator testDataGenerator = new TestDataGenerator(
												true);
										TestDataGenerator.main(null);
										relationManager = testDataGenerator
												.getRelationManager();
										SchedulerController schedulerController = SchedulerController
												.getInstance(relationManager);
										Program program = ((List<Program>) relationManager
												.getProgram()).get(0);
										Configuration configuration = schedulerController
												.initializeConfiguration(program);

										AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(
												mutationSize[j],
												mutationProbability[k],
												crossoverProbability[l],
												crossoverPoints[m],
												trackBest[n],
												replaceByGeneration[o],
												populationSize[p], 1.0);

										algorithm = GeneticFactory
												.getInstance().createAlgorithm(
														configuration,
														algorithmConfiguration,
														seed[i]);

										SchedulerTask schedulerTask = new SchedulerTask(
												relationManager, algorithm,
												program);
										Thread thread = new Thread(
												schedulerTask);
										long start = System.currentTimeMillis();
										thread.start();
										while (thread.isAlive()) {

											Thread.sleep(10000);
											System.out
													.println(schedulerTask
															.getAlgorithm()
															.getBestSchedule()
															.getHardFitness()
															+ " ["
															+ calculateRunningTime(start)
															+ "] Generation: "
															+ algorithm
																	.getGenerationNumber());

										}

										String logEntry = "Genetic Algorithm (seed: "
												+ seed[i]
												+ ") configured with ("
												+ mutationSize[j]
												+ ","
												+ mutationProbability[k]
												+ ","
												+ crossoverProbability[l]
												+ ","
												+ crossoverPoints[m]
												+ ","
												+ trackBest[n]
												+ ","
												+ replaceByGeneration[o]
												+ ","
												+ populationSize[p]
												+ ") terminated after "
												+ calculateRunningTime(start)
												+ " ("
												+ algorithm
														.getGenerationNumber()
												+ ")"
												+ "\n"
												+ "number of courses "
												+ configuration.courseList
														.size()
												+ ", number of time slots "
												+ configuration.timeSlotList
														.size()
												+ ", number of rooms "
												+ configuration.roomList.size()
												+ "\n\n";

										log.write(logEntry.getBytes());

									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static String calculateRunningTime(long start) {

		long current = (System.currentTimeMillis() - start);

		int seconds = (int) (current / 1000);
		int minutes = (seconds / 60);
		int hours = minutes / 60;

		return hours + "h " + minutes % 60 + "min " + seconds % 60 + "s";
	}
}
