package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 * Spins a given motor to the specified encoder value.
 * 
 * @author Luke Caughell
 */
public class SpinToTalonValue extends Task {
	double power;
	Motor motor;
	double targetValue;
	PidHelper pid;
	CANTalon talon;
	Encoder encoder;
	Timer timer;
	double clampPower;
	double maxPower;
	double setTime;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param _motor the motor to spin
	 * @param _targetValue the target value in linear inches
	 * @param _maxPower the maximum power to use for spinning the motor
	 */
	public SpinToTalonValue(Motor _motor, double _targetValue, double _maxPower, double _setTime){
		motor = _motor;
		targetValue = _targetValue;
		talon = motor.getTalon();
		timer = new Timer();
		maxPower = _maxPower;
		pid = new PidHelper(motor.getP(), motor.getI(), motor.getD(), motor.getThreshold());
		setTime = _setTime;
		priority = RobotConstants.LOW_PRI;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
//		motor.getEncoder().reset();
//		startValue = motor.getEncoder().get();
		pid = new PidHelper(motor.getP(), motor.getI(), motor.getD(), motor.getThreshold());
		timer.reset();
		timer.start();
		this.isFinished = false;
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		double power = pid.run(targetValue, talon.getPosition(), timer.get());
		power = Math.min(maxPower, power);
		power = Math.max(-maxPower, power);

		talon.set(power);
		
		if (pid.isFinished()||timer.get()>setTime){
			isFinished = true;
		}
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
		motor.setPower(0);
		Logging.DebugPrint("Done");
	}
}
