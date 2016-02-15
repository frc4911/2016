package org.usfirst.frc.team4911.tasks;

import java.util.ArrayList;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.RobotConstants;

/**
 * Used to cycle through several positional tasks given an array
 * of SpinToEncoder or SpinToPotentiometer Tasks.
 * 
 * @author Luke Caughell
 */
public class CycleTask extends Task{
	double power;
	Task currentTask;	
	Task[] tasks;
	int currentIndex;
	
	/**
	 * Constructor
	 * Sets local variables.
	 * @param _tasks the list of tasks to set
	 */
	public CycleTask(Task[] _tasks){
		tasks = _tasks;
		currentIndex = 0;
		currentTask = tasks[currentIndex];
		this.priority = RobotConstants.MED_PRI;
		Logging.DebugPrint("test ctor, current index: " + currentIndex);
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		currentTask.init();
		Logging.DebugPrint("test init, current index: " + currentIndex);
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		currentTask.execute();
		
		if (currentTask.getFinished()){
			this.isFinished = true;
		}
	}
	
	/**
	 * Called when the Task has finished.
	 */
	@Override
	public void end(){
		Logging.DebugPrint("test end, current index: " + currentIndex);
	}
	
	/**
	 * Updates the current task to the next task in the array.
	 */
	public void CycleUp(){
		if(currentIndex < tasks.length - 1){
			currentTask.end();
			currentIndex += 1;
			currentTask = tasks[currentIndex];
			currentTask.init();
			this.isFinished = false;
			Logging.DebugPrint("cycle up");
		}
	}
	
	/**
	 * Updates the current task to the previous task in the array.
	 */
	public void CycleDown(){
		if(currentIndex > 0){
			currentTask.end();
			currentIndex -= 1;
			currentTask = tasks[currentIndex];
			currentTask.init();
			this.isFinished = false;
			Logging.DebugPrint("cycle down");
		}
	}
}