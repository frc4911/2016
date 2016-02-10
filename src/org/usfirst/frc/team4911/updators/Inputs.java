package org.usfirst.frc.team4911.updators;

import org.usfirst.frc.team4911.helpers.GetTargetAngleHelper;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.tasks.Drive;
import org.usfirst.frc.team4911.tasks.DriveForDegree;
import org.usfirst.frc.team4911.tasks.DriveForDistance;
import org.usfirst.frc.team4911.tasks.DriveForTime;
import org.usfirst.frc.team4911.tasks.OperatorDrive;
import org.usfirst.frc.team4911.tasks.SpinToEncoderValue;
import org.usfirst.frc.team4911.tasks.SpinToRpm;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    public static Joystick payloadJoy;
    
	public static void init(){
		rightJoy = new Joystick(RobotConstants.rightJoyPort);
		leftJoy = new Joystick(RobotConstants.leftJoyPort);
	    payloadJoy = new Joystick(RobotConstants.payloadJoyPort);
	}
	
	public static void update(){
		double rightPower = Inputs.rightJoy.getY();
		double leftPower  = Inputs.leftJoy.getY();
		//Logging.DebugPrint("rightJoy: " + rightPower);
		
		if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
			Robot.taskManager.addDriveTask(new OperatorDrive(leftPower, rightPower));
		}else{
			Robot.taskManager.addDriveTask(new Drive(0));
		}
		

		if(rightJoy.getRawButton(12)){
			Robot.taskManager.addDriveTask(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor, 140, 0.5));
		}
		if(rightJoy.getRawButton(11)){
			Robot.taskManager.addDriveTask(new SpinToRpm(RobotMap.DriveFrontRightMotor, 57,0.3));
		}
		
		

	}
}
