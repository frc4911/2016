package org.usfirst.frc.team4911.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4911.robot.RobotMap;
/**
 *
 */
public class DriveSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void drive(double leftPower, double rightPower){
    	RobotMap.FrontRightTalon.set(rightPower);
    	RobotMap.RearRightTalon.set(rightPower);
    	RobotMap.FrontLeftTalon.set(-leftPower);
    	RobotMap.RearLeftTalon.set(-leftPower);

    }
    

}

