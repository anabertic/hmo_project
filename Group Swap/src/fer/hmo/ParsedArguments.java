package fer.hmo;

import java.util.ArrayList;
import java.util.List;

public class ParsedArguments {
		
	private int timeout;

	private List<Integer> awardActivity;
	private int awardStudent;
	private int minmaxPenalty;
	
	private String studentsFile;
	private String requestsFile;
	private String overlapsFile;
	private String limitsFile;
	
	private String[] filePaths;
		
	public ParsedArguments() {
		this.timeout = 0;
		this.awardActivity = new ArrayList<>();
		this.awardStudent = 0;
		this.minmaxPenalty = 0;
		
		this.studentsFile = "";
		this.requestsFile = "";
		this.overlapsFile = "";
		this.limitsFile = "";
		
		this.filePaths = new String[4];
		this.filePaths[0] = this.studentsFile;
		this.filePaths[1] = this.requestsFile;
		this.filePaths[2] = this.overlapsFile;
		this.filePaths[3] = this.limitsFile;
	}
	
	public void parseTimeout(String arg) {
		this.timeout = Integer.parseInt(arg);
	}
	
	public void parseAwardActivity(String arg) {
		arg = arg.trim();
		if (arg.contains("\"")) {
			arg = arg.replace("\"", "");
		}
		
		String[] awards = arg.split(",");
		for (int i = 0; i < awards.length; i++) {
			this.awardActivity.add(Integer.parseInt(awards[i]));
		}
	}
	
	public void parseAwardStudent(String arg) {
		this.awardStudent = Integer.parseInt(arg);
	}
	
	public void parseMinmaxPenalty(String arg) {
		this.minmaxPenalty = Integer.parseInt(arg);
	}
	
	public void parseStudentsFile(String arg) {
		this.studentsFile = arg;
	}
	
	public void parseRequestsFile(String arg) {
		this.requestsFile = arg;
	}
	
	public void parseOverlapsFile(String arg) {
		this.overlapsFile = arg;
	}
	
	public void parseLimitsFile(String arg) {
		this.limitsFile = arg;
	}
	
	// ----- GETTERS AND SETTERS -----

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public List<Integer> getAwardActivity() {
		return awardActivity;
	}

	public void setAwardActivity(List<Integer> awardActivity) {
		this.awardActivity = awardActivity;
	}

	public int getAwardStudent() {
		return awardStudent;
	}

	public void setAwardStudent(int awardStudent) {
		this.awardStudent = awardStudent;
	}

	public int getMinmaxPenalty() {
		return minmaxPenalty;
	}

	public void setMinmaxPenalty(int minmaxPenalty) {
		this.minmaxPenalty = minmaxPenalty;
	}

	public String getStudentsFile() {
		return studentsFile;
	}

	public void setStudentsFile(String studentsFile) {
		this.studentsFile = studentsFile;
	}

	public String getRequestsFile() {
		return requestsFile;
	}

	public void setRequestsFile(String requestsFile) {
		this.requestsFile = requestsFile;
	}

	public String getOverlapsFile() {
		return overlapsFile;
	}

	public void setOverlapsFile(String overlapsFile) {
		this.overlapsFile = overlapsFile;
	}

	public String getLimitsFile() {
		return limitsFile;
	}

	public void setLimitsFile(String limitsFile) {
		this.limitsFile = limitsFile;
	}

	public String[] getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(String[] filePaths) {
		this.filePaths = filePaths;
	}
	
	// ----- END GETTERS AND SETTERS -----
	
	@Override
	public String toString() {
		String str = "";
		
		str += "-timeout ";
		str += this.timeout;
		str += "\n";
		
		str += "-award-activity ";
		str += this.awardActivity;
		str += "\n";
		str += "-award-student ";
		str += this.awardStudent;
		str += "\n";
		str += "-minmax-penalty ";
		str += this.minmaxPenalty;
		str += "\n";

		str += "-students-file ";
		str += this.studentsFile;
		str += "\n";
		str += "-requests-file ";
		str += this.requestsFile;
		str += "\n";
		str += "-overlaps-file ";
		str += this.overlapsFile;
		str += "\n";
		str += "-limits-file ";
		str += this.limitsFile;
		str += "\n";
				
		return str;
	}
	
}
