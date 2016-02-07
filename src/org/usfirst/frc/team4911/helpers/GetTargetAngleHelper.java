package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * This class is used to compute angles
 * 
 * @author Luke Caughell
 *
 */
public class GetTargetAngleHelper {
	
	
	public GetTargetAngleHelper(){
		
	}
	
	/**
	 * This function takes 2 degrees (range -180 to 180),
	 * adds them together, normalizes the result to -180 to 180
	 * 
	 * @param startAngle range -180 to 180
	 * @param turnAngle number of degrees you want to turn
	 * @return
	 */
	public static double compute(double startAngle, double turnAngle){

		startAngle += turnAngle;
		if(startAngle > RobotConstants.maxAngle){
			while(startAngle > RobotConstants.maxAngle){
				startAngle -= 360;
			}
		}
		if(startAngle < RobotConstants.minAngle){
			while(startAngle < RobotConstants.minAngle){
				startAngle += 360;
			}
		}

		return startAngle;
	}
	
	public static double computeAngleBetween(double start, double current) {
		current -= start;
		if (current > RobotConstants.maxAngle) {
			current -= 360;
		}
		if (current < RobotConstants.minAngle) {
			current += 360;
		}
		
		return current;
		/*
		 * cur = 170, start = 170
		 * between = 0
		 * cur = -179, start = 170
		 * between = 11
		 */
	}
	
}
