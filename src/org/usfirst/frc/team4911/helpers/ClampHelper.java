package org.usfirst.frc.team4911.helpers;

public class ClampHelper {
	public static double clamp(double value, double min, double max){
		if (value > max){
			return max;
		}
		if (value < min){
			return min;
		}
		return value;
	}
}
