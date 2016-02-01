// File="Robot.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.commands.OperatorDrive;
import org.usfirst.frc.team4911.robot.commands.TestCommand;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * This is the main class.
 * 
 * @author Luke Caughell
 * 
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Command teleop;
	public static Command testCommand; //For autonomous
	public static DriveSystem driveSystem;

    Command autonomousCommand;
    SendableChooser chooser;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	
//        chooser.addObject("My Auto", new MyAutoCommand());
        
    	RobotMap.init();
		driveSystem = new DriveSystem();
		oi = new OI();
		teleop = new OperatorDrive();

		testCommand = new TestCommand();
		//Initializes the nav6
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", new TestCommand());
		SmartDashboard.putData("Auto mode", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    @Override
    public void disabledInit(){

    }
	
    /**
     * Periodic code for disabled mode should go here. Users should override this
     * method for code which will be called periodically at a regular rate while
     * the robot is in disabled mode.
     */
    @Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    @Override
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
    	Logging.DebugPrint("test output");
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * Initialization code for teleop mode should go here. 
     * Users should override this method for initialization 
     * code which will be called each time the robot enters teleop mode.
     */
    @Override
    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testInit() {
    	
    }
    
    /**
     * Periodic code for test mode should go here Users should override 
     * this method for code which will be called periodically at a regular
     * rate while the robot is in test mode.
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();

    }
}
