package org.usfirst.frc.team4911.helpers;year scsv'''

import java.io.*;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.updators.*;

/**
 * Handler class for the log file
 * @author Anne Gwynne-Robson
 *
 */
public class LogFileHandler {
	// The header of the log file. Be sure to update this whenever we
	// start logging new parameters.
	private final String logFileHeader = "Timestamp," +
										"Timedelta," +
										"Battery Voltage (V)," + 
										"Front Left Talon Power," +
										"Mid Left Talon Power," +
										"Rear Left Talon Power," +
										"Front Right Talon power," +
										"IMU roll," + 
										"IMU pitch," + 
										"IMU yaw";
	
	// Handle to the log file
	private PrintWriter logFileWriter;
	
	// The name of the log file
	private String logFileName;
	
	// True the first time we write a line to the log file.
	private boolean firstLogFileBodyEntry;
	
	// The time of the last log
	private long lastLogTime;
		   
	/**
	 * Creates a handler which writes to a file. The file name will look like this:
	 * <fileNameBase><time the file was created (system time in milliseconds)>.csv
	 * @param fileNameBase
	 */
	public LogFileHandler(String fileNameBase) {
		firstLogFileBodyEntry = true;
		lastLogTime = 0;
		logFileName = fileNameBase + String.valueOf(System.currentTimeMillis()) + ".csv";
		
		try {
			logFileWriter = new PrintWriter(logFileName);
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
		String logEntry = String.join(",", String.valueOf(timestamp), String.valueOf(timedelta), String.valueOf(Sensors.getVoltage()),
				+ String.valueOf(RobotMap.DriveFrontLeftTalon.get()),
				+ String.valueOf(RobotMap.DriveMidLeftTalon.get()) + ','
				+ String.valueOf(RobotMap.DriveRearLeftTalon.get()) + ','
				+ String.valueOf(RobotMap.DriveFrontRightTalon.get()) + ','
				+ String.valueOf(RobotMap.DriveMidRightTalon.get()) + ','
				+ String.valueOf(RobotMap.DriveRearRightTalon.get()) + ','
				+ String.valueOf(Sensors.getImu().getRoll()) + ','
				+ String.valueOf(Sensors.getImu().getPitch()) + ','
				+ String.valueOf(Sensors.getImu().getYaw()));
		
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
