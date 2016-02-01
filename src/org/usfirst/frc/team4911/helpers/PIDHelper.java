package org.usfirst.frc.team4911.helpers;

/**
 * This class is a helper class that computes PID.
 * 
 * @author Luke Caughell
 */
public class PIDHelper {
	double p;
	double i;
	double d;
	double previous_error;
	double integral;
	double previous_integral;
	double lastTime;
	double threshold;
	double output;
	double error;
	public PIDHelper (double _p, double _i, double _d, double _threshold){
		p=_p;
		i=_i;
		d=_d;
		threshold = _threshold;
		previous_error = 0;
		previous_integral = 0;
		lastTime = 0;
		
	}
	
	/**
	 * 
	 * @param endAngle range -1 to 1
	 * @param currentAngle range -1 to 1
	 * @param currentTime
	 * @return double
	 */
	public double run(double endAngle, double currentAngle,double currentTime){
		
		double deltaTime = currentTime-lastTime;
//		if (deltaTime > 0.1){
			error = endAngle - currentAngle;
			if(Math.abs(error) < threshold){
				return 0;
			}
			integral = (double) (previous_integral + error *  deltaTime);
			double derivative = (double) ((error - previous_error)/deltaTime);
			output = p*error+ i*integral + d * derivative;
			previous_error = error;
			previous_integral = integral;
			lastTime = currentTime;
			System.out.println(error);
			return output;
//		}else{
//			return (output);
//		}
	}
	public boolean isFinished(){
		if(Math.abs(error) < threshold){
			return true;
		}else{
			return(false);
		}
		
	}
	
	
}
