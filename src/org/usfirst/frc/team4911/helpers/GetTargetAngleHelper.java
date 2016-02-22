package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * This class is used to compute target angles.
 * 
 * @author Luke Caughell
 *
 */
public class GetTargetAngleHelper {
	
	/**
	 * Empty Constructor
	 */
	public GetTargetAngleHelper(){
	}
	
	/**
	 * This function takes 2 degrees (range -180 to 180),
	 * adds them together, normalizes the result to -180 to 180.
	 * 
	 * @param startAngle range -180 to 180
	 * @param turnAngle number of degrees you want to turn
	 * @return the final computed angle, normalized to -180 to 180
	 */
	public static double compute(double startAngle, double turnAngle){
		startAngle += turnAngle;
		
		if(startAngle > RobotConstants.maxAngle){
			while(startAngle > RobotConstants.maxAngle){
				startAngle -= 360;
			}
		}
		else if(startAngle < RobotConstants.minAngle){
			while(startAngle < RobotConstants.minAngle){
				startAngle += 360;
			}
		}

		return startAngle;
	}
	
	/**
	 * Computes the angle between two given angle values.
	 * Normalizes the result angle to -180 to 180.
	 * 
	 * @param start the starting position of the turn
	 * @param current the current position in the turn
	 * @return the computed angle traveled from start to current, normalized to -180 to 180
	 */
	public static double computeAngleBetween(double start, double current) {
		current -= start;
		if (current > RobotConstants.maxAngle) {
			current -= 360;
		}
		else if (current < RobotConstants.minAngle) {
			current += 360;
		}
		
		return current;
	}
	
	public static double potentiometerValueToDegrees(double start, double current) {
		return (current-start)*3600;
	}
	
	public static double degreesToPotentiometerValue(double degree) {
			return (degree)*(0.1/360);
	}
	
	
}
