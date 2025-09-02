// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.auto.generated.AutonomousDistance;
import frc.robot.commands.auto.generated.AutonomousTime;
import frc.robot.commands.drive.DefaultDriveCommand;
import frc.robot.commands.factories.DiverterCommandFactory;
import frc.robot.commands.factories.IndexerCommandFactory;
import frc.robot.commands.factories.IntakeCommandFactory;
import frc.robot.subsystems.DiverterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPOnBoardIO;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
public class RobotContainer {
  private final DrivetrainSubsystem drivetrain = DrivetrainSubsystem.getInstance();
  private final DiverterSubsystem diverter = DiverterSubsystem.getInstance();
  private final IndexerSubsystem indexer = IndexerSubsystem.getInstance();
  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  private final XRPOnBoardIO onboardIO = new XRPOnBoardIO();

  private final Joystick controller = new Joystick(0);

  private final SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(new DefaultDriveCommand(() -> -controller.getRawAxis(1), () -> -controller.getRawAxis(2)));
    JoystickButton intakeButton = new JoystickButton(controller, 1);
    intakeButton.whileTrue(Commands.parallel(
      DiverterCommandFactory.setToIntake(),
      IntakeCommandFactory.intake(), 
      IndexerCommandFactory.indexUp())
    );
    JoystickButton rightScore = new JoystickButton(controller, 2);
    rightScore.onTrue(DiverterCommandFactory.setToRightScore()).whileTrue(IndexerCommandFactory.indexUp());
    JoystickButton leftScore = new JoystickButton(controller, 3);
    leftScore.onTrue(DiverterCommandFactory.setToLeftScore()).whileTrue(IndexerCommandFactory.indexUp());
    


    chooser.setDefaultOption("Auto Routine Distance", new AutonomousDistance(drivetrain));
    chooser.addOption("Auto Routine Time", new AutonomousTime(drivetrain));
    SmartDashboard.putData(chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
