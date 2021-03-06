package fer.hmo.algorithms.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fer.hmo.algorithms.utils.Decision;
import fer.hmo.algorithms.utils.IAlgorithm;
import fer.hmo.models.Request;
import fer.hmo.models.Student;
import fer.hmo.state.State;

public class GreedySearch implements IAlgorithm {

	private State state;
	private int maxIterations;
	private long timeout;
	private long startingTime;
	private long endTime;
	
	private int maxScore;	// if the algorithm converges to global optimum
	private int currentScore;
	private int maxEvalScore;
	private Request maxEvalRequest;
	
	private String stopReason;	// reason for stopping
							// for greedy search, it is either:
							// - time ran out
							// - exceeded maximum number of iterations
							// - convergence to global optimum
							// - no possible states to choose from (only if algorithm is currently in a possible state)
							// - error

	public GreedySearch(State state, int maxIterations) {
		this.state = state;
		this.maxIterations = maxIterations;
		this.timeout = state.getTimeout() * 1000;
		this.startingTime = System.currentTimeMillis();
		this.endTime = this.startingTime + this.timeout;
		
		this.maxScore = state.getMaxScore();
		this.currentScore = state.getScore();
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
		// init time and evaluation score
		long currentTime = System.currentTimeMillis();
		this.maxEvalScore = this.currentScore;
		
		// check if the starting state is a valid one
		boolean isStateValid = this.state.getIsStateValid();
		
		Decision bestDecision = null;
		
		for (int i = 1; i <= this.maxIterations; i++) {
			currentTime = System.currentTimeMillis();
			if (currentTime > this.endTime) {
				this.stopReason = "timeout";
				return this.state;
			}
			
			bestDecision = this.evaluateOptions();
			if (bestDecision == null) {
				this.stopReason = "no valid decisions";
				return this.state;
			}
			// apply decision
			this.state.applyRequest(bestDecision.getRequest());
			this.currentScore = bestDecision.getScore();
			
			if (i % 100 == 0 || i == 1) {
				System.out.println("Step " + i + ": " + "score: " + this.currentScore);
			}
		}
		
		this.stopReason = "iterations";
		return this.state;
	}
	
	private Decision evaluateOptions() {
		List<Decision> decisions = new ArrayList<>();
		
		this.maxEvalScore = this.currentScore;
		this.maxEvalRequest = null;
		for (Request request : this.state.getUnsatisfiedRequests()) {
			int score = this.state.evaluateRequest(request);
			
			if (score >= this.maxEvalScore) {
				this.maxEvalScore = score;
				this.maxEvalRequest = request;
			}
			
			// this line is if one wishes to save all decisions in order to make a more complex decision
			//decisions.add(new Decision(request, score));
		}
		
		if (this.maxEvalRequest == null) {
			return null;
		}
		return new Decision(this.maxEvalRequest, this.maxEvalScore);
	}

	@Override
	public void printStatistics() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStopReason() {
		return this.stopReason;
	}

}
