package fer.hmo.data;

import java.awt.List;
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
	
	public static void parseInstance( State state) {

		state.setGroups(createGroups(state.getLimitsFile(),state.getOverlapsFile()));
		state.setStudents(createStudents(state.getStudentsFile()));
		state.setRequests(createRequests(state.getRequestsFile()));
		
	}

	private static ArrayList<Request> createRequests(String requestsFile) {
		// TODO Auto-generated method stub
		return null;
	}

	private static ArrayList<Student> createStudents(String studentsFile) {
		// TODO Auto-generated method stub
		return null;
	}

	private static ArrayList<Group> createGroups(String limitsFile, String overlapsFile) {
		// TODO Auto-generated method stub
		return null;
	}

	private void studentFile(String path){
		BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String sCurrentLine;
			ArrayList<String> items = new ArrayList<>();
			while ((sCurrentLine = br.readLine()) != null) {
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(sCurrentLine.split(",")));
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	

}
