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

	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Request> requests = new ArrayList<Request>();
	
	private Evaluation evaluation;
	private int maxScore;
	private int score;

	public State(ParsedArguments args) throws IOException {
		this.args = args;
		
		// save user-set parameters
		this.timeout = args.getTimeout();
		this.awardActivity = args.getAwardActivity();
		this.awardStudent = args.getAwardStudent();
		this.minmmaxPen = args.getMinmaxPenalty();
		
		// save instance data file paths
		this.studentsFile = args.getStudentsFile();
		this.requestsFile = args.getRequestsFile();
		this.overlapsFile = args.getOverlapsFile();
		this.limitsFile = args.getLimitsFile();

		// parse instance data
		ParsingUtils.parseInstance(this);
		
		// initialize evaluation object
		this.evaluation = new Evaluation(this);
		this.maxScore = evaluation.getMaxScore();
		this.score = evaluation.getCurrentScore();	// starting score after evaluation is initialized
	}
	
	public int evaluateRequest(Request request) {
		// check if the request would not comply with any of the hard limits
		//
		// 1)
		// check if request would put a group below the minimum student requirement
		if (!request.getCurrentGroup().canRemoveStudent()) {
			return Integer.MIN_VALUE;
		}
		
		// 2)
		// check if request would put a group above the maximum student requirement
		if (!request.getCurrentGroup().canAddStudent()) {
			return Integer.MIN_VALUE;
		}
		
		// 3)
		// check if the requested group overlaps with another group
		// student already belongs in
		if (request.getRequestedGroup().isOverlapping(request.getStudent().getGroups()) {
			return Integer.MIN_VALUE;
		}
		
		return evaluation.calculateCandidateStateScore(request);
	}
	
	public void swap(Request request){
		
		// TODO check if overlaps 
		// TODO check if within hard limits
		
		request.apply();
		
		this.updateGroupStudentsCnt(request);

		Evaluation.updateScore(this, request);
	}
	
	public void updateGroupStudentsCnt(Request request){
		int groupIndexRequested = this.groups.indexOf(request.getRequestedGroup());
		Group.addStudent(request.getRequestedGroup());
		this.groups.set(groupIndexRequested, request.getRequestedGroup());
		
		int groupIndexCurrent= this.groups.indexOf(request.getCurrentGroup());
		Group.removeStudent(request.getRequestedGroup());
		this.groups.set(groupIndexCurrent, request.getCurrentGroup());
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
	
	public int getSwapByStudentActivity(Student student, int activityId) {
		int i = student.getActivityIds().indexOf(activityId);
		
		return student.getSwapWeights().get(i);
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

	public int getScoreA() {
		return scoreA;
	}

	public void setScoreA(int scoreA) {
		this.scoreA = scoreA;
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
			System.out.println("students cnt "+g.getStudentsCnt());
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
