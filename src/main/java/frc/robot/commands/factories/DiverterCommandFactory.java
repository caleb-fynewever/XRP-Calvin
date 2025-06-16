package frc.robot.commands.factories;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.DiverterConstants;
import frc.robot.subsystems.DiverterSubsystem;

public class DiverterCommandFactory {
    private final static DiverterSubsystem diverter = DiverterSubsystem.getInstance();
    
    public static final Command setToIntake() {
        return Commands.runOnce(() -> diverter.setAngle(DiverterConstants.INTAKE_ANGLE), diverter);
    }
    
    public static final Command setToLeftScore() {
        return Commands.runOnce(() -> diverter.setAngle(DiverterConstants.LEFT_SHOOT_ANGLE), diverter);
    }

    public static final Command setToRightScore() {
        return Commands.runOnce(() -> diverter.setAngle(DiverterConstants.RIGHT_SHOOT_ANGLE), diverter);
    }
}
