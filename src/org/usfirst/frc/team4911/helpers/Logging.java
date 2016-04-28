package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.*;

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
}
