package fer.hmo.state;

import java.util.ArrayList;
import java.util.List;

import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;

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
		
		// get swap weight for (studentId,activityId) pair
		Student student = request.getStudent();
		int activityId = request.getActivityId();
		int swapValue = this.state.getSwapByStudentActivity(student, activityId);
		
		// find if student has satisfied requests for that activityId
		boolean alreadySatisfiedForThatActivity = student.existsSatisfiedRequestForActivity(activityId);
		
		// if it doesn't, increment scoreA by swap_value
		// otherwise do nothing, score is already applied for that category
		if (!alreadySatisfiedForThatActivity) {
			scoreA += swapValue;
		}
		
		return scoreA;
	}
	
	public int calculateCandidateScoreB(Request request) {
		int scoreB = this.scoreB;
		
		// get number of already satisfied requests for a given student
		Student student = request.getStudent();
		int nSatisfied = student.getSatisfiedRequests().keySet().size();
		
		// find if the new request satisfies another activity
		int activityId = request.getActivityId();
		boolean alreadySatisfiedForThatActivity = student.existsSatisfiedRequestForActivity(activityId);
		
		// if the request is for an already satisfied activity do nothing, for points were already awarded
		if (alreadySatisfiedForThatActivity) {
			return scoreB;
		}
		// otherwise, compare nSatisfied + 1 with the award-activity list
		List<Integer> awardActivity = state.getAwardActivity();
		int awardActivityLength = awardActivity.size();
		/*
		[1, 2, 4]

		0 -> 1 point
		1 -> (2 - 1) = 1 point
		2 -> (4 - 2) = 2 points
		3 -> 0 points
		*/
		if (nSatisfied == 0 && awardActivityLength > 0) {
			// "0 -> 1", if there were 0 satisfied, gain awardActivity[0] points
			return scoreB + awardActivity.get(0);
		}
		if (nSatisfied >= awardActivityLength) {
			// "3 -> 0", if there were 3 satisfied, gain 0 points since all points were already awarded
			return scoreB;
		}
		// if no edge cases are in play, calculate and add points
		return scoreB + awardActivity.get(nSatisfied) - awardActivity.get(nSatisfied - 1);
	}
	
	public int calculateCandidateScoreC(Request request) {
		int scoreC = this.scoreC;
		
		Student student = request.getStudent();
		int activityId = request.getActivityId();
		
		// get award student bonus that is used when all activity swap requests of a student are satisfied
		int awardStudent = state.getAwardStudent();
		
		// find how many activity student has
		int nActivities = student.getActivityIds().size();
		
		// find how many activities are satisfied
		int nSatisfiedActivities = student.getSatisfiedRequests().size();
		
		// if number of satisfied requests is already the same to the number of activities, points
		// were already awarded
		if (nActivities == nSatisfiedActivities) {
			return scoreC;
		}
		
		// find out if there is only one activity missing for bonus points
		if (nActivities == nSatisfiedActivities + 1) {
			// find out if the activity is already satisfied (that the request is trying to satisfy)
			boolean alreadySatisfiedForThatActivity = student.existsSatisfiedRequestForActivity(activityId);
			
			if (alreadySatisfiedForThatActivity) {
				return scoreC;
			} else {
				return scoreC + awardStudent;
			}
		}
		
		// if there is more than one missing request to satisfy all student activities, award no bonus points
		return scoreC;
	}
	
	public int calculateCandidateScoreD(Request request) {
		int scoreD = this.scoreD;
		
		Group currentGroup = request.getCurrentGroup();
		
		if (currentGroup.getStudentsCnt() < currentGroup.getMinPreferred()) {
			if (currentGroup.getStudentsCnt() + 1 >= currentGroup.getMinPreferred()); {
				scoreD -= state.getMinmmaxPen();
			}
		}
		
		return scoreD;
	}
	
	public int calculateCandidateScoreE(Request request) {
		int scoreE = this.scoreE;
		
		Group currentGroup = request.getCurrentGroup();
		
		if (currentGroup.getStudentsCnt() > currentGroup.getMinPreferred()) {
			if (currentGroup.getStudentsCnt() - 1 <= currentGroup.getMaxPreferred()); {
				scoreE -= state.getMinmmaxPen();
			}
		}
		
		return scoreE;
	}
	
	// ---- GETTERS AND SETTERS ----
	
}
