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
     * @return
     */
    public AHRS getImu() {
    	
    	return RobotMap.Imu;
    }
    
    /**
     * Returns the front right encoder that is
     * on the motor that is defined in the RobotMap.
     * @return
     */
    public Encoder getFrontRightEncoder() {
    	
    	return RobotMap.FrontRightEncoder;
    }
    
    /**
     * Returns the rear left encoder that
     * is inside the talon. Defined in the
     * RobotMap.
     * @return
     */
    public double getRearLeftEncoderValue() {
    	
    	return RobotMap.RearLeftTalon.getEncPosition();
    }
}