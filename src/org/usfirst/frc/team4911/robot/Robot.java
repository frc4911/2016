
package org.usfirst.frc.team4911.robot;
<<<<<<< HEAD


import org.usfirst.frc.team4911.helpers.*;
import org.usfirst.frc.team4911.controller.ControllerMappings;
import org.usfirst.frc.team4911.helpers.Logging;
=======

import org.usfirst.frc.team4911.helpers.*;
import org.usfirst.frc.team4911.controller.ControllerMappings;
import org.usfirst.frc.team4911.helpers.Logging;
>>>>>>> ed264e2b030172203e955d876c4037c2425e4c7c
import org.usfirst.frc.team4911.tasks.DriveForDegree;
import org.usfirst.frc.team4911.tasks.DriveStraight;
import org.usfirst.frc.team4911.tasks.SolenoidTrigger;
import org.usfirst.frc.team4911.tasks.SpinToPotentiometerValue;
import org.usfirst.frc.team4911.tasks.SpinToPower;
import org.usfirst.frc.team4911.tasks.Task;
import org.usfirst.frc.team4911.updators.AutoTaskManager;
import org.usfirst.frc.team4911.updators.CurrentManager;
import org.usfirst.frc.team4911.updators.Inputs;
import org.usfirst.frc.team4911.updators.NewCurrentManager;
import org.usfirst.frc.team4911.updators.Sensors;
import org.usfirst.frc.team4911.updators.TaskManager;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static boolean operatorDrive;
	public static TaskManager taskManager;
    final String defaultAuto = "Default";
    final  String rampartsAuto = "Default auto";
    final  String lowBarAuto = "Low Bar";

    String autoSelected;
    SendableChooser chooser;
    public CameraServer cameraServer;
	Solenoid s;
	private AutoTaskManager autoTaskManager;
	public static NewCurrentManager driveCurrentManager;

	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	taskManager = new TaskManager();
    	taskManager.init();
    	Sensors.init();
    	
//      chooser.addDefault("0.4 Power", defaultAuto);
        chooser = new SendableChooser();
        chooser.addObject("0.8 Power", rampartsAuto);
        chooser.addObject("LOW BAR", lowBarAuto);

        SmartDashboard.putData("Auto choices", chooser);
        driveCurrentManager = new NewCurrentManager(10);
 //   	s = new Solenoid(1,2);
 
        //cameraServer = CameraServer.getInstance();
        //cameraServer.startAutomaticCapture();
        
        //s = new Solenoid(1,1);   
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro.
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
		//autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		autoTaskManager = new AutoTaskManager();
		autoTaskManager.init();
		//autoTaskManager.addTask(new DriveForDegree(-90,1,false),1);
		//autoTaskManager.addTask(new DriveStraight(0,true),2);
		Sensors.getImu().zeroYaw();
		
		
		// if less than 0 do ramparts
		if (ControllerMappings.leftJoy.getRawAxis(3) < 0){
			autoSelected = rampartsAuto;
		} else {
			autoSelected = lowBarAuto;
		}
		
		switch(autoSelected) {
		case rampartsAuto:
		default:
			//Put custom auto code here   
			autoTaskManager.addTask(new DriveStraight(0,0.2,false),0.2);
			autoTaskManager.addTask(new DriveStraight(0,0.3,false),0.2);
			autoTaskManager.addTask(new DriveStraight(0,0.4,false),0.2);
			autoTaskManager.addTask(new DriveStraight(0,0.6,false),0.2);
			autoTaskManager.addTask(new DriveStraight(0,0.8,false),0.2);
			autoTaskManager.addTask(new DriveStraight(0,1,false),3);
			break;
		case lowBarAuto:
			//Put custom auto code here   
			//autoTaskManager.addTask(new SpinToPower(RobotMap.RollerBarMotor, 0.3),0.1);
			//autoTaskManager.addTask(new Task(),1);
			//autoTaskManager.addTask(new SpinToPower(RobotMap.RollerBarMotor, 0.0),0.1);
			autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
			autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerMax, 1, 2), 2);
			autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);

			autoTaskManager.addTask(new DriveStraight(0,0.2,false),0.3);
			autoTaskManager.addTask(new DriveStraight(0,0.3,false),0.3);
			autoTaskManager.addTask(new DriveStraight(0,0.4,false),6);
			break;
//		case defaultAuto:
//			//Put default auto code here
//			autoTaskManager.addTask(new DriveStraight(0,0.4,false),6);
//			break;
		}
		
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Sensors.update();
    	autoTaskManager.update();
    	SmartDashboard.putNumber("IMU: " , Sensors.getImuYawValue());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT LEFT:  ", RobotMap.DriveFrontLeftTalon.get() + RobotMap.DriveMidLeftTalon.get() + RobotMap.DriveRearLeftTalon.get());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT RIGHT: ", RobotMap.DriveFrontRightTalon.get() + RobotMap.DriveMidRightTalon.get() + RobotMap.DriveRearRightTalon.get());
    }

    /**
     * This function is called when teleop first starts
     */
    public void teleopInit() {
    	Inputs.init();
    	//Sensors.getImu().zeroYaw();
    	taskManager.init();
    	
    	// Create the log file
    	LogFileHandler logFileHandler = LogFileHandler.getInstance();
    	logFileHandler.CreateLogFile();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Sensors.update();
    	taskManager.update();
    	Inputs.update();
    	SmartDashboard.putBoolean("MODE", Inputs.getMode());
    	SmartDashboard.putNumber("IMU: " , Sensors.getImuYawValue());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT LEFT", RobotMap.DriveFrontLeftTalon.get() + RobotMap.DriveMidLeftTalon.get() + RobotMap.DriveRearLeftTalon.get());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT RIGHT", RobotMap.DriveFrontRightTalon.get() + RobotMap.DriveMidRightTalon.get() + RobotMap.DriveRearRightTalon.get());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT RIGHT Voltage", RobotMap.DriveFrontRightTalon.getOutputVoltage() + RobotMap.DriveMidRightTalon.getOutputVoltage() + RobotMap.DriveRearRightTalon.getOutputVoltage());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT LEFT Volatge", RobotMap.DriveFrontLeftTalon.getOutputVoltage() + RobotMap.DriveMidLeftTalon.getOutputVoltage() + RobotMap.DriveRearLeftTalon.getOutputVoltage());
    	SmartDashboard.putNumber("Potentiometer", RobotMap.ArmPotentiometer.get());

    	// Write an entry to the log file
    	LogFileHandler logFileHandler = LogFileHandler.getInstance();
    	logFileHandler.WriteLogEntry();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
//    public void disabledPeriodic(){
//    	SmartDashboard.putString("AUTO", autoSelected);
//    	if(ControllerMappings.autoSelectorLow.getDown()){
//    		autoSelected = rampartsAuto;
//    	}
//    	if(ControllerMappings.autoSelectorOther.getDown()){
//    		autoSelected = lowBarAuto;
//    	}
//    }
}
