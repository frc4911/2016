package org.usfirst.frc.team4911.updators;

import org.usfirst.frc.team4911.robot.RobotConstants;

public class CurrentManager {
	private static double drivePower;
	private static double rollerPower;
	private static double extenderPower;
	private static double scalePower;
	private static double shooterPower;
	
	private static double driveLimitingPercent = RobotConstants.DRIVE_VOLTAGE_ALLOCATION;
	private static double rollerLimitingPercent = RobotConstants.ROLLER_VOLTAGE_ALLOCATION;
	private static double extenderLimitingPercent = RobotConstants.EXTENDER_VOLTAGE_ALLOCATION;
	private static double scaleLimitingPercent = RobotConstants.SCALE_VOLTAGE_ALLOCATION;
	private static double shooterLimitingPercent = RobotConstants.SHOOTER_VOLTAGE_ALLOCATION;
	
	private static double voltage;
	private static double voltageValue;

	public static void update(){		
		voltage = Sensors.getVoltage();
		if (voltage<=RobotConstants.VoltagePowerLimitThreshold) {
			voltageValue = Math.abs(voltage - RobotConstants.VoltageBrownOut);
			drivePower = calculatePowerValues(voltageValue, driveLimitingPercent);
			rollerPower = calculatePowerValues(voltageValue, rollerLimitingPercent);
			extenderPower = calculatePowerValues(voltageValue, extenderLimitingPercent);
			scalePower = calculatePowerValues(voltageValue, scaleLimitingPercent);
			shooterPower = calculatePowerValues(voltageValue, shooterLimitingPercent);
		} else {
			setToDefaultPowers();
		}
	}
	
	public static double calculatePowerValues(double currentVoltagePercent, double thresholdValue){
		if (currentVoltagePercent <= thresholdValue) {
			return currentVoltagePercent/thresholdValue;
		} else {
			return(1);
		}
	}
	
	public static void setToDefaultPowers(){
		voltageValue = 1;
		drivePower = 1;
		rollerPower = 1;
		extenderPower = 1;
		scalePower = 1;
		shooterPower = 1;
	}
	
	public static double getDrivePower() {
		return drivePower;
	}

	public static double getRollerPower() {
		return rollerPower;
	}

	public static double getExtenderPower() {
		return extenderPower;
	}

	public static double getScalePower() {
		return scalePower;
	}

	public static double getShooterPower() {
		return shooterPower;
	}
}