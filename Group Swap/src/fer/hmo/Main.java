package fer.hmo;

import java.io.IOException;

import fer.hmo.algorithms.greedy.GreedySearch;
import fer.hmo.algorithms.utils.IAlgorithm;
import fer.hmo.state.State;

public class Main {

	public static void main(String[] args) {
		// parse arguments
		ParsedArguments parsedArguments = ArgumentsParser.parseArguments(args);
		System.out.println("Parsed arguments:");
		System.out.println(parsedArguments);
		System.out.println();
		
		// init State object to be used as a starting object for all algorithms
		State state = null;
		try {
			state = new State(parsedArguments, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// print out starting State
		System.out.println("Starting state");
		System.out.println("possible: " + state.isStateValid());
		System.out.println("Upper score limit = " + state.getMaxScore());
		int startingScore = state.getScore();
		System.out.println("Starting score = " + startingScore);
		System.out.println("Number of students: " + state.getStudents().size());
		System.out.println("Number of groups: " + state.getGroups().size());
		System.out.println("Number of requests: " + state.getRequests().size());
		
		// init Algorithm of choice
		IAlgorithm algorithm = null;
		
		/** Algorithm II
		 * 
		 * A simple greedy search of possible states. If caught in an impossible state from where every request takes
		 * the algorithm into another impossible state, it keeps trying them out until hopefully it stumbles upon
		 * a possible state.
		 * 
		 * Stopping criterion:
		 * If it reaches local optimum, the algorithm stops.
		 * If a number of iterations or time limit is exceeded the algorithm stops.
		 * If every request leads algorithm from possible to an impossible state, the algorithm stops.
		 */
		algorithm = new GreedySearch(state, 25001);	// -1 as not to limit a number of iterations
		
		
		// start the algorithm
		State finalState = algorithm.start();
		
		// print final state results to screen
		System.out.println("");
		System.out.println("Final state:");
		System.out.println("possible: " + finalState.isStateValid());
		System.out.println("Starting score = " + startingScore);
		System.out.println("Final score = " + finalState.getScore());
		System.out.println("Upper score limit = " + state.getMaxScore());
		
		// print algorithm statistics to screen
		algorithm.printStatistics();
		
		// save results to file
		finalState.dump();
		
	}
	
}
