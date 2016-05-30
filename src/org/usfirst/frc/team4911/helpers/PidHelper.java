package org.usfirst.frc.team4911.helpers;

/**
 * A PID controller that returns a motor power when given a target value and an actual value.
 * 
 * @author Anne Gywnne-Robson
 */
public class PidHelper {	
	private double pGain;	// The gain for the error
	private double iGain;	// The gain for the error integral
	private double dGain;	// The gain for the error derivative
	
	// The minimum allowable error. 
	// Our system does not take continuous measurements so if our 
	// current value is within this much of our target value we stop.
	private double threshold;
	
	private double prevError; 		// Keeps track of the previous error value
	private double prevIntegral;	// The previous integral
	private double prevTime; 		// The time at which the controller was last updated
	
	private boolean isFinished;	// We need this to implement the isFinished function
	
	/**
	 * Initializer that sets the gains for the proportional, integral, and derivative 
	 * components as well as the threshold value. Call this once.
	 * @param _pGain the proportional gain scalar
	 * @param _iGain the integral gain scalar
	 * @param _dGain the derivative gain scalar
	 * @param _threshold the minimum error tolerance to specify we have reached the target
	 */
	public PidHelper (double _pGain, double _iGain, double _dGain, double _threshold){
		pGain =  _pGain;
		iGain = _iGain;
		dGain = _dGain;
		threshold = _threshold;
		prevError = 0;
		prevIntegral = 0;
		prevTime = 0;
		isFinished = false;
	}
	
	/**
	 * The function that actually makes the controller go. Call this at least once a cycle.
	 * @param targetValue The value we want to reach. Can be a position, a speed, or anything else we can measure
	 * @param currentValue The value we're currently at.
	 * @param currentTime The current time.
	 * @return A drive power for the motor, NOT normalized to +/-1.
	 */
	public double run(double targetValue, double currentValue, double currentTime){
		// Both I and D depend on the time between the current error and the previous error
		double deltaTime = currentTime-prevTime;
		// P, I, and D are all derived from the difference between where we are and where we want to be
		double error = targetValue - currentValue;
		
		if(Math.abs(error) < threshold){
			isFinished = true;
			return 0;
		}
		isFinished = false;
		
		// The integral is basically the area under a graph of error against time between t = 0 and t = currentTime
		double currentIntegral = (double)(prevIntegral + error * deltaTime);
		if(Math.signum(error)!=Math.signum(prevError)){
			currentIntegral = 0;
		}
		// The derivative is the slope at t = currentTime of the error/time graph the integral is the area under
		double currentDerivative = (double) ((error - prevError)/deltaTime);
		
		prevError = error;
		prevIntegral = currentIntegral;
		prevTime = currentTime;
		
		double output = pGain * error + iGain * currentIntegral + dGain * currentDerivative;
		return output;
	}
	
	/**
	 * True when the error is within the threshold distance of our target value.
	 * @return A boolean reflecting the condition described in the above comment.
	 */
	public boolean isFinished(){
		return isFinished;
	}
	
	/**
	 * Gets the previous error value
	 * @return the previous error
	 */
	public double getError(){
		return prevError;
	}

	/**
	 * Sets the threshold value
	 * @param _threshold The new threshold value to set
	 */
	public void setThreshold(double _threshold) {
		threshold = _threshold;
	}
}