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
	private int instance;

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
	
	// for help with algorithms
	// since there is no need to evaluate requests that are already satisfied
	private List<Request> unsatisfiedRequests;

	private Evaluation evaluation;
	private int maxScore;
	private int score;
	
	private boolean isStateValid;

	public State(ParsedArguments args, int instance) throws IOException {
		this.args = args;
		
		this.instance = instance;

		// save user-set parameters
		this.timeout = args.getTimeout();
		this.awardActivity = args.getAwardActivity();
		this.awardStudent = args.getAwardStudent();
		this.minmmaxPen = args.getMinmaxPenalty();

		// save instance data file paths
		
		// read from cmd input arguments as stated in assignment
		if (instance == 0) {
			this.studentsFile = args.getStudentsFile();
			this.requestsFile = args.getRequestsFile();
			this.overlapsFile = args.getOverlapsFile();
			this.limitsFile = args.getLimitsFile();
		}
		
		// for faster approach: 
		// instance = 1 : using files from res/Instanca 1 folder
		// instance = 2 : using files from res/Instanca 2 folder
		// instance = 3 : using files from res/Instanca 3 folder
		// instance = 4 : using files from res/Instanca 4 folder
		else {
			String absolutePath = System.getProperty("user.dir");
			this.studentsFile = absolutePath+"/res/Instanca "+instance+"/student["+instance+"].csv";
			this.requestsFile = absolutePath+"/res/Instanca "+instance+"/requests["+instance+"].csv";
			this.overlapsFile = absolutePath+"/res/Instanca "+instance+"/overlaps["+instance+"].csv";
			this.limitsFile = absolutePath+"/res/Instanca "+instance+"/limits["+instance+"].csv";
		}

		// parse instance data
		ParsingUtils.parseInstance(this);
		// check if state is valid
		this.isStateValid = this.isStartingStateValid();
		// At the beginning, no requests are satisfied. 'unsatisfiedRequests' list is then equal to the 'requests' list
		this.unsatisfiedRequests = new ArrayList<Request>(this.requests);

		// initialize evaluation object
		this.evaluation = new Evaluation(this);
		this.maxScore = evaluation.getMaxScore();
		this.score = evaluation.getCurrentScore();  // starting score after the
													// evaluation is initialized
		
	}
	
	public int evaluateRequest(Request request) {
		
		// request checking transferred to isRequestValid method

		return evaluation.calculateCandidateStateScore(request);
	}
	
	public boolean isRequestValid(Request request){
		// check if the request would not comply with any of the hard limits
		//
		// 1)
		// check if request would put a group below the minimum student
		// requirement
		//System.out.println("current "+request.getCurrentGroup());
		if (!request.getStudent().getGroups().get(request.getStudent().getActivityIds().indexOf(request.getActivityId())).canRemoveStudent()) {
			return false;
		}

		// 2)
		// check if request would put a group above the maximum student
		// requirement
		if (!request.getRequestedGroup().canAddStudent()) {
			return false;
		}

		// 3)
		// check if the requested group overlaps with another group
		// student already belongs in
		/*
		if (request.getRequestedGroup().isOverlapping(request.getStudent().getGroups())) {
			return false;
		}
		*/
		if (request.getRequestedGroup().existsOverlap(request.getStudent().getGroups())) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean isStateValid(){
		// check if state doesn't satisfy hard limits
		// this includes any group that has n_students < min_students
		//		and any group that has n_students > max_students 
		for (Group group : this.getGroups()){
			if (!group.isWithinHardLimits()){
				return false;
			}
		}
		
		// check if there is an overlap between groups for every student
		for (Student student : this.getStudents()) {
			if (student.existsGroupOverlap()) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public boolean isStartingStateValid() {
		// check if state doesn't satisfy hard limits
		// this includes any group that has n_students < min_students
		//		and any group that has n_students > max_students 
		for (Group group : this.getGroups()){
			if (!group.isWithinHardLimits()){
				return false;
			}
		}
		
		return true;
	}

	public void applyRequest(Request request) {
		// first, update evaluation score for new state

		evaluation.applyRequest(request);
		this.score = evaluation.getCurrentScore();

		// UPDATE GROUP
		// then, update groups student counts that were involved in the request
		// (current and new group)
		this.updateGroupStudentsCnt(request);

		// UPDATE REQUEST
		// update the Request object itself
		Request nowUnsatisfiedRequest = request.apply();
		
		// UPDATE STUDENT
		// update student's current group
		request.getStudent().applyRequest(request, nowUnsatisfiedRequest);
		
		// remove and add request from and to 'unsatisfiedRequests' list
		// first, remove request that was just satisfied
		this.unsatisfiedRequests.remove(request);
		// second, add request that became unsatisfied because student switched
		// from group which he signed up for because of another request
		if (nowUnsatisfiedRequest != null) {
			this.unsatisfiedRequests.add(nowUnsatisfiedRequest);
		}
	}

	public void updateGroupStudentsCnt(Request request) {

		int groupIndexRequested = this.groups.indexOf(request.getRequestedGroup());
		
		Group.addStudent(this.groups.get(groupIndexRequested));
		
		int groupIndexCurrent = this.groups.indexOf(request.getCurrentGroup());
		
		Group.removeStudent(this.groups.get(groupIndexCurrent));

	}

	// HELPERS

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
	
	public void dump() {
		
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

	public ParsedArguments getArgs() {
		return args;
	}

	public void setArgs(ParsedArguments args) {
		this.args = args;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public List<Request> getUnsatisfiedRequests() {
		return this.unsatisfiedRequests;
	}
	
	public void setUnsatisfiedRequests(List<Request> unsatisfiedRequests) {
		this.unsatisfiedRequests = unsatisfiedRequests;
	}
	
	public boolean getIsStateValid() {
		return this.isStateValid;
	}
	
	public void setIsStateValid(boolean isStateValid) {
		this.isStateValid = isStateValid;
	}

	// ---- ADDERS ----

	public void addGroup(Group group) {
		this.groups.add(group);
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void addRequest(Request request) {
		this.requests.add(request);
	}
	
	// ---- UTILS ----

	@Override
	public String toString() {
		System.out.println("Groups");
		for (Group group : this.getGroups()) {
			System.out.println(group);
		}
		System.out.println("Students:");
		for (Student student : this.getStudents()) {
			System.out.println(student);

		}
		System.out.println("Requests");
		for (Request request : this.getRequests()) {
			System.out.println(request);
		}
		return "Final score: " + this.evaluation.getCurrentScore();
	}

	public ArrayList<Request> getNotSatisfiedRequests(){
		ArrayList<Request> notSatisfiedRequests = new ArrayList<>();
		for (Request request:this.requests){
			if( !request.isSatisfied()){
				notSatisfiedRequests.add(request);
			}
		}
		return notSatisfiedRequests;
	}
	
}
