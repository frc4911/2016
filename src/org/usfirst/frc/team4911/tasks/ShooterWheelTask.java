package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Motor;

public class ShooterWheelTask extends Task {
	/**
	 * Spins a given motor at a given power with no correction
	 * 
	 * @author Luke Caughell
	 */
	Motor leftMotor;
	Motor rightMotor;

	double power;
	
	/**
	 * Constructor
	 * 
	 * @param _motor the motor to set the power for
	 * @param _power the power to spin the motor to
	 */
	public ShooterWheelTask(Motor _rightMotor,Motor _leftMotor, double _power){
		leftMotor = _leftMotor;
		rightMotor = _rightMotor;
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
		leftMotor.setPower(power);
		rightMotor.setPower(-power);

		isFinished = true;
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
//		leftMotor.setPower(0);
//		rightMotor.setPower(0);
	}
}
