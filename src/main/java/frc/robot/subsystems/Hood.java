package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hood extends SubsystemBase {
    private final CANSparkMax hoodMain;
    private final CANEncoder hoodEncoder;
    private final CANPIDController hoodController;
    private double currentDegree = 0;
    private double initialSetpoint;

    public Hood() {
        hoodMain = new CANSparkMax(Constants.kHood.MOTOR_ID, Constants.kHood.MOTOR_TYPE);
        hoodMain.setInverted(true);
        hoodMain.setIdleMode(CANSparkMax.IdleMode.kCoast);
        hoodEncoder = hoodMain.getEncoder();
        hoodController = this.hoodMain.getPIDController();
        hoodController.setOutputRange(-Constants.kHood.MAX_SPEED, Constants.kHood.MAX_SPEED);
        hoodController.setP(Constants.kHood.kP);
        hoodController.setI(Constants.kHood.kI);
        hoodController.setD(Constants.kHood.kD);
        initialSetpoint = Constants.kHood.INIT_LINE_ANGLE;
        set(initialSetpoint);
    }
    public void set(double setpoint) {
        hoodController.setReference(setpoint + Constants.kHood.kFF, ControlType.kPosition);
    }
    public void incrementUp() {
        increaseSetpoint(Constants.kHood.HOOD_INCREMENT);
      }
      public void incrementDown() {
        increaseSetpoint(-Constants.kHood.HOOD_INCREMENT);
      }
      public void increaseSetpoint(double amount) {
        currentDegree+=amount;
        currentDegree=Math.max(0, currentDegree);
        currentDegree=Math.min(Constants.kHood.MAX_ANGLE-Constants.kHood.INIT_LINE_ANGLE, currentDegree);
        set(currentDegree+initialSetpoint);
        SmartDashboard.putNumber("hoodAngle", currentDegree);
        SmartDashboard.putNumber("Initial_Setpoint", initialSetpoint);
      }
}
