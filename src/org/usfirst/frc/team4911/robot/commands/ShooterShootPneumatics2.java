package org.usfirst.frc.team4911.robot.commands;

import org.usfirst.frc.team4911.robot.Robot;
import org.usfirst.frc.team4911.robot.subsystems.Shooter;
import org.usfirst.frc.team4911.robot.subsystems.ShooterPneumatics;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterShootPneumatics2 extends Command {

	public ShooterPneumatics shooterPneumatics;
    JoystickButton button;

	
    public ShooterShootPneumatics2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooterPneumatics = Robot.shooterPneumatics;
    	button =  new JoystickButton(Robot.oi.payloadJoy,5);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooterPneumatics.setShoot2(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	    return !button.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterPneumatics.setShoot2(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
