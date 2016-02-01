package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4911.robot.RobotMap;
/**
 * Involved in controlling the two winch motors.
 *
 * @author Tommy Lee
 */
public class WinchControl extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveWinch(double leftWinchPower, double rightWinchPower) {
    	
    	RobotMap.WinchLeftTalon.set(leftWinchPower);
    	RobotMap.WinchRightTalon.set(rightWinchPower);
    }
}

