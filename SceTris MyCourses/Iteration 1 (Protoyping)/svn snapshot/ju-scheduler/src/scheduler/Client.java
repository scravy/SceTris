package scheduler;

import java.util.TreeSet;

import scheduler.dao.DBConnection;
import scheduler.strategies.interfaces.Individual;

public class Client {
	public static void main(String... args) {
	    
	    String filename = null;
	    
		Scheduler tmp = new Scheduler();
		int quiet = 0;
		String outputFilename = "";
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s") || args[i].equals("--seed")) {
				tmp = new Scheduler(Long.parseLong(args[++i]));
			}
		}
		
		final Scheduler scheduler = tmp;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-i") || args[i].equals("--intensity")) {
				scheduler.setMutationIntensity(Double.parseDouble(args[++i]));
			} else if (args[i].equals("-p") || args[i].equals("--population")) {
				scheduler.setNumberOfIndividualsPerGeneration(Integer.parseInt(args[++i]));
			} else if (args[i].equals("-b") || args[i].equals("--best")) {
				scheduler.setNumberOfBest(Integer.parseInt(args[++i]));
			} else if (args[i].equals("-d") || args[i].equals("--days")) {
				scheduler.setNumberOfDays(Integer.parseInt(args[++i]));
			} else if (args[i].equals("-h") || args[i].equals("--hours")) {
				scheduler.setNumberOfHours(Integer.parseInt(args[++i]));
			} else if (args[i].equals("-f") || args[i].equals("--factory")) {
				i++;
				if (args[i].equals("ju1")) {
					// ...
				} else if (args[i].equals("ju2")) {
					scheduler.setFactory(new scheduler.strategies.ju2.JuFactory(scheduler));
				} else if (args[i].equals("an1")) {
					scheduler.setFactory(new scheduler.strategies.an1.JuFactory(scheduler));
				}			
			} else if (args[i].equals("-q")) {
				quiet = 1;
			} else if (args[i].equals("-qq")) {
				quiet = 2;
			} else if (args[i].equals("-qqq")) {
				quiet = 3;
			} else if (args[i].equals("-x") || args[i].equals("--xml")) {
				filename = args[++i];
				XMLLoader data = new XMLLoader(scheduler);
				if(!data.load(filename)) {
					System.err.println("Fehler beim Import");
					System.exit(0);
				}
			} else if (args[i].equals("-o") || args[i].equals("--out")) {
				outputFilename = args[++i];
			}
		}
		
		if (filename == null) {
		    new DBLoader(scheduler).loadData();  
		}

		final int q = quiet;
		long start = System.currentTimeMillis();
		scheduler.solve(new Observer() {
			public void done(int generation, Individual bestSchedule) {
				scheduler.setBestSchedule(bestSchedule);
				System.out.println("DONE:");
				if (q < 3) System.out.println(bestSchedule);
				System.out.printf("Generation %7d, Fitness of best: " + bestSchedule.getFitness() + "%n", generation);
				
			}
			public void nextGeneration(int generation, Individual[] population, TreeSet<Individual> best) {
				if (q < 2) System.out.printf("Generation %7d, Fitness of best: " + best.last().getFitness() + "%n", generation);
				if (q < 1) System.out.println(best.last());
			}			
		});
		
		System.out.printf("Seed: %d, Time: %dms%n", scheduler.getSeed(), System.currentTimeMillis() - start);
		System.out.println(scheduler.getFactory().getClass().getCanonicalName() + " used.");
		
		/*
		 * Commit scheduler data to DB
		 * andre@konrad: habs oben herausgenommen, da er sonst bei jeder gen geschrieben hat
		 */
		// DBConnection.commitFinalSchedule(scheduler);
		
		
		if(outputFilename != "") {
			scheduler.bestSchedule.toXML(outputFilename);
		}
	}
}