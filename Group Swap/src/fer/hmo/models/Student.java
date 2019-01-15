package fer.hmo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

	private int studentId;
	private List<Integer> activityIds;
	private List<Integer> swapWeights;
	private List<Group> groups;
	private List<Group> newGroups;
	
	private Map<Integer, Request> satisfiedRequests;
	
	public Student(int studentId) {
		this.studentId = studentId;
		
		this.activityIds = new ArrayList<>();
		this.swapWeights = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.newGroups = new ArrayList<>();
		
		this.satisfiedRequests = new HashMap<>();
	}
	
	public void add(int activityId, int swapWeight, Group group) {
		this.activityIds.add(activityId);
		this.swapWeights.add(swapWeight);
		this.groups.add(group);
		
		this.newGroups.add(new Group(group));
	}
	
	public void applyRequest(Request request) {
		this.satisfiedRequests.put(request.getActivityId(), request);
	}
	
	public boolean existsSatisfiedRequestForActivity(int activityId) {
		if (this.satisfiedRequests.get(activityId) != null) {
			return true;
		}
		
		return false;
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
	
	public Map<Integer, Request> getSatisfiedRequests() {
		return satisfiedRequests;
	}

	public void setSatisfiedRequests(Map<Integer, Request> satisfiedRequests) {
		this.satisfiedRequests = satisfiedRequests;
	}

	// ----- END GETTERS AND SETTERS -----
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentId != other.studentId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < this.getActivityIds().size();i++){
			sb.append(studentId + "," + this.getActivityIds().get(i) 
					+ "," + this.getSwapWeights().get(i)
					+ "," + this.getGroups().get(i) .getGroupId()
					+ "," + this.getNewGroups().get(i).getGroupId()+"\n");
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	
	

}
