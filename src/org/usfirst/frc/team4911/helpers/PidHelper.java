package org.usfirst.frc.team4911.helpers;

/**
 * A PID controller that returns a motor power when given a target value and an actual value.
 * 
 * @author Luke Caughell
 */
public class PidHelper {	
	private float pGain;	// The gain for the error
	private float iGain;	// The gain for the error integral
	private float dGain;	// The gain for the error derivative
	
	// The minimum allowable error. 
	// Our system does not take continuous measurements so if our 
	// current value is within this much of our target value we stop.
	private float threshold;
	
	private float prevError; 	// Keeps track of the previous error value
	private float prevIntegral;	// The previous integral
	private double prevTime; 	// The time at which the controller was last updated
	
	private boolean isFinished;	// We need this to implement the isFinished function
	
	// Initializer that sets the gains for the proportional, integral, and derivative 
	// components as well as the threshold value. Call this once.
	public PidHelper (float _pGain, float _iGain, float _dGain, float _threshold){
		pGain= _pGain;
		iGain=_iGain;
		dGain=_dGain;
		threshold = _threshold;
		prevError = 0;
		prevIntegral = 0;
		prevTime = 0;
		isFinished = false;
	}
	
	/**
	 * The function that actually makes the controller go. Call this at least once a cycle.
	 * @param targetValue - The value we want to reach. Can be a position, a speed, or anything else we can measure
	 * @param currentValue - The value we're currently at.
	 * @param currentTime - The current time.
	 * @return A drive power for the motor. Note that this value is NOT capped at +/-1.
	 */
	public float run(float targetValue, float currentValue,double currentTime){
		// Both I and D depend on the time between the current error and the previous error
		double deltaTime = currentTime-prevTime;
		// P, I, and D are all derived from the difference between where we are and where we want to be
		float error = targetValue - currentValue;
		System.out.println("Error: " + error);
		
		if(Math.abs(error) < threshold){	
			isFinished = true;
			return 0;
		}
		
		// The integral is basically the area under a graph of error against time between t=0 and t=currentTime
		float currentIntegral = (float) (prevIntegral + error *  deltaTime);
		// The derivative is the slope at t=currentTime of the error/time graph the integral is the area under
		float currentDerivative = (float) ((error - prevError)/deltaTime);
		
		prevError = error;
		prevIntegral = currentIntegral;
		prevTime = currentTime;
		
		float output = pGain*error+ iGain*currentIntegral + dGain * currentDerivative;
		
		return output;
	}
	
	/**
	 * True when the error is within the threshold distance of our target value.
	 * @return A boolean reflecting the condition described in the above comment.
	 */
	public boolean isFinished(){
		return isFinished;
	}
}