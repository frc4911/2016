package org.usfirst.frc.team4911.controller;

import edu.wpi.first.wpilibj.Joystick;

public class Dpad {
	Joystick stick;
	int angle;
	boolean pressed;
	boolean released = true;

	
	public Dpad(Joystick _stick, int _angle){
		stick = _stick;
		angle = _angle;
	}
	
	
	public boolean get(){
		if (stick.getPOV()==angle){
			pressed = stick.getRawButton(angle);
			return true;
		}
		return false;
	}
}
