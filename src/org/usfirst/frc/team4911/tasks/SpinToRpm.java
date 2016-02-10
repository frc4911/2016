package org.usfirst.frc.team4911.tasks;


import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class SpinToRpm extends Task {
	private Encoder encoder;
	private PidHelper pid;
	double targetRate;
	double prevEncoderValue = -1;
	double prevTime = 0;
	CANTalon talon;
	Timer timer;
	double power = 0.1;
	
	/**
	 * 
	 * @param _talon
	 * @param _encoder
	 * @param _targetRate
	 */
	public SpinToRpm(Motor motor, double _targetRate, double _power){
		priority = RobotConstants.LOW_PRI;
		encoder = motor.getEncoder();
		targetRate = _targetRate;
		talon = motor.getTalon();
		timer = new Timer();
		power = _power;
		
		//ANYTHING ABOVE 0.01 DOES TERRIBLE THINGS
		//pid = new PidHelper(0.01,0,0,0);
		pid = new PidHelper(motor.getP(),motor.getI(),motor.getI(),0.0);

	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
		timer.reset();
		timer.start();
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
//			Logging.DebugPrint("ENCODER: " + currentEncoderValue);
			//Added 1 to time to avoid dividing by zero
			double currentTime = timer.get();
//			Logging.DebugPrint("ENCODER DELTA "+((currentEncoderValue-prevEncoderValue)/250 + " TIME DELTA: " + (((currentTime-prevTime)+0.000001)/60)));
			double currentRate = encoder.getRate();
			Logging.DebugPrint("RPM: " + currentRate);
			Logging.DebugPrint("Encoder: " + encoder.get() + " Time: " + currentTime);
			power += pid.run(targetRate, currentRate, currentTime);
			//double power = 0.25;
			
			Logging.DebugPrint("PID: " + power);
			talon.set(power);
			
		isFinished = false;
	}
	
	@Override
	public void end(){
	}
	
	@Override
	public boolean getFinished() {
		return isFinished;
	}
}
