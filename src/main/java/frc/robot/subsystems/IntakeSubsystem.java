// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class IntakeSubsystem extends SubsystemBase {
  private final XRPMotor motor;
  private static IntakeSubsystem INSTANCE;
    public static IntakeSubsystem getInstance() {
      if (INSTANCE == null) {
        INSTANCE = new IntakeSubsystem();
    }
    return INSTANCE;
  }
  private IntakeSubsystem() {
    motor = new XRPMotor(Ports.INTAKE_MOTOR);
  }

  public void setMotorSpeed(double speed) {
    motor.set(speed);
  }

  @Override
  public void periodic() {}
}
