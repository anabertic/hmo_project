package fer.hmo.data;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;
import fer.hmo.state.State;

public class ParsingUtils {

	public static void parseInstance(State state) throws IOException {

		createGroups(state);
		updateGroupOverlaps(state);
		createStudents(state);
		state.setRequests(createRequests(state.getRequestsFile()));

	}


	public static ArrayList<Group> createGroups(State state) throws IOException {
		ArrayList<Group> groups = new ArrayList<Group>();
		BufferedReader br = new BufferedReader(new FileReader(state.getLimitsFile()));
		br.readLine();	
		String line;
		// filling in group list
		while ((line = br.readLine()) != null) {
			int[] params = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
			state.addGroup(new Group(params[0], params[1], params[2], params[3], params[4], params[5]));

		}
		return groups;

	}

	private static void updateGroupOverlaps(State state) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(state.getOverlapsFile()));
		String line;
		for (Group group : state.getGroups()) {
			br.readLine();
			ArrayList<Group> overlap = new ArrayList<Group>();
			while ((line = br.readLine()) != null) {				
				if (Integer.parseInt(line.split(",")[0]) == group.getGroupId()) {				
					overlap.add(state.findGroupById(Integer.parseInt(line.split(",")[1])));
				}			
			}
			group.setOverlap(overlap);
			br = new BufferedReader(new FileReader(state.getOverlapsFile()));
		}		
	}
	
	private static void createStudents(State state) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(state.getStudentsFile()));
		br.readLine();	
		String line;
		
		while ((line = br.readLine()) != null) {
			int studentId = Integer.parseInt(line.split(",")[0]);
			int activityId = Integer.parseInt(line.split(",")[1]);
			int swapWeight = Integer.parseInt(line.split(",")[2]);
			int group = Integer.parseInt(line.split(",")[3]);
			//int newGroup = Integer.parseInt(line.split(",")[4]);
			
			Student student = state.findStudentById(studentId);
			if (student == null){
				student = new Student(studentId);
				student.add(activityId, swapWeight, state.findGroupById(group));				
				state.addStudent(student);
			}
			else{
				student.add(activityId, swapWeight, state.findGroupById(group));
			}		
		}
		
	}

	private static ArrayList<Request> createRequests(String requestsFile) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
