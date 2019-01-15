package fer.hmo.algorithms.random;

import java.util.Collections;
import java.util.Random;

import fer.hmo.models.Request;
import fer.hmo.state.State;

public class RandomSearch {

	private State state;

	
	public RandomSearch(State state){
		this.state = state;
	}
	
	public void search(){
		//Collections.shuffle(this.state.getRequests());
		for(Request request:this.state.getRequests()){
			System.out.println(state.evaluateRequest(request));
		}
	}
	
	
	
	
}
