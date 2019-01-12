package fer.hmo;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {

	private int studentId;
	private HashMap<Activity, Integer> currentGroups;
	private HashMap<Activity,ArrayList<Integer>> wantedGroups;
	
	public Student(int studentId, HashMap<Activity,Integer> currentGroups, HashMap<Activity, ArrayList<Integer>> wantedGroups){
		this.studentId = studentId;
		this.currentGroups = currentGroups;
		this.wantedGroups = wantedGroups;
	}
	
	
	

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public HashMap<Activity, Integer> getCurrentGroups() {
		return currentGroups;
	}

	public void setCurrentGroups(HashMap<Activity, Integer> currentGroups) {
		this.currentGroups = currentGroups;
	}

	public HashMap<Activity, ArrayList<Integer>> getWantedGroups() {
		return wantedGroups;
	}

	public void setWantedGroups(HashMap<Activity, ArrayList<Integer>> wantedGroups) {
		this.wantedGroups = wantedGroups;
	}
	

}


