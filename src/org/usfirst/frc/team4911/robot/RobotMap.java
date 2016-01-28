package org.usfirst.frc.team4911.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANTalon RightShooterTalon;
	public static CANTalon LeftShooterTalon;

	public static CANTalon FrontRightTalon;
	public static CANTalon FrontLeftTalon;
	public static CANTalon RearRightTalon;
	public static CANTalon RearLeftTalon;
	
	public static Encoder FrontRightEncoder;
	public static Encoder RearLeftEncoder;
	
	
	public static Solenoid PneumaticCanRight;
	public static Solenoid PneumaticCanLeft;

	public static Joystick LeftJoy;
	public static Joystick RightJoy;

	public static void init(){
		RightShooterTalon = new CANTalon(6);
		LeftShooterTalon = new CANTalon(7);
		RearRightTalon = new CANTalon(4);
		FrontRightTalon = new CANTalon(1);
		FrontLeftTalon = new CANTalon(8);
        RearLeftTalon = new CANTalon(5);
        
		//FrontRightTalon.setFeedbackDevice();
		//RearLeftTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        
//        RearLeftTalon.setFeedbackDevice();
        
        PneumaticCanRight = new Solenoid(0);
        PneumaticCanLeft = new Solenoid(1);
        
        FrontRightEncoder = new Encoder(0,1,true,EncodingType.k4X);
        FrontRightEncoder.setDistancePerPulse(Math.PI*6/250); 
        FrontRightEncoder.setMinRate(1);
        FrontRightEncoder.setMaxPeriod(0.5);
        FrontRightEncoder.reset();
        
		LeftJoy = new Joystick(0);
		RightJoy = new Joystick(1);

	}
	
}
