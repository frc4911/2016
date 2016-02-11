package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

public class Scale extends Task{
	double encoderValue;
	SpinToEncoderValue leftSpinToEncoderValue;
	SpinToEncoderValue rightSpinToEncoderValue;

	
	public Scale(double _encoderValue){
		encoderValue = _encoderValue;
		leftSpinToEncoderValue = new SpinToEncoderValue(RobotMap.ShooterLeftMotor, _encoderValue,0.3);
		rightSpinToEncoderValue = new SpinToEncoderValue(RobotMap.ShooterRightMotor, _encoderValue,0.3);

	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
		leftSpinToEncoderValue.init();
		rightSpinToEncoderValue.init();
		
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		leftSpinToEncoderValue.execute();
		rightSpinToEncoderValue.execute();
		if (leftSpinToEncoderValue.isFinished&&rightSpinToEncoderValue.isFinished){
			isFinished = true;
		}
	}
	
	@Override
	public void end(){
		
	}
	
	@Override
	public boolean getFinished() {
		return isFinished;
	}
}
