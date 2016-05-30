package org.usfirst.frc.team4911.controller;

import edu.wpi.first.wpilibj.Joystick;

public class Button {
	Joystick stick;
	int button;
	boolean pressed;
	boolean released = true;

	public Button(Joystick _stick, int _button){
		stick = _stick;
		button = _button;
	}
	
	public boolean get(){
		return stick.getRawButton(button);
	}
	
	public boolean getDown(){
		if (stick.getRawButton(button) && pressed == false){
			pressed = stick.getRawButton(button);
			return true;
		}
		
		pressed = stick.getRawButton(button);
		return false;
	}
	
	public boolean getUp(){
		if (!stick.getRawButton(button) && released == false){
			released = !stick.getRawButton(button);
			return true;
		}
		
		released = !stick.getRawButton(button);
		return false;
	}
}