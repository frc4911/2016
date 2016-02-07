package org.usfirst.frc.team4911.updators;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

import org.usfirst.frc.team4911.helpers.Logging;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class Sensors {
/**
 *A subsystem that contains all sensors required for operating the robot.
 *
 * @author Luke Caughell
 *
 */
    
	private static Encoder DriveRightEncoder;
	private static Encoder DriveLeftEncoder;
	private static AHRS Imu;
	
	private static double DriveRightEncoderValue;

    private static PowerDistributionPanel panel;
	
	private static double DriveLeftEncoderValue;
	private static double DriveRightEncoderDistanceValue;
	private static double DriveLeftEncoderDistanceValue;
	
	private static double ImuYawValue;
	
	private static double systemCurrentDraw;

	/** 
    * Put methods for controlling this subsystem
    * here. Call these from Commands.
	*/
    public static void init() {
    	
    	
    	DriveRightEncoder = new Encoder(0,1,true,EncodingType.k4X);
        DriveRightEncoder.setDistancePerPulse(Math.PI*6/250); 
        DriveRightEncoder.setMinRate(1);
        DriveRightEncoder.setMaxPeriod(0.5);
        DriveRightEncoder.reset();
    	
    	DriveLeftEncoder = new Encoder(2,3,true,EncodingType.k4X);
        DriveLeftEncoder.setDistancePerPulse(Math.PI*6/250); 
        DriveLeftEncoder.setMinRate(1);
        DriveLeftEncoder.setMaxPeriod(0.5);
        DriveLeftEncoder.reset();
        
        Imu = new AHRS(SPI.Port.kMXP);
        Imu.reset();
        Imu.zeroYaw();
        
        panel = new PowerDistributionPanel();

    }
    
    public static void update(){
    	systemCurrentDraw = 0;
    	for (int i = 1; i <= 8; i++){
    		systemCurrentDraw += panel.getCurrent(i);
        	//Logging.DebugPrint("Channel: " + i + "Current: " + panel.getCurrent(i));
    	}
    	
    	Logging.DebugPrint("Computed: " + systemCurrentDraw);
    	//Logging.DebugPrint("Gathered: " + panel.getTotalCurrent());
    	
    	DriveRightEncoderValue = DriveRightEncoder.get();
    	DriveLeftEncoderValue = DriveLeftEncoder.get();
    	DriveRightEncoderDistanceValue = DriveRightEncoder.getDistance();
    	DriveLeftEncoderDistanceValue = DriveLeftEncoder.getDistance();
    	
    	ImuYawValue = Imu.getYaw();
    }
    
    /**
     * Returns the IMU that is defined in RobotMap.
     * @return Imu
     */
    public static AHRS getImu() {
    	
    	return Sensors.Imu;
    }
    
    public static void resetImu() {
    	 Sensors.Imu.reset();
    }
    
    /**
     * Returns the right drive encoder that is
     * on the motor that is defined in the RobotMap.
     * @return RightDriveEncoder
     */
    public static Encoder getRightDriveEncoder() {
    	
    	return Sensors.DriveRightEncoder;
    }
    
    /**
     * Returns the left drive encoder that is
     * on the motor that is defined in the RobotMap.
     * @return LeftDriveEncoder
     */
    public static Encoder getLeftDriveEncoder() {
    	
    	return Sensors.DriveLeftEncoder;
	}
    
	public static double getDriveRightEncoderValue() {
		return DriveRightEncoderValue;
	}

	//Getters for all sensor values
	public static double getDriveLeftEncoderValue() {
		return DriveLeftEncoderValue;
	}

	public static double getDriveRightEncoderDistanceValue() {
		return DriveRightEncoderDistanceValue;
	}

	public static double getDriveLeftEncoderDistanceValue() {
		return DriveLeftEncoderDistanceValue;
	}

	public static double getImuYawValue() {
		return ImuYawValue;
	}
}
