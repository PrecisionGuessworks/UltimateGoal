package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.BotUtilities;

public class TankDrivetrainSubsystem {
    private CRServo frontLeft, backLeft, frontRight, backRight;
    BotUtilities botStuff;
    Telemetry telemetry;

    //Important for curvature drive. Need to investigate further
    //the importance of them in the algorithm.
    public static final double DEFAULTQUICKSTOPTHRESHOLD = 0.2;
    public static final double DEFAULTQUICKSTOPALPHA = 0.1;
    public static final double QUICKSTOPTHRESHOLD = 0.1;

    private double m_quickStopThreshold = DEFAULTQUICKSTOPTHRESHOLD;
    private double m_quickStopAlpha = DEFAULTQUICKSTOPALPHA;
    private double m_quickStopAccumulator;

    public TankDrivetrainSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        frontLeft  = hardwareMap.get(CRServo.class, "FL");
        backLeft  = hardwareMap.get(CRServo.class, "BL");
        frontRight = hardwareMap.get(CRServo.class, "FR");
        backRight = hardwareMap.get(CRServo.class, "BR");

        frontLeft.setDirection(CRServo.Direction.REVERSE);
        backLeft.setDirection(CRServo.Direction.REVERSE);
        frontRight.setDirection(CRServo.Direction.FORWARD);
        backRight.setDirection(CRServo.Direction.FORWARD);

//        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.telemetry = telemetry;

        botStuff = new BotUtilities(telemetry);
    }

    public void setPower(double leftSpeed, double rightSpeed) {
        frontLeft.setPower(leftSpeed);
        backLeft.setPower(leftSpeed);
        frontRight.setPower(rightSpeed);
        backRight.setPower(rightSpeed);
    }

    public void setPower(double[] powers){
        setPower(powers[0], powers[1]);
    }



    /**
     * Tank drive method for differential drive platform.
     *
     * @param left The robot's power on the left drive pod. Forward is positive.
     * @param right The robot's power on the right drive pod. Forward is positive.
     */
    public void tankDrive(double left, double right) {
        setPower(deadband(left), deadband(right));
    }



    /**
     * Note: Code outline and comments borrow from WPILIB Documentation
     * Arcade drive method for differential drive platform.
     *
     * @param throttle The robot's power along the X axis [-1.0..1.0]. Forward is positive.
     * @param rotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
     *     positive.
     */
    public double[] arcadeDrive(double throttle, double rotation) {

        double leftMotorOutput, rightMotorOutput;

        throttle = deadband(throttle);
        rotation = deadband(rotation);

        leftMotorOutput = throttle + rotation;
        rightMotorOutput = throttle - rotation;

        double[] motorPowers = {leftMotorOutput, rightMotorOutput};
        setPower(motorPowers);
        return motorPowers;
    }   // arcadeDrive

    /**
     * Note: Code outline and comments borrow from WPILIB Documentation
     * Curvature drive method for differential drive platform.
     *
     * <p>The rotation argument controls the curvature of the robot's path rather than its rate of
     * heading change. This makes the robot more controllable at high speeds. Also handles the robot's
     * quick turn functionality - "quick turn" overrides constant-curvature turning for turn-in-place
     * maneuvers.
     *
     * @param throttle The robot's power along the X axis [-1.0..1.0]. Forward is positive.
     * @param rotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
     *     positive.
     * @param isQuickTurn If set, overrides constant-curvature turning for turn-in-place maneuvers.
     */

    public void curvatureDrive(double throttle, double rotation, boolean isQuickTurn){
        throttle = clampDriveVal(throttle, -1.0, 1.0);
        throttle = deadband(throttle);

        rotation = clampDriveVal(throttle, -1.0, 1.0);
        rotation = deadband(rotation);

        double angularPower;
        boolean overPower; //Used to determine is the drive power need

        if(isQuickTurn){
            if (Math.abs(throttle)  < m_quickStopThreshold){
                m_quickStopAccumulator = (1 - m_quickStopAlpha) * m_quickStopAccumulator
                        + m_quickStopAlpha * rotation * 2;
            }
            overPower = true;
            angularPower = rotation;
        }else{
            overPower = false;
            angularPower = Math.abs(throttle) * rotation;

            //Start to reset the quickStopAccumulator
            if (m_quickStopAccumulator > 1){
                m_quickStopAccumulator -= 1;
            }else if(m_quickStopAccumulator < -1){
                m_quickStopAccumulator += 1;
            }else{
                m_quickStopAccumulator = 0.0;
            }

        }

        double leftMotorOutput = throttle + angularPower;
        double rightMotorOutput = throttle - angularPower;

        //If rotation is overpowered, reduce both output to within acceptable range.
        //This prevents unintended throttling
        //Only happens when quickturning.
        if (overPower){
            if (leftMotorOutput > 1.0){
                rightMotorOutput -= leftMotorOutput - 1.0;
                leftMotorOutput = 1.0;
            } else if (rightMotorOutput > 1.0){
                leftMotorOutput -= rightMotorOutput - 1.0;
                rightMotorOutput = 1.0;
            } else if (leftMotorOutput < -1.0){
                rightMotorOutput -= leftMotorOutput + 1.0;
                leftMotorOutput = -1.0;
            } else if (rightMotorOutput < -1.0){
                leftMotorOutput -= rightMotorOutput + 1.0;
                rightMotorOutput = -1.0;
            }
        }
        //Normalize the powers, then set them.
        setPower(normalizePowers(leftMotorOutput, rightMotorOutput));
    }

    public void curvatureDrive(double throttle, double rotation){
        boolean isQuickTurn = throttle > QUICKSTOPTHRESHOLD;
        curvatureDrive(throttle, rotation, isQuickTurn);
    }

    public double[] normalizePowers(double left, double right){
        double maxMagnitude = Math.max(Math.abs(left), Math.abs(right));
        if (maxMagnitude > 1.0) {
            left /= maxMagnitude;
            right /= maxMagnitude;
        }
        double[] powers = {left, right};
        return powers;
    }

    public double deadband(double x) {
        double deadBand = 0.1;
        if (Math.abs(x) < deadBand) {
            x = 0.0;
        }
        return x;
    }   // deadband

    public double clampDriveVal(double val, double min, double max){
        //Clamps a value between a min and max
        //Take a value and return it if it's between max and min
        //If not between, return what's closest.
        if (val < min){
            return min;
        }else if(val > max){
            return max;
        }else{
            return val;
        }
    }
}
