package org.usfirst.frc.team4911.robot;

import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.updators.CurrentManager;
import org.usfirst.frc.team4911.updators.NewCurrentManager;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Luke Yancey Caughell
 */
public class RobotMap {
	//public static CANTalon RightShooterTalon;
	//public static CANTalon LeftShooterTalon;

	//public static Solenoid PneumaticCanRight;
	//public static Solenoid PneumaticCanLeft;
	
	//DRIVE
	public static CANTalon DriveFrontRightTalon;
	public static CANTalon DriveMidRightTalon;
	public static CANTalon DriveRearRightTalon;
	public static CANTalon DriveFrontLeftTalon;
	public static CANTalon DriveMidLeftTalon;
	public static CANTalon DriveRearLeftTalon;
	
	public static Motor DriveFrontRightMotor;
	public static Motor DriveMidRightMotor;
	public static Motor DriveRearRightMotor;
	public static Motor DriveFrontLeftMotor;
	public static Motor DriveMidLeftMotor;
	public static Motor DriveRearLeftMotor;
	
	public static Encoder DriveRightEncoder;
	public static Encoder DriveLeftEncoder;
	
	public static DoubleSolenoid DriveLeftSolenoid;
	public static DoubleSolenoid DriveSolenoid;

	//ROLLER
	public static CANTalon ArmTalon;
	public static CANTalon RollerTalon;
	
	public static Solenoid ArmSolenoid;

	
	public static AnalogPotentiometer ArmPotentiometer;

	public static Motor ArmMotor;
	public static Motor RollerMotor;
	
	//SCALE
	public static CANTalon ScaleRightTalon;
	public static CANTalon ScaleLeftTalon;
	
	public static Encoder ScaleRightEncoder;
	public static Encoder ScaleLeftEncoder;
	
	public static Solenoid ScaleSolenoid;
	
	public static Motor ScaleRightMotor;
	public static Motor ScaleLeftMotor;
	
	//SHOOTER
	public static CANTalon ShooterLeftTalon;
	public static CANTalon ShooterRightTalon;
	public static CANTalon ShooterLiftTalon;
	
	public static Solenoid ShooterSolenoid;
	public static Solenoid ShooterBrakeSolenoid;

	
	public static Encoder ShooterEncoder;
	
	public static Motor ShooterLeftMotor;
	public static Motor ShooterRightMotor;
	public static Motor ShooterLiftMotor;
	
	//EXTENDER
	public static CANTalon ExtenderTalon;
	public static AnalogPotentiometer ExtenderPotentiometer;
	public static Solenoid ExtenderSolenoid;
	
	public static Motor ExtenderMotor;
	
	//Current Managers
	static NewCurrentManager driveCurrentManager;
	
	//JOYSTICS
	public static Joystick LeftJoy;
	public static Joystick RightJoy;
	

	public static Solenoid compressorSolenoid;

	/**
	 * Initializes sensors and controllers 
	 */
	public static void init(){
		compressorSolenoid = new Solenoid(0,1);
		//RightShooterTalon = new CANTalon(6);
		//LeftShooterTalon = new CANTalon(7);
		
        //PneumaticCanRight = new Solenoid(0);
        //PneumaticCanLeft = new Solenoid(1);
		
		//DRIVE
		driveCurrentManager = new NewCurrentManager(12);
		
		DriveFrontRightTalon = new CANTalon(RobotConstants.frontRightMotorPort);
		DriveMidRightTalon = new CANTalon(RobotConstants.midRightMotorPort);
		DriveRearRightTalon = new CANTalon(RobotConstants.rearRightMotorPort);
		DriveFrontLeftTalon = new CANTalon(RobotConstants.frontLeftMotorPort);
		DriveMidLeftTalon = new CANTalon(RobotConstants.midLeftMotorPort);
        DriveRearLeftTalon = new CANTalon(RobotConstants.rearLeftMotorPort);
        
		DriveSolenoid = new DoubleSolenoid(1,RobotConstants.driveRightSolenoidPortA,RobotConstants.driveRightSolenoidPortB);
//		DriveLeftSolenoid = new DoubleSolenoid(1,RobotConstants.driveRightSolenoidPortA,RobotConstants.driveRightSolenoidPortB);
		
        DriveRightEncoder = new Encoder(RobotConstants.frontRightEncoderPortA, RobotConstants.frontRightEncoderPortB);
        DriveRightEncoder.setDistancePerPulse(RobotConstants.encoderDistancePerPulse);
        
    	DriveFrontRightMotor = new Motor (DriveFrontRightTalon, null, null, 0.01, 0.0, 0.0, null);// Robot.driveCurrentManager);
    	DriveMidRightMotor = new Motor (DriveMidRightTalon, null, null, 0.01, 0.0, 0.0, null); //Robot.driveCurrentManager);
    	DriveFrontLeftMotor = new Motor (DriveFrontLeftTalon, null, null, 0.01, 0, 0, null); //Robot.driveCurrentManager);
    	DriveRearRightMotor = new Motor (DriveRearRightTalon, null, null, 0.01, 0, 0, null); //Robot.driveCurrentManager);
    	DriveMidLeftMotor = new Motor (DriveMidLeftTalon, null, null, 0.01, 0.0, 0.0, null); //Robot.driveCurrentManager);
    	DriveRearLeftMotor = new Motor (DriveRearLeftTalon, null, null, 0.01, 0, 0, null); //Robot.driveCurrentManager);

    	//ROLLER
        
    	ArmTalon = new CANTalon(RobotConstants.ArmTalonPort);
    	RollerTalon = new CANTalon(RobotConstants.RollerTalonPort);
    	
    	ArmSolenoid = new Solenoid(1,RobotConstants.ArmSolenoidPort);
    	
    	ArmPotentiometer = new AnalogPotentiometer(RobotConstants.RollerPotentiometerPort);

    	ArmMotor = new Motor (ArmTalon, null, ArmPotentiometer, 30, 0.0, 0.0);
    	RollerMotor = new Motor (RollerTalon, null, null, 0.01, 0.0, 0.0);

    	//SCALE
    	 
    	ScaleRightTalon = new CANTalon(RobotConstants.ScaleRightTalonPort);
    	ScaleLeftTalon = new CANTalon(RobotConstants.ScaleLeftTalonPort);
    	
    	//ScaleRightEncoder = new Encoder(RobotConstants.ScaleRightEncoderPortA,RobotConstants.ScaleRightEncoderPortB);
    	//ScaleLeftEncoder = new Encoder(RobotConstants.ScaleLeftEncoderPortA,RobotConstants.ScaleLeftEncoderPortB);
    	
    	ScaleSolenoid = new Solenoid(1,RobotConstants.ScaleSolenoidPort);
//    	
    	ScaleRightMotor = new Motor (ScaleRightTalon, null, null, 0.01, 0.0, 0.0);
    	ScaleLeftMotor = new Motor (ScaleLeftTalon, null, null, 0.01, 0.0, 0.0);
    	 
    	//SHOOTER

    	ShooterLeftTalon = new CANTalon(RobotConstants.ShooterLeftTalonPort);
    	ShooterRightTalon = new CANTalon(RobotConstants.ShooterRightTalonPort);
    	ShooterLiftTalon = new CANTalon(RobotConstants.ShooterLiftTalonPort);
    	ShooterLiftTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	
//		ShooterLeftTalon.set(0.5);
//		ShooterRightTalon.set(0.5);
    	
    	ShooterBrakeSolenoid = new Solenoid(1,RobotConstants.ShooterBrakeSolenoidPortA);
    	ShooterSolenoid = new Solenoid(1,RobotConstants.ShooterSolenoidPort);


    	//ShooterEncoder = new Encoder(RobotConstants.ShooterLiftEncoderPort,RobotConstants.ShooterBrakeSolenoidPort);
    	
    	ShooterLeftMotor = new Motor (ShooterLeftTalon, null, null, 0.01, 0.0, 0.0);
    	ShooterRightMotor = new Motor (ShooterRightTalon, null, null, 0.01, 0.0, 0.0);
    	ShooterLiftMotor = new Motor (ShooterLiftTalon, ShooterEncoder == null ? null : ShooterEncoder, null, -1, 0.0, 0.0, 0.1);
    	

    	//EXTENDER

    	ExtenderTalon = new CANTalon(RobotConstants.ExtenderTalonPort);
    	ExtenderPotentiometer = new AnalogPotentiometer(RobotConstants.ExtenderPotentiometerPort);
    	ExtenderSolenoid = new Solenoid(1,RobotConstants.ExtenderSolenoidPortA);
 //   	ExtenderSolenoid = new Solenoid(1,3);
 	
    	ExtenderMotor = new Motor (ExtenderTalon, null, ExtenderPotentiometer, 15, 0, 0);

//        ExtenderSolenoid = new Solenoid(1,1);
           	
    	// CONTROL
		LeftJoy = new Joystick(RobotConstants.rightJoyPort);
		RightJoy = new Joystick(RobotConstants.leftJoyPort);
    	
    	ScaleRightMotor = new Motor (ScaleRightTalon, ScaleRightEncoder, null, 0, 0, 0);
    	ScaleLeftMotor = new Motor(ScaleRightTalon, ScaleRightEncoder, null, 0, 0, 0);
	}
}
