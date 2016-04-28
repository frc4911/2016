package org.usfirst.frc.team4911.helpers;

import org.usfirst.frc.team4911.updators.NewCurrentManager;

import edu.wpi.first.wpilibj.*;

/**
 * Motor class holds all motor data and sensors.
 * 
 * @author Luke Caughell
 */
public class Motor {

	// related sensors
	CANTalon talon;
	Encoder encoder;
	AnalogPotentiometer potentiometer;
	
	// any required pid scalars
	double p;
	double i;
	double d;
	double threshold;
	
	NewCurrentManager currentManager;
	


	/**
	 * Constructor
	 * Sets the basic parameters.
	 * @param talon the talon to set for this motor
	 * @param encoder an encoder object or null
	 * @param potentiometer a potentiometer object or null
	 * @param p a proportional scalar value or 0
	 * @param i an integral scalar value or 0
	 * @param d a derivative scalar value or 0
	 */
	public Motor(CANTalon talon, Encoder encoder, AnalogPotentiometer potentiometer, double p, double i, double d) {
		this.talon = talon;
		this.encoder = encoder;
		this.potentiometer = potentiometer;
		this.p = p;
		this.i = i;
		this.d = d;
	}
	public Motor(CANTalon talon, Encoder encoder, AnalogPotentiometer potentiometer, double p, double i, double d,double _threshold) {
		this.talon = talon;
		this.encoder = encoder;
		this.potentiometer = potentiometer;
		this.p = p;
		this.i = i;
		this.d = d;
		this.threshold = _threshold;
	}
	public Motor(CANTalon talon, Encoder encoder, AnalogPotentiometer potentiometer, double p, double i, double d, NewCurrentManager _currentManager) {
		this.talon = talon;
		this.encoder = encoder;
		this.potentiometer = potentiometer;
		this.p = p;
		this.i = i;
		this.d = d;
		currentManager = _currentManager;
	}
	
	
	
	public void setPower(double power){
		if (currentManager != null){
			talon.set(power*currentManager.getPower());
		}else{
			talon.set(power);
		}
	}

	/**
	 * Gets the motor talon
	 * @return the talon object
	 */
	public CANTalon getTalon() {
		return talon;
	}
	
	/**
	 * Gets the encoder object
	 * @return the motor encoder or null
	 */
	public Encoder getEncoder() {
		return encoder;
	}

	/**
	 * Gets the potentiometer object
	 * @return the motor potentiometer or null
	 */
	public AnalogPotentiometer getPotentiometer() {
		return potentiometer;
	}

	/**
	 * Gets the proportional scalar value
	 * @return the proportional scalar value or 0
	 */
	public double getP() {
		return p;
	}

	/**
	 * Gets the integral scalar value
	 * @return the integral scalar value or 0
	 */
	public double getI() {
		return i;
	}

	/**
	 * Gets the derivative scalar value
	 * @return the derivative scalar value or 0
	 */
	public double getD() {
		return d;
	}
	public double getThreshold() {
		// TODO Auto-generated method stub
		return threshold;
	}
}
