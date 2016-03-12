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
import org.usfirst.frc.team4911.tasks.DoubleSolenoidTrigger;
import org.usfirst.frc.team4911.tasks.Drive;
import org.usfirst.frc.team4911.tasks.DriveForDegree;
import org.usfirst.frc.team4911.tasks.DriveForDistance;
import org.usfirst.frc.team4911.tasks.DriveForTime;
import org.usfirst.frc.team4911.tasks.DriveStraight;
import org.usfirst.frc.team4911.tasks.ManualScale;
import org.usfirst.frc.team4911.tasks.OperatorDrive;
import org.usfirst.frc.team4911.tasks.SolenoidTrigger;
import org.usfirst.frc.team4911.tasks.SpinToEncoderValue;
import org.usfirst.frc.team4911.tasks.SpinToPotentiometerValue;
import org.usfirst.frc.team4911.tasks.SpinToPower;
import org.usfirst.frc.team4911.tasks.SpinToRpm;
import org.usfirst.frc.team4911.tasks.Task;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Inputs {
	private static CycleTask extenderCycle;
	private static CycleTask rollerCycle;

	static boolean modeToggle = true;
	static boolean extenderToggle = true;
	static boolean rollerToggle = true;

	static boolean testToggle = true;
	
	static DriveStraight driveStraightTask = new DriveStraight(0,false);
	static DriveStraight driveReverseTask = new DriveStraight(0,true);

	public static void init(){
		initCycleTasks();
	}
	
	public static void update(){
		double rightPower = ControllerMappings.rightJoy.getY();
		double leftPower  = ControllerMappings.leftJoy.getY();
		//Logging.DebugPrint("rightJoy: " + rightPower);
		
		
		if(ControllerMappings.modeButton.getDown()){
			modeToggle = !modeToggle;
		}
		SmartDashboard.putBoolean("Mode", modeToggle);
		
		if (modeToggle){
			/**
			 * DRIVE CONTROLS
			 */
			
			if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
				Robot.taskManager.addDriveTask(new OperatorDrive(-leftPower, -rightPower));
			}else{
				Robot.taskManager.addDriveTask(new Drive(0,0));
			}
			if(ControllerMappings.rightJukeButton1.getDown()){
				Robot.taskManager.addDriveTask(new DriveForDegree(90,1,true));;
			}
			if(ControllerMappings.rightJukeButton2.getDown()){
				Robot.taskManager.addDriveTask(new DriveForDegree(-90,1,false));;
			}
			
			if(ControllerMappings.leftJukeButton1.getDown()){
				driveStraightTask.setTargetHeading(Sensors.getImu().getYaw());
				Robot.taskManager.addDriveTask(driveStraightTask);
				System.out.println("down");
			}
			if(ControllerMappings.leftJukeButton1.getUp()){
				driveStraightTask.setFinished(true);
				System.out.println("up");
			}
			
			if(ControllerMappings.leftJukeButton2.getDown()){
				driveReverseTask.setTargetHeading(Sensors.getImu().getYaw());
				Robot.taskManager.addDriveTask(driveReverseTask);
				System.out.println("down");
			}
			if(ControllerMappings.leftJukeButton2.getUp()){
				driveReverseTask.setFinished(true);
				System.out.println("up");
			}
			
			Robot.taskManager.addExtenderTask(new SpinToPower(RobotMap.ExtenderMotor,ControllerMappings.payloadJoy.getY(Hand.kLeft)/5));
			
			/**
			 * EXTENDER CONTROLS
			 */
			if(ControllerMappings.extenderCycleUpButton.getDown()){
				extenderCycle.CycleUp();
				Robot.taskManager.addExtenderTask(extenderCycle);
			}
			if(ControllerMappings.extenderCycleDownButton.getDown()){
				extenderCycle.CycleDown();
				Robot.taskManager.addExtenderTask(extenderCycle);
			}
			if(ControllerMappings.extenderExtendButton.getDown()){
				RobotMap.ExtenderSolenoid.set(extenderToggle);
				extenderToggle = !extenderToggle;
			}
			
			/**
			 * ROLLER CONTROLS
			 */
			if(ControllerMappings.rollerCycleDownButton.getDown()){
				rollerCycle.CycleUp();
				Robot.taskManager.addRollerTask(rollerCycle);
			}
			if(ControllerMappings.rollerCycleDownButton.getDown()){
				rollerCycle.CycleDown();
				Robot.taskManager.addRollerTask(rollerCycle);		}
			if(RobotMap.ExtenderPotentiometer.get()<RobotConstants.ExtenderPotentiometerZero-RobotConstants.ExtenderWheelClearnace
			&& ControllerMappings.extenderExtendButton.getDown()){
				RobotMap.ExtenderSolenoid.set(extenderToggle);
				extenderToggle = !extenderToggle;
			}
	
			/**
			 * DRIVE SHIFTING
			 */
			//TODO: set shifting to activate for current draw or rpm
			if(ControllerMappings.rightJukeButton1.getDown()){
				RobotMap.DriveSolenoid.set(Value.kForward);
			}
			if(ControllerMappings.rightJukeButton2.getDown()){
				RobotMap.DriveSolenoid.set(Value.kReverse);
			}
			
			/**
			 * ROLLER CONTROLER
			 */
			Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerBarMotor,-ControllerMappings.payloadJoy.getRawAxis(5)*.75));
			//This sets the brakes to open if the power signal to the roller talon is greater than 0.1
			if(Math.abs(RobotMap.RollerBarMotor.getTalon().get())<0.1){
				RobotMap.RollerBarSolenoid.set(false);
			}else{
				RobotMap.RollerBarSolenoid.set(true);
			}
			if (ControllerMappings.rollerRoller.getDown()){
				if (rollerToggle){
					RobotMap.RollerRollerMotor.setPower(0.3);
				} else {
					RobotMap.RollerRollerMotor.setPower(0);
				}
				rollerToggle = !rollerToggle;
			}
		} else {
			/**
			 * SCALE MODE CODE
			 */
			if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
				Robot.taskManager.addDriveTask(new OperatorDrive(-leftPower, -rightPower));
			}else{
				Robot.taskManager.addDriveTask(new Drive(0,0));
			}
			
			Robot.taskManager.addScaleTask(new ManualScale(ControllerMappings.payloadJoy.getRawAxis(1),ControllerMappings.payloadJoy.getRawAxis(5)));
			
		}
	}
	


	
	public static void initCycleTasks(){
		extenderCycle = new CycleTask(new Task[]{
			new SpinToPotentiometerValue(RobotMap.ExtenderMotor,RobotConstants.ExtenderPotentiometerZero , 0.5,1),
			new SpinToPotentiometerValue(RobotMap.ExtenderMotor, RobotConstants.ExtenderPotentiometerZero - GetTargetAngleHelper.degreesToPotentiometerValue(19), 0.5,1),
			new SpinToPotentiometerValue(RobotMap.ExtenderMotor, RobotConstants.ExtenderPotentiometerZero - GetTargetAngleHelper.degreesToPotentiometerValue(35), 0.5,1),
		});
		//TODO: Set to proper degrees and tune pid
		rollerCycle = new CycleTask(new Task[]{
			new SpinToPotentiometerValue(RobotMap.RollerBarMotor,RobotConstants.RollerPotentiometerZero , 0.5,1),
			new SpinToPotentiometerValue(RobotMap.RollerBarMotor, RobotConstants.RollerPotentiometerZero - GetTargetAngleHelper.degreesToPotentiometerValue(19), 0.5,1),
			new SpinToPotentiometerValue(RobotMap.RollerBarMotor, RobotConstants.RollerPotentiometerZero - GetTargetAngleHelper.degreesToPotentiometerValue(35), 0.5,1),
		});
	}
	
	public static boolean getMode(){
		return modeToggle;
	}
}
