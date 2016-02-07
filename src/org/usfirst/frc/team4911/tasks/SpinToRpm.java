package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

public class SpinToRpm extends Task {
	double power;
	
	public SpinToRpm(double _power){
		power = _power;
	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
		
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		RobotMap.DriveFrontRightTalon.set(-power);
		RobotMap.DriveRearRightTalon.set(-power);
		RobotMap.DriveFrontLeftTalon.set(power);
		RobotMap.DriveRearLeftTalon.set(power);
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
