package org.usfirst.frc.team4911.helpers;

/**
 * This class is a helper class that computes PID.
 * 
 * @author Luke Caughell
 */
public class PID {
	float p;
	float i;
	float d;
	float previous_error;
	float integral;
	float previous_integral;
	double lastTime;
	float threshold;
	float output;
	float error;
	public PID (float _p, float _i, float _d, float _threshold){
		p=_p;
		i=_i;
		d=_d;
		threshold = _threshold;
		previous_error = 0;
		previous_integral = 0;
		lastTime = 0;
		
	}
	
	
	public float run(float endAngle, float currentAngle,double currentTime){
		
		double deltaTime = currentTime-lastTime;
//		if (deltaTime > 0.1){
			error = endAngle - currentAngle;
			if(Math.abs(error) < threshold){
				System.out.println("Error: " + error);
				return 0;
			}
			integral = (float) (previous_integral + error *  deltaTime);
			float derivative = (float) ((error - previous_error)/deltaTime);
			output = p*error+ i*integral + d * derivative;
			previous_error = error;
			previous_integral = integral;
			lastTime = currentTime;
			System.out.println("Error: " + error);

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
