/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.subsystem;

import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwPrefs;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * represents the vision camera.
 */
public class CameraSystem extends SystemBase implements ITableListener {

	// private static final String SYSTEM_NAME = "Camera";

	private static final String TABLE_NAME = "camera";

	private AxisCamera camera;
	// private ColorImage colorImage;
	// private ParticleAnalysisReport[] reportArray;
	// private final ParticleComparer particleComparer = new ParticleComparer();

	private Servo panServo;
	private Servo tiltServo;

	// private int cameraResolution = 0;
	private double panAngle = 0;
	private double tiltAngle = 0;

	private WkwFrcTarget selectedTarget = null;

	public CameraSystem() {

		super("CameraSystem");

		try {

			this.selectedTarget = null;

			this.initCamera();

			this.initServos();

		} catch (Exception anEx) {
			this.error("CameraSystem()", anEx);
		}
	}

	private void initCamera() {

		this.camera = AxisCamera.getInstance();

		if (null == this.camera) {

			this.error("initMotor()", "Camera not found.", null);

		} else {
			/*
			if (null != this.camera.getExposureControl()) {
				ExposureT anExposureT = this.camera.getExposureControl();
				this.debug("initCamera()", "anExposureT=" + anExposureT.value + ".");
			}

			if (null != this.camera.getExposurePriority()) {
				ExposurePriorityT anExposurePriorityT = this.camera.getExposurePriority();
				this.debug("initCamera()", "anExposurePriorityT=" + anExposurePriorityT.value + ".");
			}

			if (null != this.camera.getResolution()) {
				ResolutionT aResolutionT = this.camera.getResolution();
				this.debug("initCamera()", "aResolutionT width=" + aResolutionT.width + ", height="
						+ aResolutionT.height + ".");
			}

			if (null != this.camera.getRotation()) {
				RotationT aRotationT = this.camera.getRotation();
				this.debug("initCamera()", "aRotationT=" + aRotationT.value + ".");
			}

			if (null != this.camera.getWhiteBalance()) {
				WhiteBalanceT aWhiteBalanceT = this.camera.getWhiteBalance();
				this.debug("initCamera()", "aWhiteBalanceT=" + aWhiteBalanceT.value + ".");
			}
			*/
		}

	}

	private void initServos() {

		this.panServo = new Servo(WkwPrefs.getCameraPanServoChannel());

		//LiveWindow.addActuator("CameraSystem", "panServo", this.panServo);
		
		this.tiltServo = new Servo(WkwPrefs.getCameraTiltServoChannel());

		//LiveWindow.addActuator("CameraSystem", "tiltServo", this.tiltServo);
		
		if ((null == this.panServo) || (null == this.tiltServo)) {

			this.error("initDrive()", "panServo or tiltServo is null.", null);

		} else {

			this.panServo.setAngle(90);
			WkwDashboard.setCameraPanAngle(this.panServo.getAngle());

			this.tiltServo.setAngle(90);
			WkwDashboard.setCameraTiltAngle(this.tiltServo.getAngle());

		}

	}

	protected void initDefaultCommand() {

		// TODO:

	}

	public void updateStatus() {

		try {

			if (null != this.panServo) {
				if (this.panAngle != this.panServo.getAngle()) {
					this.panAngle = this.panServo.getAngle();
					this.debug("updateStatus()",
							"panServo=" + this.panServo.get() + ".");
					WkwDashboard.setCameraPanAngle(this.panServo.getAngle());
				}
			}

			if (null != this.tiltServo) {
				if (this.tiltAngle != this.tiltServo.getAngle()) {
					this.tiltAngle = this.tiltServo.getAngle();
					this.debug("updateStatus()",
							"tiltServo=" + this.tiltServo.get() + ".");
					WkwDashboard.setCameraTiltAngle(this.tiltServo.getAngle());
				}
			}

			/*
			if (null != this.camera) {
				if (this.cameraResolution != this.camera.getResolution().width) {
					this.cameraResolution = this.camera.getResolution().width;
					WkwDashboard.setCameraResolution(this.camera.getResolution().width);
				}
			}
			*/

		} catch (Exception anEx) {
			this.error("updateStatus()", anEx);
		}

	}

	public void startFindingTargets() {

		this.debug("startFindingTargets()", "Called.");

		this.selectedTarget = new WkwFrcTarget();

		final NetworkTable aNetworkTable = NetworkTable
				.getTable(CameraSystem.TABLE_NAME);

		aNetworkTable.addTableListener(this);

	}

	public void stopFindingTargets() {

		this.debug("stopFindingTargets()", "Called.");

		final NetworkTable aNetworkTable = NetworkTable
				.getTable(CameraSystem.TABLE_NAME);

		aNetworkTable.removeTableListener(this);

		this.selectedTarget = null;
	}

	public void valueChanged(ITable pTable, String pKey, Object pValue,
			boolean pIsNew) {

		if (null != this.selectedTarget) {

			this.selectedTarget.setNetworkTableValue(pKey, pValue);

		}
	}

	// public void valueChanged(final String pKey, final Object pValue) {

	// if (null != this.selectedTarget) {

	// this.selectedTarget.setNetworkTableValue(pKey, pValue);

	// }

	// }

	// public void valueConfirmed(final String pKey, final Object pValue) {

	// if (null != this.selectedTarget) {

	// this.selectedTarget.setNetworkTableValue(pKey, pValue);

	// }

	// }

	public boolean isFoundTarget() {
		boolean isFound = false;

		if (null != this.selectedTarget) {

			isFound = this.selectedTarget.isFound();

		}

		return isFound;
	}

	/**
	 * pan (rotate along the horizontal axis) the camera servo left (negative
	 * number) or right (positive number) plus the arg angle (in degrees).
	 * 
	 * @param pAngle
	 *            angle in degrees (0 to 360) to add to the current angle.
	 */
	public void panBy(final double pAngle) {

		if (null != this.panServo) {

			this.panTo(this.panServo.getAngle() + pAngle);

		}

	}

	/**
	 * tilt (rotate along the virtical axis) up (positive number) or down
	 * (negative number) the camera plus the arg angle.
	 * 
	 * @param pAngle
	 *            angle in degrees (0 to 360) to add to the current angle.
	 */
	public void tiltBy(final double pAngle) {

		if (null != this.tiltServo) {

			this.tiltTo(this.tiltServo.getAngle() + pAngle);

		}

	}

	/**
	 * pan (rotate along the horizontal axis) the camera servo left (negative
	 * number) or right (positive number) to the arg angle (in degrees).
	 * 
	 * @param pAngle
	 *            angle in degrees (0 to 360) to pan to.
	 */
	public void panTo(final double pAngle) {

		if (null != this.panServo) {

			try {

				double anAngle = pAngle;

				if (anAngle < 0.000000) {
					anAngle = 0.000000;
				} else if (anAngle > 360.000000) {
					anAngle = 360.000000;
				}

				this.panServo.setAngle(anAngle);
				WkwDashboard.setCameraPanAngle(this.panServo.getAngle());

			} catch (Exception anEx) {
				this.error("panTo()", anEx);
			}

		}

	}

	/**
	 * tilt (rotate along the virtical axis) up (positive number) or down
	 * (negative number) the camera to the arg angle.
	 * 
	 * @param pAngle
	 *            angle in degrees (0 to 360) to tilt to.
	 */
	public void tiltTo(final double pAngle) {

		if (null != this.tiltServo) {

			try {

				double anAngle = pAngle;

				if (anAngle < 0.000000) {
					anAngle = 0.000000;
				} else if (anAngle > 360.000000) {
					anAngle = 360.000000;
				}

				this.tiltServo.setAngle(anAngle);
				WkwDashboard.setCameraTiltAngle(this.tiltServo.getAngle());

			} catch (Exception anEx) {
				this.error("tiltTo()", anEx);
			}

		}

	}

	public double getPanAngle() {
		double anAngle = 0;
		if (null != this.panServo) {
			anAngle = this.panServo.getAngle();
		}
		return anAngle;
	}

	public double getTiltAngle() {
		double anAngle = 0;
		if (null != this.tiltServo) {
			anAngle = this.tiltServo.getAngle();
		}
		return anAngle;
	}

	/*
	public boolean selfTestInInitalState() {
		boolean isOk = true;

		try {

			// TODO:

		} catch (Exception anEx) {
			isOk = false;
			this.reportSelfTestState(CameraSystem.SYSTEM_NAME, "Global", "Exception", false,
					"Camera system caught exception name=" + anEx.getClass().getName()
							+ ", message=" + anEx.getMessage() + ".");
		}

		return isOk;
	}
	*/

	/*
	public void findTarget() {

		if ((null != this.camera) && this.camera.freshImage()) { // check if there is a new image

			BinaryImage aBinaryImage = null;

			try {

				final long aStartTime = System.currentTimeMillis();

				this.debug("findTarget()", "Getting new image from camera.");

				this.colorImage = this.camera.getImage(); // get the image from the camera

				this.debug("findTarget()", "colorImage width=" + this.colorImage.getWidth()
						+ ", height=" + this.colorImage.getHeight() + ".");

				aBinaryImage = this.colorImage.thresholdHSL(242, 255, 36, 255, 25, 255);

				this.reportArray = aBinaryImage.getOrderedParticleAnalysisReports();

				this.colorImage.free();
				aBinaryImage.free();

				this.debug("findTarget()", "Have " + this.reportArray.length + " particles.");

				if (this.reportArray.length > 0) {

					Arrays.sort(this.reportArray, this.particleComparer);

					ParticleAnalysisReport aParticleAnalysisReport = null;

					for (int idx = 0; idx < this.reportArray.length; idx++) {

						aParticleAnalysisReport = this.reportArray[idx];

						double aDegreesOff = -((54.0 / 640.0) * ((aParticleAnalysisReport.imageWidth / 2.0) - aParticleAnalysisReport.center_mass_x));

						switch (idx) {

						case 0:
							this.debug("findTarget()", "Best Particle:     ");
							break;
						case 1:
							this.debug("findTarget()", "2nd Best Particle: ");
							break;
						case 2:
							this.debug("findTarget()", "3rd Best Particle: ");
							break;
						default:
							this.debug("findTarget()", Integer.toString(idx + 1)
									+ "th Best Particle: ");
							break;
						}

						this.debug("findTarget()", "    X: "
								+ aParticleAnalysisReport.center_mass_x + "     Y: "
								+ aParticleAnalysisReport.center_mass_y
								+ "     Degrees off Center: " + aDegreesOff + "     Size: "
								+ aParticleAnalysisReport.particleArea);

						if (idx > 6) {
							break;
						}

					} // end for loop

				} // end if (this.reportArray.length > 0)

				final int aDuration = (int) (System.currentTimeMillis() - aStartTime);
				this.debug("findTarget()", "aDuration=" + aDuration + ".");

			} catch (AxisCameraException anAxisEx) {
				this.error("findTarget()", anAxisEx);
			} catch (NIVisionException aNivEx) {
				this.error("findTarget()", aNivEx);
			} catch (Exception anEx) {
				this.error("findTarget()", anEx);
			} finally {
				if (null != aBinaryImage) {
					try {
						aBinaryImage.free();
					} catch (NIVisionException e) {
						// nothing here
					}
				}
			}

		} // end if

	}
	*/

	/*
		class ParticleComparer implements Comparer {

			public int compare(ParticleAnalysisReport p1, ParticleAnalysisReport p2) {
				float p1Ratio = p1.boundingRectWidth / p1.boundingRectHeight;
				float p2Ratio = p2.boundingRectWidth / p2.boundingRectHeight;

				if (Math.abs(p1Ratio - p2Ratio) < 0.1) {
					return -(Math.abs((p1.imageWidth / 2) - p1.center_mass_x))
							- Math.abs(((p2.imageWidth / 2) - p2.center_mass_x));
				} else {
					if (Math.abs(p1Ratio - 1) < Math.abs(p2Ratio - 1)) {
						return 1;
					} else {
						return -1;
					}
				}
			}

			// overloaded method because the comparitor uses Objects (not Particles)
			public int compare(Object o1, Object o2) {
				return compare((ParticleAnalysisReport) o1, (ParticleAnalysisReport) o2);
			}
		}
		*/

	public class WkwFrcTarget {

		private static final String NETWORKTABLE_NAME_ROBOT_SELECTED_TARGET = "Robot Selected Target";
		private static final String NETWORKTABLE_NAME_ROBOT_DISTANCE = "Robot Selected Target Distance";
		private static final String NETWORKTABLE_NAME_ROBOT_HEIGHT = "Robot Selected Target Height";
		private static final String NETWORKTABLE_NAME_FOUND = "Robot Selected Target Found";
		private static final String NETWORKTABLE_NAME_X = "Robot Selected Target X Offset";

		private String name = "";
		private boolean found = false;
		private double degreesOffTarget = 0;
		private double distance = 0;
		private double height = 0;

		public WkwFrcTarget() {
			super();
			this.name = "";
			this.found = false;
			this.degreesOffTarget = 0;
			this.distance = 0;
			this.height = 0;
		}

		public void setNetworkTableValue(final String pKey, final Object pValue) {

			CameraSystem.this.debug("setNetworkTableValue()", "pKey=" + pKey
					+ ", pValue=" + pValue + ".");

			if (WkwFrcTarget.NETWORKTABLE_NAME_FOUND.equals(pKey)
					&& (null != pValue) && (pValue instanceof Boolean)) {
				this.found = ((Boolean) pValue).booleanValue();
			}

			if (WkwFrcTarget.NETWORKTABLE_NAME_X.equals(pKey)
					&& (null != pValue) && (pValue instanceof Double)) {
				this.degreesOffTarget = ((Double) pValue).doubleValue();

				// this.adjustCameraPan();
			}

			if (WkwFrcTarget.NETWORKTABLE_NAME_ROBOT_DISTANCE.equals(pKey)
					&& (null != pValue) && (pValue instanceof Double)) {
				this.distance = ((Double) pValue).doubleValue();
			}

			if (WkwFrcTarget.NETWORKTABLE_NAME_ROBOT_HEIGHT.equals(pKey)
					&& (null != pValue) && (pValue instanceof Double)) {
				this.height = ((Double) pValue).doubleValue();
			}

			if (WkwFrcTarget.NETWORKTABLE_NAME_ROBOT_SELECTED_TARGET
					.equals(pKey)
					&& (null != pValue)
					&& (pValue instanceof String)) {
				this.name = ((String) pValue);
			}

		}

		protected void adjustCameraPan() {
			if (this.degreesOffTarget > 3) {
				CameraSystem.this.panServo.setAngle(CameraSystem.this.panServo
						.getAngle() - 1);
			} else if (this.degreesOffTarget < 3) {
				CameraSystem.this.panServo.setAngle(CameraSystem.this.panServo
						.getAngle() + 1);
			}
		}

		public String getName() {
			return this.name;
		}

		public void setName(final String pName) {
			this.name = pName;
		}

		public boolean isFound() {
			return this.found;
		}

		public void setFound(final boolean pFound) {
			this.found = pFound;
		}

		public double getDegreesOffTarget() {
			return this.degreesOffTarget;
		}

		public void setDegreesOffTarget(final double pDegreesOffTarget) {
			this.degreesOffTarget = pDegreesOffTarget;
		}

		public double getDistance() {
			return this.distance;
		}

		public void setDistance(final double pDistance) {
			this.distance = pDistance;
		}

		public double getHeight() {
			return this.height;
		}

		public void setHeight(final double pHeight) {
			this.height = pHeight;
		}

	}

}
