// File="PayloadOperatorDrive.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4911.robot.subsystems.WinchControl;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *This command is called continuously during
 * and allows the driver to control the robot
 * with the payload joysticks.
 *
 * @author Tommy Lee
 */
public class PayloadOperatorDrive extends Command {
	
	WinchControl winchControl = Robot.winchControl;
	Joystick payloadLeftJoy = Robot.oi.payloadLeftJoy;
	Joystick payloadRightJoy = Robot.oi.payloadRightJoy;
	
    public PayloadOperatorDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    /**
     *  Called just before this Command runs the first time
     */
    @Override
    protected void initialize() {
    	
    }
    
    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
    	
    	winchControl.moveWinch(payloadLeftJoy.getY(), payloadRightJoy.getY());
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
     * @param value
     */
    protected void interrupted(boolean value) {
    	
    }

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
