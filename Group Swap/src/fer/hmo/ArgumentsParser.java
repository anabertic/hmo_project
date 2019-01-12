package fer.hmo;

import java.util.HashMap;
import java.util.Map;

public class ArgumentsParser {
	
	public static ParsedArguments parseArguments(String[] args) {
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
		
		if (!ArgumentsParser.isArgsValid(ArgumentsParser.concatenateArgs(args))) {
			return null;
		}
		
		Map<String, String> mappedArgs = ArgumentsParser.mapArguments(args);
		
		ParsedArguments parsedArguments = new ParsedArguments();
		// fill parsedArguments object
		parsedArguments.parseTimeout(mappedArgs.get("timeout"));
		parsedArguments.parseAwardActivity(mappedArgs.get("award-activity"));
		parsedArguments.parseAwardStudent(mappedArgs.get("award-student"));
		parsedArguments.parseMinmaxPenalty(mappedArgs.get("minmax-penalty"));
		parsedArguments.parseStudentsFile(mappedArgs.get("students-file"));
		parsedArguments.parseRequestsFile(mappedArgs.get("requests-file"));
		parsedArguments.parseOverlapsFile(mappedArgs.get("overlaps-file"));
		parsedArguments.parseLimitsFile(mappedArgs.get("limits-file"));
		
		return parsedArguments;
		
	}
	
	private static Map<String, String> mapArguments(String[] args) {
		Map<String, String> mappedArgs = new HashMap<>();
		
		mappedArgs.put(args[0].substring(1), args[1]);
		mappedArgs.put(args[2].substring(1), args[3]);
		mappedArgs.put(args[4].substring(1), args[5]);
		mappedArgs.put(args[6].substring(1), args[7]);
		mappedArgs.put(args[8].substring(1), args[9]);
		mappedArgs.put(args[10].substring(1), args[11]);
		mappedArgs.put(args[12].substring(1), args[13]);
		mappedArgs.put(args[14].substring(1), args[15]);
		
		return mappedArgs;
		
	}
	
	private static boolean isArgsValid(String args) {
		// find "-timeout"
		if (!args.contains("timeout")) {
			return false;
		}
		// find "-award-activity"
		if (!args.contains("award-activity")) {
			return false;
		}
		// find "–award-student"
		if (!args.contains("award-student")) {
			return false;
		}
		// find "–minmax-penalty"
		if (!args.contains("minmax-penalty")) {
			return false;
		}
		// find "–students-file"
		if (!args.contains("students-file")) {
			return false;
		}
		// find "–requests-file"
		if (!args.contains("requests-file")) {
			return false;
		}
		// find "–overlaps-file"
		if (!args.contains("overlaps-file")) {
			return false;
		}
		// find "–limits-file"
		if (!args.contains("limits-file")) {
			return false;
		}
		
		return true;
		
	}
	
	private static String concatenateArgs(String[] args) {
		String concatenatedArgs = "";
		
		for (int i = 0; i < args.length; i++) {
			concatenatedArgs += args[i];
			concatenatedArgs += " ";
		}
		
		return concatenatedArgs;
		
	}
	
}
