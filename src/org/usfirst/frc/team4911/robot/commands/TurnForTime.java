package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *	A command that turns the robot for a specified time.
 *
 *@author Luke Caughell
 *
 */
public class TurnForTime extends Command {
	Timer timer;
	DriveSystem driveSystem;
	double driveTime;
	double power;
	Command teleop;
	
    public TurnForTime(double _power, double _time) {
    	teleop = Robot.teleop;
    	driveTime = _time;
    	power = _power;
    	driveSystem = Robot.driveSystem;
    	requires(driveSystem);
    	setInterruptible(false);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    /**
     *  Called just before this Command runs the first time
     */
    @Override
    protected void initialize() {
    	teleop = Robot.teleop;
    	((OperatorDrive)teleop).setUsingDriveSystem(true);
    	timer = new Timer();
    	timer.start();
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
    	driveSystem.drive(power, -power);
    	//System.out.println("Drive For Time Power: "+power+" Time is at: "+ timer.get());
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    @Override
    protected boolean isFinished() {
        return (timer.get()>driveTime);
    }

    /**
     *  Called once after isFinished returns true
     */
    @Override
    protected void end() {
    	((OperatorDrive)teleop).setUsingDriveSystem(false);
    	driveSystem.stop();
    }
    /**
    * Called when another command which requires one or more of the same
    * subsystems is scheduled to run
    */
    @Override
    protected void interrupted() {
    	//driveSystem.stop();
    }
}
