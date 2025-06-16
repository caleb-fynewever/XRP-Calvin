// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Inches;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.xrp.XRPGyro;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  // The XRP has the left and right motors set to
  // channels 0 and 1 respectively
  private final XRPMotor leftMotor = new XRPMotor(Ports.LEFT_DRIVE_MOTOR);
  private final XRPMotor rightMotor = new XRPMotor(Ports.RIGHT_DRIVE_MOTOR);

  // The XRP has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder leftEncoder = new Encoder(Ports.LEFT_DRIVE_ENCODER_A, Ports.LEFT_DRIVE_ENCODER_B);
  private final Encoder rightEncoder = new Encoder(Ports.RIGHT_DRIVE_ENCODER_A, Ports.RIGHT_DRIVE_ENCODER_B);

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor::set, rightMotor::set);
  private final XRPGyro gyro = new XRPGyro();
  private final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();

  private static DrivetrainSubsystem INSTANCE;
    public static DrivetrainSubsystem getInstance() {
      if (INSTANCE == null) {
        INSTANCE = new DrivetrainSubsystem();
    }
    return INSTANCE;
  }
  private DrivetrainSubsystem() {
    SendableRegistry.addChild(diffDrive, leftMotor);
    SendableRegistry.addChild(diffDrive, rightMotor);

    rightMotor.setInverted(true);

    // Use inches as unit for encoder distances
    leftEncoder.setDistancePerPulse((Math.PI * DrivetrainConstants.WHEEL_DIAMETER.in(Inches)) / DrivetrainConstants.COUNTS_PER_REVOLUTION);
    rightEncoder.setDistancePerPulse((Math.PI * DrivetrainConstants.WHEEL_DIAMETER.in(Inches)) / DrivetrainConstants.COUNTS_PER_REVOLUTION);
    resetEncoders();
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public int getLeftEncoderCount() {
    return leftEncoder.get();
  }

  public int getRightEncoderCount() {
    return rightEncoder.get();
  }

  public double getLeftDistanceInch() {
    return leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return rightEncoder.getDistance();
  }

  public double getAverageDistanceInch() {
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }

  /**
   * The acceleration in the X-axis.
   *
   * @return The acceleration of the XRP along the X-axis in Gs
   */
  public double getAccelX() {
    return accelerometer.getX();
  }

  /**
   * The acceleration in the Y-axis.
   *
   * @return The acceleration of the XRP along the Y-axis in Gs
   */
  public double getAccelY() {
    return accelerometer.getY();
  }

  /**
   * The acceleration in the Z-axis.
   *
   * @return The acceleration of the XRP along the Z-axis in Gs
   */
  public double getAccelZ() {
    return accelerometer.getZ();
  }

  /**
   * Current angle of the XRP around the X-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  public double getGyroAngleX() {
    return gyro.getAngleX();
  }

  /**
   * Current angle of the XRP around the Y-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  public double getGyroAngleY() {
    return gyro.getAngleY();
  }

  /**
   * Current angle of the XRP around the Z-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  public double getGyroAngleZ() {
    return gyro.getAngleZ();
  }

  /** Reset the gyro. */
  public void resetGyro() {
    gyro.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
