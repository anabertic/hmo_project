package fer.hmo.models;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private int groupId;
	private int studentsCnt;
	private int min;
	private int minPreferred;
	private int max;
	private int maxPreferred;
	
	private List<Group> overlap;
	
	public Group(int groupId, int studentsCnt, int min, int minPreferred, int max, int maxPreferred) {
		this.groupId = groupId;
		this.studentsCnt = studentsCnt;
		this.min = min;
		this.minPreferred = minPreferred;
		this.max = max;
		this.maxPreferred = maxPreferred;
		
		overlap = new ArrayList<Group>();
	}
	
	public Group(Group group) {
		this.groupId = group.getGroupId();
		this.studentsCnt = group.getStudentsCnt();
		this.min = group.getMin();
		this.minPreferred = group.getMinPreferred();
		this.max = group.getMax();
		this.maxPreferred = group.getMaxPreferred();
		
		this.overlap = group.getOverlap();
	}
	
	public void addOverlapGroup(Group group) {
		this.overlap.add(group);
	}
	
	public boolean isOverlapping(Group group) {
		if (this.overlap.contains(group)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isOverlapping(Group group1, Group group2) {
		return group1.isOverlapping(group2);
	}
	
	public boolean isWithinHardLimits() {
		if (this.studentsCnt < this.min || this.studentsCnt > this.max) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isWithinHardLimits(Group group) {
		return group.isWithinHardLimits();
	}
	
	public boolean canAddStudent() {
		if (this.studentsCnt >= this.max) {
			return false;
		}
		
		return true;
	}
	
	public static boolean canAddStudent(Group group) {
		return group.canAddStudent();
	}
	
	public boolean canRemoveStudent() {
		if (this.studentsCnt <= this.min) {
			return false;
		}
		
		return true;
	}
	
	public static boolean canRemoveStudent(Group group) {
		return group.canAddStudent();
	}
	
	public static void addStudent(Group group){
		if (canAddStudent(group))
			group.setStudentsCnt(group.getStudentsCnt()+1);
	}
	
	public static void removeStudent(Group group){
		if (canRemoveStudent(group))
		group.setStudentsCnt(group.getStudentsCnt()-1);
	}
	
	
	// ----- GETTERS AND SETTERS -----

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getStudentsCnt() {
		return studentsCnt;
	}

	public void setStudentsCnt(int studentsCnt) {
		this.studentsCnt = studentsCnt;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMinPreferred() {
		return minPreferred;
	}

	public void setMinPreferred(int minPreferred) {
		this.minPreferred = minPreferred;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMaxPreferred() {
		return maxPreferred;
	}

	public void setMaxPreferred(int maxPreferred) {
		this.maxPreferred = maxPreferred;
	}

	public List<Group> getOverlap() {
		return overlap;
	}

	public void setOverlap(List<Group> overlap) {
		this.overlap = overlap;
	}

	// ----- END GETTERS AND SETTERS -----
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupId;
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
		Group other = (Group) obj;
		if (groupId != other.groupId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupID: " + groupId + "";
	}
	
	
	
}
