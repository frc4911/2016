package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.GetTargetAngleHelper;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.helpers.RampDownHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.updators.Sensors;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForDegree extends Task{
	Timer timer;
	double driveTime;
	double power;
	double adjustedPower;
	double driveDistance;
	double currentEncoderValueInches;
	double currentDegree;
	double startDegree;
	double degreesToTurn;
	int i;
	PidHelper pid;
	Motor motor;
	
	public DriveForDegree(double _power, double _degreesToTurn, Motor _motor){

		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		power = _power;
		degreesToTurn = _degreesToTurn;
		motor = _motor;
		
	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
		pid = new PidHelper(1,0,0,0.5/180);
	   	timer = new Timer();
	   	timer.start();
	   	startDegree = Sensors.getImuYawValue();
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		
		currentDegree = Sensors.getImuYawValue();
		double angleDif = GetTargetAngleHelper.computeAngleBetween(startDegree, currentDegree);
		power = pid.run(1, angleDif / degreesToTurn, timer.get());

		RobotMap.DriveFrontRightTalon.set(power);
		RobotMap.DriveRearRightTalon.set(power);
		RobotMap.DriveFrontLeftTalon.set(power);
		RobotMap.DriveRearLeftTalon.set(power);
		
		if(pid.isFinished()){
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
	
	@Override
	public boolean getFinished() {
		return isFinished;
	}
}
