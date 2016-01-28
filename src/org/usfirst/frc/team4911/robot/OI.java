package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4911.robot.commands.*;
//import org.usfirst.frc.team4911.robot.commands.Turn90Clockwise;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick rightJoy;
    public Joystick leftJoy;
    public Joystick payloadJoy;
	
	public OI(){
		rightJoy = new Joystick(0);
		leftJoy = new Joystick(1);
	    payloadJoy = new Joystick(2);
	   
/*	    JoystickButton rightButton1 = new JoystickButton(rightJoy,1);
	    rightButton1.whenPressed(new Turn90Clockwise());*/
	    
	    JoystickButton rightButton5 = new JoystickButton(rightJoy,5);
	    rightButton5.whenPressed(new DriveForTime(0.5,1));
	    
	    JoystickButton rightButton6 = new JoystickButton(rightJoy,6);
	    rightButton6.whenPressed(new DriveForTime(-0.5,1));
	    
	    JoystickButton rightButton3 = new JoystickButton(rightJoy,3);
	    rightButton3.whenPressed(new TurnForTime(0.5,3));
	    
	    JoystickButton rightButton4 = new JoystickButton(rightJoy,4);
	    rightButton4.whenPressed(new TurnForTime(-0.5,3));
	    
//	    JoystickButton rightButton11 = new JoystickButton(rightJoy,11);
	    
	    
	    
	    
//	    rightButton11.whenPressed(new DriveForDistance(0.1,12*5,1));
//	    
//	    JoystickButton rightButton12 = new JoystickButton(rightJoy,12);
//	    rightButton12.whenPressed(new DriveForDistance(-0.1,12*5,1));

	    JoystickButton rightButton11 = new JoystickButton(rightJoy,11);
	    rightButton11.whenPressed(new DriveForDistance(0.5,12*5,0));
	    
	    JoystickButton rightButton12 = new JoystickButton(rightJoy,12);
	    rightButton12.whenPressed(new DriveForDistance(-0.5,12*5,0));
	    
	    JoystickButton payloadButton1 = new JoystickButton(payloadJoy,1);
	    payloadButton1.whenPressed(new ShooterShoot());
	    
	    JoystickButton payloadButton2 = new JoystickButton(payloadJoy,2);
	    payloadButton2.whenPressed(new ShooterIntake());
	    
	    JoystickButton payloadButton3 = new JoystickButton(payloadJoy,6);
	    payloadButton3.whenPressed(new ShooterShootPneumatics());
	    
	    JoystickButton payloadButton4 = new JoystickButton(payloadJoy,5);
	    payloadButton4.whenPressed(new ShooterShootPneumatics2());
	    
	}
    public Joystick getLeftJoy() {
        return leftJoy;
    }

    public Joystick getRightJoy() {
        return rightJoy;
    }
    public Joystick getPayloadJoy(){
        return payloadJoy;
    }
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

