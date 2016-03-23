package org.usfirst.frc.team4911.helpers;

/**
 * A snapshot of the robot state set up all nicely so that we can
 * generate a log file entry quickly and easily from it. It consists
 * of the following:
 * 
 * Timestamp - the time at which the snapshot was taken
 * Timedelta - the time since the last snapshot
 * LogParameter... - all the various parameters that are being recorded
 * 
 * NOTE: we may very well want this to be a singleton.
 * EVEN MORE NOTE: The fields of this class MUST match those of the 
 * corresponding class in the parser. If they don't, Bad Things 
 * will happen.
 * 
 * @author Anne Gwynne-Robson
 *
 */
public class RobotState {
	public long Timestamp;
	public long Timedelta;
	
	// The various log entries
	public LogParameter BatteryVoltage;
	public LogParameter BatteryCurrent;
	public LogParameter GyroHeading;
	
	/**
	 * Constructor for the state class. All it really does is
	 * initialize all the parameters.
	 */
	public RobotState()
	{
		this.Timestamp = System.currentTimeMillis();
		this.Timedelta = 0;
		this.BatteryVoltage = new LogParameter();
		this.BatteryCurrent = new LogParameter();
		this.GyroHeading = new LogParameter();
	}
	
	/**
	 * Updates all the fields in the state class with their latest values
	 */
	void UpdateState() {
		// Create some dummy values. Doing this as doubles so that it's 
		// a little more like what it will actually be.
		double battVoltage = 4.7;
		double battCurrent = 5.1;
		double gyroHeading = 3.142;
		
		// Do a bit of math to find the timedelta since the last log entry
		long currentTime = System.currentTimeMillis();
		this.Timedelta = currentTime - this.Timestamp;
		this.Timestamp = currentTime;
		
		// Set the values (most of these are dummies)
		this.BatteryVoltage.Name = "Battery voltage (V)";
		this.BatteryVoltage.Value = String.valueOf(battVoltage);
		this.BatteryCurrent.Name = "Battery current (A)";
		this.BatteryCurrent.Value = String.valueOf(battCurrent);
		this.GyroHeading.Name = "Gyro heading (rad)";
		this.GyroHeading.Value = String.valueOf(gyroHeading);
	}
}
