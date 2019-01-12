package fer.hmo.state;

import java.util.ArrayList;
import java.util.Arrays;

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
		
		createStudents(studentsFile)
		
	}
	

}
