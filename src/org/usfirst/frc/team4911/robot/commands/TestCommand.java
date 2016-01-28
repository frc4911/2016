package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestCommand extends Command {
	Timer timer;
	DriveSystem driveSystem;
	double encoderValue;
	int limitCount;
	Command teleop;
	int i;
	double power;
	double time;
	double prevTime = 0;
	double prevValue;
	double deltaTime;
	double value;
	

	
    public TestCommand() {
    	driveSystem = Robot.driveSystem;
    	requires(driveSystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	teleop = Robot.teleop;
    	timer = new Timer();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveSystem.drive(0.4, 0.4);
    	value = RobotMap.FrontRightEncoder.getDistance();
    	time = timer.get();
    	if (prevValue!=value){
        	deltaTime = time-prevTime;
    		System.out.println("FRONT RIGHT ENC: " + value + " TIME DIFF: " + deltaTime);
    	}
    	prevTime = time;
    	prevValue = value;
		//System.out.println("REAR LEFT ENC: "+RobotMap.RearLeftTalon.getEncPosition());
//		System.out.println("rearright: "+RobotMap.RearRightTalon.getEncPosition());
//		System.out.println("frontleft: "+RobotMap.FrontLeftTalon.getEncPosition());
    	//driveSystem.drive(0.5, 0.5);
    	//System.out.println(encoderValue/limitCount);
    	//System.out.println("ENCODER POSITION: " + RobotMap.RearLeftTalon.getEncPosition());
    	//System.out.println("ENCODER VELOCITY: " + RobotMap.RearLeftTalon.getEncVelocity());
    	//System.out.println("Drive For Time Power: "+power+" Time is at: "+ timer.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	value = RobotMap.FrontRightEncoder.get();
        RobotMap.FrontRightEncoder.reset();
    	((OperatorDrive)teleop).setUsingDriveSystem(false);
    	driveSystem.stop();
		System.out.println("FRONT RIGHT ENC ENDED ON: " + value );

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveSystem.stop();
    }
    

}
