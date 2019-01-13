package fer.hmo.state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fer.hmo.ParsedArguments;
import fer.hmo.data.ParsingUtils;
import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;

public class State {
	
	private ParsedArguments args;
	
	private int timeout;
	private List<Integer> awardActivity;
	private int awardStudent;
	private int minmmaxPen;
	private String studentsFile;
	private String requestsFile;
	private String overlapsFile;
	private String limitsFile;

	private int maxScore;
	private int score;

	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Request> requests = new ArrayList<Request>();

	public State(ParsedArguments args) throws IOException {
		this.args = args;
		this.timeout = args.getTimeout();
		this.awardActivity = args.getAwardActivity();
		this.awardStudent = args.getAwardStudent();
		this.minmmaxPen = args.getMinmaxPenalty();
		this.studentsFile = args.getStudentsFile();
		this.requestsFile = args.getRequestsFile();
		this.overlapsFile = args.getOverlapsFile();
		this.limitsFile = args.getLimitsFile();

		ParsingUtils.parseInstance(this);
		
		this.score = 0;

	}
	
	
	//  HELPERS

	public Group findGroupById(int groupId) {
		Group group = null;
		for (Group g : this.groups) {
			if (g.getGroupId() == groupId) {
				group = g;
			}
		}
		return group;

	}
	
	public Student findStudentById(int studentId) {
		Student student = null;
		for (Student s : this.students) {
			if (s.getStudentId() == studentId) {
				student = s;
			}
		}
		return student;

	}

	// GETTERS AND SETTERS

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

	public int score() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
	
	// ADDERS 
	
	public void addGroup(Group group){
		this.groups.add(group);
	}
	
	public void addStudent(Student student){
		this.students.add(student);
	}
	
	public void addRequest(Request request){
		this.requests.add(request);
	}

	
	public void printState(){

		for (Group g:this.getGroups()){
			System.out.println("Group "+g.getGroupId());
			System.out.println(g.getOverlap().toString());
		}
		System.out.println();
		for (Student s:this.getStudents()){
			System.out.println("Student "+s.getStudentId());
			System.out.println(s.getActivityIds().toString());
			System.out.println(s.getSwapWeights().toString());
			System.out.println(s.getGroups().toString());
			System.out.println(s.getNewGroups().toString());
			
		}
		System.out.println();
		for (Request r:this.getRequests()){
			System.out.println("Request: ");
			System.out.println(r.getStudent().toString());
			System.out.println("current "+r.getCurrentGroup().toString());
			System.out.println("requested "+r.getRequestedGroup().toString());
		}
	}
}
