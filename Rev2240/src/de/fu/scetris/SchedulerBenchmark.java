package de.fu.scetris;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.Algorithm;
import de.fu.scetris.scheduler.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.scheduler.manager.SchedulerTask;
import de.fu.weave.orm.DatabaseException;

/**
 * Command-line utility for benchmarking the scheduler.
 * <p>
 * A simple benchmark running the Scheduler with different scales of data, the
 * use of different seeds and checking their total execution time.
 */
@Author("Konrad Reiche")
public class SchedulerBenchmark {

	static RelationManager relationManager;
	static Configuration configuration;
	static AlgorithmConfiguration algorithmConfiguration;
	static Algorithm algorithm;

	static final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH-mm";

	/**
	 * 
	 * @param start
	 * @return
	 */
	public static String calculateRunningTime(final long start) {

		long current = (System.currentTimeMillis() - start);

		int seconds = (int) (current / 1000);
		int minutes = (seconds / 60);
		int hours = minutes / 60;

		return hours + "h " + minutes % 60 + "min " + seconds % 60 + "s";
	}

	/**
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws DatabaseException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static void main(final String args[]) throws InterruptedException,
			DatabaseException, IOException, SAXException,
			ParserConfigurationException {

		FileOutputStream log = new FileOutputStream("benchmark/benchmark.dat");

		long seeds[] = { 1 };
		float mutationSizes[] = { 0.3f };
		float mutationProbabilities[] = { 0.3f };
		float crossoverProbabilities[] = { 0.85f };
		float crossoverPoints[] = { 0.5f };
		int trackBests[] = { 25 };
		int replacebyGenerations[] = { 8 };
		int populationSizes[] = { 100 };

		Thread.sleep(10000);

		runGeneticAlgorithm(seeds, log, mutationSizes, mutationProbabilities,
				crossoverProbabilities, crossoverPoints, trackBests,
				replacebyGenerations, populationSizes);

		log.close();

	}

	public static void runGeneticAlgorithm(final long seed[],
										   FileOutputStream log, final float mutationSize[],
										   final float mutationProbability[],
										   final float crossoverProbability[],
										   final float crossoverPoints[],
										   final int trackBest[], final int replaceByGeneration[],
										   final int populationSize[]) throws DatabaseException,
			ParserConfigurationException, IOException, SAXException,
			InterruptedException {

		TestDataGenerator testDataGenerator = new TestDataGenerator(new Random(5), true);
		TestDataGenerator.main(new String[] { "-seed 5" });
		relationManager = testDataGenerator.getRelationManager();
		SchedulerManager schedulerManager = SchedulerManager
				.getInstance(relationManager);

		List<BenchmarkResult> results = new ArrayList<BenchmarkResult>();

		List<Program> programList = relationManager.getProgram();
		Program program = programList.get(0);
		Configuration configuration = schedulerManager
				.initializeConfiguration(program);

		for (int i = 0; i < seed.length; ++i)
			for (int j = 0; j < mutationSize.length; ++j)
				for (int k = 0; k < mutationProbability.length; ++k)
					for (int l = 0; l < crossoverProbability.length; ++l)
						for (int m = 0; m < crossoverPoints.length; ++m)
							for (int n = 0; n < trackBest.length; ++n)
								for (int o = 0; o < replaceByGeneration.length; ++o)
									for (int p = 0; p < populationSize.length; ++p) {

										if (trackBest[n] >= populationSize[p]) {
											continue;
										}

										List<Double> hardConstraintTimeHistory = new ArrayList<Double>();
										List<Double> softConstraintTimeHistory = new ArrayList<Double>();

										results.add(new BenchmarkResult(
												hardConstraintTimeHistory,
												softConstraintTimeHistory));

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

										relationManager.enableQueryCache();

										SchedulerTask schedulerTask = new SchedulerTask(
												relationManager, algorithm,
												program, false);
										Thread thread = new Thread(
												schedulerTask);
										long start = System.currentTimeMillis();
										thread.start();

										int maximumEntriesSoFar = 0;
										int numberOfTenSeconds = 0;

										while (thread.isAlive()) {

											Thread.sleep(10000);
											++numberOfTenSeconds;

											if (numberOfTenSeconds == 1800) {
												thread.interrupt();
											}

											++maximumEntriesSoFar;

											ScheduleScore score = schedulerTask
													.getAlgorithm()
													.getBestSchedule()
													.getScore();

											hardConstraintTimeHistory.add(score
													.getHardFitness());

											softConstraintTimeHistory.add(score
													.getSoftFitness());

											log = new FileOutputStream(
													"benchmark/benchmark.dat");

											saveDataResults(results,
													maximumEntriesSoFar, log);

											System.out
													.println(schedulerTask
															.getAlgorithm()
															.getBestSchedule()
															.getScore()
															.getHardFitness()
															+ " "
															+ schedulerTask
																	.getAlgorithm()
																	.getBestSchedule()
																	.getScore()
																	.getSoftFitness()
															+ " ["
															+ calculateRunningTime(start)
															+ "] Generation: "
															+ algorithm
																	.getGenerationNumber());

											System.out.println(schedulerTask
													.getAlgorithm()
													.getBestSchedule()
													.getScore());
										}

										relationManager.disableQueryCache();

									}
	}

	public static void saveDataResults(final List<BenchmarkResult> resultList,
									   final int minimumEntries, final FileOutputStream out)
			throws IOException {

		// starting at 00:00:00
		long millis = 82800000;
		Date time = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("#Time\t\t\tHard constraint\t\t\tSoft constraint\n");

		for (int i = 0; i < minimumEntries; ++i) {
			stringBuilder.append(sdf.format(time));
			for (BenchmarkResult result : resultList) {

				stringBuilder.append("\t\t");
				if (result.hardConstraintTimeHistory.size() == i) {
					stringBuilder.append("\t\t");
				} else {
					stringBuilder.append(String.format(Locale.ENGLISH, "%.16f",
							result.hardConstraintTimeHistory.get(i)));
				}

				stringBuilder.append("\t\t");
				if (result.softConstraintTimeHistory.size() == i) {
					stringBuilder.append("\t\t");
				} else {
					stringBuilder.append(String.format(Locale.ENGLISH, "%.16f",
							result.softConstraintTimeHistory.get(i)));
				}

			}

			stringBuilder.append("\n");
			millis += 10000;
			time.setTime(millis);
		}

		out.write(stringBuilder.toString().getBytes());
		out.close();
	}

}
