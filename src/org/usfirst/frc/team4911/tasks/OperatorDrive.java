package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

public class OperatorDrive extends Task{
	double leftPower;
	double rightPower;
	
	
	public OperatorDrive(double leftPower, double rightPower){
		this.leftPower = leftPower;
		this.rightPower = rightPower;
	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
			
	}
		
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		RobotMap.DriveFrontRightTalon.set(rightPower);
		RobotMap.DriveRearRightTalon.set(rightPower);
		RobotMap.DriveFrontLeftTalon.set(-leftPower);
		RobotMap.DriveRearLeftTalon.set(-leftPower);
		isFinished = true;
	}
	
	@Override
	public void end(){
	}
}
