package org.usfirst.frc.team4911.updators;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

import org.usfirst.frc.team4911.helpers.Logging;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class Sensors {
/**
 *A subsystem that contains all sensors required for operating the robot.
 *
 * @author Luke Caughell
 *
 */
    
	private static AHRS Imu;
	

    private static PowerDistributionPanel panel;
	
	public static PowerDistributionPanel getPanel() {
		return panel;
	}

	private static double ImuYawValue;
	
	private static double systemCurrentDraw;

	/** 
    * Put methods for controlling this subsystem
    * here. Call these from Commands.
	*/
    public static void init() {
    	
    	

        
        Imu = new AHRS(SPI.Port.kMXP);
        Imu.reset();
        Imu.zeroYaw();
        
        panel = new PowerDistributionPanel(0);

    }
    
    public static double getVoltage(){
    	//return panel.getTotalCurrent();
    	return panel.getVoltage();
    	
    }
    
    public static void update(){
    	systemCurrentDraw = 0;
    	for (int i = 1; i <= 8; i++){
    		systemCurrentDraw += panel.getCurrent(i);
        	//Logging.DebugPrint("Channel: " + i + "Current: " + panel.getCurrent(i));
    	}
    	
    	//Logging.DebugPrint("Computed: " + systemCurrentDraw);
    	//Logging.DebugPrint("Gathered: " + panel.getTotalCurrent());
    	
    	
    	ImuYawValue = Imu.getYaw();
    }
    
    /**
     * Returns the IMU that is defined in RobotMap.
     * @return Imu
     */
    public static AHRS getImu() {
    	
    	return Sensors.Imu;
    }
    
    public static void resetImu() {
    	 Sensors.Imu.reset();
    	 Sensors.Imu.zeroYaw();

    }

	public static double getImuYawValue() {
		return ImuYawValue;
	}
}
