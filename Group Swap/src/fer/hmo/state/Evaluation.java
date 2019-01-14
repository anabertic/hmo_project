package fer.hmo.state;

import fer.hmo.models.Request;

public class Evaluation {
	
	private State state;
	
	private int scoreA;
	private int scoreB;
	private int scoreC;
	private int scoreD;
	private int scoreE;
	
	private int currentScore;
	private int maxScore;
	
	public Evaluation(State state) {
		this.state = state;
		
		this.calculateUpperLimit();
		this.calculateCurrentScore();
	}
	
	public void calculateUpperLimit() {
		// full logic goes here
		this.maxScore = 0;
	}
	
	public void calculateCurrentScore() {
		this.scoreA = this.calculateScoreA();
		this.scoreB = this.calculateScoreB();
		this.scoreC = this.calculateScoreC();
		this.scoreD = this.calculateScoreD();
		this.scoreE = this.calculateScoreE();
		
		this.currentScore = this.scoreA
				+ this.scoreB
				+ this.scoreC
				- this.scoreD
				- this.scoreE;
		
	}
	
	public void stateUpdated() {
		this.calculateCurrentScore();
	}
	
	public int calculateCandidateStateScore(Request request) {

		return this.calculateScoreA()
				+ this.calculateScoreB()
				+ this.calculateScoreC()
				- this.calculateScoreD()
				- this.calculateScoreE();
		
	}
	
	public int calculateScoreA() {
		
		return 0;
	}
	
	public int calculateScoreB() {
		
		return 0;
	}
	
	public int calculateScoreC() {
		
		return 0;
	}
	
	public int calculateScoreD() {
		
		return 0;
	}
	
	public int calculateScoreE() {
		
		return 0;
	}
	
}
