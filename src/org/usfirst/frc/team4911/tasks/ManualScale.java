package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.robot.RobotMap;

/**
 * Spins a given motor at a given power with no correction
 * 
 * @author Luke Caughell
 */
public class ManualScale extends Task {
	Motor leftMotor;
	Motor rightMotor;
	double power;
	double leftPower;
	double rightPower;

	/**
	 * Constructor
	 * 
	 * @param _motor the motor to set the power for
	 * @param _power the power to spin the motor to
	 */
	public ManualScale(double _rightPower, double _leftPower){
		leftPower = _rightPower;
		rightPower = _leftPower;
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
		RobotMap.ScaleLeftMotor.setPower(leftPower);
		RobotMap.ScaleRightMotor.setPower(rightPower);

		isFinished = true;
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
	}
	
	public void setPower(double _leftPower, double _rightPower){
		leftPower = _leftPower;
		rightPower = _rightPower;
	}
}