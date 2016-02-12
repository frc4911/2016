package org.usfirst.frc.team4911.tasks;

import java.util.ArrayList;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.RobotMap;

public class CycleTask extends Task{
	double power;
	Task currentTask;	
	ArrayList<Task> tasks;
	int currentIndex;
	
	public CycleTask(ArrayList<Task> _tasks){
		tasks = _tasks;
		currentIndex = 0;
		currentTask = tasks.get(currentIndex);
	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
		
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		
		currentTask.execute();
		
	}
	
	@Override
	public void end(){
	}
	
	@Override
	public boolean getFinished() {
		return isFinished;
	}
	
	public void CycleUp(){
		if(currentIndex<tasks.size()-1){
			currentTask.end();
			currentIndex += 1;
			currentTask = tasks.get(currentIndex);
			currentTask.init();
			Logging.DebugPrint("cycle up");
		}
	}
	public void CycleDown(){
		if(currentIndex>0){
			currentTask.end();
			currentIndex -= 1;
			currentTask = tasks.get(currentIndex);
			currentTask.init();
			Logging.DebugPrint("cycle down");

		}
	}
}
