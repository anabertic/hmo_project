package fer.hmo.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fer.hmo.models.Group;
import fer.hmo.models.Request;
import fer.hmo.models.Student;
import fer.hmo.state.State;

public class ParsingUtils {

	public static void parseInstance(State state) throws IOException {

		createGroups(state);
		createStudents(state);
		createRequests(state);

	}

	public static void createGroups(State state) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(state.getLimitsFile()));
		BufferedReader br2 = new BufferedReader(new FileReader(state.getOverlapsFile()));
		br.readLine();
		br2.readLine();
		String line;
		String line2;
		while ((line = br.readLine()) != null) {
			int[] params = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
			state.addGroup(new Group(params[0], params[1], params[2], params[3], params[4], params[5]));
		}		
		for (Group group : state.getGroups()) {
			br2.readLine();
			ArrayList<Group> overlap = new ArrayList<Group>();
			while ((line2 = br2.readLine()) != null) {				
				if (Integer.parseInt(line2.split(",")[0]) == group.getGroupId()) {				
					overlap.add(state.findGroupById(Integer.parseInt(line2.split(",")[1])));
				}			
			}
			group.setOverlap(overlap);
			br2 = new BufferedReader(new FileReader(state.getOverlapsFile()));
		}
		
		br.close();
		br2.close();
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
		
		br.close();
		
	}

	private static void createRequests(State state) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(state.getRequestsFile()));
		br.readLine();	
		String line;
		
		while ((line = br.readLine()) != null) {
			int studentId = Integer.parseInt(line.split(",")[0]);
			int activityId = Integer.parseInt(line.split(",")[1]);
			int groupId = Integer.parseInt(line.split(",")[2]);
			state.addRequest(new Request(state.findStudentById(studentId),activityId,state.findGroupById(groupId)));
		}
		
		br.close();
	}
	


}
