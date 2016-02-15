package org.usfirst.frc.team4911.tasks;

import java.util.ArrayList;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.robot.RobotMap;
/**
 * Task for basic operator tank drive.
 * 
 * @author Luke Caughell
 */
public class OperatorDrive extends Task{
	double leftPower;
	double rightPower;
	
	Drive driveTask;
	ArrayList<Motor> motors;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param leftPower the motor power for the left side drive
	 * @param rightPower the motor power for the right side drive
	 */
	public OperatorDrive(double leftPower, double rightPower){
		this.leftPower = leftPower;
		this.rightPower = rightPower;
		driveTask = new Drive(0, 0);
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
		
		driveTask.drive(leftPower, rightPower);
		isFinished = true;
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
	}
}