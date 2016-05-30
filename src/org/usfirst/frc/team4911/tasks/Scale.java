package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

/**
 * Task for scaling the robot in end game.
 * 
 * @author Luke Caughell
 */
public class Scale extends Task{
	double encoderValue;
	SpinToEncoderValue leftSpinToEncoderValue;
	SpinToEncoderValue rightSpinToEncoderValue;

	/**
	 * Constructor
	 * Sets the class variables.
	 * @param _encoderValue the goal encoder distance value
	 */
	public Scale(double _encoderValue){
		encoderValue = _encoderValue;
		leftSpinToEncoderValue = new SpinToEncoderValue(RobotMap.ShooterLeftMotor, _encoderValue, 0.3);
		rightSpinToEncoderValue = new SpinToEncoderValue(RobotMap.ShooterRightMotor, _encoderValue, 0.3);
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		leftSpinToEncoderValue.init();
		rightSpinToEncoderValue.init();	
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		leftSpinToEncoderValue.execute();
		rightSpinToEncoderValue.execute();
		
		if (leftSpinToEncoderValue.isFinished && rightSpinToEncoderValue.isFinished) {
			isFinished = true;
		}
	}
	
	/**
	 * Called when the task finishes.
	 */
	@Override
	public void end(){	
	}
}