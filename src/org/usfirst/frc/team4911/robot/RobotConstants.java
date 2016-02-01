package org.usfirst.frc.team4911.robot;
/**
 * A class that contains all of the values that will remain constant on the robot.
 * 
 * Use it for things like: serial Ports, constant things like wheel diameter, and
 * constants for functions like motor power. Basically anything that would otherwise
 * be a random number in the middle of your code.
 * 
 * @author Luke Caughell
 *
 */
public class RobotConstants {
	//Operator Input
	public static final int rightJoyPort = 0;
	public static final int  leftJoyPort = 1;
	public static final int  payloadJoyPor = 2;

	//Encoder
	public static final int frontRightEncoderPortA = 0;
	public static final int frontRightEncoderPortB = 1;
	
	//Drive System Constants
	public static final int frontRightMotorPort = 1;
	public static final int frontLeftMotorPort = 8;
	public static final int rearRightMotorPort = 4;
	public static final int rearLeftMotorPort = 5;
	
	//Robot Size Constants
	public static double wheelDiameter = 6;
	
	//RampDownHelper constants 
	public static final double amplitude = 20;
	public static final double floor = 5.0;
	public static final double ceiling = 1.0;
	public static final double rampUp = 1.0;
	public static final double rampDown = 15;
	
	//IMU min and max angles
	public static final double maxAngle = 180;
	public static final double minAngle = -180;

	// True if we want to produce debug logs, false otherwise.
	public static boolean debug = true;
}
