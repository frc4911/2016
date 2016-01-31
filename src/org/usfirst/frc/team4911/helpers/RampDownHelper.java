package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * A helper class that gets takes a % complete of an action and returns
 * a value that moves along a cosine wave to ramp power down
 * 
 * @author Luke Caughell
 *
 */
public class RampDownHelper {
	// Set amplitude of cos wave.
	double amplitude = RobotConstants.amplitude;
	//Floor sets the min power the function will return. Should be lowest possible motor speed.
	double floor = RobotConstants.floor;
	//Ceiling sets the max power the function will return  range(Floor-1)
	double ceiling = RobotConstants.ceiling;
	//Ramp up sets the rate at which the motor power ramps up at the start.
	double rampUp = RobotConstants.rampDown;
	//Ramp down is the rate at which the motor ramps down as it nears its goal. ()
	double rampDown = RobotConstants.rampDown;
	
	/**
	 * Constructor that sets all of the parameters of the ramp function
	 * @param _amplitude
	 * @param _floor
	 * @param _ceiling
	 * @param _rampUp
	 * @param _rampDown
	 */
	public RampDownHelper(double _amplitude, double _floor, double _ceiling, double _rampUp, double _rampDown){
		amplitude = _amplitude;
		floor = _floor;
		ceiling = _ceiling;
		rampUp = _rampUp;
		rampDown = _rampDown;
	}
	
	public RampDownHelper(){

	}
	
	/**
	 * Returns the ramped power based on (goalDistance/currentDistance)
	 * @param goalDistance
	 * @param currentDistance
	 * @return
	 */
	public double getRampedPower(double goalDistance, double currentDistance){
        double fractionOfGoalDistance = Math.min(currentDistance / goalDistance, 1.0);        
        double rampedPower = amplitude * Math.pow(Math.cos(0.5 * Math.PI * fractionOfGoalDistance) , rampUp) * Math.pow(fractionOfGoalDistance , rampDown);
        rampedPower = Math.min(rampedPower + floor, ceiling);
        return rampedPower;
    }
}
