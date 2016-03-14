package org.usfirst.frc.team4911.updators;

import java.util.ArrayList;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.tasks.Drive;
import org.usfirst.frc.team4911.tasks.DriveStraight;
import org.usfirst.frc.team4911.tasks.Task;

import edu.wpi.first.wpilibj.Timer;

public class AutoTaskManager {
	//A list of all current running tasks on the robot
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<Double> times  = new ArrayList<Double>();

	Task currentTask;
	Timer timer;
	
	public void init(){
		timer = new Timer();
		timer.start();
	}
	
	public void update(){
		// hack for drive
		if (tasks.size() > 0){
			currentTask = tasks.get(0);
			if (!currentTask.getFinished() && times.get(0) > timer.get()){
				currentTask.execute();
			}else{
				tasks.get(0).end();
				tasks.remove(0);
				times.remove(0);
				timer.reset();
				if (tasks.size() > 0){
					tasks.get(0).init();
				}
			}
		}
	}

	public void stopAll(){

	}
	
//	public void addTask(Task newTask){
//		newTask.init();
//		tasks.add(newTask);
//	}

	public void addTask(Task newTask, double time) {
		// TODO Auto-generated method stub
		newTask.init();
		tasks.add(newTask);
		times.add(time);		
	}

}