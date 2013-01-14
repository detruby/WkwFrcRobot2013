/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.sensor;

/**
 * interface that defines a handler for input interrupts.
 */
public interface DigitalInputInterruptHandler {

	public void handleInputStateChange(final DigitalInterruptInput pDigitalInput);

}
