package org.usfirst.frc.team4911.updators;

import org.usfirst.frc.team4911.helpers.Logging;

public class NewCurrentManager {
	private double outputPower;
	private double limitingThreshold;
	
	private double voltage;

	public NewCurrentManager(double _limitingThreshold){
		limitingThreshold = _limitingThreshold;
		outputPower = 1;
	}
	
	public void update(){
		voltage = Sensors.getVoltage();
		if (voltage<=limitingThreshold){
			Logging.DebugPrint("lowering power, voltage: " + voltage);
			outputPower -= 0.001;
		} else if(outputPower < 0.999) {
			Logging.DebugPrint("raising power, voltage: " + voltage);
			outputPower += 0.002;
		}
	}
	
	public double calculatePowerValues(double currentVoltagePercent, double thresholdValue) {
		if (currentVoltagePercent <= thresholdValue) {
			return currentVoltagePercent / thresholdValue;
		} else {
			return(1);
		}
	}
	
	public void setToDefaultPowers() {
		outputPower = 1;
	}
	
	public double getPower(){
		return outputPower;
	}
}