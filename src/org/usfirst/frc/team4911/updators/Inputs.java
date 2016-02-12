package org.usfirst.frc.team4911.updators;

import java.util.ArrayList;

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
import org.usfirst.frc.team4911.tasks.SpinToEncoderValue;
import org.usfirst.frc.team4911.tasks.SpinToRpm;
import org.usfirst.frc.team4911.tasks.Task;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class Inputs {
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    public static Joystick payloadJoy;
	static boolean pressed5 = false;
	static boolean pressed6 = false;

	
    static CycleTask cycleTaskTest;
    static ArrayList<Task> cycleTaskTestList;
    
	public static void init(){
		rightJoy = new Joystick(RobotConstants.rightJoyPort);
		leftJoy = new Joystick(RobotConstants.leftJoyPort);
	    payloadJoy = new Joystick(RobotConstants.payloadJoyPort);
	    
	    cycleTaskTestList = new ArrayList<Task>();
	    cycleTaskTestList.add(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,0,0.3));
	    cycleTaskTestList.add(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,-20,0.3));
	    cycleTaskTestList.add(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,-87,0.3));
	    cycleTaskTestList.add(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor,-218-500,0.3));
	    cycleTaskTest = new CycleTask(cycleTaskTestList);
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
		
		
		if(payloadJoy.getRawButton(5)  && pressed5 == false){
			cycleTaskTest.CycleUp();
		}
		pressed5 = payloadJoy.getRawButton(5);

		
		if(payloadJoy.getRawButton(6) && pressed6 == false){
			cycleTaskTest.CycleDown();
		}
		pressed6 = payloadJoy.getRawButton(6);
		
		Robot.taskManager.addDriveTask(cycleTaskTest);

		if(rightJoy.getRawButton(12)){
			Robot.taskManager.addDriveTask(new SpinToEncoderValue(RobotMap.DriveFrontRightMotor, 140, 0.5));
		}
		if(rightJoy.getRawButton(11)){
			Robot.taskManager.addDriveTask(new SpinToRpm(RobotMap.DriveFrontRightMotor, 57,0.3));
		}
		

	}
}
