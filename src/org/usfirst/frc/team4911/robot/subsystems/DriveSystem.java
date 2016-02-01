// File="DriveSystem.java" Org="FRC4911" Year="2016"
package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4911.robot.RobotMap;
/**
 * A subsystem of all motors involved in driving the chassis.
 *
 *@author Luke Caughell
 */
public class DriveSystem extends Subsystem {
    
    /** 
    * Put methods for controlling this subsystem
    * here. Call these from Commands.
	*/
    public void initDefaultCommand() {
        /** 
        * Set the default command for a subsystem here.
        * setDefaultCommand(new MySpecialCommand());
        */
    }
    
    /**
     * Sets the power of the drive motors on the left 
     * and right sides of the robot
     * @param leftPower
     * @param rightPower
     */
    public void drive(double leftPower, double rightPower) {
    	RobotMap.FrontRightTalon.set(-rightPower);
    	RobotMap.RearRightTalon.set(-rightPower);
    	RobotMap.FrontLeftTalon.set(leftPower);
    	RobotMap.RearLeftTalon.set(leftPower);
    }
    
    /**
     * Sets all drive motors to 0
     */
    public void stop() {
    	RobotMap.FrontRightTalon.set(0);
    	RobotMap.RearRightTalon.set(0);
    	RobotMap.FrontLeftTalon.set(0);
    	RobotMap.RearLeftTalon.set(0);
    }
}

