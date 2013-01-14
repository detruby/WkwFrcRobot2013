/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.sensor;

import java.util.Enumeration;
import java.util.Vector;

/**
 * class to manage input interrupts.
 */
public class InputInterruptManager extends Vector {

	private static final long serialVersionUID = -3815131899271472260L;
	
	private static Object lock = new Object();
	private static InputInterruptManager instance;

	public static InputInterruptManager getInstance() {
		synchronized (InputInterruptManager.lock) {
			if (null == InputInterruptManager.instance) {
				InputInterruptManager.instance = new InputInterruptManager();
			}
		}
		return InputInterruptManager.instance;
	}

	private InputInterruptManager() {
		super();
	}

	public void add(final DigitalInterruptInput pInput) {
		if (null == pInput) {
			throw new IllegalArgumentException("pInput is null");
		}

		super.addElement(pInput);
	}

	public void remove(final DigitalInterruptInput pInput) {
		if (null == pInput) {
			throw new IllegalArgumentException("pInput is null");
		}

		super.removeElement(pInput);
	}

	public void check() {

		final Enumeration aInputEnumeration = super.elements();

		DigitalInterruptInput anInput = null;

		while (aInputEnumeration.hasMoreElements()) {

			anInput = (DigitalInterruptInput) aInputEnumeration.nextElement();

			if (anInput.isChanged()) {

				new HandlerThread(anInput).start();

			}

		}

	}

	private class HandlerThread extends Thread {

		private DigitalInterruptInput input;

		private HandlerThread(final DigitalInterruptInput pInput) {

			super();

			if (null == pInput) {
				throw new IllegalArgumentException("pInput is null");
			}

			this.input = pInput;
		}

		public void run() {

			this.input.getHandler().handleInputStateChange(this.input);

		}

	}

}
