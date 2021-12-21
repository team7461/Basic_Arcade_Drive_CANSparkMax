// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XboxController driveController = new XboxController(Constants.kOI.DRIVE_CONTROLLER);
  private final XboxController operatorController = new XboxController(Constants.kOI.OPERATOR_CONTROLLER);
  private final Drivetrain drivetrain = new Drivetrain();
  //private final Flywheel flywheel = new Flywheel();
  private final Hood hood = new Hood();
  private final Intake intake = new Intake();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drivetrain.setDefaultCommand(
      new RunCommand(() -> drivetrain.curveDrive(
        OI.getTriggers(driveController), OI.getLeftStick(driveController), driveController.getXButton()), drivetrain));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //new JoystickButton(driveController, XboxController.Button.kY.value)
    //    .whenHeld(new RunCommand(() -> flywheel.setSpeed(), flywheel))
    //    .whenReleased(new RunCommand(() -> flywheel.stop(), flywheel));
    new JoystickButton(driveController, XboxController.Button.kA.value)
        .whenPressed(new InstantCommand(() -> hood.incrementUp(), hood));
    new JoystickButton(driveController, XboxController.Button.kB.value)
        .whenPressed(new InstantCommand(() -> hood.incrementDown(), hood));
    new JoystickButton(driveController, XboxController.Button.kStart.value)
        .whenPressed(new InstantCommand(() -> intake.actuate(), intake));
    new JoystickButton(driveController, XboxController.Button.kBack.value)
        .whenHeld(new RunCommand(() -> intake.startIntake(), intake))
        .whenReleased(new InstantCommand(() ->   intake.stopIntake(), intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
