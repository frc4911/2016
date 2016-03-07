package org.usfirst.frc.team4911.updators;

import java.util.ArrayList;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.tasks.Drive;
import org.usfirst.frc.team4911.tasks.Task;

public class AutoTaskManager {
	//A list of all current running tasks on the robot
	private ArrayList<Task> tasks = new ArrayList<Task>();
	Task currentTask;
	
	public void init(){
	}
	
	public void update(){
		// hack for drive
		if (tasks.size()>0){
			currentTask = tasks.get(0);
			if (!currentTask.getFinished()){
				currentTask.execute();
			}else{
				tasks.remove(0);
			}
		}
	}

	public void stopAll(){

	}
	
	public void addTask(Task newTask){
		newTask.init();
		tasks.add(newTask);
	}

}