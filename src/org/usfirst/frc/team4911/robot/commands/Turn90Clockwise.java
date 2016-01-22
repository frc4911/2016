package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.PID;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4911.robot.subsystems.Nav6;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn90Clockwise extends Command {
	DriveSystem driveSystem = Robot.driveSystem;
	Nav6 nav6 = Robot.nav6;
	double targetAngle;
	private double startAngle;
	private double currentAngle;
	Command teleop;
	Timer clock = new Timer();
	PID pid;
	
    public Turn90Clockwise() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	teleop = Robot.teleop;
    	((OperatorDrive)teleop).setUsingDriveSystem(true);
    	driveSystem = Robot.driveSystem;
    	targetAngle = 90;
    	System.out.print(nav6.imu);
    	nav6.imu.zeroYaw();
		clock.start();
		pid = new PID(0.007f,0.004f,0.001f,0.5f/360f);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = nav6.imu.getYaw();
    	double power = pid.run(90, nav6.imu.getYaw(), clock.get());
    	driveSystem.drive(-1*power, 1*power);
    	System.out.println("Current angle dif: " + (targetAngle-nav6.imu.getYaw()));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return(targetAngle<currentAngle);
    	return(pid.isFinished());
    }

    // Called once after isFinished returns true
    protected void end() {
    	((OperatorDrive)teleop).setUsingDriveSystem(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
