package org.usfirst.frc.team4911.controller;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A class that contains all of the values that will remain constant on the robot.
 * 
 * Use it for things like: serial Ports, constant things like wheel diameter, and
 * constants for functions like motor power. Basically anything that would otherwise
 * be a random number in the middle of your code.
 * 
 * @author Luke Caughell
 */
public class ControllerMappings {
	// Operator Input
	public static final int rightJoyPort = 0;
	public static final int  leftJoyPort = 1;
	public static final int  payloadJoyPort = 2;
	
	public static Joystick rightJoy = new Joystick(rightJoyPort);
	public static Joystick  leftJoy = new Joystick(leftJoyPort);
	public static Joystick  payloadJoy = new Joystick(payloadJoyPort);
	// Operator Thresholds
	public static final double JoyThreshold = 0.075;
	
	public static Button modeButton;
	
	public static Button extenderCycleUpButton;
	public static Button extenderCycleDownButton;
	
	public static Button extenderManualUpButton;
	public static Button extenderManualDownButton;


	
	public static void init(){
		extenderCycleUpButton = new Button(rightJoy,5);
	}
}
