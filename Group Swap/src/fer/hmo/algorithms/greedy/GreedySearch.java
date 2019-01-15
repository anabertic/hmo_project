package fer.hmo.algorithms.greedy;

import java.util.Collections;
import java.util.Random;

import fer.hmo.models.Request;
import fer.hmo.models.Student;
import fer.hmo.state.State;

public class GreedySearch {

	private State state;
	private int maxIterations;

	public GreedySearch(State state, int maxIterations) {
		this.state = state;
		this.maxIterations = maxIterations;
	}

	public void search() {

		int maxEvaluation;
		int evaluation;
		Request maxRequest = null;

		for (int i = 0; i < maxIterations; i++) {
			maxEvaluation = Integer.MIN_VALUE;
			for (Request request : this.state.getNotSatisfiedRequests()) {
				evaluation = state.evaluateRequest(request);
				if (evaluation > maxEvaluation) {
					maxEvaluation = evaluation;
					maxRequest = request;
				}
			}
			state.applyRequest(maxRequest);

			if (state.getEvaluation().getCurrentScore() == state.getEvaluation().getMaxScore()) {
				break;
			}
			if (i % 100 == 0) {
				System.out.print("Round " + i + ": ");
				System.out.println("maxScore " + maxEvaluation + " score: " + state.getEvaluation().getCurrentScore());
			}
		}

	}

}
