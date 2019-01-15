package fer.hmo;

import java.io.IOException;
import java.util.ArrayList;

import fer.hmo.algorithms.greedy.GreedySearch;
import fer.hmo.algorithms.random.RandomSearch;
import fer.hmo.data.ParsingUtils;
import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;
import fer.hmo.state.State;

public class Main {

	public static void main(String[] args) throws IOException {
		// parse arguments
		//meta...
		// -timeout 600 -> number of seconds for algorithm to run
		//points...
		// -award-activity "1,2,4" -> points awarded to State based on number of requests satisfied per student
		// –award-student 1 -> points awarded when all student activities are transfered to one of requested groups
		// –minmax-penalty 1 -> points deducted for every student in every group that is not within the preferred size limits
		//files...
		// –students-file student.csv
		// –requests-file requests.csv
		// –overlaps-file overlaps.csv
		// –limits-file limits.csv
		
		ParsedArguments parsedArguments = ArgumentsParser.parseArguments(args);
		System.out.println("Parsed arguments:");
		System.out.println(parsedArguments);
		System.out.println();
		
		int instance = 1;
		State state = new State(parsedArguments, 1);
		System.out.println("Starting state info:");
		System.out.println("Max Score = " + state.getMaxScore());
		System.out.println("Starting Score = " + state.getEvaluation().getCurrentScore());
		System.out.println("Number of students: " + state.getStudents().size());
		System.out.println("Number of groups: " + state.getGroups().size());
		System.out.println("Number of requests: " + state.getRequests().size());
		System.out.println();
		//System.out.println(state);

		GreedySearch greedySearch = new GreedySearch(state,10000);
		greedySearch.search();
		//System.out.println(state);
		System.out.println("Max possible score "+state.getMaxScore());
		System.out.println("Our score "+state.getEvaluation().getCurrentScore());
		
		
	}

}
