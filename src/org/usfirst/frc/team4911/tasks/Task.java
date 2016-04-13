package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * Base class for all tasks.
 * 
 * @author Luke Caughell
 */
public class Task {
	
	/**
	 * When this is set to true end is called and the command is removed from the task manager
	 */
	protected boolean isFinished = false;
	
	/**
	 * Sets whether another task can interrupt this task.
	 * Defaults to true
	 */
	public boolean interruptible = true;
	
	/**
	 * Only used if interruptible is true
	 * Higher values will take priority over lower values
	 * If priority is equal the task will interrupt 
	 */
	int priority = RobotConstants.ZERO_PRI;
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	public void init(){
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	public void execute(){
	}
	
	/**
	 * This is called by the task manager when getFinished returns true
	 */
	public void end(){
	}
	
	/**
	 * Called instead of end in special cases
	 */
	public void interupt(){
		this.end();
	}
	
	public boolean getFinished() {
		return isFinished;
	}
}
