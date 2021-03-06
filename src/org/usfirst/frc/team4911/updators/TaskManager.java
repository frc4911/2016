package org.usfirst.frc.team4911.updators;

import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;
import org.usfirst.frc.team4911.tasks.Drive;
import org.usfirst.frc.team4911.tasks.ShooterWheelTask;
import org.usfirst.frc.team4911.tasks.SpinToPower;
import org.usfirst.frc.team4911.tasks.Task;

/**
 * A class that executes tasks.
 * Tasks are separated by what system
 * they use on the robot.
 * 
 * @author Luke Caughell
 *
 */
public class TaskManager {
	//A list of all current running tasks on the robot
	private Task[] tasks = new Task[RobotConstants.NUM_TASKS];

	public void init(){
	}
	
	public void update(){
		// hack for drive
		if (tasks[RobotConstants.DRIVE_TASK] == null) {
			this.addDriveTask(new Drive(0,0));
		}
		
		if (tasks[RobotConstants.SHOOTER_WHEELS_TASK] == null) {
			this.addShooterWheelsTask(new ShooterWheelTask(RobotMap.ShooterLeftMotor, RobotMap.ShooterRightMotor, 0));
		}
		
		if (tasks[RobotConstants.ROLLER_TASK] == null) {
			this.addRollerTask(new SpinToPower(RobotMap.RollerMotor, 0));
		}
		
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i] != null) {
				if (tasks[i].getFinished()) {
					//Logging.DebugPrint("Task " + i + " is finished");
					tasks[i].end();
					tasks[i] = null;
				} else {
					//Logging.DebugPrint("Task " + tasks[i] + " executed");
					tasks[i].execute();
				}
			}
		}
	}

	public void stopAll(){
	}
	
	/**
	 * Initializes a task and adds it to the run thing
	 * @param currentTask
	 * @param newTask
	 * @return
	 */
	public Task addTask(Task currentTask, Task newTask){
		//Logging.DebugPrint("Trying to add new task...");
		if (currentTask == null) {
			//Logging.DebugPrint("current task is null.");
			newTask.init();
			return newTask;
		}
		
		if (currentTask.interruptible) {
			if (currentTask.getPriority() <= newTask.getPriority()) {
				//Logging.DebugPrint("current task is interuptible and lower priority.");
				currentTask.interupt();
				newTask.init();
				return newTask;
			}
		}
		
		return currentTask;
	}
	
	public void addDriveTask(Task newTask){
		tasks[RobotConstants.DRIVE_TASK] = addTask(tasks[RobotConstants.DRIVE_TASK],newTask);
	}
	
	public void addShooterTask(Task newTask){
		tasks[RobotConstants.SHOOTER_TASK] = addTask(tasks[RobotConstants.SHOOTER_TASK],newTask);
	}
	
	public void addShooterWheelsTask(Task newTask){
		tasks[RobotConstants.SHOOTER_WHEELS_TASK] = addTask(tasks[RobotConstants.SHOOTER_WHEELS_TASK],newTask);
	}
	
	public void addArmTask(Task newTask){
		tasks[RobotConstants.ARM_TASK] = addTask(tasks[RobotConstants.ARM_TASK],newTask);
	}
	
	public void addExtenderTask(Task newTask){
		tasks[RobotConstants.EXTENDER_TASK] = addTask(tasks[RobotConstants.EXTENDER_TASK],newTask);
	}
	
	public void addScaleTask(Task newTask){
		tasks[RobotConstants.SCALE_TASK] = addTask(tasks[RobotConstants.SCALE_TASK],newTask);
	}
	
	public void addRollerTask(Task newTask){
		tasks[RobotConstants.ROLLER_TASK] = addTask(tasks[RobotConstants.ROLLER_TASK],newTask);
	}
}