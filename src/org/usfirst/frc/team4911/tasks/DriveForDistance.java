package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.RampDownHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Task for driving a set distance based on encoder value.
 * 
 * @author Luke Caughell
 */
public class DriveForDistance extends Task{
	Timer timer;
	double driveTime;
	double power;
	double adjustedPower;
	double driveDistance;
	double currentEncoderValueInches;
	Command teleop;
	RampDownHelper rampDown;
	int i;
	Motor motor;
	Drive drive;
	
	/**
	 * Constructor
	 * Sets class variables.
	 * @param _power the motor power to use for driving
	 * @param _driveDistance the distance to drive in inches
	 * @param _motor the motor object to drive
	 */
	public DriveForDistance(double _power, double _driveDistance, Motor _motor){
		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		power = _power;
		driveDistance = _driveDistance;
		motor = _motor;
		drive = new Drive(0,0);
		
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
	   	rampDown = new RampDownHelper();
	   	motor.getEncoder().reset();
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		currentEncoderValueInches = motor.getEncoder().getDistance();
		power = rampDown.getRampedPower(driveDistance,currentEncoderValueInches);
		
		drive.setPower(power);
		drive.execute();
		
		if((driveDistance - Math.abs(currentEncoderValueInches) <= 0)){
			isFinished = true;
		}
	}
	
	/**
	 * Called when the task is finished.
	 */
	@Override
	public void end(){
		drive.setPower(0);
		drive.execute();
	}
}
