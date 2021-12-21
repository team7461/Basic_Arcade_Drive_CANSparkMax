package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends SubsystemBase {
    /**
     * Creates a new ExampleSubsystem.
     */
    public final CANSparkMax frontRight;
    public final CANSparkMax backRight;
    public final CANSparkMax frontLeft;
    public final CANSparkMax backLeft;
    private final  DifferentialDrive diffDrive;
    
    public Drivetrain() {
        frontRight = new CANSparkMax(Constants.kVDriveTrain.FR, Constants.kVDriveTrain.MOTOR_TYPE);
        backRight = new CANSparkMax(Constants.kVDriveTrain.BR, Constants.kVDriveTrain.MOTOR_TYPE);
        frontLeft = new CANSparkMax(Constants.kVDriveTrain.FL, Constants.kVDriveTrain.MOTOR_TYPE);
        backLeft = new CANSparkMax(Constants.kVDriveTrain.BL, Constants.kVDriveTrain.MOTOR_TYPE);

        frontLeft.restoreFactoryDefaults();
        frontRight.restoreFactoryDefaults();
        backLeft.restoreFactoryDefaults();
        backRight.restoreFactoryDefaults();

        frontLeft.setSmartCurrentLimit(Constants.kVDriveTrain.CURRENT_LIMIT);
        frontRight.setSmartCurrentLimit(Constants.kVDriveTrain.CURRENT_LIMIT);
        backLeft.setSmartCurrentLimit(Constants.kVDriveTrain.CURRENT_LIMIT);
        backRight.setSmartCurrentLimit(Constants.kVDriveTrain.CURRENT_LIMIT);

        frontLeft.setInverted(Constants.kVDriveTrain.DRIVE_INVERTED);
        frontRight.setInverted(Constants.kVDriveTrain.DRIVE_INVERTED);
        backLeft.setInverted(Constants.kVDriveTrain.DRIVE_INVERTED);
        backRight.setInverted(Constants.kVDriveTrain.DRIVE_INVERTED);

        diffDrive = new DifferentialDrive(frontLeft, frontRight);
        //front motors are controlled, others follow corresponding
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }
    public void curveDrive(double linearVelocity, double angularVelocity, boolean isQuickturn) {

          diffDrive.curvatureDrive(linearVelocity, angularVelocity, isQuickturn);
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  }
