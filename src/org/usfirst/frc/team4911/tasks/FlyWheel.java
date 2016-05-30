package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

/**
 * Task for driving the flwheel to shoot the ball-er-boulder.
 * 
 * @author Luke Caughell
 */
public class FlyWheel extends Task{
	double rpm;
	SpinToRpm leftSpinToRpm;
	SpinToRpm rightSpinToRpm;

	/**
	 * Constructor
	 * Sets class variables.
	 * @param _rpm the flywheel rpm to target
	 */
	public FlyWheel(double _rpm){
		rpm = _rpm;
		leftSpinToRpm= new SpinToRpm(RobotMap.ShooterLeftMotor, rpm, 0.1);
		rightSpinToRpm= new SpinToRpm(RobotMap.ShooterLeftMotor, rpm, 0.1);
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		leftSpinToRpm.init();
		rightSpinToRpm.init();
	}
	
	/**
	 * This is called constantly called by the task manager.
	 */
	@Override
	public void execute(){
		leftSpinToRpm.execute();
		rightSpinToRpm.execute();

		isFinished = true;
	}
	
	/**
	 * Called when the task has completed.
	 */
	@Override
	public void end(){	
	}
}