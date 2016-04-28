package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.robot.RobotMap;

/**
 * Spins a given motor at a given power with no correction
 * 
 * @author Luke Caughell
 */
public class SpinToPower extends Task {
	Motor motor;
	double power;
	
	/**
	 * Constructor
	 * 
	 * @param _motor the motor to set the power for
	 * @param _power the power to spin the motor to
	 */
	public SpinToPower(Motor _motor, double _power){
		motor = _motor;
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
		motor.setPower(power);
		isFinished = true;
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
	}
}