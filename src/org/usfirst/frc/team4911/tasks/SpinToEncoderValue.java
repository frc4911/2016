package org.usfirst.frc.team4911.tasks;

import org.usfirst.frc.team4911.helpers.Logging;
import org.usfirst.frc.team4911.helpers.Motor;
import org.usfirst.frc.team4911.helpers.PidHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;
import org.usfirst.frc.team4911.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class SpinToEncoderValue extends Task {
	double power;
	Motor motor;
	double targetValue;
	PidHelper pid;
	CANTalon talon;
	Encoder encoder;
	Timer timer;
	double clampPower;
	double maxPower;
//	double startValue;
	
	public SpinToEncoderValue(Motor _motor, double _targetValue, double _maxPower){
		motor = _motor;
		encoder = motor.getEncoder();
		pid = new PidHelper(motor.getP(),motor.getI(),motor.getD(),1);
		targetValue = _targetValue;
		talon = motor.getTalon();
		timer = new Timer();
		maxPower = _maxPower;
		
		priority = RobotConstants.LOW_PRI;
	}

	//This is called when the command is first added to the task manager
	@Override
	public void init(){
//		motor.getEncoder().reset();
//		startValue = motor.getEncoder().get();
		timer.reset();
		timer.start();
	}
	
	//This is called constantly called by the task manager
	@Override
	public void execute(){
		double power = pid.run(targetValue, encoder.get(), timer.get());
		power = Math.min(maxPower, power);
		power = Math.max(-maxPower, power);

		talon.set(power);
		
		if (pid.isFinished()){
			isFinished = true;
		}
	}
	
	@Override
	public void end(){
		Logging.DebugPrint("Done");
	}
	
	@Override
	public boolean getFinished() {
		return isFinished;
	}
}
