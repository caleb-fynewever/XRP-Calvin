package frc.robot.commands.factories;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommandFactory {
    private static final IntakeSubsystem intake = IntakeSubsystem.getInstance();

    public static Command intake() {
        return Commands.runEnd(() -> intake.setMotorSpeed(IntakeConstants.IN_SPEED), () -> intake.setMotorSpeed(0), intake);
    }

    public static Command outtake() {
        return Commands.runEnd(() -> intake.setMotorSpeed(IntakeConstants.OUT_SPEED), () -> intake.setMotorSpeed(0), intake);
    }
}
