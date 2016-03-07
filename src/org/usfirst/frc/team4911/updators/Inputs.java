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
import org.usfirst.frc.team4911.tasks.SpinToRpm;
import org.usfirst.frc.team4911.tasks.Task;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    public static Joystick payloadJoy;
	static boolean pressed5 = false;
	static boolean pressed6 = false;

	
    static CycleTask cycleTaskTest;
    
    static Task[] cycleTaskTestArray = {
    		new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,0,0.3),
    		new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,-20,0.3),
    		new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,-87,0.3),
    		new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,-218-500,0.3)
	};
    
	public static void init(){
		rightJoy = new Joystick(RobotConstants.rightJoyPort);
		leftJoy = new Joystick(RobotConstants.leftJoyPort);
	    payloadJoy = new Joystick(RobotConstants.payloadJoyPort);
	    cycleTaskTest = new CycleTask(cycleTaskTestArray);
	    
	    
	}
	
	public static void update(){
		double rightPower = ControllerMappings.payloadJoy.getRawAxis(1);
		double leftPower = ControllerMappings.payloadJoy.getRawAxis(5);
		//Logging.DebugPrint("rightJoy: " + rightPower);
		
		if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
			Robot.taskManager.addDriveTask(new OperatorDrive(leftPower/3, rightPower/3));
		}else{
			Robot.taskManager.addDriveTask(new Drive(0));
		}
		
		
		if (ControllerMappings.button5.getDown()){
			Robot.taskManager.addDriveTask(new DriveForDegree(90));
			//Robot.taskManager.addDriveTask(new DriveForDegree(GetTargetAngleHelper.compute(Sensors.getImuYawValue(), 200)));

		}
		Logging.DebugPrint(""+Sensors.getImuYawValue());
		
//		if (ControllerMappings.button6.getDown()){
//			
//		}
		
		
//		 if (payloadjoy.getshootbutton)
//			taskmanager.addshoottask(new shoot task)
		
		Button button12 = new Button(rightJoy,12);
		button12.getDown();
		
		//Logging.DebugPrint(""+ControllerMappings.button5.getDown());
		
		if(rightJoy.getRawButton(12)){
			Robot.taskManager.addDriveTask(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor, 140, 0.5));
		}
		if(rightJoy.getRawButton(11)){
			Robot.taskManager.addDriveTask(new SpinToRpm(RobotMap.DriveFrontRightMotor, 57,0.3));
		}
		
		if (payloadJoy.getRawButton(1)){
			Robot.taskManager.addArmTask(new SolenoidTrigger(RobotMap.ExtenderSolenoid,true));
		}
		if (payloadJoy.getRawButton(2)){
			Robot.taskManager.addArmTask(new SolenoidTrigger(RobotMap.ExtenderSolenoid,false));
		}

	}
}
