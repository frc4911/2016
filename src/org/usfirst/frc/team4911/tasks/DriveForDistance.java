package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.ClampHelper;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.helpers.RampDownHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.updators.Sensors;

import edu.wpi.first.wpilibj.Encoder;
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
	double maxPower;
	double rampedPower;
	double pidPower;
	double adjustedPower;
	double driveDistance;
	double currentEncoderValueInches;
	double currentDegree;
	Command teleop;
	RampDownHelper rampDown;
	int i;
	Motor motor;
	Drive drive;
	Encoder encoder;
	PidHelper pid;
	private double heading;
	
	/**
	 * Constructor
	 * Sets class variables.
	 * @param _power the motor power to use for driving
	 * @param _driveDistance the distance to drive in inches
	 * @param _motor the motor object to drive
	 */
	public DriveForDistance(double _driveDistance, Motor _motor, double _maxPower){
		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		driveDistance = _driveDistance;
		motor = _motor;
		encoder = motor.getEncoder();
		drive = new Drive(0,0);
		maxPower = _maxPower;
		heading = 0;
	}
	public DriveForDistance(double _driveDistance, Encoder _encoder, double _maxPower, double _heading){
		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		driveDistance = _driveDistance;
		encoder = _encoder;
		maxPower = _maxPower;
		drive = new Drive(0,0);
		heading = _heading;
	}
	public DriveForDistance(double _driveDistance, Encoder _encoder, double _maxPower){
		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		driveDistance = _driveDistance;
		encoder = _encoder;
		maxPower = _maxPower;
		drive = new Drive(0,0);
		heading = 0;
	}	
	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		pid = new PidHelper(0.015, 0, 0.0, 2);
	   	rampDown = new RampDownHelper();
	   	timer = new Timer();
	   	encoder.reset();
	   	
	   	timer.reset();
	   	timer.start();
	   	isFinished = false;
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		
		currentEncoderValueInches = encoder.getDistance();
		
		currentDegree = Sensors.getImuYawValue();

		rampedPower = rampDown.getRampedPower(driveDistance,currentEncoderValueInches);
		adjustedPower = ClampHelper.clamp(rampedPower, -maxPower, maxPower);
		
		pidPower = pid.run(heading, currentDegree , timer.get());
		pidPower = ClampHelper.clamp(pidPower, -adjustedPower, adjustedPower);

		System.out.println("distance: " + currentEncoderValueInches + "/" + driveDistance);
		
		System.out.println("ramp Power: " + rampedPower);
		System.out.println("adjusted Power: " + rampedPower);
		System.out.println("pid Power: " + rampedPower);
		
		drive.setLeftPower(adjustedPower+pidPower);
		drive.setRightPower(adjustedPower-pidPower);
		
		drive.execute();
		
		if((Math.abs(driveDistance) - Math.abs(currentEncoderValueInches) <= 0)){
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
