package org.usfirst.frc.team4911.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANTalon FrontRightTalon;
	public static CANTalon FrontLeftTalon;
	public static CANTalon RearRightTalon;
	public static CANTalon RearLeftTalon;
	public static Joystick LeftJoy;
	public static Joystick RightJoy;

	public static void init(){
		RearRightTalon = new CANTalon(1);
		FrontRightTalon = new CANTalon(4);
		FrontLeftTalon = new CANTalon(8);
        RearLeftTalon = new CANTalon(5);
		LeftJoy = new Joystick(0);
		RightJoy = new Joystick(1);

	}
	
}
