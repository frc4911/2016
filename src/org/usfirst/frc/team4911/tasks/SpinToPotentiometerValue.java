package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * Spins a given motor to the specified potentiometer value.
 * 
 * @author Luke Caughell
 */
public class SpinToPotentiometerValue extends Task {
	double power;
	Motor motor;
	double targetValue;
	PidHelper pid;
	CANTalon talon;
	AnalogPotentiometer potentiometer;
	Timer timer;
	double clampPower;
	double maxPower;
	
	/**
	 * Constructor
	 * Sets class variables
	 * @param _motor the motor to spin
	 * @param _targetValue the target value in linear inches
	 * @param _maxPower the maximum power to use for spinning the motor
	 */
	public SpinToPotentiometerValue(Motor _motor, double _targetValue, double _maxPower){
		motor = _motor;
		potentiometer = motor.getPotentiometer();
		targetValue = _targetValue;
		talon = motor.getTalon();
		timer = new Timer();
		maxPower = _maxPower;
		pid = new PidHelper(motor.getP(), motor.getI(), motor.getD(), 1);
		
		priority = RobotConstants.LOW_PRI;
	}

	/**
	 * This is called when the command is first added to the task manager
	 */
	@Override
	public void init(){
//		motor.getEncoder().reset();
//		startValue = motor.getEncoder().get();
		potentiometer = motor.getPotentiometer();
		timer.reset();
		timer.start();
		this.isFinished = false;
	}
	
	/**
	 * This is called constantly called by the task manager
	 */
	@Override
	public void execute(){
		double power = pid.run(targetValue, potentiometer.get(), timer.get());
		power = Math.min(maxPower, power);
		power = Math.max(-maxPower, power);

		talon.set(power);
		
		if (pid.isFinished()){
			isFinished = true;
		}
	}
	
	/**
	 * Called when the task has finished.
	 */
	@Override
	public void end(){
		Logging.DebugPrint("Done");
	}
}
