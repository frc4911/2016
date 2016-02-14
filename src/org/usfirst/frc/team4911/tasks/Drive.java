package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

/**
 * Basic task for driving.
 * Sets a motor to the given power.
 * 
 * @author Luke Caughell
 */
public class Drive extends Task{
	double power;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param _power the power to set for the motor
	 */
	public Drive(double _power){
		power = _power;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		RobotMap.DriveFrontRightTalon.set(-power);
		RobotMap.DriveRearRightTalon.set(-power);
		RobotMap.DriveFrontLeftTalon.set(power);
		RobotMap.DriveRearLeftTalon.set(power);
		isFinished = true;
	}
	
	/**
	 * Called when the task has ended.
	 */
	@Override
	public void end(){
	}
}
