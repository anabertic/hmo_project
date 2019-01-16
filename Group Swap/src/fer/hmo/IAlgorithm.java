package fer.hmo;

import fer.hmo.state.State;

public interface IAlgorithm {

	public State start();
	
	public void printStatistics();
	
}
