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
		this.calculateStartingScore();
	}
	
	// ---- CALLED ONLY ONCE ----
	
	public void calculateUpperLimit() {
		// full logic goes here
		this.maxScore = 0;
	}
	
	public void calculateStartingScore() {
		
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
	
	public int calculateScoreA() {
		// based on state only
		
		return 0;
	}
	
	public int calculateScoreB() {
		// based on state only
		
		return 0;
	}
	
	public int calculateScoreC() {
		// based on state only
		
		return 0;
	}
	
	public int calculateScoreD() {
		// based on state only
		
		return 0;
	}
	
	public int calculateScoreE() {
		// based on state only
		
		return 0;
	}
	
	// ---- CALLED ON EVERY REQUEST TAKEN INTO CONSIDERATION ----
	
	public int calculateCandidateStateScore(Request request) {

		return 0;
	}
	
	public int calculateCandidateScoreA(Request request) {
		int scoreA = this.scoreA;
		
		
		
		return scoreA;
	}
	
	public int calculateCandidateScoreB(Request request) {
		
		return 0;
	}
	
	public int calculateCandidateScoreC(Request request) {
		
		return 0;
	}
	
	public int calculateCandidateScoreD(Request request) {
		
		return 0;
	}
	
	public int calculateCandidateScoreE(Request request) {
		
		return 0;
	}
	
	// ---- GETTERS AND SETTERS ----
	
}
