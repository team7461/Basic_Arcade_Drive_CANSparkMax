package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private TalonSRX intakeTalon;
    private DoubleSolenoid solenoid;
    private boolean ret;
    public Intake()
    {
        intakeTalon = new TalonSRX(Constants.kIntake.INTAKE_PORT);
        intakeTalon.setInverted(Constants.kIntake.INVERTED);
        solenoid = new DoubleSolenoid(Constants.kIntake.PCM_PORT, Constants.kIntake.SOLENOID_FRONT, Constants.kIntake.SOLENOID_BACK);
        solenoid.set(DoubleSolenoid.Value.kOff);
        solenoid.set(DoubleSolenoid.Value.kReverse);
        ret = true;
    }

    public void startIntake()
    {
        intakeTalon.set(ControlMode.PercentOutput, Constants.kIntake.INTAKE_SPEED);
    }

    public void stopIntake()
    {
        intakeTalon.set(ControlMode.PercentOutput, 0);
    }
    
    public void actuate()
    {
        if(ret){
            solenoid.set(Value.kReverse);
            SmartDashboard.putBoolean("actuated", true);

        }
        else{
            solenoid.set(Value.kForward);
            SmartDashboard.putBoolean("actuated", false);
        }
        SmartDashboard.putBoolean("ret", ret);
        ret = !ret;

    }
}

