package org.usfirst.frc.team4911.helpers;

import edu.wpi.first.wpilibj.*;

public class Motor {

	CANTalon talon;
	
	Encoder encoder;
	AnalogPotentiometer potentiometer;
	
	double p;
	double i;
	double d;
	
	/**
	 * 
	 * @param talon 
	 * @param encoder
	 * @param potentiometer
	 * @param p
	 * @param i
	 * @param d
	 */
	public Motor(CANTalon talon, Encoder encoder, AnalogPotentiometer potentiometer, double p, double i, double d) {
		this.talon = talon;
		this.encoder = encoder;
		this.potentiometer = potentiometer;
		this.p = p;
		this.i = i;
		this.d = d;
	}
	
	public Encoder getEncoder() {
		return encoder;
	}

	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}

	public AnalogPotentiometer getPotentiometer() {
		return potentiometer;
	}

	public void setPotentiometer(AnalogPotentiometer potentiometer) {
		this.potentiometer = potentiometer;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public CANTalon getTalon() {
		return talon;
	}

	public void setTalon(CANTalon talon) {
		this.talon = talon;
	}



	
	
}
