package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * A helper class that gets takes a percent complete of an action and returns
 * a value that moves along a cosine wave to ramp power down.
 * 
 * @author Luke Caughell
 */
public class RampDownHelper {
	// Set amplitude of cosine wave.
	double amplitude = RobotConstants.amplitude;
	//Floor sets the minimum power the function will return. Should be lowest possible motor speed.
	double floor = RobotConstants.floor;
	//Ceiling sets the maximum power the function will return  range (Floor-1)
	double ceiling = RobotConstants.ceiling;
	//Ramp up sets the rate at which the motor power ramps up at the start.
	double rampUp = RobotConstants.rampUp;
	//Ramp down is the rate at which the motor ramps down as it nears its goal.
	double rampDown = RobotConstants.rampDown;
	
	/**
	 * Constructor that sets all of the parameters of the ramp function.
	 * @param _amplitude the amplitude of the cosine wave
	 * @param _floor the minimum power to return
	 * @param _ceiling the maximum power to return
	 * @param _rampUp the rate at which the power ramps up
	 * @param _rampDown the rate at which the power ramps down
	 */
	public RampDownHelper(double _amplitude, double _floor, double _ceiling, double _rampUp, double _rampDown){
		amplitude = _amplitude;
		floor = _floor;
		ceiling = _ceiling;
		rampUp = _rampUp;
		rampDown = _rampDown;
	}
	
	/**
	 * Default constructor, uses values from robot constants
	 */
	public RampDownHelper(){
	}
	
	/**
	 * Returns the ramped power based on current distance toward the overall goal distance.
	 * @param goalDistance the target end distance
	 * @param currentDistance the distance currently traveled
	 * @return the ramped down power, clamped to floor and ceiling.
	 */
	public double getRampedPower(double goalDistance, double currentDistance){
        double fractionOfGoalDistance = Math.min(currentDistance / goalDistance, 1.0);        
        double rampedPower = amplitude * Math.pow(Math.cos(0.5 * Math.PI * fractionOfGoalDistance) , rampDown) * Math.pow(fractionOfGoalDistance , rampUp);
        rampedPower = Math.min(rampedPower + floor, ceiling);
        return rampedPower;
    }
	/**
	 * Returns the ramped power based on current distance toward the overall goal distance specifically for a target
	 * that is lower than the current value.
	 * @param goalDistance the target end distance
	 * @param currentDistance the distance currently traveled
	 * @return the ramped down power, clamped to floor and ceiling.
	 */
	public double getRampedPowerDescending(double goalRampValue, double startRampValue, double currentDistance){
        double fractionOfGoalDistance = Math.min((startRampValue-currentDistance) / (startRampValue-goalRampValue), 1.0);        
        double rampedPower = amplitude * Math.pow(Math.cos(0.5 * Math.PI * fractionOfGoalDistance) , rampUp) * Math.pow(fractionOfGoalDistance , rampDown);
        rampedPower = Math.min(rampedPower + floor, ceiling);
        return rampedPower;
    }
}
