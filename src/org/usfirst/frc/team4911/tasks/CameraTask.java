package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotMap;

public class CameraTask extends Task{
	private double objectHeight;
	private double focalLength;
	private double imageHeight;
	private double sensorHeight;
	
	/**
	 * A class that uses camera data so that
	 * the robot will be able to line itself up
	 * with the goal.
	 * 
	 * @param _power the power to set for the motor
	 */
	public CameraTask(double _objectHeight,double _focalLength,double _imageHeight,double _sensorHeight){
		objectHeight = _objectHeight;
		focalLength = _focalLength;
		imageHeight = _imageHeight;
		sensorHeight = _sensorHeight;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
//		RobotMap.DriveFrontRightTalon.set(-power);
//		RobotMap.DriveRearRightTalon.set(-power);
//		RobotMap.DriveFrontLeftTalon.set(power);
//		RobotMap.DriveRearLeftTalon.set(power);
		isFinished = true;
	}
	
	/**
	 * Called when the task has ended.
	 */
	@Override
	public void end(){
	}
	
	public double computeDistance(double pixleHeight){
		
		return (focalLength*objectHeight*imageHeight)/(pixleHeight*sensorHeight);
	
	}
}
