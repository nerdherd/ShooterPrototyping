package frc.robot.subsystem;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public class Shooter {
    private TalonFX leftShooter;
    private TalonFX rightShooter;
    double percentOutputTop = 0.5;
    double percentOutputBottom = 0.1;

    private double[] speeds = {-0.1, 0.5, 0.7};
    private double[] speeds2 = {-0.1, 0.5, 0.7};
    private int index = 0;

    public Shooter(){
        leftShooter = new TalonFX(10);
        rightShooter = new TalonFX(11);
        
        leftShooter.configVoltageCompSaturation(11);
        rightShooter.configVoltageCompSaturation(11);

        leftShooter.enableVoltageCompensation(true);
        rightShooter.enableVoltageCompensation(false);
        
        leftShooter.setInverted(false);
        rightShooter.setInverted(true);
        // rightShooter.setInverted(TalonFXInvertType.FollowMaster);
        
        SmartDashboard.putNumber("Power", 0);
    }

    public void printSpeeds() {
        SmartDashboard.putNumber("Index", index);
        SmartDashboard.putNumber("IntakeTop", speeds[0]);
        SmartDashboard.putNumber("OuttakeTop1", speeds[1]);
        SmartDashboard.putNumber("OuttakeTop2", speeds[2]);
        SmartDashboard.putNumber("IntakeBottom", speeds2[0]);
        SmartDashboard.putNumber("OuttakeBottom1", speeds2[1]);
        SmartDashboard.putNumber("OuttakeBottom2", speeds2[2]);
    }

    public CommandBase setIndex(int index) {
        return Commands.runOnce(() -> {
            this.index = index;
        });
    }

    public CommandBase setSpeed() {
        return Commands.runOnce(() -> {
            leftShooter.set(ControlMode.PercentOutput, speeds[index]);
            rightShooter.set(ControlMode.PercentOutput, speeds2[index]);
        });
    }

    // public CommandBase intake() {
    //     return Commands.runOnce(() -> {
    //         leftShooter.set(ControlMode.PercentOutput, -0.10);
    //         rightShooter.set(ControlMode.PercentOutput, -0.10);
    //         SmartDashboard.putBoolean("Intake", true);
    //     });
    // }
    // public CommandBase outtakeSlow() {
    //     return Commands.runOnce(() -> {
    //         leftShooter.set(ControlMode.PercentOutput, percentOutputTop);
    //         rightShooter.set(ControlMode.PercentOutput, percentOutputBottom);
    //     });
    // }
    // public CommandBase outtakeFast() {
    //     return Commands.runOnce(() -> {
    //         leftShooter.set(ControlMode.PercentOutput, percentOutputTop);
    //         rightShooter.set(ControlMode.PercentOutput, percentOutputBottom);
    //     });
    // }

    public CommandBase setPowerZero() {
        return Commands.runOnce(() -> {
            leftShooter.set(ControlMode.PercentOutput, 0);
            rightShooter.set(ControlMode.PercentOutput, 0);
        });
    }

    public CommandBase increaseTop() {
        return Commands.runOnce(() -> {
            this.speeds[index] += 0.1;
        });
    }
    public CommandBase increaseBottom() {
        return Commands.runOnce(() -> {
            this.speeds2[index] += 0.1;
        });
    }

    public CommandBase decreaseTop() {
        return Commands.runOnce(() -> {
            this.speeds[index] -= 0.1;
        });
    }
    public CommandBase decreaseBottom() {
        return Commands.runOnce(() -> {
            this.speeds2[index] -= 0.1;
        });
    }

    // public void increaseOutputTop() {
    //     percentOutputTop += .1;
    // }

    // public void increaseOutputBottom() {
    //     percentOutputBottom += .1;
    // }

    // public void decreaseOutputTop() {
    //     percentOutputTop -= .1;
    // }

    // public void decreaseOutputBottom() {
    //     percentOutputBottom -= .1;
    // }

    public void reportToSmartDashboard(){
        // leftShooter.set(ControlMode.PercentOutput, SmartDashboard.getNumber("Power", 0));
        // SmartDashboard.putNumber("Left RPM", leftShooter.getSelectedSensorVelocity(0) * 10 / 2048);
        // SmartDashboard.putNumber("Right RPM", rightShooter.getSelectedSensorVelocity(0) * 10 / 2048);
        // SmartDashboard.putNumber("Left Current", leftShooter.getSupplyCurrent());
        // SmartDashboard.putNumber("Right Current", rightShooter.getSupplyCurrent());
        SmartDashboard.putNumber("Percent Output Bottom", percentOutputBottom);
        SmartDashboard.putNumber("Percent Output Top", percentOutputTop);
    }

}
