// File="PowerOutputHelper.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * Used to control the power percentages of each motor
 * controlling the winches.
 * 
 * @author Tommy Lee
 */
public class PowerOutputHelper {

	static double winchLeftMotorPower = RobotConstants.winchLeftMotorPower;
	static double winchRightMotorPower = RobotConstants.winchRightMotorPower;
	
	/**
	 * Constructor that sets all of the parameters of the power output method.
	 * @param _winchLeftMotorPower
	 * @param _winchRightMotorPower
	 */
	public PowerOutputHelper(double _winchLeftMotorPower, double _winchRightMotorPower) {
		
		winchLeftMotorPower = _winchLeftMotorPower;
		winchRightMotorPower = _winchRightMotorPower;
	}
	
	public PowerOutputHelper() {
	
	}
	
	/**
	 * Returns power for the left winch motor.
	 * @return winchLeftMotorPower
	 */
	public static double getLeftPowerOutput() {
		
		return winchLeftMotorPower;
	}
	
	/**
	 * Returns power for the right winch motor.
	 * @return winchRightMotor
	 */
	public static double getRightPowerOutput() {
		
		return winchRightMotorPower;
	}
}
