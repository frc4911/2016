// File="Sensors.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *A subsystem that contains all sensors required for operating the robot.
 *
 * @author Tommy Lee
 *
 */
public class Sensors extends Subsystem {
    
	public static Encoder RightDriveEncoder;
	public static Encoder LeftDriveEncoder;
	
	public static AHRS Imu;

	/** 
    * Put methods for controlling this subsystem
    * here. Call these from Commands.
	*/
    public void init() {
    	
    	RightDriveEncoder = new Encoder(0,1,true,EncodingType.k4X);
        RightDriveEncoder.setDistancePerPulse(Math.PI*6/250); 
        RightDriveEncoder.setMinRate(1);
        RightDriveEncoder.setMaxPeriod(0.5);
        RightDriveEncoder.reset();
    	
    	LeftDriveEncoder = new Encoder(2,3,true,EncodingType.k4X);
        LeftDriveEncoder.setDistancePerPulse(Math.PI*6/250); 
        LeftDriveEncoder.setMinRate(1);
        LeftDriveEncoder.setMaxPeriod(0.5);
        LeftDriveEncoder.reset();
        
        Imu = new AHRS(SPI.Port.kMXP);
    }
    
    /**
     * Returns the IMU that is defined in RobotMap.
     * @return Imu
     */
    public AHRS getImu() {
    	
    	return Sensors.Imu;
    }
    
    public void resetImu() {
    	 Sensors.Imu.reset();;
    }
    
    /**
     * Returns the right drive encoder that is
     * on the motor that is defined in the RobotMap.
     * @return RightDriveEncoder
     */
    public Encoder getRightDriveEncoder() {
    	
    	return Sensors.RightDriveEncoder;
    }
    
    /**
     * Returns the left drive encoder that is
     * on the motor that is defined in the RobotMap.
     * @return LeftDriveEncoder
     */
    public Encoder getLeftDriveEncoder() {
    	
    	return Sensors.LeftDriveEncoder;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}