package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;


/**
 * This command is called continuously during
 * and allows the driver to control the robot
 * with joysticks
 * 
 * @author Luke Caughell
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

    /**
     *  Called just before this Command runs the first time
     */
    @Override
    protected void initialize() {
    	usingDriveSystem = false;

    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
    	if (usingDriveSystem == false){
    		//DON'T FORGET TO ADD BACK IN
    		driveSystem.drive(leftJoy.getY(), rightJoy.getY());
    	}
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     *  Called once after isFinished returns true
     */
    @Override
    protected void end() {
    	this.cancel();

    }

    /**
     *  Called when another command which requires one or more of the same
     *  subsystems is scheduled to run
     */
    @Override
    protected void interrupted() {
    }
    
    /**
     * If using drive system is set to true
     * operator drive will not update motor powers
     * @param value
     */
    public void setUsingDriveSystem(boolean value){
    	usingDriveSystem = value;
    }
}
