package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase
{
    public final CANSparkMax main;
    public final CANSparkMax follow;
    public final CANEncoder mainEncoder;
    private final SimpleMotorFeedforward feedForward;
    private final PIDController pidController;
    private double goal = 0;
    public double velocity;

    public Flywheel(){
        main = new CANSparkMax(Constants.kFlywheel.MAIN_ID, Constants.kFlywheel.MOTOR_TYPE);
        follow = new CANSparkMax(Constants.kFlywheel.FOLLOWER_ID, Constants.kFlywheel.MOTOR_TYPE);

        mainEncoder = main.getEncoder();

        main.restoreFactoryDefaults();
        follow.restoreFactoryDefaults();

        main.setSmartCurrentLimit(Constants.kFlywheel.CURRENT_LIMIT);
        follow.setSmartCurrentLimit(Constants.kFlywheel.CURRENT_LIMIT);

        main.setInverted(Constants.kFlywheel.INVERTED);
        follow.follow(main, true);

        velocity = 0;

        main.setOpenLoopRampRate(Constants.kFlywheel.OPEN_LOOP_RAMP);
        follow.setOpenLoopRampRate(Constants.kFlywheel.OPEN_LOOP_RAMP);

        pidController = new PIDController(
            Constants.kFlywheel.kP,
            Constants.kFlywheel.kI, 
            Constants.kFlywheel.kD);

        feedForward = new SimpleMotorFeedforward(
            Constants.kFlywheel.kS, 
            Constants.kFlywheel.kV, 
            Constants.kFlywheel.kA);

            main.burnFlash();
            follow.burnFlash();

            main.setIdleMode(IdleMode.kCoast);
            follow.setIdleMode(IdleMode.kCoast);
        
    }    
    public void setSpeed() {
        goal = Constants.kFlywheel.GOAL;
    }

    public void stop() {
        goal = 0;
    }

    @Override
    public void periodic()
    {
        double output = pidController.calculate(mainEncoder.getVelocity(), goal);
        SmartDashboard.putNumber("Flywheel Velocity", mainEncoder.getVelocity());
        SmartDashboard.putNumber("Output", output);
        SmartDashboard.putNumber("setPoint", pidController.getSetpoint());
        SmartDashboard.putNumber("Applied Output", main.getAppliedOutput());
        SmartDashboard.putNumber("Goal ", goal);
        // if(goal == 0)
        //     main.set(0);
        // else
            main.set(2);
    }
}
