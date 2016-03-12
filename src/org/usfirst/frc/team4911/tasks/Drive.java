package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;

/**
 * Basic task for driving.
 * Sets a motor to the given power.
 * 
 * @author Luke Caughell
 */
public class Drive extends Task{
	double leftPower;
	double rightPower;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param _power the power to set for the motor
	 */
	public Drive(double _leftPower,double _rightPower){
		leftPower = _leftPower;
		rightPower = _rightPower;
		this.priority = RobotConstants.LOW_PRI;
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
		RobotMap.DriveFrontLeftMotor.setPower(-leftPower);
		RobotMap.DriveMidLeftMotor.setPower(-leftPower);
		RobotMap.DriveRearLeftMotor.setPower(-leftPower);
		RobotMap.DriveFrontRightMotor.setPower(rightPower);
		RobotMap.DriveMidRightMotor.setPower(rightPower);
		RobotMap.DriveRearRightMotor.setPower(rightPower);
		isFinished = true;
	}
	
	public void drive(double _leftPower,double _rightPower){
		leftPower =_leftPower;
		rightPower = _rightPower;
		execute();
	}
	
	/**
	 * Called when the task has ended.
	 */
	@Override
	public void end(){
	}
	
	public void setLeftPower(double power){
		leftPower = power;
	}
	public void setRightPower(double power){
		rightPower = power;
	}
	public void setPower(double power){
		rightPower = power;
		leftPower = power;
	}
}
