package org.usfirst.frc.team4911.robot.subsystems;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Nav6 extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private SerialPort serial;
	public static IMU imu;

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());

    }
	public void init(){
    	try {
			serial = new SerialPort(57600,SerialPort.Port.kOnboard);
	    	short update_rate_hz = 50;
	    	imu = new IMU(serial,(byte) update_rate_hz);
	    	System.out.println("Serial port worked");
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Serial port failed");
			
		}
    	System.out.print(imu);

	}

    
}

