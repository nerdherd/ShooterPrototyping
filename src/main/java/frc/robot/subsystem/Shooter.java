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

    private double[] speedsLeft = {-0.1, 0.1, 0.0};
    private double[] speedsRight = {-0.1, 0.1, 0.0};

    double percentOutputTop = 0.0;
    double percentOutputBottom = 0.0;

    int velocityTop = 0; // 11700 is 60% output
    int velocityBottom = 0;

    private int index = 0;

    private boolean tooHigh = false;
    private boolean tooLow = false;

    public Shooter(){
        leftShooter = new TalonFX(19);
        rightShooter = new TalonFX(20);
        
        leftShooter.configVoltageCompSaturation(11);
        rightShooter.configVoltageCompSaturation(11);

        leftShooter.enableVoltageCompensation(true);
        rightShooter.enableVoltageCompensation(false);
        
        leftShooter.setInverted(false);
        rightShooter.setInverted(false);
        // rightShooter.setInverted(TalonFXInvertType.FollowMaster);
        
        SmartDashboard.putNumber("Power", 0);
    }

    public void printSpeeds() {
        SmartDashboard.putNumber("Index", index);

        // SmartDashboard.putNumber("IntakeTop", speedsLeft[0]);
        // SmartDashboard.putNumber("IntakeBottom", speedsRight[0]);

        // SmartDashboard.putNumber("OuttakeTop1", speedsLeft[1]);
        // SmartDashboard.putNumber("OuttakeBottom1", speedsRight[1]);

        // SmartDashboard.putNumber("OuttakeTop2", speedsLeft[2]);
        // SmartDashboard.putNumber("OuttakeBottom2", speedsRight[2]);

        // SmartDashboard.putNumber("Percent Output Bottom: ", percentOutputBottom);
        // SmartDashboard.putNumber("Percent Output Top: ", percentOutputTop);

        SmartDashboard.putNumber("Ticks Per Second Top: ", velocityTop);
        SmartDashboard.putNumber("Ticks Per Second Bottom ", velocityBottom);


    }

    public CommandBase setIndex(int index) {
        return Commands.runOnce(() -> {
            this.index = index;
        });
    }

    public CommandBase setSpeed() {
        return Commands.run(() -> {
            // leftShooter.set(ControlMode.PercentOutput, speedsLeft[index]); // TODO DEBUG
            // rightShooter.set(ControlMode.PercentOutput, speedsRight[index]);

            // leftShooter.set(ControlMode.PercentOutput, percentOutputTop); // TODO DEBUG
            // rightShooter.set(ControlMode.PercentOutput, percentOutputBottom);

            leftShooter.set(ControlMode.Velocity, velocityTop); // TODO DEBUG
            rightShooter.set(ControlMode.Velocity, velocityBottom);
        });
    }

    public CommandBase setPowerZero() {
        return Commands.runOnce(() -> {
            leftShooter.set(ControlMode.PercentOutput, 0);
            rightShooter.set(ControlMode.PercentOutput, 0);
        });
    }

    public CommandBase increaseTop() {
        return Commands.runOnce(() -> {
            // if (percentOutputTop <= 0.9) {
            //     this.speedsLeft[2] += 0.05; // TODO DEBUG
            //     percentOutputTop += 0.05;
            //     tooHigh = false;
            // }

            if(velocityTop <= 21000) {
                velocityTop += 1024;
                tooHigh = false;
            }
            else {
                tooHigh = true;
            }
            SmartDashboard.putBoolean("Too high", tooHigh);
        });
    }

    public CommandBase increaseBottom() {
        return Commands.runOnce(() -> {
            // if(percentOutputBottom <= 0.9) {
            //     this.speedsRight[2] += 0.05; // TODO DEBUG
            //     percentOutputBottom += 0.05;
            //     tooHigh = false;

            if(velocityBottom <= 21000) {
                velocityBottom += 1024;
                tooHigh = false;
            }
            else {
                tooHigh = true;
            }
            SmartDashboard.putBoolean("Too high", tooHigh);
        });
    }

    public CommandBase decreaseTop() {
        return Commands.runOnce(() -> {
            // if(percentOutputTop >= 0.051) {
            //     this.speedsLeft[2] -= 0.05; // TODO DEBUG
            //     percentOutputTop -= 0.05;
            //     tooLow = false;

            if(velocityTop >= 1100) {
                velocityTop -= 1024;
                tooLow = false;
            }
            else {
                tooLow = true;
            }
            SmartDashboard.putBoolean("Too low", tooLow);
        });
    }

    public CommandBase decreaseBottom() {
        return Commands.runOnce(() -> {
            // if(percentOutputBottom >= 0.051) {
            //     this.speedsRight[2] -= 0.05; // TODO DEBUG
            //     percentOutputBottom -= 0.05;
            //     tooLow = false;
            if(velocityBottom >= 1100) {
                velocityBottom -= 1024;
                tooLow = false;
            }
            else {
                tooLow = true;
            }
            SmartDashboard.putBoolean("Too low", tooLow);
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
        SmartDashboard.putNumber("Left RPS", leftShooter.getSelectedSensorVelocity(0) * 10 / 2048);
        SmartDashboard.putNumber("Right RPS", rightShooter.getSelectedSensorVelocity(0) * 10 / 2048);
        SmartDashboard.putNumber("Left Speed", leftShooter.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("Right Speed", rightShooter.getSelectedSensorVelocity(0));
        // SmartDashboard.putNumber("Left Current", leftShooter.getSupplyCurrent());
        // SmartDashboard.putNumber("Right Current", rightShooter.getSupplyCurrent());
        
    }

}
