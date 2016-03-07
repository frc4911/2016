
package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.controller.ControllerMappings;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.tasks.CameraTask;
import org.usfirst.frc.team4911.tasks.DriveStraight;
import org.usfirst.frc.team4911.updators.AutoTaskManager;
import org.usfirst.frc.team4911.updators.CurrentManager;
import org.usfirst.frc.team4911.updators.Inputs;
import org.usfirst.frc.team4911.updators.NewCurrentManager;
import org.usfirst.frc.team4911.updators.Sensors;
import org.usfirst.frc.team4911.updators.TaskManager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    public static NewCurrentManager currentManager;
    CameraTask cameraTask;
    AutoTaskManager autoTaskManager;


	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	currentManager = new NewCurrentManager(1);
    	RobotMap.init();
    	taskManager = new TaskManager();
    	Sensors.init();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        Encoder testEnc;
        Sensors.getImu().reset();
        //cameraTask = new CameraTask(381, 2.8, 625, 6.35,0.3);
        //57
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
    	Sensors.resetImu();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		autoTaskManager = new AutoTaskManager();
		autoTaskManager.addTask(new DriveStraight(0));
		
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    	Sensors.update();
    	autoTaskManager.update();
    }

    /**
     * This function is called when teleop first starts
     */
    public void teleopInit() {
    	Inputs.init();
    	taskManager.init();
    	RobotMap.DriveFrontRightTalon.setEncPosition(0);
    	Sensors.getPanel().resetTotalEnergy();
    	Sensors.getPanel().clearStickyFaults();
    	Sensors.getImu().zeroYaw();
    	
    	
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	currentManager.update();
    	RobotMap.driveCurrentManager.update();
    	Sensors.update();
    	taskManager.update();
    	Inputs.update();   
//    	cameraTask.execute();
//    	Logging.DebugPrint("distance "+(cameraTask.computeDistance()*0.0394));


    	
//    	Logging.DebugPrint("PWM encoder: " + RobotMap.DriveFrontRightMotor.getEncoder().get());
//    	Logging.DebugPrint("Encoder:" + (RobotMap.DriveRightEncoder.get()));
    	
//    	Logging.DebugPrint("Encoder Quad:" + (RobotMap.DriveFrontRightTalon.isSensorPresent(FeedbackDevice.QuadEncoder)));
//    	Logging.DebugPrint("Encoder Pulse:" + (RobotMap.DriveFrontRightTalon.isSensorPresent(FeedbackDevice.PulseWidth)));
//    	Logging.DebugPrint("Encoder Pulse:" + (RobotMap.DriveFrontRightTalon.getPosition()));

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}