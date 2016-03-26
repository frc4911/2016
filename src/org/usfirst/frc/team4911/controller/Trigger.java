package org.usfirst.frc.team4911.controller;

import edu.wpi.first.wpilibj.Joystick;

public class Trigger {
	
	Joystick stick;
	int axis;
	boolean pressed;
	
	public Trigger(Joystick _stick, int _trigger){
		stick = _stick;
		axis = _trigger;
	}
	public double get(){
		return stick.getRawAxis(axis);
	}
	
	public boolean getDown(double threshold){
		if (getPressed(threshold) && pressed == false){
			pressed = getPressed(threshold);
			return true;
		}
		pressed = getPressed(threshold);
		return false;
	}
	public boolean getPressed(double threshold){
		return stick.getRawAxis(axis)>threshold;
	}
	
}
