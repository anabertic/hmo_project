package fer.hmo;

import fer.hmo.algorithms.random.RandomSearch;

public class Main {

	public static void main(String[] args) {
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
		
		
		int timeout = Integer.parseInt(args[1]);
		System.out.println(args[0] + " " + timeout);
		System.out.println();
		
		String awardActivity = args[3];
		System.out.println("award-activity " + awardActivity);
		int awardStudent = Integer.parseInt(args[5]);
		System.out.println("award-student " + awardStudent);
		int minmaxPenalty = Integer.parseInt(args[7]);
		System.out.println("minmax-penalty " + minmaxPenalty);
		System.out.println();
		
		String studentsFile = args[9];
		System.out.println("students-file " + studentsFile);
		String requestsFile = args[11];
		System.out.println("requests-file " + requestsFile);
		String overlapsFile = args[13];
		System.out.println("overlaps-file " + overlapsFile);
		String limitsFile = args[15];
		System.out.println("limits-file " + limitsFile);
		System.out.println();
		
		String[] filePaths = new String[4];
		filePaths[0] = studentsFile;
		filePaths[1] = requestsFile;
		filePaths[2] = overlapsFile;
		filePaths[3] = limitsFile;
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("With ArgumentParser...");
		ParsedArguments parsedArguments = ArgumentsParser.parseArguments(args);
		System.out.println(parsedArguments);
		
		
		
		
		//RandomSearch randomSearch = new RandomSearch(args);
		
	}

}
