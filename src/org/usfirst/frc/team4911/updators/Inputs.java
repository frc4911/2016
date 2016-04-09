package org.usfirst.frc.team4911.updators;

import java.util.ArrayList;

import org.usfirst.frc.team4911.controller.Button;
import org.usfirst.frc.team4911.controller.ControllerMappings;
import org.usfirst.frc.team4911.helpers.ClampHelper;
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
import org.usfirst.frc.team4911.tasks.ShooterWheelTask;
import org.usfirst.frc.team4911.tasks.SolenoidTrigger;
import org.usfirst.frc.team4911.tasks.SpinToEncoderValue;
import org.usfirst.frc.team4911.tasks.SpinToPotentiometerValue;
import org.usfirst.frc.team4911.tasks.SpinToPower;
import org.usfirst.frc.team4911.tasks.SpinToRpm;
import org.usfirst.frc.team4911.tasks.SpinToTalonValue;
import org.usfirst.frc.team4911.tasks.Task;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Inputs {
	private static CycleTask shooterCycle;
	private static CycleTask armCycle;

	static boolean modeToggle = true;
	static boolean extenderToggle = true;
	static boolean payloadManualOverride = false;
	static boolean rollerToggle = true;

	static double rollerPower;
	static Timer rollerTimer;
	
	static Timer shooterTimer;
	
	static boolean testToggle = true;
	
	static DriveStraight driveStraightTask = new DriveStraight(0,0, false);
//	static DriveStraight driveReverseTask = new DriveStraight(0,true);

	public static void init(){
		modeToggle = true;
		payloadManualOverride = false;
		shooterTimer = new Timer();
		rollerTimer = new Timer();
		shooterTimer.start();

		initCycleTasks();
	}
	
	public static void update(){
		double rightPower = ControllerMappings.rightJoy.getY();
		double leftPower  = ControllerMappings.leftJoy.getY();
		//Logging.DebugPrint("rightJoy: " + rightPower);
		
		
		if(ControllerMappings.modeButton.getDown()){
			modeToggle = !modeToggle;
		}
		if(ControllerMappings.payloadManual.getDown()){
			payloadManualOverride = !payloadManualOverride;
		}
		SmartDashboard.putBoolean("Mode", modeToggle);
		
		if (modeToggle){
			/**
			 * DRIVE CONTROLS
			 */
			
			if(ControllerMappings.ZeroYaw.getDown()){
				Sensors.getImu().zeroYaw();
			}
			
			if(Math.abs(leftPower) > RobotConstants.JoyThreshold || Math.abs(rightPower) > RobotConstants.JoyThreshold) {
				Logging.DebugPrint("LEFT: "+ -leftPower +" RIGHT: " + -rightPower);
				Robot.taskManager.addDriveTask(new OperatorDrive(-leftPower, -rightPower));
			}else{
				Robot.taskManager.addDriveTask(new Drive(0,0));
			}
//			if(ControllerMappings.rightJukeButton1.getDown()){
//				Robot.taskManager.addDriveTask(new Drive(1,1));
//			}
			if(ControllerMappings.rightJukeButton2.getDown()){
				Robot.taskManager.addDriveTask(new DriveForDegree(-90,1,false));
			}
			
			if(ControllerMappings.rightJukeButton1.getDown()){
				driveStraightTask.setTargetHeading(Sensors.getImu().getYaw());
				Robot.taskManager.addDriveTask(driveStraightTask);
			}
			if(ControllerMappings.rightJukeButton1.getUp()){
				driveStraightTask.setFinished(true);
			}
//			
//			if(ControllerMappings.leftJukeButton2.getDown()){
//				driveReverseTask.setTargetHeading(Sensors.getImu().getYaw());
//				Robot.taskManager.addDriveTask(driveReverseTask);
//			}
//			if(ControllerMappings.leftJukeButton2.getUp()){
//				driveReverseTask.setFinished(true);
//			}
			
			Robot.taskManager.addExtenderTask(new SpinToPower(RobotMap.ExtenderMotor,ControllerMappings.payloadJoy.getY(Hand.kLeft)/5));
			
			/**
			 * EXTENDER CONTROLS
			 */
//			if(ControllerMappings.extenderCycleUpButton.getDown()){
//				extenderCycle.CycleUp();
//				Robot.taskManager.addExtenderTask(extenderCycle);
//			}
//			if(ControllerMappings.extenderCycleDownButton.getDown()){
//				extenderCycle.CycleDown();
//				Robot.taskManager.addExtenderTask(extenderCycle);
//			}
//			if(ControllerMappings.extenderExtendButton.getDown()){
//				RobotMap.ExtenderSolenoid.set(extenderToggle);
//				extenderToggle = !extenderToggle;
//			}
			
			/**
			 * ROLLER CONTROLS
			 */
			if(ControllerMappings.rollerCollectPosition.getDown()){
				
				Robot.taskManager.addArmTask(new SpinToPotentiometerValue(RobotMap.ArmMotor,RobotConstants.ArmPotentiometerMax , 0.5,1));
				
			}
			if(ControllerMappings.ArmCycleDown.getDown()){
				armCycle.CycleDown();
				Robot.taskManager.addArmTask(armCycle);
			}
//			if(ControllerMappings.ArmCycleUp.get()){
//				RobotMap.ShooterSolenoid.set(true);
//			}
			
//			if(RobotMap.ExtenderPotentiometer.get()<RobotConstants.ExtenderPotentiometerZero-RobotConstants.ExtenderWheelClearnace
//			&& ControllerMappings.extenderExtendButton.getDown()){
//				RobotMap.ExtenderSolenoid.set(extenderToggle);
//				extenderToggle = !extenderToggle;
//			}
	
			/**
			 * DRIVE SHIFTING
			 */
			//TODO: set shifting to activate for current draw or rpm
			if(ControllerMappings.leftJukeButton2.getDown()){
				RobotMap.DriveSolenoid.set(Value.kForward);
			}
			if(ControllerMappings.leftJukeButton1.getDown()){
				RobotMap.DriveSolenoid.set(Value.kReverse);
			}
			
			/**
			 * ROLLER CONTROLER
			 */
			
				
			if (payloadManualOverride == false){
				boolean running = false;
				rollerPower = ControllerMappings.payloadJoy.getRawAxis(5)*.75;
				// potentiometer spins opposite of actuator
	//			if(RobotMap.RollerPotentiometer.get() < RobotConstants.RollerPotentiometerMin &&
	//					RobotMap.RollerPotentiometer.get() > RobotConstants.RollerPotentiometerMax){
	//				Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerBarMotor,rollerPower));
	//				running = true;
	//			}else{
	//				// roller > 0 is moving down
	//				if(RobotMap.RollerPotentiometer.get() > RobotConstants.RollerPotentiometerMin && rollerPower > 0){
	//					Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerBarMotor,rollerPower));
	//					running = true;
	//				}
	//				// roller < 0 is moving up
	//				if (RobotMap.RollerPotentiometer.get() < RobotConstants.RollerPotentiometerMax && rollerPower < 0){
	//					Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerBarMotor,rollerPower));
	//					running = true;
	//				}
	//			}
	//			
	//			if (!running){
	//				Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerBarMotor,0));
	//			}
				Robot.taskManager.addArmTask(new SpinToPower(RobotMap.ArmMotor,rollerPower));
	
				
				//This sets the brakes to open if the power signal to the roller talon is greater than 0.1
				
				/**
				 * SHOOTER CONTROLLS
				 */
				if(ControllerMappings.shooterCycleDown.getDown()){
					shooterCycle.CycleDown();
					Robot.taskManager.addShooterTask(shooterCycle);
				}
				if(ControllerMappings.shooterCycleUp.getDown()){
					shooterCycle.CycleUp();
					Robot.taskManager.addShooterTask(shooterCycle);
				}
				
				SmartDashboard.putNumber("pot", RobotMap.ArmPotentiometer.get());
				double shooterLiftPower = ClampHelper.clamp(-ControllerMappings.payloadJoy.getRawAxis(1) , -0.75, 0.5);
				Robot.taskManager.addShooterTask(new SpinToPower(RobotMap.ShooterLiftMotor,shooterLiftPower));
				
				//if (ControllerMappings.shooterCollectPosition.getDown()){
				//	Robot.taskManager.addShooterTask(new SpinToEncoderValue(RobotMap.ShooterLiftMotor, RobotConstants.shooterCollectEcnoderValue ,0.3));				
				//}
			} else {
				Robot.taskManager.addShooterTask(new SpinToPower(RobotMap.ShooterLiftMotor,ControllerMappings.payloadJoy.getRawAxis(1)/4));
				Robot.taskManager.addArmTask(new SpinToPower(RobotMap.ArmMotor,ControllerMappings.payloadJoy.getRawAxis(5)));
				
			}

			if((ControllerMappings.shooterShoot.get()&&shooterTimer.get()>RobotConstants.ShooterSpinup)||ControllerMappings.ArmCycleUp.get()){
				//Shoot
				RobotMap.ShooterSolenoid.set(true);
			}else{
				RobotMap.ShooterSolenoid.set(false);
			}
			SmartDashboard.putNumber("Time", shooterTimer.get());
			if(ControllerMappings.shooterCollect.getPressed(0.2)){
				Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerMotor,-1));
				Robot.taskManager.addShooterWheelsTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, -1));
			}
			if(ControllerMappings.shooterEject.getPressed(0.2)){
				Robot.taskManager.addShooterWheelsTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 1));
				Robot.taskManager.addRollerTask(new SpinToPower(RobotMap.RollerMotor,1));

			}
			if (ControllerMappings.shooterPrime.get()){
				//Spin up shooter
				//Start shooter time
				Robot.taskManager.addShooterWheelsTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 1));
				
			}else{
				shooterTimer.reset();
			}
			SmartDashboard.putNumber("Encoder", RobotMap.ShooterLiftMotor.getTalon().getPosition());
			
			
//			RobotMap.ShooterRightTalon.set(0.5);
//			RobotMap.ShooterLeftTalon.set(0.5);


			//RobotMap.ShooterLeftMotor.setPower(0.2);
			//RobotMap.ShooterRightMotor.setPower(0.2);
			
//			if(ControllerMappings.shooterShoot.get()&&shooterTimer.get()>RobotConstants.ShooterSpinup){
//				//Shoot
//				RobotMap.ShooterSolenoid.set(true);
//			} else if (ControllerMappings.shooterPrime.get()){
//				//Spin up shooter
//				//Start shooter time
//				shooterTimer.start();
//				RobotMap.ShooterLeftMotor.setPower(1);
//				RobotMap.ShooterRightMotor.setPower(-1);
//				RobotMap.RollerRollerMotor.setPower(0);
//			} else if(ControllerMappings.shooterPrime.getUp()){
//				//Stop shooter spin up
//				RobotMap.ShooterRightMotor.setPower(0);
//				RobotMap.ShooterLeftMotor.setPower(0);
//				//Reset shooter time
//				shooterTimer.reset();
//				shooterTimer.stop();
//			} else if(ControllerMappings.shooterCollect.getPressed(0.2)){
//				//Collect
//				RobotMap.ShooterRightMotor.setPower(-1);
//				RobotMap.ShooterLeftMotor.setPower(1);
//				RobotMap.RollerRollerMotor.setPower(1);
//			} else if(ControllerMappings.shooterEject.getPressed(0.2)){
//				//Eject
//				RobotMap.ShooterRightMotor.setPower(1);
//				RobotMap.ShooterLeftMotor.setPower(-1);
//				RobotMap.RollerRollerMotor.setPower(-1);
//			} else if (ControllerMappings.rollerRoller.get()){
//				//Roller bar roll
//				RobotMap.RollerRollerMotor.setPower(-1);				
//			} else{
//				//Stop all
//				RobotMap.ShooterRightMotor.setPower(0);
//				RobotMap.ShooterLeftMotor.setPower(0);
//				RobotMap.RollerRollerMotor.setPower(0);
//			}
			
			SmartDashboard.putNumber("lift",(RobotMap.ShooterLiftTalon.getPosition()));
//			SmartDashboard.putNumber("lift",(RobotMap.ShooterLeftTalon.get()));

			if(Math.abs(RobotMap.ArmMotor.getTalon().get())<0.05){
				RobotMap.ArmSolenoid.set(false);
			}else{
				RobotMap.ArmSolenoid.set(true);
			}
			
			if(Math.abs(RobotMap.ShooterLiftTalon.get())<0.025){
				RobotMap.ShooterBrakeSolenoid.set(false);
			}else{
				RobotMap.ShooterBrakeSolenoid.set(true);				
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
			
			Robot.taskManager.addScaleTask(new ManualScale(ControllerMappings.payloadJoy.getRawAxis(5),ControllerMappings.payloadJoy.getRawAxis(1)));
			if(Math.abs(RobotMap.ScaleLeftTalon.get()) > 0.03 || Math.abs(RobotMap.ScaleRightTalon.get())>0.03){
			//if(ControllerMappings.payloadJoy.getRawButton(1)){
				RobotMap.ScaleSolenoid.set(true);
			}else{
				RobotMap.ScaleSolenoid.set(false);
			}
			
		}
	}
	


	
	public static void initCycleTasks(){
		shooterCycle = new CycleTask(new Task[]{
			new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterCollect,0.3,0.5),

		});
		//TODO: Set to proper degrees and tune pid
		armCycle = new CycleTask(new Task[]{
			new SpinToPotentiometerValue(RobotMap.ArmMotor,RobotConstants.ArmPotentiometerCollect , 0.5,1.25,RobotConstants.ArmPotentiometerThreshold),
//			new SpinToPotentiometerValue(RobotMap.RollerBarMotor, RobotConstants.RollerPotentiometerMax - GetTargetAngleHelper.degreesToPotentiometerValue(19), 0.5,1),
//			new SpinToPotentiometerValue(RobotMap.RollerBarMotor, RobotConstants.RollerPotentiometerMax - GetTargetAngleHelper.degreesToPotentiometerValue(35), 0.5,1),
		});
	}
	
	public static boolean getMode(){
		return modeToggle;
	}
}
