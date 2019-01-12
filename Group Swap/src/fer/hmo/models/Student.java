package fer.hmo.models;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private int studentId;
	private List<Integer> activityIds;
	private List<Integer> swapWeights;
	private List<Group> groups;
	private List<Group> newGroups;
	
	public Student(int studentId) {
		this.studentId = studentId;
		
		this.activityIds = new ArrayList<>();
		this.swapWeights = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.newGroups = new ArrayList<>();
	}
	
	public void add(int activityId, int swapWeight, Group group) {
		this.activityIds.add(activityId);
		this.swapWeights.add(swapWeight);
		this.groups.add(group);
		
		this.newGroups.add(new Group(group));
	}
	
	public void applyRequest(Request request) {
		this.newGroups.set(this.activityIds.indexOf(request.getActivityId()), request.getRequestedGroup());
		request.setSatisfied(true);
	}
	
	// ----- GETTERS AND SETTERS -----

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<Integer> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(List<Integer> activityIds) {
		this.activityIds = activityIds;
	}

	public List<Integer> getSwapWeights() {
		return swapWeights;
	}

	public void setSwapWeights(List<Integer> swapWeights) {
		this.swapWeights = swapWeights;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getNewGroups() {
		return newGroups;
	}

	public void setNewGroups(List<Group> newGroups) {
		this.newGroups = newGroups;
	}
	
	// ----- END GETTERS AND SETTERS -----
}
