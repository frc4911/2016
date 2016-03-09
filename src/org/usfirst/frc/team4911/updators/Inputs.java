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

public class Inputs {
	private static CycleTask extenderCycle;
	private static CycleTask rollerCycle;

	static boolean extenderToggle = true;
	
	public static void init(){
		initCycleTasks();
	}
	
	public static void update(){
		double rightPower = ControllerMappings.rightJoy.getY();
		double leftPower  = ControllerMappings.leftJoy.getY();
		//Logging.DebugPrint("rightJoy: " + rightPower);
		
		/**
		 * DRIVE CONTROLS
		 */
		if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
			Robot.taskManager.addDriveTask(new OperatorDrive(-leftPower, -rightPower));
		}else{
			Robot.taskManager.addDriveTask(new Drive(0,0));
		}
		if(ControllerMappings.leftJukeButton1.getDown()){
			Robot.taskManager.addDriveTask(new DriveForDegree(90));;
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
		if(RobotMap.ExtenderPotentiometer.get()<RobotConstants.ExtenderPotentiometerZero-RobotConstants.ExtenderWheelClearnace
		&& ControllerMappings.extenderExtendButton.getDown()){
			RobotMap.ExtenderSolenoid.set(extenderToggle);
			extenderToggle = !extenderToggle;
		}
		
		/**
		 * ROLLER CONTROLS
		 */
		if(ControllerMappings.rollerCycleDownButton.getDown()){
			extenderCycle.CycleUp();
			Robot.taskManager.addExtenderTask(extenderCycle);
		}
		if(ControllerMappings.rollerCycleDownButton.getDown()){
			extenderCycle.CycleDown();
			Robot.taskManager.addExtenderTask(extenderCycle);
		}
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
		Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerBarMotor,ControllerMappings.payloadJoy.getRawAxis(5)*.75));
		//This sets the brakes to open if the power signal to the roller talon is greater than 0.1
		if(Math.abs(RobotMap.RollerBarMotor.getTalon().get())<0.1){
			RobotMap.RollerBarSolenoid.set(false);
		}else{
			RobotMap.RollerBarSolenoid.set(true);
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
			new SpinToPotentiometerValue(RobotMap.ExtenderMotor,RobotConstants.RollerPotentiometerZero , 0.5,1),
			new SpinToPotentiometerValue(RobotMap.ExtenderMotor, RobotConstants.RollerPotentiometerZero - GetTargetAngleHelper.degreesToPotentiometerValue(19), 0.5,1),
			new SpinToPotentiometerValue(RobotMap.ExtenderMotor, RobotConstants.RollerPotentiometerZero - GetTargetAngleHelper.degreesToPotentiometerValue(35), 0.5,1),
		});
	}
}
