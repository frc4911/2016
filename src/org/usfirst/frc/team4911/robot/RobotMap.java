package org.usfirst.frc.team4911.robot;

import com.kauailabs.navx.frc.AHRS;

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
	
	public static AHRS Imu;
	
	public static Encoder RightDriveEncoder;
	public static Encoder LeftDriveEncoder;
	
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
		
		RearRightTalon = new CANTalon(4);
		FrontRightTalon = new CANTalon(1);
		FrontLeftTalon = new CANTalon(8);
		RearLeftTalon = new CANTalon(5);
        
        Imu = new AHRS(SPI.Port.kMXP);

        RightDriveEncoder = new Encoder(0,1,true,EncodingType.k4X);
        RightDriveEncoder.setDistancePerPulse(Math.PI*6/250); 
        RightDriveEncoder.setMinRate(1);
        RightDriveEncoder.setMaxPeriod(0.5);
        RightDriveEncoder.reset();
        
        LeftDriveEncoder = new Encoder(0,1,true,EncodingType.k4X);
        LeftDriveEncoder.setDistancePerPulse(Math.PI*6/250); 
        LeftDriveEncoder.setMinRate(1);
        LeftDriveEncoder.setMaxPeriod(0.5);
        LeftDriveEncoder.reset();
        
		LeftJoy = new Joystick(0);
		RightJoy = new Joystick(1);
	}
}
