package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.RampDownHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.updators.Sensors;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForDistance extends Task{
	Timer timer;
	double driveTime;
	double power;
	double adjustedPower;
	double driveDistance;
	double currentEncoderValueInches;
	Command teleop;
	RampDownHelper rampDown;
	int i;
	
	public DriveForDistance(double _power, double _driveDistance){
		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		
		power = _power;
		driveDistance = _driveDistance;
	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
	   	rampDown = new RampDownHelper();
	   	Sensors.getRightDriveEncoder().reset();
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		
		currentEncoderValueInches = Sensors.getDriveRightEncoderDistanceValue();
		Logging.DebugPrint("" + Sensors.getDriveRightEncoderDistanceValue());
		power = rampDown.getRampedPower(driveDistance,currentEncoderValueInches);
		
		RobotMap.DriveFrontRightTalon.set(-power);
		RobotMap.DriveRearRightTalon.set(-power);
		RobotMap.DriveFrontLeftTalon.set(power);
		RobotMap.DriveRearLeftTalon.set(power);
		
		if((driveDistance - Math.abs(currentEncoderValueInches)<=0)){
			isFinished = true;
		}
		
	}
	
	@Override
	public void end(){
		RobotMap.DriveFrontLeftTalon.set(0);
		RobotMap.DriveFrontRightTalon.set(0);
		RobotMap.DriveRearLeftTalon.set(0);
		RobotMap.DriveRearRightTalon.set(0);
	}
}
