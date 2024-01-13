// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystem.Shooter;

public class RobotContainer {
  private final CommandPS4Controller commandOperatorController = new CommandPS4Controller(1);
  private final PS4Controller operatorController = commandOperatorController.getHID();
  public Shooter shooter = new Shooter();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    commandOperatorController.povUp().onTrue(shooter.increaseTop());
    commandOperatorController.povDown().onTrue(shooter.decreaseTop());

    commandOperatorController.povLeft().onTrue(shooter.increaseBottom());
    commandOperatorController.povRight().onTrue(shooter.decreaseBottom());

    // commandOperatorController.triangle()
    //   .whileTrue(shooter.setIndex(0).andThen(shooter.setSpeed()))
    //   .onFalse(shooter.setPowerZero());

    commandOperatorController.triangle()
      .whileTrue(shooter.setTargetSpeed())
      .onFalse(shooter.setPowerZero());
    
    commandOperatorController.square()
      .whileTrue(shooter.setIndex(1).andThen(shooter.setSpeed()))
      .onFalse(shooter.setPowerZero());

    commandOperatorController.circle()
      .whileTrue(shooter.setIndex(2).andThen(shooter.setSpeed()))
      .onFalse(shooter.setPowerZero());

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void initShuffleboard() {
    shooter.reportToSmartDashboard();
  }
}

