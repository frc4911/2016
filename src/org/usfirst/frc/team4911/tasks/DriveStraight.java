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
public class DriveStraight extends Task{
	Timer timer;
	double driveTime;
	double power;
	double adjustedPower;
	double driveDistance;
	double currentEncoderValueInches;
	double currentDegree;
	double startDegree;
	double degreesToTurn;
	double basePower;
	Drive drive;
	int i;
	PidHelper pid;
	Motor motor;
	boolean reversed;

	
	/**
	 * Constructor
	 * Sets the class variables.
	 * 
	 * @param _power the motor power to set for turning
	 * @param _degreesToTurn the number of degrees to turn 0 to 360
	 * @param _motor the motor object to turn
	 */
	public DriveStraight(double _degreesToTurn, double _basePower, boolean _reversed){
		interruptible = false;
		degreesToTurn = _degreesToTurn;
		drive = new Drive(0, 0);
		this.priority = RobotConstants.MED_PRI;
		reversed = _reversed;
		basePower = _basePower;

	}
//	public DriveStraight(){
//		interruptible = false;
//		drive = new Drive(0, 0);
//		this.priority = RobotConstants.LOW_PRI;
//	}
	
	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		pid = new PidHelper(0.25, 0, 0, 2);
	   	timer = new Timer();
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
	//	double angleDif = GetTargetAngleHelper.computeAngleBetween(startDegree, currentDegree);
		power = pid.run(degreesToTurn, currentDegree , timer.get());
		//power = Math.min(power, 0.3);
		//power = Math.max(power, -0.3);
		
		//if (reversed){power = -power;};
		
		if (pid.isFinished()){
			//0.3 for rock wall
			//0.4 for rock wall and ramparts
			power = basePower;
			if (reversed){power = -power;};
			drive.setRightPower(power);
			drive.setLeftPower(power);
		} else {
			if (!reversed){
				if (power < 0){
					drive.setRightPower(-power);
				}else{
					drive.setLeftPower(power);
				}
			}
			else {
				if (power > 0){
					drive.setRightPower(-power);
				}else{
					drive.setLeftPower(power);
				}
			}
		}
//		Logging.DebugPrint("RIGHT"+drive.getRightPower());
//		Logging.DebugPrint("LEFT"+drive.getLeftPower());

		drive.execute();
		
		isFinished = false;
	}
	
	/**
	 * Called when the Task has finished.
	 */
	@Override
	public void end(){
		drive.setPower(0);
		drive.execute();
	}
	public void setTargetHeading(double target){
		degreesToTurn = target;
	}
	public void setFinished(boolean value){
		isFinished = value;
	}
}
