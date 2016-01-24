package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {
	DriveSystem driveSystem;
	double driveDistance;
	double power;
	double startPosition;
	Command teleop;
	boolean direction;
	int directionValue;
	double adjustedPower;
	
    public DriveForDistance(double _power, double _distance, boolean _direction) {
    	
    	driveDistance = _distance;
    	direction = _direction;
    	
    	directionValue = direction ? 1 : -1;
    	
    	System.out.println(directionValue);
    	power = _power*-directionValue;
    	driveSystem = Robot.driveSystem;
    	requires(driveSystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	adjustedPower = power;
    	System.out.println("initialize got called");
    	startPosition = encoderAverage();
    	teleop = Robot.teleop;
    	((OperatorDrive)teleop).setUsingDriveSystem(true);
    	

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double stopValue = inchesToEncoderValue(driveDistance);
    	//System.out.println("Target: " + stopValue);
    	//System.out.println("Current: " + encoderAverage());
    	
    	
    	//System.out.println(" ");
    	//System.out.println("Input Value: " + (stopValue-encoderAverage()));
    	
    	//if(stopValue-encoderAverage()<450){
    	//	adjustedPower = Math.abs(power)*((stopValue-encoderAverage())/450)*-directionValue;
    	//}
    	
    	System.out.println("Power: "+ adjustedPower);

    	driveSystem.drive(adjustedPower, adjustedPower);
		System.out.println("Current Value: "+ encoderAverage());
//		System.out.println("Stop Value: "+stopValue);
    	//System.out.println("ENCODER VELOCITY: " + RobotMap.RearLeftTalon.getEncVelocity());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (direction){
    		return (encoderAverage()>inchesToEncoderValue(driveDistance));
    	} else {
    		return (encoderAverage()<inchesToEncoderValue(driveDistance));
    	}
    }
    

    // Called once after isFinished returns true
    protected void end() {
    	((OperatorDrive)teleop).setUsingDriveSystem(false);
    	driveSystem.stop();


    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveSystem.stop();
    	
    }
    
    double inchesToEncoderValue(double inch){
 //   	System.out.println("Start position: " + startPosition);
    	return ((inch/18.849)*720*directionValue)+startPosition;
    }
    //takes two doubles and averages them
    double encoderAverage(){
    	//return((Math.abs(RobotMap.RearLeftTalon.getEncPosition())+Math.abs(RobotMap.FrontRightTalon.getEncPosition()))/2)*directionValue;
    	return((RobotMap.RearLeftTalon.getEncPosition()-(RobotMap.FrontRightTalon.getEncPosition()))/2);

    }
}
