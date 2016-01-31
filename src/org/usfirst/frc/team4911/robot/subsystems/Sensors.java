// File="Sensors.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.RobotMap;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *A subsystem that contains all sensors required for operating the robot.
 *
 * @author Tommy Lee
 *
 */
public class Sensors extends Subsystem {
    
	/** 
    * Put methods for controlling this subsystem
    * here. Call these from Commands.
	*/
    public void initDefaultCommand() {
    	/*
         * Set the default command for a subsystem here.
         * setDefaultCommand(new MySpecialCommand());
         */
    }
    
    /**
     * Returns the IMU that is defined in RobotMap.
     * @return Imu
     */
    public AHRS getImu() {
    	
    	return RobotMap.Imu;
    }
    
    /**
     * Returns the right drive encoder that is
     * on the motor that is defined in the RobotMap.
     * @return RightDriveEncoder
     */
    public Encoder getRightDriveEncoder() {
    	
    	return RobotMap.RightDriveEncoder;
    }
    
    /**
     * Returns the left drive encoder that is
     * on the motor that is defined in the RobotMap.
     * @return LeftDriveEncoder
     */
    public Encoder getLeftDriveEncoder() {
    	
    	return RobotMap.LeftDriveEncoder;
	}
}