package de.fu.scetris;

import java.util.List;

public class BenchmarkResult {

	List<Double> hardConstraintTimeHistory;
	List<Double> softConstraintTimeHistory;

	public BenchmarkResult(List<Double> hardConstraintTimeHistory,
			List<Double> softConstraintTimeHistory) {
		super();
		this.hardConstraintTimeHistory = hardConstraintTimeHistory;
		this.softConstraintTimeHistory = softConstraintTimeHistory;
	}
}
