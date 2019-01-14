package fer.hmo.models;

public class Request {

	private Student student;
	private int activityId;
	private Group currentGroup;
	private Group requestedGroup;
	private boolean satisfied;
	
	public Request(Student student, int activityId, Group requestedGroup) {
		this.student = student;
		this.activityId = activityId;
		this.currentGroup = student.getGroups().get(student.getActivityIds().indexOf(this.activityId));
		this.requestedGroup = requestedGroup;
		
		this.satisfied = false;
	}
	
	public void apply(){
		Student student = this.getStudent();
		student.getNewGroups().set(student.getActivityIds().indexOf(this.getActivityId()), this.getRequestedGroup());
		this.setSatisfied(true);
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
	
	public Group getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}
	
	// ----- END GETTERS AND SETTERS -----
	
	
}
