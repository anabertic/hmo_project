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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityId;
		result = prime * result + ((requestedGroup == null) ? 0 : requestedGroup.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		Request other = (Request) obj;
		if (activityId != other.activityId)
			return false;
		if (requestedGroup == null) {
			if (other.requestedGroup != null)
				return false;
		} else if (!requestedGroup.equals(other.requestedGroup))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
