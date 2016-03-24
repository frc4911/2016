package org.usfirst.frc.team4911.helpers;

import java.io.*;
import org.usfirst.frc.team4911.updators.*;

/**
 * Handler class for the log file
 * @author Anne Gwynne-Robson
 *
 */
public class LogFileHandler {
	// The header of the log file. Be sure to update this whenever we
	// start logging new parameters.
	private String logFileHeader = "Timestamp,Timedelta,Battery Voltage (V)";
	
	// Handle to the log file
	private PrintWriter logFileWriter = null;
	
	// True the first time we write a line to the log file.
	boolean firstLogFileBodyEntry = true;
	
	// The time of the last log
	long lastLogTime = 0;
	
	// The instance of this class that's here so we can make this a
	// singleton.
	private static LogFileHandler instance = null;
	   
	// Private constructor. Also a singleton thing.
	protected LogFileHandler() {
		// Exists to defeat instantiation
	}
	
	// Call this whenever you want to use this class.
	public static LogFileHandler getInstance() {
		if(instance == null) {
			instance = new LogFileHandler();
		}
		return instance;
	}
	
	/**
	 * Opens the log file for open and writes the header
	 */
	public void CreateLogFile() throws FileNotFoundException {
		try {
			logFileWriter = new PrintWriter("logfile.csv");
			logFileWriter.println(logFileHeader);
			logFileWriter.flush();
		} catch (FileNotFoundException e) {
			// We don't really have exception handling so we'll just catch
			// and keep going
			Logging.DebugPrint(e.getMessage());
		}
	}
	
	/**
	 * Writes the robot state to the log file.
	 */
	public void WriteLogEntry()
	{
		// If the file didn't initialize then we can't write to it and
		// should just return.
		if (logFileWriter == null) {
			return;
		}
		
		// Calculate time stamp information
		long timestamp = System.currentTimeMillis();
		long timedelta = (firstLogFileBodyEntry) ? 0 : timestamp - lastLogTime;
		lastLogTime = timestamp;
		
		// Build a single line of our log file
		String logEntry = String.valueOf(timestamp) + ','
				+ String.valueOf(timedelta) + ','
				+ String.valueOf(Sensors.getVoltage());
		
		if (firstLogFileBodyEntry) {
			// Check that the number of fields in our header and body
			// lines is the same and adds a line to the file telling us
			// if this is not the case.
			if (logFileHeader.split(",").length == logEntry.split(",").length) {
				logEntry = "You're missing a field in either the header or the body";
			}
			
			// Update this so that we don't do all the length checks and 
			// such again.
			firstLogFileBodyEntry = false;
		}
		
		//Write to the log file
		logFileWriter.println(logEntry);
		logFileWriter.flush();
	}
}
