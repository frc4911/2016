package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.GetTargetAngleHelper;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.updators.Sensors;

import edu.wpi.first.wpilibj.Timer;

/**
 * Task for turning a set amount.
 * 
 * @author Luke Caughell
 */
public class DriveForDegree extends Task{
	Timer timer;
	double driveTime;
	double power;
	double adjustedPower;
	double driveDistance;
	double currentEncoderValueInches;
	double currentDegree;
	double startDegree;
	double degreesToTurn;
	int i;
	PidHelper pid;
	Motor motor;
	
	/**
	 * Constructor
	 * Sets the class variables.
	 * 
	 * @param _power the motor power to set for turning
	 * @param _degreesToTurn the number of degrees to turn 0 to 360
	 * @param _motor the motor object to turn
	 */
	public DriveForDegree(double _degreesToTurn){
		this.priority = RobotConstants.LOW_PRI;
		interruptible = true;
		degreesToTurn = _degreesToTurn;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		pid = new PidHelper(0.4, 0, 0, 0.08);
	   	timer = new Timer();
	   	timer.start();
	   	startDegree = Sensors.getImuYawValue();
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		currentDegree = Sensors.getImuYawValue();
		double angleDif = GetTargetAngleHelper.computeAngleBetween(startDegree, currentDegree);
		power = pid.run(1, angleDif / degreesToTurn, timer.get());
		RobotMap.DriveFrontRightTalon.set(power);
		RobotMap.DriveRearRightTalon.set(power);
		RobotMap.DriveFrontLeftTalon.set(power);
		RobotMap.DriveRearLeftTalon.set(power);
		Logging.DebugPrint("POW" + power);
		if(pid.isFinished()){
			isFinished = true;
		}
	}
	
	/**
	 * Called when the Task has finished.
	 */
	@Override
	public void end(){
		RobotMap.DriveFrontLeftTalon.set(0);
		RobotMap.DriveFrontRightTalon.set(0);
		RobotMap.DriveRearLeftTalon.set(0);
		RobotMap.DriveRearRightTalon.set(0);
	}
}
