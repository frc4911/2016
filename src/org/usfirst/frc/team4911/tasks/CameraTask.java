package org.usfirst.frc.team4911.tasks;

import java.util.LinkedList;
import org.usfirst.frc.team4911.helpers.ClampHelper;
import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CameraTask extends Task{
	private double objectHeight;
	private double focalLength;
	private double imageHeight;
	private double sensorHeight;
	private double height;
	private double xPosition;
	private double drivePower;

	private double maxPower;
	
	private Timer timer;
	
	private double width;
	
    private NetworkTable cameraTable;
	private LinkedList<Integer> averageList;
	
	private PidHelper centeringPid;
	@SuppressWarnings("unused")
	private PidHelper aimingPid;
	
	private Drive drive;
	private ShooterWheelTask shooterWheel;
	private SpinToTalonValue shooterLift;
	
	private boolean finishedCentering;
	
	
	/**
	 * A class that uses camera data so that
	 * the robot will be able to line itself up
	 * with the goal.
	 * 
	 */
	public CameraTask(double _objectHeight,double _focalLength,double _imageHeight,double _sensorHeight, double _maxPower){
		objectHeight = _objectHeight;
		focalLength = _focalLength;
		imageHeight = _imageHeight;
		sensorHeight = _sensorHeight;
		maxPower = _maxPower;
		averageList = new LinkedList<Integer>();
        cameraTable = NetworkTable.getTable("GRIP/myContoursReport");
        
        centeringPid = new PidHelper(1, 0, 0, 20);
        aimingPid = new PidHelper(0.1, 0, 0, 3);

        shooterWheel = new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 1);
        shooterWheel.setPriority(RobotConstants.UBER_PRI);
        timer = new Timer();
        timer.start();
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		//TODO: add back in and give a proper target value
//		shooterLift = new SpinToTalonValue(RobotMap.ShooterLiftMotor, "target angle value", 0.7);
		drive = new Drive(0, 0);
		shooterLift.setPriority(RobotConstants.UBER_PRI);
		drive.setPriority(RobotConstants.UBER_PRI);
		finishedCentering = false;
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
    	if (cameraTable.getNumberArray("height", new double[0]).length != 0){
    		height = cameraTable.getNumberArray("height", new double[0])[0];
    	} else {
    		height = 0;
    	}
    	
    	if (cameraTable.getNumberArray("centerX", new double[0]).length != 0){
    		xPosition = cameraTable.getNumberArray("centerX", new double[0])[0];
    	} else {
    		xPosition = 0;
    	}
    	
    	if (cameraTable.getNumberArray("width", new double[0]).length != 0){
    		width = cameraTable.getNumberArray("width", new double[0])[0];
    	} else {
    		width = 0;
    	}
    	
    	centeringPid.setThreshold(width);
    	drivePower = centeringPid.run(RobotConstants.cameraWidth / 2, xPosition, timer.get());

    	if (centeringPid.isFinished() && !finishedCentering){
    		drivePower = 0.2;
    		finishedCentering = true;
    	} else {
    		drivePower = ClampHelper.clamp(drivePower, -maxPower, maxPower);
    		drive.drive(drivePower, drivePower);
    	}
    	
    	// Adds tasks to the task manager
    	Robot.taskManager.addDriveTask(drive);
    	Robot.taskManager.addShooterTask(shooterLift);
    	
    	Logging.DebugPrint("DISTANCE: "+computeDistance(run(height))*0.0394);
//    	Logging.DebugPrint("HEIGHT AVERAGE: "+run(height));

		isFinished = false;
	}
	
	/**
	 * Called when the task has ended.
	 */
	@Override
	public void end(){
	}
	
	public double computeDistance(double _height){
		return (focalLength * objectHeight * imageHeight) / (_height * sensorHeight);
	}
	public double run(double value){
		if (averageList.size() < 20) {
			averageList.add((int) value);
		} else {
			averageList.pop();
			averageList.add((int) value);
		}
		
		double averageCount = 0;
		for(Integer i : averageList){
			averageCount += i;
		}
		
		return (averageCount / averageList.size());
	}
}