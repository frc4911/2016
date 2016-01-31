package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.helpers.RampDownHelper;
import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.Sensors;
import org.usfirst.frc.team4911.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that drives for a specific distance.
 * 
 * @author Luke Caughell
 *
 */
public class DriveForDistance extends Command {
	DriveSystem driveSystem;
	Command teleop;
	
	double driveDistance;
	double power;
	
	double currentEncoderValueInches;
	double prevValueEncoderValueInches = 0;
	
	double currentTime;
	double prevTime = 0;
	
//	double wheelDiameter = 6;
//	double oneRotationInInches = Math.PI * wheelDiameter;
//	double encoderPulsePerRotation = 250;
//	double inchesPerTick = oneRotationInInches / encoderPulsePerRotation;
//	double turnSlipFactor = 1;
	

    
    RampDownHelper rampDown;

    Timer timer;
    
    public DriveForDistance(double _power, double _distance) {
    	// TODO: set all encoder params in the robot map
    	rampDown = new RampDownHelper();
    	driveSystem = Robot.driveSystem;
    	power = _power;
    	driveDistance = _distance;
    	requires(driveSystem);
    }

    /**
     *  Called just before this Command runs the first time
     */
    @Override
    protected void initialize() {
    	Sensors.RightDriveEncoder.reset();
    	System.out.println("initialize got called");
    	teleop = Robot.teleop;
    	((OperatorDrive)teleop).setUsingDriveSystem(true);
    	timer = new Timer();
    	timer.start();
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    @Override
    protected void execute() {
    	// TODO:  get encoder value
    	// TODO:  if value != lastvalue
    	// TODO: 	print value
    	// TODO: 	print time delta
    	currentEncoderValueInches = Sensors.RightDriveEncoder.getDistance();
    	power = rampDown.getRampedPower(driveDistance,currentEncoderValueInches);
    	driveSystem.drive(power, power);
    	currentTime = timer.get();
    	if (currentEncoderValueInches!=prevValueEncoderValueInches){
    		System.out.println("Rate: " + Sensors.RightDriveEncoder.getRate());
    		System.out.println("Stopped: " + Sensors.RightDriveEncoder.getStopped());
    		System.out.println("Encoder: " + currentEncoderValueInches + " Time: " + (currentTime-prevTime));
        	prevTime =  currentTime;
        	prevValueEncoderValueInches = currentEncoderValueInches;
    	}
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    @Override
    protected boolean isFinished() {
    	// TODO:  if encoder value * inchesPerTick >= distance +/- threshold
    	// TODO: 	return true
    	
    	return (driveDistance - Math.abs(currentEncoderValueInches)<=0);
    }
    

    /**
     *  Called once after isFinished returns true
     */
    @Override
    protected void end() {
    	((OperatorDrive)teleop).setUsingDriveSystem(false);
    	driveSystem.stop();
    }

    /** 
     * Called when another command which requires one or more of the same
     *  subsystems is scheduled to run
     */
    @Override
    protected void interrupted() {
    	driveSystem.stop();
    }
    
    /**
     * changes power based on distance to goal 
     * @param goalDistance
     * @param currentDistance
     * @return
     */
//    private double getRampedPower(double goalDistance, double currentDistance){
//        double fractionOfGoalDistance = Math.min(currentDistance / goalDistance, 1.0);        
//        double rampedPower = AMPLITUDE * Math.pow(Math.cos(0.5 * Math.PI * fractionOfGoalDistance) , RAMP_UP) * Math.pow(fractionOfGoalDistance , RAMP_DOWN);
//        rampedPower = Math.min(rampedPower + FLOOR, CEILING);
//        return rampedPower;
//    }

}
