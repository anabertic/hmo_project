package fer.hmo.algorithms.random;

import fer.hmo.models.Request;
import fer.hmo.state.State;

public class RandomSearch {

	private State state;
	
	public RandomSearch(State state){
		this.state = state;
	}
	
	public void search(){
		for(Request request:this.state.getRequests()){
		//Request r = this.state.getRequests().get(0);
		request.apply();
		}
	}
	
	
	
	
}
