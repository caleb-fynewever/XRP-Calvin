package frc.robot.commands.drive;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DefaultDriveCommand extends Command {
  private static final DrivetrainSubsystem drivetrain = DrivetrainSubsystem.getInstance();
  private final Supplier<Double> speedSupplier;
  private final Supplier<Double> rotationSupplier;
  public DefaultDriveCommand(Supplier<Double> speedSupplier, Supplier<Double> rotationSupplier) {
    this.speedSupplier = speedSupplier;
    this.rotationSupplier = rotationSupplier;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drivetrain.arcadeDrive(speedSupplier.get(), rotationSupplier.get());}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
