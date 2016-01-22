package org.usfirst.frc.team4911.robot.commands;

import org.omg.PortableInterceptor.ObjectIdHelper;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorDrive extends Command {
	DriveSystem driveSystem = Robot.driveSystem;
	Joystick leftJoy = Robot.oi.leftJoy;
	Joystick rightJoy = Robot.oi.rightJoy;

	private boolean usingDriveSystem;
	
    public OperatorDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	usingDriveSystem = false;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (usingDriveSystem == false){
    		driveSystem.drive(leftJoy.getY(), rightJoy.getY());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.cancel();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void setUsingDriveSystem(boolean value){
    	usingDriveSystem = value;
    }
}
