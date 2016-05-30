
package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.controller.ControllerMappings;
import org.usfirst.frc.team4911.helpers.LogFileHandler;
import org.usfirst.frc.team4911.tasks.DriveForDegree;
import org.usfirst.frc.team4911.tasks.DriveForDistance;
import org.usfirst.frc.team4911.tasks.DriveStraight;
import org.usfirst.frc.team4911.tasks.DriveStraightRampedPower;
import org.usfirst.frc.team4911.tasks.ShooterWheelTask;
import org.usfirst.frc.team4911.tasks.SolenoidTrigger;
import org.usfirst.frc.team4911.tasks.SpinToPotentiometerValue;
import org.usfirst.frc.team4911.tasks.SpinToTalonValue;
import org.usfirst.frc.team4911.tasks.Task;
import org.usfirst.frc.team4911.updators.AutoTaskManager;
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
    final String rampartsAuto = "Default auto";
    final String lowBarAuto = "Low Bar";
    final String frenchAuto = "French";

    private boolean isIMUZeroed = false;
    private boolean isDriveEncZeroed = false;
    
    String autoSelected;
    SendableChooser chooser;
    public CameraServer cameraServer;
	Solenoid s;
	private AutoTaskManager autoTaskManager;
	public static NewCurrentManager driveCurrentManager;
	
	LogFileHandler TeleopLogHandler;
	LogFileHandler AutoLogHandler;
	
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
 
        //cameraServer = CameraServer.getInstance();
        //cameraServer.startAutomaticCapture(); 
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro.
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional 
	 * strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	RobotMap.ShooterLiftMotor.getTalon().setEncPosition(0);
    	autoSelected = (String) chooser.getSelected();
		//autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		autoTaskManager = new AutoTaskManager();
		autoTaskManager.init();
		Sensors.getImu().zeroYaw();
		
		isIMUZeroed = false;
		isDriveEncZeroed = false;
		
		// if less than 0 do ramparts
		if (ControllerMappings.leftJoy.getRawAxis(3) <= 0) {
			autoSelected = rampartsAuto;
			System.out.println("STATIC DEFENSE AUTO: " + ControllerMappings.leftJoy.getRawAxis(3));
		} else { //if (ControllerMappings.leftJoy.getRawAxis(3) > 0.75) {
			autoSelected = lowBarAuto;
			System.out.println("LOW BAR AUTO: " + ControllerMappings.leftJoy.getRawAxis(3));
		} //else{
		//	autoSelected = frenchAuto;
		//	System.out.println("FRENCH AUTO: " + ControllerMappings.leftJoy.getRawAxis(3));
		//}
		System.out.println("Auto selected: " + autoSelected);

		/**
		 * COMMENTED OUT AUTO BECAUSE OF SOLENOID ERRORS
		 */
		switch (autoSelected) {
		case rampartsAuto:
		default:
			//Put custom auto code here   
			AddDefaultTasks();
			break;
		case lowBarAuto:
			AddLowBarTasks();
			break;
		//case frenchAuto:
		//	AddChevalDuFriesTasks();
		//	break;
		}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	SmartDashboard.putNumber("IMU" , Sensors.getImu().getYaw());
    	if (!isIMUZeroed) {
    		if (Math.abs(Sensors.getImu().getYaw()) < 1) {
    			isIMUZeroed = true;
    		} else {
    			System.out.println("TRYING TO ZERO IMU");
    			Sensors.getImu().zeroYaw();
    		}
    	}
    	if (!isDriveEncZeroed) {
    		if (Math.abs(RobotMap.DriveEncoder.get()) < 1) {
    			isDriveEncZeroed = true;
    		} else {
    			System.out.println("TRYING TO ZERO Drive Encoder");
    			RobotMap.DriveEncoder.reset();
    		}
    	}
    	
    	if (isIMUZeroed && isDriveEncZeroed) {
    		Sensors.update();
    		autoTaskManager.update();
    		//SmartDashboard.putNumber("Encoder" , RobotMap.ShooterLiftTalon.getPosition());
    	}
    	
    	SmartDashboard.putNumber("Encoder" , RobotMap.ShooterLiftTalon.getPosition());
    }

    /**
     * This function is called when teleop first starts
     */
    public void teleopInit() {
    	Inputs.init();
    	//Sensors.getImu().zeroYaw();
    	taskManager.init();
    	RobotMap.DriveEncoder.reset();
    	
    	// TODO: testing for high goal auto
    	Sensors.getImu().zeroYaw();
    	
    	// create log file
    	String teleopLogName = "teleoplog" + String.valueOf(System.currentTimeMillis());
    	TeleopLogHandler = new LogFileHandler(teleopLogName);
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//driveCurrentManager.update();
    	Sensors.update();
    	taskManager.update();
    	Inputs.update();
    	
    	System.out.println("pot:  " + RobotMap.ArmPotentiometer.get());
    	//System.out.println("drive encoder: " + RobotMap.DriveEncoder.getDistance());
    	//System.out.println("drive ticks: " + RobotMap.DriveEncoder.get());

    	SmartDashboard.putNumber("Encoder Degrees" , RobotMap.ShooterLiftTalon.getPosition()*90);
    	SmartDashboard.putNumber("Encoder" , RobotMap.ShooterLiftTalon.getPosition());
    	SmartDashboard.putNumber("pov", ControllerMappings.payloadJoy.getPOV());
    	SmartDashboard.putNumber("Feedback Device:  ",  RobotMap.ShooterLiftTalon.getEncPosition());
    	SmartDashboard.putBoolean("MODE", Inputs.getMode());
    	SmartDashboard.putNumber("IMU: " , Sensors.getImuYawValue());
    	SmartDashboard.putNumber("DRIVE ENCODER", RobotMap.DriveEncoder.get());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT LEFT", RobotMap.DriveFrontLeftTalon.get() + RobotMap.DriveMidLeftTalon.get() + RobotMap.DriveRearLeftTalon.get());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT RIGHT", RobotMap.DriveFrontRightTalon.get() + RobotMap.DriveMidRightTalon.get() + RobotMap.DriveRearRightTalon.get());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT RIGHT Voltage", RobotMap.DriveFrontRightTalon.getOutputVoltage() + RobotMap.DriveMidRightTalon.getOutputVoltage() + RobotMap.DriveRearRightTalon.getOutputVoltage());
    	SmartDashboard.putNumber("DRIVE TALON POWER OUTPUT LEFT Volatge", RobotMap.DriveFrontLeftTalon.getOutputVoltage() + RobotMap.DriveMidLeftTalon.getOutputVoltage() + RobotMap.DriveRearLeftTalon.getOutputVoltage());
    	SmartDashboard.putString("Potentiometer", Double.toString(RobotMap.ArmPotentiometer.get()));
    	SmartDashboard.putNumber("Wheel Encoder", RobotMap.DriveEncoder.getDistance());
    	//System.out.println(RobotMap.ArmPotentiometer.get());
    	
    	// Write an entry to the log file
    	TeleopLogHandler.WriteLogEntry();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
    /**
     * Adds tasks for running low bar auto with a high shot
     */
    @SuppressWarnings("unused")
	private void AddLowBarHighShotTasks()
    {
    	// Sets of commands are separated by spaces when there is a change in subsystem
    	// lower shooter
    	autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterAutoLow,0.7,2), 1.7);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.1);
		
		// lower arm
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoDown, 0.7, 1), 1);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);

		// drive through low bar
		// turn toward goal
		autoTaskManager.addTask(new DriveForDistance(RobotConstants.ShooterAutoDriveDist, RobotMap.DriveEncoder, 0.8), 7);
		autoTaskManager.addTask(new DriveForDegree(RobotConstants.ShooterAutoTurnDegree, 0.4, 2, true), 2);

		// move arm out of the way
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoUp, 0.5, 2), 2);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);
				
		// lift shooter to position
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterAutoHigh, 0.6, 2), 1);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.1);
		
		// intake ball to secure it
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, -.5), .5);
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 0), 0.5);
		
		// spin flywheels
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 1), 1.25);
		
		// shoot
		autoTaskManager.addTask(new Task(),0.5);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterSolenoid, true), 0.1);
		autoTaskManager.addTask(new Task(),0.5);
		
		// stop flywheels
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 0), 0.1);
		
		// rotate back to zero
		autoTaskManager.addTask(new DriveForDegree(RobotConstants.ShooterAutoTurnDegree * -1, 0.6, 2, false), 2);
		
		// lower shooter
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.05);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterAuto, 0.3, 2), 1);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.05);
		
		// lower arm
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoDown, 0.7, 1), 1);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);
		
		// drive back to NZ
		// cant drive backwards with given task...
		//autoTaskManager.addTask(new DriveForDistance(RobotConstants.ShooterAutoDriveDist, RobotMap.DriveEncoder, -0.4), 3);
    }
    
    /**
     * Adds the low bar autonomous tasks to the task manager 
     */
    private void AddLowBarTasks()
    {
    	//Sets of commands are separated by spaces when there is a change in subsystem
    	// lower shooter
    	autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterAutoLow, 0.3, 2), 1.7);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.1);
		
		// lower arm
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoDown, 0.7, 1), 1);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);
		
		// drive through low bar
		// turn toward goal
		autoTaskManager.addTask(new DriveForDistance(134, RobotMap.DriveEncoder, 0.8), 7);
		autoTaskManager.addTask(new DriveForDegree(30, 1, 1, true), 1);

		// move arm out of the way
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoUp, 0.5, 2), 2);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);
		
		// lift shooter to position
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterAutoLow, 0.4, 2), 1);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.1);
		
		// drive onto batter
		// turn to goal
		autoTaskManager.addTask(new DriveForDistance(158, RobotMap.DriveEncoder, 0.8, 30), 7);
		autoTaskManager.addTask(new DriveForDegree(32, 0.8, 1.5, true), 1.5);
		
		// intake ball to secure it
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, -.5), .5);
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 0), 0.5);
		
		// spin flywheels
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 1), 1.25);
		
		// shoot
		autoTaskManager.addTask(new Task(),0.5);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterSolenoid, true), 0.1);
		autoTaskManager.addTask(new Task(),0.5);
		
		// stop flywheels
		autoTaskManager.addTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 0), 0.1);		
    }
    
    /**
     * Adds all the Cheval Du Fries-specific tasks to the task manager
     */
    /*private void AddChevalDuFriesTasks()
    {
    	// Sets of commands are separated by spaces when there is a change in subsystem
    	// lower shooter
    	autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterAuto, 0.4, 2), 2);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.1);
		
		// lower arm
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoDown, 0.7, 2), 2);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);
		
		// raise shooter
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToTalonValue(RobotMap.ShooterLiftMotor, RobotConstants.ShooterLiftMax, 0.6, 0.5), 0.5);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ShooterBrakeSolenoid, false), 0.1);
		
		// raise arm to cheval prep
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoFrench, 0.7, 2), 2);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);	
		
		// drive to cheval
		autoTaskManager.addTask(new DriveForDistance(40, RobotMap.DriveEncoder, 0.8), 15);	
		
		// lower armonto cheval
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinToPotentiometerValue(RobotMap.ArmMotor, RobotConstants.ArmPotentiometerAutoFrenchDown, 0.7, 2), 2);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);		
		
		// drive over cheval
		autoTaskManager.addTask(new DriveForDistance(50, RobotMap.DriveEncoder, 0.8), 15);
		
		// lift arm back up
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, true), 0.1);
		autoTaskManager.addTask(new SpinForTime(RobotMap.ArmMotor, 0.5, 0.7), 0.7);
		autoTaskManager.addTask(new SolenoidTrigger(RobotMap.ArmSolenoid, false), 0.1);
		
		// drive out of outer works
		autoTaskManager.addTask(new DriveForDistance(55, RobotMap.DriveEncoder, 0.8), 15);
    }*/

    /**
     * Default auto, drive straight over static defenses such as ramparts, rock wall, moat, or rough terrain
     */
    private void AddDefaultTasks()
    {
    	// Sets of commands are separated by spaces when there is a change in subsystem
		autoTaskManager.addTask(new DriveStraightRampedPower(0,1,1),1);
		autoTaskManager.addTask(new DriveStraight(0,1,false),1.75);
		autoTaskManager.addTask(new DriveStraightRampedPower(1,0,1),1);
    }
}