package org.usfirst.frc.team4911.robot.subsystems;

import org.usfirst.frc.team4911.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterPneumatics extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setShoot(boolean value){
    	RobotMap.PneumaticCanRight.set(value);
    	
    }
    public void setShoot2(boolean value){
    	RobotMap.PneumaticCanLeft.set(value);
    	
    }
    
}

