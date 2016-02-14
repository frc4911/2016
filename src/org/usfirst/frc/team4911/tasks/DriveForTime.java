package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Task to drive for an amount of time.
 * 
 * @author Luke Caughell
 */
public class DriveForTime extends Task{
	Timer timer;
	double driveTime;
	double power;
	double endTime;
	Command teleop;
	int i;
	Motor motor;
	
	/**
	 * Constructor
	 * Sets class variables.
	 * 
	 * @param _power the power to use for driving.
	 * @param _time the amount of time for which to drive.
	 * @param _motor the motor object to drive.
	 */
	public DriveForTime(double _power, double _time, Motor _motor){
		this.priority = RobotConstants.LOW_PRI;
		
		power = _power;
		endTime = _time;
		motor = _motor;
	}

	/**
	 * This is called when the command is first added to the task manager.
	 */
	@Override
	public void init(){
		timer = new Timer();
		timer.start();
	}
	
	/**
	 * This is called constantly called by the task manager.
	 */
	@Override
	public void execute(){
		RobotMap.DriveFrontRightTalon.set(-power);
		RobotMap.DriveRearRightTalon.set(-power);
		RobotMap.DriveFrontLeftTalon.set(power);
		RobotMap.DriveRearLeftTalon.set(power);
		
		if(timer.get() > endTime){
			isFinished = true;
		}
	}
	
	/**
	 * Called when the task has completed.
	 */
	@Override
	public void end(){
		RobotMap.DriveFrontLeftTalon.set(0);
		RobotMap.DriveFrontRightTalon.set(0);
		RobotMap.DriveRearLeftTalon.set(0);
		RobotMap.DriveRearRightTalon.set(0);
	}
}
