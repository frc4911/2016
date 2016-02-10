package org.usfirst.frc.team4911.tasks;

import java.util.ArrayList;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.robot.RobotMap;

public class OperatorDrive extends Task{
	double leftPower;
	double rightPower;
	
	ArrayList<Motor> motors;
	
	
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
		RobotMap.DriveFrontLeftMotor.getTalon().set(-leftPower);
		RobotMap.DriveRearLeftMotor.getTalon().set(-leftPower);
		RobotMap.DriveFrontRightMotor.getTalon().set(rightPower);
		RobotMap.DriveRearRightMotor.getTalon().set(rightPower);

		isFinished = true;
	}
	
	@Override
	public void end(){
	}
}
