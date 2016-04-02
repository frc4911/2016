package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

/**
 * Spins a given motor at a given power with no correction
 * 
 * @author Luke Caughell
 */
public class SpinForTime extends Task {
	Motor motor;
	double power;
	double endTime;
	Timer timer;
	/**
	 * Constructor
	 * 
	 * @param _motor the motor to set the power for
	 * @param _power the power to spin the motor to
	 */
	public SpinForTime(Motor _motor, double _power , double _endTime){
		motor = _motor;
		power = _power;
		timer = new Timer();
		endTime = _endTime;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		timer.reset();
		timer.start();
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		motor.setPower(power);
		if(timer.get() > endTime){
			isFinished = true;
		}
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
	}
}