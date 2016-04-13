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
	double timeoutTime;
	int i;
	boolean reversed;
	PidHelper pid;
	Motor motor;
	Drive drive;
	
	/**
	 * Constructor
	 * Sets the class variables.
	 * 
	 * @param _power the motor power to set for turning
	 * @param _degreesToTurn the number of degrees to turn 0 to 360
	 * @param _motor the motor object to turn
	 */
	public DriveForDegree(double _degreesToTurn, double _timeoutTime, boolean _reversed){
		this.priority = RobotConstants.MED_PRI;
		interruptible = true;
		degreesToTurn = _degreesToTurn;
		timeoutTime = _timeoutTime;
		drive = new Drive(0,0);
		timer = new Timer();
		//WAS 1.7
		pid = new PidHelper(1.3, 0, 0, 0.5/180);
		reversed = _reversed;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
	   	timer.reset();
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
		power = pid.run( 1 , angleDif / degreesToTurn, timer.get());

		if (reversed){
			drive.setLeftPower(power);
			drive.setRightPower(-power);
		} else {
			drive.setLeftPower(-power);
			drive.setRightPower(power);
		}
		
		drive.execute();
//		RobotMap.DriveFrontRightTalon.set(power);
//		RobotMap.DriveRearRightTalon.set(power);
//		RobotMap.DriveFrontLeftTalon.set(power);
//		RobotMap.DriveRearLeftTalon.set(power);
		
		if(pid.isFinished() || timer.get() > timeoutTime){
			isFinished = true;
			Logging.DebugPrint("finished");
		}
	}
	
	/**
	 * Called when the Task has finished.
	 */
	@Override
	public void end(){
		drive.setPower(0);
		drive.execute();
	}
}
