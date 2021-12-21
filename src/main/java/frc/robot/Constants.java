// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static class kVDriveTrain {
        public final static int FL = 1, FR = 15, BL = 42, BR = 14; 
        public final static MotorType MOTOR_TYPE = MotorType.kBrushless;
        public static final int CURRENT_LIMIT = 35;
        public static final boolean DRIVE_INVERTED = false;
    }
    public final static class kOI {
        public static final int DRIVE_CONTROLLER = 0;
        public static final int OPERATOR_CONTROLLER = 1;
    }
    public final static class kFlywheel
    {
        public static final int MAIN_ID = 3;
        public static final int FOLLOWER_ID = 13;
        public final static MotorType MOTOR_TYPE = MotorType.kBrushless;
        public static final int CURRENT_LIMIT = 35;
        public static final boolean INVERTED = true;
        public static final double OPEN_LOOP_RAMP = 1;

        public static final double kP = 0.001, kI = 0, kD = 0.0005;
        // public static final double MAX_VELOCITY = 0, MAX_ACCELERATION = 0, ERR_TOLERANCE = 0;
        public static final double GOAL = 3000;
        public static final double GOAL_TOLERANCE = 0.90;

        public static final double kS = 0, kV = 0, kA = 0;
    }
    public final static class kHood
    {   
        public static final int MOTOR_ID = 4;
        public static final MotorType MOTOR_TYPE = MotorType.kBrushless;
        public static final double MAX_SPEED = 0.3;
        public static final double kP = 0.1;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double INIT_LINE_ANGLE = 7.1;
        public static final double kFF = 0.0;
        public static final double HOOD_INCREMENT = 0.5;
        public static final double MAX_ANGLE = 15;
    }
    public final static class kHopper
    {

    }
    public final static class kIntake
    {
        public static final int INTAKE_PORT = 7;
        public static final int PCM_PORT = 17;
        public static final double INTAKE_SPEED = 0.60007461;
        public static final int SOLENOID_FRONT = 1;
        public static final int SOLENOID_BACK = 0;
        public static final boolean INVERTED = true;
    }
}
