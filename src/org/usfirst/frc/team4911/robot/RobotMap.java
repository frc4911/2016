package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.*;

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
	
	public static CANTalon DriveFrontRightTalon;
	public static CANTalon DriveFrontLeftTalon;
	public static CANTalon DriveRearRightTalon;
	public static CANTalon DriveRearLeftTalon;
	
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
		
		DriveFrontRightTalon = new CANTalon(RobotConstants.frontRightMotorPort);
		DriveFrontLeftTalon = new CANTalon(RobotConstants.frontLeftMotorPort);
		DriveRearRightTalon = new CANTalon(RobotConstants.rearRightMotorPort);
        DriveRearLeftTalon = new CANTalon(RobotConstants.rearLeftMotorPort);

		LeftJoy = new Joystick(RobotConstants.rightJoyPort);
		RightJoy = new Joystick(RobotConstants.leftJoyPort);
	}
	
}
