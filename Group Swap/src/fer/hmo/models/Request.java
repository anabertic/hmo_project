package fer.hmo.models;

public class Request {

	private Student student;
	private int activityId;
	private Group requestedGroup;
	private boolean satisfied;
	
	public Request(Student student, int activityId, Group requestedGroup) {
		this.student = student;
		this.activityId = activityId;
		this.requestedGroup = requestedGroup;
		
		this.satisfied = false;
	}
	
	// ----- GETTERS AND SETTERS -----

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public Group getRequestedGroup() {
		return requestedGroup;
	}

	public void setRequestedGroup(Group requestedGroup) {
		this.requestedGroup = requestedGroup;
	}

	public boolean isSatisfied() {
		return satisfied;
	}

	public void setSatisfied(boolean satisfied) {
		this.satisfied = satisfied;
	}
	
	// ----- END GETTERS AND SETTERS -----
	
	
}