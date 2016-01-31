package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * This class is used to compute angles
 * 
 * @author Luke Caughell
 *
 */
public class ComputeAngleHelper {
	
	
	public ComputeAngleHelper(){
		
	}
	/**
	 * This function takes 2 degrees (range -180 to 180),
	 * adds them together, normalizes the result to -180 to 180
	 * 
	 * @param startAngle range -180 to 180
	 * @param turnAngle range -180 to 180
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
	
}
