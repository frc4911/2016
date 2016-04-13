package org.usfirst.frc.team4911.updators;

import org.usfirst.frc.team4911.helpers.Logging;
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
