package org.usfirst.frc.team4911.controller;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A class that contains all of the buttons we will use to control the robot
 * 
 * 
 * 
 * @author Luke Caughell
 */
public class ControllerMappings {
	// Ports & Operator Inputs
	public static final int rightJoyPort = 0;
	public static final int  leftJoyPort = 1;
	public static final int  payloadJoyPort = 2;
	
	public static Joystick rightJoy = new Joystick(rightJoyPort);
	public static Joystick  leftJoy = new Joystick(leftJoyPort);
	public static Joystick  payloadJoy = new Joystick(payloadJoyPort);
	// Operator Thresholds
	public static final double JoyThreshold = 0.075;
	
	//RIGHT JOY
	
	public static Button rightJukeButton1;
	public static Button rightJukeButton2;
	public static Button rightJukeButton3;
	public static Button rightJukeButton4;
	
	//LEFT JOY
	
	public static Button leftJukeButton1;
	public static Button leftJukeButton2;
	public static Button leftJukeButton3;
	public static Button leftJukeButton4;
	
	//DRIVER SCALE
	public static Button autoScale;
	public static Button manualScale;
	
	
	//PAYLOAD JOY
	public static Button modeButton;

	
	public static Button button5 = new Button(payloadJoy,5);
	public static Button button6 = new Button(payloadJoy,6);
	
	public static Button extenderCycleUpButton;
	public static Button extenderCycleDownButton;
	
	public static Button extenderExtendButton;
	
	public static Button extenderManualUpButton;
	public static Button extenderManualDownButton;

}
