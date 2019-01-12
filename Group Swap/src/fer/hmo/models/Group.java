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
	
	
}
