package fer.hmo.algorithms.utils;

import fer.hmo.state.State;

public interface IAlgorithm {

	public State start();
	
	public void printStatistics();
	
	public String getStopReason();
	
}
