// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class DiverterSubsystem extends SubsystemBase {
  private final XRPServo servo;

  private static DiverterSubsystem INSTANCE;
    public static DiverterSubsystem getInstance() {
      if (INSTANCE == null) {
        INSTANCE = new DiverterSubsystem();
    }
    return INSTANCE;
  }

  private DiverterSubsystem() {
    // Device number 4 maps to the physical Servo 1 port on the XRP
    servo = new XRPServo(Ports.DIVERTER_SERVO);
  }

  @Override
  public void periodic() {}

  /**
   * Set the current angle of the arm (0 - 180 degrees).
   *
   * @param angleDeg Desired arm angle in degrees
   */
  public void setAngle(double angleDeg) {
    servo.setAngle(angleDeg);
  }
}
