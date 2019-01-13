package fer.hmo.state;

import fer.hmo.models.Request;

public class Evaluation {

	public static int scoreA(State state) {
		
		return 0;
	}
	
	public static int scoreB(State state) {
		
		return 0;
	}
	
	public static int scoreC(State state) {
		
		return 0;
	}
	
	public static int scoreD(State state) {
		
		return 0;
	}
	
	public static int scoreE(State state) {
		
		return 0;
	}
	
	public static int maxScore(int instance) {
		State state = new State(instance);
		
		return Evaluation.scoreA(state)
				+ Evaluation.scoreB(state)
				+ Evaluation.scoreC(state)
				- Evaluation.scoreD(state)
				- Evaluation.scoreE(state);
		
	}
	
	// NOT SURE ABOUT THIS
	
	public static void updateScore(State state,Request request){
		updateScoreA(state,request);
		//updateScoreB(state,request);
		//updateScoreC(state,request);
		//updateScoreD(state,request);
		//updateScoreE(state,request);
		
	}
	
	public static void updateScoreA(State state, Request request){
		int index = state.getStudents().indexOf(state.findStudentById(request.getStudent().getStudentId()));
		int swapWeight = state.getStudents().get(index).getSwapWeights().get(index);
		state.setScoreA(state.getScoreA()+swapWeight);
	}
	
	public static void updateScoreD(State state, Request request){
	
	}
	
	
}
