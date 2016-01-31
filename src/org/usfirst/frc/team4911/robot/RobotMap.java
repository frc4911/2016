package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Luke Caughell
 * 
 */
public class RobotMap {
//	public static CANTalon RightShooterTalon;
//	public static CANTalon LeftShooterTalon;
//
//	public static Solenoid PneumaticCanRight;
//	public static Solenoid PneumaticCanLeft;
	
	public static CANTalon FrontRightTalon;
	public static CANTalon FrontLeftTalon;
	public static CANTalon RearRightTalon;
	public static CANTalon RearLeftTalon;
	
	public static Encoder FrontRightEncoder;
	

	public static Joystick LeftJoy;
	public static Joystick RightJoy;

	/**
	 * Initializes sensors and controllers 
	 */
	public static void init(){
//		RightShooterTalon = new CANTalon(6);
//		LeftShooterTalon = new CANTalon(7);
		
//      PneumaticCanRight = new Solenoid(0);
//      PneumaticCanLeft = new Solenoid(1);
		
		FrontRightTalon = new CANTalon(RobotConstants.frontRightMotorPort);
		FrontLeftTalon = new CANTalon(RobotConstants.frontLeftMotorPort);
		RearRightTalon = new CANTalon(RobotConstants.rearRightMotorPort);
        RearLeftTalon = new CANTalon(RobotConstants.rearLeftMotorPort);

        FrontRightEncoder = new Encoder(RobotConstants.frontRightEncoderPortA,RobotConstants.frontRightEncoderPortB,true,EncodingType.k4X);
        FrontRightEncoder.setDistancePerPulse(Math.PI*RobotConstants.wheelDiameter/250); 
        FrontRightEncoder.setMinRate(1);
        FrontRightEncoder.setMaxPeriod(0.5);
        FrontRightEncoder.reset();
        
		LeftJoy = new Joystick(RobotConstants.rightJoyPort);
		RightJoy = new Joystick(RobotConstants.leftJoyPort);

	}
	
}
