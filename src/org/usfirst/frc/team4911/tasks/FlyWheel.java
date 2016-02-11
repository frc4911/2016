package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

public class FlyWheel extends Task{
	double rpm;
	SpinToRpm leftSpinToRpm;
	SpinToRpm rightSpinToRpm;

	
	public FlyWheel(double _rpm){
		rpm = _rpm;
		leftSpinToRpm= new SpinToRpm(RobotMap.ShooterLeftMotor, rpm,0.1);
		rightSpinToRpm= new SpinToRpm(RobotMap.ShooterLeftMotor, rpm,0.1);

	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
		leftSpinToRpm.init();
		rightSpinToRpm.init();
		
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		leftSpinToRpm.execute();
		rightSpinToRpm.execute();

		isFinished = true;
	}
	
	@Override
	public void end(){
		
	}
	
	@Override
	public boolean getFinished() {
		return isFinished;
	}
}
