//Copyright (c) 2020-2023 Essexville Hampton Public Schools (FRC 8517)

package frc.robot.commandGroups;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drive.AutoDriveDelayCommand;
import frc.robot.commands.Drive.AutoDriveOdometry;
import frc.robot.commands.Drive.AutoDriveRotateCommand;
import frc.robot.commands.Shooter.FlipperSetCommand;
import frc.robot.commands.Shooter.ShooterSetShotCommand;
import frc.robot.lib.FlipperStates;
import frc.robot.lib.ShooterState;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class Auto3Note extends SequentialCommandGroup {
  public Auto3Note(DrivetrainSubsystem _drive, ShooterSubsystem _shooter, IntakeSubsystem _intake) {
    // GD.G_AllianceSign is a mutilpier to change the direction of an X, Y Pose or an Angle.
    // By default this play is setup for the Blue side and when Red some values need to be multiplied by -1.0 to change the direction or location.
    // Pose X positive is away from the drivers and Y positive is to the left.
    // The starting Pose for the robot is (0,0) (X,Y)

    addCommands(
      new ShooterSetShotCommand(_shooter, ShooterState.PODIUM),                                                                                         // Set Shooter Speed
      new AutoDriveOdometry(_drive, new Pose2d(Units.inchesToMeters(36),Units.inchesToMeters(6.1),new Rotation2d(Math.toDegrees(0))), 4),               // Drive to side

      new AutoDriveRotateCommand(_drive, 40, 0.5),                                                                                                      // Rotate to Speaker
      new FlipperSetCommand(_shooter, FlipperStates.SHOOT),                                                                                             // Shoot Note #1
      new AutoDriveDelayCommand(_drive, 0.5),                                                                                                           // Delay for Note to be released
      new FlipperSetCommand(_shooter, FlipperStates.BACK),                                                                                              // Set flippers back
      new AutoDriveRotateCommand(_drive, 0, .5),                                                                                                        // Rotate to Straight

      new InstantCommand(_intake::spinOn),                                                                                                              // Turn on the intake
      new AutoDriveOdometry(_drive, new Pose2d(Units.inchesToMeters(32),Units.inchesToMeters(-14.05),new Rotation2d(Math.toDegrees(0))), 4),            // Strafe to second note
      new AutoDriveOdometry(_drive, new Pose2d(Units.inchesToMeters(60),Units.inchesToMeters(-12.4),new Rotation2d(Math.toDegrees(0))), 4),             // Drive back to Note 

      new AutoDriveRotateCommand(_drive, 30, 0.5),                                                                                                      // Rotate to Speaker
      new FlipperSetCommand(_shooter, FlipperStates.SHOOT),                                                                                             // Shoot Note #2
      new AutoDriveDelayCommand(_drive, 0.5),                                                                                                           // Delay for Note to be released
      new FlipperSetCommand(_shooter, FlipperStates.BACK),                                                                                              // Set flippers back
      new AutoDriveRotateCommand(_drive, 0, 0.5),                                                                                                       // Rotate to Straight

       new AutoDriveOdometry(_drive, new Pose2d(Units.inchesToMeters(30),Units.inchesToMeters(-67.00),new Rotation2d(Math.toDegrees(0))), 4),        // Drive at angle to 3rd Note
      new AutoDriveOdometry(_drive, new Pose2d(Units.inchesToMeters(74.74),Units.inchesToMeters(-64.00),new Rotation2d(Math.toDegrees(0))), 4),         // Drive back to note

      new AutoDriveDelayCommand(_drive, 0.5),                                                                                                           // Delay for Note to be grabbed
      new FlipperSetCommand(_shooter, FlipperStates.SHOOT),                                                                                             // Shoot Note #3
      new AutoDriveDelayCommand(_drive, 0.5),                                                                                                           // Delay for Note to be released
      new FlipperSetCommand(_shooter, FlipperStates.BACK),                                                                                              // Set flippers back
      new InstantCommand(_intake::spinOff),                                                                                                             // Turn off spinners
      new ShooterSetShotCommand(_shooter, ShooterState.OFF)                                                                                             // Turn off shooter
    );
  }
}
