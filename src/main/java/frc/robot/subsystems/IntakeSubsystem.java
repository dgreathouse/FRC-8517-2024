//Copyright (c) 2020-2023 Essexville Hampton Public Schools (FRC 8517)

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.lib.GD;
import frc.robot.lib.ISubsystem;
import frc.robot.lib.k;


public class IntakeSubsystem extends SubsystemBase implements ISubsystem {
  TalonFX m_leftMotor;

  VoltageOut leftVoltageOut = new VoltageOut(0);

  public void updateDashboard() {

  }

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {

    initialize();
  }

  public void initialize() {
    RobotContainer.subsystems.add(this);
    m_leftMotor = new TalonFX(k.ROBORIO_CAN_IDS.INTAKE_LEFT_SPIN);

  }
  public void spinOn(){
    GD.G_Intake_Speed = k.INTAKE.SPIN_SPEED_DEFAULT_VOLT;
  }
  public void spinOff(){
    GD.G_Intake_Speed = 0.0;
  }
  public void spin(double _volts){
    m_leftMotor.setControl(leftVoltageOut.withOutput(_volts).withEnableFOC(true));

  }

  @Override
  public void periodic() {
    spin(GD.G_Intake_Speed);
    // This method will be called once per scheduler run
  }
}
