package fer.hmo;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



public class ParsingUtils {
	
	public void studentFile(String path){
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
