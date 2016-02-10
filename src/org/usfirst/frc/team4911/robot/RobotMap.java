package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.helpers.Motor;

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
	
	public static Motor DriveFrontRightMotor;
	public static Motor DriveFrontLeftMotor;
	public static Motor DriveRearRightMotor;
	public static Motor DriveRearLeftMotor;
	
	public static Encoder DriveRightEncoder;
	public static Encoder DriveLeftEncoder;

	public static CANTalon ScaleRightTalon;
	public static CANTalon ScaleLeftTalon;
	
	public static Encoder ScaleRightEncoder;
	public static Encoder ScaleLeftEncoder;
	
	public static Motor ScaleRightMotor;
	public static Motor ScaleLeftMotor;
	
	public static Joystick LeftJoy;
	public static Joystick RightJoy;
	
	public static CANTalon FlyWheelRightTalon;
	public static CANTalon FlyWheelLeftTalon;
	
	public static Encoder FlyWheelRightEncoder;
	public static Encoder FlyWheelLeftEncoder;
	
	public static Motor FlyWheelRightMotor;
	public static Motor FlyWheelLeftMotor;


	/**
	 * Initializes sensors and controllers 
	 */
	public static void init(){
//		RightShooterTalon = new CANTalon(6);
//		LeftShooterTalon = new CANTalon(7);
		
//      PneumaticCanRight = new Solenoid(0);
//      PneumaticCanLeft = new Solenoid(1);
		
		DriveFrontRightTalon = new CANTalon(RobotConstants.frontRightMotorPort);
		DriveRearRightTalon = new CANTalon(RobotConstants.rearRightMotorPort);
		DriveFrontLeftTalon = new CANTalon(RobotConstants.frontLeftMotorPort);
        DriveRearLeftTalon = new CANTalon(RobotConstants.rearLeftMotorPort);
        
        DriveRightEncoder = new Encoder(RobotConstants.frontRightEncoderPortA, RobotConstants.frontRightEncoderPortB);
        DriveRightEncoder.setDistancePerPulse(RobotConstants.encoderDistancePerPulse);
        
    	DriveFrontRightMotor = new Motor (DriveFrontRightTalon, DriveRightEncoder, null, 0.0001, 0.0, 0.0);
    	DriveFrontLeftMotor = new Motor (DriveFrontLeftTalon, null, null, 0, 0, 0);
    	DriveRearRightMotor = new Motor (DriveRearRightTalon, null, null, 0, 0, 0);
    	DriveRearLeftMotor = new Motor (DriveRearLeftTalon, null, null, 0, 0, 0);

		LeftJoy = new Joystick(RobotConstants.rightJoyPort);
		RightJoy = new Joystick(RobotConstants.leftJoyPort);
		
    	FlyWheelRightMotor = new Motor (FlyWheelRightTalon, FlyWheelRightEncoder, null, 0, 0, 0);
    	FlyWheelLeftMotor = new Motor (FlyWheelLeftTalon, FlyWheelRightEncoder, null, 0, 0, 0);
    	
    	ScaleRightMotor = new Motor (ScaleRightTalon, ScaleRightEncoder, null, 0, 0, 0);
    	ScaleLeftMotor = new Motor(ScaleRightTalon, ScaleRightEncoder, null, 0, 0, 0);
    	
	}
	
}
