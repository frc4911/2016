package org.usfirst.frc.team4911.updators;

import java.util.ArrayList;

import org.usfirst.frc.team4911.controller.Button;
import org.usfirst.frc.team4911.controller.ControllerMappings;
import org.usfirst.frc.team4911.helpers.GetTargetAngleHelper;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.tasks.CycleTask;
import org.usfirst.frc.team4911.tasks.Drive;
import org.usfirst.frc.team4911.tasks.DriveForDegree;
import org.usfirst.frc.team4911.tasks.DriveForDistance;
import org.usfirst.frc.team4911.tasks.DriveForTime;
import org.usfirst.frc.team4911.tasks.OperatorDrive;
import org.usfirst.frc.team4911.tasks.SolenoidTrigger;
import org.usfirst.frc.team4911.tasks.SpinToEncoderValue;
import org.usfirst.frc.team4911.tasks.SpinToPower;
import org.usfirst.frc.team4911.tasks.SpinToRpm;
import org.usfirst.frc.team4911.tasks.Task;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

public class Inputs {    
    
	public static void init(){  
	}
	
	public static void update(){
		double rightPower = ControllerMappings.rightJoy.getY();
		double leftPower  = ControllerMappings.leftJoy.getY();
		//Logging.DebugPrint("rightJoy: " + rightPower);
		
		if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
			Robot.taskManager.addDriveTask(new OperatorDrive(leftPower, rightPower));
		}else{
			Robot.taskManager.addDriveTask(new Drive(0,0));
		}
		
		if(ControllerMappings.leftJukeButton1.getDown()){
			Logging.DebugPrint("test");
			Robot.taskManager.addDriveTask(new SolenoidTrigger(RobotMap.DriveLeftSolenoid,Value.kForward));
		}
		
		if(ControllerMappings.leftJukeButton2.getDown()){
			Logging.DebugPrint("test2");
			Robot.taskManager.addDriveTask(new SolenoidTrigger(RobotMap.DriveLeftSolenoid,Value.kReverse));		
		}
		
		if(ControllerMappings.leftJukeButton1.getDown()){
			Logging.DebugPrint("test");
			Robot.taskManager.addDriveTask(new SolenoidTrigger(RobotMap.DriveRightSolenoid,Value.kForward));
		}
		if(ControllerMappings.leftJukeButton2.getDown()){
			Logging.DebugPrint("test2");
			Robot.taskManager.addDriveTask(new SolenoidTrigger(RobotMap.DriveRightSolenoid,Value.kReverse));		
		}
		
		Robot.taskManager.addExtenderTask(new SpinToPower(RobotMap.ExtenderMotor,ControllerMappings.payloadJoy.getY(Hand.kLeft)));
		Logging.DebugPrint("JOY"+ControllerMappings.payloadJoy.getY(Hand.kLeft));
		
		//if(!ControllerMappings.leftJukeButton2.getDown() && !ControllerMappings.leftJukeButton2.getDown()){
			//Robot.taskManager.addDriveTask(new SolenoidTrigger(RobotMap.DriveLeftSolenoid,Value.kOff));		
		//}
	}
}
