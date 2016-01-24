
package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4911.robot.commands.ExampleCommand;
import org.usfirst.frc.team4911.robot.commands.OperatorDrive;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4911.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4911.robot.subsystems.Nav6;
import org.usfirst.frc.team4911.robot.subsystems.Shooter;
import org.usfirst.frc.team4911.robot.subsystems.ShooterPneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// A comment I added to give us something to commit while working with sourcetree
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static Command teleop;
	public static DriveSystem driveSystem;
	public static Nav6 nav6;
	public static Shooter shooter;
	public static ShooterPneumatics shooterPneumatics;


    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
		driveSystem = new DriveSystem();
		oi = new OI();
		teleop = new OperatorDrive();
		shooter = new Shooter();
		shooterPneumatics = new ShooterPneumatics();
		nav6 = new Nav6();
		//Initializes the nav6
		nav6.init();

        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
    }
	
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
    	
    	//DISABLED DRIVE FOR SAFTEY
    	//DON'T FORGET TO TURN IT BACK ON
    	//teleop.start();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        RobotMap.FrontRightTalon.setPosition(0);
        RobotMap.RearLeftTalon.setPosition(0);


    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {


        Scheduler.getInstance().run();
//		System.out.println("RIGHT: "+RobotMap.FrontRightTalon.getEncPosition());
//		System.out.println("rearleft: "+RobotMap.RearLeftTalon.getEncPosition());
//		System.out.println("rearright: "+RobotMap.RearRightTalon.getEncPosition());
//		System.out.println("frontleft: "+RobotMap.FrontLeftTalon.getEncPosition());


    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
