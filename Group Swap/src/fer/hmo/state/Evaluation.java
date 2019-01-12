package fer.hmo.state;

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
	
}
