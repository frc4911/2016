package org.usfirst.frc.team4911.updators;

import org.usfirst.frc.team4911.helpers.RampDownHelper;
import org.usfirst.frc.team4911.robot.RobotConstants;

public class NewCurrentManager {
	private double power;
	
	private double outputPower;

	private RampDownHelper rampDown;
	
	private double limitingThreshold;
	
	private double voltage;
	
	private double voltageValue;

	public NewCurrentManager(double _limitingThreshold){
		rampDown = new RampDownHelper();
		limitingThreshold = _limitingThreshold;
	}
	
	public void update(){
		voltage = Sensors.getVoltage();
		if (voltage<=RobotConstants.VoltagePowerLimitThreshold){
			voltageValue = Math.abs(voltage - RobotConstants.VoltageBrownOut);
			outputPower = rampDown.getRampedPowerDescending(RobotConstants.VoltageBrownOut,limitingThreshold,voltage);
		} else {
			setToDefaultPowers();
		}
	}
	
	public double calculatePowerValues(double currentVoltagePercent, double thresholdValue){
		if (currentVoltagePercent <= thresholdValue){
			return currentVoltagePercent/thresholdValue;
		}
		else return(1);
	}
	
	public void setToDefaultPowers(){
		voltageValue = 1;
		outputPower = 1;
	}
	
	public double getPower(){
		return outputPower;
	}
}
