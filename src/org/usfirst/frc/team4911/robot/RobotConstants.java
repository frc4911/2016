package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.helpers.Motor;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;

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
	public static final int  payloadJoyPort = 2;
	public static final double JoyThreshold = 0.075;

	//Encoder
	public static final int frontRightEncoderPortA = 0;
	public static final int frontRightEncoderPortB = 1;
	
	public static final double wheelDiameter = 6;
	public static final double encoderPulsePerRotation = 250;
	public static final double encoderDistancePerPulse = wheelDiameter * Math.PI / encoderPulsePerRotation;
	
	//Drive System Constants
	public static final int frontRightMotorPort = 1;
	public static final int frontLeftMotorPort = 8;
	public static final int rearRightMotorPort = 4;
	public static final int rearLeftMotorPort = 5;
	
	
	//TODO: add ports and set to final as we get port values
	
	//DRIVE
	public static int DriveFrontRightTalonPort;
	public static int DriveMidRightTalonPort;
	public static int DriveRearRightTalonPort;
	public static int DriveFrontLeftTalonPort;
	public static int DriveMidLeftTalonPort;
	public static int DriveRearLeftTalonPort;
	
	public static int DriveRightEncoderPort;
	public static int DriveLeftEncoderPort;
	
	//ROLLER
	public static int RollerBarTalonPort;
	public static int RollerRollerTalonPort;
	
	public static int RollerBarSolenoidPort;
	
	public static int RollerPotentiometerPort;

	
	//SCALE
	public static int ScaleRightTalonPort;
	public static int ScaleLeftTalonPort;
	
	public static int ScaleRightEncoderPortA;
	public static int ScaleRightEncoderPortB;
	public static int ScaleLeftEncoderPortA;
	public static int ScaleLeftEncoderPortB;
	
	public static int ScaleSolenoidPort;
	
	//SHOOTER
	public static int ShooterLeftTalonPort;
	public static int ShooterRightTalonPort;
	public static int ShooterLiftTalonPort;
	
	public static int ShooterLeftSolenoidPort;
	public static int ShooterRightSolenoidPort;

	public static int ShooterEncoderPortA;
	public static int ShooterEncoderPortB;
	
	//EXTENDER
	public static int ExtenderPort;
	public static int ExtenderPotentiometerPort;
	public static int ExtenderSolenoidPort;
	
	
	
	
	//Robot Size Constants
	
	//RampDownHelper constants 
	public static final double amplitude = 20;
	public static final double floor = 0.2;
	public static final double ceiling = 0.5;
	public static final double rampUp = 1.0;
	public static final double rampDown = 15;
	
	//IMU min and max angles
	public static final double maxAngle = 180;
	public static final double minAngle = -180;

	// True if we want to produce debug logs, false otherwise.
	public static boolean debug = true;
	
	//PID VALUES
	public static final double pDefault = 1;
	public static final double iDefault = 0;
	public static final double dDefault = 0;
	
	// TASKS
	public static final int NUM_TASKS = 5;
	public static final int DRIVE_TASK = 0;
	public static final int SHOOTER_TASK = 1;
	public static final int ARM_TASK = 2;
	public static final int EXTENDER_TASK = 3;
	public static final int SCALE_TASK = 4;
	
	// TASK PRIORITY
	public static final int ZERO_PRI = 0;
	public static final int LOW_PRI = 1;
	public static final int MED_PRI = 2;
	public static final int HIGH_PRI = 3;
	public static final int UBER_PRI = 4;
	
	//VOLTAGE LIMITING CONSTATNS
	public static final double VoltagePowerLimitThreshold = 12;
	public static final double VoltageBrownOut = 11.5;
	
	public static final double DRIVE_VOLTAGE_ALLOCATION = 0.5;
	public static final double SHOOTER_VOLTAGE_ALLOCATION = 0.1;
	public static final double EXTENDER_VOLTAGE_ALLOCATION = 0.2;
	public static final double ROLLER_VOLTAGE_ALLOCATION = 0.3;
	public static final double SCALE_VOLTAGE_ALLOCATION = 0.2;
	
}
