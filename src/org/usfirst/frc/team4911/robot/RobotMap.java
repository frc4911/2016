package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Luke Caughell
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
	
	public static CANTalon WinchLeftTalon;
	public static CANTalon WinchRightTalon;
	
	public static Joystick LeftJoy;
	public static Joystick RightJoy;
	
	public static Joystick PayloadLeftJoy;
	public static Joystick PayloadRightJoy;

	/**
	 * Initializes sensors and controllers 
	 */
	public static void init(){
		
		FrontRightTalon = new CANTalon(RobotConstants.frontRightMotorPort);
		FrontLeftTalon = new CANTalon(RobotConstants.frontLeftMotorPort);
		RearRightTalon = new CANTalon(RobotConstants.rearRightMotorPort);
        RearLeftTalon = new CANTalon(RobotConstants.rearLeftMotorPort);
        
        WinchLeftTalon = new CANTalon(RobotConstants.winchLeftMotorPort);
        WinchRightTalon = new CANTalon(RobotConstants.winchRightMotorPort);
        
		LeftJoy = new Joystick(RobotConstants.rightJoyPort);
		RightJoy = new Joystick(RobotConstants.leftJoyPort);

		PayloadLeftJoy = new Joystick(RobotConstants.payloadJoyPort);
		PayloadRightJoy = new Joystick(RobotConstants.payloadJoyPort);
	}
	
}
