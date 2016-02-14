package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.RobotConstants;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * Task for triggering a solenoid.
 * 
 * @author Luke Caughell
 */
public class SolenoidTrigger extends Task {
	Solenoid solenoid;
	int channel;
	boolean activated;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param _solenoid the solenoid to trigger
	 * @param _activated the state to set for the solenoid
	 */
	public SolenoidTrigger(Solenoid _solenoid, boolean _activated) {
		solenoid = _solenoid;
		activated = _activated;
		this.priority = RobotConstants.LOW_PRI;
	}
	
	/**
	 * This is called when the command is first added to the task manager
	 */
	public void init() {
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	public void execute() {
		Logging.DebugPrint("" + activated);
		solenoid.set(activated);
		isFinished = true;
	}
	
	/**
	 * Called when the task finishes.
	 */
	public void end() {
	}
}
