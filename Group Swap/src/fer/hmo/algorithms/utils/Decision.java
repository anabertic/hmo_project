package fer.hmo.algorithms.utils;

import fer.hmo.models.Request;

public class Decision implements Comparable {

	private Request request;
	private int score;
	
	public Decision(Request request, int score) {
		this.request = request;
		this.score = score;
	}
	
	// ---- GETTERS AND SETTERS ----

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	// ---- COMPARE TO ----

	@Override
	public int compareTo(Object o) {
		Decision otherDecision = (Decision) o;
		
		// negative if this < that
		// 0 if equal
		// positive if this > that
		
		if (this.score < otherDecision.getScore()) {
			return -1;
		}
		if (this.score == otherDecision.getScore()) {
			return 0;
		}
		if (this.score > otherDecision.getScore()) {
			return 1;
		}
		
		// dummy return
		return 0;
	}
	
	
}
