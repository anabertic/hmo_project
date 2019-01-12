package fer.hmo.state;

import java.util.ArrayList;
import java.util.Arrays;

import fer.hmo.data.ParsingUtils;
import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;

public class State {
	
	private String[] args;
	private int timeout;
	private int[] awardActivity;
	private int awardStudent;
	private int minmmaxPen;
	private String studentsFile;
	private String requestsFile;
	private String overlapsFile;
	private String limitsFile;
	
	private int maxScore;
	
	private ArrayList<Group> groups;
	private ArrayList<Student> students;
	private ArrayList<Request>requests; 
	
	public State(String[] args){
		this.timeout = Integer.parseInt(args[0]);
		this.awardActivity = Arrays.stream(args[1].split(",")).mapToInt(Integer::parseInt).toArray();
		this.awardStudent = Integer.parseInt(args[2]);
		this.minmmaxPen = Integer.parseInt(args[3]);
		this.studentsFile = args[4];
		this.requestsFile = args[5];
		this.overlapsFile = args[6];
		this.limitsFile = args[7];
		
		
		ParsingUtils.parseInstance(this);
	
		
	}
	
	//                 GETTERS AND SETTERS

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int[] getAwardActivity() {
		return awardActivity;
	}

	public void setAwardActivity(int[] awardActivity) {
		this.awardActivity = awardActivity;
	}

	public int getAwardStudent() {
		return awardStudent;
	}

	public void setAwardStudent(int awardStudent) {
		this.awardStudent = awardStudent;
	}

	public int getMinmmaxPen() {
		return minmmaxPen;
	}

	public void setMinmmaxPen(int minmmaxPen) {
		this.minmmaxPen = minmmaxPen;
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

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	
	

}
