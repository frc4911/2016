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
	public static final int frontRightMotorPort = 10;
	public static final int midRightMotorPort = 11;
	public static final int rearRightMotorPort = 12;
	
	public static final int frontLeftMotorPort = 9;
	public static final int midLeftMotorPort = 8;
	public static final int rearLeftMotorPort = 7;
	


	public static final int driveRightSolenoidPortA = 0;
	public static final int driveRightSolenoidPortB = 2;
	
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
	public static int RollerBarTalonPort = 14;
	public static int RollerRollerTalonPort = 13;
	public static final double RollerPotentiometerMax = 0.819;
	public static final double RollerPotentiometerMin =  0.855;

	
	public static final int RollerBarSolenoidPort = 6;
	


	
	public static int RollerPotentiometerPort = 1;
	
	//SCALE
	public static int ScaleRightTalonPort = 2;
	public static int ScaleLeftTalonPort = 3;
	
	public static int ScaleRightEncoderPortA;
	public static int ScaleRightEncoderPortB;
	public static int ScaleLeftEncoderPortA;
	public static int ScaleLeftEncoderPortB;
	
	public static int ScaleSolenoidPort = 5;
	
	//SHOOTER
	public static int ShooterLeftTalonPort = 3;
	public static int ShooterRightTalonPort = 2;
	public static int ShooterLiftTalonPort = 1;
	
	public static int ShooterBrakeSolenoidPort = 3;

	public static int ShooterLiftEncoderPort = 3;
	public static double ShooterSpinup = 3;
	public static double shooterCollectEcnoderValue = 25;

	
	//EXTENDER
	public static final int ExtenderTalonPort = 6;
	public static final int ExtenderPotentiometerPort = 0;
	public static final double ShooterLiftZero = 0.1774;
	public static final double ExtenderPotentiometerMax =  0.1567;

	public static final double ExtenderWheelClearnace = 0.000;

	
	public static final int ExtenderSolenoidPortA = 7;
//	public static final int ExtenderSolenoidPortB = 1;

	
	//Robot Size Constants (size matters)
	
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
	
	//VOLTAGE LIMITING CONSTANTS
	public static final double VoltagePowerLimitThreshold = 12;
	public static final double VoltageBrownOut = 11.5;
	
	public static final double DRIVE_VOLTAGE_ALLOCATION = 0.5;
	public static final double SHOOTER_VOLTAGE_ALLOCATION = 0.1;
	public static final double EXTENDER_VOLTAGE_ALLOCATION = 0.2;
	public static final double ROLLER_VOLTAGE_ALLOCATION = 0.3;
	public static final double SCALE_VOLTAGE_ALLOCATION = 0.2;
}
