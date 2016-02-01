package org.usfirst.frc.team4911.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team4911.helpers.GetTargetAngleHelper;
import org.usfirst.frc.team4911.helpers.PIDHelper;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4911.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnForDegree extends Command {
	DriveSystem driveSystem;
	Sensors sensors;
	
	private double currentDegree;
	private double degreesToTurn;
	private double endDegree;
	private double power;
	private double adjustedPower;
	Timer timer;
	
	private PIDHelper pid;
	
	
    public TurnForDegree(double _targetDegree, double _power) {
    	degreesToTurn = _targetDegree;
    	power = _power;
    	pid = new PIDHelper(1, 0, 0, 0.5/180);
    	driveSystem = Robot.driveSystem;
    	requires(driveSystem);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	sensors = Robot.sensors;
//    	sensors.resetImu();
    	timer = new Timer();
    	timer.start();
    	endDegree = GetTargetAngleHelper.compute(currentDegree, degreesToTurn);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDegree = sensors.getImu().getYaw();
    	adjustedPower = (pid.run(currentDegree/RobotConstants.maxAngle, endDegree/RobotConstants.maxAngle, timer.get()));
        adjustedPower = Math.min(adjustedPower,power);
        adjustedPower = Math.max(adjustedPower,-power);
    	driveSystem.drive(adjustedPower, -adjustedPower);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return pid.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
