/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.sensor;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * a DigitalInput that interrupts when the state changes.
 */
public class DigitalInterruptInput extends DigitalInput {

	private boolean currentState;
	private DigitalInputInterruptHandler handler;

	/**
	 * @param channel
	 */
	public DigitalInterruptInput(final int pChannel, final DigitalInputInterruptHandler pHandler) {
		super(pChannel);

		if (null == pHandler) {
			throw new IllegalArgumentException("pHandler is null");
		}

		this.handler = pHandler;
		InputInterruptManager.getInstance().add(this);
	}

	public boolean isChanged() {
		final boolean isChanged = (this.currentState != super.get());
		if (isChanged) {
			this.currentState = super.get();
		}
		return isChanged;
	}

	/**
	 * @return the handler
	 */
	public DigitalInputInterruptHandler getHandler() {
		return handler;
	}

}
