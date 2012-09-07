package scheduler;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import scheduler.data.Lecturer;
import scheduler.strategies.interfaces.Individual;


public class Benchmark {
	public static void main(String... args) {
		final ArrayList<Integer> results = new ArrayList<Integer>();
		int MAX = 20;
		Random rand = new Random();
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s") || args[i].equals("--seed")) {
				rand = new Random(Long.parseLong(args[++i]));
			}
			if (args[i].equals("-m") || args[i].equals("--max")) {
				MAX = Integer.parseInt(args[++i]);
			}
		}
		
		for (int N = 1; N <= MAX; N++) {
			Scheduler scheduler = new Scheduler(rand.nextLong());
			
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
					}
				}
			}
			
			Lecturer schweppe = scheduler.createLecturer("Schweppe");
			Lecturer esponda = scheduler.createLecturer("Esponda");
			Lecturer loehr = scheduler.createLecturer("Loehr");
			Lecturer kyas = scheduler.createLecturer("Kyas");
			Lecturer liers = scheduler.createLecturer("Liers");
			Lecturer kriegel = scheduler.createLecturer("Kriegel");
			Lecturer prechelt = scheduler.createLecturer("Prechelt");
			
			scheduler.createCourse("Alp1", schweppe, 100, 1);
			scheduler.createCourse("Alp2", esponda, 100, 1);
			scheduler.createCourse("Alp3", loehr, 100, 1);
			scheduler.createCourse("Alp4", kyas, 100, 1);
			scheduler.createCourse("Alp5", loehr, 100, 1);
			
			scheduler.createCourse("Ti1", liers, 100, 1);
			scheduler.createCourse("Ti2", esponda, 100, 1);
			scheduler.createCourse("Ti3", kyas, 100, 1);
			scheduler.createCourse("Ti4", liers, 100, 4);
			
			scheduler.createCourse("Mafi1", kriegel, 100, 2);
			scheduler.createCourse("Mafi2", kriegel, 100, 2);
			scheduler.createCourse("Mafi3", kriegel, 100, 2);
			
			scheduler.createCourse("GTI", kyas, 100, 1);
			scheduler.createCourse("DBS", schweppe, 100, 1);
			scheduler.createCourse("SWT", prechelt, 100, 1);
			scheduler.createCourse("AWS", prechelt, 100, 1);
			scheduler.createCourse("SWP1", prechelt, 25, 1);
			scheduler.createCourse("SWP2", schweppe, 25, 1);
			scheduler.createCourse("Erlang", kyas, 40, 3);
			scheduler.createCourse("SWT+", prechelt, 40, 1);
			
			scheduler.createRoom("Taku9 Großer HS", 200);
			scheduler.createRoom("Mathe HS", 100);
			scheduler.createRoom("Seminarraum", 40);
			
			long start = System.currentTimeMillis();
			scheduler.solve(new Observer() {
				public void done(int generation, Individual bestSchedule) {
					System.out.print(".");
					results.add(generation);
				}
				public void nextGeneration(int generation, Individual[] population, TreeSet<Individual> best) {
					
				}			
			});
			
			if (N == MAX) {
				System.out.println();
				System.out.println(scheduler.getFactory().getClass().getCanonicalName() + " used.");
			}
		}
		long sum = 0;
		for (Integer i : results) {
			sum += i;
		}
		System.out.printf("Average number of generations: %d %n", sum / MAX);
	}
}