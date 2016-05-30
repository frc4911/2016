package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotConstants;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * Task for triggering a single state solenoid.
 * 
 * @author Luke Caughell
 */
public class SolenoidTrigger extends Task {
	Solenoid solenoid;
	int channel;
	boolean value;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param _solenoid the solenoid to trigger
	 * @param _activated the state to set for the solenoid
	 */
	public SolenoidTrigger(Solenoid _solenoid, boolean _value) {
		solenoid = _solenoid;
		value = _value;
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
		solenoid.set(value);
		isFinished = true;
	}
	
	/**
	 * Called when the task finishes.
	 */
	public void end() {
	}
}
