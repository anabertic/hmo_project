package fer.hmo.data;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

		state.setGroups(createGroups(state));
		updateGroupOverlaps(state);
		state.setStudents(createStudents(state.getStudentsFile()));
		state.setRequests(createRequests(state.getRequestsFile()));

	}

	private static void updateGroupOverlaps(State state) throws NumberFormatException, IOException {
		BufferedReader br2 = new BufferedReader(new FileReader(state.getOverlapsFile()));
		String line2;
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
		
	}

	public static ArrayList<Group> createGroups(State state) throws IOException {
		ArrayList<Group> groups = new ArrayList<Group>();
		BufferedReader br = new BufferedReader(new FileReader(state.getLimitsFile()));
		
		br.readLine();
		
		String line;
		String line2;

		// filling in group list
		while ((line = br.readLine()) != null) {
			int[] params = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
			groups.add(new Group(params[0], params[1], params[2], params[3], params[4], params[5]));

		}

		// assigning overlap list to each group
	
		return groups;

	}

	private static ArrayList<Student> createStudents(String studentsFile) {
		ArrayList<Student> students = new ArrayList<>();
		// TODO Auto-generated method stub
		return students;
	}

	private static ArrayList<Request> createRequests(String requestsFile) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
