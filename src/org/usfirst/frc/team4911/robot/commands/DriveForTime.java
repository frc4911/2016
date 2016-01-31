package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that drives for a specific time.
 * 
 * @author Luke Caughell
 * 
 */
public class DriveForTime extends Command {
	Timer timer;
	DriveSystem driveSystem;
	double driveTime;
	double power;
	Command teleop;
	int i;
	
    public DriveForTime(double _power, double _time) {
    	driveTime = _time;
    	power = _power;
    	driveSystem = Robot.driveSystem;
    	requires(driveSystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

/**
 *      Called just before this Command runs the first time
 */
    @Override
    protected void initialize() {
    	teleop = Robot.teleop;
    	((OperatorDrive)teleop).setUsingDriveSystem(true);
    	timer = new Timer();
    	timer.start();
    	RobotMap.RightDriveEncoder.reset();
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
    	i+=1;
    	System.out.println("Time: " + timer.get());
    	System.out.println(i);
    	System.out.println("ENCODER: " + RobotMap.RightDriveEncoder.getDistance());
    	
    	driveSystem.drive(power, power);
    	//System.out.println("ENCODER POSITION: " + RobotMap.RearLeftTalon.getEncPosition());
    	//System.out.println("ENCODER VELOCITY: " + RobotMap.RearLeftTalon.getEncVelocity());
    	//System.out.println("Drive For Time Power: "+power+" Time is at: "+ timer.get());
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
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
     *  Called when another command which requires one or more of the same
     *  subsystems is scheduled to run
     */
    @Override
    protected void interrupted() {
    	//driveSystem.stop();
    }
}
