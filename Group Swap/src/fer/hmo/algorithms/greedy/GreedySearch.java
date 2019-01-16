package fer.hmo.algorithms.greedy;

import java.util.Collections;
import java.util.Random;

import fer.hmo.IAlgorithm;
import fer.hmo.models.Request;
import fer.hmo.models.Student;
import fer.hmo.state.State;

public class GreedySearch implements IAlgorithm {

	private State state;
	private int maxIterations;

	public GreedySearch(State state, int maxIterations) {
		this.state = state;
		this.maxIterations = maxIterations;
	}

	public void help() {
		int count=0;
		for (Request request : this.state.getNotSatisfiedRequests()) {
			if (this.state.isRequestValid(request)){
				count+=1;
			}
		}
		System.out.println(this.state.getNotSatisfiedRequests().size());
		System.out.println("cunt"+count);
	}

	public void search() {

		int maxEvaluation;
		int evaluation;
		Request maxRequest = null;

		for (int i = 0; i < maxIterations; i++) {
			maxEvaluation = Integer.MIN_VALUE;
			for (Request request : this.state.getNotSatisfiedRequests()) {
				
				evaluation = state.evaluateRequest(request);
				//System.out.print(request);
				//System.out.println("-> "+evaluation);
				if (evaluation > maxEvaluation) {
					maxEvaluation = evaluation;
					maxRequest = request;
				}
			}
			//System.out.println("chosen "+maxRequest+" -> "+maxEvaluation);

			//System.out.println(this.state);
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

	@Override
	public State start() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printStatistics() {
		// TODO Auto-generated method stub
		
	}

}
