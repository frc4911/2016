// File="LevelScaling.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.helpers.PowerOutputHelper;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Helps the robot scale the tower by using three different methods:
 * 1) Main: Encoders work with the IMU to keep pitch as close to zero as possible.
 * 2) Backup: Work in tandem with both encoders to help keep the robot level.
 * 3) Emergency: Manual override.
 *
 * @author Tommy Lee
 */

// Programming manual control first.
public class LevelScaling extends Command {

	Command teleop;
	DriveSystem driveSystem;
	double leftPower;
	double rightPower;
	double leftEncoderValue;
	double rightEncoderValue;
	
    public LevelScaling(double _leftPower, double _rightPower) {
        
    	driveSystem = Robot.driveSystem;
    	leftPower = _leftPower;
    	rightPower = _rightPower;
    }

    /**
     *  Called just before this Command runs the first time
     */
    @Override
    protected void initialize() {
    	
    	teleop = Robot.teleop;
    	((OperatorDrive)teleop).setUsingDriveSystem(true);
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
    	
    	leftPower = PowerOutputHelper.getLeftPowerOutput();
    	rightPower = PowerOutputHelper.getRightPowerOutput();
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
    }
    
    /**
     *  Called when another command which requires one or more of the same
     *  subsystems is scheduled to run
     */
    @Override
    protected void interrupted() {
    }
}
