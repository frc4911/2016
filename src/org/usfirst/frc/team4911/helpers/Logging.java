package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.AnalogPotentiometer;
import org.usfirst.frc.team4911.robot.CANTalon;
import org.usfirst.frc.team4911.robot.DoubleSolenoid;
import org.usfirst.frc.team4911.robot.Encoder;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.robot.Solenoid;
import java.io.*;
import com.google.gson.*;

/**
 * Functionality related to logging.
 * 
 * @author Anne Gwynne-Robson
 */
public class Logging {

	/**
	 * Prints to the RioLog window if RobotConstants.debug is set to true
	 */
	static public void DebugPrint(String message){
		if (RobotConstants.debug) {
			System.out.println(message);
		}
	}
	
	/**
	 * Writes an entry to the log file consisting of the given state serialized into
	 * a JSON blob.
	 * 
	 * @param state - The state to log
	 */
	public void LogRobotState(RobotState state) {
		try {
			// We never close the log file since there's only one and we stop
			// writing to it when we pull the plug on the bot so there isn't
			// really a chance to close it nicely.
			@SuppressWarnings("resource")
			PrintWriter logFileWriter = new PrintWriter("logfile.txt");
			
			Gson gson = new GsonBuilder().create();
			String jsonString = gson.toJson(state);
			logFileWriter.println(jsonString);
			logFileWriter.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
