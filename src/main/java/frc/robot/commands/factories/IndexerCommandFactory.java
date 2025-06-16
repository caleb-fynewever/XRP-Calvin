package frc.robot.commands.factories;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.IndexerConstants;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexerCommandFactory {
    private final static IndexerSubsystem indexer = IndexerSubsystem.getInstance();

    public static Command indexUp() {
        return Commands.runEnd(() -> indexer.setMotorSpeed(IndexerConstants.UP_SPEED), () -> indexer.setMotorSpeed(0), indexer);
    }

    public static Command indexDown() {
        return Commands.runEnd(() -> indexer.setMotorSpeed(IndexerConstants.DOWN_SPEED), () -> indexer.setMotorSpeed(0), indexer);
    }
}
