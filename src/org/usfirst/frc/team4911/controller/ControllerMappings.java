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
	
	public static Button rightJukeButton1 = new Button(rightJoy, 3);
	public static Button rightJukeButton2 = new Button(rightJoy, 4);
	public static Button rightJukeButton3;
	public static Button rightJukeButton4;

	public static Button rightTrigger;
	//LEFT JOY
	
	public static Button leftJukeButton1 = new Button(leftJoy,3);
	public static Button leftJukeButton2 = new Button(leftJoy,4);
	
	public static Button autoSelectorLow = new Button(leftJoy,11);
	public static Button autoSelectorOther = new Button(leftJoy,12);
	
	public static Button leftJukeButton3;
	public static Button leftJukeButton4;
	
	public static Button leftTrigger;
	//DRIVER SCALE
	public static Button autoScale;
	public static Button manualScale;
	
	
	//PAYLOAD JOY
	public static Button modeButton = new Button(payloadJoy, 8);
	
//	public static Button extenderCycleUpButton = new Button(payloadJoy,3);
	public static Button extenderCycleDownButton = new Button(payloadJoy,4);
	public static Button extenderExtendButton = new Button(payloadJoy,9);
	
	//public static Button rollerCycleUpButton = new Button(payloadJoy,1);
	//public static Button rollerCycleDownButton = new Button(payloadJoy,2);
	public static Button rollerCollectPosition = new Button(payloadJoy,3);
	public static Button rollerRoller = new Button(payloadJoy,10);

	public static Button shooterCycleDown = new Button(payloadJoy,1);
	public static Button shooterCycleUp = new Button(payloadJoy,2);

	public static Button ZeroYaw = new Button(rightJoy,1);
	
	public static Button ArmCycleDown = new Button(payloadJoy,3);
	public static Button ArmCycleUp = new Button(payloadJoy,4);
	
	public static Button shooterShoot = new Button(payloadJoy,6);
	public static Button shooterPrime = new Button(payloadJoy,5);
	public static Button payloadManual = new Button (payloadJoy, 7);
	public static Trigger shooterCollect = new Trigger(payloadJoy,2);
	public static Trigger shooterEject = new Trigger(payloadJoy,3);
	
	

}
