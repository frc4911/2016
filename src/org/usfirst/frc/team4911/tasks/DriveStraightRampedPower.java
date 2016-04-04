package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.ClampHelper;
import org.usfirst.frc.team4911.helpers.GetTargetAngleHelper;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.updators.Sensors;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Task for turning a set amount.
 * 
 * @author Luke Caughell
 */
public class DriveStraightRampedPower extends Task{
	Timer timer;
	double driveTime;
	double pidCorrectionPower;
	double driveDistance;
	double currentEncoderValueInches;
	double currentDegree;
	double startDegree;
	double rampedPower;
	double startPower;
	double slope;
	double endTime;
	Drive drive;
	int i;
	PidHelper pid;
	Motor motor;
	boolean reversed;
	 double heading;

	
	/**
	 * Constructor
	 * Sets the class variables.
	 * 
	 * @param _power the motor power to set for turning
	 * @param _motor the motor object to turn
	 */
	public DriveStraightRampedPower(double _startPower, double _endPower, double _endTime){
		interruptible = false;
		drive = new Drive(0, 0);
		this.priority = RobotConstants.MED_PRI;
		//Assume start time 0
		slope = (_endPower-_startPower)/_endTime;
		timer = new Timer();
		endTime = _endTime;
		startPower = _startPower;
		heading = 0;
	}

	public DriveStraightRampedPower(double _startPower, double _endPower, double _heading, double _endTime){
		interruptible = false;
		drive = new Drive(0, 0);
		this.priority = RobotConstants.MED_PRI;
		//Assume start time 0
		slope = (_endPower-_startPower)/_endTime;
		timer = new Timer();
		endTime = _endTime;
		startPower = _startPower;
		heading = _heading;
	}
	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		pid = new PidHelper(0.015, 0, 0.0, 2);
		timer.reset();
	   	timer.start();
	   	startDegree = Sensors.getImuYawValue();
	   	isFinished = false;
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		currentDegree = Sensors.getImuYawValue();
		rampedPower = startPower + (slope*timer.get());
		pidCorrectionPower = pid.run(heading, currentDegree , timer.get());
		pidCorrectionPower = ClampHelper.clamp(pidCorrectionPower, -rampedPower, rampedPower);
		SmartDashboard.putNumber("PID", pidCorrectionPower);
		 
		if (pid.isFinished()){
			drive.setRightPower(rampedPower);
			drive.setLeftPower(rampedPower);
		} else {
			drive.setLeftPower(rampedPower + pidCorrectionPower);
			drive.setRightPower(rampedPower - pidCorrectionPower);
		}
		
		drive.execute();
		
		if (timer.get()> endTime){
			isFinished = false;
		}
	}
	
	/**
	 * Called when the Task has finished.
	 */
	@Override
	public void end(){
		//drive.setPower(0);
		//drive.execute();
	}
	
	public void setFinished(boolean value){
		isFinished = value;
	}
}
