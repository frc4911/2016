/**
 * The parser that takes the json log files and renders them into
 * nice .csvs all ready to be opened in Excel.
 * 
 * @author Anne Gwynne-Robson
 */

import java.io.*;
import java.lang.reflect.*;
import com.google.gson.*;

public class Parser {	
	/**
	 * Takes in a log file of JSON blob RobotStates and generates a .csv
	 * from the contents.
	 * 
	 * @param filePath - the path of the log file to be parsed
	 * @param csvFilePath
	 */
	public void ParseToCsv(String logFilePath, String csvFilePath) {
		try(BufferedReader br = new BufferedReader(new FileReader(logFilePath)); PrintWriter pr = new PrintWriter(csvFilePath)) {		
		    RobotState logEntry;
		    String csvLine;
		    
		    // First, we write the header of the .csv file
		    String line = br.readLine();
		    logEntry = this.ParseLogEntry(line);
		    csvLine = this.RobotStateToCsvLine(logEntry, true);
		    pr.println(csvLine);

		    // And then the body
		    for(;line != null; line=br.readLine()) {
		    	logEntry = this.ParseLogEntry(line);
		    	csvLine = this.RobotStateToCsvLine(logEntry, false);
		    	pr.println(csvLine);
		    }
		    
		    br.close();
		    pr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	// Parses a single log entry into a RobotState object
	private RobotState ParseLogEntry(String logEntryAsJson) {
	    Gson gson = new GsonBuilder().create(); 
	    RobotState logEntryAsState = gson.fromJson(logEntryAsJson, RobotState.class);
	    return logEntryAsState;
	}
	
	/**
	 * Parses a RobotState object into a csv file line
	 * @param state - the RobotState object to parse
	 * @param isHeader - true if the line we're parsing is a header line
	 * @return - a string containing the line
	 */
	private String RobotStateToCsvLine(RobotState state, boolean isHeader) {
		String line = "";
		
		Field[] fields = state.getClass().getFields();
		
		try {
			// This looks a little scary but all it's doing is going
			// through each field of the RobotState object that got 
			// passed in and pulling out the value we want to write 
			// to the csv file. Note that if we're generating the
			// header we're interested in names instead of values.
			for (Field field : fields) {
				if (field.getType() == LogParameter.class) {
					LogParameter logEntry = (LogParameter)field.get(state);
					line = line + ((isHeader) ? logEntry.Name : logEntry.Value) + ',';
				}
				else
				{
					line = line + ((isHeader) ? field.getName() : field.get(state)) + ',';
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return line.substring(0, line.length()-1);
	}
}
