package org.usfirst.frc.team4911.tasks;


import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 * Spins a motor to a set power or rpm, corrected with PID
 * 
 * @author Luke Caughell
 */
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
	 * Constructor
	 * Sets class variables
	 * @param motor the specified motor to drive
	 * @param _targetRate the expected rpm to spin at
	 * @param _power the power to spin at the target rate
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
		pid = new PidHelper(motor.getP(), motor.getI(), motor.getI(), 0.0);
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
		timer.reset();
		timer.start();
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		double currentTime = timer.get();
		double currentRate = encoder.getRate();
		Logging.DebugPrint("RPM: " + currentRate);
		Logging.DebugPrint("Encoder: " + encoder.get() + " Time: " + currentTime);
		power += pid.run(targetRate, currentRate, currentTime);
		
		Logging.DebugPrint("PID: " + power);
		talon.set(power);
			
		isFinished = false;
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
	}
}
