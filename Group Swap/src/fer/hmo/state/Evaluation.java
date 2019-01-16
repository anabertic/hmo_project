package fer.hmo.state;

import java.util.List;

import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;

public class Evaluation {

	private State state;
	
	private static final int MIN_SCORE = -100000;

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

	private void calculateUpperLimit() {
		int upperScoreLimit = 0;

		// 1)
		// upper limit of scoreA
		// total sum of all swap weights
		for (Student student : state.getStudents()) {
			for (Integer swapWeight : student.getSwapWeights()) {
				upperScoreLimit += swapWeight;
			}
		}

		// 2)
		// upper limit of scoreB
		// awardActivity[min(nActivities, awardActivity.size())] for every
		// student
		List<Integer> awardActivity = state.getAwardActivity();
		for (Student student : state.getStudents()) {
			int nActivities = student.getActivityIds().size();

			if (nActivities <= 0) {
				continue;
			}

			upperScoreLimit += awardActivity.get(Math.min(nActivities, awardActivity.size()) - 1);
		}

		// 3)
		// upper limit of scoreC
		// nStudents * awardStudent
		upperScoreLimit += state.getStudents().size() * state.getAwardStudent();

		// 4)
		// upper limit of scoreD
		// 0, since it is to be deducted for the total score

		// 5)
		// upper limit of scoreE
		// 0, since it is to be deducted for the total score

		this.maxScore = upperScoreLimit;
	}

	private void calculateStartingScore() {
		

		this.scoreA = this.calculateScoreA();
		this.scoreB = this.calculateScoreB();
		this.scoreC = this.calculateScoreC();
		this.scoreD = this.calculateScoreD();
		this.scoreE = this.calculateScoreE();

		this.currentScore = this.scoreA + this.scoreB + this.scoreC - this.scoreD - this.scoreE;

	}

	private int calculateScoreA() {
		// based on state only

		// always 0 at the start since no request has been fulfilled yet
		return 0;
	}

	private int calculateScoreB() {
		// based on state only

		// always 0 at the start since no request has been fulfilled yet
		return 0;
	}

	private int calculateScoreC() {
		// based on state only

		// always 0 at the start since no request has been fulfilled yet
		return 0;
	}

	private int calculateScoreD() {
		// based on state only

		int scoreD = 0;

		// deduct state.getMinmmaxPen() number of points for every student in
		// every group
		// that contains a number of student below the minimum preferred limit

		int minPenalty = this.state.getMinmmaxPen();
		for (Group group : this.state.getGroups()) {
			int nStudents = group.getStudentsCnt();
			int minPreferred = group.getMinPreferred();

			if (nStudents < minPreferred) {
				scoreD += (minPreferred - nStudents) * minPenalty;
			}
		}

		return scoreD;
	}

	private int calculateScoreE() {
		// based on state only

		int scoreE = 0;

		// deduct state.getMinmmaxPen() number of points for every student in
		// every group
		// that contains a number of student above the maximum preferred limit

		int maxPenalty = this.state.getMinmmaxPen();
		for (Group group : this.state.getGroups()) {
			int nStudents = group.getStudentsCnt();
			int maxPreferred = group.getMaxPreferred();

			if (nStudents > maxPreferred) {
				scoreD += (nStudents - maxPreferred) * maxPenalty;
			}
		}

		return scoreE;
	}

	// ---- CALLED ON EVERY REQUEST TAKEN INTO CONSIDERATION ----
	
	public int calculateCandidateStateScore(Request request) {
		
		if (!state.isRequestValid(request)) 
			return MIN_SCORE;
		if (!state.isStateValid()){
			return MIN_SCORE;
		}
		return this.calculateCandidateScoreA(request) + this.calculateCandidateScoreB(request)
				+ this.calculateCandidateScoreC(request) - this.calculateCandidateScoreD(request)
				- this.calculateCandidateScoreE(request);

	}

	public int applyRequest(Request request) {
		if (!state.isRequestValid(request)){
			this.currentScore = MIN_SCORE;
		}
		if (!state.isStateValid()){
			return MIN_SCORE;
		}

		else {
			this.scoreA = this.calculateCandidateScoreA(request);
			this.scoreB = this.calculateCandidateScoreB(request);
			this.scoreC = this.calculateCandidateScoreC(request);
			this.scoreD = this.calculateCandidateScoreD(request);
			this.scoreE = this.calculateCandidateScoreE(request);

			this.currentScore = this.scoreA + this.scoreB + this.scoreC - this.scoreD - this.scoreE;
		}
		return this.currentScore;

	}

	private int calculateCandidateScoreA(Request request) {
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

	private int calculateCandidateScoreB(Request request) {
		int scoreB = this.scoreB;

		// get number of already satisfied requests for a given student
		Student student = request.getStudent();
		int nSatisfied = student.getSatisfiedRequests().keySet().size();

		// find if the new request satisfies another activity
		int activityId = request.getActivityId();
		boolean alreadySatisfiedForThatActivity = student.existsSatisfiedRequestForActivity(activityId);

		// if the request is for an already satisfied activity do nothing, for
		// points were already awarded
		if (alreadySatisfiedForThatActivity) {
			return scoreB;
		}
		// otherwise, compare nSatisfied + 1 with the award-activity list
		List<Integer> awardActivity = state.getAwardActivity();
		int awardActivityLength = awardActivity.size();
		/*
		 * [1, 2, 4]
		 * 
		 * 0 -> 1 point 1 -> (2 - 1) = 1 point 2 -> (4 - 2) = 2 points 3 -> 0
		 * points
		 */
		if (nSatisfied == 0 && awardActivityLength > 0) {
			// "0 -> 1", if there were 0 satisfied, gain awardActivity[0] points
			return scoreB + awardActivity.get(0);
		}
		if (nSatisfied >= awardActivityLength) {
			// "3 -> 0", if there were 3 satisfied, gain 0 points since all
			// points were already awarded
			return scoreB;
		}
		// if no edge cases are in play, calculate and add points
		return scoreB + awardActivity.get(nSatisfied) - awardActivity.get(nSatisfied - 1);
	}

	private int calculateCandidateScoreC(Request request) {
		int scoreC = this.scoreC;

		Student student = request.getStudent();
		int activityId = request.getActivityId();

		// get award student bonus that is used when all activity swap requests
		// of a student are satisfied
		int awardStudent = state.getAwardStudent();

		// find how many activity student has
		int nActivities = student.getActivityIds().size();

		// find how many activities are satisfied
		int nSatisfiedActivities = student.getSatisfiedRequests().size();

		// if number of satisfied requests is already the same to the number of
		// activities, points
		// were already awarded
		if (nActivities == nSatisfiedActivities) {
			return scoreC;
		}

		// find out if there is only one activity missing for bonus points
		if (nActivities == nSatisfiedActivities + 1) {
			// find out if the activity is already satisfied (that the request
			// is trying to satisfy)
			boolean alreadySatisfiedForThatActivity = student.existsSatisfiedRequestForActivity(activityId);

			if (alreadySatisfiedForThatActivity) {
				return scoreC;
			} else {
				return scoreC + awardStudent;
			}
		}

		// if there is more than one missing request to satisfy all student
		// activities, award no bonus points
		return scoreC;
	}

	private int calculateCandidateScoreD(Request request) {
		int scoreD = this.scoreD;

		Group currentGroup = request.getCurrentGroup();

		if (currentGroup.getStudentsCnt() < currentGroup.getMinPreferred()) {
			if (currentGroup.getStudentsCnt() + 1 >= currentGroup.getMinPreferred())
				;
			{
				scoreD -= state.getMinmmaxPen();
			}
		}

		return scoreD;
	}

	private int calculateCandidateScoreE(Request request) {
		int scoreE = this.scoreE;

		Group currentGroup = request.getCurrentGroup();

		if (currentGroup.getStudentsCnt() > currentGroup.getMinPreferred()) {
			if (currentGroup.getStudentsCnt() - 1 <= currentGroup.getMaxPreferred())
				;
			{
				scoreE -= state.getMinmmaxPen();
			}
		}

		return scoreE;
	}

	// ---- GETTERS AND SETTERS ----

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getScoreA() {
		return scoreA;
	}

	public void setScoreA(int scoreA) {
		this.scoreA = scoreA;
	}

	public int getScoreB() {
		return scoreB;
	}

	public void setScoreB(int scoreB) {
		this.scoreB = scoreB;
	}

	public int getScoreC() {
		return scoreC;
	}

	public void setScoreC(int scoreC) {
		this.scoreC = scoreC;
	}

	public int getScoreD() {
		return scoreD;
	}

	public void setScoreD(int scoreD) {
		this.scoreD = scoreD;
	}

	public int getScoreE() {
		return scoreE;
	}

	public void setScoreE(int scoreE) {
		this.scoreE = scoreE;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

}
